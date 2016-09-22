package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Repaid;
import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.RepaidService;
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
@RequestMapping(value = "/rest/repaid")
public class RepaidController {
    @Autowired
    private RepaidService service;
    @Autowired
    private CodeService codeService;
    private Logger logger = LogManager.getLogger(RepaidController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Repaid> repaids = service.findAll(new WarehouseSpecs<Repaid>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(repaids.getTotalElements());
        jld.setRows(repaids.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载出库单列表");
        return jld;
    }
    @RequestMapping(method = RequestMethod.POST)
    public Repaid create(@RequestBody Repaid repaid) {
        repaid.getRepaidDetails().forEach(repaidDetail -> {
            repaidDetail.setRepaid(repaid);
        });
        repaid.setNumber(codeService.getRepaidNum());
        repaid.setAudit(UserDetailsUtils.getCurrent().getUsername());
        repaid.setExecutor(UserDetailsUtils.getCurrent().getUsername());
        repaid.setDate(new Date());
        repaid.setStateTime(new Date());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建工具归还，单号--"+repaid.getNumber());
        return service.save(repaid);
    }
}
