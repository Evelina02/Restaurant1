package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.bean.Order;
import by.restaurant.bean.constant.Category;
import by.restaurant.bean.constant.DeliveryType;
import by.restaurant.bean.constant.OrderState;
import by.restaurant.bean.constant.PaymentType;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class AddDish implements Command {

	private static final Logger logger = LogManager.getLogger(AddDish.class);
	private static final String ADD_DISH_ERROR = "add_dish_error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String name = request.getParameter(RequestParameterName.DISH_NAME);
		String picture = request.getParameter(RequestParameterName.DISH_PICTURE);
		String amount = replaceCommaWithDot(request.getParameter(RequestParameterName.DISH_AMOUNT));
		Category category = Category.valueOf(request.getParameter(RequestParameterName.DISH_CATEGORY));
		Double price = Double.parseDouble(replaceCommaWithDot(request.getParameter(RequestParameterName.DISH_PRICE)));
		String ingredients = request.getParameter(RequestParameterName.DISH_INGREDIENTS);
		Dish dish;
		
		if(ingredients == null || ingredients.isEmpty()){
			dish = new Dish(name, price, picture, category, amount);
		}
		else {
			Set<String> ingredientsList = parseIngredients(ingredients);
			dish = new Dish(name, price, picture, category, amount, ingredientsList);
			
		}

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

			boolean added = dishService.addDish(dish);

			if (!added) {
				request.setAttribute(RequestParameterName.ADD_DISH_ERROR_MESSAGE, resourceBundle.getString(ADD_DISH_ERROR));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ADMIN_MENU_PAGE);
				dispatcher.forward(request, response);
			}

			session.setAttribute(SessionAttributeName.COMMAND, "admin_menu");
		
			response.sendRedirect(request.getContextPath() + "/Controller?command=admin_menu&message=dish_added");

		} catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during additing a new dish in menu", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

	private Set<String> parseIngredients(String allIngredients) {

		Set<String> ingredients = new HashSet<>();
		String[] arrIngredients = allIngredients.split(",");
		for(String ingredient: arrIngredients) {
			ingredient = ingredient.trim();
			ingredient = firstUpperCase(ingredient);
			ingredients.add(ingredient);
		}
		return ingredients;
	}
	
	private String firstUpperCase(String word) {
		
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
	
	private String replaceCommaWithDot(String str) {
		
		return str.replace(',', '.');
	}
}
