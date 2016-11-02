package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Inspection;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by jiangliang on 2016/11/2.
 */
@Repository
public interface InspectionRepository extends PagingAndSortingRepository<Inspection, String>, JpaSpecificationExecutor {
    Inspection findByMaintenanceProjectAndName(MaintenanceProject maintenanceProject,String name);
}
