package by.restaurant.controller.ajaxcommand.impl;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import by.restaurant.bean.Basket;
import by.restaurant.bean.Dish;
import by.restaurant.controller.ajaxcommand.AjaxCommand;
import by.restaurant.controller.command.impl.AddDish;
import by.restaurant.controller.command.impl.ShowMenu;
import by.restaurant.controller.command.util.Sender;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class ResetPassword implements AjaxCommand {

	private static final Logger logger = LogManager.getLogger(ResetPassword.class);

	private static final String MAIL_SUBJECT = "Восстановление пароля";
	private static final String MAIL_BODY = "Здравствуйте! Операция восстановления пароля прошла успешно! Ваш новый пароль - ";
	private static final String RESET_PASSWORD_ERROR = "reset_password_error";
	private static final String SYMBOLS = "qweertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String responseToJsp = null;



				ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
				HttpSession session = request.getSession();

				String login = request.getParameter(RequestParameterName.LOGIN);

				try {

					ServiceFactory serviceFactory = ServiceFactory.getInstance();
					UserService userService = serviceFactory.getUserService();

					if (!userService.isExist(login)) {
//						request.setAttribute(RequestParameterName.NO_SUCH_LOGIN, resourceBundle.getString("wrong_login"));
//						RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
//						dispatcher.forward(request, response);
						responseToJsp = "{\"message\":\"no_such_login\"}";
					}else {
						
					String newPassword = RandomStringUtils.random(8, 0, 61, true, true, SYMBOLS.toCharArray());

					boolean reseted = userService.resetPassword(login, BCrypt.hashpw(newPassword, BCrypt.gensalt()));

					if (!reseted) {
//						request.setAttribute(RequestParameterName.RESET_PASSWORD_ERROR_MESSAGE,
//								resourceBundle.getString(RESET_PASSWORD_ERROR));
//						RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
//						dispatcher.forward(request, response);
						responseToJsp = "{\"message\":\"error\"}";

					} else {
						
						String userEmail = userService.getEmailByLogin(login);
						Sender sslSender = new Sender();
						sslSender.send(MAIL_SUBJECT, MAIL_BODY + newPassword , userEmail);

//						response.sendRedirect(request.getContextPath() + "/jsp/authorization.jsp?message=password_changed");
						responseToJsp = "{\"message\":\"ok\"}";

					}
	

					}
				} catch (ServiceException e) {
		            logger.log(Level.ERROR, "Error during reseting password", e);
					RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
					dispatcher.forward(request, response);
				}

			
	return responseToJsp;
}
}
