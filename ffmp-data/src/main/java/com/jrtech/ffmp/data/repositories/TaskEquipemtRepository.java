package com.jrtech.ffmp.data.repositories;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangliang on 2016/8/18.
 */
@Repository
public interface TaskEquipemtRepository extends CrudRepository<TaskEquipemt, String> {
    Page<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask,Pageable pageable);
    List<TaskEquipemt> findByMaintenanceTaskAndDescriptionNot(MaintenanceTask maintenanceTask,String description);
    List<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask);
}
