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
				JSONObject contactJson = local.getJSONObject("contact");
				Contact contact = new Contact();
				contact.setFacebookName(contactJson.getString("facebookName"));
				contact.setTwitter(contactJson.getString("twitter"));
				contact.setPhone(contactJson.getString("phone"));
				contact.setFacebook(contactJson.getString("facebook"));
				contact.setFormattedPhone(contactJson.getString("formattedPhone"));
				contact.setFacebookUsername(contactJson.getString("facebookUsername"));
				localaux.setContact(contact);
				Location location = new Location();
				JSONObject locationJSON = local.getJSONObject("location");
				location.setAddress(locationJSON.getString("address"));
				location.setCc(locationJSON.getString("cc"));
				location.setCity(locationJSON.getString("city"));
				location.setCrossStreet(locationJSON.getString("crossStreet"));
				location.setCountry(locationJSON.getString("country"));
				location.setLat(Double.parseDouble(locationJSON.getString("lat")));
				location.setLng(Double.parseDouble(locationJSON.getString("lng")));
				location.setPostalCode(locationJSON.getString("postalCode"));
				location.setState(locationJSON.getString("state"));
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
