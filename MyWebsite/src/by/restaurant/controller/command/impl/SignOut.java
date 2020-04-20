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
import by.restaurant.controller.constantname.SessionAttributeName;


public class SignOut implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");

		final String GOODBYE = resourceBundle.getString("message.goodbye") + session.getAttribute(SessionAttributeName.LOGIN) + "!";

		request.setAttribute(RequestParameterName.GOODBYE_MESSAGE, GOODBYE);

        session.removeAttribute(SessionAttributeName.ID_USER);
		session.removeAttribute(SessionAttributeName.LOGIN);
		session.removeAttribute(SessionAttributeName.ROLE);
		session.removeAttribute(SessionAttributeName.BASKET);
		RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.WELCOME_PAGE);
		dispatcher.forward(request, response);//можно redirect
	}
}
