package edu.thu.icomponent;

import edu.thu.bean.JSONResult;

/**
 * 服务功能部件的总接口
 */
public interface IComponent {

	/**
	 * 返回成功
	 */
	void onResultSucceed(JSONResult xmlResult, String message, String content);

	/**
	 * 返回失败
	 */
	void onResultFail(JSONResult xmlResult, String message, String content);

	/**
	 * 出现异常
	 */
	void onResultException(JSONResult xmlResult, String message, String content);

}
