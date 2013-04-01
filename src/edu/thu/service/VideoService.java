package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.JSONResult;
import edu.thu.icomponent.IVideoComponent;

public class VideoService {

	public IVideoComponent videoComponent;

	public void video(JSONResult xmlResult, HashMap<String, String> paramMap) {
		videoComponent.video(xmlResult, paramMap);
	}

}
