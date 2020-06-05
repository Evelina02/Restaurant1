package by.restaurant.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Review implements Serializable{

    private static final long serialVersionUID = 121L;

	private int id;
	private String body;
	private String time;
	private String userLogin;
	private boolean isDeleted;
	
	public Review() {}

	public Review(String body, String time) {
		super();
		this.body = body;
		this.time = time;
	}
	
	public Review(int id, String body, String time, String userLogin, boolean isDeleted) {
		super();
		this.id = id;
		this.body = body;
		this.time = time;
		this.userLogin = userLogin;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
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
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
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
		return "Review [id=" + id + ", body=" + body + ", time=" + time + ", userLogin=" + userLogin + ", isDeleted="
				+ isDeleted + "]";
	}





}
