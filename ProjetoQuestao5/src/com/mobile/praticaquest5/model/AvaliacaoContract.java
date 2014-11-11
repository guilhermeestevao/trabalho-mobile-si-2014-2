package com.mobile.praticaquest5.model;

import android.net.Uri;

import com.mobile.praticaquest5.sqlite.AvaliacaoTable;

public class AvaliacaoContract {

	public static final String AUTHORITY = "com.mobile.praticaquest5.contentprovider.aluno.provider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + AvaliacaoTable.TABLE_NAME);
	public static final int AVALIACAO_ID_LOCAL = 1;
	
	public static final int ID_COLUMN_INDEX = AvaliacaoTable.ID_COLUMN_INDEX;
	public static final int ID_LOCAL_COLUMN_INDEX = AvaliacaoTable.ID_LOCAL_COLUMN_INDEX;
	public static final int NOME_LOCAL_COLUMN_INDEX = AvaliacaoTable.NOME_LOCAL_COLUMN_INDEX;
	public static final int AVALIACAO_CUSTO_LOCAL_COLUMN_INDEX = AvaliacaoTable.AVALIACAO_CUSTO_LOCAL_COLUMN_INDEX;
	public static final int MEDIA_GERAL_LOCAL_COLUMN_INDEX = AvaliacaoTable.MEDIA_GERAL_LOCAL_COLUMN_INDEX;
	public static String TABLE_NAME = AvaliacaoTable.TABLE_NAME;
	public static String ID = AvaliacaoTable.ID;
	public static String NOME_LOCAL = AvaliacaoTable.NOME_LOCAL;
	public static String MEDIA_GERAL_LOCAL = AvaliacaoTable.MEDIA_GERAL_LOCAL;
	public static String AVALIACAO_CUSTO_LOCAL = AvaliacaoTable.AVALIACAO_CUSTO_LOCAL;
	public static String ID_LOCAL = AvaliacaoTable.ID_LOCAL;
	
}
