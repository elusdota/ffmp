package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Inbounds;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/6/28.入库单dao.elus
 */
@Repository
public interface InboundsRepository extends PagingAndSortingRepository<Inbounds, String>, JpaSpecificationExecutor {
}
