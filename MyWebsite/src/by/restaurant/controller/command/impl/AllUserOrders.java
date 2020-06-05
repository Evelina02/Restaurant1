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
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class AllUserOrders implements Command {

	private static final String ORDER_ADDED = "order_added";
	private static final String ORDER_DELETED = "order_deleted";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);

		List<Order> orders = null;
		
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			OrderService orderService = serviceFactory.getOrderService();

			orders = orderService.allOrdersOfUser(idUser);

		    request.setAttribute(RequestParameterName.ALL_USER_ORDERS, orders);

			
			String message = request.getParameter(RequestParameterName.MESSAGE);
			if (message != null && message.equals(ORDER_ADDED)) {
				request.setAttribute(RequestParameterName.ORDER_ADDED, resourceBundle.getString("order.added"));
			}
			if (message != null && message.equals(ORDER_DELETED)) {
				request.setAttribute(RequestParameterName.ORDER_DELETED, resourceBundle.getString("order.deleted"));
			}

			session.setAttribute(SessionAttributeName.COMMAND, "all_user_orders");

			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.USER_ORDERS_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
