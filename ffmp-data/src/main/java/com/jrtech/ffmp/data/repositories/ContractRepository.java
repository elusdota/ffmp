package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Contract;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 合同信息数据仓库
 * Created by suelmer on 2016/7/18.
 */
@Repository
public interface ContractRepository  extends PagingAndSortingRepository<Contract, String>, JpaSpecificationExecutor {
}
