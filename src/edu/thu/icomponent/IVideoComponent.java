package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

/**
 * 视频功能部件的接口
 */
public interface IVideoComponent {

	void video(XmlResult xmlResult, HashMap<String, String> paramMap);

}
