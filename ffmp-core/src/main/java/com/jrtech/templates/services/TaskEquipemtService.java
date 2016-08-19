package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;

/**
 * Created by jiangliang on 2016/8/18.
 */
public interface TaskEquipemtService {
    Iterable<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask);

    TaskEquipemt save(TaskEquipemt taskEquipemt);

    void delete(String id);
}
