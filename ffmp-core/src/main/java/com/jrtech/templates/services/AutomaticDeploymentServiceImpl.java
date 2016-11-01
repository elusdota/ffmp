package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.MrrStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    @Autowired
    private MrrStandardService mrrStandardService;

    @Override
    @Scheduled(cron = "0 1 0 1 1-12 ?")
//    @Scheduled(cron = "0 0 22 * * ?")
    public void buildScheduledTask() {
        maintenanceProjectService.findByTerminate(false).forEach(maintenanceProject -> {
            MaintenanceTask maintenanceTask = taskRuntimeService.findOneByName(getName(maintenanceProject.getCode()));
            if (maintenanceTask == null) {
                taskRuntimeService.save(buildMaintenanceTask(maintenanceProject, "巡检任务", getName(maintenanceProject.getCode()), "系统指定的巡检任务,请按标准执行巡检任务！"));
            }
        });
    }

    @Override
    @Scheduled(cron = "0 10 0 1 1-12 ?")
    public void replacementEquipmentTask() {
        maintenanceProjectService.findByTerminate(false).forEach(maintenanceProject -> {
            MaintenanceTask maintenanceTask = buildEquipmentTask(maintenanceProject);
            if (null != maintenanceTask) {
                taskRuntimeService.save(maintenanceTask);
            }
        });
    }

    public MaintenanceTask buildMaintenanceTask(MaintenanceProject maintenanceProject,String taskDefinition,String name,String description) {
        MaintenanceTask maintenanceTask = new MaintenanceTask();
        maintenanceTask.setMaintenanceProject(maintenanceProject);
        maintenanceTask.setTaskDefinition(taskDefinitionService.findOneByName(taskDefinition));
        maintenanceTask.setCustomer(maintenanceProject.getCustomer());
        maintenanceTask.setDelegate(maintenanceProject.getDelegate());
        maintenanceTask.setName(name);
        Calendar calender1 = Calendar.getInstance();
        calender1.setTime(new Date());
        calender1.add(Calendar.DATE, maintenanceProject.getDays());
        maintenanceTask.setStartdate(calender1.getTime());
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(Calendar.DATE, maintenanceProject.getDays()+5);
        maintenanceTask.setEnddate(calender.getTime());
        if(null!=description){
            maintenanceTask.setDescription(description);
        }
        return maintenanceTask;
    }

    private MaintenanceTask buildEquipmentTask(MaintenanceProject maintenanceProject) {
        MaintenanceTask maintenanceTask = buildMaintenanceTask(maintenanceProject,"维修任务","定期检修",null);
        final String[] description = {};
        maintenanceProject.getEquipments().forEach(equipment -> {
            MrrStandard mrrStandard = mrrStandardService.findOneByName(equipment.getTypemin());
            mrrStandard.getTechniqueRequirementsList().forEach(techniqueRequirements -> {
            Date date1 = new Date();
            Date date2 = new Date();
            if (techniqueRequirements.getMaturity() == "生产日期") {
                date1 = getDate(equipment.getProductionDate(), techniqueRequirements.getChangetime(), -1);
                date2 = getDate(equipment.getProductionDate(), techniqueRequirements.getLifetime(), -1);
            } else {
                date1 = getDate(equipment.getInputDate(), techniqueRequirements.getChangetime(), -1);
                date2 = getDate(equipment.getInputDate(), techniqueRequirements.getLifetime(), -1);
            }
            if (techniqueRequirements.getChangetime() > 0 && date1.after(new Date()) && date1.before(getDate(new Date(), 0, 1))) {
                description[0] = description[0] + equipment.getCode() + ",到期维修。技术要求："+techniqueRequirements.getName();
            }
            if (techniqueRequirements.getLifetime() > 0 && date2.after(new Date()) && date2.before(getDate(new Date(), 0, 1))) {
                description[0] = description[0] + equipment.getCode() + ",到期报废。技术要求："+techniqueRequirements.getName();
            }
            });
        });
        if(null!=description[0]){
            maintenanceTask.setDescription(description[0]);
            return maintenanceTask;
        }else {
            return null;
        }
    }

    private Date getDate(Date date1, int year,int month) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        calender.add(Calendar.YEAR, year);
        calender.add(Calendar.MONTH, month);
        return calender.getTime();
    }

    private String getName(String code) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
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
