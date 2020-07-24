package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.Order;
import by.restaurant.bean.Review;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.OrderService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class AllReviews implements Command {

	private static final Logger logger = LogManager.getLogger(AllReviews.class);
	private static final String REVIEW_ADDED = "review_added";
	private static final String REVIEW_DELETED = "review_deleted";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		List<Review> reviews = null;
		
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			
			reviews = userService.showAllReviews();

		    request.setAttribute(RequestParameterName.ALL_REVIEWS, reviews);

			String message = request.getParameter(RequestParameterName.MESSAGE);
			if (message != null && message.equals(REVIEW_ADDED)) {
				request.setAttribute(RequestParameterName.ADD_REVIEW_SUCCESS_MESSAGE, resourceBundle.getString(REVIEW_ADDED));
			}
			if (message != null && message.equals(REVIEW_DELETED)) {
				request.setAttribute(RequestParameterName.REVIEW_DELETED, resourceBundle.getString(REVIEW_DELETED));
			}
			
			session.setAttribute(SessionAttributeName.COMMAND, "all_reviews");

			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.REVIEWS_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
            logger.log(Level.ERROR, "Error during opening the page with all revies", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
