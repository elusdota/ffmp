package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jiangliang on 2016/8/18.
 */
@Repository
public interface TaskEquipemtRepository extends CrudRepository<TaskEquipemt, String> {
    Iterable<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask);
}
