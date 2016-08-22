package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Material;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by suelmer on 2016/8/18.
 */
public interface MaterialRepository extends PagingAndSortingRepository<Material, String>, JpaSpecificationExecutor {
}
