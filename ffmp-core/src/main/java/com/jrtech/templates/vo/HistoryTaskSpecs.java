package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/24.任务节点查询对象，elus
 */
public class HistoryTaskSpecs<T> {
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
                                         CriteriaBuilder builder ) {
                Path<String> name = root.get("name");
                Path<Boolean> suspended=root.get("suspended");
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(builder.equal(suspended, true));
                if (parameters.getSearch() != null) {
                    list.add(builder.like(name,
                            "%" + parameters.getSearch() + "%"));
                }
                if (getCustomerPredicates(root,builder).size()>0){
                    list.addAll(getCustomerPredicates(root,builder));
                }
                Predicate[] p = new Predicate[list.size()];
                return builder.and(list.toArray(p));
            }
        };
    }
    public  List<Predicate> getCustomerPredicates(Root<T> root,CriteriaBuilder builder){
        List<Predicate> list = new ArrayList<Predicate>();
        Path<Customer> customerPath=root.get("customer");
        if(customer!=null){
            list.add(builder.equal(customerPath,customer));
        }
        return list;
    }
}
