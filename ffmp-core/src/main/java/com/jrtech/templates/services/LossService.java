package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Loss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/8/15.
 */
public interface LossService {
    /**
     * 查出所有报损单
     */
    Page<Loss> findAll(Specification<Loss> spec, Pageable pageable);

    /**
     * 保存报损单
     * @param loss
     * @return 报损单
     */
    Loss save(Loss loss);

    /**
     * 删除报损单
     * @param id
     */
    void delete(String id);

    /**
     * 通过id查出报损单
     * @param id
     * @return
     */
    Loss findOne(String id);
}
