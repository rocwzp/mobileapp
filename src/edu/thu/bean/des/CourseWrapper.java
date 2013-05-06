package edu.thu.bean.des;

import java.io.Serializable;
import java.util.List;

/**
 * 课程列表的包装类
 * 
 * @author hujiawei
 * 
 */

public class CourseWrapper implements Serializable {

	private static final long serialVersionUID = 5784396349159493301L;
	private List<Course> courseList;

	public CourseWrapper() {
	}

	public CourseWrapper(List<Course> courseList) {
		this.courseList = courseList;
	}

	// build json content
	public String buildJsonContent() {
		StringBuffer buffer = new StringBuffer("[");
		for (int i = 0; i < courseList.size(); i++) {
			if (i != 0) {
				buffer.append(",");
			}
			buffer.append(courseList.get(i).buildJsonContent());
		}
		buffer.append("]");
		return buffer.toString();
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

}
