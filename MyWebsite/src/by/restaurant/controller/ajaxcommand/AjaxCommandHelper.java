package by.restaurant.controller.ajaxcommand;

import java.util.HashMap;
import java.util.Map;

import by.restaurant.controller.ajaxcommand.impl.AddDishToBasket;
import by.restaurant.controller.ajaxcommand.impl.ChangeAmountOfDish;
import by.restaurant.controller.ajaxcommand.impl.NoSuchCommand;


public class AjaxCommandHelper {

	private static final AjaxCommandHelper instance = new AjaxCommandHelper();
	
	private final Map <AjaxCommandName, AjaxCommand> ajaxCommands = new HashMap<>();
	
	public AjaxCommandHelper() {
		
		ajaxCommands.put(AjaxCommandName.CHANGE_AMOUNT_OF_DISH, new ChangeAmountOfDish());
		ajaxCommands.put(AjaxCommandName.ADD_DISH_TO_BASKET, new AddDishToBasket());

		ajaxCommands.put(AjaxCommandName.NO_SUCH_COMMAND, new NoSuchCommand());
	}
	
	public static AjaxCommandHelper getInstance() {
		
		return instance;
	}
	
	public AjaxCommand getAjaxCommand(String commandName) throws AjaxCommandException {
		
		AjaxCommandName name = null;
		AjaxCommand ajaxCommand = null;
		
		try {
			name = AjaxCommandName.valueOf(commandName.toUpperCase());
			ajaxCommand = ajaxCommands.get(name);
		} catch(IllegalArgumentException | NullPointerException e) {
			ajaxCommand = ajaxCommands.get(AjaxCommandName.NO_SUCH_COMMAND);
			throw new AjaxCommandException("Error! Incorrect request"); //???
		}
		return ajaxCommand;
	}


}
