package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Purchases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by jiangliang on 2016/6/28.采购单Service,elus
 */
public interface PurchasesService {
    /**
     * 查询出所有采购单
     *
     * @return 采购单列表
     */
    Page<Purchases> findAll(Specification<Purchases> spec, Pageable pageable);

    /**
     * 保存采购单
     *
     * @return 采购单
     */
    Purchases save(Purchases purchases);

    /**
     * 通过id查询
     *
     * @param id
     * @return 采购单
     */
    Purchases findOne(String id);

    /**
     * 通过id删除
     *
     * @param id
     */
    void delete(String id);
}
