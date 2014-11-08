package com.ufc.mobile.quest1;

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
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GoogleMap googleMap;
	private List<Local> locais;
	
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
			// check if map is created successfully or not
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true); 
			googleMap.setMyLocationEnabled(true);

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
			Intent it = new Intent(MainActivity.this, ListaTodosLocaisActivity.class);
			
			Local[] locaisaux = new Local[locais.size()];
			for(int i=0;i<locais.size();i++){
				locaisaux[i] = locais.get(i);
			}
			
			it.putExtra("locais", locaisaux);
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
						.setTitle("Atenção")
						.setMessage(
								"Não foi possivel acessar essas informações...")
						.setPositiveButton("OK", null);
				builder.create().show();
			}else{
				addPontos(locais);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this)
						.setTitle("Atenção")
						.setMessage(
								"Foram carregados "+result.size()+" pontos")
						.setPositiveButton("OK", null);
				builder.create().show();
			}
		}		

		private void addPontos(List<Local> locais){
			for (Local local : locais) {
				MarkerOptions marker = new MarkerOptions().position(new LatLng(local.getLocation().getLat(), local.getLocation().getLng())).title(local.getName());			 
				googleMap.addMarker(marker);
			}
		}
	}
}
