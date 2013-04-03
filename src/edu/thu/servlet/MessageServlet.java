package edu.thu.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.thu.bean.JSONResult;
import edu.thu.icomponent.ILoginComponent;
import edu.thu.icomponent.IMessageComponent;
import edu.thu.service.AuthService;
import edu.thu.service.MessageService;

public class MessageServlet extends HttpServlet {

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

		MessageService messageService = new MessageService();
		// using Java reflection to set service component
		try {
			messageService.messageComponent = (IMessageComponent) Class.forName(
					"edu.thu.component." + repository.toLowerCase() + ".MessageComponent").newInstance();
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

		// using Java reflection to invoke component method
		try {
			Method method = messageService.getClass().getMethod(action, JSONResult.class, HashMap.class);
			method.invoke(messageService, result, paramMap);
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
