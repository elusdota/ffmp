package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.ffmp.data.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/6/28.
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository repository;

    @Override
    public Page<Inventory> findAll(Specification<Inventory> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Page<Inventory> findByInventoryTypeAndNameLike(String inventoryType,String name, Pageable pageable) {
        return repository.findByInventoryTypeAndNameLike(inventoryType, name, pageable);
    }

    @Override
    public Page<Inventory> findByInventoryTypeNotAndNameLike(String inventoryType, String name, Pageable pageable) {
        return repository.findByInventoryTypeNotAndNameLike(inventoryType,name,pageable);
    }
}
