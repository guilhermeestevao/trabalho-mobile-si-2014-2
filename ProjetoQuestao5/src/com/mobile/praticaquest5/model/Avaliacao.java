package com.mobile.praticaquest5.model;

import java.io.Serializable;

import com.mobile.praticaquest5.sqlite.AvaliacaoTable;

import android.database.Cursor;

public class Avaliacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private long idLocal;
	private String nomeLocal;
	private float mediaGeralLocal;
	private float avaliacaoCustoLocal;
	
	public Avaliacao() {
	}

	public Avaliacao(long id, long idLocal, String nomeLocal,
			float mediaGeralLocal, float avaliacaoCustoLocal) {
		super();
		this.id = id;
		this.idLocal = idLocal;
		this.nomeLocal = nomeLocal;
		this.mediaGeralLocal = mediaGeralLocal;
		this.avaliacaoCustoLocal = avaliacaoCustoLocal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public float getMediaGeralLocal() {
		return mediaGeralLocal;
	}

	public void setMediaGeralLocal(float mediaGeralLocal) {
		this.mediaGeralLocal = mediaGeralLocal;
	}

	public float getAvaliacaoCustoLocal() {
		return avaliacaoCustoLocal;
	}

	public void setAvaliacaoCustoLocal(float avaliacaoCustoLocal) {
		this.avaliacaoCustoLocal = avaliacaoCustoLocal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(avaliacaoCustoLocal);
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (idLocal ^ (idLocal >>> 32));
		result = prime * result + Float.floatToIntBits(mediaGeralLocal);
		result = prime * result
				+ ((nomeLocal == null) ? 0 : nomeLocal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (Float.floatToIntBits(avaliacaoCustoLocal) != Float
				.floatToIntBits(other.avaliacaoCustoLocal))
			return false;
		if (id != other.id)
			return false;
		if (idLocal != other.idLocal)
			return false;
		if (Float.floatToIntBits(mediaGeralLocal) != Float
				.floatToIntBits(other.mediaGeralLocal))
			return false;
		if (nomeLocal == null) {
			if (other.nomeLocal != null)
				return false;
		} else if (!nomeLocal.equals(other.nomeLocal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Avaliacao [id=" + id + ", idLocal=" + idLocal + ", nomeLocal="
				+ nomeLocal + ", mediaGeralLocal=" + mediaGeralLocal
				+ ", avaliacaoCustoLocal=" + avaliacaoCustoLocal + "]";
	}
	
	public static Avaliacao cursorToAvaliacao(Cursor c){
		if(c == null || c.getCount() != 1){
			return null;
		}
		c.moveToFirst();
		
		Avaliacao newAvaliacao = new Avaliacao();
		newAvaliacao.setId(c.getLong(AvaliacaoTable.ID_COLUMN_INDEX));
		newAvaliacao.setNomeLocal(c.getString(AvaliacaoTable.NOME_LOCAL_COLUMN_INDEX));
		newAvaliacao.setIdLocal(c.getLong(AvaliacaoTable.ID_LOCAL_COLUMN_INDEX));
		newAvaliacao.setMediaGeralLocal(c.getFloat(AvaliacaoTable.MEDIA_GERAL_LOCAL_COLUMN_INDEX));
		newAvaliacao.setAvaliacaoCustoLocal(c.getFloat(AvaliacaoTable.AVALIACAO_CUSTO_LOCAL_COLUMN_INDEX));
		
		return newAvaliacao;
		
	}
	
}