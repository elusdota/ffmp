package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/17.
 */
@RestController
@RequestMapping(value = "/rest/task")
public class TaskController {
    @Autowired
    private TaskRuntimeService service;
    @Autowired
    private TaskHistoryService taskHistoryService;
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
        if (maintenanceTask.getRepairnumber() != null) {
            if (repairFormService.findOneByCode(maintenanceTask.getRepairnumber()) == null) {
                throw new ServiceException("报修单编号不存在！请检查数据。");
            }
        }
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOneByName(maintenanceTask.getMaintenanceProject().getName());
        HistoryTaskNode historyTaskNode = new HistoryTaskNode();
        historyTaskNode.setName("创建任务");
        historyTaskNode.setDueDate(new Date());
        historyTaskNode.setType("start");
        historyTaskNode.setDelegate(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername()));
        historyTaskNode.setMaintenanceTask(maintenanceTask);
        maintenanceTask.getHistoryTaskNodes().add(historyTaskNode);
        maintenanceTask.setMaintenanceProject(maintenanceProject);
        maintenanceTask.setCustomer(maintenanceProject.getCustomer());
        maintenanceTask.setDelegate(maintenanceProject.getDelegate());
        maintenanceTask.setOwner(accountService.findOneByName(userDetailsUtils.getCurrent().getUsername()));
        return service.save(maintenanceTask);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public MaintenanceTask update(@RequestBody MaintenanceTask maintenanceTask) {
        maintenanceTask.getHistoryTaskNodes().forEach(historyTaskNode -> {
            historyTaskNode.setMaintenanceTask(maintenanceTask);
        });
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
    public List<HistoryTaskNode> getFlowchart(@RequestParam("id") String id) {
        MaintenanceTask maintenanceTask = service.findOne(id);
        List<HistoryTaskNode> historyTaskNodes = taskHistoryService.findByMaintenanceTask(maintenanceTask);
        return historyTaskNodes;
    }
}
