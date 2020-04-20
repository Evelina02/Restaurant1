package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Basket;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.factory.ServiceFactory;

public class ClearBasket  implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		Basket basket = (Basket)session.getAttribute(SessionAttributeName.BASKET);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		DishService dishService = serviceFactory.getDishService();
		dishService.clearBasket(basket);
		
		request.setAttribute(RequestParameterName.DISH_DELETED, resourceBundle.getString("dish_added"));
		session.removeAttribute(SessionAttributeName.BASKET);
        session.setAttribute("command", "show_basket");
        
    	response.sendRedirect(request.getContextPath() + "/Controller?command=show_basket&message=basket_cleared");
	

	}
}
