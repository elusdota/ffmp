package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by jiangliang on 2016/8/18.
 */
public interface TaskEquipemtService {
    Page<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask,Pageable pageable);

    TaskEquipemt save(TaskEquipemt taskEquipemt);

    void delete(String id);
}
