package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/8/11.
 */
public interface EmployeeService {
    Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);

    Employee save(Employee employee);

    void delete(String id);

    Employee findOne(String id);
}
