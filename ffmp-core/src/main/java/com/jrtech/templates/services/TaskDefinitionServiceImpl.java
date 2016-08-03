package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.TaskDefinition;
import com.jrtech.ffmp.data.repositories.TaskDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/8/1.
 */
@Service
public class TaskDefinitionServiceImpl implements TaskDefinitionService {
    @Autowired
    private TaskDefinitionRepository repository;

    @Override
    public TaskDefinition save(TaskDefinition taskDefinition) {
        return repository.save(taskDefinition);
    }

    @Override
    public TaskDefinition findOneByName(String name) {
        return repository.findOneByName(name);
    }
}
