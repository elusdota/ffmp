package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/7/27.
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentRepository repository;

    @Override
    public Page<Equipment> findByOwner(MaintenanceProject owner, Pageable pageable) {
        return repository.findByOwner(owner, pageable);
    }
}
