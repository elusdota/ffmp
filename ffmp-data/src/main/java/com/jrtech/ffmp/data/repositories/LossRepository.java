package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Loss;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/8/15.报损单dao，elus
 */
@Repository
public interface LossRepository extends PagingAndSortingRepository<Loss,String>, JpaSpecificationExecutor {
}
