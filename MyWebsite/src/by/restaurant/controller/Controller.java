package by.restaurant.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.command.CommandHelper;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.dao.pool.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = JspPageName.ERROR_PAGE;
		RequestDispatcher dispatcher;
		try {
			String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
			Command command = CommandHelper.getInstance().getCommand(commandName);
				
	        Router router = command.execute(request);
	        page = router.getPagePath();
	        if (router.getRouteType() == Router.RouteType.FORWARD) {
	            dispatcher = request.getRequestDispatcher(page);
	            if(dispatcher != null) {
					dispatcher.forward(request, response);
				}else {
					dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
					dispatcher.forward(request, response);
				}
	        } else {
	            response.sendRedirect(page);
	        }
	        
		}catch(CommandException e) {
            dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
            dispatcher.forward(request, response);
		}catch(Exception e) {
			dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
            dispatcher.forward(request, response);
		}
	}
	
}
