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

	private static final String URL = "http://192.168.0.107:8080/";

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
			JSONArray jarray = jsonObject.getJSONArray("venues");
			JSONObject jsonObjLocal;

			for (int i = 0; i < jarray.length(); i++) {
				jsonObjLocal = new JSONObject(jarray.getString(i));
				Local local = new Local();

				//Obtendo informações simples como id e nome
				long id = jsonObjLocal.getLong("id");
				local.setId(id);
				String name = jsonObjLocal.getString("name");
				local.setName(name);

				//Obtendo informações de contatos
				JSONObject jsonObjConntact = jsonObjLocal.getJSONObject("contact");
				Contact contact = new Contact();
				String phone;
				String formattedPhone;
				String twitter;
				String facebookName;
				String facebook;
				String facebookUsername;
				if(jsonObjConntact.has("phone")){
					phone = jsonObjConntact.getString("phone");
					contact.setPhone(phone);
				}
				if(jsonObjConntact.has("formattedPhone")){
					formattedPhone = jsonObjConntact.getString("formattedPhone");
					contact.setFormattedPhone(formattedPhone);
				}
				if(jsonObjConntact.has("twitter")){
					twitter = jsonObjConntact.getString("twitter");
					contact.setTwitter(twitter);
				}
				if(jsonObjConntact.has("facebookName")){
					facebookName = jsonObjConntact.getString("facebookName");
					contact.setFacebookName(facebookName);
				}
				if(jsonObjConntact.has("facebook")){
					facebook = jsonObjConntact.getString("facebook");
					contact.setFacebook(facebook);
				}
				if(jsonObjConntact.has("facebookUsername")){
					facebookUsername = jsonObjConntact.getString("facebookUsername");
					contact.setFacebookUsername(facebookUsername);
				}
				local.setContact(contact);

				//Obtendo informações de localização
				JSONObject jsonObjLocation = jsonObjLocal.getJSONObject("location");
				Location location = new Location();
				String address;
				String cc;
				String city;
				String country;
				String[] formattedAddress;
				Double lat;
				Double lng;
				String crossStreet;
				String postalCode;
				String state;

				if(jsonObjLocation.has("address")){
					address = jsonObjLocation.getString("address");
					location.setAddress(address);
				}
				if(jsonObjLocation.has("cc")){
					cc = jsonObjLocation.getString("cc");
					location.setCc(cc);
				}
				if(jsonObjLocation.has("city")){
					city = jsonObjLocation.getString("city");
					location.setCity(city);
				}
				if(jsonObjLocation.has("country")){
					country = jsonObjLocation.getString("country");
					location.setCountry(country);
				}
				if(jsonObjLocation.has("lat")){
					lat = jsonObjLocation.getDouble("lat");
					location.setLat(lat);
				}
				if(jsonObjLocation.has("lng")){
					lng = jsonObjLocation.getDouble("lng");
					location.setLng(lng);
				}
				if(jsonObjLocation.has("crossStreet")){
					crossStreet = jsonObjLocation.getString("crossStreet");
					location.setCrossStreet(crossStreet);
				}
				if(jsonObjLocation.has("postalCode")){
					postalCode = jsonObjLocation.getString("postalCode");
					location.setPostalCode(postalCode);
				}
				if(jsonObjLocation.has("state")){
					state = jsonObjLocation.getString("state");
					location.setState(state);
				}
				local.setLocation(location);
				venues.add(local);
			}
			return venues;
		}catch(JSONException e){
			Log.i("JSON CHAMADO", e.toString());
		}

		return venues;
	}
}
