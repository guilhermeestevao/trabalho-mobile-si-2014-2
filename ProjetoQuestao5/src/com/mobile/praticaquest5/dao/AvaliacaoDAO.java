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

	public AvaliacaoDAO(Context context) {
		helper = KeepSQLHelper.getInstance(context);
	}

	public void open() {
		db = helper.getWritableDatabase();
	}

	public boolean isOpen() {
		return db.isOpen();
	}

	private Cursor query(String[] columns, String selection,
			String selectionArgs[], String groupBy, String having,
			String orderBy) {
		return db.query(AvaliacaoTable.TABLE_NAME, columns, selection,
				selectionArgs, groupBy, having, orderBy);
	}

	public List<Avaliacao> getAll() {
		List<Avaliacao> allAvaliacao = new ArrayList<Avaliacao>();
		Cursor c = query(null, null, null, null, null, null);
		while (c.moveToNext()) {

			Avaliacao newAvaliacao = new Avaliacao();
			newAvaliacao.setId(c.getLong(AvaliacaoTable.ID_COLUMN_INDEX));
			newAvaliacao.setNomeLocal(c
					.getString(AvaliacaoTable.NOME_LOCAL_COLUMN_INDEX));
			newAvaliacao.setIdLocal(c
					.getLong(AvaliacaoTable.ID_LOCAL_COLUMN_INDEX));
			newAvaliacao.setMediaGeralLocal(c
					.getFloat(AvaliacaoTable.MEDIA_GERAL_LOCAL_COLUMN_INDEX));
			newAvaliacao
					.setAvaliacaoCustoLocal(c
							.getFloat(AvaliacaoTable.AVALIACAO_CUSTO_LOCAL_COLUMN_INDEX));
			allAvaliacao.add(newAvaliacao);

		}
		c.close();
		return allAvaliacao;

	}

	private Cursor queryByIdLocal(long id) {
		String columns[] = { String.valueOf(id) };
		return query(null, "id_local = ?", columns, null, null, null);
	}

	public List<Avaliacao> getAvaliacaoByIdLocal(long id) {
		List<Avaliacao> allAvaliacao = new ArrayList<Avaliacao>();
		Cursor c = queryByIdLocal(id);
		while (c.moveToNext()) {

			Avaliacao newAvaliacao = new Avaliacao();
			newAvaliacao.setId(c.getLong(AvaliacaoTable.ID_COLUMN_INDEX));
			newAvaliacao.setNomeLocal(c
					.getString(AvaliacaoTable.NOME_LOCAL_COLUMN_INDEX));
			newAvaliacao.setIdLocal(c
					.getLong(AvaliacaoTable.ID_LOCAL_COLUMN_INDEX));
			newAvaliacao.setMediaGeralLocal(c
					.getFloat(AvaliacaoTable.MEDIA_GERAL_LOCAL_COLUMN_INDEX));
			newAvaliacao
					.setAvaliacaoCustoLocal(c
							.getFloat(AvaliacaoTable.AVALIACAO_CUSTO_LOCAL_COLUMN_INDEX));
			allAvaliacao.add(newAvaliacao);

		}
		c.close();
		return allAvaliacao;
	}

	public int delete(String where, String values[]) {
		return db.delete(AvaliacaoTable.TABLE_NAME, where, values);
	}

	public int delete(long id) {
		String values[] = { id + "" };
		return delete("_id = ?", values);
	}

	public Avaliacao insert(Avaliacao avaliacao) {
		ContentValues value = new ContentValues();
		value.put(AvaliacaoTable.ID_LOCAL, avaliacao.getIdLocal());
		value.put(AvaliacaoTable.NOME_LOCAL, avaliacao.getNomeLocal());
		value.put(AvaliacaoTable.MEDIA_GERAL_LOCAL,
				avaliacao.getMediaGeralLocal());
		value.put(AvaliacaoTable.AVALIACAO_CUSTO_LOCAL,
				avaliacao.getAvaliacaoCustoLocal());

		long id = db.insert(AvaliacaoTable.TABLE_NAME, null, value);

		Cursor c = queryByIdLocal(id);
		Avaliacao newAvaliacao = Avaliacao.cursorToAvaliacao(c);
		c.close();

		return newAvaliacao;
	}

	public void close() {
		db.close();
	}

	public int update(Avaliacao avaliacao) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(AvaliacaoTable.ID, avaliacao.getId());
		contentValues.put(AvaliacaoTable.ID_LOCAL, avaliacao.getIdLocal());
		contentValues.put(AvaliacaoTable.NOME_LOCAL, avaliacao.getNomeLocal());
		contentValues.put(AvaliacaoTable.AVALIACAO_CUSTO_LOCAL,
				avaliacao.getAvaliacaoCustoLocal());
		contentValues.put(AvaliacaoTable.MEDIA_GERAL_LOCAL,
				avaliacao.getMediaGeralLocal());
		return db.update(AvaliacaoTable.TABLE_NAME, contentValues, "_id = ?",
				new String[] { Long.toString(avaliacao.getId()) });
	}

}
