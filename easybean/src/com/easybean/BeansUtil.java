/**
 * 
 */
package com.easybean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.easybean.annotation.Column;
import com.easybean.annotation.Id;

/**
 * @author ¶¡¶¡
 *
 */
public class BeansUtil {
	
	public static class BeansInfo {
		String name;
		Field field;
		Method readMethod;
		Method writeMethod;
		
		void setValue(Object obj, ResultSet rs){
			Class<?> type = field.getType();
			Object value = null;
			
			try {
				if(type == Short.class || type == short.class)
					value = rs.getShort(name);
				else if(type == Integer.class || type == int.class)
					value = rs.getInt(name);
				else if(type == Long.class || type == long.class)
					value = rs.getLong(name);
				else if(type == Float.class || type == float.class)
					value = rs.getFloat(name);
				else if(type == Double.class || type == double.class)
					value = rs.getDouble(name);
				else if(type == Boolean.class || type == boolean.class)
					value = rs.getBoolean(name);
				else if(type == String.class)
					value = rs.getString(name);
				else if(type == Date.class)
					value = rs.getDate(name);
				else
					value = rs.getObject(name);
				
				writeMethod.invoke(obj, value);
			} catch (Exception e) {
				throw new EasyBeanException(e);
			}
		}
	}

	public static Map<String, BeansInfo> getBeansInfo(Class<?> beanClass){
		Map<String, BeansInfo> _cache = new HashMap<String, BeansInfo>();
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			Field[] fields = beanClass.getDeclaredFields();
			
			for (PropertyDescriptor pd : pds) {
				if(pd.getWriteMethod() != null && pd.getReadMethod() != null) {
					BeansInfo info = new BeansInfo();
					
					for (Field field : fields) {
						if(field.getName().equalsIgnoreCase(pd.getName())) {
							info.field = field;
							break;
						}
					}
					
					info.readMethod = pd.getReadMethod();
					info.writeMethod = pd.getWriteMethod();
					
					if(info.field.isAnnotationPresent(Id.class))
						info.name = info.field.getAnnotation(Id.class).name();
					else if(info.field.isAnnotationPresent(Column.class))
						info.name = info.field.getAnnotation(Column.class).name();
					else 
						info.name = info.field.getName();
					
					_cache.put(info.name.toUpperCase(), info);
				}
			}
		} catch (IntrospectionException e) {
			throw new EasyBeanException(e);
		}
		
		return _cache;
	}
	
}
