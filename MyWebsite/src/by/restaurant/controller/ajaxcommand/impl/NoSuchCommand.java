package by.restaurant.controller.ajaxcommand.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.restaurant.controller.ajaxcommand.AjaxCommand;

public class NoSuchCommand implements AjaxCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return null;
		//return "{\"status\":\"error\"}";
	}

}
