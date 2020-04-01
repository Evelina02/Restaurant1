package by.restaurant.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import by.restaurant.bean.util.DeliveryType;
import by.restaurant.bean.util.OrderState;
import by.restaurant.bean.util.PaymentType;

public class Order implements Serializable{

    private static final long serialVersionUID = 112L;

	private int id;
	private LocalDateTime orderTime;
	private LocalDateTime deliveryTime;
	private double price;
	private PaymentType paymentType;
	private DeliveryType deliveryType;
	private OrderState state;
	
	private Set<String> dishes;

	public Order() {}

	public Order(int id, LocalDateTime orderTime, LocalDateTime deliveryTime, double price, PaymentType paymentType,
			DeliveryType deliveryType, OrderState state, Set<String> dishes) {

		this.id = id;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.price = price;
		this.paymentType = paymentType;
		this.deliveryType = deliveryType;
		this.state = state;
		this.dishes = dishes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(LocalDateTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Set<String> getDishes() {
		return dishes;
	}

	public void setDishes(Set<String> dishes) {
		this.dishes = dishes;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", deliveryTime=" + deliveryTime + ", price=" + price
				+ ", paymentType=" + paymentType + ", deliveryType=" + deliveryType + ", state=" + state + ", dishes="
				+ dishes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryTime == null) ? 0 : deliveryTime.hashCode());
		result = prime * result + ((deliveryType == null) ? 0 : deliveryType.hashCode());
		result = prime * result + ((dishes == null) ? 0 : dishes.hashCode());
		result = prime * result + id;
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Order other = (Order) obj;
		if (deliveryTime == null) {
			if (other.deliveryTime != null)
				return false;
		} else if (!deliveryTime.equals(other.deliveryTime))
			return false;
		if (deliveryType != other.deliveryType)
			return false;
		if (dishes == null) {
			if (other.dishes != null)
				return false;
		} else if (!dishes.equals(other.dishes))
			return false;
		if (id != other.id)
			return false;
		if (orderTime == null) {
			if (other.orderTime != null)
				return false;
		} else if (!orderTime.equals(other.orderTime))
			return false;
		if (paymentType != other.paymentType)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	
	
}
