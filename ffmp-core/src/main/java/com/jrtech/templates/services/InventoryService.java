package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/6/28.库存Service,elus
 */
public interface InventoryService {
    //查出所有库存
    Page<Inventory> findAll(Specification<Inventory> spec, Pageable pageable);

    //通过材料名称查询
    Page<Inventory> findByInventoryTypeAndNameLike(String inventoryType,String name, Pageable pageable);
    Page<Inventory> findByInventoryTypeNotAndNameLike(String inventoryType,String name, Pageable pageable);
}
