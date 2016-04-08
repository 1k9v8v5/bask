package com.example.kv.bask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.*;
import android.util.Log;
import android.database.sqlite.*;
import android.content.*;
import android.app.*;
import android.database.Cursor;
import java.sql.Date;
import android.widget.*;
import android.view.*;
import java.text.SimpleDateFormat;
public class BasketList extends AppCompatActivity implements Constants
{
	private dbmanag dbcreate;
	private ListView list;
	SimpleCursorAdapter	adapter;

	/*private static String[] FROM = {COLUMN_NAME_LIST};
	private int[] TO ={R.id.datelistid};*/
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.basketlist);
		/*dbcreate = new dbmanag(this);
		dbcreate.open();
		Cursor cursorlist = dbcreate.getAllDataList();
		while(cursorlist.moveToNext()) {
			name_list=cursorlist.getString(2);
			//int idColIndex = cursorlist.getColumnIndex("_id");
			//	int nameColIndex = cursofrunit.getColumnIndex("name");
			Log.d("t",name_list);
		}
		cursorlist.close();
		dbcreate.close();*/
		list = (ListView) findViewById(R.id.list_view);
		dbcreate = new dbmanag(this);
		dbcreate.open();
		String[] from = new String[] {dbmanag.COLUMN_DATE_LIST,dbmanag.COLUMN_NAME_LIST};
		int[] to = new int[] {R.id.datelistid,R.id.listid};
		Cursor cursorlist = dbcreate.getAllDataList();
		adapter = new SimpleCursorAdapter(this, R.layout.basket_list, cursorlist, from, to);	
		list.setAdapter(adapter);
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// закрываем подключение при выходе
		dbcreate.close();
	}
}
