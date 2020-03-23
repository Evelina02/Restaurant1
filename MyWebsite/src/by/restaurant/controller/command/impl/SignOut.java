package by.restaurant.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.User;
import by.restaurant.bean.util.Role;
import by.restaurant.controller.JspPageName;
import by.restaurant.controller.RequestParameterName;
import by.restaurant.controller.SessionAttributeName;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;

public class SignOut implements Command {

	
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		
		String page = null;
		HttpSession session = request.getSession();

		final String GOODBYE = "Bye, " + session.getAttribute(SessionAttributeName.LOGIN_ATTRIBUTE) + "!";

		//user.setSignedIn(false);

		request.setAttribute(RequestParameterName.GOODBYE_MESSAGE, GOODBYE);
		page = JspPageName.FIRST_PAGE;

		session.removeAttribute(SessionAttributeName.LOGIN_ATTRIBUTE);
		session.removeAttribute(SessionAttributeName.ROLE_ATTRIBUTE);
		
		return page;

	}
}
