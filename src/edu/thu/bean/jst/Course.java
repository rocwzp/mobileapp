package edu.thu.bean.jst;

public class Course {
	private long id;
	private long catid;
	private String title;
	private String thumb;
	private String tags;
	private long created;
	private long pv;
	private String baogaoren;
	private long weight;
	private String videoUrl;
	
	public Course(){
		
	}

	public Course(long id, long catid, String title, String thumb, String tags, long created, 
			long pv, String baogaoren, long weight, String videoUrl) {
		this.id = id;
		this.catid = catid;
		this.title = title;
		this.thumb = thumb;
		this.tags = tags;
		this.created = created;
		this.pv = pv;
		this.baogaoren = baogaoren;
		this.weight = weight;
		this.videoUrl = videoUrl;
	}

	// build json content
	public String buildJsonContent() {
		return "{\"id\":" + id + ",\"catid\":" + catid + ",\"title\":\"" + title + "\",\"thumb\":\"" + thumb
				+ "\",\"tags\":\"" + tags + "\",\"created\":" + created + ",\"pv\":" + pv 
				+ ",\"baogaoren\":\"" + baogaoren + "\",\"weight\":" + weight + ",\"videoUrl\":\"" + videoUrl + "\"}";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}
	
	public long getPv() {
		return pv;
	}

	public void setPv(long pv) {
		this.pv = pv;
	}
	
	public String getBaogaoren() {
		return baogaoren;
	}

	public void setBaogaoren(String baogaoren) {
		this.baogaoren = baogaoren;
	}
	
	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}
	
	public String getUrl() {
		return videoUrl;
	}

	public void setUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
}
