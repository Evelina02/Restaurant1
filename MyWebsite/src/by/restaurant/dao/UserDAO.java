package by.restaurant.dao;
import java.util.List;

import by.restaurant.bean.Review;
import by.restaurant.bean.User;

public interface UserDAO {

	int addUser(User user)throws DAOException;
	User getUser(String login, String password) throws DAOException;
	boolean isExist(String login) throws DAOException;
	List<User> findAllUsers() throws DAOException;
	public User getUserById(int id) throws DAOException;
	int updateUser(int idUser, String login, String email, String address) throws DAOException;
	List<Review> showAllReviews()  throws DAOException;
	int changePassword(int idUser, String oldPassword, String newPassword)  throws DAOException;
	int banUser(int idUser) throws DAOException;
	int unbanUser(int idUser)  throws DAOException;
	boolean isBanned(String login) throws DAOException;
	int addReview(Review review, int idUser) throws DAOException;
	int deleteReview(int reviewId)  throws DAOException;
	int resetPassword(String login, String newPassword) throws DAOException;
	String getEmailByLogin(String login) throws DAOException;
	String getEmailById(int idUser)  throws DAOException;
	int getIdByLogin(String login) throws DAOException;
	double getLoyaltyPointsById(int idUser) throws DAOException;
	int updateUserLoyaltyPoints(int idUser, double loyaltyPoints) throws DAOException;
	
}
