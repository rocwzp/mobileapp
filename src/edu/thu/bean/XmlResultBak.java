package edu.thu.bean;

import edu.thu.util.CommonUtil;

/**
 * HTTP请求返回的xml数据结果的封装类
 */
public class XmlResultBak {

	private int code;// result code
	private String message;// result message
	private String content;// result content

	public XmlResultBak() {
		code = CommonUtil.RESULT_CODE_DEFAULT;
		message = CommonUtil.RESULT_MESSAGE_DEFAULT;
		content = CommonUtil.RESULT_CONTENT_DEFAULT;
	}

	public String buildXmlContent() {
		StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		result.append("<result>\n");
		result.append("<code>" + code + "</code>\n");
		result.append("<message>" + message + "</message>\n");
		result.append("<content>" + content + "</content>\n");
		result.append("</result>");
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
