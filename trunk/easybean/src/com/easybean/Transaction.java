/**
 * 
 */
package com.easybean;

import java.sql.Connection;

/**
 * @author ¶¡¶¡
 * 
 */
public class Transaction {

	/**
	 * The status of the transaction.
	 */
	boolean active = true;

	/**
	 * The underlying Connection.
	 */
	final Connection connection;

	public Transaction(Connection connection) {
		this.connection = connection;
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
}
