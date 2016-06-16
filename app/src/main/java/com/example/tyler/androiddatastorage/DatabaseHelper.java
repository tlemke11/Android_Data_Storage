package com.example.tyler.androiddatastorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by Tyler on 6/15/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "number_db";
    public static final String TABLE_NAME = "number_table";
    public static final String COL_1 = "ID";
    public static final String COL_2= "NUMBER";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT, NUMBER TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);

    }

    public boolean insertData(String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, number);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor resultCursor = db.rawQuery("SELECT "+ COL_2 + " from " + TABLE_NAME + " ; ", null);
        System.out.println("Got raqQuery");
        return resultCursor;
    }


    public boolean updateData(String id, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, number);

        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{ id });
        return true;
    }
}


