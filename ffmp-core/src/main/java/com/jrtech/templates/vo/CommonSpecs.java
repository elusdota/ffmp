package com.jrtech.templates.vo;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/15.
 */
public class CommonSpecs<T> {
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
                Predicate[] p = new Predicate[list.size()];
                return builder.and(list.toArray(p));
            }
        };
    }
}
