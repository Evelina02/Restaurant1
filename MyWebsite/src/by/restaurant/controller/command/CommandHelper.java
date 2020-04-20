package by.restaurant.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.restaurant.controller.command.impl.*;

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
