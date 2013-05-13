package edu.thu.component.jst;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.JSONResult;
import edu.thu.bean.jst.CommentData;
import edu.thu.bean.jst.CommentWrapper;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.IMessageComponent;
import edu.thu.util.CommonUtil;

public class MessageComponent extends AbstractComponent implements IMessageComponent {

	@Override
	public void message(JSONResult xmlResult, HashMap<String, String> paramMap) {
		int userId = Integer.parseInt(paramMap.get("userId"));
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			String sql = "select TS.PERSON_NAME AS \"user\", TJ.CONTENT AS \"comment\" from (select TI.SEND, TI.CONTENT from T_USER_MESSAGE TUM join T_INFORMATION TI on TUM.Information_Id=TI.Id where TUM.USER_ID="+userId
					+ ") TJ, T_USER TU, T_STU TS where TU.ID=TJ.SEND AND TU.STU_ID=TS.ID";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<CommentData> commentList = new ArrayList<CommentData>();
			CommentData comment;
			while (rs.next()) {
				comment = new CommentData();
				comment.setUser(rs.getString("user"));
				comment.setComment(rs.getString("comment"));
				commentList.add(comment);
			}
			onResultSucceed(xmlResult, "获取评论成功", new CommentWrapper(commentList).buildJsonContent());
			if (commentList.size() < 1) {
				onResultFail(xmlResult, "没有可以获取的评论", null);
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "网络或者服务器故障", null);
		}
	}

}
