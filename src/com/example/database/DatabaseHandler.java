package com.example.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DrugManager";
	
	//constructor
	public DatabaseHandler(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//table name
	private static final String TABLE_NAME = "TABLE_DRUG";
	
	//colume names
	public static final String CODE = "CODE"; //resolved barcode
	public static final String NAME = "NAME";
	public static final String USAGE = "USAGE"; //text for usage
	public static final String TIME_PER_DAY = "TIME_PER_DAY"; //how many time a day
	public static final String AMOUNT = "AMOUNT"; // amount(number of pill) each time
	public static final String INDICATION = "INDICATION"; //for what symptoms
	public static final String SIDE_EFFECT = "SIDE_EFFECT";
	public static final String ACTIVE = "ACTION"; // in use by user?
	public static final String LONG_TERM = "LONG_TERM"; // long term use?
	
	
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_DRUG_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + CODE + " INTEGER PRIMARY KEY," + NAME + " TEXT,"
                + USAGE + " TEXT," + TIME_PER_DAY + " INTEGER," + AMOUNT 
                +" TEXT," + INDICATION + " TEXT," + SIDE_EFFECT +" TEXT,"
                + ACTIVE + " TEXT," + LONG_TERM + " TEXT)";
        db.execSQL(CREATE_DRUG_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    // Drop older table if existed
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    // Create tables again
	    onCreate(db);
	}
	
	// all CREATE, READ, UPDAE, DELETE action below
	public void addDrug(Drug drug){
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put(CODE, drug.getCode()); 
	    values.put(NAME, drug.getName()); // drug Phone Number
	    values.put(USAGE,drug.getUsage());
	    values.put(TIME_PER_DAY, drug.getTimePerDay());
	    values.put(INDICATION, drug.getIndication());
	    values.put(AMOUNT, drug.getAmount());
	    values.put(SIDE_EFFECT, drug.getSideEffect());
	    values.put(ACTIVE, drug.isActive());
	    values.put(LONG_TERM, drug.isLongTerm());
	    // Inserting Row
	    db.insert(TABLE_NAME, null, values);
	    db.close(); // Closing database connection
	}
	public Drug getDrug(int code){
		SQLiteDatabase db = this.getReadableDatabase();
		 
	    Cursor cursor = db.query(TABLE_NAME, new String[] { CODE,
	            NAME, USAGE }, CODE + "=?",
	            new String[] { String.valueOf(code) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    Drug drug = new Drug(Integer.parseInt(cursor.getString(0)),
	            cursor.getString(1), cursor.getString(2));
	    // return drug
	    return drug;
	}
	
	public List<Drug> getAllDrugs() {
	    List<Drug> drugList = new ArrayList<Drug>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_NAME;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Drug drug = new Drug();
	            drug.setCode(Integer.parseInt(cursor.getString(0)));
	            drug.setName(cursor.getString(1));
	            drug.setUsage(cursor.getString(2));
	            // Adding drug to list
	            drugList.add(drug);
	        } while (cursor.moveToNext());
	    }
	    // return drug list
	    return drugList;
	}
	
	public int updateDrug(Drug drug) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues values = new ContentValues();
	    values.put(NAME, drug.getName());
	    values.put(USAGE, drug.getUsage());
	    // updating row
	    return db.update(TABLE_NAME, values, CODE + " = ?",
	            new String[] { String.valueOf(drug.getCode()) });
	}
	
	public void deleteDrug(Drug drug) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_NAME, CODE + " = ?",
	            new String[] { String.valueOf(drug.getCode()) });
	    db.close();
	}
	
	public Cursor getCursor(){
		String[] columns = new String[]{CODE, NAME, USAGE};
		//Cursor cursor = this.getReadableDatabase().query(TABLE_NAME, columns,
		//  null, null, null, null, null);
		Cursor cursor = this.getReadableDatabase().rawQuery("SELECT CODE AS _id, NAME, USAGE FROM " 
				+ TABLE_NAME, null);
		return cursor;
	}
}

	