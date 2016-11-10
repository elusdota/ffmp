package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.Inspection;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.MrrStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    @Autowired
    private InspectionService inspectionService;

    @Override
    @Scheduled(cron = "0 1 0 1 1-12 ?")
//    @Scheduled(cron = "0 0 22 * * ?")
    public void buildScheduledTask() {
        maintenanceProjectService.findByTerminate(false).forEach(maintenanceProject -> {
            MaintenanceTask maintenanceTask = taskRuntimeService.findOneByName(getName());
            if (maintenanceTask == null) {
                LocalDate today = LocalDate.now();
                int year = today.getYear();
                taskRuntimeService.save(buildMaintenanceTask(maintenanceProject, "巡检任务", maintenanceProject.getCode()+year+getName(), getInspection(maintenanceProject)));
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

    public MaintenanceTask buildMaintenanceTask(MaintenanceProject maintenanceProject, String taskDefinition, String name, String description) {
        MaintenanceTask maintenanceTask = new MaintenanceTask();
        maintenanceTask.setMaintenanceProject(maintenanceProject);
        maintenanceTask.setTaskDefinition(taskDefinitionService.findOneByName(taskDefinition));
        maintenanceTask.setCustomer(maintenanceProject.getCustomer());
        maintenanceTask.setDelegate(maintenanceProject.getDelegate());
        maintenanceTask.setName(name);
        Calendar calender1 = Calendar.getInstance();
        calender1.setTime(new Date());
        calender1.add(Calendar.DATE, maintenanceProject.getDays() - 1);
        maintenanceTask.setStartdate(calender1.getTime());
        Calendar calender = Calendar.getInstance();
        calender.setTime(new Date());
        calender.add(Calendar.DATE, maintenanceProject.getDays() + 4);
        maintenanceTask.setEnddate(calender.getTime());
        if (null != description) {
            maintenanceTask.setDescription(description);
        }
        if(taskDefinition.equals("巡检任务")&&getName().equals("月巡检")){
         maintenanceTask.setType("月度巡检");
        }
        if(taskDefinition.equals("巡检任务")&&getName().equals("年度巡检")){
            maintenanceTask.setType("年度巡检");
        }
        if(taskDefinition.equals("巡检任务")&&(getName().equals("第一季度巡检")||getName().equals("第二季度巡检")||getName().equals("第三季度巡检"))){
            maintenanceTask.setType("季度巡检");
        }
        return maintenanceTask;
    }

    private MaintenanceTask buildEquipmentTask(MaintenanceProject maintenanceProject) {
        MaintenanceTask maintenanceTask = buildMaintenanceTask(maintenanceProject, "维修任务", "定期检修", null);
        final String[] description = {""};
        maintenanceProject.getEquipments().forEach(equipment -> {
            MrrStandard mrrStandard = mrrStandardService.findOneByName(equipment.getTypemin());
            mrrStandard.getTechniqueRequirementsList().forEach(techniqueRequirements -> {
                Date date1 = new Date();
                Date date2 = new Date();
                if (techniqueRequirements.getMaturity() == "生产日期") {
                    date1 = getChangDate(equipment.getProductionDate(), techniqueRequirements.getChangetime(), -1);
                    date2 = getDate(equipment.getProductionDate(), techniqueRequirements.getLifetime(), -1);
                } else {
                    date1 = getChangDate(equipment.getInputDate(), techniqueRequirements.getChangetime(), -1);
                    date2 = getDate(equipment.getInputDate(), techniqueRequirements.getLifetime(), -1);
                }
                if (techniqueRequirements.getChangetime() > 12 && date1.after(new Date()) && date1.before(getDate(new Date(), 0, 1))) {
                    description[0] = description[0] + equipment.getCode() + ",到期维修。技术要求：" + techniqueRequirements.getName();
                }
                if (techniqueRequirements.getLifetime() > 0 && date2.after(new Date()) && date2.before(getDate(new Date(), 0, 1))) {
                    description[0] = description[0] + equipment.getCode() + ",到期报废。技术要求：" + techniqueRequirements.getName();
                }
            });
        });
        if (description[0].length() > 0) {
            maintenanceTask.setDescription(description[0]);
            return maintenanceTask;
        } else {
            return null;
        }
    }

    private Date getDate(Date date1, int year, int month) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        calender.add(Calendar.YEAR, year);
        calender.add(Calendar.MONTH, month);
        return calender.getTime();
    }
    private Date getChangDate(Date date1, int month1, int month2) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        calender.add(Calendar.MONTH, month1);
        calender.add(Calendar.MONTH, month2);
        return calender.getTime();
    }

    private String getName() {
        LocalDate today = LocalDate.now();
//        int year = today.getYear();
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
                name = "月巡检";
        }
        return name;
    }

    public String getInspection(MaintenanceProject maintenanceProject) {
        String InspectionDescription = maintenanceProject.getName();
        InspectionDescription = InspectionDescription + "-----";
        Inspection inspection;
        switch (getName()) {
            case "月巡检":
                inspection = inspectionService.findByMaintenanceProjectAndName(maintenanceProject, "月度巡检");
                break;
            case "年度巡检":
                inspection = inspectionService.findByMaintenanceProjectAndName(maintenanceProject, "年度巡检");
                break;
            default:
                inspection = inspectionService.findByMaintenanceProjectAndName(maintenanceProject, "季度巡检");
        }
        if (null != inspection) {
            InspectionDescription = InspectionDescription + "巡检百分比为：" + inspection.getRatio() + "%。巡检" +
                    "设施分类为:";
            final String[] mrr = {""};
            inspection.getMrrStandards().forEach(mrrStandard -> {
                mrr[0] = mrr[0] + mrrStandard.getName() + "，编码：" + mrrStandard.getCode() + ";";
            });
            InspectionDescription = InspectionDescription + mrr[0] + "巡检时请按照设施分类的技术标准执行。扫码时可调取技术标准";
        }
        return InspectionDescription;
    }
}
