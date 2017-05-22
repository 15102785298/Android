package wust.tantian.DAO;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import wust.tantian.enity.Food;
import wust.tantian.enity.Order;
import wust.tantian.pub.order_String;

public class DataBaseImp implements FoodDAO, OrderDAO {

	private DBhelper dbhelper;

	public DataBaseImp(Context context) {
		dbhelper = new DBhelper(context);
	}

	@Override
	public boolean save(Food food) {
		boolean flag = false;
		SQLiteDatabase database = null;
		if (food != null) {
			String sql = "insert into food (name,level,time,effect,step,content,path) values(?,?,?,?,?,?,?)";
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

	public boolean deleteAll() {
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

	/**
	 * 保存订单
	 */
	@Override
	public boolean order_save(Order order) {
		boolean flag = false;
		SQLiteDatabase database = null;
		StringBuffer sb_sql = new StringBuffer();
		if (order != null) {
			sb_sql.append("insert into ");
			sb_sql.append(order_String.TABLE_NAME);
			sb_sql.append(" ( ");
			sb_sql.append(order_String.ADDRESS);
			sb_sql.append(" , ");
			sb_sql.append(order_String.ALL_PAY);
			sb_sql.append(" , ");
			sb_sql.append(order_String.CONTENT);
			sb_sql.append(" , ");
			sb_sql.append(order_String.FOOD_NAME);
			sb_sql.append(" , ");
			sb_sql.append(order_String.NUM);
			sb_sql.append(" , ");
			sb_sql.append(order_String.PHONE);
			sb_sql.append(" , ");
			sb_sql.append(order_String.PRICE);
			sb_sql.append(" ) values(?,?,?,?,?,?,?) ");
			String sql = sb_sql.toString();
			Object[] params = new Object[] { order.getAddress(),
					order.getAll_pay(), order.getContent(),
					order.getFood_name(), order.getNum(), order.getPhone(),
					order.getPrice() };
			database = dbhelper.getWritableDatabase();
			database.execSQL(sql, params);
			flag = true;
			database.close();
		}
		return flag;
	}

	/**
	 * 更新订单（根据id）
	 */
	@Override
	public boolean order_update(Order order, int ID) {
		boolean flag = false;
		SQLiteDatabase database = null;
		if (order != null) {
			StringBuffer sb_sql = new StringBuffer();
			sb_sql.append("update ");
			sb_sql.append(order_String.TABLE_NAME);
			sb_sql.append(" set ");
			sb_sql.append(order_String.ADDRESS);
			sb_sql.append("=?,");
			sb_sql.append(order_String.ALL_PAY);
			sb_sql.append("=?,");
			sb_sql.append(order_String.CONTENT);
			sb_sql.append("=?,");
			sb_sql.append(order_String.FOOD_NAME);
			sb_sql.append("=?,");
			sb_sql.append(order_String.NUM);
			sb_sql.append("=?,");
			sb_sql.append(order_String.PHONE);
			sb_sql.append("=?,");
			sb_sql.append(order_String.PRICE);
			sb_sql.append("=?");
			String sql = sb_sql.toString();
			Object[] params = new Object[] { order.getAddress(),
					order.getAll_pay(), order.getContent(),
					order.getFood_name(), order.getNum(), order.getPhone(),
					order.getPrice() };
			database = dbhelper.getWritableDatabase();
			database.execSQL(sql, params);
			flag = true;
			database.close();
		}
		return flag;
	}

	/**
	 * 根据id删除某一订单 ，目前用不上
	 */
	@Override
	public boolean order_delete(int ID) {
		boolean flag = false;
		SQLiteDatabase database = null;
		try {
			StringBuffer sb_sql = new StringBuffer();
			sb_sql.append("delete from ");
			sb_sql.append(order_String.TABLE_NAME);
			sb_sql.append(" where _id = ? ");
			String sql = sb_sql.toString();
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

	/**
	 * 删除所有的订单
	 */
	@Override
	public boolean order_deleteAll() {
		boolean flag = false;
		SQLiteDatabase database = null;
		try {
			StringBuffer sb_sql = new StringBuffer();
			sb_sql.append("delete from ");
			sb_sql.append(order_String.TABLE_NAME);
			String sql = sb_sql.toString();
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
	public List<Order> order_getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据订单中菜品的名称进行订单的模糊查询
	 * 
	 * @see 下期实现
	 * 
	 */
	@Override
	public List<Order> order_fuzzyQuery(String food_name) {
		// TODO Auto-generated method stub
		return null;
	}

}
