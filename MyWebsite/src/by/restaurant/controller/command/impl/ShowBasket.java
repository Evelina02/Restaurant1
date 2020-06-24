package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

//		String message = request.getParameter(RequestParameterName.MESSAGE);
//		if (message != null && message.equals(RequestParameterName.DISH_DELETED)) {
//			request.setAttribute(RequestParameterName.DISH_DELETED, resourceBundle.getString("dish_deleted"));
//		}
//		
//		if (message != null && message.equals(RequestParameterName.BASKET_CLEARED)) {
//			request.setAttribute(RequestParameterName.BASKET_CLEARED, resourceBundle.getString("basket_cleared"));
//		}
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
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
