package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Dish;
import by.restaurant.bean.Order;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class AdminMenu implements Command {

	private static final String DISH_CHANGED = "dish_changed";
	private static final String DISH_ADDED = "dish_added";// добавить в localiz

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		List<Dish> allDishes = null;
		
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

			allDishes = dishService.getAllDishes();

		    request.setAttribute(RequestParameterName.ALL_DISHES, allDishes);

			String message = request.getParameter(RequestParameterName.MESSAGE);
			if (message != null && message.equals(DISH_CHANGED)) {
				request.setAttribute(RequestParameterName.DISH_CHANGED_SUCCESS, resourceBundle.getString(DISH_CHANGED));
			}
			if (message != null && message.equals(DISH_ADDED)) {
				request.setAttribute(RequestParameterName.ADD_DISH_SUCCESS_MESSAGE, resourceBundle.getString(DISH_ADDED));
			}

			session.setAttribute(SessionAttributeName.COMMAND, "admin_menu");

			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ADMIN_MENU_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
