package edu.thu.bean.des;

import java.io.Serializable;

/**
 * øŒ≥Ã¿‡
 * 
 * @author hujiawei
 * 
 */
public class Course implements Serializable {

	private static final long serialVersionUID = -3573192440808294842L;
	private long id;
	private String name;
	private String desc;
	private String userId;
	private String creator;
	private String createDate;
	private String teacherDesc;
	private long count;

	public Course() {

	}

	public Course(long id, String name, String desc, String userId, String creator, String createDate, String teacherDesc, long count) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.userId = userId;
		this.creator = creator;
		this.createDate = createDate;
		this.teacherDesc = teacherDesc;
		this.count = count;
	}

	// build json content
	public String buildJsonContent() {
		return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"userId\":\"" + userId + "\",\"creator\":\"" + creator + "\",\"desc\":\""
				+ desc + "\",\"teacherDesc\":\"" + teacherDesc + "\",\"createDate\":\"" + createDate + "\",\"count\":" + count + "}";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTeacherDesc() {
		return teacherDesc;
	}

	public void setTeacherDesc(String teacherDesc) {
		this.teacherDesc = teacherDesc;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
