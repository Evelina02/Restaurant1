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

		final String LOGIN = (String) session.getAttribute(SessionAttributeName.LOGIN);

		request.setAttribute(RequestParameterName.GOODBYE_MESSAGE, resourceBundle.getString("message.goodbye"));
		request.setAttribute(RequestParameterName.LOGIN, LOGIN);

        session.removeAttribute(SessionAttributeName.ID_USER);
		session.removeAttribute(SessionAttributeName.LOGIN);
		session.removeAttribute(SessionAttributeName.ROLE);
		session.removeAttribute(SessionAttributeName.BASKET);
		session.removeAttribute(SessionAttributeName.COMMAND);
		//response.sendRedirect(request.getContextPath() + "/Controller?command=all_user_orders&message=order_deleted");

		RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.WELCOME_PAGE);
		dispatcher.forward(request, response);//можно redirect
	}
}
