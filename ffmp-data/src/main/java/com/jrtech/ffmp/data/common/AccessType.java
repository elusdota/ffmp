package com.jrtech.ffmp.data.common;

/***
 * 
 * 标示数据类型
 *
 * @author Shawn
 *
 */
public enum AccessType {
	/***
	 * 系统内置数据，用户不能更改
	 */
	SYSTEM("System"),
	/***
	 * 用户创建的数据，在权限允许的情况下可以对其进行操作
	 */
	USER("User");
	
	private String name;
	private AccessType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
