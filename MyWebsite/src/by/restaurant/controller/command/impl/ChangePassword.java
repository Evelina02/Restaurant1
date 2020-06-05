package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.restaurant.bean.User;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.util.Sender;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class ChangePassword implements Command {

	private static final String MAIL_SUBJECT = "Смена пароля";
	private static final String MAIL_BODY = "Здравствуйте! Ваш пароль успешно изменён! \n\n\n Онлайн-ресторан";
	
	private static final String OLD_PASSWORD = "old_password";
	private static final String NEW_PASSWORD = "new_password";
	private static final String WRONG_OLD_PASSWORD = "wrong_old_password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String oldPassword = request.getParameter(OLD_PASSWORD);
		String newPassword = request.getParameter(NEW_PASSWORD);

		int idUser = (int) session.getAttribute(SessionAttributeName.ID_USER);

		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			boolean changed = userService.changePassword(idUser, oldPassword, newPassword);

			if (!changed) {
				response.sendRedirect(request.getContextPath() + "/Controller?command=profile&message=wrong_old_password");
			} else {
				String userEmail = userService.getEmailById(idUser);
				Sender sslSender = new Sender();
				sslSender.send(MAIL_SUBJECT, MAIL_BODY, userEmail);

				response.sendRedirect(request.getContextPath() + "/Controller?command=profile&message=password_changed");
			}
		} catch (ServiceException e) {
			// log
        	RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
        }

	}

}
