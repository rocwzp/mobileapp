package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.IVideoComponent;

public class VideoService {

	public IVideoComponent videoComponent;

	public void video(XmlResult xmlResult, HashMap<String, String> paramMap) {
		videoComponent.video(xmlResult, paramMap);
	}

}
