package by.restaurant.controller.command.impl;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import by.restaurant.controller.JspPageName;
import by.restaurant.controller.RequestParameterName;
import by.restaurant.controller.Router;
import by.restaurant.controller.SessionAttributeName;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;


public class SignOut implements Command {

	
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		Router router = new Router();
		String page = JspPageName.ERROR_PAGE;
		HttpSession session = request.getSession();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");

		final String GOODBYE = resourceBundle.getString("message.goodbye") + session.getAttribute(SessionAttributeName.LOGIN) + "!";

		request.setAttribute(RequestParameterName.GOODBYE_MESSAGE, GOODBYE);
		page = JspPageName.WELCOME_PAGE;
		router.setPagePath(page);
		
        session.removeAttribute(SessionAttributeName.ID_USER);
		session.removeAttribute(SessionAttributeName.LOGIN);
		session.removeAttribute(SessionAttributeName.ROLE);
		
		
		return router;
	}
}
