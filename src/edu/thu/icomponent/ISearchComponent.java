package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

public interface ISearchComponent {

	void search(JSONResult xmlResult, HashMap<String, String> paramMap);

}
