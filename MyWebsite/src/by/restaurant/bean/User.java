package by.restaurant.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import by.restaurant.bean.constant.Role;

public class User implements Serializable{

	private static final long serialVersionUID = 3L;

	private int id;
	private String login;
	private String password;
	private Role role;
	private String email;
	private String address;
	private double loyaltyPoints;
	private boolean banned;
	
	private List<Order> orders;
	private List<Review> reviews; 

	
	public User() {}

	public User(String login, String password, Role role, String email, String address) {
		
		this.login = login;
		this.password = password;
		this.role = role;
		this.email = email;
		this.address = address;
	}

	public User(int id, String login, String password, Role role, String email, String address, double loyaltyPoints,
			boolean banned, List<Order> orders, List<Review> reviews) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
		this.email = email;
		this.address = address;
		this.loyaltyPoints = loyaltyPoints;
		this.banned = banned;
		this.orders = orders;
		this.reviews = reviews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + "[id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", email=" + email
				+ ", address=" + address + ", loyaltyPoints=" + loyaltyPoints + ", banned=" + banned + ", orders="
				+ orders + ", reviews=" + reviews + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (banned ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		long temp;
		temp = Double.doubleToLongBits(loyaltyPoints);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
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
		if (banned != other.banned)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (Double.doubleToLongBits(loyaltyPoints) != Double.doubleToLongBits(other.loyaltyPoints))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (reviews == null) {
			if (other.reviews != null)
				return false;
		} else if (!reviews.equals(other.reviews))
			return false;
		if (role != other.role)
			return false;
		return true;
	}


	
}
