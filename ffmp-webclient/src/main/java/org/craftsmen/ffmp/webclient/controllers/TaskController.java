package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskDefinition;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiangliang on 2016/7/17.任务控制器,elus
 */
@RestController
@RequestMapping(value = "/rest/task")
public class TaskController {
    @Autowired
    private TaskRuntimeService service;
    @Autowired
    private TaskHistoryService taskHistoryService;
    @Autowired
    private TaskDefinitionService taskDefinitionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RepairFormService repairFormService;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;

    @RequestMapping(value = "/findRunTask", method = RequestMethod.POST)
    public JSONListData findRunTask(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<MaintenanceTask> maintenanceTasks = service.findBySuspended(pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(maintenanceTasks.getTotalElements());
        jld.setRows(maintenanceTasks.getContent());
        return jld;
    }

    @RequestMapping(value = "/findHistoryTask", method = RequestMethod.POST)
    public JSONListData findHistoryTask(@RequestBody TableGetDataParameters parameters) {
        HistoryTaskSpecs<MaintenanceTask> historyTaskSpecs = new HistoryTaskSpecs<MaintenanceTask>();
        historyTaskSpecs.setCustomer(customerService.findOneByAccount(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername())));
        PageableImpl pageable = new PageableImpl(parameters);
        Page<MaintenanceTask> maintenanceTasks = service.findAll(historyTaskSpecs.spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(maintenanceTasks.getTotalElements());
        jld.setRows(maintenanceTasks.getContent());
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public MaintenanceTask create(@RequestBody MaintenanceTask maintenanceTask) {
        if (!maintenanceTask.getRepairnumber().isEmpty()) {
            if (repairFormService.findOneByCode(maintenanceTask.getRepairnumber()) == null) {
                throw new ServiceException("报修单编号不存在！请检查数据。");
            }
        }
        TaskDefinition taskDefinition = taskDefinitionService.findOneByName("维修任务");
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOneByCode(maintenanceTask.getMaintenanceProject().getCode());
        maintenanceTask.setMaintenanceProject(maintenanceProject);
        maintenanceTask.setTaskDefinition(taskDefinition);
        maintenanceTask.setCustomer(maintenanceProject.getCustomer());
        maintenanceTask.setDelegate(maintenanceProject.getDelegate());
        maintenanceTask.setOwner(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername()));
        return service.save(maintenanceTask);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public MaintenanceTask update(@RequestBody MaintenanceTask maintenanceTask) {
        return service.save(maintenanceTask);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceTask get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    @RequestMapping(value = "/repairFormCode", method = RequestMethod.GET)
    public MaintenanceTask getByRepairFormCode(@RequestParam("repairFormCode") String repairFormCode) {
        return service.findOneByRepairnumber(repairFormCode);
    }

    @RequestMapping(value = "/flowchart", method = RequestMethod.GET)
    public TaskDefinition getFlowchart(@RequestParam("id") String id) {
        MaintenanceTask maintenanceTask = service.findOne(id);
        TaskDefinition taskDefinition=taskDefinitionService.findOneByName(maintenanceTask.getTaskDefinition().getName());
        List<HistoryTaskNode> historyTaskNodes = taskHistoryService.findByMaintenanceTaskOrderByDueDateDesc(maintenanceTask);
        taskDefinition.getFlowchartStepses().forEach(flowchartSteps -> {
            HistoryTaskNode historyTaskNode=taskHistoryService.findOneByMaintenanceTaskAndFlowchartSteps(maintenanceTask, flowchartSteps);
            if (historyTaskNode==null) {
              flowchartSteps.setColor("without");
            }
            else{
                if(historyTaskNode.getDescription().equals("yes")){
                    flowchartSteps.setColor("approved");
                }else{
                    flowchartSteps.setColor("rejected");
                }
            }
        });
        return taskDefinition;
    }
}
