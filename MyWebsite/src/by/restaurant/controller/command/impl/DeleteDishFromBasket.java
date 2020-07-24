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

import by.restaurant.bean.Basket;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class DeleteDishFromBasket implements Command {
	
	private static final Logger logger = LogManager.getLogger(DeleteDishFromBasket.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int dishId = Integer.parseInt(request.getParameter(RequestParameterName.DISH_ID));

		Basket basket = (Basket) session.getAttribute(SessionAttributeName.BASKET);
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();
			dishService.deleteDishFromBasket(basket, dishId);

			if (basket.getDishes().size() == 0) {
				response.sendRedirect(request.getContextPath() + "/Controller?command=clear_basket");
			} else {
				dishService.countTotalPrice(basket);

				session.setAttribute(SessionAttributeName.BASKET, basket);
				session.setAttribute(SessionAttributeName.COMMAND, "show_basket");

				response.sendRedirect(
						request.getContextPath() + "/Controller?command=show_basket&message=dish_deleted");
			}
		} catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during deleting dish from basket", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}
}
