/**
 * 
 */
package com.easybean;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ¶¡¶¡
 * 
 */
public class Transaction {

	/**
	 * The status of the transaction.
	 */
	boolean active = true;
	
	boolean oldAutoCommit = true;

	/**
	 * The underlying Connection.
	 */
	final Connection connection;

	public Transaction(Connection connection) throws SQLException {
		this.connection = connection;
		
		oldAutoCommit = connection.getAutoCommit();
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void commit(){
		try {
			connection.commit();
		} catch (Exception e) {
			throw new EasyBeanException(e);
		}
	}
	
	public void rollback(){
		try {
			connection.rollback();
		} catch (Exception e) {
			throw new EasyBeanException(e);
		}
	}
	
	public void close(){
		try {
			connection.close();
		} catch (Exception e) {
			throw new EasyBeanException(e);
		}
	}
	
	public boolean isOldAutoCommit() {
		return oldAutoCommit;
	}

	public void setOldAutoCommit(boolean oldAutoCommit) {
		this.oldAutoCommit = oldAutoCommit;
	}
}
