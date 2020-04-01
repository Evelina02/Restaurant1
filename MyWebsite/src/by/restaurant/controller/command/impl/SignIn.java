package by.restaurant.controller.command.impl;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import by.restaurant.bean.User;
import by.restaurant.controller.Router;
import by.restaurant.controller.Router.RouteType;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.command.CommandException;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.service.ServiceException;
import by.restaurant.service.UserService;
import by.restaurant.service.factory.ServiceFactory;

public class SignIn implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		
		Router router = new Router();
		String page = JspPageName.ERROR_PAGE;
		HttpSession session = request.getSession(); 
		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password = request.getParameter(RequestParameterName.PASSWORD);


		try {

			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			UserService userService = serviceFactory.getUserService();
			if(userService.isExist(login)) {
				User user = userService.getUser(login, password);
				if(user == null) {
					request.setAttribute(RequestParameterName.WRONG_PASSWORD, resourceBundle.getString("wrong_password"));
					page = JspPageName.SIGN_IN_PAGE;
					router.setPagePath(page);
				}else {
	                session.setAttribute(SessionAttributeName.ID_USER, user.getId());
	                session.setAttribute(SessionAttributeName.LOGIN, user.getLogin());
	                session.setAttribute(SessionAttributeName.ROLE, user.getRole());
	        		final String HELLO_MESSAGE = resourceBundle.getString("message.hello") + "" + session.getAttribute(SessionAttributeName.LOGIN) + "!";

	                request.setAttribute(RequestParameterName.HELLO_MESSAGE, HELLO_MESSAGE);
	                String commandName = (String)session.getAttribute("command");
	                
					if(commandName == null) {
	                	page = JspPageName.WELCOME_PAGE;
	                }else {
	                	page = request.getContextPath() + "/Controller?command=" + commandName;
	                }	
	                router.setPagePath(page);
	                router.setRouteType(RouteType.REDIRECT);;
				}

				
			}else {
				request.setAttribute(RequestParameterName.NO_SUCH_LOGIN, resourceBundle.getString("wrong_login"));
				page = JspPageName.SIGN_IN_PAGE;		
				router.setPagePath(page);
			}
		} catch (ServiceException e) {
			throw new CommandException("Error during signing in (getting service)", e);
		}
		
		return router;
		
	}

}
