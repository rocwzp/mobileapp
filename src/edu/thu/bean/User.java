package edu.thu.bean;

/**
 * �û���
 * 
 * @author hujiawei
 * 
 */
public class User {

	private String userId;
	private String userName;

	public User(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	// build json content
	public String buildJsonContent() {
		return "{\"userId\":\"" + userId + "\",\"userName\":\"" + userName + "\"}";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
