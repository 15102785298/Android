package wust.dayin1.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "food";
	public static final String NAME = "name";
	public static final String LEVEL = "level";
	public static final String TIME = "time";
	public static final String EFFECT = "effect";
	public static final String STEP = "step";
	public static final String CONTENT = "content";
	public static final String PATH = "path";
	public static final String ID = "_id";

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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
