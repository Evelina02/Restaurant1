package by.restaurant.dao;

import java.util.ArrayList;

import by.restaurant.bean.User;

public interface UserDAO {

	int addUser(User user)throws DAOException;
	User getUser(String login, String password) throws DAOException;
	boolean isExist(String login) throws DAOException;
	ArrayList<User> findActiveUsers() throws DAOException;
}
