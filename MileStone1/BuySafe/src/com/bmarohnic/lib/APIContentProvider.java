package com.bmarohnic.lib;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class APIContentProvider extends ContentProvider{

	public static final String AUTHORITY = "com.bmarohnic.lib.apicontentprovider";
	
	public static class RecallData implements BaseColumns{
		
		public static final Uri RECALL_URI = Uri.parse("content://" + AUTHORITY + "/recalls");
		
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.bmarohnic.lib.recall";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.bmarohnic.lib.recall";
		
		// Define the columns to be created
		public static final String RECALLNO_COLUMN = "recallNo";
		public static final String RECALLURL_COLUMN = "recallURL";
		public static final String RECDATE_COLUMN = "recDate";
		public static final String MANUFACTURER_COLUMN = "manufacturer";
		public static final String TYPE_COLUMN = "type";
		public static final String PRNAME_COLUMN = "prname";
		public static final String HAZARD_COLUMN = "hazard";
		public static final String COUNTRY_MFG_COLUMN = "country_mfg";
		
		// Define the columns that will be returned.
		public static final String[] PROJECTION = {"_Id", RECALLNO_COLUMN, RECALLURL_COLUMN, RECDATE_COLUMN, MANUFACTURER_COLUMN,
			TYPE_COLUMN, PRNAME_COLUMN, HAZARD_COLUMN, COUNTRY_MFG_COLUMN};
		
		// Set the constructor to private since there is no reason to call the constructor from outside this class.
		private RecallData(){};
		
		}
	
	public static final int RECALLS = 1;
	public static final int RECALLS_ID = 2;
	
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static{
		uriMatcher.addURI(AUTHORITY, "recalls/", RECALLS);
		uriMatcher.addURI(AUTHORITY, "recalls/#", RECALLS_ID);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		
		switch(uriMatcher.match(uri)){
		case RECALLS:
			return RecallData.CONTENT_TYPE;
		
		case RECALLS_ID:
			return RecallData.CONTENT_TYPE;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		MatrixCursor result = new MatrixCursor(RecallData.PROJECTION);
		
//		switch(uriMatcher.match(uri)){
//		case RECALLS:
//			return RecallData.CONTENT_TYPE;
//		
//		case RECALLS_ID:
//			return RecallData.CONTENT_TYPE;
//		}
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
