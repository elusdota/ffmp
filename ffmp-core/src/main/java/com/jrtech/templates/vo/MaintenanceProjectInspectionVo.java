package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Inspection;
import com.jrtech.ffmp.data.entities.MaintenanceProject;

import java.util.Collection;
import java.util.List;

/**
 * Created by jiangliang on 2016/11/2.
 */
public class MaintenanceProjectInspectionVo {
    private String id;
    private Collection<Inspection> inspections;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<Inspection> getInspections() {
        return inspections;
    }

    public void setInspections(Collection<Inspection> inspections) {
        this.inspections = inspections;
    }
}
