package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Basket;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class DeleteDishFromBasket implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();
		int dishId = Integer.parseInt(request.getParameter(RequestParameterName.DISH_ID));

		Basket basket = (Basket)session.getAttribute(SessionAttributeName.BASKET);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		DishService dishService = serviceFactory.getDishService();
		basket = dishService.deleteDishFromBasket(basket, dishId);
		
		request.setAttribute(RequestParameterName.DISH_DELETED, resourceBundle.getString("dish_added"));
		session.setAttribute(SessionAttributeName.BASKET, basket);
        session.setAttribute("command", "show_basket");
        
    	response.sendRedirect(request.getContextPath() + "/Controller?command=show_basket&message=dish_deleted");
	}

}
