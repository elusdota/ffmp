package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.repositories.MaintenanceProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/7/14.
 */
@Service
public class MaintenanceProjectServiceImpl implements  MaintenanceProjectService {
    @Autowired
    private MaintenanceProjectRepository repository;
    @Override
    public Page<MaintenanceProject> findAll(Specification<MaintenanceProject> spec, Pageable pageable) {
        return repository.findAll(spec,pageable);
    }

    @Override
    public MaintenanceProject findOneByName(String name) {
        return repository.findOneByName(name);
    }

    @Override
    public MaintenanceProject findOneByCode(String code) {
        return repository.findOneByCode(code);
    }

    @Override
    public MaintenanceProject findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public MaintenanceProject save(MaintenanceProject maintenanceProject) {
        return repository.save(maintenanceProject);
    }
}
