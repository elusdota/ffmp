package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.AutoUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by suelmer on 2016/10/20.
 */
public interface AutoUpdateService {

    /**
     * 上传一个新的app 版本
     * @param autoUpdate
     * @return
     */
    AutoUpdate save(AutoUpdate autoUpdate);

    /**
     * 查找最近更新的记录
     * @return
     */
    AutoUpdate getLastUpdate();
    /**
     * 查询所有自动更新记录
     * @param spec
     * @param pageable
     * @return
     */
    Page<AutoUpdate> findAll(Specification<AutoUpdate> spec, Pageable pageable);
}
