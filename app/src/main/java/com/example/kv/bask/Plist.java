package com.example.kv.bask;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.content.Intent;
import android.widget.*;
public class Plist extends Activity
{
	TextView text;
	EditText edit;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
		edit = (EditText) findViewById(R.id.listedit);
		}
	public void onButtonListClick(View view)
	{
		Intent addprod = new Intent(Plist.this,addProduct.class);
		addprod.putExtra("id",edit.getText().toString());
		startActivity(addprod);
	}
}
