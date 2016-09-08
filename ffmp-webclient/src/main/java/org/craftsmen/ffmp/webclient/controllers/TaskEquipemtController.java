package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskEquipemt;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import com.jrtech.templates.vo.TaskEquipemtVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/8/18.
 */
@RestController
@RequestMapping(value = "/rest/taskEquipemt")
public class TaskEquipemtController {
    @Autowired
    private TaskEquipemtService service;
    @Autowired
    private TaskRuntimeService taskRuntimeService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    static final Logger logger = LogManager.getLogger(TaskEquipemtController.class.getName());

    @RequestMapping(value = "/findByMaintenanceTask",method = RequestMethod.POST)
    public JSONListData findByMaintenanceTask(@RequestBody TableGetDataParameters parameters) {
        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(parameters.getSearch());
        PageableImpl pageable = new PageableImpl(parameters);
        Page<TaskEquipemt> taskEquipemts = service.findByMaintenanceTask(maintenanceTask,pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(taskEquipemts.getTotalElements());
        jld.setRows(taskEquipemts.getContent());
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public TaskEquipemt save(@RequestBody TaskEquipemtVO taskEquipemtVO){
        TaskEquipemt taskEquipemt = new TaskEquipemt();
        taskEquipemt.setDescription(taskEquipemtVO.getDescription());
        Equipment equipment=equipmentService.findOneByCode(taskEquipemtVO.getEquipmentCode());
        MaintenanceTask maintenanceTask=taskRuntimeService.findOne(taskEquipemtVO.getMaintenanceTaskId());
       if( maintenanceTask.getMaintenanceProject().getEquipments().contains(equipment)){
           taskEquipemt.setEquipment(equipment);
           taskEquipemt.setMaintenanceTask(maintenanceTask);
           logger.info(userDetailsUtils.getCurrent().getUsername() +
                   ":创建任务设备，设备编号--"+taskEquipemt.getEquipment().getName()+"。任务名称--"+maintenanceTask.getName());
           return service.save(taskEquipemt);
       }else {
           throw new ServiceException("该设备不在此任务项目设备列表中！");
       }

    }

}
