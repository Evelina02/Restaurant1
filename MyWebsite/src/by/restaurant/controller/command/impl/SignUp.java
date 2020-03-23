package by.restaurant.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.User;
import by.restaurant.bean.util.Role;
import by.restaurant.controller.JspPageName;
import by.restaurant.controller.RequestParameterName;
import by.restaurant.controller.SessionAttributeName;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class SignUp implements Command {

	private static final String LOGIN_EXISTS = "User with such login already exists!";
	private static final String REGISTER_SUCCESS_MESSAGE = "Thank you for registration!";
	private static final String REGISTER_ERROR_MESSAGE = "Error during registration!";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		
		String page = null;
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password1");
		String email = request.getParameter("email");
		String address = request.getParameter("address");

		try {
			
			User user = new User(login, password, Role.CLIENT, email);
			
			if(address != null) {
				user.setAddress(address);
			}
			
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			if(userService.isExist(login)) {
				request.setAttribute(RequestParameterName.LOGIN_EXISTS, LOGIN_EXISTS);
				page = JspPageName.SIGN_UP_PAGE;
			}else {
				boolean added = userService.addUser(user);
				
				if(!added) {
					request.setAttribute(RequestParameterName.REGISTER_ERROR_MESSAGE, REGISTER_ERROR_MESSAGE);
					page = JspPageName.SIGN_IN_PAGE;
				}else {
//					user.setSignedIn(true);
					session.setAttribute(SessionAttributeName.LOGIN_ATTRIBUTE, user.getLogin());
	                session.setAttribute(SessionAttributeName.ROLE_ATTRIBUTE, user.getRole());
	                request.setAttribute(RequestParameterName.REGISTER_SUCCESS_MESSAGE, REGISTER_SUCCESS_MESSAGE);
					//request.setAttribute(RequestParameterName.MESSAGE, user.getLogin());
					page = JspPageName.WELCOME_PAGE;
				}
			}
	
		} catch (ServiceException e) {
			throw new CommandException("Error during signing up(getting service))", e);
		}
		
		return page;
	}

}
