package org.craftsmen.ffmp.webclient.controllers;

import java.util.Date;

import com.jrtech.ffmp.data.entities.Inbounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.InboundsService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.InventorySearch;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.WarehouseSpecs;

/**
 * Created by jiangliang on 2016/6/28.入库controller,elus
 */
@RestController
@RequestMapping(value = "/rest/inbounds")
public class InboundsController {
    @Autowired
    private InboundsService service;
    @Autowired
    private CodeService codeService;
    private Logger logger = LogManager.getLogger(InboundsController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Inbounds> inboundses = service.findAll(new WarehouseSpecs<Inbounds>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(inboundses.getTotalElements());
        jld.setRows(inboundses.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载入库单列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Inbounds create(@RequestBody Inbounds inbounds) {
        inbounds.getInboundsdetails().forEach(inboundsDetail -> {
            inboundsDetail.setInbounds(inbounds);
        });
        inbounds.setNumber(codeService.getInboundsNum());
        inbounds.setAudit(UserDetailsUtils.getCurrent().getUsername());
        inbounds.setExecutor(UserDetailsUtils.getCurrent().getUsername());
        inbounds.setDate(new Date());
        inbounds.setStateTime(new Date());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建入库单，入库单编号--"+inbounds.getNumber());
        return service.save(inbounds);
    }
}
