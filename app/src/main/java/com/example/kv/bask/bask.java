package com.example.kv.bask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.database.sqlite.*;
import android.content.*;
import android.app.*;
import android.database.Cursor;
public class bask extends AppCompatActivity implements Constants{
	private db dbcreate;
	String LOG_TAG = "Log";
	private static String[] FROM = {COLUMN_ID_PROD,COLUMN_DATE_PROD,COLUMN_LIST_PROD,
		COLUMN_NAME_PROD,COLUMN_COUNT_PROD,COLUMN_ID_UNIT_PROD,COLUMN_PRICE_PROD};
	
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
		base.execSQL("insert into unit values(1,'шт');");
		base.execSQL("insert into product values(1,(" +

					    (new java.util.Date()).getTime() + ") ,'1','а','в',3,500);");	
	/*base.execSQL("delete from unit");
		base.execSQL("delete from product");*/
		Cursor cursor = base.query(DB_TABLE_PROD, FROM, null, null, null, null, null);

		while(cursor.moveToNext()) {
			int idColIndex = cursor.getColumnIndex("_id");
			int nameColIndex = cursor.getColumnIndex("unitID");	
			Log.d(LOG_TAG,"id = "+cursor.getString(idColIndex)+" | "+
				  "name = " + cursor.getString(nameColIndex));
    }
}
}
