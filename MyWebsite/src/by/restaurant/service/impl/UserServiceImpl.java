package by.restaurant.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.Review;
import by.restaurant.bean.User;
import by.restaurant.controller.listener.PoolListener;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.impl.validator.Validator;
import by.restaurant.service.impl.validator.ValidatorException;

public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(PoolListener.class);

	@Override
	public User getUser(String login, String password) throws ServiceException{
		
		if(!Validator.validateLogin(login)) {
			throw new ValidatorException("Login is not correct!");
		}
//		if(!Validator.validatePassword(password)) {
//			//log
//			throw new ValidatorException("Password is not correct!");
//		}
		
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
	public synchronized boolean addUser(User user) throws ServiceException {

		if(!Validator.validateIsNull(user)) {
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
	public List<User> getAllUsers() throws ServiceException {
		
		List<User> users;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 users = userDAO.findAllUsers(); 
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

	@Override
	public User getUserById(int idUser) throws ServiceException {

		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		
		User user = null;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			user = userDAO.getUserById(idUser);
		} catch (DAOException e) {
			throw new ServiceException("Error during checking isExist user in service", e);
		}
		return user;
	}

	@Override
	public boolean updateUser(int idUser, String login, String email, String address) throws ServiceException {
		
		if(!Validator.validateLogin(login) || !Validator.validateString(email) || 
				!Validator.validateString(address)){
			//log
			throw new ValidatorException("Information of user is not correct!");
		}
		
		boolean added;

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			int status = userDAO.updateUser(idUser, login, email, address);
			if(status == 1) {
				 added = true;
			 }else {
				 added = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during checking isExist user in service", e);
		}
		return added;
	}

	@Override
	public List<Review> showAllReviews() throws ServiceException {
		
		List<Review> reviews;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 reviews = userDAO.showAllReviews();
		 } catch (DAOException e) {
			 throw new ServiceException("Error during getting all active users (in service)", e);
       }
		 return reviews;
	}

	@Override
	public boolean changePassword(int idUser, String oldPassword, String newPassword) throws ServiceException {
		
//		if(!Validator.validatePassword(oldPassword) || !Validator.validatePassword(newPassword)){
//			//log
//			throw new ValidatorException("Password is not correct!");
//		}
		
		boolean changed;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 int status = userDAO.changePassword(idUser, oldPassword, newPassword);
			 if(status == 1) {
				 changed = true;
			 }else {
				 changed = false;
			 }
		 } catch (DAOException e) {
			 throw new ServiceException("Error during additing user (in service)", e);
      }
		 return changed;
	}

	@Override
	public boolean banUser(int idUser) throws ServiceException {
		
		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		
		boolean banned;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 int status = userDAO.banUser(idUser);
			 if(status == 1) {
				 banned = true;
			 }else {
				 banned = false;
			 }
		 } catch (DAOException e) {
			 throw new ServiceException("Error during additing user (in service)", e);
     }
		 return banned;
	}

	@Override
	public boolean unbanUser(int idUser) throws ServiceException {
		
		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		
		boolean unbanned;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 int status = userDAO.unbanUser(idUser);
			 if(status == 1) {
				 unbanned = true;
			 }else {
				 unbanned = false;
			 }
		 } catch (DAOException e) {
			 throw new ServiceException("Error during additing user (in service)", e);
    }
		 return unbanned;
	}

	@Override
	public boolean isBanned(String login) throws ServiceException {
		
		if (!Validator.validateLogin(login)) {
			// log
			throw new ValidatorException("Login is not correct!");
		}
		
		boolean banned;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 banned = userDAO.isBanned(login);
		 } catch (DAOException e) {
			 throw new ServiceException("Error during checking isExist user in service", e);
      }
		return banned;
	}

	@Override
	public boolean addReview(Review review, int idUser) throws ServiceException {
		
		if(!Validator.validateIsNull(review)) {
			//log
			throw new ValidatorException("Object is null!");
		}
		
		boolean added;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 int status = userDAO.addReview(review, idUser);
			 if(status == 1) {
				 added = true;
			 }else {
				 added = false;
			 }
		 } catch (DAOException e) {
			 throw new ServiceException("Error during additing review (in service)", e);
      }
		 return added;
	}

	@Override
	public boolean deleteReview(int reviewId) throws ServiceException {
	
		if(!Validator.validatePositiveNumber(reviewId)) {
			//log
			throw new ValidatorException("Id of review is a negative number!");
		}
		
		boolean deleted;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 int status = userDAO.deleteReview(reviewId);
			 if(status == 1) {
				 deleted = true;
			 }else {
				 deleted = false;
			 }
		 } catch (DAOException e) {
			 throw new ServiceException("Error during additing review (in service)", e);
     }
		 return deleted;
	}

	@Override
	public boolean resetPassword(String login, String newPassword) throws ServiceException {
		
		if (!Validator.validateLogin(login)) {
			// log
			throw new ValidatorException("Login is not correct!");
		}
//		if (!Validator.validatePassword(newPassword)) {
//			// log
//			throw new ValidatorException("Password is not correct!");
//		}
		
		boolean reseted;
		 try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 int status = userDAO.resetPassword(login, newPassword);
			 if(status == 1) {
				 reseted = true;
			 }else {
				 reseted = false;
			 }
		 } catch (DAOException e) {
			 throw new ServiceException("Error during additing review (in service)", e);
    }
		 return reseted;
	}

	@Override
	public String getEmailByLogin(String login) throws ServiceException {
		
		if (!Validator.validateLogin(login)) {
			// log
			throw new ValidatorException("Login is not correct!");
		}

		String email;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 email = userDAO.getEmailByLogin(login);
		} catch (DAOException e) {
			 throw new ServiceException("Error during getting email of user (in service)", e);
       }
		 return email;
	}

	@Override
	public String getEmailById(int idUser) throws ServiceException {
		
		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		
		String email;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 email = userDAO.getEmailById(idUser);
		} catch (DAOException e) {
			 throw new ServiceException("Error during getting email of user (in service)", e);
       }
		 return email;
	}

	@Override
	public int getIdByLogin(String login) throws ServiceException {
		
		if (!Validator.validateLogin(login)) {
			// log
			throw new ValidatorException("Login is not correct!");
		}

		int idUser;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 idUser = userDAO.getIdByLogin(login);
		} catch (DAOException e) {
			 throw new ServiceException("Error during getting email of user (in service)", e);
       }
		 return idUser;
	}

	
	@Override
	public double getLoyaltyPointsById(int idUser) throws ServiceException {
		
		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		
		double loyaltyPoints;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 UserDAO userDAO = daoFactory.getUserDAO();
			 loyaltyPoints = userDAO.getLoyaltyPointsById(idUser);
		} catch (DAOException e) {
			 throw new ServiceException("Error during getting loyalty points of user (in service)", e);
       }
		 return loyaltyPoints;
	}

	
	@Override
	public boolean updateLoyaltyPoints(int idUser, double loyaltyPoints) throws ServiceException {
		
		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		if(!Validator.validatePositiveNumber(loyaltyPoints)) {
			//log
			throw new ValidatorException("Loyalty points of user is a negative number!");
		}
		
		boolean updated;

		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			int status = userDAO.updateUserLoyaltyPoints(idUser, loyaltyPoints);
			if(status == 1) {
				updated = true;
			 }else {
				updated = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during updating loyalty points of user in service", e);
		}
		return updated;
	}
}
