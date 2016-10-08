package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Annexes;
import com.jrtech.ffmp.data.entities.Contract;
import com.jrtech.ffmp.data.repositories.AnnexesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by suelmer on 2016/9/30.
 */
@Service
public class AnnexesServiceImpl implements AnnexesService {
    @Autowired
    private AnnexesRepository annexesRepository;
    @Override
    public Iterable<Annexes> annexesList() {
        return annexesRepository.findAll();
    }

    @Override
    public Annexes save(Annexes annexes) {
        return annexesRepository.save(annexes);
    }

    @Override
    public Annexes findOne(String id) {
        return annexesRepository.findOne(id);
    }

    @Override
    public void deleteAnnexes(String id) {
        annexesRepository.delete(id);
    }

    @Override
    public List<Annexes> getAnexesByContract(Contract contract) {
        return annexesRepository.getAnexesByContract(contract);
    }
}
