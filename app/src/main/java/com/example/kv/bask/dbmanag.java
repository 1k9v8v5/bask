package com.example.kv.bask;

/**
 * Created by kv on 24.03.16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dbmanag implements Constants {


    private db db;
    private SQLiteDatabase base;
    private final Context context;
    private static String[] FROM_UNIT = {COLUMN_ID_UNIT,COLUMN_NAME_UNIT};
    public dbmanag(Context context){
        this.context = context;
    }
    // открыть подключение
    public void open() {
        db = new db(context, DB_NAME, null, DB_VERSION);
        base = db.getWritableDatabase();
    }
    // закрыть подключение
    public void close() {
        if (db!=null) db.close();
    }
    // получить все данные из таблицы DB_TABLE_UNIT
    public Cursor getAllDataUnit() {
        //return base.query(DB_TABLE_PROD, null, null, null, null, null, null);
         return base.query(DB_TABLE_UNIT, FROM_UNIT, null, null, null, null, null);
    }
}
