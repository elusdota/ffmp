package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Dispatch;
import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.ffmp.data.repositories.DispatchRepository;
import com.jrtech.ffmp.data.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/6/29.
 */
@Service
public class DispatchServiceImpl implements DispatchService {
    @Autowired
    private DispatchRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<Dispatch> findAll(Specification<Dispatch> spec, Pageable pageable) {
        return repository.findAll(spec,pageable);
    }

    @Override
    public Dispatch save(Dispatch dispatch) {
        dispatch.getDispatchdetails().forEach(dispatchDetail -> {
            Inventory inventory = inventoryRepository.findOne(dispatchDetail.getInventory_id());
            if (inventory.getQuantity() > dispatchDetail.getQuantity()) {
                inventory.setQuantity(inventory.getQuantity() - dispatchDetail.getQuantity());
                inventoryRepository.save(inventory);
            } else {
                if(inventory.getInventoryType()=="工具"){
                    inventory.setQuantity(inventory.getQuantity() - dispatchDetail.getQuantity());
                    inventoryRepository.save(inventory);
                }else {
                    inventoryRepository.delete(dispatchDetail.getInventory_id());
                }
            }
        });
        return repository.save(dispatch);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Dispatch findOne(String id) {
        return repository.findOne(id);
    }
}
