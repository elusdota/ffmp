package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Manufacturer;
import com.jrtech.ffmp.data.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by suelmer on 2016/7/19.
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer findOne(String id) {
        return manufacturerRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        manufacturerRepository.delete(id);
    }

    @Override
    public Page<Manufacturer> findAll(Specification<Manufacturer> spec, Pageable pageable) {
        return manufacturerRepository.findAll(spec,pageable);
    }
}
