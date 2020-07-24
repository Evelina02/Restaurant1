package by.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.factory.ServiceFactory;

public class CloseOrder implements Command {
	
	private static final Logger logger = LogManager.getLogger(CloseOrder.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		int orderId = Integer.parseInt(request.getParameter(RequestParameterName.ORDER_ID));

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			OrderService orderService = serviceFactory.getOrderService();
			orderService.closeOrder(orderId);
			
			session.setAttribute(SessionAttributeName.COMMAND, "all_orders");
			
			response.sendRedirect(request.getContextPath() + "/Controller?command=all_orders&message=order_closed");

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during closing order", e);
        	RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
        }
	}
}
