package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiangliang on 2016/8/10.
 */
@Component
public class AutomaticDeploymentServiceImpl implements AutomaticDeploymentService {
    @Autowired
    private TaskRuntimeService taskRuntimeService;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;
    @Autowired
    private TaskDefinitionService taskDefinitionService;

    @Override
    @Scheduled(cron = "0 0 22 * * ?")
    public void buildScheduledTask() {
        System.out.println("自动任务生成---------"+new Date());
        maintenanceProjectService.findAll().forEach(maintenanceProject -> {
            MaintenanceTask maintenanceTask = taskRuntimeService.findOneByName(getName(maintenanceProject.getCode()));
            if (maintenanceTask == null) {
                taskRuntimeService.save(buildMaintenanceTask(maintenanceProject));
            }
        });
    }

    @Override
    public void replacementEquipmentTask() {
    }

    private MaintenanceTask buildMaintenanceTask(MaintenanceProject maintenanceProject) {
        MaintenanceTask maintenanceTask = new MaintenanceTask();
        maintenanceTask.setMaintenanceProject(maintenanceProject);
        maintenanceTask.setTaskDefinition(taskDefinitionService.findOneByName("巡检任务"));
        maintenanceTask.setCustomer(maintenanceProject.getCustomer());
        maintenanceTask.setDelegate(maintenanceProject.getDelegate());
        maintenanceTask.setName(getName(maintenanceProject.getCode()));
        maintenanceTask.setStartdate(new Date());
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(Calendar.DATE, 5);
        maintenanceTask.setEnddate(calender.getTime());
        maintenanceTask.setDescription("系统指定的巡检任务,请按标准执行巡检任务！");
        return maintenanceTask;
    }

    private String getName(String code) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        System.out.println(month+"-------------------------------");
        String name = "";
        switch (month) {
            case 3:
                name = "第一季度巡检";
                break;
            case 6:
                name = "第二季度巡检";
                break;
            case 9:
                name = "第三季度巡检";
                break;
            case 12:
                name = "年度巡检";
                break;
            default:
                name = month + "月巡检";
        }
        return code + year + name;
    }
}
