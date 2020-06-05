package by.restaurant.dao;

import java.util.List;

import by.restaurant.bean.Order;

public interface OrderDAO {

	boolean addOrder(Order order, int idUser)throws DAOException;
	List<Order> selectAllOrdersOfUser(int idUser) throws DAOException;
	List<Order> selectAllOrders() throws DAOException;
	int cancelOrder(int orderId) throws DAOException;
	int doOrder(int orderId) throws DAOException;
	int closeOrder(int orderId) throws DAOException;
	int getIdOrderByOrderTime(String orderTime) throws DAOException;
}
