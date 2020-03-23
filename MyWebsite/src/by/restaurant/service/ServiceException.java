package by.restaurant.service;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 4L;
	
	public ServiceException() {}
	
	public ServiceException(String message){
		super(message);
	}
	public ServiceException(String message, Exception e){
		super(message, e);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
