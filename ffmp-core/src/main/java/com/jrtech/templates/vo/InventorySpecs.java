package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.Inventory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/29.库存多条件查询组装，elus
 */
public class InventorySpecs {
    public static Specification<Inventory> spec(InventorySearch inventorySearch) {
        return new Specification<Inventory>() {
            public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                Path<String> namePath = root.get("name");
                Path<String> typePath = root.get("type");
                Path<String> manufacturerPath = root.get("manufacturer");
                Path<String> modelPath = root.get("model");
                List<Predicate> list = new ArrayList<Predicate>();
                if (inventorySearch.getName() != null) {
                    list.add(builder.like(namePath, "%" + inventorySearch.getName() + "%"));
                }
                if (inventorySearch.getType() != null) {
                    list.add(builder.like(typePath, "%" + inventorySearch.getType() + "%"));
                }
                if (inventorySearch.getManufacturer() != null) {
                    list.add(builder.like(manufacturerPath, "%" + inventorySearch.getManufacturer() + "%"));
                }
                if (inventorySearch.getModel() != null) {
                    list.add(builder.like(modelPath, "%" + inventorySearch.getModel() + "%"));
                }
                Predicate[] p = new Predicate[list.size()];
                return builder.and(list.toArray(p));
            }
        };
    }

}
