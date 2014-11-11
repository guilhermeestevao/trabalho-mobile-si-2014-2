package com.mobile.praticaquest5.contentprovider;

import com.mobile.praticaquest5.dao.AvaliacaoDAO;
import com.mobile.praticaquest5.model.Avaliacao;
import com.mobile.praticaquest5.model.AvaliacaoContract;
import com.mobile.praticaquest5.sqlite.AvaliacaoTable;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class AvaliacaoProvider extends ContentProvider {

	private AvaliacaoDAO dao;

	private static UriMatcher uriAvaliacao;

	static {
		uriAvaliacao = new UriMatcher(UriMatcher.NO_MATCH);
		
		// content://com.mobile.praticaquest5.contentprovider.aluno.provider/avaliacao/2 (exemplo de id=2)
		uriAvaliacao.addURI(AvaliacaoContract.AUTHORITY, "avaliacao/#", AvaliacaoContract.AVALIACAO_ID_LOCAL);
	}

	@Override
	public boolean onCreate() {
		dao = new AvaliacaoDAO(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		switch (uriAvaliacao.match(uri)) {
		case AvaliacaoContract.AVALIACAO_ID_LOCAL:
			String aux = uri.getLastPathSegment();
			return dao.queryByIdLocalProvider(Long.parseLong(aux));
		default:
			throw new IllegalArgumentException("Uri invalida: "+uri);
		}
		
	}

	@Override
	public String getType(Uri uri) {
		switch (uriAvaliacao.match(uri)) {
		case AvaliacaoContract.AVALIACAO_ID_LOCAL:
			return "vnd.android.cursor.dir/vnd.mobile.avaliacao";
		default:
			throw new IllegalArgumentException("Uri invalida: "+uri);
		}
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
