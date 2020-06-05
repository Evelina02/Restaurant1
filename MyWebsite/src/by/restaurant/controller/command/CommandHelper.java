package by.restaurant.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.restaurant.controller.ajaxcommand.AjaxCommandName;
import by.restaurant.controller.command.impl.AddDish;
import by.restaurant.controller.command.impl.AddDishToBasket;
import by.restaurant.controller.command.impl.AddReview;
import by.restaurant.controller.command.impl.AdminMenu;
import by.restaurant.controller.command.impl.AllOrders;
import by.restaurant.controller.command.impl.AllReviews;
import by.restaurant.controller.command.impl.AllUserOrders;
import by.restaurant.controller.command.impl.AllUsers;
import by.restaurant.controller.command.impl.BanUser;
import by.restaurant.controller.command.impl.CancelOrder;
import by.restaurant.controller.command.impl.ChangeDish;
import by.restaurant.controller.command.impl.ChangeLanguage;
import by.restaurant.controller.command.impl.ChangePassword;
import by.restaurant.controller.command.impl.ClearBasket;
import by.restaurant.controller.command.impl.CloseOrder;
import by.restaurant.controller.command.impl.CreateOrder;
import by.restaurant.controller.command.impl.DeleteDish;
import by.restaurant.controller.command.impl.DeleteDishFromBasket;
import by.restaurant.controller.command.impl.DeleteReview;
import by.restaurant.controller.command.impl.DoOrder;
import by.restaurant.controller.command.impl.ExportToXml;
import by.restaurant.controller.command.impl.NoSuchCommand;
import by.restaurant.controller.command.impl.Profile;
import by.restaurant.controller.command.impl.ResetPassword;
import by.restaurant.controller.command.impl.SaveUserChanges;
import by.restaurant.controller.command.impl.SearchDishesByPartOfName;
import by.restaurant.controller.command.impl.ShowBasket;
import by.restaurant.controller.command.impl.ShowMenu;
import by.restaurant.controller.command.impl.SignIn;
import by.restaurant.controller.command.impl.SignOut;
import by.restaurant.controller.command.impl.SignUp;
import by.restaurant.controller.command.impl.StaxParser;
import by.restaurant.controller.command.impl.UnbanUser;

public class CommandHelper {

	private static final CommandHelper instance = new CommandHelper();
	
	private final Map <CommandName, Command> commands = new HashMap<>();
	
	public CommandHelper() {
		
		commands.put(CommandName.SIGN_IN, new SignIn());
		commands.put(CommandName.SIGN_OUT, new SignOut());
		commands.put(CommandName.SIGN_UP, new SignUp());
		commands.put(CommandName.SHOW_MENU, new ShowMenu());
		commands.put(CommandName.CHANGE_LANGUAGE, new ChangeLanguage());
		commands.put(CommandName.SHOW_BASKET, new ShowBasket());
		commands.put(CommandName.ADD_DISH_TO_BASKET, new AddDishToBasket());
		commands.put(CommandName.DELETE_DISH_FROM_BASKET, new DeleteDishFromBasket());
		commands.put(CommandName.CLEAR_BASKET, new ClearBasket());
		commands.put(CommandName.CREATE_ORDER, new CreateOrder());
		commands.put(CommandName.ALL_USER_ORDERS, new AllUserOrders());
		commands.put(CommandName.PROFILE, new Profile());
		commands.put(CommandName.SAVE_USER_CHANGES, new SaveUserChanges());
		commands.put(CommandName.CANCEL_ORDER, new CancelOrder());
		commands.put(CommandName.ALL_REVIEWS, new AllReviews());
		commands.put(CommandName.CHANGE_PASSWORD, new ChangePassword());
		commands.put(CommandName.ALL_USERS, new AllUsers());
		commands.put(CommandName.BAN_USER, new BanUser());
		commands.put(CommandName.UNBAN_USER, new UnbanUser());
		commands.put(CommandName.ADMIN_MENU, new AdminMenu());
		commands.put(CommandName.DELETE_DISH, new DeleteDish());
		commands.put(CommandName.RESET_PASSWORD, new ResetPassword());
		commands.put(CommandName.CHANGE_DISH, new ChangeDish());
		commands.put(CommandName.ADD_DISH, new AddDish());
		commands.put(CommandName.ADD_REVIEW, new AddReview());
		commands.put(CommandName.DELETE_REVIEW, new DeleteReview());
		commands.put(CommandName.ALL_ORDERS, new AllOrders());
		commands.put(CommandName.DO_ORDER, new DoOrder());
		commands.put(CommandName.CLOSE_ORDER, new CloseOrder());
		commands.put(CommandName.SEARCH_DISHES_BY_PART_OF_NAME, new SearchDishesByPartOfName());

		commands.put(CommandName.STAX_PARSER, new StaxParser());
		commands.put(CommandName.EXPORT_TO_XML, new ExportToXml());

		commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
	}
	
	public static CommandHelper getInstance() {
		
		return instance;
	}
	
	public Command getCommand(String commandName) throws CommandException {
		
		CommandName name = null;
		Command command = null;
		
		try {
			name = CommandName.valueOf(commandName.toUpperCase());
			command = commands.get(name);
		} catch(IllegalArgumentException | NullPointerException e) {
			command = commands.get(CommandName.NO_SUCH_COMMAND);
			throw new CommandException("Error! Incorrect request"); //???
		}
		return command;
	}


}
