package com.tienlam.apporderfood.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SQLExecute extends SQLiteOpenHelper {

    public SQLExecute(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // Thực thi lênh SQL
    public void executeSQL(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    // Đọc dữ liệu
    public Cursor retrieveData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    // Đọc dữ liệu
    public Cursor searchData(String name){
        SQLiteDatabase database = getReadableDatabase();
        return database.query("food", new String[]{"id", "name", "price"}, "name LIKE ?", new String[]{"%"+ name+ "%"},null,null,null);
    }
}
