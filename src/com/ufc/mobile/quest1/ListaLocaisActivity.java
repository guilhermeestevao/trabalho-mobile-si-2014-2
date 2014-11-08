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

	public static List<Local> locais;
	private ExpandableListAdapter adapter;
	private ExpandableListView expandableListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_locais);

		List<Local> locais = ListaLocaisActivity.locais;
		
		adapter = new ExpandableListAdapter(ListaLocaisActivity.this, locais);
		expandableListView = (ExpandableListView) findViewById(R.id.listView);
		expandableListView.setAdapter(adapter);
	}
}
