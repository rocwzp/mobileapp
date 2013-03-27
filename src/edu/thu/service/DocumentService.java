package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.IActivityComponent;
import edu.thu.icomponent.IDownloadComponent;
import edu.thu.icomponent.IStudydesignComponent;
import edu.thu.icomponent.ISuggestionComponent;

public class DocumentService {

	public ISuggestionComponent suggestionComponent;
	public IStudydesignComponent studydesignComponent;
	public IActivityComponent activityComponent;
	public IDownloadComponent downloadComponent;

	public void suggestion(XmlResult xmlResult, HashMap<String, String> paramMap) {
		suggestionComponent.suggestion(xmlResult, paramMap);
	}

	public void studydesign(XmlResult xmlResult, HashMap<String, String> paramMap) {
		studydesignComponent.studydesign(xmlResult, paramMap);
	}

	public void activity(XmlResult xmlResult, HashMap<String, String> paramMap) {
		activityComponent.activity(xmlResult, paramMap);
	}

	public void download(XmlResult xmlResult, HashMap<String, String> paramMap) {
		downloadComponent.download(xmlResult, paramMap);
	}

}
