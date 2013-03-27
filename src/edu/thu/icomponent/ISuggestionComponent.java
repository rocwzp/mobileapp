package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

public interface ISuggestionComponent {

	void suggestion(XmlResult xmlResult, HashMap<String, String> paramMap);

}
