package edu.thu.component.des;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.JSONResult;
import edu.thu.bean.des.Course;
import edu.thu.bean.des.CourseWrapper;
import edu.thu.bean.des.Resource;
import edu.thu.bean.des.ResourceWrapper;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.ISearchComponent;
import edu.thu.util.CommonUtil;

/**
 * 资源搜索功能
 * 
 * TODO:暂时没有去实现真实的功能,暂时不考虑分页和类别
 * 
 * @author hujiawei
 * 
 */
public class SearchComponent extends AbstractComponent implements ISearchComponent {

	@Override
	public void search(JSONResult xmlResult, HashMap<String, String> paramMap) {
		// TODO 如果类型是课程，则返回所有的课程，如果是资源，则需要考虑page和count
		String sql = null;
		int type = CommonUtil.SEARCH_TYPE_RESOURCE;
		if (paramMap.get("searchType") != null && (type = Integer.parseInt(paramMap.get("searchType"))) == CommonUtil.SEARCH_TYPE_COURSE) {
			sql = " SELECT COURSE.COURSE_ID AS \"id\", COURSE.COURSE_NAME AS \"name\", COURSE.COURSE_DESC AS \"desc\", COURSE.COURSE_CREATOR AS \"creator\", COURSE.COURSE_CREATE_DATE AS \"createDate\", COURSE.COURSE_TEACHER_DESC AS \"teacherDesc\", COURSE.USER_ID AS \"userId\", COURSE.COURSE_COUNT AS \"count\" "
					+ "FROM DESIGN.COURSE ORDER BY \"createDate\" DESC ";
		} else {
			int count;
			if (paramMap.get("count") != null) {
				count = Integer.parseInt(paramMap.get("count"));
			} else {
				count = CommonUtil.SEARCH_DEFAULT_COUNT;
			}
			sql = "SELECT KL.KL_ID AS \"id\", KL.KL_NAME AS \"name\", KL.KL_AUTH AS \"author\", KL.KL_KEYWORD AS \"keyword\", KL.KL_DESC AS \"desc\", KL.KL_CATALOG_ID AS \"catalogId\", C.KL_CATALOG_NAME AS \"catalogName\", KL.LOM_PATH AS \"lompath\", KL.KL_FILENAME AS \"filename\", KL.KL_FILEEXT AS \"fileext\", KL.USER_ID AS \"userId\", KL.KL_STATUS AS \"status\" "
					+ " FROM DESIGN.KNOWLEDGE KL, DESIGN.KNOWLEDGE_CATALOG C "
					+ "WHERE ROWNUM <= "
					+ count
					+ " AND kl.KL_CATALOG_ID = c.KL_CATALOG_ID ORDER BY \"id\" DESC";
		}

		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_DES);
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			if (type == CommonUtil.SEARCH_TYPE_RESOURCE) {
				List<Resource> resourceList = new ArrayList<Resource>();
				Resource resource;
				while (rs.next()) {
					resource = new Resource();
					resource.setId(rs.getLong("id"));
					resource.setName(rs.getString("name"));
					resource.setUserId(rs.getString("userId"));
					resource.setAuthor(rs.getString("author"));
					resource.setCatalogId(rs.getLong("catalogId"));
					resource.setCatalogName(rs.getString("catalogName"));
					resource.setDesc(rs.getString("desc"));
					resource.setKeyword(rs.getString("keyword"));
					resource.setFilename(rs.getString("filename"));
					resource.setFileext(rs.getString("fileext"));
					resource.setStatus(rs.getInt("status"));
					resourceList.add(resource);
				}
				onResultSucceed(xmlResult, "搜索成功", new ResourceWrapper(resourceList).buildJsonContent());
				if (resourceList.size() < 1) {
					onResultFail(xmlResult, "没有搜索结果", null);
				}
			} else {
				List<Course> courseList = new ArrayList<Course>();
				Course course;
				while (rs.next()) {
					course = new Course();
					course.setId(rs.getLong("id"));
					course.setName(rs.getString("name"));
					course.setUserId(rs.getString("userId"));
					course.setCreator(rs.getString("creator"));
					course.setCreateDate(rs.getString("createDate"));
					course.setDesc(rs.getString("desc"));
//					course.setTeacherDesc(rs.getString("tearcherDesc"));//TODO
					course.setTeacherDesc("暂无教师简介");
					course.setCount(rs.getLong("count"));
					courseList.add(course);
				}
				onResultSucceed(xmlResult, "搜索成功", new CourseWrapper(courseList).buildJsonContent());
				if (courseList.size() < 1) {
					onResultFail(xmlResult, "没有搜索结果", null);
				}
			}

			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "网络或者服务器故障", null);
		}
	}

}
