package by.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.controller.ajaxcommand.AjaxCommand;
import by.restaurant.controller.ajaxcommand.AjaxCommandException;
import by.restaurant.controller.ajaxcommand.AjaxCommandHelper;
import by.restaurant.controller.command.impl.AddDish;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;

@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(AjaxController.class);

	private static final long serialVersionUID = 1L;

	public AjaxController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ajaxCommandName = request.getParameter(RequestParameterName.COMMAND_NAME);
		AjaxCommand ajaxCommand = null;
		try {
			ajaxCommand = AjaxCommandHelper.getInstance().getAjaxCommand(ajaxCommandName);
			String ajaxResponse = ajaxCommand.execute(request, response);

			PrintWriter out = response.getWriter();
			response.setContentType("text/plain");
			out.print(ajaxResponse);
			out.flush();
		} catch (AjaxCommandException e) {
			logger.log(Level.ERROR, "Error in ajax controller", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
