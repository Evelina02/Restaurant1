package by.restaurant.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import by.restaurant.bean.util.Role;

public class User implements Serializable{

	private static final long serialVersionUID = 3L;

	private String login;
	private String password;
	private Role role;
	private String email;
	private String address;
	private double loyaltyPoints;
	private boolean isBanned;
	
	private List<Order> orders;//все заказы 
	private List<Review> reviews;//все отзывы 

	
	public User() {};	
	
	public User(String login, String password, Role role, String email) {
		this.login = login;
		this.password = password;
		this.role = role;
		this.email = email;
	}


	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLoyaltyPoints() {
		return loyaltyPoints;
	}
	public void setLoyaltyPoints(double loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
	public boolean isBanned() {
		return isBanned;
	}
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", userType=" + role + ", email=" + email
				+ ", address=" + address + ", loyaltyPoints=" + loyaltyPoints + ", isBanned=" + isBanned + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (isBanned ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		long temp;
		temp = Double.doubleToLongBits(loyaltyPoints);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (isBanned != other.isBanned)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (Double.doubleToLongBits(loyaltyPoints) != Double.doubleToLongBits(other.loyaltyPoints))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		return true;
	}

	
	
	
}
