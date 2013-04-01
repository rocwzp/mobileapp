package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

/**
 * 消息功能部件的接口
 */
public interface IMessageComponent {

	void message(JSONResult xmlResult, HashMap<String, String> paramMap);

}
