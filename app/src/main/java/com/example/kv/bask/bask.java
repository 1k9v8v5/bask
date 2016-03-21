package com.example.kv.bask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.database.sqlite.*;
import android.content.*;
import android.app.*;
import android.database.Cursor;
import java.sql.Date;
public class bask extends AppCompatActivity implements Constants{
	private db dbcreate;
	String LOG_TAG = "Log";
	private static String[] FROM_PROD = {COLUMN_ID_PROD,COLUMN_ID_LIST_PROD,
		COLUMN_NAME_PROD,COLUMN_COUNT_PROD,COLUMN_ID_UNIT_PROD,COLUMN_PRICE_PROD};
	private static String[] FROM_UNIT = {COLUMN_ID_UNIT,COLUMN_NAME_UNIT};
	Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bask);
		dbcreate = new db(this);
		String dname =DB_TABLE_UNIT;
		String b=this.getDatabasePath(dname).getAbsolutePath();
		Log.d(LOG_TAG,b);
		SQLiteDatabase base = dbcreate.getReadableDatabase();
		/*ContentValues values = new ContentValues();
		values.put(COLUMN_NAME_UNIT, "нн");
		base.insertOrThrow(DB_TABLE_UNIT, null, values);*/

        /*base.execSQL("delete from unit");
		base.execSQL("insert into unit values(1,'шт');");*/
	//	base.execSQL("delete from product");
		/*base.execSQL("insert into product values(1," +
				(new java.util.Date().getTime()) + " ,'1','а','в',3,500.12);");*/
		/*base.execSQL("insert into list values(1," +
				(new java.util.Date().getTime()) + " ,'list');");*/

		Cursor cursor = base.query(DB_TABLE_PROD, FROM_PROD, null, null, null, null, null);
		while(cursor.moveToNext()) {
			int idColIndex = cursor.getColumnIndex("_id");
		//	long dateColIndex = cursor.getLong(1);
			int priceColIndex = cursor.getColumnIndex("price");
		//	date = new Date(dateColIndex);
			Log.d(LOG_TAG,"id = " + cursor.getString(idColIndex) + " | " +
					 +Double.parseDouble(cursor.getString(priceColIndex)));
		//		  "date = " + date + " " +Float.parseFloat(cursor.getString(priceColIndex)));
    }
		Cursor cursorunit = base.query(DB_TABLE_UNIT, FROM_UNIT, null, null, null, null, null);
		while(cursorunit.moveToNext()) {
			int idColIndex = cursorunit.getColumnIndex("unitID");;
			int nameColIndex = cursorunit.getColumnIndex("name");
			Log.d(LOG_TAG,"id = " + cursorunit.getString(idColIndex) + " | "
					 +cursorunit.getString(nameColIndex));
		}
}
}
