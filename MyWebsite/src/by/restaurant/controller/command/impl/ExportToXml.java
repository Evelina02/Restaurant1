package by.restaurant.controller.command.impl;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.restaurant.bean.Dish;
import by.restaurant.controller.command.Command;
import by.restaurant.controller.constantname.JspPageName;
import by.restaurant.controller.constantname.RequestParameterName;
import by.restaurant.controller.constantname.SessionAttributeName;
import by.restaurant.dao.DAOException;
import by.restaurant.service.DishService;
import by.restaurant.service.ServiceException;
import by.restaurant.service.XMLService;
import by.restaurant.service.factory.ServiceFactory;

public class ExportToXml implements Command {
	
	private static final Logger logger = LogManager.getLogger(ExportToXml.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.localization.local");
		HttpSession session = request.getSession();
		
		try {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			DishService dishService = serviceFactory.getDishService();
			List<Dish> dishes = dishService.getAllDishes();

			XMLService.createXMLDocument(dishes);

			session.setAttribute(SessionAttributeName.COMMAND, "admin_menu");
			
			response.sendRedirect(request.getContextPath() + "/Controller?command=admin_menu&message=dish_added");
			
		} catch (ServiceException | TransformerConfigurationException | ParserConfigurationException e) {
            logger.log(Level.ERROR, "Error during exporting menu to XML", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.ERROR_PAGE);
			dispatcher.forward(request, response);	
		}
	}

}
