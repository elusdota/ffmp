package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.repositories.MaintenanceTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/14.
 */
@Service
public class TaskRuntimeServiceImpl implements TaskRuntimeService {
    @Autowired
    private MaintenanceTaskRepository repository;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrganizationService organizationService;

    @Override
    public Page<MaintenanceTask> findAll(Specification<MaintenanceTask> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public MaintenanceTask save(MaintenanceTask maintenanceTask) {
        return repository.save(maintenanceTask);
    }

    @Override
    public MaintenanceTask findOneByRepairnumber(String repairnumber) {
        return repository.findOneByRepairnumber(repairnumber);
    }

    @Override
    public MaintenanceTask findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public MaintenanceTask findOneByName(String name) {
        return null;
    }

    @Override
    public Page<MaintenanceTask> findBySuspended(Pageable pageable) {
        final Organization[] organization = {new Organization("", 2)};
        accountService.getOrganizations(userDetailsUtils.getCurrent().getUsername()).forEach(organization1 -> {
            if (organization1.getType() == 2) {
                organization[0] = organization1;
            }
        });
        if (organization[0].getId() == null) {
            return repository.findBySuspended(false, pageable);
        }else {
            return repository.findByDelegateAndSuspended(organization[0], false, pageable);
        }
    }
}
