package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import com.jrtech.ffmp.data.repositories.TaskEquipemtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangliang on 2016/8/18.
 */
@Service
public class TaskEquipemtServiceImpl implements TaskEquipemtService {
    @Autowired
    private TaskEquipemtRepository repository;

    @Override
    public Page<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask,Pageable pageable) {
        return repository.findByMaintenanceTask(maintenanceTask,pageable);
    }

    @Override
    public TaskEquipemt save(TaskEquipemt taskEquipemt) {
        return repository.save(taskEquipemt);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public List<TaskEquipemt> findByMaintenanceTaskAndDescriptionNot(MaintenanceTask maintenanceTask, String description) {
        return repository.findByMaintenanceTaskAndDescriptionNot(maintenanceTask, description);
    }

    @Override
    public List<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask) {
        return repository.findByMaintenanceTask(maintenanceTask);
    }
}
