package by.restaurant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.command.CommandHelper;
import by.restaurant.controller.command.impl.AddDish;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.dao.pool.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(Controller.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		Command command = null;
		
		try {
			command = CommandHelper.getInstance().getCommand(commandName);
			command.execute(request, response);

		} catch (CommandException e) {
			logger.log(Level.ERROR, "Error in controller", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}


	}
	
}
