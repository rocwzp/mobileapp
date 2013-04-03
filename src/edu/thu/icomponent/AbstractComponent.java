package edu.thu.icomponent;

import edu.thu.bean.JSONResult;
import edu.thu.util.CommonUtil;

/**
 * ʵ�ֹ��ܲ����ܽӿڵ��࣬����ʵ�ֽӿڷ��������е�component���̳������</br> û�н�code����Ϊ����������Ϊ�����ϲ����η���code�ĺ���
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
