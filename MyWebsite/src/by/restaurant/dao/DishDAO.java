package by.restaurant.dao;

import java.util.List;

import by.restaurant.bean.Dish;

public interface DishDAO {

	void addDish(Dish dish) throws DAOException;
	List<Dish> findSnacks() throws DAOException;
	List<Dish> findHotDishes() throws DAOException;
	List<Dish> findSalads() throws DAOException;
	List<Dish> findPizza() throws DAOException;
	List<Dish> findDesserts() throws DAOException;
	List<Dish> findDrinks() throws DAOException;


}
