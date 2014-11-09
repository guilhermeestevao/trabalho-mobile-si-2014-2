package com.mobile.praticaquest5.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AvaliacaoTable {

	public static final int ID_COLUMN_INDEX = 0;
	public static final int ID_LOCAL_COLUMN_INDEX = 1;
	public static final int NOME_LOCAL_COLUMN_INDEX = 2;
	public static final int AVALIACAO_CUSTO_LOCAL_COLUMN_INDEX = 3;
	public static final int MEDIA_GERAL_LOCAL_COLUMN_INDEX = 4;
	public static String TABLE_NAME = "avaliacao";
	public static String ID = "_id";
	public static String NOME_LOCAL = "nome_local";
	public static String MEDIA_GERAL_LOCAL = "media_geral_local";
	public static String AVALIACAO_CUSTO_LOCAL = "avaliacao_custo_local";
	public static String ID_LOCAL = "id_local";
	
	private static String CREATE_TABLE = 
			"CREATE  TABLE "+ TABLE_NAME + 
			"(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
				+ ID_LOCAL + " INTEGER NOT NULL, "
				+ NOME_LOCAL + " TEXT NOT NULL, "
				+ AVALIACAO_CUSTO_LOCAL + " REAL NOT NULL, "
				+ MEDIA_GERAL_LOCAL + " REAL NOT NULL)";
	
	public static void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_TABLE);
	}
	
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		Log.w(AvaliacaoTable.class.getSimpleName(),
				"O banco de dados sera atualizado v"+oldVersion+"->v"+newVersion);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
}
