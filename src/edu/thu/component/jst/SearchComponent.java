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
import edu.thu.icomponent.ISearchComponent;

public class SearchComponent extends AbstractComponent implements ISearchComponent {

	@Override
	public void search(JSONResult xmlResult, HashMap<String, String> paramMap) {
		InitialContext context;
		String catalog = paramMap.get("searchType");
		String[] types = { "", "经", "政", "文", "党", "社", "国", "其" };
		if(catalog.equalsIgnoreCase("0")) catalog=types[0];
		else if(catalog.equalsIgnoreCase("1")) catalog=types[1];
		else if(catalog.equalsIgnoreCase("2")) catalog=types[2];
		else if(catalog.equalsIgnoreCase("3")) catalog=types[3];
		else if(catalog.equalsIgnoreCase("4")) catalog=types[4];
		else if(catalog.equalsIgnoreCase("5")) catalog=types[5];
		else if(catalog.equalsIgnoreCase("6")) catalog=types[6];
		else if(catalog.equalsIgnoreCase("7")) catalog=types[7];
		String keyword = paramMap.get("keyword");
		String count = paramMap.get("count");
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jst_study");
			Connection connection = dataSource.getConnection();
			String sql = "SELECT * FROM (SELECT MC.ID AS \"id\", MC.CATID AS \"catid\", MC.TITLE AS \"title\", MC.THUMB AS \"thumb\", MC.TAGS AS \"tags\", MC.CREATED AS \"created\", MC.PV AS \"pv\", MC.BAOGAOREN AS \"baogaoren\", MC.WEIGHT AS \"weight\", MVS.STREAM AS \"videoUrl\" "
					+ " FROM jst_study.M_COURSE MC, jst_study.M_VIDEOS MVS, jst_study.M_CATEGORY MCAT "						
					+ " WHERE MC.ID = MVS.COURSEID AND MCAT.CATID = MC.CATID AND MC.MODELID=4 "
					+ " AND MCAT.NAME like '%"+catalog+"%' AND title like '%"+ keyword + "%' ORDER BY \"created\" DESC)"
					+ " WHERE ROWNUM <= "
					+ count;
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
			onResultException(xmlResult, "网络或者服务器故障", null);
		}
	}

}
