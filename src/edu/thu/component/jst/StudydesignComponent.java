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
import edu.thu.icomponent.IStudydesignComponent;
import edu.thu.util.CommonUtil;

public class StudydesignComponent extends AbstractComponent implements IStudydesignComponent {

	@Override
	public void studydesign(JSONResult xmlResult, HashMap<String, String> paramMap) {
		int userId = Integer.parseInt(paramMap.get("userId"));
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			String sql = "select TCC.COUTEXT AS \"comment\", MC.TITLE AS \"user\" "+ 
						 "from jst_study.M_COURSE MC, jst_study.T_COURSE_COMMENT TCC where TCC.USER_ID= " + userId + 
						 " AND TCC.COURSE_ID = MC.ID";
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
			onResultSucceed(xmlResult, "��ȡ���۳ɹ�", new CommentWrapper(commentList).buildJsonContent());
			if (commentList.size() < 1) {
				onResultFail(xmlResult, "û�п��Ի�ȡ������", null);
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "������߷���������", null);
		}
	}

}
