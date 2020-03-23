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
import by.restaurant.dao.pool.*;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//processRequest(request, response);
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//processRequest(request, response);
		
		String page = null;

		try {
			String commandName = request.getParameter(RequestParameterName.COMMAND_NAME);
			Command command = CommandHelper.getInstance().getCommand(commandName);
				
			page = command.execute(request);
		}catch(CommandException e) {
			page = JspPageName.ERROR_PAGE;
		}catch(Exception e) {
			page = JspPageName.ERROR_PAGE;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		
		if(dispatcher != null) {
			dispatcher.forward(request, response);
		}else {
			errorMessageDireclyFromResponse(response);
		}
	}
	
//	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
//		
//
//	}
		
		private void errorMessageDireclyFromResponse(HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("text/html");
			response.getWriter().println("ERROR !!!");
			
		}

		@Override
		public void init() throws ServletException {
			super.init();
			try {
				ConnectionPool.getInstance().initPoolData();
			} catch (ConnectionPoolException e) {
				e.printStackTrace();
				//
			}
		}
		
	    @Override
	    public void destroy() {
	    	ConnectionPool.getInstance().dispose();
	    	super.destroy();
	    }

}
