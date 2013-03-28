package edu.thu.component.jst;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.ILoginComponent;

public class LoginComponent extends AbstractComponent implements ILoginComponent {

	@Override
	public void login(XmlResult xmlResult, HashMap<String, String> paramMap) {
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jst_study");
			Connection connection = dataSource.getConnection();
			String sql = "select * from jst_study.T_USER where login_id='" + paramMap.get("userId") + "'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			String pass=paramMap.get("password");			
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(pass.getBytes()); 
			byte b[] = md.digest(); 
			int i; 

			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if(i<0) i+= 256; 
				if(i<16) 
					buf.append("0"); 
				buf.append(Integer.toHexString(i)); 
			} 
			pass=buf.toString();
			if (rs.next()) {
				if (rs.getString("password").equalsIgnoreCase(pass)) {
					onResultSucceed(xmlResult, "µÇÂ½³É¹¦", null);
				} else {
					onResultFail(xmlResult, "µÇÂ¼Ê§°Ü", null);
				}
			} else {
				onResultFail(xmlResult, "µÇÂ¼Ê§°Ü", null);
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "ÍøÂç»òÕß·þÎñÆ÷¹ÊÕÏ", null);
		}
	}

}
