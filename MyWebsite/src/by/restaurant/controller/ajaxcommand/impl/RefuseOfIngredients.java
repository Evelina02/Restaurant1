package by.restaurant.controller.ajaxcommand.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.controller.ajaxcommand.AjaxCommand;
import by.restaurant.controller.command.impl.AddDish;
import by.restaurant.controller.command.impl.ShowMenu;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class RefuseOfIngredients implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String responseToJsp = null;

		//ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();
		int dishId = Integer.parseInt(request.getParameter(RequestParameterName.DISH_ID));
		String selectedIngredients = request.getParameter("selected");
		Set<String> refusedIngredients = null;
		
		if(!selectedIngredients.equals("")) {
			refusedIngredients = parseIngredients(selectedIngredients);
		}
		
		Basket basket = (Basket) session.getAttribute(SessionAttributeName.BASKET);


		for(Dish dish : basket.getDishes()) {

			if(dish.getId() == dishId) {
				
				if(selectedIngredients.equals("")) {
					dish.setRefusalOfIngredients(null);
				}else {
					
					dish.setRefusalOfIngredients(refusedIngredients);
				}

		
				responseToJsp = "{\"status\":\"yes\"}";
				break;
				}
		}	
		
		session.setAttribute(SessionAttributeName.COMMAND, "show_basket");
		return responseToJsp;
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
	
}
