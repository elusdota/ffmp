package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inbounds;
import com.jrtech.ffmp.data.entities.InboundsDetail;
import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.ffmp.data.repositories.InboundsRepository;
import com.jrtech.ffmp.data.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jiangliang on 2016/6/28.
 */
@Service
public class InboundsServiceImpl implements InboundsService {
    @Autowired
    private InboundsRepository repository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<Inbounds> findAll(Specification<Inbounds> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Inbounds save(Inbounds inbounds) {

        inbounds.getInboundsdetails().forEach(inboundsDetail -> {
            buildInventory(inboundsDetail, inbounds);
        });
        return repository.save(inbounds);
    }

    @Override
    public Inbounds findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    public Inventory buildInventory(InboundsDetail inboundsDetail, Inbounds inbounds) {
        Inventory inventory = new Inventory();
        inventory.setName(inboundsDetail.getName());
        inventory.setExecutor(inbounds.getExecutor());
        inventory.setDescription(inboundsDetail.getDescription());
        inventory.setManufacturer(inboundsDetail.getManufacturer());
        inventory.setModel(inboundsDetail.getModel());
        inventory.setPrice(inboundsDetail.getPrice());
        inventory.setQuantity(inboundsDetail.getQuantity());
        inventory.setInventoryType(inboundsDetail.getInventoryType());
        inventory.setType(inboundsDetail.getType());
        inventory.setTime(new Date());
        return inventoryRepository.save(inventory);
    }
}
