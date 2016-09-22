package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.ffmp.data.entities.Lending;
import com.jrtech.ffmp.data.repositories.InventoryRepository;
import com.jrtech.ffmp.data.repositories.LendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/9/22.
 */
@Service
public class LendingServiceImpl implements LendingService {
    @Autowired
    private LendingRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<Lending> findAll(Specification<Lending> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Lending save(Lending lending) {
        lending.getLendingDetails().forEach(lendingDetail -> {
            String id =lendingDetail.getInventory().getId();
            Inventory inventory = inventoryRepository.findOne(id);
            if (inventory.getQuantity() >=lendingDetail.getQuantity()) {
                inventory.setQuantity(inventory.getQuantity() - lendingDetail.getQuantity());
                inventoryRepository.save(inventory);
                lendingDetail.setInventory(inventory);
            } else {
                throw new ServiceException("库存不能为负数！");
            }
        });
        return repository.save(lending);
    }
}
