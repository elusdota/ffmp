package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.MrrStandard;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by suelmer on 2016/7/16.
 */
@Repository
public interface MrrStandardRepository extends PagingAndSortingRepository<MrrStandard, String>, JpaSpecificationExecutor {

    MrrStandard findOneByName(String name);

    MrrStandard findOneByParent(MrrStandard parent);

    @Query("SELECT o FROM MrrStandard o WHERE o.parent is null")
    Iterable<MrrStandard> findRoot();

}
