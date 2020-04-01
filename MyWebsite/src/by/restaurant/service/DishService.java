package by.restaurant.service;

import java.util.List;

import by.restaurant.bean.Dish;
import by.restaurant.dao.DAOException;

public interface DishService {

	void addDish(Dish dish) throws ServiceException;
	List<Dish> findSnacks() throws ServiceException;
	List<Dish> findHotDishes() throws ServiceException;
	List<Dish> findSalads() throws ServiceException;
	List<Dish> findPizza() throws ServiceException;
	List<Dish> findDesserts() throws ServiceException;
	List<Dish> findDrinks() throws ServiceException;

}
