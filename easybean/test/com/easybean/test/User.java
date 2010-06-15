/**
 * 
 */
package com.easybean.test;

import com.easybean.annotation.Column;
import com.easybean.annotation.Table;

/**
 * @author crane.ding
 * 
 */
@Table(name = "User")
public class User {
	private Integer id;
	@Column(name = "username")
	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "id=" + id + " username=" + username;
	}
}
