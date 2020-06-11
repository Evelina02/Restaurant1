package by.restaurant.service.impl.validator;

public class Validator {
	
	private static final String PATTERN_LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{3,20}$";
	private static final String PATTERN_PASSWORD = "^[a-zA-z]{1}[a-zA-Z1-9]{3,20}$";
	
	public static boolean validateIsNull(Object o){
	 
		if(o == null) {
			return false;
		}
		  
		return true;
	}

	public static boolean validateLogin(String login){
		
		if(!login.matches(PATTERN_LOGIN)) {
			return false;
		}
		return true;
	}

	public static boolean validatePassword(String password){
		
		if(!password.matches(PATTERN_PASSWORD)) {
			return false;
		}
		return true;
	}

	public static boolean validateString(String str) {

		if (str.isEmpty() || str == null) {
			return false;
		}
		return true;
	}
	
	public static boolean validatePositiveNumber(int number){
		
		if(number < 0) {
			return false;
		}
		return true;
	}
	
	public static boolean validatePositiveNumber(double number){
		
		if(number < 0) {
			return false;
		}
		return true;
	}
}
