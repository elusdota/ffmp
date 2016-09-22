package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Lending;
import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.LendingService;
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
 * Created by jiangliang on 2016/9/22.
 */
@RestController
@RequestMapping(value = "/rest/lending")
public class LendingController {
    @Autowired
    private LendingService service;
    @Autowired
    private CodeService codeService;
    private Logger logger = LogManager.getLogger(LendingController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Lending> lendings = service.findAll(new WarehouseSpecs<Lending>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(lendings.getTotalElements());
        jld.setRows(lendings.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载出库单列表");
        return jld;
    }
    @RequestMapping(method = RequestMethod.POST)
    public Lending create(@RequestBody Lending lending) {
        lending.getLendingDetails().forEach(lendingDetail -> {
            lendingDetail.setLending(lending);
        });
        lending.setNumber(codeService.getLendingNum());
        lending.setAudit(UserDetailsUtils.getCurrent().getUsername());
        lending.setExecutor(UserDetailsUtils.getCurrent().getUsername());
        lending.setDate(new Date());
        lending.setStateTime(new Date());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建工具借出，单号--"+lending.getNumber());
        return service.save(lending);
    }
}
