package com.example.kv.bask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.util.Log;
import android.widget.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.*;public class addProduct extends Activity
{
	String[] data = {"one", "two", "three", "four", "five"};
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
		// адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент 
     /*   spinner.setSelection(2);*/
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id) {
					// показываем позиция нажатого элемента
					Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});
	}
	public void onSave(View view)
	{
	
	}
	public void onClose(View view)
	{

	}
}
