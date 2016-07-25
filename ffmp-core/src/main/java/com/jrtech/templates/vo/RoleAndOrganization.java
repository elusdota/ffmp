package com.jrtech.templates.vo;


import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.entities.Role;

/**
 * Created by jiangliang on 2016/6/27.
 */
public class RoleAndOrganization {
    private Role role;
    private Organization organization;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
