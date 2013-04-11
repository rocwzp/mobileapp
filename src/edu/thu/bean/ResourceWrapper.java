package edu.thu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 资源列表的包装类
 * 
 * @author hujiawei
 * 
 */

public class ResourceWrapper implements Serializable {

	private static final long serialVersionUID = 5784396349159493301L;
	private List<Resource> resourceList;

	public ResourceWrapper(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

	// build json content
	public String buildJsonContent() {
		StringBuffer buffer = new StringBuffer("[");
		for (int i = 0; i < resourceList.size(); i++) {
			if (i != 0) {
				buffer.append(",");
			}
			buffer.append(resourceList.get(i).buildJsonContent());
		}
		buffer.append("]");
		return buffer.toString();
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}

}
