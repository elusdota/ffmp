package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/8/11.
 */
@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>, JpaSpecificationExecutor {
    Employee findOneByCode(String code) ;
}
