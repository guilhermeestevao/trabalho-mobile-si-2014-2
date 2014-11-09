package com.mobile.praticaquest5;

import java.util.ArrayList;
import java.util.List;

import com.mobile.praticaquest5.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<Friend> friends;
	private ArrayList<FriendChecked> friendChecked;
	
	public FriendAdapter(Context context, ArrayList<Friend> friends, ArrayList<FriendChecked> friendChecked) {
		this.context = context;
		this.friends = friends;
		this.friendChecked = friendChecked;
	}

	@Override
	public int getCount() {
		return friendChecked.size();
	}

	@Override
	public Object getItem(int position) {
		return friendChecked.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		FriendChecked fc = friendChecked.get(position);
		Friend f = friends.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.layout_list, null);
		
		ImageView imagem = (ImageView) view.findViewById(R.id.imagem);
		TextView texto = (TextView) view.findViewById(R.id.texto);
		CheckBox check = (CheckBox) view.findViewById(R.id.checkBoxFriend);
		
		imagem.setImageResource(f.getDrawableId());
		texto.setText(f.getNome());
		check.setChecked(fc.isChecked());
		
		final int _position = position;
		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(!friendChecked.get(_position).isChecked())
					friendChecked.get(_position).setChecked(true);
				else
					friendChecked.get(_position).setChecked(false);
			}
		});
		
		return view;
		
	}
	
	public ArrayList<FriendChecked> listChecked(){
		return friendChecked;
	}
	
	
	
}
