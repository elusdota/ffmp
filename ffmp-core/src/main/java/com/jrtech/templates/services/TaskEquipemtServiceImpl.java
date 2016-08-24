package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import com.jrtech.ffmp.data.repositories.TaskEquipemtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangliang on 2016/8/18.
 */
@Service
public class TaskEquipemtServiceImpl implements TaskEquipemtService {
    @Autowired
    private TaskEquipemtRepository repository;

    @Override
    public Iterable<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask) {
        return repository.findByMaintenanceTask(maintenanceTask);
    }

    @Override
    public TaskEquipemt save(TaskEquipemt taskEquipemt) {
        return repository.save(taskEquipemt);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}