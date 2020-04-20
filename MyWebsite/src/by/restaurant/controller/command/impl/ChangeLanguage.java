package by.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;

public class ChangeLanguage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(true);

		session.setAttribute("local", request.getParameter("language"));

		String commandName = (String)session.getAttribute("command");
		if(commandName == null) {
			response.sendRedirect(JspPageName.WELCOME_PAGE);
        }else {
        	response.sendRedirect(request.getContextPath() + "/Controller?command=" + commandName);
        }	

	}
}

