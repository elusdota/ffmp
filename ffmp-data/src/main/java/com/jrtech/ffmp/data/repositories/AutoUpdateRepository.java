package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.AutoUpdate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by suelmer on 2016/10/20.
 */
@Repository
public interface AutoUpdateRepository  extends PagingAndSortingRepository<AutoUpdate, String>, JpaSpecificationExecutor {

//    AutoUpdate findTopOrderByCreateTimeDesc();
}
