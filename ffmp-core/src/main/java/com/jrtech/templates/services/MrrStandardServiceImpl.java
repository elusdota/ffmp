package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.ffmp.data.repositories.MrrStandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by suelmer on 2016/7/16.
 */
@Service
public class MrrStandardServiceImpl implements MrrStandardService {
    @Autowired
    private MrrStandardRepository mrrStandardRepository;

    @Override
    public MrrStandard save(MrrStandard mrrStandard) {
        return mrrStandardRepository.save(mrrStandard);
    }

    @Override
    public MrrStandard findOne(String id) {
        return mrrStandardRepository.findOne(id);
    }

    @Override
    public Page<MrrStandard> findAll(Specification<MrrStandard> spec, Pageable pageable) {
        return mrrStandardRepository.findAll(spec,pageable);
    }
}
