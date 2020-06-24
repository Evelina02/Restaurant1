package by.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import by.restaurant.bean.Review;
import by.restaurant.bean.User;

public interface UserService {

	User getUser(String login, String password) throws ServiceException;
		
	boolean addUser(User user) throws ServiceException;
	
	boolean isExist(String login) throws ServiceException;
	
	List<User> getAllUsers() throws ServiceException;

	User getUserById(int idUser) throws ServiceException;

	boolean updateUser(int idUser, String login, String email, String address) throws ServiceException;

	List<Review> showAllReviews()  throws ServiceException;

	boolean changePassword(int idUser, String oldPassword, String newPassword)  throws ServiceException;

	boolean banUser(int idUser) throws ServiceException;

	boolean unbanUser(int idUser)  throws ServiceException;

	boolean isBanned(String login) throws ServiceException;

	boolean addReview(Review review, int idUser) throws ServiceException;

	boolean deleteReview(int reviewId) throws ServiceException;

	boolean resetPassword(String login, String newPassword)  throws ServiceException;

	String getEmailByLogin(String login)  throws ServiceException;

	String getEmailById(int idUser) throws ServiceException;

	int getIdByLogin(String login) throws ServiceException;

	double getLoyaltyPointsById(int idUser) throws ServiceException;

	boolean updateLoyaltyPoints(int idUser, double loyaltyPoints) throws ServiceException;
}
