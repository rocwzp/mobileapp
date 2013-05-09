package edu.thu.bean.jst;

public class CommentData {
	private String user;
	private String comment;
	
	public CommentData(){};
	public CommentData(String user, String comment) {
		this.user = user;
		this.comment = comment;
	}
	
	public String buildJsonContent() {
		return "{\"user\":\"" + user + "\",\"comment\":\"" + comment + "\"}";
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
