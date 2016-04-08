package com.example.kv.bask;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.content.Intent;
import android.widget.*;
import android.database.Cursor;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
public class Plist extends AppCompatActivity
{
	private dbmanag dbcreate;
	private EditText edit;
	private Cursor cursor;
	private String id_list="";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
		dbcreate = new dbmanag(this);
		edit = (EditText) findViewById(R.id.listedit);
		}
	public void onButtonListClick(View view)
	{
		dbcreate.open();
		dbcreate.insertList(edit.getText().toString());
		Cursor cursorlistid = dbcreate.getAllDataListID();
		if (cursorlistid.moveToFirst()) {
			id_list = cursorlistid.getString(0);
			Log.d("LOG_TAG",id_list);
		}
		else{
			cursorlistid.close();
		}
		dbcreate.close();
		Intent addprod = new Intent(Plist.this,addProduct.class);
		addprod.putExtra("text",edit.getText().toString());
		addprod.putExtra("id",id_list);
		startActivity(addprod);
	}
}
