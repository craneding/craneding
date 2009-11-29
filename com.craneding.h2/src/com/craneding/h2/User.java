/**
 * 
 */
package com.craneding.h2;

import com.craneding.h2.jdbc.easy.Column;
import com.craneding.h2.jdbc.easy.Table;

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
