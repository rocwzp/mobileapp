package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.ILoginComponent;

public class AuthService {

	public ILoginComponent loginComponent;

	public void login(XmlResult xmlResult, HashMap<String, String> paramMap) {
		loginComponent.login(xmlResult, paramMap);
	}

}
