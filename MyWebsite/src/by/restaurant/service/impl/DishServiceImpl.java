package by.restaurant.service.impl;

import java.util.List;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.bean.Order;
import by.restaurant.bean.User;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.DishDAO;
import by.restaurant.dao.OrderDAO;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.impl.validator.Validator;
import by.restaurant.service.impl.validator.ValidatorException;

public class DishServiceImpl implements DishService {

	
	@Override
	public boolean addDish(Dish dish) throws ServiceException {

		if(!Validator.validateIsNull(dish)) {
			//log
			throw new ValidatorException("Object dish is null!");
		}
		
		boolean added;

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			DishDAO dishDAO = daoFactory.getDishDAO();
			int status = dishDAO.addDish(dish);
			if(status == 1) {
				added = true;
			 }else {
				 added = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during changing dish in service", e);
		}
		return added;
	}
	
	@Override
	public List<Dish> searchDishByPartOfName(String partOfName) throws ServiceException {

		List<Dish> dishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 dishes = dishDAO.searchDishByPartOfName(partOfName); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding dish (in service)", e);
       }
		 return dishes;
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
	
	public void putDishToBasket(Basket basket, Dish dish, int count) {
		//валидация
		basket.getDishes().add(dish);
		basket.getCountDishById().put(dish.getId(), count);
	}
	
	public void deleteDishFromBasket(Basket basket, int idDish) {
		// валидация

		Dish dish = null;
		for (Dish d : basket.getDishes()) {
			if (d.getId() == idDish) {
				dish = d;
			}
		}

		basket.getDishes().remove(dish);
		basket.getCountDishById().remove(idDish);
		
	}
	
	public void clearBasket(Basket basket) {
		// валидация

		basket.getDishes().clear();
		basket.getCountDishById().clear();
	}
	
	public void countTotalPrice(Basket basket) {
		// валидация

		
		double totalPrice = 0;
		for(Dish dish : basket.getDishes()){
			totalPrice += dish.getPrice()*basket.getCountDishById().get(dish.getId());
		}
		basket.setTotalPrice(totalPrice);
	}


	@Override
	public List<Dish> getAllDishes() throws ServiceException {

		List<Dish> alldishes;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 DishDAO dishDAO = daoFactory.getDishDAO();
			 alldishes = dishDAO.getAllDishes(); 
			 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during finding all dishes (in service)", e);
       }
		 return alldishes;
	}


	@Override
	public boolean deleteDish(int dishId) throws ServiceException {

		boolean deleted;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			DishDAO orderDAO = daoFactory.getDishDAO();

			int status = orderDAO.deleteDish(dishId);
			if(status == 1) {
				deleted = true;
			 }else {
				 deleted = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during deleting dish (in service)", e);
		}
		return deleted;
	}


	@Override
	public boolean updateDish(int idDish, String picture, Double price, String amount) throws ServiceException {

		boolean updated;

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			DishDAO dishDAO = daoFactory.getDishDAO();
			int status = dishDAO.updateDish(idDish, picture, price, amount);
			if(status == 1) {
				updated = true;
			 }else {
				 updated = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during changing dish in service", e);
		}
		return updated;
	}

}
