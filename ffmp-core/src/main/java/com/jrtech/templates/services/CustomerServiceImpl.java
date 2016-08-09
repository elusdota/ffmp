package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Customer;
import com.jrtech.ffmp.data.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/23.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public Page<Customer> findAll(Specification<Customer> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findOneByName(String name) {
        return repository.findOneByName(name);
    }

    @Override
    public Customer findOneByAccount(Account account) {
        return repository.findOneByAccount(account);
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Customer findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public boolean isDuplicateNameOnSameLevel(Customer customer) {
        Customer customer1 = repository.findOneByName(customer.getName());
        if (null == customer1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<Customer> findByNameLike(String name) {
        return repository.findByNameLike(name);
    }

}
