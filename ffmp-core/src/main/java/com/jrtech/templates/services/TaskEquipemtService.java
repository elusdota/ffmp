package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jiangliang on 2016/8/18.任务设备Service(业务接口) elus
 */
public interface TaskEquipemtService {
    /**
     * 分页获取数据
     * @param maintenanceTask 任务对象
     * @param pageable 分页对象
     * @return
     */
    Page<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask, Pageable pageable);

    /**
     * 保存
     * @param taskEquipemt 任务设备对象
     * @return 任务设备
     */
    TaskEquipemt save(TaskEquipemt taskEquipemt);

    /**
     * 删除
     * @param id 任务设备id
     */
    void delete(String id);

    /**
     * 获取任务设备列表通过任务和注释分组
     * @param maintenanceTask
     * @param description
     * @return
     */
    List<TaskEquipemt> findByMaintenanceTaskAndDescriptionNot(MaintenanceTask maintenanceTask, String description);

    /**
     * 通过任务获取任务设备列表
     * @param maintenanceTask
     * @return 任务设备列表
     */
    List<TaskEquipemt> findByMaintenanceTask(MaintenanceTask maintenanceTask);
}
