package edu.thu.component.jst;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.Course;
import edu.thu.bean.CourseWrapper;
import edu.thu.bean.JSONResult;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.ISuggestionComponent;
import edu.thu.util.CommonUtil;

public class SuggestionComponent extends AbstractComponent implements ISuggestionComponent {

	@Override
	public void suggestion(JSONResult xmlResult, HashMap<String, String> paramMap) {
		int count;
		if (paramMap.get("count") != null) {
			count = Integer.parseInt(paramMap.get("count"));
		} else {
			count = CommonUtil.SUGGESTION_DEFAULT_COUNT;
		}
		int type = Integer.parseInt(paramMap.get("suggestionType"));
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			String sql="";
			if(type == CommonUtil.SUGGESTION_TYPE_HOT)
				sql = "SELECT MC.ID AS \"id\", MC.CATID AS \"catid\", MC.TITLE AS \"title\", MC.THUMB AS \"thumb\", MC.TAGS AS \"tags\", MC.CREATED AS \"created\", MC.PV AS \"pv\", MC.BAOGAOREN AS \"baogaoren\", MC.WEIGHT AS \"weight\", MV.DESCRIPTION AS \"description\" "
						+ " FROM jst_study.M_COURSE MC, jst_study.M_VIDEO MV "
						+ "WHERE ROWNUM <= "
						+ count
						+ " AND MC.ID = MV.COURSEID ORDER BY \"pv\" DESC";
			else 
				sql = "SELECT MC.ID AS \"id\", MC.CATID AS \"catid\", MC.TITLE AS \"title\", MC.THUMB AS \"thumb\", MC.TAGS AS \"tags\", MC.CREATED AS \"created\", MC.PV AS \"pv\", MC.BAOGAOREN AS \"baogaoren\", MC.WEIGHT AS \"weight\", MV.DESCRIPTION AS \"description\" "
						+ " FROM jst_study.M_COURSE MC, jst_study.M_VIDEO MV "
						+ "WHERE ROWNUM <= "
						+ count
						+ " AND MC.ID = MV.COURSEID ORDER BY \"created\" DESC";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<Course> courseList = new ArrayList<Course>();
			Course course;
			while (rs.next()) {
				course = new Course();
				course.setId(rs.getLong("id"));
				course.setCatid(rs.getLong("catid"));
				course.setTitle(rs.getString("title"));
				course.setThumb(rs.getString("thumb"));
				course.setTags(rs.getString("tags"));
				course.setCreated(rs.getLong("created"));
				course.setPv(rs.getLong("pv"));
				course.setBaogaoren(rs.getString("baogaoren"));
				course.setWeight(rs.getLong("weight"));
				course.setDescription(rs.getString("description"));
				courseList.add(course);
			}
			onResultSucceed(xmlResult, "推荐成功", new CourseWrapper(courseList).buildJsonContent());
			if (courseList.size() < 1) {
				onResultFail(xmlResult, "没有可以推荐的内容", null);
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "网络或者服务器故障", null);
		}
	}

}
