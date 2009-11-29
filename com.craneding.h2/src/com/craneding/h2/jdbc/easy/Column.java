/**
 * 
 */
package com.craneding.h2.jdbc.easy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author crane.ding
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
public @interface Column {

	/**
	 * ×Ö¶ÎÃû³Æ
	 */
	String name();
	
}
