package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

/**
 * 登录功能部件的接口
 */
public interface ILoginComponent {

	void login(JSONResult xmlResult, HashMap<String, String> paramMap);

}
