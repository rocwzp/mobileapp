package edu.thu.bean.jst;

public class UserData {
	private Long userId;
	private Long stuid;
	private Long groupid;
	private String group_name;
	private String login_id;	
	private String personName;
	private String sex;
	private String mobile;
	private String telephone;
	private String email;
	private String address;
	

	public UserData(Long userId, Long stuid, Long groupid, String group_name, String login_id, String personName,
			String sex, String mobile, String telephone, String email, String address) {
		this.userId = userId;
		this.stuid = stuid;
		this.groupid = groupid;
		this.group_name = group_name;
		this.login_id = login_id;
		this.personName = personName;
		this.sex = sex;
		this.mobile = mobile;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
	}

	// build json content
	public String buildJsonContent() {
		return "{\"userId\":" + userId + ",\"stuid\":" + stuid + ",\"groupid\":" + groupid + ",\"group_name\":\"" + group_name +
				"\",\"login_id\":\"" + login_id + "\",\"personName\":\"" + personName + 
				"\",\"sex\":\"" + sex + "\",\"mobile\":\"" + mobile + "\",\"telephone\":\"" + telephone + 
				"\",\"email\":\"" + email + "\",\"address\":\"" + address + "\"}";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStuId() {
		return stuid;
	}

	public void setStuId(Long stuid) {
		this.stuid = stuid;
	}

	public Long getGroupId() {
		return groupid;
	}

	public void setGroupId(Long groupid) {
		this.groupid = groupid;
	}
	
	public String getGroupName() {
		return group_name;
	}

	public void setGroupName(String group_name) {
		this.group_name = group_name;
	}
	
	public String getLoginId() {
		return login_id;
	}

	public void setLoginId(String login_id) {
		this.login_id = login_id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
