package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.ffmp.data.entities.Repaid;
import com.jrtech.ffmp.data.repositories.InventoryRepository;
import com.jrtech.ffmp.data.repositories.RepaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/9/22.
 */
@Service
public class RepaidServiceImpl implements RepaidService {
    @Autowired
    private RepaidRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<Repaid> findAll(Specification<Repaid> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Repaid save(Repaid repaid) {
        repaid.getRepaidDetails().forEach(repaidDetail -> {
            Inventory inventory = repaidDetail.getInventory();
            inventory.setQuantity(inventory.getQuantity() + repaidDetail.getQuantity());
            inventoryRepository.save(inventory);
        });
        return repository.save(repaid);
    }
}
