package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by suelmer on 2016/8/18.
 */
public interface MaterialService {

    Material save(Material material);

    Material findOne(String id);

    Page<Material> findAll(Specification<Material> spec, Pageable pageable);

    Page<Material> findByMaintenanceTask(MaintenanceTask maintenanceTask, Pageable pageable);
}
