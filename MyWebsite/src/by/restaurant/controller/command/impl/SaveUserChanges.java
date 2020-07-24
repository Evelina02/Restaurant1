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
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class SaveUserChanges implements Command {
	
	private static final Logger logger = LogManager.getLogger(SaveUserChanges.class);

	private static final String SAVE_CHANGES_ERROR = "save_changes_error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String login = request.getParameter(RequestParameterName.LOGIN);
		String email = request.getParameter(RequestParameterName.EMAIL);
		String address = request.getParameter(RequestParameterName.ADDRESS);

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);

			boolean updated = userService.updateUser(idUser, login, email, address);

			if (!updated) {
				request.setAttribute(RequestParameterName.SAVE_USER_CHANGES_ERROR_MESSAGE,
						resourceBundle.getString(SAVE_CHANGES_ERROR));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.PROFILE_PAGE);
				dispatcher.forward(request, response);
			}

			response.sendRedirect(request.getContextPath() + "/Controller?command=profile&message=changes_saved");

		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error during saving user changes", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
