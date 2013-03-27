package edu.thu.icomponent;

import edu.thu.bean.XmlResult;

/**
 * �����ܲ������ܽӿ�
 */
public interface IComponent {

	/**
	 * ���سɹ�
	 */
	void onResultSucceed(XmlResult xmlResult, String message, String content);

	/**
	 * ����ʧ��
	 */
	void onResultFail(XmlResult xmlResult, String message, String content);

	/**
	 * �����쳣
	 */
	void onResultException(XmlResult xmlResult, String message, String content);

}
