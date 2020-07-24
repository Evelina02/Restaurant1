package by.restaurant.bean;

import java.io.Serializable;
import java.util.Set;

import by.restaurant.bean.constant.Category;
import by.restaurant.bean.constant.DishState;

public class Dish implements Serializable {

    private static final long serialVersionUID = 12L;

	private int id;
	private String name;
	private double price;
	private String picture;
	private Category category;
	private String amount;
	private DishState state;
	private boolean isDeleted;
	
	private Set<String> ingredients;
	private Set<String> refusalOfIngredients;
	
	public Dish() {}

	
	public Dish(String name, double price, String picture, Category category, String amount, Set<String> ingredients) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.category = category;
		this.amount = amount;
		this.ingredients = ingredients;
	}


	public Dish(int id, String name, double price, String picture, Category category, String amount, DishState state,
			boolean isDeleted, Set<String> ingredients, Set<String> refusalOfIngredients) {

		this.id = id;
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.category = category;
		this.amount = amount;
		this.state = state;
		this.isDeleted = isDeleted;
		this.ingredients = ingredients;
		this.refusalOfIngredients = refusalOfIngredients;
	}



	public Dish(String name, double price, String picture, Category category, String amount) {
		super();
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.category = category;
		this.amount = amount;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public DishState getState() {
		return state;
	}

	public void setState(DishState state) {
		this.state = state;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<String> ingredients) {
		this.ingredients = ingredients;
	}

	public Set<String> getRefusalOfIngredients() {
		return refusalOfIngredients;
	}

	public void setRefusalOfIngredients(Set<String> refusalOfIngredients) {
		this.refusalOfIngredients = refusalOfIngredients;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + "[id=" + id + ", name=" + name + ", price=" + price + ", picture=" + picture + ", category="
				+ category + ", amount=" + amount + ", state=" + state + ", isDeleted=" + isDeleted + ", ingredients="
				+ ingredients + ", refusalOfIngredients=" + refusalOfIngredients + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((refusalOfIngredients == null) ? 0 : refusalOfIngredients.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Dish other = (Dish) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (category != other.category)
			return false;
		if (id != other.id)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (isDeleted != other.isDeleted)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (refusalOfIngredients == null) {
			if (other.refusalOfIngredients != null)
				return false;
		} else if (!refusalOfIngredients.equals(other.refusalOfIngredients))
			return false;
		if (state != other.state)
			return false;
		return true;
	};	
	
	
	
}
