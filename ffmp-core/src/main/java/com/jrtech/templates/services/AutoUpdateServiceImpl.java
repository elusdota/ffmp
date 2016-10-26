package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.AutoUpdate;
import com.jrtech.ffmp.data.repositories.AutoUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by suelmer on 2016/10/20.
 */
@Service
public class AutoUpdateServiceImpl implements AutoUpdateService {
    @Autowired
    private AutoUpdateRepository autoUpdateRepository;
    @Override
    public AutoUpdate save(AutoUpdate autoUpdate) {
        return autoUpdateRepository.save(autoUpdate);
    }

    @Override
    public AutoUpdate getLastUpdate() {
        return null;
    }

    @Override
    public Page<AutoUpdate> findAll(Specification<AutoUpdate> spec, Pageable pageable) {
        return autoUpdateRepository.findAll(spec,pageable);
    }
}
