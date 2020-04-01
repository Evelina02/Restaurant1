package by.restaurant.service;

import java.util.ArrayList;

import by.restaurant.bean.User;

public interface UserService {

	User getUser(String login, String password) throws ServiceException;
		
	boolean addUser(User user) throws ServiceException;
	
	boolean isExist(String login) throws ServiceException;
	
	ArrayList<User> getAllActiveUsers() throws ServiceException;
}
