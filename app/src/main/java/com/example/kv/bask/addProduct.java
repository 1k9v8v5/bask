package com.example.kv.bask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.util.Log;
import android.widget.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.text.*;public class addProduct extends Activity
{
	String LOG_TAG = "Log";
	Date date;
	TextView textlist;
	TextView tdate;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
		Intent intent = getIntent();
		Log.d(LOG_TAG,intent.getStringExtra("id"));
		date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
		String strTime = simpleDateFormat.format(date);
		textlist = (TextView) findViewById(R.id.listNameExt);
		textlist.setText(intent.getStringExtra("id"));
		tdate = (TextView) findViewById(R.id.listDate);
		tdate.setText(strTime);
	}
	public void onSave(View view)
	{
	
	}
	public void onClose(View view)
	{

	}
}
