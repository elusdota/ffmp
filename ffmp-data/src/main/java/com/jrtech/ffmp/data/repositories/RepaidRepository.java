package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Repaid;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/9/22.
 */
@Repository
public interface RepaidRepository  extends PagingAndSortingRepository<Repaid, String>, JpaSpecificationExecutor {
}
