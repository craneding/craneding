/**
 * 
 */
package com.easybean;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author ¶¡¶¡
 * 
 */
public abstract class AbstractEasyBean {
	
	protected DataSource dataSource;
	protected final ThreadLocal<Transaction> local = new ThreadLocal<Transaction>();

	public AbstractEasyBean() {
	}

	public AbstractEasyBean(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public Transaction beginTransaction(){
		Transaction transaction = currentTransaction();
		
		try {
			transaction.oldAutoCommit = transaction.connection.getAutoCommit();
			transaction.connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		}
		
		return transaction;
	}
	
	public void endTransaction(){
		Transaction transaction = currentTransaction();
		
		if(transaction.isActive()) {
			try {
				transaction.rollback();
			} finally {
				close();
			}
		} else
			close();
	}

	public void commitTransaction(){
		Transaction transaction = currentTransaction();
		
		try {
			transaction.connection.commit();
			
			transaction.setActive(false);
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		}
	}
	
	protected void close() {
		try {
			currentTransaction().connection.setAutoCommit(currentTransaction().oldAutoCommit);
			currentTransaction().close();
		} catch (SQLException e) {
			throw new EasyBeanException(e);
		} finally {
			local.remove();
		}
	}

	protected Connection currentConnection() {
		return currentTransaction().connection;
	}
	
	protected Transaction currentTransaction() {
		Transaction transaction = local.get();
		
		if(transaction != null)
			return transaction;
		else {
			try {
				transaction = new Transaction(dataSource.getConnection());
			} catch (SQLException e) {
				throw new EasyBeanException(e);
			}
			
			local.set(transaction);
			
			return transaction;
		}
	}
}
