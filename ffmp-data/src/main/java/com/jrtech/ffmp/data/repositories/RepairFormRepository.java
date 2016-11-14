package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.RepairForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/7/25.保修单数据接口
 */
@Repository
public interface RepairFormRepository extends PagingAndSortingRepository<RepairForm, String>, JpaSpecificationExecutor {
    RepairForm findOneByCode(String code);

    Page<RepairForm> findByProcessing(boolean processing,Pageable pageable);
}
