package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Order;
import by.restaurant.bean.User;
import by.restaurant.bean.constant.DeliveryType;
import by.restaurant.bean.constant.OrderState;
import by.restaurant.bean.constant.PaymentType;
import by.restaurant.bean.constant.Role;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.util.Sender;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class CreateOrder implements Command {
	
	private static final Logger logger = LogManager.getLogger(CreateOrder.class);

	private static final String MAIL_SUBJECT = "���������� ������";
	// private static final String COUNT_OF_POINTS ;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		DeliveryType deliveryType = null;
		PaymentType paymentType = null;

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String orderTime = format.format(date);

		String deliveryTime = request.getParameter(RequestParameterName.DATE_TIME);
		deliveryTime = deliveryTime.replace('T', ' ');

		String delivery = request.getParameter("delivery");
		if (delivery.equals("courier")) {
			deliveryType = DeliveryType.BY_COURIER;
		} else if (delivery.equals("self_delivery")) {
			deliveryType = DeliveryType.SELF_DELIVERY;
		}

		String payment = request.getParameter("payment");
		if (payment.equals("card")) {
			paymentType = PaymentType.CREDIT_CARD;
		} else if (payment.equals("cash")) {
			paymentType = PaymentType.IN_CASH;
		}

		try {

			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			double countOfPoints = Double.parseDouble((request.getParameter("usedPoints")));

			Basket basket = (Basket) session.getAttribute(SessionAttributeName.BASKET);

			// 1
			double oldTotalPrice = basket.getTotalPrice();
			double newTotalPrice = oldTotalPrice - countOfPoints;

			basket.setTotalPrice(newTotalPrice);

			// 2
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);
			double oldLoyaltyPoints = userService.getLoyaltyPointsById(idUser);
			double newLoyaltyPoints = oldLoyaltyPoints - countOfPoints;

		
			userService.updateLoyaltyPoints(idUser, newLoyaltyPoints);

			// 3

			basket.setUsedLoyaltyPoints(countOfPoints);
			

			///////////////////////////////////////////////////////


			OrderState state = OrderState.IN_PROCESS;

			Order order = new Order(orderTime, deliveryTime, paymentType, deliveryType, basket, state);

			OrderService orderService = serviceFactory.getOrderService();

			boolean added = orderService.addOrder(order, idUser);

			// ��� ������

			double orderPrice = basket.getTotalPrice();
			double loyaltyPoints = userService.getLoyaltyPointsById(idUser);

			if (orderPrice > 10 && orderPrice <= 30) {
				loyaltyPoints += orderPrice * 0.01;
			} else if (orderPrice > 30 && orderPrice <= 60) {
				loyaltyPoints += orderPrice * 0.03;
			} else if (orderPrice > 60 && orderPrice <= 100) {
				loyaltyPoints += orderPrice * 0.05;
			} else if (orderPrice > 100) {
				loyaltyPoints += orderPrice * 0.07;
			}

			userService.updateLoyaltyPoints(idUser, loyaltyPoints);

			if (!added) {
				request.setAttribute(RequestParameterName.CREATE_ORDER_ERROR_MESSAGE,
						resourceBundle.getString("create_order.error"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.BASKET_PAGE);
				dispatcher.forward(request, response);
			} else {
				session.setAttribute(SessionAttributeName.COMMAND, "all_user_orders");

				DishService dishService = serviceFactory.getDishService();
				dishService.clearBasket(basket);

				session.removeAttribute(SessionAttributeName.BASKET);

				String userEmail = userService.getEmailById(idUser);
				int idOrder = orderService.getIdOrderByOrderTime(orderTime);

				String MAIL_BODY = "������������! ��� ����� �" + idOrder + " ��������! \n\n\n������-��������";
				Sender sslSender = new Sender();
				sslSender.send(MAIL_SUBJECT, MAIL_BODY, userEmail);

				response.sendRedirect(
						request.getContextPath() + "/Controller?command=all_user_orders&message=order_added");
			}
		} catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during creating order", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}
}
