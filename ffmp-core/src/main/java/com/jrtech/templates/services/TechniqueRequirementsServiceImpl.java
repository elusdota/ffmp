package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.TechniqueRequirements;
import com.jrtech.ffmp.data.repositories.TechniqueRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by suelmer on 2016/9/4.
 */
@Service
public class TechniqueRequirementsServiceImpl implements TechniqueRequirementsService {

    @Autowired
    private TechniqueRequirementsRepository techniqueRequirementsRepository;

    @Override
    public TechniqueRequirements save(TechniqueRequirements techniqueRequirements) {
        return techniqueRequirementsRepository.save(techniqueRequirements);
    }

    @Override
    public TechniqueRequirements findOne(String id) {
        return techniqueRequirementsRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        techniqueRequirementsRepository.delete(id);
    }
}
