package by.restaurant.dao;

import java.util.List;

import by.restaurant.bean.Order;

public interface OrderDAO {

	void addOrder(Order order, int idUser)throws DAOException;
	List<Order> getOrdersInProcess() throws DAOException;
}
