package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/7/27.
 */
@Repository
public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, String>, JpaSpecificationExecutor {
    Page<Equipment> findByOwner(MaintenanceProject owner, Pageable pageable);
}