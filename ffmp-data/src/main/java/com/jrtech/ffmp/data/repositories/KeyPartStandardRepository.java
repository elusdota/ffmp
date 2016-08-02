package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.KeyPartStandard;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 重点部位标准数据仓库
 * Created by suelmer on 2016/7/18.
 */
public interface KeyPartStandardRepository  extends PagingAndSortingRepository<KeyPartStandard, String>, JpaSpecificationExecutor {
}
