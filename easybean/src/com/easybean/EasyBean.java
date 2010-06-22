/**
 * 
 */
package com.easybean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.easybean.BeansUtil.BeansInfo;

/**
 * @author ¶¡¶¡
 */
public final class EasyBean extends AbstractEasyBean {

	class LocalInfo {
		String sql;
		List<Object> parmerters = new ArrayList<Object>();
	}
	
	protected ThreadLocal<LocalInfo> _localInfo = new ThreadLocal<LocalInfo>();
	
	public static EasyBean newInstance(DataSource dataSource){
		return new EasyBean(dataSource);
	}
	
	public EasyBean() {
	}

	public EasyBean(DataSource dataSource) {
		super(dataSource);
	}

	public EasyBean sql(String sql){
		currentLocalInfo().get().sql = sql;
		
		return this;
	}

	public EasyBean addParameters(Object... parmerters) {
		for (Object o : parmerters) {
			currentLocalInfo().get().parmerters.add(o);
		}
		
		return this;
	}

	private ThreadLocal<LocalInfo> currentLocalInfo() {
		if(_localInfo.get() == null) {
			_localInfo.set(new LocalInfo());
		}
		
		return _localInfo;
	}
	
	public <T> T findUnique(Class<T> clazz) {
		List<T> array = findList(clazz);
		
		return array.isEmpty() ? null : array.get(0);
	}

	public <T> List<T> findList(Class<T> clazz) {
		ArrayList<T> results = new ArrayList<T>();
		
		PreparedStatement prepareCall = prepareCall(currentLocalInfo().get().sql);
		
		ResultSet rs = null;
		try {
			rs = prepareCall.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			Map<String, BeansInfo> beansInfo = BeansUtil.getBeansInfo(clazz);
			
			while(rs.next()) {
				T obj = clazz.newInstance();
				
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String name = metaData.getColumnName(i).toUpperCase();
					
					if(beansInfo.containsKey(name)) {
						beansInfo.get(name).setValue(obj, rs);
					}
				}
				
				results.add(obj);
			}
		} catch (Exception e) {
			throw new EasyBeanException(e);
		} finally {
			autoClose(prepareCall, rs);
		}
		
		return results;
	}
	
	public <T> List<T> findList(Class<T> clazz, int startIndex, int maxCount) {
		ArrayList<T> results = new ArrayList<T>();
		
		PreparedStatement prepareCall = prepareCall(currentLocalInfo().get().sql);
		
		ResultSet rs = null;
		try {
			rs = prepareCall.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			Map<String, BeansInfo> beansInfo = BeansUtil.getBeansInfo(clazz);
			
			rs.first();
			rs.relative(startIndex - 1);
			
			for (int i = 1; i <= maxCount; i++) {
				T obj = clazz.newInstance();
				
				for (int j = 1; j <= metaData.getColumnCount(); j++) {
					String name = metaData.getColumnName(j).toUpperCase();
					
					if(beansInfo.containsKey(name)) {
						beansInfo.get(name).setValue(obj, rs);
					}
				}
				
				results.add(obj);
				
				if(!rs.next())
					break;
			}
		} catch (Exception e) {
			throw new EasyBeanException(e);
		} finally {
			autoClose(prepareCall, rs);
		}
		
		return results;
	}
	
	public Map<String, Object> findUniqueMap() {
		List<Map<String,Object>> map = findListMap();
		
		return map.isEmpty() ? null : map.get(0);
	}
	
	public List<Map<String, Object>> findListMap() {
		ArrayList<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		
		PreparedStatement prepareCall = prepareCall(currentLocalInfo().get().sql);
		
		ResultSet rs = null;
		try {
			rs = prepareCall.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String name = metaData.getColumnName(i).toUpperCase();
					
					map.put(metaData.getColumnLabel(i).toUpperCase(), rs.getObject(name));
				}
				
				results.add(map);
			}
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		} finally {
			autoClose(prepareCall, rs);
		}
		
		return results;
	}
	
	public List<Map<String, Object>> findListMap(int startIndex, int maxCount) {
		ArrayList<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		
		PreparedStatement prepareCall = prepareCall(currentLocalInfo().get().sql);
		
		ResultSet rs = null;
		try {
			rs = prepareCall.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			
			rs.first();
			rs.relative(startIndex - 1);
			
			for (int n = 1; n <= maxCount; n++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					String name = metaData.getColumnName(i).toUpperCase();
					
					map.put(metaData.getColumnLabel(i).toUpperCase(), rs.getObject(name));
				}
				
				results.add(map);
				
				if(!rs.next())
					break;
			}
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		} finally {
			autoClose(prepareCall, rs);
		}
		
		return results;
	}
	
	public EasyBean update(){
		PreparedStatement prepareCall = prepareCall(currentLocalInfo().get().sql);
		
		try {
			prepareCall.executeUpdate();
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		} finally {
			autoClose(prepareCall, null);
		}
		
		return this;
	}
	
	public EasyBean save(){
		PreparedStatement prepareCall = prepareCall(currentLocalInfo().get().sql);
		
		try {
			prepareCall.execute();
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		} finally {
			autoClose(prepareCall, null);
		}
		
		return this;
	}
	
	public EasyBean delete() {
		PreparedStatement prepareCall = prepareCall(currentLocalInfo().get().sql);
		
		try {
			prepareCall.execute();
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		} finally {
			autoClose(prepareCall, null);
		}
		
		return this;
	}
	
	protected PreparedStatement prepareCall(String sql){
		PreparedStatement _call = null;
		
		List<Object> parmerters = currentLocalInfo().get().parmerters;
		try {
			_call = currentConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			for (int i = 1; i <= parmerters.size(); i++)
				_call.setObject(i, parmerters.get(i - 1));
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		} finally {
			parmerters.clear();
		}
		
		return _call;
	}
	
	private void autoClose(PreparedStatement prepareCall, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}

		if (prepareCall != null)
			try {
				prepareCall.close();
			} catch (SQLException e) {
			}
		
		try {
			if(currentConnection().getAutoCommit()) {
				close();
			}
		} catch (SQLException e) {
		}
		
		currentLocalInfo().remove();
	}
}
