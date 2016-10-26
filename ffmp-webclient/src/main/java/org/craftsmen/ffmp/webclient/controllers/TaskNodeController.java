package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.*;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.HistoryTaskNodeVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private TaskEquipemtService taskEquipemtService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AutomaticDeploymentServiceImpl automaticDeploymentService;
    private Logger logger = LogManager.getLogger(TaskNodeController.class.getName());

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
        String userName = UserDetailsUtils.getCurrent().getUsername();
        Account account = accountService.findOneByName(userName);
        MaintenanceTask maintenanceTask = taskRuntimeService.findOne(historyTaskNodeVO.getMaintenanceTaskId());
        //获取当前操作步骤
        FlowchartSteps flowchartSteps = getShtep(historyTaskNodeVO.getMaintenanceTaskId());
        if(flowchartSteps.getName().equals("巡检")){
            List<TaskEquipemt> taskEquipemts=taskEquipemtService.findByMaintenanceTask(maintenanceTask);
           int totals= maintenanceTask.getMaintenanceProject().getEquipments().size();
            if(totals>0){
                if(taskEquipemts.size()/totals<0.09){
                    throw new ServiceException("巡检设备必须超过设备总数的9%，目前只有"+taskEquipemts.size()/totals*100+"%");
                }
            }else {
                throw new ServiceException("该项目没有设备，请先录入设备！");
            }
        }
        HistoryTaskNode historyTaskNode = bulidHistoryTaskNode(historyTaskNodeVO, maintenanceTask, account, flowchartSteps);
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建任务执行节点，名称--" + flowchartSteps.getName() + "。任务名称：" +
                maintenanceTask.getName());
        if (flowchartSteps.getCatch(historyTaskNode.getDescription()).equals("en") || flowchartSteps.getCatch(historyTaskNode.getDescription()).equals("en1")) {
            //执行结束任务步骤
            service.save(historyTaskNode);
            maintenanceTask.setSuspended(true);
            taskRuntimeService.save(maintenanceTask);
            FlowchartSteps flowchartSteps1 = findOneByTaskDefinitionAndParametric(maintenanceTask.getTaskDefinition(), flowchartSteps.getCatch(historyTaskNode.getDescription()));
            logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建任务执行节点，名称--" + flowchartSteps1.getName() + "。任务名称：" +
                    maintenanceTask.getName());
            if(maintenanceTask.getTaskDefinition().getName().equals("巡检任务")){
                buildTask(maintenanceTask);
            }
            return service.save(bulidHistoryTaskNode(historyTaskNodeVO, maintenanceTask, account, flowchartSteps1));
        } else {
            return service.save(historyTaskNode);
        }
    }

    public HistoryTaskNode bulidHistoryTaskNode(HistoryTaskNodeVO historyTaskNodeVO, MaintenanceTask maintenanceTask, Account account, FlowchartSteps flowchartSteps) {
        HistoryTaskNode historyTaskNode = new HistoryTaskNode();
        historyTaskNode.setMaintenanceTask(maintenanceTask);
        historyTaskNode.setName(getShtep(historyTaskNodeVO.getMaintenanceTaskId()).getName());
        historyTaskNode.setDueDate(new Date());
        historyTaskNode.setDelegate(account);
        historyTaskNode.setDescription(historyTaskNodeVO.getStepResult());
        historyTaskNode.setFlowchartSteps(flowchartSteps);
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

    private void buildTask(MaintenanceTask maintenanceTask) {
        final String[] description = {"维修以下设备："};
        taskEquipemtService.findByMaintenanceTaskAndDescriptionNot(maintenanceTask, "功能正常").forEach(taskEquipemt -> {
            description[0] = description[0] + taskEquipemt.getEquipment().getCode()+";";
        });
        taskRuntimeService.save(automaticDeploymentService.buildMaintenanceTask(maintenanceTask.getMaintenanceProject(), "维修任务", maintenanceTask.getName() + "-维修", description[0]));
    }
}
