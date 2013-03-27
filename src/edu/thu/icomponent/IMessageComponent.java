package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

/**
 * 消息功能部件的接口
 */
public interface IMessageComponent {

	void message(XmlResult xmlResult, HashMap<String, String> paramMap);

}
