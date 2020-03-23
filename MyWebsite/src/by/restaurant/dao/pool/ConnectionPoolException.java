package by.restaurant.dao.pool;

public class ConnectionPoolException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ConnectionPoolException() {}

	public ConnectionPoolException(String message){
		super(message);
	}
	public ConnectionPoolException(String message, Exception e){
		super(message, e);
	}
	
	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}
}
