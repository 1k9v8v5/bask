package com.example.kv.bask;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.content.Intent;
import android.widget.*;
public class Plist extends Activity
{
	private dbmanag dbcreate;
	private EditText edit;
	
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
		dbcreate.close();
		Intent addprod = new Intent(Plist.this,addProduct.class);
		addprod.putExtra("id",edit.getText().toString());
		startActivity(addprod);
	}
}
