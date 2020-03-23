package by.restaurant.bean;

import java.time.LocalDateTime;

public class Review {

	private int id;
	private String body;
	private LocalDateTime reviewTime;
	private boolean isDeleted;
	
	
	public Review(int id, String body, LocalDateTime reviewTime, boolean isDeleted) {
		super();
		this.id = id;
		this.body = body;
		this.reviewTime = reviewTime;
		this.isDeleted = isDeleted;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public LocalDateTime getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(LocalDateTime reviewTime) {
		this.reviewTime = reviewTime;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
