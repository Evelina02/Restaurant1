package by.restaurant.service.impl;

import java.util.List;

import by.restaurant.bean.Dish;
import by.restaurant.bean.Order;
import by.restaurant.bean.User;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.OrderDAO;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.impl.validator.Validator;
import by.restaurant.service.impl.validator.ValidatorException;

public class OrderServiceImpl implements OrderService {

	@Override
	public boolean addOrder(Order order, int idUser) throws ServiceException {

		if(!Validator.validateIsNull(order)) {
			//log
			throw new ValidatorException("Order is null!");
		}
		
		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		
		boolean status;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();
			UserDAO userDAO = daoFactory.getUserDAO();

			status = orderDAO.addOrder(order, idUser);

			User user = userDAO.getUserById(idUser);
			user.setOrders(orderDAO.selectAllOrdersOfUser(idUser));


		} catch (DAOException e) {
			throw new ServiceException("Error during additing order (in service)", e);
		}
		return status;

	}

	@Override
	public List<Order> allOrdersOfUser(int idUser) throws ServiceException{
		
		if(!Validator.validatePositiveNumber(idUser)) {
			//log
			throw new ValidatorException("Id of user is a negative number!");
		}
		
		List<Order> orders;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();

			orders = orderDAO.selectAllOrdersOfUser(idUser);

		} catch (DAOException e) {
			throw new ServiceException("Error during getting all orders of user (in service)", e);
		}
		
		return orders;

	}

	@Override
	public boolean cancelOrder(int orderId) throws ServiceException {

		if(!Validator.validatePositiveNumber(orderId)) {
			//log
			throw new ValidatorException("Id of order is a negative number!");
		}
		
		boolean canceled;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();

			int status = orderDAO.cancelOrder(orderId);
			if(status == 1) {
				canceled = true;
			 }else {
				 canceled = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during getting all orders of user (in service)", e);
		}
		return canceled;
	}

	@Override
	public List<Order> allOrders() throws ServiceException {

		List<Order> orders;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();

			orders = orderDAO.selectAllOrders();

		} catch (DAOException e) {
			throw new ServiceException("Error during getting all orders of user (in service)", e);
		}
		
		return orders;

	}
	
	@Override
	public boolean closeOrder(int orderId) throws ServiceException {

		if(!Validator.validatePositiveNumber(orderId)) {
			//log
			throw new ValidatorException("Id of order is a negative number!");
		}
		
		boolean closed;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();

			int status = orderDAO.closeOrder(orderId);
			if(status == 1) {
				closed = true;
			 }else {
				 closed = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during closing order (in service)", e);
		}
		return closed;
	}

	@Override
	public boolean doOrder(int orderId) throws ServiceException {

		if(!Validator.validatePositiveNumber(orderId)) {
			//log
			throw new ValidatorException("Id of order is a negative number!");
		}
		
		boolean done;
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();
			OrderDAO orderDAO = daoFactory.getOrderDAO();

			int status = orderDAO.doOrder(orderId);
			if(status == 1) {
				done = true;
			 }else {
				 done = false;
			 }
		} catch (DAOException e) {
			throw new ServiceException("Error during doing order (in service)", e);
		}
		return done;
	}

	@Override
	public int getIdOrderByOrderTime(String orderTime) throws ServiceException {

		if(!Validator.validateString(orderTime)) {
			//log
			throw new ValidatorException("Order time is null!");
		}
		
		int idOrder;
		try {
			 DAOFactory daoFactory = DAOFactory.getInstance();
			 OrderDAO orderDAO = daoFactory.getOrderDAO();
			 idOrder = orderDAO.getIdOrderByOrderTime(orderTime);
		} catch (DAOException e) {
			 throw new ServiceException("Error during getting id order (in service)", e);
       }
		 return idOrder;
	}
	
}
