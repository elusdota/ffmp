package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.ffmp.data.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/27.
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentRepository repository;
    @Autowired
    private MrrStandardService mrrStandardService;

    @Override
    public Equipment save(Equipment equipment) {
        return repository.save(equipment);
    }

    @Override
    public Page<Equipment> findByOwner(MaintenanceProject owner, Pageable pageable) {
        return repository.findByOwner(owner, pageable);
    }

    @Override
    public Page<Equipment> findAll(Specification<Equipment> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public List<Equipment> findByOwner(MaintenanceProject owner) {
        return repository.findByOwner(owner);
    }

    @Override
    public Equipment findOneByCode(String code) {
        return repository.findOneByCode(code);
    }

    @Override
    public Equipment findOne(String id) {
        return repository.findOne(id);
    }

}
