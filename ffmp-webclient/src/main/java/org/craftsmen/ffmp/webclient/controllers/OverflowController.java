package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Overflow;
import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.OverflowService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.InventorySearch;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.WarehouseSpecs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by jiangliang on 2016/8/15.报溢单控制器，elus
 */
@RestController
@RequestMapping(value = "/rest/overflow")
public class OverflowController {
    @Autowired
    private OverflowService service;
    @Autowired
    private CodeService codeService;
    private Logger logger = LogManager.getLogger(OverflowController.class.getName());
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Overflow> overflows = service.findAll(new WarehouseSpecs<Overflow>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(overflows.getTotalElements());
        jld.setRows(overflows.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载报溢单列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Overflow create(@RequestBody Overflow overflow) {
        overflow.getOverflowdetails().forEach(inboundsDetail -> {
            inboundsDetail.setOverflow(overflow);
        });
        overflow.setNumber(codeService.getOverflowNum());
        overflow.setAudit(UserDetailsUtils.getCurrent().getUsername());
        overflow.setExecutor(UserDetailsUtils.getCurrent().getUsername());
        overflow.setDate(new Date());
        overflow.setStateTime(new Date());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建报溢单，编号--"+overflow.getNumber());
        return service.save(overflow);
    }
}
