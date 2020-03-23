package by.restaurant.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import by.restaurant.bean.User;
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

public class SignIn implements Command {

	private static final String WRONG_LOGIN = "There is not a user with such login!";
	private static final String WRONG_PASSWORD = "Wrong password!";
	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		
		String page = null;
		HttpSession session = request.getSession(); 
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			if(userService.isExist(login)) {
				User user = userService.getUser(login, password);
				if(user == null) {
					request.setAttribute(RequestParameterName.WRONG_PASSWORD, WRONG_PASSWORD);
					page = JspPageName.SIGN_IN_PAGE;
				}else {
					//user.setSignedIn(true);
	                session.setAttribute(SessionAttributeName.LOGIN_ATTRIBUTE, user.getLogin());
	                session.setAttribute(SessionAttributeName.ROLE_ATTRIBUTE, user.getRole().name());
					//request.setAttribute(RequestParameterName.MESSAGE, user.getLogin());
					page = JspPageName.WELCOME_PAGE;		
				}
			}else {
				request.setAttribute(RequestParameterName.NO_SUCH_LOGIN, WRONG_LOGIN);
				page = JspPageName.SIGN_IN_PAGE;			
			}
		} catch (ServiceException e) {
			throw new CommandException("Error during signing in (getting service)", e);
		}
		
		return page;
		
	}

}
