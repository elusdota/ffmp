package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/23.
 */
public interface CustomerService {
    Page<Customer> findAll(Specification<Customer> spec, Pageable pageable);

    Iterable<Customer> findAll();

    Customer findOneByName(String name);

    Customer findOneByAccount(Account account);

    Customer save(Customer customer);

    void delete(String id);

    Customer findOne(String id);

    boolean isDuplicateNameOnSameLevel(Customer customer);
}
