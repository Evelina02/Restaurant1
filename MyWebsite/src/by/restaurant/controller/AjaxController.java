package by.restaurant.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.controller.ajaxcommand.AjaxCommand;
import by.restaurant.controller.ajaxcommand.AjaxCommandException;
import by.restaurant.controller.ajaxcommand.AjaxCommandHelper;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.command.CommandHelper;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.factory.ServiceFactory;

@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxController() {
		super();
		// TODO Auto-generated constructor stub
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
			//

		}
	}

}
