package by.restaurant.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.restaurant.controller.JspPageName;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;

public class NoSuchCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) throws CommandException {

		return JspPageName.ERROR_PAGE;
	}

}
