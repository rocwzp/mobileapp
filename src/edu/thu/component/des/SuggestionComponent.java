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
import edu.thu.bean.des.Resource;
import edu.thu.bean.des.ResourceWrapper;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.ISuggestionComponent;
import edu.thu.util.CommonUtil;

/**
 * 资源推荐功能
 * 
 * @author hujiawei
 * 
 */
public class SuggestionComponent extends AbstractComponent implements ISuggestionComponent {

	@Override
	public void suggestion(JSONResult xmlResult, HashMap<String, String> paramMap) {
		int count;
		if (paramMap.get("count") != null) {
			count = Integer.parseInt(paramMap.get("count"));
		} else {
			count = CommonUtil.SUGGESTION_DEFAULT_COUNT;
		}
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_DES);
			Connection connection = dataSource.getConnection();
			String sql = "SELECT KL.KL_ID AS \"id\", KL.KL_NAME AS \"name\", KL.KL_AUTH AS \"author\", KL.KL_KEYWORD AS \"keyword\", KL.KL_DESC AS \"desc\", KL.KL_CATALOG_ID AS \"catalogId\", C.KL_CATALOG_NAME AS \"catalogName\", KL.LOM_PATH AS \"lompath\", KL.KL_FILENAME AS \"filename\", KL.KL_FILEEXT AS \"fileext\", KL.USER_ID AS \"userId\", KL.KL_STATUS AS \"status\" "
					+ " FROM DESIGN.KNOWLEDGE KL, DESIGN.KNOWLEDGE_CATALOG C "
					+ "WHERE ROWNUM <= "
					+ count
					+ " AND kl.KL_CATALOG_ID = c.KL_CATALOG_ID ORDER BY \"id\" DESC";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
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
			onResultSucceed(xmlResult, "推荐成功", new ResourceWrapper(resourceList).buildJsonContent());
			if (resourceList.size() < 1) {
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
