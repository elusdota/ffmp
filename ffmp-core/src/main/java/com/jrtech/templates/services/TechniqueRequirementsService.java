package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.TechniqueRequirements;

/**
 * Created by suelmer on 2016/9/4.
 */
public interface TechniqueRequirementsService {

    /**
     * 创建
     * @param techniqueRequirements
     * @return MrrStandard
     */
    TechniqueRequirements save(TechniqueRequirements techniqueRequirements);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    TechniqueRequirements findOne(String id);

    /**
     * 通过id删除
     * @param id
     */
    void delete(String id);
}
