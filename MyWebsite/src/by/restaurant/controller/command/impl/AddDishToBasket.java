package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.Role;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class AddDishToBasket implements Command {

	private static final String DISH_CATEGORY = "dishCategory";
	private static final String COUNT = "count";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		if (session.getAttribute(SessionAttributeName.ID_USER) == null) {
			request.setAttribute(RequestParameterName.SIGN_IN_REQUIRED, resourceBundle.getString("sign_in_required"));
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
			dispatcher.forward(request, response);
		}

		int dishId = Integer.parseInt(request.getParameter(RequestParameterName.DISH_ID));
		int countOfDish = Integer.parseInt(request.getParameter(COUNT));

		String dishCategory = request.getParameter(DISH_CATEGORY);
		Dish dish = null;


		switch (dishCategory) {

		case RequestParameterName.SNACKS:
			dish = findSnackById(dishId);
			break;
		case RequestParameterName.HOT_DISHES:
			dish = findHotDishById(dishId);
			break;
		case RequestParameterName.SALADS:
			dish = findSaladById(dishId);
			break;
		case RequestParameterName.PIZZA:
			dish = findPizzaById(dishId);
			break;
		case RequestParameterName.DESSERTS:
			dish = findDessertById(dishId);
			break;
		case RequestParameterName.DRINKS:
			dish = findDrinkById(dishId);
			break;

		default:

			dish = findSnackById(dishId);
			if(dish == null) {
				dish = findHotDishById(dishId);
			}
			if(dish == null) {
			dish = findSaladById(dishId);
			}
			if(dish == null) {
			dish = findPizzaById(dishId);
			}
			if(dish == null) {
			dish = findDessertById(dishId);
			}
			if(dish == null) {
			dish = findDrinkById(dishId);
			}

		}
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

			Basket basket = (Basket) session.getAttribute(SessionAttributeName.BASKET);

			if (basket == null) {
				basket = new Basket();
				dishService.putDishToBasket(basket, dish, countOfDish);
				dishService.countTotalPrice(basket);
			}

			else {
				if (basket.getCountDishById().containsKey(dish.getId())) {
					int newCountOfDish = basket.getCountDishById().get(dish.getId()) + countOfDish;
					basket.getCountDishById().put(dish.getId(), newCountOfDish);
					dishService.countTotalPrice(basket);
				} else {
					dishService.putDishToBasket(basket, dish, countOfDish);
					dishService.countTotalPrice(basket);
				}
			}

			request.setAttribute(RequestParameterName.DISH_ADDED, resourceBundle.getString("dish_added"));
			session.setAttribute(SessionAttributeName.BASKET, basket);
			session.setAttribute(SessionAttributeName.COMMAND, "show_menu");

			response.sendRedirect(request.getContextPath() + "/Controller?command=show_menu&message=dish_added");

		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

	private Dish findDrinkById(int dishId) {

		List<Dish> drinks = ShowMenu.getDrinks();

		for (Dish dish : drinks) {
			if (dishId == dish.getId()) {
				return dish;
			}
		}
		return null;
	}

	private Dish findDessertById(int dishId) {

		List<Dish> desserts = ShowMenu.getDesserts();

		for (Dish dish : desserts) {
			if (dishId == dish.getId()) {
				return dish;
			}
		}
		return null;
	}

	private Dish findPizzaById(int dishId) {

		List<Dish> pizza = ShowMenu.getPizza();

		for (Dish dish : pizza) {
			if (dishId == dish.getId()) {
				return dish;
			}
		}
		return null;
	}

	private Dish findHotDishById(int dishId) {

		List<Dish> hotDishes = ShowMenu.getHotDishes();

		for (Dish dish : hotDishes) {
			if (dishId == dish.getId()) {
				return dish;
			}
		}
		return null;
	}

	private Dish findSnackById(int dishId) {

		List<Dish> snacks = ShowMenu.getSnacks();

		for (Dish dish : snacks) {
			if (dishId == dish.getId()) {
				return dish;
			}
		}
		return null;
	}

	private Dish findSaladById(int dishId) {

		List<Dish> salads = ShowMenu.getSalads();

		for (Dish dish : salads) {
			if (dishId == dish.getId()) {
				return dish;
			}
		}
		return null;

	}
}
