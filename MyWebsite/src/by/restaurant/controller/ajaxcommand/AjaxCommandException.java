package by.restaurant.controller.ajaxcommand;

public class AjaxCommandException extends Exception{

	private static final long serialVersionUID = 2L;
	
	public AjaxCommandException() {}
	
	public AjaxCommandException(String message){
		super(message);
	}
	public AjaxCommandException(String message, Exception e){
		super(message, e);
	}
	
	public AjaxCommandException(Throwable cause) {
		super(cause);
	}
}
