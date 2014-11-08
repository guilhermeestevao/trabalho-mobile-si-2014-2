package com.ufc.mobile.quest1;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ufc.mobile.quest1.model.Local;
import com.ufc.mobile.quest1.util.LocalREST;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GoogleMap googleMap;
	private List<Local> locais;
	private Location myLocation;

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


			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.options, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
				.setTitle("Aten��o")
				.setMessage(
						"N�o foi possivel acessar essas informa��es...")
						.setPositiveButton("OK", null);
				builder.create().show();
			}else{
				addPontos(locais);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this)
				.setTitle("Aten��o")
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
				MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(local.getName());			 
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
