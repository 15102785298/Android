package wust.tantian.DAO;

import java.util.List;

import wust.tantian.enity.Food;
import wust.tantian.enity.Order;

public interface OrderDAO {
	public boolean order_save(Order order);

	public boolean order_update(Order order, int ID);

	public boolean order_delete(int ID);

	public boolean order_deleteAll();

	public List<Order> order_getAll();

	public List<Order> order_fuzzyQuery(String text);

}
