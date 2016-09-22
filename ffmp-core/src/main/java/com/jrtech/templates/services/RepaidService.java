package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Repaid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/9/22.
 */
public interface RepaidService {
    Page<Repaid> findAll(Specification<Repaid> spec, Pageable pageable);

    Repaid save(Repaid repaid);
}
