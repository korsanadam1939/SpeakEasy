package com.erdemakgul.speakeasy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {

    private DatabaseHelper dbHelper;

    public DataManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addEntry(String firstWord, String secondWord) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstword", firstWord);
        values.put("secondword", secondWord);
        db.insert("kelimeler", null, values);
        db.close();
    }

    public String printEntries(int idnumber) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // `sayi`'yı `String`'e dönüştürüyoruz
        Cursor cursor = db.rawQuery("SELECT * FROM kelimeler WHERE id= "+idnumber, null);


        String result="bulunamadı.";



        if (cursor != null && cursor.moveToFirst()) {

            int firstWordIx = cursor.getColumnIndex("firstword");

            // 'id' kolonundan değer al
            result = cursor.getString(firstWordIx);
        }


        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return result;
    }
    public int iddondur(String word,int a) {
        int result = -1; // Varsayılan döndürme değeri (bulunamadı)

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if(a==1){
            // SQL sorgusu: firstword sütununda verilen 'word' değeri ile eşleşen id'yi getirir
            Cursor cursor = db.rawQuery("SELECT id FROM kelimeler WHERE firstword = ?", new String[]{word});

            if (cursor != null && cursor.moveToFirst()) {
                int idIx = cursor.getColumnIndex("id");
                // 'id' kolonundan değer al
                result = cursor.getInt(idIx);
            }

            // Cursor ve database bağlantısını kapat
            if (cursor != null) {
                cursor.close();
            }
            db.close();

            return result;
        }
        else {
            // SQL sorgusu: firstword sütununda verilen 'word' değeri ile eşleşen id'yi getirir
            Cursor cursor = db.rawQuery("SELECT id FROM kelimeler WHERE secondword = ?", new String[]{word});

            if (cursor != null && cursor.moveToFirst()) {
                int idIx = cursor.getColumnIndex("id");
                // 'id' kolonundan değer al
                result = cursor.getInt(idIx);
            }

            // Cursor ve database bağlantısını kapat
            if (cursor != null) {
                cursor.close();
            }
            db.close();

            return result;

        }


    }

    public void listeyeekle(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM kelimeler", null);


        int idIx = cursor.getColumnIndex("id");

        while (cursor.moveToNext()) {
            System.out.println(cursor.getInt(idIx));
            Ekle.numbers.add(cursor.getInt(idIx));
        }

        cursor.close();
        db.close();



    }

    public void deleteEntry(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 'kelimeler' tablosundan belirtilen 'id' ile eşleşen kaydı siler
        db.delete("kelimeler", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public String ikinciyibul(String ilkkelime) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // SQL sorgusunda ilkkelime'yi tırnak içine al
        Cursor cursor = db.rawQuery("SELECT * FROM kelimeler WHERE firstword = ?", new String[]{ilkkelime});

        String result = "bulunamadı.";

        if (cursor != null && cursor.moveToFirst()) {
            int secondWordIx = cursor.getColumnIndex("secondword");

            if (secondWordIx != -1) {
                result = cursor.getString(secondWordIx);
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return result;
    }









}
