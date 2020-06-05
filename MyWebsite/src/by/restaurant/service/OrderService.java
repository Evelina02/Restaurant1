package by.restaurant.service;

import java.util.List;

import by.restaurant.bean.Order;
import by.restaurant.bean.User;

public interface OrderService {

	boolean addOrder(Order order, int idUser) throws ServiceException;
	List<Order> allOrders() throws ServiceException;
	List<Order> allOrdersOfUser(int idUser) throws ServiceException;
	boolean cancelOrder(int orderId)  throws ServiceException;
	boolean closeOrder(int orderId) throws ServiceException;
	boolean doOrder(int orderId) throws ServiceException;
	int getIdOrderByOrderTime(String orderTime) throws ServiceException;
}
