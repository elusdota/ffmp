package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Overflow;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/8/15.报溢单，elus
 */
@Repository
public interface OverflowRepository extends PagingAndSortingRepository<Overflow, String>, JpaSpecificationExecutor {
}
