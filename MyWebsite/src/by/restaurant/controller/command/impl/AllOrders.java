package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.Order;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class AllOrders implements Command {

	private static final String ORDER_DONE = "order_done";
	private static final String ORDER_CLOSED = "order_closed";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		List<Order> orders = null;
		
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			OrderService orderService = serviceFactory.getOrderService();

			orders = orderService.allOrders();

		    request.setAttribute(RequestParameterName.ALL_ORDERS, orders);

			
			String message = request.getParameter(RequestParameterName.MESSAGE);
			if (message != null && message.equals(ORDER_DONE)) {
				request.setAttribute(RequestParameterName.ORDER_DONE, resourceBundle.getString(ORDER_DONE));
			}
			if (message != null && message.equals(ORDER_CLOSED)) {
				request.setAttribute(RequestParameterName.ORDER_CLOSED, resourceBundle.getString(ORDER_CLOSED));
			}

			session.setAttribute(SessionAttributeName.COMMAND, "all_orders");

			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ALL_ORDERS_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}
}
