package com.mobile.praticaquest5;

import java.util.ArrayList;
import java.util.List;

import com.mobile.praticaquest5.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityListFriends extends ListActivity{

	private FriendAdapter adapter;
	private ArrayList<FriendChecked> friendChecked;
	private ArrayList<FriendChecked> friendsCheckedAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_list_main);
		
		ArrayList<Friend> friends = inserirItens();
		
		friendChecked = new ArrayList<FriendChecked>();
		
		if(savedInstanceState != null){
			boolean check[] = savedInstanceState.getBooleanArray("checked");
			int i = 0;
			for(Friend f: friends){
				friendChecked.add(new FriendChecked(f, check[i]));
				i++;
			}
		}
			
		if(friendChecked == null || friendChecked.size() == 0){
			for(Friend f: friends){
				friendChecked.add(new FriendChecked(f, false));
			}
		}
		
		adapter = new FriendAdapter(this, friends, friendChecked);
		setListAdapter(adapter);

		friendsCheckedAdapter = adapter.listChecked();
		
	}
	
	public ArrayList<Friend> inserirItens(){
		
		ArrayList<Friend> friends = new ArrayList<Friend>();
		friends.add(new Friend("Administrador", R.drawable.administrator));
		friends.add(new Friend("Técnico 1",R.drawable.technicalsupportrepresentative_male_light));
		friends.add(new Friend("Técnico 2",R.drawable.technicalsupportrepresentative_female_light));
		friends.add(new Friend("Entregador Pizza", R.drawable.pizzadeliveryman_male_light));
		friends.add(new Friend("Pessoa 1",R.drawable.person_undefined_female_light));
		friends.add(new Friend("Pessoa 2",R.drawable.person_undefined_female_dark));
		friends.add(new Friend("Pessoa 3", R.drawable.person_coffeebreak_female_dark));
		friends.add(new Friend("Pessoa 4",R.drawable.person_coffeebreak_female_light));
		friends.add(new Friend("Pessoa 5",R.drawable.person_coffeebreak_male_dark));
		friends.add(new Friend("Pessoa 6", R.drawable.person_coffeebreak_male_light));
		friends.add(new Friend("Médico 1",R.drawable.nurse_female_dark));
		friends.add(new Friend("Médico 2",R.drawable.nurse_male_dark));
		friends.add(new Friend("Executivo", R.drawable.executive));
		friends.add(new Friend("Cliente 1",R.drawable.customer_female_dark));
		friends.add(new Friend("Cliente 2",R.drawable.customer_female_light));
		
		return friends;
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_list_friends, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_compartilhar) {
			
			Intent it = new Intent(ActivityListFriends.this, ActivityFriendsChecked.class);
			
			List<String> nomes = new ArrayList<String>();
			
			for(FriendChecked fc: friendsCheckedAdapter){
				if(fc.isChecked()){
					String nome = fc.getFriend().getNome();
					nomes.add(nome);
				}
			}
			
			String n[] = new String[nomes.size()];
			
			for(int i=0; i < n.length; i++){
				n[i] = nomes.get(i);
			}
			
			it.putExtra("friends", n);
			
			startActivity(it);
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	
		boolean check[] = new boolean[friendsCheckedAdapter.size()];
		
		for(int i=0; i < check.length; i++){
			check[i] = friendsCheckedAdapter.get(i).isChecked();
		}
		
		outState.putBooleanArray("checked", check);
		
	}
	
}
