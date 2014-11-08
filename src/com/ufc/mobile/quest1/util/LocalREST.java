package com.ufc.mobile.quest1.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ufc.mobile.quest1.model.Contact;
import com.ufc.mobile.quest1.model.Local;
import com.ufc.mobile.quest1.model.Location;

public class LocalREST {
	
	private static final String URL = "http://192.168.0.118:8080/";
	
	public List<Local> getLocais(){
		String[] resposta = new WebServiceCliente().get(URL);
		if(resposta[0].equals("200")){
			List<Local> usuarios = getLocais(resposta[1]);
			return usuarios;
		}else{
			return null;
		}
	}
	
	private List<Local> getLocais(String json){
		List<Local> venues = new ArrayList<Local>();
		
		try{
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = jsonObject.getJSONArray("venues");
			JSONObject local;
			
			for(int i = 0; i < jsonArray.length(); i++){
				local = new JSONObject(jsonArray.getString(i));
				Local localaux = new Local();
				localaux.setId(Long.parseLong(local.getString("id")));
				JSONObject contactJson = jsonObject.getJSONObject(local.getString("contact"));
				Log.i("JSON CHAMADO","passou");
				Contact contact = new Contact();
				//contact.setFormattedPhone(contactJson.getString("formattedPhone"));
				contact.setPhone(local.getString("phone"));
				localaux.setContact(contact);
				Location location = new Location();
				location.setAddress(local.getString("address"));
				location.setCc(local.getString("cc"));
				location.setCity(local.getString("city"));
				location.setCountry(local.getString("country"));
				location.setLat(Double.parseDouble(local.getString("lat")));
				location.setLng(Double.parseDouble(local.getString("lng")));
				location.setPostalCode(local.getString("postalCode"));
				location.setState(local.getString("state"));
				localaux.setLocation(location);
				localaux.setName(local.getString("name"));
				venues.add(localaux);
			}
			return venues;
		}catch(JSONException e){
			Log.i("JSON CHAMADO", e.toString());
		}
		
		return venues;
	}
}
