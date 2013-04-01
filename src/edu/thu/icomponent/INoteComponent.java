package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

public interface INoteComponent {

	void note(JSONResult xmlResult, HashMap<String, String> paramMap);

}
