package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

/**
 * ��¼���ܲ����Ľӿ�
 */
public interface ILoginComponent {

	void login(XmlResult xmlResult, HashMap<String, String> paramMap);

}
