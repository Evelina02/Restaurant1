package by.restaurant.controller.command.impl;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.User;
import by.restaurant.bean.util.Role;
import by.restaurant.controller.JspPageName;
import by.restaurant.controller.RequestParameterName;
import by.restaurant.controller.Router;
import by.restaurant.controller.SessionAttributeName;
import by.restaurant.controller.Router.RouteType;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class SignUp implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		Router router = new Router();
		String page = JspPageName.ERROR_PAGE;
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String email = request.getParameter(RequestParameterName.EMAIL);
		String address = request.getParameter(RequestParameterName.ADDRESS);
		try {
			
			User user = new User(login, password, Role.CLIENT, email);
			
			if(address != null) {
				user.setAddress(address);
			}
			
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			if(userService.isExist(login)) {
				request.setAttribute(RequestParameterName.LOGIN_EXISTS, resourceBundle.getString("message.login_exists"));
				page = JspPageName.SIGN_UP_PAGE;
            	router.setPagePath(page);

			}else {
				boolean added = userService.addUser(user);
				
				if(!added) {
					request.setAttribute(RequestParameterName.REGISTER_ERROR_MESSAGE, resourceBundle.getString("message.register.error"));
					page = JspPageName.SIGN_IN_PAGE;
                	router.setPagePath(page);

				}else {
	                session.setAttribute(SessionAttributeName.ID_USER, user.getId());
					session.setAttribute(SessionAttributeName.LOGIN, user.getLogin());
	                session.setAttribute(SessionAttributeName.ROLE, user.getRole());
	                request.setAttribute(RequestParameterName.REGISTER_SUCCESS_MESSAGE, resourceBundle.getString("message.register.success_message"));
					page = JspPageName.WELCOME_PAGE;
					String commandName = (String)session.getAttribute("command");

					if(commandName == null) {
	                	page = JspPageName.WELCOME_PAGE;
	                }else {
	                	page = request.getContextPath() + "/Controller?command=" + commandName;
	                }	
	                	router.setPagePath(page);
	                	router.setRouteType(RouteType.REDIRECT);;
					}
			}
	
		} catch (ServiceException e) {
			throw new CommandException("Error during signing up(getting service))", e);
		}
				
		return router;
		}

}
