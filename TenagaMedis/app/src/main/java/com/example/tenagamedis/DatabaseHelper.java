package com.example.tenagamedis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.tenagamedis.DaftarContract.*;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Initialize Database Name and Table Name
    private static final String DATABASE_NAME = "daftar.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_DAFTARLIST_TABLE = "CREATE TABLE " +
                DaftarContract.DaftarEntry.TABLE_NAME + " (" +
                DaftarEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DaftarEntry.COLUMN_1 + " TEXT NOT NULL, " +
                DaftarEntry.COLUMN_2 + " TEXT NOT NULL, " +
                DaftarEntry.COLUMN_3 + " TEXT NOT NULL, " +
                DaftarEntry.COLUMN_4 + " TEXT NOT NULL, " +
                DaftarEntry.COLUMN_5 + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_DAFTARLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DaftarEntry.TABLE_NAME);
        onCreate(db);

    }

//    public boolean addText(String text){
//        //get WriteAble Database
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        //Create ContentValues
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("txt",text);
//        //Add Values into Database
//        sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
//        return true;
//    }
//
//    public ArrayList getAllText() {
//        //Get Readable Database
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        ArrayList<String> arrayList = new ArrayList<String>();
//        //Create Cursor to select all values
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME
//                , null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            arrayList.add(cursor.getString(cursor.getColumnIndex("txt")));
//            cursor.moveToNext();
//        }
//        return arrayList;
//    }
}
