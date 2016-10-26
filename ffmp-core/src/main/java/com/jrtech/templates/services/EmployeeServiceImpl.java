package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Employee;
import com.jrtech.ffmp.data.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/8/11.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    @Override
    public Page<Employee> findAll(Specification<Employee> spec, Pageable pageable) {
        return repository.findAll(spec,pageable);
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void delete(String id) {
         repository.delete(id);
    }

    @Override
    public Employee findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public Employee findOneByCode(String code) {
        return repository.findOneByCode(code);
    }
}
