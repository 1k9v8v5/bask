package com.example.kv.bask;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.content.Intent;
import android.widget.*;
import android.database.Cursor;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
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
		list = (ListView) findViewById(R.id.list_product);
		registerForContextMenu(list);
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
		
		list.setAdapter(sca);
		list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {
					Log.d("LOG_TAG", "itemClick: position = " + position + ", id = "
						  + id);
				}
			});
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
			dbcreate.deleteList(id_list);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
    }
	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menuitem, menu);
}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
			case R.id.edit:
				
				return true;
			case R.id.del_item:
				
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}
	protected void onDestroy() {
		super.onDestroy();
		// закрываем подключение при выходе
		dbcreate.close();
	}
}
