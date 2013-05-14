package edu.thu.component.jst;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import edu.thu.bean.JSONResult;
import edu.thu.bean.jst.Course;
import edu.thu.bean.jst.CourseWrapper;
import edu.thu.icomponent.AbstractComponent;
import edu.thu.icomponent.IFavoriteComponent;
import edu.thu.util.CommonUtil;

public class FavoriteComponent extends AbstractComponent implements IFavoriteComponent {

	@SuppressWarnings("resource")
	@Override
	public void favorite(JSONResult xmlResult, HashMap<String, String> paramMap) {
		int userId = Integer.parseInt(paramMap.get("userId"));
		int courseId = Integer.parseInt(paramMap.get("courseId"));
		Boolean isexist=false;
		String date = null;
		Long id;
		InitialContext context;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(CommonUtil.JNDI_JST);
			Connection connection = dataSource.getConnection();
			//�ж��Ƿ���ѡ�˿�
			String sql = "select * from jst_study.T_STU_COURSE t where t.user_id="+userId+" and t.course_id="+courseId;
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);	
			if(rs.next()) 
				isexist=true;
			else 
				isexist=false;
			//����ѡ����ɾ���˿β�����ɾ���ɹ�
			if(isexist){
				sql = "delete from jst_study.T_STU_COURSE t where t.user_id="+userId+" and t.course_id="+courseId;//612 707662
				statement = connection.createStatement();
				rs = statement.executeQuery(sql);	
				rs.close();
				statement.close();
				onResultSucceed(xmlResult, "��ɾ��ѡ��", null);
			} else {		
				//��ȡʱ��
				sql = "select sysdate from dual";
				statement = connection.createStatement();
				rs = statement.executeQuery(sql);	
				if (rs.next()) {
					date = rs.getString("SYSDATE");
				}			
				rs.close();
				statement.close();
				//��ȡid
				sql = "select id from jst_study.T_STU_COURSE t order by t.id desc";
				statement = connection.createStatement();
				rs = statement.executeQuery(sql);
				if (rs.next()) {
					id = rs.getLong("id")+1;
				} else {
					id = (long) 350;
				}
				rs.close();
				statement.close();
				
				sql = "insert into jst_study.T_STU_COURSE t values ("+id+", "+userId+", "+courseId+", 0, to_date ('"+ date+ "', 'YYYY-MM-DD HH24:MI:SS'))";
				statement = connection.createStatement();
				rs = statement.executeQuery(sql);
				rs.close();
				statement.close();
				onResultSucceed(xmlResult, "��ѡ��", null);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			onResultException(xmlResult, "������߷���������", null);
		}
	}
}
