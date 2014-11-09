package com.mobile.praticaquest5.dao;

import java.util.ArrayList;
import java.util.List;

import com.mobile.praticaquest5.model.Avaliacao;
import com.mobile.praticaquest5.sqlite.AvaliacaoTable;
import com.mobile.praticaquest5.sqlite.KeepSQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AvaliacaoDAO {

	private SQLiteDatabase db;
	private KeepSQLHelper helper;
	
	public AvaliacaoDAO(Context context){
		helper = KeepSQLHelper.getInstance(context);
	}
	
	public void open(){
		db = helper.getWritableDatabase();
	}
	
	public boolean isOpen(){
		return db.isOpen();
	}
	
	private Cursor query(String[] columns,
			String selection, String selectionArgs[],
			String groupBy, String having, String orderBy){
		return db.query(AvaliacaoTable.TABLE_NAME,
				columns, selection, selectionArgs, groupBy, having, orderBy);
	}
	
	public List<Avaliacao> getAll(){
		List<Avaliacao> allAvaliacao = new ArrayList<Avaliacao>();
		Cursor c = query(null, null, null, null, null, null);
		while(c.moveToNext()){
			Avaliacao a = Avaliacao.cursorToAvaliacao(c);
			allAvaliacao.add(a);
		}
		c.close();
		return allAvaliacao;
		
	}
	
	private Cursor queryById(long id){
		String columns[] = {String.valueOf(id)};
		return query(null, "_id = ?", columns,
				null, null, null);
	}
	
	public Avaliacao getAvaliacaoById(long id){
		Cursor c = queryById(id);
		Avaliacao a = Avaliacao.cursorToAvaliacao(c);
		c.close();
		return a;
	}
	
	public int delete(String where, String values[]){
		return db.delete(AvaliacaoTable.TABLE_NAME, where, values);
	}
	
	public int delete(long id){
		String values[] = { id +""}; 
		return delete("_id = ?", values);
	}
	
	
	
	public Avaliacao insert(Avaliacao avaliacao){
		ContentValues value =  new ContentValues();
		value.put(AvaliacaoTable.ID_LOCAL, avaliacao.getIdLocal());
		value.put(AvaliacaoTable.NOME_LOCAL, avaliacao.getNomeLocal());
		value.put(AvaliacaoTable.MEDIA_GERAL_LOCAL, avaliacao.getMediaGeralLocal());
		value.put(AvaliacaoTable.AVALIACAO_CUSTO_LOCAL, avaliacao.getAvaliacaoCustoLocal());
		
		long id = db.insert(AvaliacaoTable.TABLE_NAME, null, value);
		
		Cursor c = queryById(id);
		Avaliacao newAvaliacao = Avaliacao.cursorToAvaliacao(c);
		c.close();
		
		return newAvaliacao;
	}
	
	
	public void close(){
		db.close();
	}
	
	public int update(Avaliacao avaliacao) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(AvaliacaoTable.ID, avaliacao.getId());
		contentValues.put(AvaliacaoTable.ID_LOCAL, avaliacao.getIdLocal());
		contentValues.put(AvaliacaoTable.NOME_LOCAL, avaliacao.getNomeLocal());
		contentValues.put(AvaliacaoTable.AVALIACAO_CUSTO_LOCAL, avaliacao.getAvaliacaoCustoLocal());
		contentValues.put(AvaliacaoTable.MEDIA_GERAL_LOCAL, avaliacao.getMediaGeralLocal());
		return db.update(AvaliacaoTable.TABLE_NAME, contentValues, "_id = ?", new String[]{Long.toString(avaliacao.getId())});
	}
	
}
