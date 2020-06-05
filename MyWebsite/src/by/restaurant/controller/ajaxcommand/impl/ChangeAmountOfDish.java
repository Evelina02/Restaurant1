package by.restaurant.controller.ajaxcommand.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.controller.ajaxcommand.AjaxCommand;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class ChangeAmountOfDish implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String responseToJsp = null;
		int count = Integer.parseInt(request.getParameter("count"));
		int id = Integer.parseInt(request.getParameter("id"));

		HttpSession session = request.getSession();
		Basket basket = (Basket) session.getAttribute(SessionAttributeName.BASKET);
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

			basket.getCountDishById().put(id, count);
			dishService.countTotalPrice(basket);
			session.setAttribute(SessionAttributeName.BASKET, basket);

			Dish dish = null;
			for (Dish d : basket.getDishes()) {
				if (d.getId() == id) {
					dish = d;
				}
			}
			Double dishPrice = basket.getCountDishById().get(id) * dish.getPrice();
			Double totalPrice = basket.getTotalPrice();
			responseToJsp = "{\"dishPrice\":\"" + dishPrice + "\",\"totalPrice\":\"" + totalPrice + "\"}";
		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
		return responseToJsp;
	}

}
