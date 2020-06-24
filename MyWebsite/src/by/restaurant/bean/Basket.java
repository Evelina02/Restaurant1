package by.restaurant.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Basket implements Serializable {

	 private static final long serialVersionUID = 18L;
	 
	 private Set<Dish> dishes;
	 private Map <Integer, Integer> countDishById;
	 private double totalPrice;
	 private boolean usedLoyaltyPoints;
	
	 public Basket() {

		 dishes = new HashSet<>();
		 countDishById = new HashMap<>();
	}
	 
	public Basket(Set<Dish> dishes, Map<Integer, Integer> countDishById, double totalPrice) {
		super();
		this.dishes = dishes;
		this.countDishById = countDishById;
		this.totalPrice = totalPrice;
	}
	public Set<Dish> getDishes() {
		return dishes;
	}
	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}
	public Map<Integer, Integer> getCountDishById() {
		return countDishById;
	}
	public void setCountDishById(Map<Integer, Integer> countDishById) {
		this.countDishById = countDishById;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isUsedLoyaltyPoints() {
		return usedLoyaltyPoints;
	}

	public void setUsedLoyaltyPoints(boolean usedLoyaltyPoints) {
		this.usedLoyaltyPoints = usedLoyaltyPoints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countDishById == null) ? 0 : countDishById.hashCode());
		result = prime * result + ((dishes == null) ? 0 : dishes.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (usedLoyaltyPoints ? 1231 : 1237);
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
		Basket other = (Basket) obj;
		if (countDishById == null) {
			if (other.countDishById != null)
				return false;
		} else if (!countDishById.equals(other.countDishById))
			return false;
		if (dishes == null) {
			if (other.dishes != null)
				return false;
		} else if (!dishes.equals(other.dishes))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		if (usedLoyaltyPoints != other.usedLoyaltyPoints)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Basket [dishes=" + dishes + ", countDishById=" + countDishById + ", totalPrice=" + totalPrice
				+ ", usedLoyaltyPoints=" + usedLoyaltyPoints + "]";
	}
	
	 
}
