package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.ISearchComponent;

public class SearchService {

	public ISearchComponent searchComponent;

	public void search(XmlResult xmlResult, HashMap<String, String> paramMap) {
		searchComponent.search(xmlResult, paramMap);
	}

}
