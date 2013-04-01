package edu.thu.component.des;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.JSONResult;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.ISearchComponent;

public class SearchComponent extends AbstractComponent implements ISearchComponent {

	@Override
	public void search(JSONResult xmlResult, HashMap<String, String> paramMap) {
		InitialContext context;
		String searchType = paramMap.get("searchType");
		String catalog = paramMap.get("catalog");
		String keyword = paramMap.get("keyword");
		String count = paramMap.get("count");
		String page = paramMap.get("page");
		StringBuffer resultContent = new StringBuffer();
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/design");
			Connection connection = dataSource.getConnection();
			String sql = "select * from portal.SYS_USER where userid='" + paramMap.get("userId") + "'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("userpass").equalsIgnoreCase(paramMap.get("password"))) {
				} else {
					onResultSucceed(xmlResult, "µÇÂ¼³É¹¦", null);
				}
			} else {
				onResultFail(xmlResult, "µÇÂ¼Ê§°Ü", null);
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			onResultException(xmlResult, "ÍøÂç»òÕß·þÎñÆ÷¹ÊÕÏ", null);
		}
	}

}
