package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inspection;
import com.jrtech.ffmp.data.entities.MaintenanceProject;

import java.util.Collection;

/**
 * Created by jiangliang on 2016/11/2.
 */
public interface InspectionService {
    Inspection save(Inspection inspection);

    void delete(String id);

    Inspection findByMaintenanceProjectAndName(MaintenanceProject maintenanceProject,String name);
}
