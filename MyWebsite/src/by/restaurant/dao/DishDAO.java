package by.restaurant.dao;

import by.restaurant.bean.Dish;

public interface DishDAO {

	int addDish(Dish dish) throws DAOException;
}
