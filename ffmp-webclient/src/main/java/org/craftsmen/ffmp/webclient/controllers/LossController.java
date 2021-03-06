package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Loss;
import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.LossService;
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
 * Created by jiangliang on 2016/8/15.报损单控制器，elus
 */
@RestController
@RequestMapping(value = "/rest/loss")
public class LossController {
    @Autowired
    private LossService service;
    @Autowired
    private CodeService codeService;
    private Logger logger = LogManager.getLogger(LossController.class.getName());
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Loss> losses = service.findAll(new WarehouseSpecs<Loss>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(losses.getTotalElements());
        jld.setRows(losses.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载报损单列表");
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Loss create(@RequestBody Loss loss) {
        loss.getLossdetails().forEach(dispatchDetail -> {
            dispatchDetail.setLoss(loss);
        });
        loss.setNumber(codeService.getLossNum());
        loss.setAudit(UserDetailsUtils.getCurrent().getUsername());
        loss.setExecutor(UserDetailsUtils.getCurrent().getUsername());
        loss.setDate(new Date());
        loss.setStateTime(new Date());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建报损单，单号---"+loss.getNumber());
        return service.save(loss);
    }
}
