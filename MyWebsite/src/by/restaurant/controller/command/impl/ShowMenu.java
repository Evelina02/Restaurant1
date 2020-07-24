package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.Role;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class ShowMenu implements Command {
	
	private static final Logger logger = LogManager.getLogger(ShowMenu.class);

	private static List<Dish> snacks;
    private static List<Dish> hotDishes;
    private static List<Dish> salads;
    private static List<Dish> pizza;
    private static List<Dish> desserts;
    private static List<Dish> drinks;
    
    private static final String DISH_ADDED_MESSAGE = "dishAddedMessage";
    private static final String NOTHING_FOUND_MESSAGE = "nothing_found";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession(); 

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

			snacks = dishService.findSnacks();
		    hotDishes = dishService.findHotDishes();
		    salads = dishService.findSalads();
		    pizza = dishService.findPizza();
		    desserts = dishService.findDesserts();
		    drinks = dishService.findDrinks();

		    String message = request.getParameter(RequestParameterName.MESSAGE);
		    if(message!=null && message.equals(NOTHING_FOUND_MESSAGE)){
			    request.setAttribute(RequestParameterName.NOTHING_FOUND_MESSAGE, resourceBundle.getString(NOTHING_FOUND_MESSAGE));
		    }
		    if(message!=null && message.equals(RequestParameterName.DISH_ADDED)){
	    		request.setAttribute(RequestParameterName.DISH_ADDED, resourceBundle.getString("dish_added"));
		    }

		    request.setAttribute(RequestParameterName.SNACKS, snacks);
		    request.setAttribute(RequestParameterName.HOT_DISHES, hotDishes);
		    request.setAttribute(RequestParameterName.SALADS, salads);
		    request.setAttribute(RequestParameterName.PIZZA, pizza);
		    request.setAttribute(RequestParameterName.DESSERTS, desserts);
		    request.setAttribute(RequestParameterName.DRINKS, drinks);

            session.setAttribute(SessionAttributeName.COMMAND, "show_menu");

            RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.MENU_PAGE);
    		dispatcher.forward(request, response);
    		
	    } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during opening the menu page", e);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
	    }

	}

	 
		public static List<Dish> getSnacks() {
			return snacks;
		}

		public static void setSnacks(List<Dish> snacks) {
			ShowMenu.snacks = snacks;
		}

		public static List<Dish> getHotDishes() {
			return hotDishes;
		}

		public static void setHotDishes(List<Dish> hotDishes) {
			ShowMenu.hotDishes = hotDishes;
		}



		public static List<Dish> getSalads() {
			return salads;
		}



		public static void setSalads(List<Dish> salads) {
			ShowMenu.salads = salads;
		}



		public static List<Dish> getPizza() {
			return pizza;
		}



		public static void setPizza(List<Dish> pizza) {
			ShowMenu.pizza = pizza;
		}



		public static List<Dish> getDesserts() {
			return desserts;
		}



		public static void setDesserts(List<Dish> desserts) {
			ShowMenu.desserts = desserts;
		}



		public static List<Dish> getDrinks() {
			return drinks;
		}



		public static void setDrinks(List<Dish> drinks) {
			ShowMenu.drinks = drinks;
		}
}

