package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/14.
 */
@Repository
public interface HistoryTaskNodeRepository extends PagingAndSortingRepository<HistoryTaskNode, String>, JpaSpecificationExecutor {
    HistoryTaskNode findOneByName(String name);

    List<HistoryTaskNode> findByMaintenanceTaskOrderByDueDateAsc(MaintenanceTask maintenanceTask);
}
