package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Purchases;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/6/28.采购单dao.elus
 */
@Repository
public interface PurchasesRepository extends PagingAndSortingRepository<Purchases, String>, JpaSpecificationExecutor {
}
