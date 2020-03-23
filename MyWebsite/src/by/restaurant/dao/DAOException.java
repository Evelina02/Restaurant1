package by.restaurant.dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = 3L;
	
	public DAOException() {}
	
	public DAOException(String message){
		super(message);
	}
	public DAOException(String message, Exception e){
		super(message, e);
	}
	
	public DAOException(Throwable cause) {
		super(cause);
	}
}
