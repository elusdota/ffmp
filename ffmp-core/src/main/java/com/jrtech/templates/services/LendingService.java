package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Lending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/9/22.
 */
public interface LendingService {
    Page<Lending> findAll(Specification<Lending> spec, Pageable pageable);
    Lending save(Lending lending);
}
