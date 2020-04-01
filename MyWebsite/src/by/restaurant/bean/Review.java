package by.restaurant.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable{

    private static final long serialVersionUID = 121L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + id;
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result + ((reviewTime == null) ? 0 : reviewTime.hashCode());
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
		Review other = (Review) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (id != other.id)
			return false;
		if (isDeleted != other.isDeleted)
			return false;
		if (reviewTime == null) {
			if (other.reviewTime != null)
				return false;
		} else if (!reviewTime.equals(other.reviewTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", body=" + body + ", reviewTime=" + reviewTime + ", isDeleted=" + isDeleted + "]";
	}
	
	
}
