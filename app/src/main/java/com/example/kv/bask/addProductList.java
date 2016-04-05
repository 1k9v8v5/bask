package com.example.kv.bask;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.AdapterView.OnCreateContextMenuListener;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import android.widget.AdapterView.*;
public class addProductList extends AppCompatActivity implements LoaderCallbacks<Cursor>
{
	private dbmanag dbcreate;
	private ListView list;
	private SimpleCursorAdapter sca;
	private Cursor cursor;
	private TextView textlist;
    private static String id_list="";
	private String name_list="";
	private String _id = "";

	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list);
		Intent intent = getIntent();
		id_list = intent.getStringExtra("id");
		list = (ListView) findViewById(R.id.list_product);
		registerForContextMenu(list);
		dbcreate = new dbmanag(this);
		dbcreate.open();
		Cursor cursorlistid = dbcreate.getDataListName(id_list);
		if (cursorlistid.moveToFirst())
		{
			name_list = cursorlistid.getString(2);
			//Log.d("LOG_TAG",name_list);
		}
		else
		{
			cursorlistid.close();
		}
		textlist = (TextView) findViewById(R.id.name_list_product);
		textlist.setText(name_list);
		String[] from = new String[] {dbmanag.COLUMN_NAME_PROD};
		int[] to = new int[] {R.id.textv_list_product};
		cursor = dbcreate.getDataProductList(id_list);
		sca = new SimpleCursorAdapter(this, R.layout.item_list_product, cursor, from, to, 0);

		list.setAdapter(sca);
		/*list.setOnItemClickListener(new OnItemClickListener() {
		 public void onItemClick(AdapterView<?> parent, View view,
		 int position, long id) {


		 }
		 });*/
		/*list.setOnItemLongClickListener(new OnItemLongClickListener(){
				public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
				{
					_id = Long.toString(id);
					//Log.d("LOG_TAG"," _id");
					return true;
				}
			});*/
		/*list.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view,
										   int position, long id)
				{
					_id = Long.toString(id);
					Log.d("LOG_TAG"," _id");
				}
				
				public void onNothingSelected(AdapterView<?> parent)
				{
					Log.d("LOG_TAG", "itemSelect: nothing");
				}
			});*/
			
			
		getLoaderManager().initLoader(0, null, this);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.item:
				Intent addprodoflist = new Intent(addProductList.this, addProduct.class);
				addprodoflist.putExtra("id", id_list);
				addprodoflist.putExtra("text", name_list);
				startActivity(addprodoflist);
				return true;
			case R.id.del:
				dbcreate.deleteList(id_list);
				getLoaderManager().getLoader(0).forceLoad();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
    }

	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
									ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menuitem, menu);
	
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId())
		{
			case R.id.edit:
				Intent editproduct = new Intent(addProductList.this, editProduct.class);
				_id = Long.toString(info.id);
				//Log.d("LOG_TAG",info.id+"");
				
				editproduct.putExtra("_id", _id);
				editproduct.putExtra("id", id_list); 
				editproduct.putExtra("text", name_list);
				startActivity(editproduct);
				return true;
			case R.id.del_item:
				dbcreate.deleteProductItem(Long.toString(info.id));
				getLoaderManager().getLoader(0).forceLoad(); 
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}

	@Override
	public void onLoadFinished(Loader<Cursor> p1, Cursor p2)
	{
// TODO: Implement this method
		sca.swapCursor(p2);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> p1)
	{
// TODO: Implement this method
	}

	@Override
	public Loader<Cursor> onCreateLoader(int p1, Bundle p2)
	{
// TODO: Implement this method
		return new MyCursorLoader(this, dbcreate);
	}
	static class MyCursorLoader extends CursorLoader
	{

		dbmanag db;

		public MyCursorLoader(Context context, dbmanag db)
		{
			super(context);
			this.db = db;
		}

		@Override
		public Cursor loadInBackground()
		{

			Cursor cursor = db.getDataProductList(id_list);
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			return cursor;
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// закрываем подключение при выходе
		dbcreate.close();
	}
}
