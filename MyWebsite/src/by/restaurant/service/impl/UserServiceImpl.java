package by.restaurant.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.DishDAO;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.impl.validator.Validator;
import by.restaurant.service.impl.validator.ValidatorException;

public class UserServiceImpl implements UserService {
	
	@Override
	public User getUser(String login, String password) throws ServiceException{
		
		if(!Validator.validateLogin(login)) {
			//log
			throw new ValidatorException("Wrong login!");
		}
		if(!Validator.validatePassword(password)) {
			//log
			throw new ValidatorException("Wrong password!");
		}
		User user;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 user = userDAO.getUser(login, password);
		 } catch (DAOException e) {
			 throw new ServiceException("Error during getting user (in service)", e);
       }
		 return user;
	}

	@Override
	public boolean addUser(User user) throws ServiceException {

		if(!Validator.validateIsNull(user)) {
			//log
			throw new ValidatorException("Object user is null!");
		}
		
		boolean added;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 int status = userDAO.addUser(user);
			 if(status == 1) {
				 added = true;
			 }else {
				 added = false;
			 }
		 } catch (DAOException e) {
			 throw new ServiceException("Error during additing user (in service)", e);
       }
		 return added;
	}

	@Override
	public ArrayList<User> getAllActiveUsers() throws ServiceException {
		
		ArrayList<User> users;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 users = userDAO.findActiveUsers(); 
		 } catch (DAOException e) {
			 throw new ServiceException("Error during getting all active users (in service)", e);
       }
		 return users;
	}
    
	@Override
	public boolean isExist(String login) throws ServiceException{
		
		if(!Validator.validateLogin(login)) {
			//log
			throw new ValidatorException("Wrong login!");
		}
		boolean exist;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 exist = userDAO.isExist(login);
		 } catch (DAOException e) {
			 throw new ServiceException("Error during checking isExist user in service", e);
       }
		return exist;
	}
	
}
