package by.restaurant.dao;

import java.util.List;

import by.restaurant.bean.Dish;

public interface DishDAO {

	int addDish(Dish dish) throws DAOException;
	List<Dish> findSnacks() throws DAOException;
	List<Dish> findHotDishes() throws DAOException;
	List<Dish> findSalads() throws DAOException;
	List<Dish> findPizza() throws DAOException;
	List<Dish> findDesserts() throws DAOException;
	List<Dish> findDrinks() throws DAOException;
	List<Dish> getAllDishes() throws DAOException;
	int deleteDish(int dishId)  throws DAOException;
	int updateDish(int idDish, String picture, Double price, String amount)  throws DAOException;
	List<Dish> searchDishByPartOfName(String partOfName) throws DAOException;


}
