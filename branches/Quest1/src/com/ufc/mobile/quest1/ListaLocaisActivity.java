package com.ufc.mobile.quest1;

import java.util.ArrayList;
import java.util.List;

import com.ufc.mobile.quest1.adapter.ExpandableListAdapter;
import com.ufc.mobile.quest1.model.Local;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

public class ListaLocaisActivity extends Activity {

	private List<Local> locais;
	private ExpandableListAdapter adapter;
	private ExpandableListView expandableListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_locais);

		Log.i("JSON CHAMADO", String.valueOf(locais.size()));
		/*Intent it = getIntent();
		Local[] locaisaux = (Local[]) it.getExtras().get("locais");
		locais = new ArrayList<Local>();
		
		for(int i=0;i<locaisaux.length;i++){
			locais.add(locaisaux[i]);
		}
		
		adapter = new ExpandableListAdapter(ListaLocaisActivity.this, locais);
		expandableListView = (ExpandableListView) findViewById(R.id.listView);
		expandableListView.setAdapter(adapter);*/
	}
}
