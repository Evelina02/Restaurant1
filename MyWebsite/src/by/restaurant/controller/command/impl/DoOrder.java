package by.restaurant.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.constant.DeliveryType;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.util.Sender;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class DoOrder implements Command {

	private static final String MAIL_SUBJECT = "Ваш заказ готов!";
	private static final String END_OF_BODY = "\n\n\nОнлайн-ресторан";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String mailBodyPart2 = null;
		int orderId = Integer.parseInt(request.getParameter(RequestParameterName.ORDER_ID));
		String delivery = request.getParameter("deliveryType");
		if (delivery.equals("BY_COURIER")) {
			String deliveryTime = request.getParameter("deliveryTime");
			mailBodyPart2 = "Ожидайте курьера приблизительно в " + deliveryTime;
		} else if (delivery.equals("SELF_DELIVERY")) {
			mailBodyPart2 = "Вы можете забрать заказ в любое удобное для Вас время";
		}
		String orderTime = request.getParameter("orderTime");

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			OrderService orderService = serviceFactory.getOrderService();
			orderService.doOrder(orderId);
			
			session.setAttribute(SessionAttributeName.COMMAND, "all_orders");
			
			UserService userService = serviceFactory.getUserService();
			String login = request.getParameter(RequestParameterName.LOGIN);
			String userEmail = userService.getEmailByLogin(login);
			int idOrder = orderService.getIdOrderByOrderTime(orderTime);
			String MAIL_BODY = "Здравствуйте! Ваш заказ №" + idOrder + " готов!";
			Sender sslSender = new Sender();
			sslSender.send(MAIL_SUBJECT, MAIL_BODY + mailBodyPart2 + END_OF_BODY, userEmail);

			response.sendRedirect(request.getContextPath() + "/Controller?command=all_orders&message=order_done");

        } catch (ServiceException e) {
            //log
        	RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
        }
	}

}
