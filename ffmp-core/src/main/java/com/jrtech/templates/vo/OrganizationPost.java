package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Organization;

/**
 * Created by jiangliang on 2016/7/15.
 */
public class OrganizationPost {
    private String parentId;
    private Organization organization;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
