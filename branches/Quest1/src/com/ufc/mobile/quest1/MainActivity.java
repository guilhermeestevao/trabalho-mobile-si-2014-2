package com.ufc.mobile.quest1;

import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ufc.mobile.quest1.model.Local;
import com.ufc.mobile.quest1.util.LocalREST;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GoogleMap googleMap;
	private List<Local> locais;
	private Location myLocation;
	private static final LatLng QUIXADA = new LatLng(-4.96843850, -39.016125);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			// Loading map
			initilizeMap();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			new DownloadJsonAsyncTask().execute();
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(QUIXADA, 15));
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
			
			googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
				@Override
				public void onInfoWindowClick(Marker marker) {
					Intent it = new Intent("AVALIAR");
					long id = getLocalTitle(marker.getTitle());
					
					it.putExtra("local", marker.getTitle());
					it.putExtra("id", id);
					startActivity(it);
				}
				
			});
			
			googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {
				
				@Override
				public View getInfoWindow(Marker marker) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public View getInfoContents(Marker marker) {
					
					long id = getLocalTitle(marker.getTitle());
					
					Uri uri = Uri.parse("content://com.mobile.praticaquest5.contentprovider.aluno.provider/avaliacao");
					uri = Uri.withAppendedPath(uri, String.valueOf(id));
					Cursor cursor = getContentResolver().query(uri, null, null, null, null);
					
					LinearLayout ll = new LinearLayout(MainActivity.this);
					ll.setOrientation(LinearLayout.VERTICAL);
					
					TextView tv = new TextView(MainActivity.this);
					tv.setGravity(Gravity.CENTER);
					tv.setTextSize(20);
					tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
					tv.setText(marker.getTitle());
					ll.addView(tv);
					
					TextView tv1 = new TextView(MainActivity.this);
					tv1.setGravity(Gravity.CENTER);
					tv1.setText(marker.getSnippet());
					ll.addView(tv1);
					
					cursor.moveToFirst();
					
					RatingBar rb = new RatingBar(MainActivity.this);
					rb.setRating((float) cursor.getDouble(0));
					ll.addView(rb);
					
					cursor.close();
					
					return ll;
				}
			});
			
		}
	}

	private long getLocalTitle(String title){
		for (Local local : this.locais) {
			if(local.getName().equals(title))
				return local.getId();
		}
		
		return 0;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.options, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.visualizarLocais:
			Intent it = new Intent(this, ListaLocaisActivity.class);
			ListaLocaisActivity.locais = this.locais;
			startActivity(it);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	class DownloadJsonAsyncTask extends AsyncTask<Void, Void, List<Local>>{

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(MainActivity.this, "Aguarde",
					"Buscando pontos...");
		}

		@Override
		protected List<Local> doInBackground(Void... params) {
			locais = new LocalREST().getLocais();
			return locais;
		}

		@Override
		protected void onPostExecute(List<Local> result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if(result == null){
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this)
				.setTitle("Atencao")
				.setMessage(
						"Nao foi possivel acessar essas informacoes...")
						.setPositiveButton("OK", null);
				builder.create().show();
			}else{
				addPontos(locais);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this)
				.setTitle("Atencao")
				.setMessage(
						"Foram carregados "+result.size()+" pontos")
						.setPositiveButton("OK", null);
				builder.create().show();
			}
		}		

		private void addPontos(List<Local> locais){
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true); 
			googleMap.setMyLocationEnabled(true);
			String locationProvider = LocationManager.NETWORK_PROVIDER;
			LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			myLocation = locationManager.getLastKnownLocation(locationProvider);

			for (Local local : locais) {
				double latitude = local.getLocation().getLat();
				double longitude = local.getLocation().getLng();
				
				MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).
						title(local.getName()).snippet("Clique aqui para avaliar o local");			 
				
				googleMap.addMarker(marker);
				LatLng latLngLocal = new LatLng(latitude, longitude);
				LatLng myLatLgn = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
				double distancia = distance(myLatLgn, latLngLocal);
				local.setDistancia(distancia);
			
			}
				
		}

		private double distance(LatLng start, LatLng end){
			double lat1 = start.latitude;
			double lat2 = end.latitude;
			double lon1 = start.longitude;
			double lon2 = end.longitude;
			double dLat = Math.toRadians(lat2-lat1);
			double dLon = Math.toRadians(lon2-lon1);
			double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
			double c = 2 * Math.asin(Math.sqrt(a));
			return 6366000 * c;
		}
	}
}
