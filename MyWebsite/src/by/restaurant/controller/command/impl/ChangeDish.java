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
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class ChangeDish implements Command {

	private static final String CHANGE_DISH_ERROR = "change_dish_error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String picture = request.getParameter(RequestParameterName.PICTURE);
		Double price = Double.parseDouble(request.getParameter(RequestParameterName.PRICE));
		String amount = request.getParameter(RequestParameterName.AMOUNT);

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();
			int idDish = Integer.parseInt(request.getParameter(RequestParameterName.DISH_ID));

			boolean updated = dishService.updateDish(idDish, picture, price, amount);
			
			if(!updated) {
				request.setAttribute(RequestParameterName.CHANGE_DISH_ERROR_MESSAGE, resourceBundle.getString(CHANGE_DISH_ERROR));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ADMIN_MENU_PAGE);
				dispatcher.forward(request, response);
			}
						
			response.sendRedirect(request.getContextPath() + "/Controller?command=admin_menu&message=dish_changed");

        } catch (ServiceException e) {
            //log
        	RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
        }
	}

}
