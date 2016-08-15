package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/8/15.
 */
public interface EquipmentService {
    public Page<Equipment> findByOwner(MaintenanceProject owner, Pageable pageable);

    public Page<Equipment> findAll(Specification<Equipment> spec, Pageable pageable);

    public Equipment findOneByCode(String code);
}
