package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.FlowchartSteps;
import com.jrtech.ffmp.data.entities.TaskDefinition;
import com.jrtech.ffmp.data.repositories.FlowchartStepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/8/1.
 */
@Service
public class FlowchartStepsServiceImpl implements FlowchartStepsService {
    @Autowired
    private FlowchartStepsRepository repository;

    @Override
    public FlowchartSteps findOneByParametric(String parametric) {
        return repository.findOneByParametric(parametric);
    }

    @Override
    public FlowchartSteps findOneByTaskDefinitionAndParametric(TaskDefinition taskDefinition, String parametric) {
        return repository.findOneByTaskDefinitionAndParametric(taskDefinition,parametric);
    }
}
