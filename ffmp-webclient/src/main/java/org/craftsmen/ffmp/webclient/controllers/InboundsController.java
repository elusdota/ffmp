package org.craftsmen.ffmp.webclient.controllers;

import java.util.Date;

import com.jrtech.ffmp.data.entities.Inbounds;
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
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    private final String LOAD_ERROR = "加载数据错误";
    private final String CREATE_ERROR = "创建采购单错误";
    private final String UPDATE_ERROR = "修改采购单错误";
    private final String DELETE_ERROR = "删除采购单错误";

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        try {
            PageableImpl pageable = new PageableImpl(parameters);
            Page<Inbounds> inboundses = service.findAll(new WarehouseSpecs<Inbounds>().spec(parameters), pageable);
            JSONListData jld = new JSONListData();
            jld.setTotal(inboundses.getTotalElements());
            jld.setRows(inboundses.getContent());
            return jld;
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getMessage() + LOAD_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Inbounds create(@RequestBody Inbounds inbounds) {
        try {
            inbounds.getInboundsdetails().forEach(inboundsDetail -> {
                inboundsDetail.setInbounds(inbounds);
            });
            inbounds.setNumber(codeService.getInboundsNum());
            inbounds.setAudit(userDetailsUtils.getCurrent().getUsername());
            inbounds.setExecutor(userDetailsUtils.getCurrent().getUsername());
            inbounds.setDate(new Date());
            inbounds.setStateTime(new Date());
            return service.save(inbounds);
        } catch (DataAccessException ex) {
            throw new ServiceException(CREATE_ERROR, ex);
        }
    }
}
