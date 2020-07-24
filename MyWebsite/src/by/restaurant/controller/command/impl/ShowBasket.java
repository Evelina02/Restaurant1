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

import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class ShowBasket implements Command {

	private static final Logger logger = LogManager.getLogger(ShowBasket.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

        logger.log(Level.ERROR, "Error during opening the basket", "!!!!!!");

		try {
// баллы		
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);
			double loyaltyPoints = userService.getLoyaltyPointsById(idUser);

			request.setAttribute(RequestParameterName.USER_LOYALTY_POINTS, loyaltyPoints);

			session.setAttribute(SessionAttributeName.COMMAND, "show_basket");

			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.BASKET_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during opening the basket page", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
