package by.restaurant.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.restaurant.controller.Router;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.constantname.JspPageName;

public class NoSuchCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {

		Router router = new Router();
		router.setPagePath(JspPageName.ERROR_PAGE);
		return router;
	}

}
