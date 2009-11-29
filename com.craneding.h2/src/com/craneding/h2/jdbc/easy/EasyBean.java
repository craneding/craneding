/**
 * 
 */
package com.craneding.h2.jdbc.easy;

import java.beans.PropertyDescriptor;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author crane.ding
 * 
 */
public class EasyBean extends EasyBeanBase{

	private EasyBean(String url, String user, String password) {
		super(url, user, password);
	}
	
	public static EasyBean newInstance(String url, String user, String password){
		return new EasyBean(url, user, password);
	}
	
	public EasyBean open() throws SQLException{
		conn = DriverManager.getConnection(url, user, password);
		return this;
	}
	
	public EasyBean close() throws SQLException {
		if(conn != null)
			conn.close();
		return this;
	}

	public EasyBean transaction(EasyTransaction transaction) throws SQLException {
		
		conn.setAutoCommit(false);
		
		try {
			transaction.execute(this);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			if(e instanceof RuntimeException)
				throw (RuntimeException)e;
			else if(e instanceof SQLException)
				throw (SQLException)e;
			else
				throw new RuntimeException(e.getCause());
		} 
		
		return this;
	}
	
	public <T> List<T> select(String sql, Class<? extends T> clazz) throws SQLException{
		List<T> results = new ArrayList<T>();
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		T t = null;
		
		try {
			Map<String, PropertyDescriptor> descriptors = getPropertyDescriptors(clazz, rs);
			
			while (rs.next()) {
				t = clazz.newInstance();
				
				Set<String> keySet = descriptors.keySet();
				for (String name : keySet) {
					PropertyDescriptor descriptor = descriptors.get(name);
					Object o = rs.getObject(name);
					Object value = toValue(descriptor.getPropertyType(), o);
					descriptor.getWriteMethod().invoke(t, value);
				}
				
				results.add(t);
			}
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			else if (e instanceof SQLException)
				throw (SQLException) e;
			else
				throw new RuntimeException(e.getCause());
		}
		
		return results;
	}

	public <T> T selectUnique(String sql, Class<? extends T> clazz) throws SQLException {
		List<T> list = select(sql, clazz);
		if(list.size() == 1)
			return list.get(0);
		else
			throw new SQLException("query did not return a unique result: " + list.size());
	}

	public EasyBean execute(String sql) throws SQLException{
		Statement st = conn.createStatement();
		st.execute(sql);
		st.close();
		return this;
	}
}
