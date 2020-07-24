package by.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class BanUser implements Command {
	
	private static final Logger logger = LogManager.getLogger(BanUser.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		int idUser = Integer.parseInt(request.getParameter(RequestParameterName.USER_ID));
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			if (!userService.banUser(idUser)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
				dispatcher.forward(request, response);
			}
			
			session.setAttribute(SessionAttributeName.COMMAND, "all_users");
			
			response.sendRedirect(request.getContextPath() + "/Controller?command=all_users&message=user_banned");

		} catch (ServiceException e) {
            logger.log(Level.ERROR, "Error: ban user", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
