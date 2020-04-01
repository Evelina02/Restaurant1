package by.restaurant.controller.command;

import javax.servlet.http.HttpServletRequest;

import by.restaurant.controller.Router;

public interface Command {

	public Router execute(HttpServletRequest request) throws CommandException;
}
