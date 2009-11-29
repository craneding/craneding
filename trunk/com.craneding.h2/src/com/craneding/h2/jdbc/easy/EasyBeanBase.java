/**
 * 
 */
package com.craneding.h2.jdbc.easy;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author crane.ding
 *
 */
public abstract class EasyBeanBase {
	
	protected Connection conn;
	protected final String url;
	protected final String user;
	protected final String password;

	protected EasyBeanBase(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * @see java.lang.Class#forName(String)
	 */
	public static void forName(String className) throws ClassNotFoundException {
		Class.forName(className);
	}
	
	protected final static Map<String, PropertyDescriptor> getPropertyDescriptors(Class<?> clazz, ResultSet rs) throws IntrospectionException, SQLException, SecurityException, NoSuchFieldException{
		BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		
		Map<String, PropertyDescriptor> pdMap = new HashMap<String, PropertyDescriptor>();
		ResultSetMetaData md = rs.getMetaData();
		for (int i = 1; i <= md.getColumnCount(); i++) {
			for (PropertyDescriptor pd : pds){
				if(pd.getReadMethod() != null && pd.getWriteMethod() != null){
					String ColumnName = md.getColumnName(i);
					String FieldName = pd.getName();
					Field field = null;
					
					try {
						field = clazz.getField(FieldName);
					} catch (Exception e) {
						field = clazz.getDeclaredField(FieldName);
					}
					
					if (field.isAnnotationPresent(Column.class))
						FieldName = field.getAnnotation(Column.class).name();
					if (ColumnName.equalsIgnoreCase(FieldName)) {
						pdMap.put(ColumnName, pd);
					}
				}
			}
		}
		
		return pdMap;
	}
	
	protected final static Object toValue(Class<?> propertyType, Object value) {
		if(value.getClass() == propertyType)
			return value;
		
		if(value == null)
			return null;
		
		if(value instanceof Number){
			String s = String.valueOf(value);
			if(propertyType == short.class || propertyType == Short.class)
				return Short.valueOf(s);
			if(propertyType == int.class || propertyType == Integer.class)
				return Integer.valueOf(s);
			if(propertyType == long.class || propertyType == Long.class)
				return Long.valueOf(s);
			if(propertyType == float.class || propertyType == Float.class)
				return Float.valueOf(s);
			if(propertyType == double.class || propertyType == Double.class)
				return Double.valueOf(s);
			if(propertyType == BigDecimal.class || propertyType == BigDecimal.class)
				return new BigDecimal(s);
			if(propertyType == BigInteger.class || propertyType == BigInteger.class)
				return new BigInteger(s);
		}
		
		if(value instanceof java.util.Date && propertyType == Date.class) {
			return new Date(((java.sql.Date)value).getTime());
		}
		
		return value;
	}
}
