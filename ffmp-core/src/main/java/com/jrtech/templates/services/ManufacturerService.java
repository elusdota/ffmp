package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by suelmer on 2016/7/29.
 */
public interface ManufacturerService {
    /**
     * 创建生产厂商
     * @param manufacturer
     * @return MrrStandard
     */
    Manufacturer save(Manufacturer manufacturer);

    /**
     * 通过id查询 生产厂商
     * @param id
     * @return
     */
    Manufacturer findOne(String id);

    /**
     * 通过id删除生产厂商
     * @param id
     */
    void delete(String id);

    /**
     * 查询所有的生产厂商
     * @param spec
     * @param pageable
     * @return
     */
    Page<Manufacturer> findAll(Specification<Manufacturer> spec, Pageable pageable);
}
