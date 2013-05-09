package edu.thu.bean.jst;

import java.util.List;

public class CommentWrapper {
	private List<CommentData> commentList;

	public CommentWrapper(List<CommentData> commentList) {
		this.commentList = commentList;
	}

	// build json content
	public String buildJsonContent() {
		StringBuffer buffer = new StringBuffer("[");
		for (int i = 0; i < commentList.size(); i++) {
			if (i != 0) {
				buffer.append(",");
			}
			buffer.append(commentList.get(i).buildJsonContent());
		}
		buffer.append("]");
		return buffer.toString();
	}

	public List<CommentData> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentData> commentList) {
		this.commentList = commentList;
	}
}
