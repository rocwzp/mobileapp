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
import edu.thu.icomponent.IFavoriteComponent;
import edu.thu.icomponent.INoteComponent;
import edu.thu.icomponent.IProblemComponent;
import edu.thu.service.StoreService;

public class StoreServlet extends HttpServlet {

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
		String action = request.getParameter("action");
		String repository = request.getParameter("repository");

		StoreService storeService = new StoreService();
		try {
			storeService.favoriteComponent = (IFavoriteComponent) Class.forName(
					"edu.thu.component." + repository.toLowerCase() + ".FavoriteComponent").newInstance();
			storeService.noteComponent = (INoteComponent) Class.forName(
					"edu.thu.component." + repository.toLowerCase() + ".NoteComponent").newInstance();
			storeService.problemComponent = (IProblemComponent) Class.forName(
					"edu.thu.component." + repository.toLowerCase() + ".ProblemComponent").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONResult result = new JSONResult();
		HashMap<String, String> paramMap = new HashMap<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			if (!key.equalsIgnoreCase("repository") && !key.equalsIgnoreCase("action")) {
				paramMap.put(key, request.getParameter(key));
			}
		}

		try {
			Method method = storeService.getClass().getMethod(action, JSONResult.class, HashMap.class);
			method.invoke(storeService, result, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result.buildJsonContent());
		BufferedWriter out = null;
		out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
		out.write(result.buildJsonContent());
		out.flush();
		out.close();
	}

}
