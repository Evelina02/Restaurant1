package by.restaurant.bean;

import java.time.LocalDateTime;

import by.restaurant.bean.util.DeliveryType;
import by.restaurant.bean.util.PaymentType;
import by.restaurant.bean.util.State;

public class Order {

	private int id;
	private LocalDateTime orderTime;
	private LocalDateTime deliveryTime;
	private double price;
	private PaymentType paymentType;
	private DeliveryType deliveryType;
	private State state;
	
}
