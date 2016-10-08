package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Annexes;
import com.jrtech.ffmp.data.entities.Contract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by suelmer on 2016/8/19.
 */
@Repository
public interface AnnexesRepository extends PagingAndSortingRepository<Annexes, String>, JpaSpecificationExecutor {
    List<Annexes> getAnexesByContract(Contract contract);
}
