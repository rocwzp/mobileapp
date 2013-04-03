package edu.thu.bean;

import edu.thu.util.CommonUtil;

/**
 * HTTP请求返回的json数据结果的封装类 V0.1
 */
public class JSONResult {

	private int code;// result code
	private String message;// result message
	private String content;// result content

	public JSONResult() {
		code = CommonUtil.RESULT_CODE_DEFAULT;
		message = CommonUtil.RESULT_MESSAGE_DEFAULT;
		content = CommonUtil.RESULT_CONTENT_DEFAULT;
	}

	public String buildJsonContent() {
		if (content == null) {
			System.out.println("content is null");
		}
		StringBuffer result = new StringBuffer();
		// {"code":0/-1/1,"message":"message","content":{content}}
		result.append("{\"code\":").append(code).append(",\"message\":\"").append(message).append("\",\"content\":")
				.append(content).append("}");
		return result.toString();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
