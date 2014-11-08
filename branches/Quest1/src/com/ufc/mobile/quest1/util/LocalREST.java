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
	
	private static final String URL = "http://192.168.0.105:8080/";
	
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
				local = jsonArray.getJSONObject(i);
				Local localaux = new Local();
				localaux.setId(Long.parseLong(local.getString("id")));
				JSONObject contactJson = local.getJSONObject("contact");
				Contact contact = new Contact();
				if(contactJson.has("facebookName"))
					contact.setFacebookName(contactJson.getString("facebookName"));
				if(contactJson.has("twitter"))
					contact.setTwitter(contactJson.getString("twitter"));
				if(contactJson.has("phone"))
					contact.setPhone(contactJson.getString("phone"));
				if(contactJson.has("facebook"))
					contact.setFacebook(contactJson.getString("facebook"));
				if(contactJson.has("formattedPhone"))
					contact.setFormattedPhone(contactJson.getString("formattedPhone"));
				if(contactJson.has("facebookUsername"))
					contact.setFacebookUsername(contactJson.getString("facebookUsername"));
				localaux.setContact(contact);
				Location location = new Location();
				JSONObject locationJSON = local.getJSONObject("location");
				if(contactJson.has("address"))
					location.setAddress(locationJSON.getString("address"));
				if(contactJson.has("cc"))
					location.setCc(locationJSON.getString("cc"));
				if(contactJson.has("city"))
					location.setCity(locationJSON.getString("city"));
				if(contactJson.has("crossStreet"))
					location.setCrossStreet(locationJSON.getString("crossStreet"));
				if(contactJson.has("country"))
					location.setCountry(locationJSON.getString("country"));
				if(contactJson.has("lat"))
					location.setLat(Double.parseDouble(locationJSON.getString("lat")));
				if(contactJson.has("lng"))
					location.setLng(Double.parseDouble(locationJSON.getString("lng")));
				if(contactJson.has("postalCode"))
					location.setPostalCode(locationJSON.getString("postalCode"));
				if(contactJson.has("state"))
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
