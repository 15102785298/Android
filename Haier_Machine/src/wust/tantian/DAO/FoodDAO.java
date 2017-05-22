package wust.tantian.DAO;

import java.util.List;

import wust.tantian.enity.Food;

public interface FoodDAO {
	
	public boolean save(Food food);

	public boolean update(Food food, int ID);

	public boolean delete(int ID);
	
	public boolean deleteAll();

	public List<Food> getByName(String name);

	public List<Food> getAll();

	public List<Food> fuzzyQuery(String text);

	public Food getByID(int ID);
}
