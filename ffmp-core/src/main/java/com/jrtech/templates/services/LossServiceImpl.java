package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.ffmp.data.entities.Loss;
import com.jrtech.ffmp.data.repositories.InventoryRepository;
import com.jrtech.ffmp.data.repositories.LossRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/8/15.
 */
@Service
public class LossServiceImpl implements LossService {
    @Autowired
    private LossRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<Loss> findAll(Specification<Loss> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Loss save(Loss loss) {
        loss.getLossdetails().forEach(lossDetail -> {
            Inventory inventory = inventoryRepository.findOne(lossDetail.getInventory_id());
            if (inventory.getQuantity() > lossDetail.getQuantity()) {
                inventory.setQuantity(inventory.getQuantity() - lossDetail.getQuantity());
                inventoryRepository.save(inventory);
            } else {
                inventoryRepository.delete(lossDetail.getInventory_id());
            }
        });
        return repository.save(loss);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Loss findOne(String id) {
        return repository.findOne(id);
    }
}
