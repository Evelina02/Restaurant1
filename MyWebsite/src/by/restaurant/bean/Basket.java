package by.restaurant.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket implements Serializable {

	 private static final long serialVersionUID = 18L;
	 
	 private List<Dish> dishes;
	 private Map <Integer, Integer> countDishById;
	
	 public Basket() {
		 
		 dishes = new ArrayList<Dish>();
		 countDishById = new HashMap<>();
			
	 }

	 public Basket(List<Dish> dishes, Map<Integer, Integer> countDishById) {
		this.dishes = dishes;
		this.countDishById = countDishById;
	 }
	

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public Map<Integer, Integer> getCountDishById() {
		return countDishById;
	}

	public void setCountDishById(Map<Integer, Integer> countDishById) {
		this.countDishById = countDishById;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countDishById == null) ? 0 : countDishById.hashCode());
		result = prime * result + ((dishes == null) ? 0 : dishes.hashCode());
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
		return true;
	}

	@Override
	public String toString() {
		return "Basket [dishes=" + dishes + ", countDishById=" + countDishById + "]";
	}
	 
	 
	 
	 
}
