package edu.thu.icomponent;

import edu.thu.bean.JSONResult;

/**
 * �����ܲ������ܽӿ�
 */
public interface IComponent {

	/**
	 * ���سɹ�
	 */
	void onResultSucceed(JSONResult xmlResult, String message, String content);

	/**
	 * ����ʧ��
	 */
	void onResultFail(JSONResult xmlResult, String message, String content);

	/**
	 * �����쳣
	 */
	void onResultException(JSONResult xmlResult, String message, String content);

}
