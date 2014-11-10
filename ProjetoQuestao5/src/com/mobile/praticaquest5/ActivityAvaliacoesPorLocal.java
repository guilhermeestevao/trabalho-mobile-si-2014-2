package com.mobile.praticaquest5;

import java.util.List;

import com.mobile.praticaquest5.dao.AvaliacaoDAO;
import com.mobile.praticaquest5.model.Avaliacao;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityAvaliacoesPorLocal extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_lista_avaliacao);
		
		Intent it = getIntent();
		long id = it.getExtras().getLong("idLocal");
		
		AvaliacaoDAO dao = new AvaliacaoDAO(this);
		dao.open();
		List<Avaliacao> avaliacoes = dao.getAvaliacaoByIdLocal(id);
		dao.close();
		AvaliacaoAdapter adapter = new AvaliacaoAdapter(this, avaliacoes);
		setListAdapter(adapter);
		
	}
	
}
