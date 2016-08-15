package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/7/27.
 */
public interface EquipmentService {
    Page<Equipment> findByOwner(MaintenanceProject owner, Pageable pageable);
    Page<Equipment> findAll(Specification<Equipment> spec, Pageable pageable);
    Equipment findOneByCode(String code);
}
