package edu.thu.bean.jst;

import java.util.List;

public class CourseWrapper {
	private List<Course> courseList;

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
