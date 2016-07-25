package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Dispatch;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/6/29.出库单dao .elus
 */
@Repository
public interface DispatchRepository extends PagingAndSortingRepository<Dispatch, String>, JpaSpecificationExecutor {
}
