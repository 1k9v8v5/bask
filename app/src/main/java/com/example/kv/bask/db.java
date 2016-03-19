package com.example.kv.bask;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class db extends SQLiteOpenHelper implements Constants

{
	public db(Context context, String name, CursorFactory factory,
			  int version) {
		super(context, name, factory, version);
    }
	public db (Context context){
		super(context,DB_NAME,null,11);
	}
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		p1.execSQL("create table "+ DB_TABLE_UNIT + " ("
				   + COLUMN_ID_UNIT + " integer primary key autoincrement, " 
				   + COLUMN_NAME_UNIT + " text " + ");");
		
		//p1.execSQL("insert into DB_TABLE_UNIT values(1,`шт`)");		   
				   
		p1.execSQL("create table "+ DB_TABLE_PROD + " ("
				   + COLUMN_ID_PROD + " integer primary key autoincrement, " 
				   + COLUMN_DATE_PROD + " integer, "
				   + COLUMN_ID_UNIT_PROD + " integer, "
				   + COLUMN_LIST_PROD + " text, "
				   + COLUMN_NAME_PROD + " text, "
				   + COLUMN_COUNT_PROD + " integer, "
				   + COLUMN_PRICE_PROD  + " real, " 
				   + "foreign key( " + COLUMN_ID_UNIT_PROD + " ) references "
				   + DB_TABLE_UNIT + "(" + COLUMN_ID_UNIT + ")" + ");");
		
		//p1.execSQL("insert into DB_TABLE_PROD values(1,(" +

			//	   (new java.util.Date()).getTime() + ") ,а,в,3,1,500)");	   
				   
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int p2, int p3)
	{
		// TODO: Implement this method
		db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PROD);
		onCreate(db);
	}

}

