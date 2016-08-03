package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.templates.services.TaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangliang on 2016/7/27.任务执行节点控制器elus
 */
@RestController
@RequestMapping(value = "/rest/historyTaskNode")
public class HistoryTaskNodeController {
    @Autowired
    private TaskHistoryService service;

    @RequestMapping(method = RequestMethod.POST)
    public HistoryTaskNode create(@RequestBody HistoryTaskNode historyTaskNode) {
        return service.save(historyTaskNode);
    }
}
