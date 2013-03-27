package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

/**
 * 登录功能部件的接口
 */
public interface ILoginComponent {

	void login(XmlResult xmlResult, HashMap<String, String> paramMap);

}
