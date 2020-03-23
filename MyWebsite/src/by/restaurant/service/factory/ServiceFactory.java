package by.restaurant.service.factory;

import by.restaurant.service.DishService;
import by.restaurant.service.UserService;
import by.restaurant.service.impl.DishServiceImpl;
import by.restaurant.service.impl.UserServiceImpl;

public final class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();
	
	private final UserService userService = new UserServiceImpl();
	private final DishService dishService = new DishServiceImpl();

	private ServiceFactory() {}

	public static ServiceFactory getInstance()
	{
		return instance;
	}
	
	public UserService getUserService()
	{
		return userService;
	}
	
	public DishService getDishService()
	{
		return dishService;
	}
}
