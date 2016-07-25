package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.HistoryTaskNode;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.TaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jiangliang on 2016/7/17.
 */
@RestController
@RequestMapping(value = "/rest/taskNode")
public class TaskNodeController {
    @Autowired
    private TaskHistoryService service;
    private final String LOAD_ERROR = "加载数据错误";
    private final String CREATE_ERROR = "创建任务错误";
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HistoryTaskNode get(@RequestParam("id") String id) {
        try {
            return service.findOne(id);
        } catch (DataAccessException ex) {
            throw new ServiceException(LOAD_ERROR, ex);
        }
    }
}
