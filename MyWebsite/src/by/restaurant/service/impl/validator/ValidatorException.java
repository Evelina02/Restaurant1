package by.restaurant.service.impl.validator;

import by.restaurant.service.ServiceException;

public class ValidatorException extends ServiceException {

	private static final long serialVersionUID = 452L;
	
	public ValidatorException() {}
	
	public ValidatorException(String message){
		super(message);
	}
	public ValidatorException(String message, Exception e){
		super(message, e);
	}
	
	public ValidatorException(Throwable cause) {
		super(cause);
	}
}
