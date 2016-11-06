package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

/**
 * Created by jiangliang on 2016/8/15.
 */
public interface EquipmentService {
    Equipment save(Equipment equipment);

    public Page<Equipment> findByOwner(MaintenanceProject owner, Pageable pageable);

    public Page<Equipment> findAll(Specification<Equipment> spec, Pageable pageable);

    List<Equipment> findByOwner(MaintenanceProject owner);

    public Equipment findOneByCode(String code);

    Collection<Equipment> findByOwnerAndCodeIsNull(MaintenanceProject owner);

    Equipment findOne(String id);
}
