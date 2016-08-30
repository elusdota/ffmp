package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.*;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.HistoryTaskNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by jiangliang on 2016/7/17.任务节点控制器，elus
 */
@RestController
@RequestMapping(value = "/rest/taskNode")
public class TaskNodeController {
    @Autowired
    private TaskHistoryService service;
    @Autowired
    private TaskRuntimeService taskRuntimeService;
    @Autowired
    private TaskHistoryService taskHistoryService;
    @Autowired
    private FlowchartStepsService flowchartStepsService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;

    @RequestMapping(method = RequestMethod.GET)
    public HistoryTaskNode get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    /**
     * 创建任务历史节点，elus
     *
     * @param historyTaskNodeVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public HistoryTaskNode create(@RequestBody HistoryTaskNodeVO historyTaskNodeVO) {
        String userName = userDetailsUtils.getCurrent().getUsername();
        Account account=accountService.findOneByName(userName);
        MaintenanceTask maintenanceTask=taskRuntimeService.findOne(historyTaskNodeVO.getMaintenanceTaskId());
        HistoryTaskNode historyTaskNode = service.save(bulidHistoryTaskNode(historyTaskNodeVO, maintenanceTask,account));
        if (getShtep(historyTaskNode.getMaintenanceTask().getId()).getParametric().equals("en") || getShtep(historyTaskNode.getMaintenanceTask().getId()).getParametric().equals("en1")) {
            maintenanceTask.setSuspended(true);
            taskRuntimeService.save(maintenanceTask);
            return service.save(bulidHistoryTaskNode(historyTaskNodeVO,maintenanceTask,account));
        } else {
            return historyTaskNode;
        }
    }

    public HistoryTaskNode bulidHistoryTaskNode(HistoryTaskNodeVO historyTaskNodeVO,MaintenanceTask maintenanceTask,Account account) {
        HistoryTaskNode historyTaskNode = new HistoryTaskNode();
        historyTaskNode.setMaintenanceTask(maintenanceTask);
        historyTaskNode.setName(getShtep(historyTaskNodeVO.getMaintenanceTaskId()).getName());
        historyTaskNode.setDueDate(new Date());
        historyTaskNode.setDelegate(account);
        historyTaskNode.setDescription(historyTaskNodeVO.getStepResult());
        historyTaskNode.setFlowchartSteps(getShtep(historyTaskNodeVO.getMaintenanceTaskId()));
        return historyTaskNode;
    }

    /**
     * 获取下一步的操作，elus
     *
     * @param id 任务id
     * @return 任务步骤
     */
    @RequestMapping(value = "/getSteps", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public FlowchartSteps getSteps(@RequestParam("id") String id) {
        return getShtep(id);
    }

    public FlowchartSteps getShtep(String id) {
        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(id);
        List<HistoryTaskNode> historyTaskNodes = taskHistoryService.findByMaintenanceTaskOrderByDueDateDesc(maintenanceTask);
        if (historyTaskNodes.size() > 0) {
            HistoryTaskNode historyTaskNode = historyTaskNodes.get(0);
            FlowchartSteps flowchartSteps = findOneByTaskDefinitionAndParametric(maintenanceTask.getTaskDefinition(), historyTaskNode.getFlowchartSteps().getCatch(historyTaskNode.getDescription()));
            return null == flowchartSteps ? null : flowchartSteps;
        } else {
            FlowchartSteps flowchartSteps = findOneByTaskDefinitionAndParametric(maintenanceTask.getTaskDefinition(), "st");
            return flowchartSteps;
        }
    }

    private FlowchartSteps findOneByTaskDefinitionAndParametric(TaskDefinition taskDefinition, String parametric) {
        FlowchartSteps flowchartSteps = flowchartStepsService.findOneByTaskDefinitionAndParametric(taskDefinition, parametric);
        return flowchartSteps;
    }
}
