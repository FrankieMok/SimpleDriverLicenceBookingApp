package com.example.isc7424s12020_a1_1518687;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "DatabaseHelper";

	private static final String DATABASE_NAME = "Licencetable.db";
	private static final String TABLE_NAME = "driving_licence";
	private static final String COL1 = "ID";
	private static final String COL2 = "DRIVE_LIC";
	private static final String COL3 = "DATE";
	private static final String COL4 = "TIME_SLOT";

	public DatabaseHelper(@Nullable Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME
				+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,DRIVE_LIC TEXT, DATE TEXT,TIME_SLOT TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		onCreate(db);
	}

	public boolean insertData(String lic, String date, String time) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COL2,lic);
		contentValues.put(COL3,date);
		contentValues.put(COL4,time);
		long result = db.insert(TABLE_NAME, null, contentValues); // result to check if value correctly inserted
		if (result == -1)
			return false;
		else
			return true;
	}

	public Cursor getAllData() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor result = db.rawQuery("select * from "+TABLE_NAME, null);
		return result;
	}

	public Cursor getDateOfLic(String LicOf, String DateOf) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery =
				("SELECT * FROM " + TABLE_NAME + " WHERE DATE='"+ DateOf +"' AND DRIVE_LIC='" + LicOf + "'");
		Cursor result = db.rawQuery(selectQuery, null);
		return result;
	}


	public Cursor getTimeOfDate(String dateOf, String timeOf) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery =
				("SELECT * FROM " + TABLE_NAME + " WHERE TIME_SLOT='"+ timeOf +"' AND DATE='" + dateOf + "'");
		Cursor result = db.rawQuery(selectQuery, null);
		return result;
	}

	public Cursor getLic(String LicOf) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery =
				("SELECT * FROM " + TABLE_NAME + " WHERE DRIVE_LIC='"+ LicOf + "'");
		Cursor result = db.rawQuery(selectQuery, null);
		return result;
	}

}
