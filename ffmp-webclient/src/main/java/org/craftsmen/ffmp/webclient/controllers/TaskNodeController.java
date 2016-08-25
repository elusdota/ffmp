package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.FlowchartSteps;
import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.ffmp.data.entities.TaskDefinition;
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
        System.out.println("任务id---------"+historyTaskNodeVO.getMaintenanceTaskId());
        System.out.println("步骤---------" + historyTaskNodeVO.getStepResult());
        String userName =  userDetailsUtils.getCurrent().getUsername();
        System.out.println("userName---------" +userName);
        HistoryTaskNode historyTaskNode=new HistoryTaskNode();
        historyTaskNode.setMaintenanceTask(taskRuntimeService.findOne(historyTaskNodeVO.getMaintenanceTaskId()));
        historyTaskNode.setName(getShtep(historyTaskNodeVO.getMaintenanceTaskId()).getName());
        historyTaskNode.setDueDate(new Date());
        historyTaskNode.setDelegate(accountService.findOneByName(userName));
        historyTaskNode.setDescription(historyTaskNodeVO.getStepResult());
        historyTaskNode.setFlowchartSteps(getShtep(historyTaskNodeVO.getMaintenanceTaskId()));
        historyTaskNode =  service.save(historyTaskNode);
        System.out.println("historyTaskNode---------" + historyTaskNode.getId());
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
//        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(id);
//        HistoryTaskNode historyTaskNode = taskHistoryService.findByMaintenanceTaskOrderByDueDateDesc(maintenanceTask).get(0);
//        if (null != historyTaskNode) {
//            FlowchartSteps flowchartSteps = flowchartStepsService.findOneByParametric(historyTaskNode.getFlowchartSteps().getCatch(historyTaskNode.getDescription()));
//            return null == flowchartSteps ? null : flowchartSteps;
//        } else {
//            FlowchartSteps flowchartSteps = flowchartStepsService.findOneByParametric("st");
//            return flowchartSteps;
//        }
    }

    public FlowchartSteps getShtep(String id) {
        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(id);
        List<HistoryTaskNode> historyTaskNodes=taskHistoryService.findByMaintenanceTaskOrderByDueDateDesc(maintenanceTask);
        if (historyTaskNodes.size() > 0) {
            HistoryTaskNode historyTaskNode = historyTaskNodes.get(0);
            FlowchartSteps flowchartSteps = findOneByTaskDefinitionAndParametric(maintenanceTask.getTaskDefinition(),historyTaskNode.getFlowchartSteps().getCatch(historyTaskNode.getDescription()));
            return null == flowchartSteps ? null : flowchartSteps;
        } else {
            FlowchartSteps flowchartSteps = findOneByTaskDefinitionAndParametric(maintenanceTask.getTaskDefinition(),"st");
            return flowchartSteps;
        }
    }
    private FlowchartSteps findOneByTaskDefinitionAndParametric(TaskDefinition taskDefinition,String parametric){
        FlowchartSteps flowchartSteps = flowchartStepsService.findOneByTaskDefinitionAndParametric(taskDefinition,parametric);
        return flowchartSteps;
    }
}
