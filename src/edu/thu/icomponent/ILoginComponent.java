package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

/**
 * ��¼���ܲ����Ľӿ�
 */
public interface ILoginComponent {

	void login(JSONResult xmlResult, HashMap<String, String> paramMap);

}
