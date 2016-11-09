package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.templates.services.ServiceException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jiangliang on 2016/11/9.
 */
public class EquipmentSpecs<T> {
    public Specification<T> spec(Collection<MrrStandard> mrrStandards,MaintenanceProject maintenanceProject) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                Path<MaintenanceProject> owner = root.get("owner");
                Path<String> typemax = root.get("typemax");
                Path<String> typemin = root.get("typemin");
                List<Predicate> list = new ArrayList<Predicate>();
                if (maintenanceProject != null) {
                    list.add(builder.equal(owner, maintenanceProject));
                }
                if(mrrStandards.size()>0){
                    mrrStandards.forEach(mrrStandard -> {
                        if (mrrStandard != null) {
                            list.add(builder.equal(typemin, mrrStandard.getName()));
                            if (mrrStandard.getParent() != null) {
                                list.add(builder.equal(typemax, mrrStandard.getParent().getName()));
                            }
                        }
                    });
                }else{
                    throw new ServiceException("无数据！");
                }

                Predicate[] p = new Predicate[list.size()];
                return builder.and(list.toArray(p));
            }
        };
    }
}
