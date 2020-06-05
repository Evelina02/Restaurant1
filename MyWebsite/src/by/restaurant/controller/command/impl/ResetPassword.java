package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.util.Sender;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class ResetPassword implements Command {

	private static final String MAIL_SUBJECT = "Восстановление пароля";
	private static final String MAIL_BODY = "Здравствуйте! Операция восстановления пароля прошла успешно! Ваш новый пароль - ";
	private static final String RESET_PASSWORD_ERROR = "reset_password_error";
	private static final String SYMBOLS = "qweertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String login = request.getParameter(RequestParameterName.LOGIN);

		try {
			String newPassword = RandomStringUtils.random(8, 0, 61, true, true, SYMBOLS.toCharArray());

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			boolean reseted = userService.resetPassword(login, BCrypt.hashpw(newPassword, BCrypt.gensalt()));

			if (!reseted) {
				request.setAttribute(RequestParameterName.RESET_PASSWORD_ERROR_MESSAGE,
						resourceBundle.getString(RESET_PASSWORD_ERROR));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
				dispatcher.forward(request, response);
			} else {
				
				String userEmail = userService.getEmailByLogin(login);
				Sender sslSender = new Sender();
				sslSender.send(MAIL_SUBJECT, MAIL_BODY + newPassword , userEmail);

				response.sendRedirect(request.getContextPath() + "/jsp/authorization.jsp?message=password_changed");
			}
		} catch (ServiceException e) {
			// log
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
