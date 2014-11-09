package com.mobile.praticaquest5;

import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.mobile.praticaquest5.dao.AvaliacaoDAO;
import com.mobile.praticaquest5.model.Avaliacao;

public class ActivityListaAvaliacoes extends ListActivity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_lista_avaliacao);
		AvaliacaoDAO dao = new AvaliacaoDAO(this);
		dao.open();
		List<Avaliacao> avaliacoes = dao.getAll();
		dao.close();
		AvaliacaoAdapter adapter = new AvaliacaoAdapter(this, avaliacoes);
		setListAdapter(adapter);
	}
	
	
}
