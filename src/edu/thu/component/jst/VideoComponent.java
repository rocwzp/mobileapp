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
import edu.thu.bean.jst.Course;
import edu.thu.bean.jst.CourseWrapper;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.IVideoComponent;
import edu.thu.util.CommonUtil;

public class VideoComponent extends AbstractComponent implements IVideoComponent {

	@Override
	public void video(JSONResult xmlResult, HashMap<String, String> paramMap) {
		int type = Integer.parseInt(paramMap.get("courseFor"));
		int userId = Integer.parseInt(paramMap.get("userId"));
		int groupId = Integer.parseInt(paramMap.get("groupId"));
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			String sql="";
			if(type == CommonUtil.FAVORITE_TYPE_STU)
				sql = "SELECT MC.ID AS \"id\", MC.CATID AS \"catid\", MC.TITLE AS \"title\", MC.THUMB AS \"thumb\", MC.TAGS AS \"tags\", MC.CREATED AS \"created\", MC.PV AS \"pv\", MC.BAOGAOREN AS \"baogaoren\", MC.WEIGHT AS \"weight\", MVS.STREAM AS \"videoUrl\" "
						+ " FROM jst_study.M_COURSE MC, jst_study.M_VIDEOS MVS, jst_study.T_STU_COURSE TSC "						
						+ " WHERE TSC.USER_ID = "+ userId +" AND TSC.COURSE_ID = MC.ID AND MC.ID = MVS.COURSEID AND MC.MODELID=4 ORDER BY \"created\" DESC";
			else 
				sql = "SELECT MC.ID AS \"id\", MC.CATID AS \"catid\", MC.TITLE AS \"title\", MC.THUMB AS \"thumb\", MC.TAGS AS \"tags\", MC.CREATED AS \"created\", MC.PV AS \"pv\", MC.BAOGAOREN AS \"baogaoren\", MC.WEIGHT AS \"weight\", MVS.STREAM AS \"videoUrl\" "
						+ " FROM jst_study.M_COURSE MC, jst_study.M_VIDEOS MVS, jst_study.T_GROUP_COURSE_COMMEND TGC "
						+ " WHERE TGC.GROUP_ID = "+ groupId +" AND TSC.COURSE_ID = MC.ID AND MC.ID = MVS.COURSEID AND MC.MODELID=4 ORDER BY \"created\" DESC)";
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
				course.setUrl(rs.getString("videoUrl"));
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
