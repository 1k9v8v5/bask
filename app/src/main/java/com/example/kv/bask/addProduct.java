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
import android.database.Cursor;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.text.*;public class addProduct extends Activity
{
	private dbmanag dbcreate;
	String[] data = {"one", "two", "three", "four", "five"};
	String LOG_TAG = "Log";
	Date date;
	TextView textlist;
	TextView tdate;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
		dbcreate = new dbmanag(this);
		/*dbcreate.open();
		Cursor cursorunit=dbcreate.getAllDataUnit();
		while(cursorunit.moveToNext()) {
			int idColIndex = cursorunit.getColumnIndex("unitID");;
			int nameColIndex = cursorunit.getColumnIndex("name");
			Log.d(LOG_TAG,"id = " + cursorunit.getString(idColIndex) + " | "
					+cursorunit.getString(nameColIndex));
		}
		dbcreate.close();*/
		Intent intent = getIntent();
		Log.d(LOG_TAG,intent.getStringExtra("id"));
		date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
		String strTime = simpleDateFormat.format(date);
		textlist = (TextView) findViewById(R.id.listNameExt);
		textlist.setText(intent.getStringExtra("id"));
		tdate = (TextView) findViewById(R.id.listDate);
		tdate.setText(strTime);
		
		ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		map = new HashMap<String, String>();
		dbcreate.open();
		
		Cursor cursorunit=dbcreate.getAllDataUnit();
		while(cursorunit.moveToNext()) {
			int idColIndex = cursorunit.getColumnIndex("unitID");;
			int nameColIndex = cursorunit.getColumnIndex("name");
			map = new HashMap<String, String>();
			//map.put(cursorunit.getString(idColIndex),cursorunit.getString(nameColIndex));
			map.put("unitID",cursorunit.getString(idColIndex));
			map.put("name",cursorunit.getString(nameColIndex));
			myArrList.add(map);
		}
		dbcreate.close();
		  
		for (HashMap.Entry entry : map.entrySet()) {
			Log.d(LOG_TAG,"Key: " + entry.getKey() + " Value: "
							   + entry.getValue());
		}
		// Получаем набор элементов
	/*	Set<HashMap.Entry<String,String >> set = map.entrySet();

// Отобразим набор
		for (HashMap.Entry<String, String> me : set) {
			Log.d(LOG_TAG,me.getKey() + ": ");
			Log.d(LOG_TAG,me.getValue());
		}*/
		/*for (String key : map.keySet()) {
			Log.d(LOG_TAG,"Key: " + key);
		}
		*/
		/*for (String value : map.values()) {
			Log.d(LOG_TAG,"Value: " + value);
		}*/
		
		/*map.put("Name", "Мурзик");
		map.put("Tel", "495 501-3545");
		myArrList.add(map);
		
		map = new HashMap<String, String>();
		map.put("Name", "Васька");
		map.put("Tel", "495 431-5468");
		myArrList.add(map);*/
		
		
		
		// адаптер
		
		SimpleAdapter adapter = new SimpleAdapter(this, myArrList,  android.R.layout.simple_spinner_item, 
												  new String[] {"name"}, 
												  new int[] {android.R.id.text1});
     //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
		//CustomAdapter adapter = new CustomAdapter(this, android.R.layout.simple_spinner_item, data);
    //   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        // заголовок
     /*   spinner.setPrompt("Title");
        // выделяем элемент 
        spinner.setSelection(2);*/
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id) {
					// показываем позиция нажатого элемента
					//Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

					// Set adapter flag that something has been chosen
					//CustomAdapter.flag = true;
					
					//Parent is a Map structure and data
					               HashMap<String, Object> map = (HashMap<String, Object>) parent
					                        .getItemAtPosition(position);
					              Toast.makeText(addProduct.this,
								   map.get("unitID").toString(),
					                        Toast.LENGTH_SHORT).show();
					
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
