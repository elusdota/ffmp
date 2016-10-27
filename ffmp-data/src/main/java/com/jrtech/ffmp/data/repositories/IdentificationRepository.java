package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Employee;
import com.jrtech.ffmp.data.entities.Identification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/10/27.
 */
@Repository
public interface IdentificationRepository extends CrudRepository<Identification, String> {
    Iterable<Identification> findByEmployee(Employee employee);
}
