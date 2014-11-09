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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private TextView TVnomeLocal;
	private EditText ETtituloAvaliacao;
	private Spinner SfazerNoLocal;
	private RatingBar RBatendimento;
	private RatingBar RBcomida;
	private RatingBar RBcustoBeneficio;
	private RatingBar RBambiente;
	private RatingBar RBcustoPorPessoa;
	private CheckBox CBservicoReserva;
	private CheckBox CBmesasExternas;
	private CheckBox CBmusicaAoVivo;
	private CheckBox CBservicoEntrega;
	private RadioGroup RGvoltarAoLocal;
	private Button btnEnviar;
	private String local;
	private long id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(getIntent().hasExtra("local") && getIntent().hasExtra("id")){
			 local = getIntent().getExtras().getString("local");
			 id = getIntent().getExtras().getLong("id");
			 Toast.makeText(this, id+" "+local, Toast.LENGTH_LONG).show();
		}
		
	
	
		TVnomeLocal = (TextView) findViewById(R.id.nomeLocal);
		ETtituloAvaliacao = (EditText) findViewById(R.id.tituloAvaliacao);
		SfazerNoLocal = (Spinner) findViewById(R.id.fazerNoLocal);
		RBatendimento = (RatingBar) findViewById(R.id.rating_atendimento);
		RBcomida = (RatingBar) findViewById(R.id.rating_comida);
		RBcustoBeneficio = (RatingBar) findViewById(R.id.rating_custo_beneficio);
		RBambiente = (RatingBar) findViewById(R.id.rating_ambiente);
		RBcustoPorPessoa = (RatingBar) findViewById(R.id.rating_custo_por_pessoa);
		CBservicoReserva = (CheckBox) findViewById(R.id.servico_reserva);
		CBmesasExternas = (CheckBox) findViewById(R.id.mesas_externas);
		CBmusicaAoVivo = (CheckBox) findViewById(R.id.musica_ao_vivo);
		CBservicoEntrega = (CheckBox) findViewById(R.id.servico_entrega);
		RGvoltarAoLocal = (RadioGroup) findViewById(R.id.radio_group_voltaria);

		btnEnviar = (Button) findViewById(R.id.btn_enviar_dados);

		btnEnviar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String nomeLocal = TVnomeLocal.getText().toString();
				String tituloLocal = ETtituloAvaliacao.getText().toString();
				float atendimento = RBatendimento.getRating();
				float comida = RBcomida.getRating();
				float custoBeneficio = RBcustoBeneficio.getRating();
				float ambiente = RBambiente.getRating();
				float custoPorPessoa = RBcustoPorPessoa.getRating();
				float mediaAvaliacao = (atendimento + custoBeneficio + ambiente + comida) / 4;

				if (tituloLocal.equals("") || tituloLocal == null) {
					Toast.makeText(MainActivity.this, "Campo Titulo Local n�o preenchido", Toast.LENGTH_SHORT).show();
				} else if (atendimento == 0) {
					Toast.makeText(MainActivity.this, "Campo Atendimento n�o preenchido", Toast.LENGTH_SHORT).show();
				} else if (comida == 0) {
					Toast.makeText(MainActivity.this, "Campo Comida n�o preenchido", Toast.LENGTH_SHORT).show();
				} else if (custoBeneficio == 0) {
					Toast.makeText(MainActivity.this, "Campo Custo Benef�cio n�o preenchido", Toast.LENGTH_SHORT).show();
				} else if (ambiente == 0) {
					Toast.makeText(MainActivity.this, "Campo Ambiente n�o preenchido", Toast.LENGTH_SHORT).show();
				} else if (custoPorPessoa == 0) {
					Toast.makeText(MainActivity.this, "Campo Custo por Pessoa n�o preenchido", Toast.LENGTH_SHORT).show();
				} else if (RGvoltarAoLocal.getCheckedRadioButtonId() == -1) {
					Toast.makeText(MainActivity.this, "Campo Voltar ao local n�o preenchido", Toast.LENGTH_SHORT).show();
				} else {

					Intent it = new Intent(MainActivity.this,
							ActivityResposta.class);
					it.putExtra("nomeLocal", nomeLocal);
					it.putExtra("tituloLocal", tituloLocal);
					it.putExtra("mediaAvaliacao", mediaAvaliacao);
					it.putExtra("custoPorPessoa", custoPorPessoa);

					startActivity(it);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
