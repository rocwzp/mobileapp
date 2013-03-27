package edu.thu.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.ISearchComponent;
import edu.thu.service.SearchService;

public class SearchServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String repository = request.getParameter("repository");

		SearchService searchService = new SearchService();
		try {
			searchService.searchComponent = (ISearchComponent) Class.forName(
					"edu.thu.component." + repository.toLowerCase() + ".SearchComponent").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		XmlResult xmlResult = new XmlResult();
		HashMap<String, String> paramMap = new HashMap<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			if (!key.equalsIgnoreCase("repository") && !key.equalsIgnoreCase("action")) {
				paramMap.put(key, request.getParameter(key));
			}
		}

		try {
			Method method = searchService.getClass().getMethod(action, XmlResult.class, HashMap.class);
			method.invoke(searchService, xmlResult, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		BufferedWriter out = null;
		out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
		out.write(xmlResult.buildXmlContent());
		out.flush();
		out.close();
	}

}
