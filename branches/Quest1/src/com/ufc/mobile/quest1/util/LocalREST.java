package com.ufc.mobile.quest1.util;

import java.util.List;

import com.ufc.mobile.quest1.model.Local;

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
		return null;
	}
}
