package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inspection;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.repositories.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by jiangliang on 2016/11/2.
 */
@Service
public class InspectionServiceImpl implements InspectionService {
    @Autowired
    private InspectionRepository repository;

    @Override
    public Inspection save(Inspection inspection) {
        return repository.save(inspection);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Inspection findByMaintenanceProjectAndName(MaintenanceProject maintenanceProject, String name) {
        return repository.findByMaintenanceProjectAndName(maintenanceProject,name);
    }

}
