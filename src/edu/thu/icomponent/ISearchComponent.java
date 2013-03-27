package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

public interface ISearchComponent {

	void search(XmlResult xmlResult, HashMap<String, String> paramMap);

}
