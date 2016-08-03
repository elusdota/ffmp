package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.KeyPartStandard;
import com.jrtech.ffmp.data.repositories.KeyPartStandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * 重点部位标准服务层实现
 * Created by suelmer on 2016/7/18.
 */
@Service
public class KeyPartStandardServiceImpl implements KeyPartStandardService {

    @Autowired
    private KeyPartStandardRepository keyPartStandardRepository;
    @Override
    public KeyPartStandard save(KeyPartStandard keyPartStandard) {
        return keyPartStandardRepository.save(keyPartStandard);
    }

    @Override
    public KeyPartStandard findOne(String id) {
        return keyPartStandardRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        keyPartStandardRepository.delete(id);
    }

    @Override
    public Page<KeyPartStandard> findAll(Specification<KeyPartStandard> spec, Pageable pageable) {
        return keyPartStandardRepository.findAll(spec,pageable);
    }
}
