package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Annexes;
import com.jrtech.ffmp.data.entities.Contract;

import java.util.List;

/**
 * Created by suelmer on 2016/9/30.
 */
public interface AnnexesService {
    public Iterable<Annexes> annexesList();

    public Annexes save(Annexes annexes);

    public Annexes findOne(String id);

    public void deleteAnnexes(String id);

    public List<Annexes> getAnexesByContract(Contract contract);
}
