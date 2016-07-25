package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Purchases;
import com.jrtech.ffmp.data.repositories.PurchasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/6/28.
 */
@Service
public class PurchasesServiceImpl implements PurchasesService {
    @Autowired
    private PurchasesRepository repository;

    @Override
    public Page<Purchases> findAll(Specification<Purchases> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Purchases save(Purchases purchases) {
        return repository.save(purchases);
    }

    @Override
    public Purchases findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
