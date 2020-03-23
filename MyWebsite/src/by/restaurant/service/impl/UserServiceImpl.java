package by.restaurant.service.impl;

import java.util.ArrayList;

import by.restaurant.bean.User;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
//валидация
public class UserServiceImpl implements UserService {
	
	@Override
	public User getUser(String login, String password) throws ServiceException {
		
		//валидация
		User user;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 user = userDAO.getUser(login, password);
		 } catch (DAOException e) {
			 throw new ServiceException("Error in getUserById().", e);
       }
		 return user;
	}

	@Override
	public boolean addUser(User user) throws ServiceException {

		//валидация
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
			 throw new ServiceException("Error in getUserById().", e);
       }
		 return added;
	}

	@Override
	public ArrayList<User> getAllUsers() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
    
	@Override
	public boolean isExist(String login) throws ServiceException{
		//valid
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
	
	
	
	
	
	
	
	
//	private DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
//    private UserDAO userDao = daoFactory.getUserDao();
//
//    public User getUserByLoginPassword(String userLogin, String userPassword) throws ServiceException {
//
//        User userByLogin;
//        try {
//            userByLogin = userDao.getUserByLoginPassword(userLogin, userPassword);
//        } catch (DAOException e) {
//            throw new ServiceException("Error in getUserByLoginPassword.", e);
//        }
//        return userByLogin;
//    }
//
//    public boolean addUser(User user) throws ServiceException {
//        boolean isSaved;
//        try {
//            isSaved = userDao.save(user);
//        } catch (DAOException e) {
//            throw new ServiceException("Error in save user.", e);
//        }
//        return isSaved;
//    }
//
//    public User getUserInfoToAccount(int id) throws ServiceException {
//        User userFromDB;
//        try {
//            userFromDB = userDao.getUserById(id);
//        } catch (DAOException e) {
//            throw new ServiceException("Error in getUserById().", e);
//        }
//        return userFromDB;
//    }
}
