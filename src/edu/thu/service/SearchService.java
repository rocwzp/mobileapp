package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.JSONResult;
import edu.thu.icomponent.ISearchComponent;

public class SearchService {

	public ISearchComponent searchComponent;

	public void search(JSONResult xmlResult, HashMap<String, String> paramMap) {
		searchComponent.search(xmlResult, paramMap);
	}

}
