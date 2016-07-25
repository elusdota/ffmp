package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/7/23.
 */
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, String>, JpaSpecificationExecutor {
    Customer findOneByName(String name);

    Customer findOneByAccount(Account account);
}
