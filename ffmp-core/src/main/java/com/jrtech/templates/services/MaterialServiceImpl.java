package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Material;
import com.jrtech.ffmp.data.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by suelmer on 2016/8/18.
 */
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;
    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material findOne(String id) {
        return materialRepository.findOne(id);
    }

    @Override
    public Page<Material> findAll(Specification<Material> spec, Pageable pageable) {
        return materialRepository.findAll(spec,pageable);
    }
}
