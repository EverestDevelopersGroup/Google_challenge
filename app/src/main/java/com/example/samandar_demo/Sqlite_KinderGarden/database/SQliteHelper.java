package com.example.samandar_demo.Sqlite_KinderGarden.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQliteHelper extends SQLiteOpenHelper {


    public SQliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // Insert data method
    public void insertdata(

                           String bogcha_name,
                           String viloyat,
                           String xizmatlar,
                           String kuntartib,
                           String tili,
                           String ratsion,
                           String bola_soni,
                           String tolov_oy,
                           String ustunlik_jihat,
                           String bonus,
                           String xavfsizlik,
                           String raqam,
                           String qoshimca_malumot,


                           byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, bogcha_name);
        statement.bindString(2, viloyat);
        statement.bindString(3, xizmatlar);
        statement.bindString(4, kuntartib);
        statement.bindString(5, tili);
        statement.bindString(6, ratsion);
        statement.bindString(7, bola_soni);
        statement.bindString(8, tolov_oy);
        statement.bindString(9, ustunlik_jihat);
        statement.bindString(10, bonus);
        statement.bindString(11, xavfsizlik);
        statement.bindString(12, raqam);
        statement.bindString(13, qoshimca_malumot);

        statement.bindBlob(14, image);

        statement.executeInsert();
    }

    // Get data method
    public Cursor getdata(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }

    // update data method
    public void updateData( String bogcha_name,
                            String viloyat,
                            String xizmatlar,
                            String kuntartib,
                            String tili,
                            String ratsion,
                            String bola_soni,
                            String tolov_oy,
                            String ustunlik_jihat,
                            String bonus,
                            String xavfsizlik,
                            String raqam,
                            String qoshimca_malumot,

                           byte[] image,
                           int id)



    {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE FOOD SET bogcha_name = ?, viloyat = ?, xizmatlar = ?, kuntartib = ?,tili = ?, ratsion = ?,bola_soni = ?, tolov_oy = ?,ustunlik_jihat = ?, bonus = ?,xavfsizlik = ?, raqam = ?,qoshimca_malumot = ?,    image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, bogcha_name);
        statement.bindString(2, viloyat);
        statement.bindString(3, xizmatlar);
        statement.bindString(4, kuntartib);
        statement.bindString(5, tili);
        statement.bindString(6, ratsion);
        statement.bindString(7, bola_soni);
        statement.bindString(8, tolov_oy);
        statement.bindString(9, ustunlik_jihat);
        statement.bindString(10, bonus);
        statement.bindString(11, xavfsizlik);
        statement.bindString(12, raqam);
        statement.bindString(13, qoshimca_malumot);
        statement.bindBlob(14, image);
        statement.bindDouble(15, (double) id);
        statement.execute();
        database.close();


    }

    // delete data method
    public void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM FOOD WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        ;
        statement.bindDouble(1, (double) id);
        statement.execute();
        database.close();
    }


}
