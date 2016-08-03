package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.FlowchartSteps;
import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.ffmp.data.entities.MaintenanceTask;
import com.jrtech.templates.services.FlowchartStepsService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.TaskHistoryService;
import com.jrtech.templates.services.TaskRuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET)
    public HistoryTaskNode get(@RequestParam("id") String id) {
        return service.findOne(id);
    }

    /**
     * 创建任务历史节点，elus
     * @param historyTaskNode
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public HistoryTaskNode create(@RequestBody HistoryTaskNode historyTaskNode) {
//        FlowchartSteps flowchartSteps=
        return service.save(historyTaskNode);
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
        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(id);
        HistoryTaskNode historyTaskNode = taskHistoryService.findByMaintenanceTaskOrderByDueDateAsc(maintenanceTask).get(0);
        if (null != historyTaskNode) {
            FlowchartSteps flowchartSteps = flowchartStepsService.findOneByParametric(historyTaskNode.getFlowchartSteps().getCatch(historyTaskNode.getDescription()));
            return null == flowchartSteps ? null : flowchartSteps;
        } else {
            FlowchartSteps flowchartSteps = flowchartStepsService.findOneByParametric("st");
            return flowchartSteps;
        }
    }

}
