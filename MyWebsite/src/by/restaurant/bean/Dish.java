package by.restaurant.bean;

import java.io.Serializable;
import java.util.Set;

import by.restaurant.bean.util.Category;
import by.restaurant.bean.util.State;

public class Dish implements Serializable {

    private static final long serialVersionUID = 12L;

	private int id;
	private String name;
	private double price;
	private String picture;
	private Category category;
	private double amount;
	private State state;
	
	private Set<String> ingredients;
	private Set<String> refusalOfIngredients;
	
	public Dish() {};	
	
	public Dish(int id, String name, double price, String picture, Category category, double amount, State state,
			Set<String> ingredients, Set<String> refusalOfIngredients) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.category = category;
		this.amount = amount;
		this.state = state;
		this.ingredients = ingredients;
		this.refusalOfIngredients = refusalOfIngredients;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", price=" + price + ", picture=" + picture + ", category="
				+ category + ", amount=" + amount + ", state=" + state + ", ingredients=" + ingredients
				+ ", refusalOfIngredients=" + refusalOfIngredients + "]";
	}

	
	
}
