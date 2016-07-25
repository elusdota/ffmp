package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/7/14.
 */
@Repository
public interface MaintenanceTaskRepository extends PagingAndSortingRepository<MaintenanceTask, String>, JpaSpecificationExecutor {
    Page<MaintenanceTask> findByDelegateAndSuspended(Organization delegate,boolean suspended, Pageable pageable);
    MaintenanceTask findOneByRepairnumber(String repairnumber);
}
