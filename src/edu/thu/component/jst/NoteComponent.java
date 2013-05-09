package edu.thu.component.jst;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.JSONResult;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.INoteComponent;
import edu.thu.util.CommonUtil;

public class NoteComponent extends AbstractComponent implements INoteComponent {

	@Override
	public void note(JSONResult xmlResult, HashMap<String, String> paramMap) {
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			String date = null;
			Long id;
			String sql = "select sysdate from dual";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			if (rs.next()) {
				date = rs.getString("SYSDATE");
			}			
			rs.close();
			statement.close();
			
			sql = "select id from T_COURSE_COMMENT t order by t.id desc";
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				id = rs.getLong("id")+1;
			} else {
				id = (long) 250;
			}
			rs.close();
			statement.close();
			
			sql = "INSERT INTO T_COURSE_COMMENT VALUES ("+id+", '"+paramMap.get("content")+"', to_date ('"+ date+ "', 'YYYY-MM-DD HH24:MI:SS'), "+paramMap.get("userId")+", "+paramMap.get("courseId")+", '', '')";
			System.out.println(sql);
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			rs.close();
			statement.close();
			onResultSucceed(xmlResult, "评论成功", "{\"id\":"+id+"\",\"date\":\""+date+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "网络或者服务器故障", null);
		}
	}

}
