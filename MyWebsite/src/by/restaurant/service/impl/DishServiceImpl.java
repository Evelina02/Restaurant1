package by.restaurant.service.impl;

import java.util.List;

import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.DishDAO;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.impl.validator.Validator;
import by.restaurant.service.impl.validator.ValidatorException;

public class DishServiceImpl implements DishService {

	
	@Override
	public void addDish(Dish dish) throws ServiceException {

		if(!Validator.validateIsNull(dish)) {
			//log
			throw new ValidatorException("Object dish is null!");
		}
		
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishDAO.addDish(dish);; 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during adding dish (in service)", e);
       }
	}
	

	@Override
	public List<Dish> findSnacks() throws ServiceException {
		
		List<Dish> dishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishes = dishDAO.findSnacks(); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding snacks (in service)", e);
       }
		 return dishes;
	}

	@Override
	public List<Dish> findHotDishes() throws ServiceException {

		List<Dish> dishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishes = dishDAO.findHotDishes(); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding hot dishes (in service)", e);
       }
		 return dishes;
	}

	@Override
	public List<Dish> findSalads() throws ServiceException {

		List<Dish> dishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishes = dishDAO.findSalads(); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding salads (in service)", e);
       }
		 return dishes;
	}

	@Override
	public List<Dish> findPizza() throws ServiceException {

		List<Dish> dishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishes = dishDAO.findPizza(); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding pizza (in service)", e);
       }
		 return dishes;
	}

	@Override
	public List<Dish> findDesserts() throws ServiceException {

		List<Dish> dishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishes = dishDAO.findDesserts(); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding desserts (in service)", e);
       }
		 return dishes;
	}

	@Override
	public List<Dish> findDrinks() throws ServiceException {

		List<Dish> dishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishes = dishDAO.findDrinks(); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding drinks (in service)", e);
       }
		 return dishes;
	} 
	
	
}
