package edu.thu.icomponent;

import edu.thu.bean.JSONResult;
import edu.thu.util.CommonUtil;

/**
 * 实现功能部件总接口的类，基本实现接口方法，所有的component都继承这个类</br> 没有将code设置为方法参数是为了向上层屏蔽返回code的含义
 */
public class AbstractComponent implements IComponent {

	@Override
	public void onResultSucceed(JSONResult xmlResult, String message, String content) {
		xmlResult.setCode(CommonUtil.RESULT_CODE_SUCCEED);
		setMessageContent(xmlResult, message, content);
	}

	@Override
	public void onResultFail(JSONResult xmlResult, String message, String content) {
		xmlResult.setCode(CommonUtil.RESULT_CODE_FAIL);
		setMessageContent(xmlResult, message, content);
	}

	@Override
	public void onResultException(JSONResult xmlResult, String message, String content) {
		xmlResult.setCode(CommonUtil.RESULT_CODE_EXCEPTION);
		setMessageContent(xmlResult, message, content);
	}
	
	private void setMessageContent(JSONResult xmlResult, String message, String content) {
		if (message != null) {
			xmlResult.setMessage(message);
		} else {
			xmlResult.setMessage(CommonUtil.RESULT_MESSAGE_DEFAULT);
		}
		if (content != null) {
			xmlResult.setContent(content);
		} else {
			xmlResult.setContent(CommonUtil.RESULT_CONTENT_DEFAULT);
		}
	}

}
