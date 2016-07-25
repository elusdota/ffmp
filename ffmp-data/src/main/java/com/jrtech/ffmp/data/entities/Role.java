package com.jrtech.ffmp.data.entities;

import java.util.Collection;
import java.util.TreeSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = {"authorities","organization"})
public class Role extends AbstractNamedObject {

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<GrantedAuthorityImpl> authorities = new TreeSet<>();
	@NotNull
	@ManyToOne
	private Organization organization;

	protected Role() {
		super();
	}
	
	public Role(String name) {
		super(name);
	}
	
	public Role(String name, Organization organization) {
		this(name);
		this.organization = organization;
	}
    
	public Collection<GrantedAuthorityImpl> getAuthorities() {
		return authorities;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
