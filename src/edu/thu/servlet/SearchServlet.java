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

import edu.thu.bean.JSONResult;
import edu.thu.icomponent.ISearchComponent;
import edu.thu.service.SearchService;
import edu.thu.util.CommonUtil;

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

		System.out.println(request.getRequestURL().toString());
//		String action = request.getParameter("action");
//		String repository = request.getParameter("repository");
//
//		SearchService searchService = new SearchService();
//		try {
//			searchService.searchComponent = (ISearchComponent) Class.forName(
//					"edu.thu.component." + repository.toLowerCase() + ".SearchComponent").newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		JSONResult result = new JSONResult();
//		HashMap<String, String> paramMap = new HashMap<String, String>();
//		Enumeration<String> enumeration = request.getParameterNames();
//		while (enumeration.hasMoreElements()) {
//			String key = (String) enumeration.nextElement();
//			if (!key.equalsIgnoreCase("repository") && !key.equalsIgnoreCase("action")) {
//				paramMap.put(key, request.getParameter(key));
//			}
//		}
//
//		try {
//			Method method = searchService.getClass().getMethod(action, JSONResult.class, HashMap.class);
//			method.invoke(searchService, result, paramMap);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// db not available
		 JSONResult result = new JSONResult();
		 result.setCode(CommonUtil.RESULT_CODE_SUCCEED);
		 result.setMessage("«Î«Û≥…π¶");
		 if (Integer.parseInt(request.getParameter("searchType")) == CommonUtil.SEARCH_TYPE_COURSE) {
			 result.setContent(getInitParameter("jsoncontent_course"));
		}else {
//			result.setContent(getInitParameter("jsoncontent_resource"));
			String action = request.getParameter("action");
			String repository = request.getParameter("repository");

			SearchService searchService = new SearchService();
			try {
				searchService.searchComponent = (ISearchComponent) Class.forName(
						"edu.thu.component." + repository.toLowerCase() + ".SearchComponent").newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

//			JSONResult result = new JSONResult();
			HashMap<String, String> paramMap = new HashMap<String, String>();
			Enumeration<String> enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				if (!key.equalsIgnoreCase("repository") && !key.equalsIgnoreCase("action")) {
					paramMap.put(key, request.getParameter(key));
				}
			}

			try {
				Method method = searchService.getClass().getMethod(action, JSONResult.class, HashMap.class);
				method.invoke(searchService, result, paramMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println(result.buildJsonContent());
		BufferedWriter out = null;
		out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
		out.write(result.buildJsonContent());
		out.flush();
		out.close();
	}

}
