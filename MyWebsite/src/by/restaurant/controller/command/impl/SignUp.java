package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import by.restaurant.bean.User;
import by.restaurant.bean.constant.Role;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.command.util.Sender;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.dao.DAOException;
import by.restaurant.dao.UserDAO;
import by.restaurant.dao.factory.DAOFactory;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class SignUp implements Command {
	
	private static final Logger logger = LogManager.getLogger(SignUp.class);

	private static final String MAIL_SUBJECT = "Спасибо за регистрацию!";
	private static final String MAIL_BODY = "Вы успешно зарегистрированы на сайте онлайн-ресторана!";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();

		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		String email = request.getParameter(RequestParameterName.EMAIL);
		String address = request.getParameter(RequestParameterName.ADDRESS);
		try {

			User user = new User(login, BCrypt.hashpw(password, BCrypt.gensalt()), Role.CLIENT, email, address);

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			if (userService.isExist(login)) {
				request.setAttribute(RequestParameterName.LOGIN_EXISTS,
						resourceBundle.getString("message.login_exists"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_UP_PAGE);
				dispatcher.forward(request, response);

			} else {
				boolean added = userService.addUser(user);

				if (!added) {
					request.setAttribute(RequestParameterName.REGISTER_ERROR_MESSAGE,
							resourceBundle.getString("register.error"));
					RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
					dispatcher.forward(request, response);
				} else {
					int userId = userService.getIdByLogin(login);
					session.setAttribute(SessionAttributeName.ID_USER, userId);
					session.setAttribute(SessionAttributeName.LOGIN, user.getLogin());
					session.setAttribute(SessionAttributeName.ROLE, user.getRole());
					request.setAttribute(RequestParameterName.REGISTER_SUCCESS_MESSAGE,
							resourceBundle.getString("register.success_message"));

					Sender sslSender = new Sender();
					sslSender.send(MAIL_SUBJECT, MAIL_BODY, email);
				}
				String commandName = (String) session.getAttribute(SessionAttributeName.COMMAND);

				if (commandName == null) {
					response.sendRedirect(JspPageName.WELCOME_PAGE);
				} else {
					response.sendRedirect(request.getContextPath() + "/Controller?command=" + commandName);
				}
			}

		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error during signing up in system", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}

	}
}
