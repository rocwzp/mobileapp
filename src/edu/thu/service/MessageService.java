package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.IMessageComponent;
import edu.thu.icomponent.IVideoComponent;

public class MessageService {

	public IMessageComponent messageComponent;

	public void message(XmlResult xmlResult, HashMap<String, String> paramMap) {
		messageComponent.message(xmlResult, paramMap);
	}

}
