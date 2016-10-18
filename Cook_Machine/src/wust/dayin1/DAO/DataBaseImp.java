package wust.dayin1.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import wust.dayin1.enity.Food;

public class DataBaseImp implements FoodDAO {

	private DBhelper dbhelper;

	public DataBaseImp(Context context) {
		dbhelper = new DBhelper(context);
	}

	@Override
	public boolean save(Food food) {
		boolean flag = false;
		SQLiteDatabase database = null;
		if (food != null) {
			String sql = "insert into food(name,level,time,effect,step,content,path) values(?,?,?,?,?,?,?)";
			Object[] params = new Object[] { food.getName(), food.getLevel(),
					food.getTime(), food.getEffect(), food.getStep(),
					food.getContent(), food.getPath() };
			database = dbhelper.getWritableDatabase();
			database.execSQL(sql, params);
			flag = true;
			database.close();
		}
		return flag;
	}

	@Override
	public boolean update(Food food, int ID) {
		boolean flag = false;
		SQLiteDatabase database = null;
		if (food != null) {
			String sql = "update food set name=?,level=?,time=?,effect=?,step=?,content=?,path=? where _id=?";
			Object[] params = new Object[] { food.getName(), food.getLevel(),
					food.getTime(), food.getEffect(), food.getStep(),
					food.getContent(), food.getPath(), ID };
			database = dbhelper.getWritableDatabase();
			database.execSQL(sql, params);
			flag = true;
			database.close();
		}
		return flag;
	}

	@Override
	public boolean delete(int ID) {
		boolean flag = false;
		SQLiteDatabase database = null;
		try {
			String sql = "delete from food where _id = ? ";
			String[] params = new String[] { String.valueOf(ID) };
			database = dbhelper.getWritableDatabase();
			database.execSQL(sql, params);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}
	
	public boolean deleteAll(){
		boolean flag = false;
		SQLiteDatabase database = null;
		try {
			String sql = "delete from food";
			database = dbhelper.getWritableDatabase();
			database.execSQL(sql, null);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (database != null) {
				database.close();
			}
		}
		return flag;
	}

	@Override
	public List<Food> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Food> getAll() {
		SQLiteDatabase database = null;
		Cursor cursor = null;
		Food food = null;
		List<Food> list = new ArrayList<Food>();
		String sql = "select * from food";
		database = dbhelper.getReadableDatabase();
		cursor = database.rawQuery(sql, null);
		while (cursor.moveToNext()) {
			food = new Food();
			food.setName(cursor.getString(cursor.getColumnIndex("name")));
			food.setEffect(cursor.getString(cursor.getColumnIndex("effect")));
			food.setTime(cursor.getString(cursor.getColumnIndex("time")));
			food.setLevel(cursor.getString(cursor.getColumnIndex("level")));
			food.setPath(cursor.getString(cursor.getColumnIndex("path")));
			food.setStep(cursor.getString(cursor.getColumnIndex("step")));
			food.setContent(cursor.getString(cursor.getColumnIndex("content")));
			list.add(food);
		}
		cursor.close();
		database.close();
		return list;
	}

	@Override
	public List<Food> fuzzyQuery(String text) {
		SQLiteDatabase database = null;
		List<Food> lists = new ArrayList<Food>();
		Cursor cursor = null;
		Food food = null;
		String sql = "select * from food where  name like ?";
		String[] params = new String[] { text + "%" };
		database = dbhelper.getReadableDatabase();
		cursor = database.rawQuery(sql, params);
		while (cursor.moveToNext()) {
			food = new Food();
			food.setName(cursor.getString(cursor.getColumnIndex("name")));
			food.setEffect(cursor.getString(cursor.getColumnIndex("effect")));
			food.setPath(cursor.getString(cursor.getColumnIndex("path")));
			lists.add(food);
		}
		database.close();
		cursor.close();
		return lists;
	}

	@Override
	public Food getByID(int ID) {
		SQLiteDatabase database = null;
		Cursor cursor = null;
		Food food = null;
		String sql = "select * from food where _id = ?";
		database = dbhelper.getReadableDatabase();
		String[] params = new String[] { String.valueOf(ID) };
		cursor = database.rawQuery(sql, params);
		if (cursor.moveToNext()) {
			food = new Food();
			food.setName(cursor.getString(cursor.getColumnIndex("name")));
			food.setEffect(cursor.getString(cursor.getColumnIndex("effect")));
			food.setTime(cursor.getString(cursor.getColumnIndex("time")));
			food.setLevel(cursor.getString(cursor.getColumnIndex("level")));
			food.setPath(cursor.getString(cursor.getColumnIndex("path")));
			food.setStep(cursor.getString(cursor.getColumnIndex("step")));
			food.setContent(cursor.getString(cursor.getColumnIndex("content")));
		}
		database.close();
		cursor.close();
		return food;
	}

}
