package wust.dayin1.DAO;

import wust.dayin1.pub.food_String;
import wust.dayin1.pub.order_String;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = food_String.TABLE_NAME;
	public static final String NAME = food_String.NAME;
	public static final String LEVEL = food_String.LEVEL;
	public static final String TIME = food_String.TIME;
	public static final String EFFECT = food_String.EFFECT;
	public static final String STEP = food_String.STEP;
	public static final String CONTENT = food_String.CONTENT;
	public static final String PATH = food_String.PATH;
	public static final String ID = food_String.ID;

	/** @see this database name is food */
	public DBhelper(Context context) {
		super(context, "food", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME
				+ " TEXT NOT NULL," + LEVEL + " TEXT NOT NULL," + TIME
				+ " TEXT NOT NULL," + EFFECT + " TEXT NOT NULL," + STEP
				+ " TEXT NOT NULL," + CONTENT + " TEXT NOT NULL," + PATH
				+ " TEXT NOT NULL)");
		db.execSQL("CREATE TABLE " + order_String.TABLE_NAME + " (" + order_String.ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + order_String.FOOD_NAME
				+ " TEXT NOT NULL," + order_String.ADDRESS + " TEXT NOT NULL," + order_String.ALL_PAY
				+ " TEXT NOT NULL," + order_String.CONTENT + " TEXT NOT NULL," + order_String.NUM
				+ " TEXT NOT NULL," + order_String.PRICE + " TEXT NOT NULL," + order_String.PHONE
				+ " TEXT NOT NULL)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
