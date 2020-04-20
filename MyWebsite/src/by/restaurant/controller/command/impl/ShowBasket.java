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

public class ShowBasket implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String message = request.getParameter(RequestParameterName.MESSAGE);
		if (message != null && message.equals(RequestParameterName.DISH_DELETED)) {
			request.setAttribute(RequestParameterName.DISH_DELETED, resourceBundle.getString("dish_deleted"));
		}
		
		if (message != null && message.equals(RequestParameterName.BASKET_CLEARED)) {
			request.setAttribute(RequestParameterName.DISH_DELETED, resourceBundle.getString("basket_cleared"));
		}
		session.setAttribute("command", "show_basket");

		RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.BASKET_PAGE);
		dispatcher.forward(request, response);

	}

}
