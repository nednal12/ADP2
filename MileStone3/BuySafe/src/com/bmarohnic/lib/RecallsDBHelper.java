package com.bmarohnic.lib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// RecallsDBHelper is used to generate the SQLite database that will house the 
// recall information within the watchlist.

public class RecallsDBHelper extends SQLiteOpenHelper {

	// Define the database name and version number.
	private static final String LOGTAG = "RecallsDBHelper";
	private static final String DB_NAME = "recalls.db";
	private static final int DB_VERSION = 1;
	// Define the table name and all of the columns.
	public static final String RECALLS = "recalls";
	public static final String COLUMN_ID = "key";
	public static final String RECALLNO = "recallNo";
	public static final String RECDATE = "recDate";
	public static final String TYPE = "type";
	public static final String MANUFACTURER = "manufacturer";
	public static final String HAZARD = "hazard";
	public static final String RECALLURL = "recallURL";
	public static final String PRNAME = "prname";
	public static final String COUNTRY_MFG = "country_mfg";
	
	
	// Define the text string that will be used within the onCreate() method.
	// This text defines the table name, key value, and other columns.
	private static final String CREATE_TABLE = 
			"CREATE TABLE " + RECALLS + " (" +
			COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			RECDATE + " TEXT, " +
			TYPE + " TEXT, " +
			RECALLNO + " TEXT, " +
			MANUFACTURER + " TEXT, " +
			HAZARD + " TEXT, " +
			RECALLURL + " TEXT, " +
			PRNAME + " TEXT, " +
			COUNTRY_MFG + " TEXT " +
			")";
	
	// The context of the calling activity must be captured.
	public RecallsDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	// Create the table. If the table already exists, do nothing.
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
		Log.i(LOGTAG, "Table has been created");

	}

	// This is never used, but it may need to be in the future if the CPSC.gov API is ever 
	// updated to either remove or add data elements.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldDBVer, int newDBVer) {
		db.execSQL("DROP TABLE IF EXISTS " + RECALLS);
		onCreate(db);
	}

}
