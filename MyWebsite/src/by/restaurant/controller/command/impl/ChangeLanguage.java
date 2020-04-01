package by.restaurant.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.restaurant.controller.JspPageName;
import by.restaurant.controller.Router;
import by.restaurant.controller.Router.RouteType;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;

public class ChangeLanguage implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		Router router = new Router();
		String page = JspPageName.ERROR_PAGE;
		HttpSession session = request.getSession(true);

		session.setAttribute("local", request.getParameter("language"));

		String commandName = (String)session.getAttribute("command");
        
		if(commandName == null) {
        	page = JspPageName.WELCOME_PAGE;
        }else {
        	page = request.getContextPath() + "/Controller?command=" + commandName;
        }	
        router.setPagePath(page);
        router.setRouteType(RouteType.REDIRECT);
	
        return router;
	}
}

