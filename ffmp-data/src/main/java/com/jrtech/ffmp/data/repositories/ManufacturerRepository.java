package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 生产厂商数据仓库
 * Created by suelmer on 2016/7/19.
 */
@Repository
public interface ManufacturerRepository extends PagingAndSortingRepository<Manufacturer, String>, JpaSpecificationExecutor {
}
