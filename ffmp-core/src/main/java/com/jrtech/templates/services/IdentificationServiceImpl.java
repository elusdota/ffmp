package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Employee;
import com.jrtech.ffmp.data.entities.Identification;
import com.jrtech.ffmp.data.repositories.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/10/27.
 */
@Service
public class IdentificationServiceImpl implements IdentificationService {
    @Autowired
    private IdentificationRepository repository;

    @Override
    public Identification findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public Identification save(Identification identification) {
        return repository.save(identification);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Iterable<Identification> findByEmployee(Employee employee) {
        return repository.findByEmployee(employee);
    }
}
