package com.jrtech.ffmp.data.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "parent" })
public class Organization extends AbstractTreeNode<Organization> {

	protected Organization() {
		super();
	}

	public Organization(String name, int type) {
		super(name);
		this.type = type;
	}

	/**
	 * 类型0：部门，1：分公司，2：维修小组
	 */
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "organization", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Collection<Role> roles = new ArrayList<>();

	public Collection<Role> getRoles() {
		return roles;
	}
}
