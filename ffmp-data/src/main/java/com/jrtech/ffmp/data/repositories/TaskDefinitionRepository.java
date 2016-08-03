package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.TaskDefinition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/8/1.
 */
@Repository
public interface TaskDefinitionRepository extends CrudRepository<TaskDefinition, String> {
    TaskDefinition findOneByName(String name);
}
