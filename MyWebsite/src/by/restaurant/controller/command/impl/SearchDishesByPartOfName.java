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
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class SearchDishesByPartOfName implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String partOfName = request.getParameter("partOfName");
		List<Dish> dishes = null;

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

			dishes = dishService.searchDishByPartOfName(partOfName);

			if(dishes == null) {
				response.sendRedirect(request.getContextPath() + "/Controller?command=show_menu&message=nothing_found");

			}
			else {
			request.setAttribute(RequestParameterName.ALL_DISHES, dishes);
			session.setAttribute(SessionAttributeName.COMMAND, "search_dishes_by_part_of_name");
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.MENU_PAGE);
			dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

	}
}
