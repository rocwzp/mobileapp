package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

/**
 * ��Ϣ���ܲ����Ľӿ�
 */
public interface IMessageComponent {

	void message(XmlResult xmlResult, HashMap<String, String> paramMap);

}
