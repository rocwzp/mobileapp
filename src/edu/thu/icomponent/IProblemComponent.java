package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.XmlResult;

public interface IProblemComponent {

	void problem(XmlResult xmlResult, HashMap<String, String> paramMap);

}
