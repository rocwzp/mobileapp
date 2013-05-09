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
import edu.thu.icomponent.IActivityComponent;
import edu.thu.util.CommonUtil;

public class ActivityComponent extends AbstractComponent implements IActivityComponent {

	@Override
	public void activity(JSONResult xmlResult, HashMap<String, String> paramMap) {
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			String sql = "select TCC.COUTEXT AS \"comment\", TS.PERSON_NAME AS \"user\" "+ 
						 "from jst_study.T_USER TU, jst_study.T_STU TS, jst_study.T_COURSE_COMMENT TCC where TCC.COURSE_ID='" + paramMap.get("courseId") + 
						 "' AND TCC.USER_ID = TU.ID AND TU.STU_ID = TS.ID";
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
