package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.User;
import by.restaurant.bean.constant.Role;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class SignUp implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
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
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_UP_PAGE);
				dispatcher.forward(request, response);
				
			}
			boolean added = userService.addUser(user);
			
			if(!added) {
				request.setAttribute(RequestParameterName.REGISTER_ERROR_MESSAGE, resourceBundle.getString("register.error"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
				dispatcher.forward(request, response);
			}
			
			HttpSession session = request.getSession();
            session.setAttribute(SessionAttributeName.ID_USER, user.getId());
			session.setAttribute(SessionAttributeName.LOGIN, user.getLogin());
            session.setAttribute(SessionAttributeName.ROLE, user.getRole());
            request.setAttribute(RequestParameterName.REGISTER_SUCCESS_MESSAGE, resourceBundle.getString("register.success_message"));

            
            String commandName = (String)session.getAttribute("command");
			
            if(commandName == null) {
				response.sendRedirect(JspPageName.WELCOME_PAGE);
	        }else {
	        	response.sendRedirect(request.getContextPath() + "/Controller?command=" + commandName);
	        }

	    } catch (ServiceException e) {
	        //log
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
	    }
			
	}
}
