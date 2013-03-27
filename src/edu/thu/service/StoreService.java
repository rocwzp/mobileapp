package edu.thu.service;

import java.util.HashMap;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.IFavoriteComponent;
import edu.thu.icomponent.INoteComponent;
import edu.thu.icomponent.IProblemComponent;

public class StoreService {

	public IFavoriteComponent favoriteComponent;
	public INoteComponent noteComponent;
	public IProblemComponent problemComponent;

	public void favorite(XmlResult xmlResult, HashMap<String, String> paramMap) {
		favoriteComponent.favorite(xmlResult, paramMap);
	}

	public void note(XmlResult xmlResult, HashMap<String, String> paramMap) {
		noteComponent.note(xmlResult, paramMap);
	}

	public void problem(XmlResult xmlResult, HashMap<String, String> paramMap) {
		problemComponent.problem(xmlResult, paramMap);
	}

}
