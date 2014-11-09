package com.mobile.praticaquest5;

import com.mobile.praticaquest5.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ActivityResposta extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resposta);
		
		Bundle b = getIntent().getExtras();
		String nomeLocal = b.getString("nomeLocal");
		String tituloLocal = b.getString("tituloLocal");
		float media = b.getFloat("mediaAvaliacao");
		float custoPorPessoa = b.getFloat("custoPorPessoa");
		
		TextView TVnomeLocal = (TextView) findViewById(R.id.nome_local_resposta);
		TVnomeLocal.setText(nomeLocal);
		TextView TVtituloLocal = (TextView) findViewById(R.id.titulo_local_resposta);
		TVtituloLocal.setText(tituloLocal);
		RatingBar ratingMedia = (RatingBar) findViewById(R.id.rating_media_resposta);
		ratingMedia.setRating(media);
		RatingBar ratingCustoPorPessoa = (RatingBar) findViewById(R.id.rating_custo_por_pessoa_reposta);
		ratingCustoPorPessoa.setRating(custoPorPessoa);
		
		Button compartilhar = (Button) findViewById(R.id.compartilar_resposta);
		compartilhar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent it = new Intent(ActivityResposta.this, ActivityListFriends.class);
				startActivity(it);
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_resposta, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
