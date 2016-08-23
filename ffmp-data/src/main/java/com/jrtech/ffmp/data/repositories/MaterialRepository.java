package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by suelmer on 2016/8/18.
 */
public interface MaterialRepository extends PagingAndSortingRepository<Material, String>, JpaSpecificationExecutor {
    //通过任务查询
    Page<Material> findByMaintenanceTask(MaintenanceTask maintenanceTask,Pageable pageable);
}
