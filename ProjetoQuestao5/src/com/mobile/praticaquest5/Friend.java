package com.mobile.praticaquest5;

public class Friend {

	private String nome;
	private int drawableId;
	
	public Friend() {
	}
	
	public Friend(String nome, int drawableId) {
		super();
		this.nome = nome;
		this.drawableId = drawableId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDrawableId() {
		return drawableId;
	}

	public void setDrawableId(int drawableId) {
		this.drawableId = drawableId;
	}

	@Override
	public String toString() {
		return "Friend [nome=" + nome + "]";
	}	
	
}
