package com.example.kv.bask;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.content.Intent;
import android.widget.*;
import android.database.Cursor;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
public class Plist extends AppCompatActivity implements OnClickListener
{
	private dbmanag dbcreate;
	private EditText edit;
	private Cursor cursor;
	private String id_list="";
	Button btnPlist;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
		dbcreate = new dbmanag(this);
		btnPlist = (Button) findViewById(R.id.addbuttonlist);
		edit = (EditText) findViewById(R.id.listedit);
		btnPlist.setOnClickListener(this);
		}
	public void onClick(View v)
	{
		// по id определеяем кнопку, вызвавшую этот обработчик
		switch (v.getId())
		{
			case R.id.addbuttonlist:
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
				break;
		}
	}
		
	/*public void onButtonListClick(View view)
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
	}*/
}
