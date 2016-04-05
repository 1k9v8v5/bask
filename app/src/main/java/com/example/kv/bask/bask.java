package com.example.kv.bask;

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

public class bask extends AppCompatActivity implements Constants{
	private dbmanag dbcreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bask);
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.item:
				Intent plist = new Intent(bask.this,Plist.class);
				startActivity(plist);
				return true;
			case R.id.del:
				dbcreate.open();
				//dbcreate.deleteList();
				Cursor cursorunit=dbcreate.getAllDataProduct();
				while(cursorunit.moveToNext()) {
					int idColIndex = cursorunit.getColumnIndex("_id");;
					//	int nameColIndex = cursorunit.getColumnIndex("name");
					Log.d("t",cursorunit.getString(idColIndex));
				}
				dbcreate.close();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
    }
	public void onButtonClick(View view)
	{
		Intent plist = new Intent(bask.this,Plist.class);
		startActivity(plist);
	}
}
