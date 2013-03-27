package edu.thu.icomponent;

import edu.thu.bean.XmlResult;
import edu.thu.util.CommonUtil;

/**
 * ʵ�ֹ��ܲ����ܽӿڵ��࣬����ʵ�ֽӿڷ��������е�component���̳������</br> û�н�code����Ϊ����������Ϊ�����ϲ����η���code�ĺ���
 */
public class AbstractComponent implements IComponent {

	@Override
	public void onResultSucceed(XmlResult xmlResult, String message, String content) {
		xmlResult.setCode(CommonUtil.RESULT_CODE_SUCCEED);
		if (message != null) {
			xmlResult.setMessage(message);
		}
		if (content != null) {
			xmlResult.setContent(content);
		}
	}

	@Override
	public void onResultFail(XmlResult xmlResult, String message, String content) {
		xmlResult.setCode(CommonUtil.RESULT_CODE_FAIL);
		if (message != null) {
			xmlResult.setMessage(message);
		}
		if (content != null) {
			xmlResult.setContent(content);
		}
	}

	@Override
	public void onResultException(XmlResult xmlResult, String message, String content) {
		xmlResult.setCode(CommonUtil.RESULT_CODE_EXCEPTION);
		if (message != null) {
			xmlResult.setMessage(message);
		}
		if (content != null) {
			xmlResult.setContent(content);
		}
	}

}
