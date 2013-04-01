package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

public interface ISuggestionComponent {

	void suggestion(JSONResult xmlResult, HashMap<String, String> paramMap);

}
