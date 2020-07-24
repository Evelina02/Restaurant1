//package by.restaurant.controller.ajaxcommand.impl;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.ResourceBundle;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import by.restaurant.bean.Basket;
//import by.restaurant.bean.Dish;
//import by.restaurant.controller.ajaxcommand.AjaxCommand;
//import by.restaurant.controller.command.impl.AddDish;
//import by.restaurant.controller.command.impl.ShowMenu;
//import by.restaurant.controller.constantname.JspPageName;
//import by.restaurant.controller.constantname.RequestParameterName;
//import by.restaurant.controller.constantname.SessionAttributeName;
//import by.restaurant.service.DishService;
//import by.restaurant.service.ServiceException;
//import by.restaurant.service.UserService;
//import by.restaurant.service.factory.ServiceFactory;
//
//public class UseLoyaltyPoints implements AjaxCommand {
//
//	private static final Logger logger = LogManager.getLogger(AddDish.class);
//	private static final String COUNT = "count";
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		String responseToJsp = null;
//
//		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
//		HttpSession session = request.getSession();
//		try {
//
//			double countOfPoints = Double.parseDouble((request.getParameter(COUNT)));
//
//			Basket basket = (Basket) session.getAttribute(SessionAttributeName.BASKET);
//
//			//1
//			double oldTotalPrice = basket.getTotalPrice();
//			double newTotalPrice = oldTotalPrice - countOfPoints;
//
//			basket.setTotalPrice(newTotalPrice);
//			
//			//2
//			ServiceFactory serviceFactory = ServiceFactory.getInstance();
//			UserService userService = serviceFactory.getUserService();
//			
//			int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);
//			double oldLoyaltyPoints = userService.getLoyaltyPointsById(idUser);
//			double newLoyaltyPoints = oldLoyaltyPoints - countOfPoints;
//
//			//в базу потом
//			//userService.updateLoyaltyPoints(idUser, newLoyaltyPoints);
//
//			//3
//	
//			// это тоже потом basket.setUsedLoyaltyPoints(true);
//			responseToJsp = "{\"newLoyaltyPoints\":\"" + newLoyaltyPoints + "\",\"newTotalPrice\":\"" + newTotalPrice + "\"}";
//		
//			
//		} catch (ServiceException e) {
//			// log
//			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
//			dispatcher.forward(request, response);
//		}
//		return responseToJsp;
//	}
//}
