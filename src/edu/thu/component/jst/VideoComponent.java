package edu.thu.component.jst;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.XmlResult;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.IVideoComponent;

public class VideoComponent extends AbstractComponent implements IVideoComponent {

	@Override
	public void video(XmlResult xmlResult, HashMap<String, String> paramMap) {
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jst_study");
			Connection connection = dataSource.getConnection();
			String sql = "select * from jst_study.M_VIDEOS where courseid='" + paramMap.get("courseId") + "'";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt("video_status")==6) {
					String video_url=rs.getString("stream");
					StringBuffer videostream = new StringBuffer("<videostream>\n");
					videostream.append("<url>" + video_url + "</url>\n");
					videostream.append("</videostream>");
					video_url=videostream.toString();
					onResultSucceed(xmlResult, "获取成功", video_url);
				} else {
					onResultFail(xmlResult, "视频未转换", null);
				}
			} else {
				onResultFail(xmlResult, "无此视频", null);
			}
			rs.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "网络或者服务器故障", null);
		}
	}

}
