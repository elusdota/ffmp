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

    @RequestMapping(method = RequestMethod.GET)
    public HistoryTaskNode get(@RequestParam("id") String id) {
        return service.findOne(id);
    }
}
