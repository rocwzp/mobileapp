package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

public interface IProblemComponent {

	void problem(JSONResult xmlResult, HashMap<String, String> paramMap);

}
