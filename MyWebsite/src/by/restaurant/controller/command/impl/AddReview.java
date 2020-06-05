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

import by.restaurant.bean.Basket;
import by.restaurant.bean.Order;
import by.restaurant.bean.Review;
import by.restaurant.bean.constant.DeliveryType;
import by.restaurant.bean.constant.OrderState;
import by.restaurant.bean.constant.PaymentType;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class AddReview implements Command {

	private static final String ADD_REVIEW_ERROR = "add_review_error";//добавить в loc

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String comment = request.getParameter(RequestParameterName.COMMENT);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss a");
		String reviewTime = format.format(date);
		

		Review review = new Review(comment, reviewTime);
		
		
		
		
		

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);
			boolean added = userService.addReview(review, idUser);

			if (!added) {
				request.setAttribute(RequestParameterName.ADD_REVIEW_ERROR_MESSAGE,
						resourceBundle.getString(ADD_REVIEW_ERROR));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.REVIEWS_PAGE);
				dispatcher.forward(request, response);
			}

			session.setAttribute(SessionAttributeName.COMMAND, "all_reviews");
		
			response.sendRedirect(request.getContextPath() + "/Controller?command=all_reviews&message=review_added");

		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	
	}

}
