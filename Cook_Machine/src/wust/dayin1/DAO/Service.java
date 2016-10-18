package wust.dayin1.DAO;

import java.util.List;

import wust.dayin1.enity.Food;
import android.content.Context;

public class Service {
	
	private FoodDAO dao = null;
	
	public Service(Context context){
		dao = new DataBaseImp(context);
	}
	

	public boolean save(Food food) {
		boolean flag = dao.save(food);
		return flag;
	}

	public boolean update(Food food, int ID) {
		return dao.update(food, ID);
	}
	
	public boolean delete(int ID){
		return dao.delete(ID);
	}
	
	public boolean deleteAll(){
		return dao.deleteAll();
	}
	
	public Food getById(int ID) {
		return dao.getByID(ID);
	}
	
	public List<Food> getByName(String name){
		return dao.getByName(name);
	}

	public List<Food> getAll(Context context) {
		return dao.getAll();
	}
	
	public List<Food> fuzzyQuery(String text){
		return dao.fuzzyQuery(text);
	}
}
