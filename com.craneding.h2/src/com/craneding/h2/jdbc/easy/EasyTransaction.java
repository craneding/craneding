/**
 * 
 */
package com.craneding.h2.jdbc.easy;

import java.sql.SQLException;


/**
 * @author crane.ding
 *
 */
public interface EasyTransaction {
	
	public void execute(EasyBean easyBean) throws SQLException;
	
}
