package com.bmarohnic.lib;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// This is used as the interface between the activity, the database, and the data structure.
public class RecallsDataSource {
	
	private static String LOGTAG = "RecallsDataSource";
	SQLiteOpenHelper dbHelper;
	SQLiteDatabase database;
	
	// Define method used to return a string array of all the columns in the database.
	private static final String[] retrieveAllColumns = {
		RecallsDBHelper.COLUMN_ID,
		RecallsDBHelper.RECALLNO,
		RecallsDBHelper.RECDATE,
		RecallsDBHelper.TYPE,
		RecallsDBHelper.MANUFACTURER,
		RecallsDBHelper.HAZARD,
		RecallsDBHelper.RECALLURL,
		RecallsDBHelper.PRNAME,
		RecallsDBHelper.COUNTRY_MFG
		
	};
	
	
	public RecallsDataSource(Context context) {
		dbHelper = new RecallsDBHelper(context);
	}
	
	// Define public method to allow other classes to open the database connection.
	public void openDB() {
		database = dbHelper.getWritableDatabase();
		Log.i(LOGTAG, "Database opened");
	}
	
	// Define public method to allow other classes to close the database connection.
	public void closeDB() {
		dbHelper.close();
		Log.i(LOGTAG, "Database closed");
	}
	
	// Take the Recall object and generate a new database entry.
	public Recall create(Recall recall) {
		
		ContentValues contentValues = new ContentValues();
		contentValues.put(RecallsDBHelper.RECALLNO, recall.getRecallNo());
		contentValues.put(RecallsDBHelper.RECALLURL, recall.getRecallURL());
		contentValues.put(RecallsDBHelper.RECDATE, recall.getRecDate());
		contentValues.put(RecallsDBHelper.PRNAME, recall.getPrname());
		contentValues.put(RecallsDBHelper.HAZARD, recall.getHazard());
		contentValues.put(RecallsDBHelper.COUNTRY_MFG, recall.getCountry_mfg());
		contentValues.put(RecallsDBHelper.MANUFACTURER, recall.getManufacturer());
		contentValues.put(RecallsDBHelper.TYPE, recall.getType());
		
		long insertId = database.insert(RecallsDBHelper.RECALLS, null, contentValues);
		
		recall.setRecallId(insertId);
		
		return recall;
		
	}
	
	// Method is used to pull back all of the information in the database.
	// The individual recalls are stored in a List in order to prepare them
	// to be passed into the ListView adapter.
	public List<Recall> retrieveAllData() {
		List<Recall> recalls = new ArrayList<Recall>();
		
		Cursor cursor = database.query(RecallsDBHelper.RECALLS, retrieveAllColumns, null, null, null, null, null);
		
		if(cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Recall recall = new Recall();
				recall.setRecallId(cursor.getLong(cursor.getColumnIndex(RecallsDBHelper.COLUMN_ID)));
				recall.setCountry_mfg(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.COUNTRY_MFG)));
				recall.setHazard(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.HAZARD)));
				recall.setManufacturer(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.MANUFACTURER)));
				recall.setPrname(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.PRNAME)));
				recall.setRecallNo(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.RECALLNO)));
				recall.setRecallURL(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.RECALLURL)));
				recall.setRecDate(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.RECDATE)));
				recall.setType(cursor.getString(cursor.getColumnIndex(RecallsDBHelper.TYPE)));
				
				recalls.add(recall);
			}
		}
		return recalls;
	}
}
