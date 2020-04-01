package by.restaurant.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Dish;
import by.restaurant.bean.User;
import by.restaurant.bean.util.Role;
import by.restaurant.controller.Router;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class ShowMenu implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		Router router = new Router();
		String page = JspPageName.ERROR_PAGE;
		HttpSession session = request.getSession(); 

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();

		    List<Dish> snacks = dishService.findSnacks();
		    List<Dish> hotDishes = dishService.findHotDishes();
		    List<Dish> salads = dishService.findSalads();
		    List<Dish> pizza = dishService.findPizza();
		    List<Dish> desserts = dishService.findDesserts();
		    List<Dish> drinks = dishService.findDrinks();

            request.setAttribute(RequestParameterName.SNACKS, snacks);
            request.setAttribute(RequestParameterName.HOT_DISHES, hotDishes);
            request.setAttribute(RequestParameterName.SALADS, salads);
            request.setAttribute(RequestParameterName.PIZZA, pizza);
            request.setAttribute(RequestParameterName.DESSERTS, desserts);
            request.setAttribute(RequestParameterName.DRINKS, drinks);

            session.setAttribute("command", "show_menu");


//            Role role = Role.valueOf(session.getAttribute(SessionAttributeName.ROLE_ATTRIBUTE).toString());
//            if (role == Role.ADMIN) {
//                page = JspPageName.ADMIN_MENU_PAGE;
//            } else {
//                page = JspPageName.MENU_PAGE;
//            }
            
            page = JspPageName.MENU_PAGE;
            router.setPagePath(page);

		} catch (ServiceException e) {
			//log
			throw new CommandException("Error during finding all dishes (getting service)", e);
		}
		
		return router;
	}

}

