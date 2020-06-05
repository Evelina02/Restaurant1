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
import by.restaurant.dao.DAOException;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.XMLService;
import by.restaurant.service.factory.ServiceFactory;

public class StaxParser implements Command {

	private static final String ADD_DISH_ERROR = "add_dish_error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();
		
		try {
			List<Dish> dishes = XMLService.parse(request.getParameter("fileName"));
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

			for(Dish dish: dishes) {

				boolean added = dishService.addDish(dish);

				if (!added) {
					request.setAttribute(RequestParameterName.ADD_DISH_ERROR_MESSAGE, resourceBundle.getString(ADD_DISH_ERROR));
					RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ADMIN_MENU_PAGE);
					dispatcher.forward(request, response);
				}
			}
			session.setAttribute(SessionAttributeName.COMMAND, "admin_menu");
			
			response.sendRedirect(request.getContextPath() + "/Controller?command=admin_menu&message=dish_added");
			
		} catch (ServiceException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);	
		} catch (DAOException e) {
		RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
		dispatcher.forward(request, response);		
		}
}

}
