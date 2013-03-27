package edu.thu.icomponent;

import edu.thu.bean.XmlResult;
import edu.thu.util.CommonUtil;

/**
 * 实现功能部件总接口的类，基本实现接口方法，所有的component都继承这个类</br> 没有将code设置为方法参数是为了向上层屏蔽返回code的含义
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
