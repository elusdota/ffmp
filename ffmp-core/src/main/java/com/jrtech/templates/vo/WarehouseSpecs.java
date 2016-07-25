package com.jrtech.templates.vo;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/29.采购，入库，出库对象的查询封装成jpa Specification对象，elus
 */
public class WarehouseSpecs<T> {
	public Specification<T> spec(InventorySearch inventorySearch) {
		return new Specification<T>() {
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				Path<String> numberPath = root.get("number");
				Path<String> executorPath = root.get("executor");
				Path<Date> datePath = root.get("date");
				List<Predicate> list = new ArrayList<Predicate>();
				if (inventorySearch.getNumber() != null) {
					list.add(builder.like(numberPath,
							"%" + inventorySearch.getNumber() + "%"));
				}
				if (inventorySearch.getExecutor() != null) {
					list.add(builder.like(executorPath,
							"%" + inventorySearch.getExecutor() + "%"));
				}
				if (inventorySearch.getStartdate() != null
						&& inventorySearch.getEnddate() != null) {
					list.add(builder.between(datePath,
							inventorySearch.getStartdate(),
							inventorySearch.getEnddate()));
				}
				Predicate[] p = new Predicate[list.size()];
				return builder.and(list.toArray(p));
			}
		};
	}
}
