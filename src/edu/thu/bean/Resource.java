package edu.thu.bean;

import java.io.Serializable;

/**
 * ×ÊÔ´Àà
 * 
 * @author hujiawei
 * 
 */
public class Resource implements Serializable {

	private static final long serialVersionUID = 3706071648466093655L;
	private long id;//
	private long catalogId;// catalog id may be null
	private String catalogName;
	private String name;
	private String desc;// may be null
	private String keyword;
	private String author;
	private String userId;
	private String filename;
	private String fileext;
	private String lompath;// may be null
	private int status;
	
	public Resource(){
		
	}

	public Resource(long id, long catalogId, String catalogName, String name, String desc, String keyword, String author, String userId,
			String filename, String fileext, String lompath, int status) {
		this.id = id;
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.name = name;
		this.desc = desc;
		this.keyword = keyword;
		this.author = author;
		this.userId = userId;
		this.filename = filename;
		this.fileext = fileext;
		this.lompath = lompath;
		this.status = status;
	}

	// build json content
	public String buildJsonContent() {
		return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"catalogId\":" + catalogId + ",\"catalogName\":\"" + catalogName
				+ "\",\"userId\":\"" + userId + "\",\"author\":\"" + author + "\",\"desc\":\"" + desc + "\",\"fileext\":\"" + fileext
				+ "\",\"filename\":\"" + filename + "\",\"lompath\":\"" + lompath + "\",\"status\":" + status + "}";
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

	public long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(long catalogId) {
		this.catalogId = catalogId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public String getLompath() {
		return lompath;
	}

	public void setLompath(String lompath) {
		this.lompath = lompath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

}
