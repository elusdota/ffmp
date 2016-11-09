package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inspection;
import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.ffmp.data.entities.TechniqueRequirements;
import com.jrtech.ffmp.data.repositories.MrrStandardRepository;
import com.jrtech.ffmp.data.repositories.TechniqueRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by suelmer on 2016/7/16.
 */
@Service
public class MrrStandardServiceImpl implements MrrStandardService {
    @Autowired
    private MrrStandardRepository mrrStandardRepository;
    @Autowired
    private TechniqueRequirementsRepository techniqueRequirementsRepository;

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
        return mrrStandardRepository.findAll(spec, pageable);
    }

    @Override
    public Iterable<MrrStandard> findAll() {
        return mrrStandardRepository.findAll();
    }

    @Override
    public Iterable<MrrStandard> findRoot() {
        return mrrStandardRepository.findRoot();
    }

    @Override
    public MrrStandard findOneByName(String name) {
        return mrrStandardRepository.findOneByName(name);
    }

    @Override
    public MrrStandard findOneByParent(MrrStandard parent) {
        return mrrStandardRepository.findOneByParent(parent);
    }

    @Override
    public MrrStandard findOneByCode(String code) {
        return mrrStandardRepository.findOneByCode(code);
    }

    @Override
    public Collection<MrrStandard> findExpired() {
        Collection<TechniqueRequirements> techniqueRequirementses = techniqueRequirementsRepository.findByLifetimeIsNotOrChangetimeIsNot(0, 0);
        Collection<MrrStandard> mrrStandards = new ArrayList<>();
        techniqueRequirementses.forEach(techniqueRequirements -> {
            if (!mrrStandards.contains(techniqueRequirements.getMrrStandard())) {
                mrrStandards.add(techniqueRequirements.getMrrStandard());
            }
        });
        return mrrStandards;
    }

    @Override
    public void delete(String id) {
        mrrStandardRepository.delete(id);
    }

}
