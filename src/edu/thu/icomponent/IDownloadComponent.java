package edu.thu.icomponent;

import java.util.HashMap;

import edu.thu.bean.JSONResult;

public interface IDownloadComponent {

	void download(JSONResult xmlResult, HashMap<String, String> paramMap);

}
