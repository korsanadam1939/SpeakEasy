package com.erdemakgul.speakeasy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Kelimeler.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Veritabanı ilk oluşturulduğunda bu metod çağrılır.
        db.execSQL("CREATE TABLE kelimeler (id INTEGER PRIMARY KEY, firstword VARCHAR, secondword VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Veritabanı sürümü yükseltildiğinde bu metod çağrılır.
        db.execSQL("DROP TABLE IF EXISTS kelimeler");
        onCreate(db);
    }
}
