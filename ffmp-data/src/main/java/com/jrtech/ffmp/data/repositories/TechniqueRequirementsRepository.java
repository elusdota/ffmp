package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.TechniqueRequirements;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by suelmer on 2016/9/4.
 */
@Repository
public interface TechniqueRequirementsRepository extends PagingAndSortingRepository<TechniqueRequirements, String>, JpaSpecificationExecutor {
    Collection<TechniqueRequirements> findByLifetimeIsNotOrChangetimeIsNot(int lifetime, int changetime);
}
