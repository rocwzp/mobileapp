package edu.thu.component.jst;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.JSONResult;
import edu.thu.bean.jst.UserData;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.ILoginComponent;
import edu.thu.util.CommonUtil;

public class LoginComponent extends AbstractComponent implements ILoginComponent {

	@Override
	public void login(JSONResult xmlResult, HashMap<String, String> paramMap) {
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			String sql = "select TU.ID AS \"userId\", TS.ID AS \"stuid\", TU.GROUP_ID AS \"groupid\", TG.NAME AS \"group_name\", TU.LOGIN_ID AS \"login_id\", TU.PASSWORD AS \"password\", TS.PERSON_NAME AS \"personName\", TS.SEX AS \"sex\", TS.MOBILE AS \"mobile\", TS.TEL AS \"telephone\", TS.EMAIL AS \"email\", TS.ADDRESS AS \"address\" "
					+ "from jst_study.T_USER TU, jst_study.T_STU TS, jst_study.T_GROUP TG where TU.LOGIN_ID='"
					+ paramMap.get("userId")
					+ "' AND TU.STU_ID = TS.ID AND TU.GROUP_ID = TG.ID";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			String pass = paramMap.get("password");
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte b[] = md.digest();
			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			pass = buf.toString();
			if (rs.next()) {
				 ////////
				 //TODO commit UserData class here
				 if (rs.getString("password").equalsIgnoreCase(pass)) {
				 onResultSucceed(xmlResult, "µÇÂ½³É¹¦",
				 new UserData(rs.getLong("userId"), rs.getLong("stuid"), rs.getLong("groupid"), rs.getString("group_name"), rs.getString("login_id"),
				 rs.getString("personName"), rs.getString("sex"), rs.getString("mobile"),
				 rs.getString("telephone"), rs.getString("email"), rs.getString("address")).buildJsonContent());
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
