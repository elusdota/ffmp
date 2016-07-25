package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.RepairForm;
import com.jrtech.ffmp.data.repositories.RepairFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/7/25.
 */
@Service
public class RepairFormServiceImpl implements RepairFormService {
    @Autowired
    private RepairFormRepository repository;

    @Override
    public RepairForm save(RepairForm repairForm) {
        return repository.save(repairForm);
    }

    @Override
    public Page<RepairForm> findAll(Specification<RepairForm> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public RepairForm findOneByCode(String code) {
        return repository.findOneByCode(code);
    }
}
