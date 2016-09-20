package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Dispatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.DispatchService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.InventorySearch;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.WarehouseSpecs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/29.出库单controller,elus
 */
@RestController
@RequestMapping(value = "/rest/dispatch")
public class DispatchController {
    @Autowired
    private DispatchService service;
    @Autowired
    private CodeService codeService;
   private Logger logger = LogManager.getLogger(DispatchController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Dispatch> dispatches = service.findAll(new WarehouseSpecs<Dispatch>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(dispatches.getTotalElements());
        jld.setRows(dispatches.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载出库单列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Dispatch create(@RequestBody Dispatch dispatch) {
        dispatch.getDispatchdetails().forEach(dispatchDetail -> {
            dispatchDetail.setDispatch(dispatch);
        });
        dispatch.setNumber(codeService.getDispatchNum());
        dispatch.setAudit(UserDetailsUtils.getCurrent().getUsername());
        dispatch.setExecutor(UserDetailsUtils.getCurrent().getUsername());
        dispatch.setDate(new Date());
        dispatch.setStateTime(new Date());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建出库单，出库单号--"+dispatch.getNumber());
        return service.save(dispatch);
    }
}
