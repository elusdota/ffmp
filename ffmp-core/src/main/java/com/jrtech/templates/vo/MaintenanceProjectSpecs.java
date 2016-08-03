package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Customer;
import com.jrtech.templates.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/23.项目查询对象，elus
 */
@Service
public class MaintenanceProjectSpecs<T> {
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Specification<T> spec(TableGetDataParameters parameters) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                Path<String> name = root.get("name");
                List<Predicate> list = new ArrayList<Predicate>();
                if (parameters.getSearch() != null) {
                    list.add(builder.like(name,
                            "%" + parameters.getSearch() + "%"));
                }
                if (getCustomerPredicates(root, builder).size() > 0) {
                    list.addAll(getCustomerPredicates(root, builder));
                }
                Predicate[] p = new Predicate[list.size()];
                return builder.and(list.toArray(p));
            }
        };
    }

    public List<Predicate> getCustomerPredicates(Root<T> root, CriteriaBuilder builder) {
        List<Predicate> list = new ArrayList<Predicate>();
        Path<Customer> customerPath = root.get("customer");
        if (customer != null) {
            list.add(builder.equal(customerPath, customer));
        }
        return list;
    }
}
