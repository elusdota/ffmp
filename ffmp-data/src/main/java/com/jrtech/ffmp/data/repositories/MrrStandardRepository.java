package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.MrrStandard;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by suelmer on 2016/7/16.
 */
@Repository
public interface MrrStandardRepository extends PagingAndSortingRepository<MrrStandard, String>, JpaSpecificationExecutor {
}
