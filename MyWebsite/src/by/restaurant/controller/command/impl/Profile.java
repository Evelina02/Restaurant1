package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.User;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class Profile implements Command {
	
	private static final Logger logger = LogManager.getLogger(Profile.class);

	private static final String CHANGES_SAVED = "changes_saved";
	private static final String PASSWORD_CHANGED = "password_changed";
	private static final String WRONG_OLD_PASSWORD = "wrong_old_password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String message = request.getParameter(RequestParameterName.MESSAGE);
		if (message != null && message.equals(CHANGES_SAVED)) {
			request.setAttribute(RequestParameterName.CHANGES_SAVED, resourceBundle.getString(CHANGES_SAVED));
		}
		
		if (message != null && message.equals(PASSWORD_CHANGED)) {
			request.setAttribute(RequestParameterName.CHANGE_PASSWORD_SUCCESS_MESSAGE, resourceBundle.getString(PASSWORD_CHANGED));
		}
		if (message != null && message.equals(WRONG_OLD_PASSWORD)) {
			request.setAttribute(RequestParameterName.CHANGE_PASSWORD_ERROR_MESSAGE, resourceBundle.getString(WRONG_OLD_PASSWORD));
		}

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);

			User user = userService.getUserById(idUser);
			request.setAttribute(RequestParameterName.USER, user);

			session.setAttribute(SessionAttributeName.COMMAND, "profile");

			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.PROFILE_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during opening the profile page", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
