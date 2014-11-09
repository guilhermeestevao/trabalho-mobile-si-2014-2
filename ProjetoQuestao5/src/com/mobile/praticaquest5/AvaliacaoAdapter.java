package com.mobile.praticaquest5;

import java.util.List;

import com.mobile.praticaquest5.model.Avaliacao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class AvaliacaoAdapter extends BaseAdapter{

	private Context context;
	private List<Avaliacao> avaliacoes;

	public AvaliacaoAdapter(Context context, List<Avaliacao> avaliacoes) {
		this.context = context;
		this.avaliacoes = avaliacoes;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return avaliacoes.size();
	}

	@Override
	public Object getItem(int id) {
		// TODO Auto-generated method stub
		return avaliacoes.get(id);
	}

	@Override
	public long getItemId(int itemId) {
		// TODO Auto-generated method stub
		return itemId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Avaliacao avaliacao = avaliacoes.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.avaliacao_layout, null);
		TextView text = (TextView) view.findViewById(R.id.local);
		RatingBar rating = (RatingBar) view.findViewById(R.id.avaliacao);
		text.setText(avaliacao.getNomeLocal());
		rating.setRating(avaliacao.getAvaliacaoCustoLocal());
		return view;
	}

}
