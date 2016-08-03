package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.TaskDefinition;

/**
 * Created by jiangliang on 2016/8/1.任务定义接口,elus
 */
public interface TaskDefinitionService {
    TaskDefinition save(TaskDefinition taskDefinition);

    TaskDefinition findOneByName(String name);
}
