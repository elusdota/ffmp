package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.FlowchartSteps;
import com.jrtech.ffmp.data.entities.TaskDefinition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/8/1.
 */
@Repository
public interface FlowchartStepsRepository extends CrudRepository<FlowchartSteps, String> {
    FlowchartSteps findOneByParametric(String parametric);
    FlowchartSteps findOneByTaskDefinitionAndParametric(TaskDefinition taskDefinition,String parametric);
}
