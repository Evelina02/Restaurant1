package by.restaurant.service;

import java.util.List;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.bean.Order;
import by.restaurant.dao.DAOException;

public interface DishService {

	boolean addDish(Dish dish) throws ServiceException;
	List<Dish> getAllDishes() throws ServiceException;
	List<Dish> findSnacks() throws ServiceException;
	List<Dish> findHotDishes() throws ServiceException;
	List<Dish> findSalads() throws ServiceException;
	List<Dish> findPizza() throws ServiceException;
	List<Dish> findDesserts() throws ServiceException;
	List<Dish> findDrinks() throws ServiceException;

	void putDishToBasket(Basket basket, Dish dish, int count) throws ServiceException;
	void deleteDishFromBasket(Basket basket, int idDish) throws ServiceException;
	void clearBasket(Basket basket) throws ServiceException;
	void countTotalPrice(Basket basket) throws ServiceException;
	boolean deleteDish(int dishId) throws ServiceException;
	boolean updateDish(int idDish, String picture, Double price, String amount) throws ServiceException;
	List<Dish> searchDishByPartOfName(String partOfName) throws ServiceException;

}
