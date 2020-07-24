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
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class SignIn implements Command {

	private static final Logger logger = LogManager.getLogger(SignIn.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();

			if (!userService.isExist(login)) {
				request.setAttribute(RequestParameterName.NO_SUCH_LOGIN, resourceBundle.getString("wrong_login"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
				dispatcher.forward(request, response);

			} else if (userService.isBanned(login)) {
				request.setAttribute(RequestParameterName.BANNED_USER, resourceBundle.getString("banned_user"));
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
				dispatcher.forward(request, response);

			} else {

				User user = userService.getUser(login, password);
				if (user == null) {
					request.setAttribute(RequestParameterName.WRONG_PASSWORD,
							resourceBundle.getString("wrong_password"));
					RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.SIGN_IN_PAGE);
					dispatcher.forward(request, response);
				} else {
					session.setAttribute(SessionAttributeName.ID_USER, user.getId());
					session.setAttribute(SessionAttributeName.LOGIN, user.getLogin());
					session.setAttribute(SessionAttributeName.ROLE, user.getRole());
					final String HELLO_MESSAGE = resourceBundle.getString("message.hello") + ""
							+ session.getAttribute(SessionAttributeName.LOGIN) + "!";

					request.setAttribute(RequestParameterName.HELLO_MESSAGE, HELLO_MESSAGE);

					String commandName = (String) session.getAttribute(SessionAttributeName.COMMAND);

					if (commandName == null) {
						response.sendRedirect(JspPageName.WELCOME_PAGE);
					} else {
						response.sendRedirect(request.getContextPath() + "/Controller?command=" + commandName);
					}
				}
			}

		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error during signing in system", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);
		}
	}
}
