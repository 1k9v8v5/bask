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
import android.widget.Spinner;
import android.widget.Toast;
import android.database.Cursor;
import java.util.HashMap;
import java.util.ArrayList;
public class addProduct extends Activity
{
	private dbmanag dbcreate;
	String LOG_TAG = "Log";
	private Date date;
	private TextView textlist;
	private TextView tdate;
	private EditText ename;
	private EditText ecount;
	private EditText eprice;
	private String posunit="";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
		dbcreate = new dbmanag(this);
		Intent intent = getIntent();
		//Log.d(LOG_TAG,intent.getStringExtra("id"));
		date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
		String strTime = simpleDateFormat.format(date);
		textlist = (TextView) findViewById(R.id.listNameExt);
		textlist.setText(intent.getStringExtra("id"));
		tdate = (TextView) findViewById(R.id.listDate);
		tdate.setText(strTime);
		ename = (EditText) findViewById(R.id.nameListEditText);
		ecount = (EditText) findViewById(R.id.countListEditText);
		eprice = (EditText) findViewById(R.id.priceEditText);
		ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;
		//map = new HashMap<String, String>();
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
		// адаптер
		SimpleAdapter adapter = new SimpleAdapter(this, myArrList,  android.R.layout.simple_spinner_item, 
												  new String[] {"name"},
												  new int[] {android.R.id.text1});
     //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
		//CustomAdapter adapter = new CustomAdapter(this, android.R.layout.simple_spinner_item, data);
    //   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
            // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id) {
					// Set adapter flag that something has been chosen
					//CustomAdapter.flag = true;
					//Parent is a Map structure and data
							posunit =Integer.toString(position);
							HashMap<String, Object> map = (HashMap<String, Object>) parent.getItemAtPosition(position);
							Toast.makeText(addProduct.this, map.get("unitID").toString(),Toast.LENGTH_SHORT).show();
				}
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
				}
			});
	}
	public void onSave(View view)
	{
		Log.d(LOG_TAG, ename.getText().toString() + " " + ecount.getText().toString() + " "
				+ posunit + eprice.getText().toString());
		dbcreate.open();
		String id_list="";
		Cursor cursorlistid = dbcreate.getAllDataListID();
		if (cursorlistid.moveToFirst()) {
			id_list = cursorlistid.getString(0);
			Log.d(LOG_TAG,id_list);
		}
		else{
			cursorlistid.close();
		}
		dbcreate.insertProd(posunit, ename.getText().toString(), ecount.getText().toString(), eprice.getText().toString(),id_list);
		dbcreate.close();
	}
	public void onClose(View view)
	{

	}
}
