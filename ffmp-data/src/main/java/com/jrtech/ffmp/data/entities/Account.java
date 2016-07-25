package com.jrtech.ffmp.data.entities;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

/***
 * 用户帐户
 * @author Shawn
 *
 */
@Entity
public class Account extends AbstractDomainObject implements Comparable<Account> {
	@NotEmpty
	@Column(unique = true)
	private String name;
	@NotEmpty
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<Role>();
	protected Account() {
		super();
	}
	
	/***
	 * 创建新的用户帐户。
	 * @param name 帐户名。不能为空。
	 * @param password 帐户密码。不能为空。
	 */
	public Account(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	/***
	 * 获取帐户登录名
	 * @return
	 */
	public String getName() {
		return name;
	}

	/***
	 * 设置帐户登录名。不能为空。
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/***
	 * 获取帐户的登录密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/***
	 * 设置帐户的登录密码。不能为空。
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/***
	 * 获取与帐户相关的角色。
	 * @return
	 */
	public Collection<Role> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return String.format("{ID: %s, NAME: %s, PASSWORD: hide}", getId(), getName());
	}

	@Override
	public int compareTo(Account o) {
		return getName().compareTo(o.getName());
	}

}
