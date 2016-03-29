package com.example.kv.bask;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.content.Intent;
import android.widget.*;
import android.database.Cursor;
import android.util.Log;
public class addProductList extends Activity
{
	private dbmanag dbcreate;
	private ListView list;
	private SimpleCursorAdapter sca;
	private Cursor cursor;
	private TextView textlist;
	private String id_list="";
	String name_list="";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list);
		Intent intent = getIntent();
		id_list = intent.getStringExtra("id");
		dbcreate = new dbmanag(this);
		dbcreate.open();
		Cursor cursorlistid = dbcreate.getDataListName(id_list);
		if (cursorlistid.moveToFirst()) {
			name_list = cursorlistid.getString(2);
			Log.d("LOG_TAG",name_list);
		}
		else{
			cursorlistid.close();
		}
		textlist = (TextView) findViewById(R.id.name_list_product);
		textlist.setText(name_list);
		String[] from = new String[] {dbmanag.COLUMN_NAME_PROD};
		int[] to = new int[] {R.id.textv_list_product};
		cursor = dbcreate.getDataProductList(id_list);
		sca = new SimpleCursorAdapter(this, R.layout.item_list_product,cursor, from, to, 0);
		list = (ListView) findViewById(R.id.list_product);
		list.setAdapter(sca);
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
				Intent addprodoflist = new Intent(addProductList.this,addProduct.class);
				addprodoflist.putExtra("id",id_list);
				addprodoflist.putExtra("text",name_list);
				startActivity(addprodoflist);
				return true;
			case R.id.del:

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
    }
	protected void onDestroy() {
		super.onDestroy();
		// закрываем подключение при выходе
		dbcreate.close();
	}
}
