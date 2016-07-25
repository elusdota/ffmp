package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by jiangliang on 2016/6/28.库存dao. elus
 */
@Repository
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, String>, JpaSpecificationExecutor {
    
	/**
	 * 通过材料名称查询 库存
	 * @param name 材料名称
	 * @param pageable 分页对象
	 * @return 库存列表
	 */
	Page<Inventory> findByNameLike(String name, Pageable pageable);
}
