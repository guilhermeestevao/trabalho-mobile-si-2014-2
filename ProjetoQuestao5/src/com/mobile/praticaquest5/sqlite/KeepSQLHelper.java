package com.mobile.praticaquest5.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class KeepSQLHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "avaliacao.db";
	private static KeepSQLHelper instance;

	private KeepSQLHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static KeepSQLHelper getInstance(Context context) {
		if(instance == null){
			instance = new KeepSQLHelper(context);
		}
		return instance;
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		AvaliacaoTable.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		AvaliacaoTable.onUpgrade(db, oldVersion, newVersion);
		
	}


}
