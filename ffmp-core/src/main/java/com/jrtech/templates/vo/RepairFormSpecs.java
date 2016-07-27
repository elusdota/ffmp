package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/25.
 */
public class RepairFormSpecs<T> {
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
                Path<String> code = root.get("code");
                Path<String> projectNumber = root.get("projectNumber");
                Path<String> person = root.get("person");
                List<Predicate> list = new ArrayList<Predicate>();
                if (parameters.getSearch()!= null) {
                    list.add(builder.like(code, "%" + parameters.getSearch() + "%"));
                    list.add(builder.like(projectNumber, "%" + parameters.getSearch() + "%"));
                    list.add(builder.like(person, "%" + parameters.getSearch() + "%"));
                }
                Predicate p1=null;
                Predicate[] p = new Predicate[list.size()];
                if (getCustomerPredicates(root, builder).size() > 0) {
                    list.addAll(getCustomerPredicates(root, builder));
                    p1=getCustomerPredicates(root, builder).get(0);
                    return builder.and(p1,builder.or(list.toArray(p)));
                }else{
                    if(list.size()>0){
                        return builder.and(builder.or(list.toArray(p)));
                    }else{
                        return builder.and(list.toArray(p));
                    }
                }
            }
        };
    }

    public List<Predicate> getCustomerPredicates(Root<T> root, CriteriaBuilder builder) {
        List<Predicate> list = new ArrayList<Predicate>();
        Path<Account> account = root.get("account");
        if (customer != null) {
            list.add(builder.and(builder.equal(account, customer.getAccount())));
        }
        return list;
    }
}
