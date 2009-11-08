/**
 * 
 */
package com.craneding.domain;

import java.io.Serializable;

/**
 * ª·‘±±Ì
 * 
 * @author crane.ding
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 8168167847729223083L;

	private Integer id;
	private String name;
	private String password;
	private String email;
	private String qq;
	private String telephone;
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
