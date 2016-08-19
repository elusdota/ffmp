package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.ffmp.data.entities.Overflow;
import com.jrtech.ffmp.data.entities.OverflowDetail;
import com.jrtech.ffmp.data.repositories.InventoryRepository;
import com.jrtech.ffmp.data.repositories.OverflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jiangliang on 2016/8/15.
 */
@Service
public class OverflowServiceImpl implements OverflowService {
    @Autowired
    private OverflowRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<Overflow> findAll(Specification<Overflow> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Overflow save(Overflow overflow) {
        overflow.getOverflowdetails().forEach(overflowDetail -> {
            buildInventory(overflowDetail, overflow);
        });
        return repository.save(overflow);
    }

    @Override
    public Overflow findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    public Inventory buildInventory(OverflowDetail overflowDetail, Overflow overflow) {
        Inventory inventory = new Inventory();
        inventory.setName(overflowDetail.getName());
        inventory.setExecutor(overflow.getExecutor());
        inventory.setDescription(overflowDetail.getDescription());
        inventory.setManufacturer(overflowDetail.getManufacturer());
        inventory.setModel(overflowDetail.getModel());
        inventory.setPrice(overflowDetail.getPrice());
        inventory.setQuantity(overflowDetail.getQuantity());
        inventory.setType(overflowDetail.getType());
        inventory.setTime(new Date());
        return inventoryRepository.save(inventory);
    }
}
