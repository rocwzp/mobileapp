package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.JSONResult;
import edu.thu.icomponent.IMessageComponent;
import edu.thu.icomponent.IVideoComponent;

public class MessageService {

	public IMessageComponent messageComponent;

	public void message(JSONResult xmlResult, HashMap<String, String> paramMap) {
		messageComponent.message(xmlResult, paramMap);
	}

}
