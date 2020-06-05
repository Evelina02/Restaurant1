package by.restaurant.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import by.restaurant.bean.constant.DeliveryType;
import by.restaurant.bean.constant.OrderState;
import by.restaurant.bean.constant.PaymentType;

public class Order implements Serializable{

    private static final long serialVersionUID = 112L;

	private int id;
	private String orderTime;
	private String deliveryTime;
	private PaymentType paymentType;
	private DeliveryType deliveryType;
	private OrderState state;
	private String userLogin;
	private Basket basket;

	public Order() {}

	public Order(String orderTime, String deliveryTime, PaymentType paymentType, DeliveryType deliveryType,
			Basket basket, OrderState state) {
		
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.paymentType = paymentType;
		this.deliveryType = deliveryType;
		this.basket = basket;
		this.state = state;
	}

	public Order(int id, String orderTime, String deliveryTime, PaymentType paymentType, DeliveryType deliveryType,
			OrderState state, String userLogin, Basket basket) {
		super();
		this.id = id;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.paymentType = paymentType;
		this.deliveryType = deliveryType;
		this.state = state;
		this.userLogin = userLogin;
		this.basket = basket;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
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

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basket == null) ? 0 : basket.hashCode());
		result = prime * result + ((deliveryTime == null) ? 0 : deliveryTime.hashCode());
		result = prime * result + ((deliveryType == null) ? 0 : deliveryType.hashCode());
		result = prime * result + id;
		result = prime * result + ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
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
		if (basket == null) {
			if (other.basket != null)
				return false;
		} else if (!basket.equals(other.basket))
			return false;
		if (deliveryTime == null) {
			if (other.deliveryTime != null)
				return false;
		} else if (!deliveryTime.equals(other.deliveryTime))
			return false;
		if (deliveryType != other.deliveryType)
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
		if (state != other.state)
			return false;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderTime=" + orderTime + ", deliveryTime=" + deliveryTime + ", paymentType="
				+ paymentType + ", deliveryType=" + deliveryType + ", state=" + state + ", userLogin=" + userLogin
				+ ", basket=" + basket + "]";
	}


}
