package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

/**
 * ��Ϣ���ܲ����Ľӿ�
 */
public interface IMessageComponent {

	void message(JSONResult xmlResult, HashMap<String, String> paramMap);

}
