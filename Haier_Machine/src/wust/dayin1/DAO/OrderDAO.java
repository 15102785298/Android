package wust.dayin1.DAO;

import java.util.List;

import wust.dayin1.enity.Food;
import wust.dayin1.enity.Order;

public interface OrderDAO {
	public boolean order_save(Order order);

	public boolean order_update(Order order, int ID);

	public boolean order_delete(int ID);

	public boolean order_deleteAll();

	public List<Order> order_getAll();

	public List<Order> order_fuzzyQuery(String text);

}
