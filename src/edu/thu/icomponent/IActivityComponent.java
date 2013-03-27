package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

public interface IActivityComponent {

	void activity(XmlResult xmlResult, HashMap<String, String> paramMap);

}
