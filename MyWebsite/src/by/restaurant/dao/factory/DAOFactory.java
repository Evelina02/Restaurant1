package by.restaurant.dao.factory;

import by.restaurant.dao.DishDAO;
import by.restaurant.dao.OrderDAO;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.impl.DishDAOImpl;
import by.restaurant.dao.impl.OrderDAOImpl;
import by.restaurant.dao.impl.UserDAOImpl;

public final class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAOImpl = new UserDAOImpl();
	private final DishDAO dishDAOImpl = new DishDAOImpl();
	private final OrderDAO orderDAOImpl = new OrderDAOImpl();

	private DAOFactory() {}
	
	public static DAOFactory getInstance()
	{
		return instance;
	}
	
	public UserDAO getUserDAO()
	{
		return userDAOImpl;
	}
	public DishDAO getDishDAO()
	{
		return dishDAOImpl;
	}
	
	public OrderDAO getOrderDAO()
	{
		return orderDAOImpl;
	}
}
