package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Loss;
import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.LossService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.InventorySearch;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.WarehouseSpecs;
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
    @Autowired
    private UserDetailsUtils userDetailsUtils;
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Loss> losses = service.findAll(new WarehouseSpecs<Loss>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(losses.getTotalElements());
        jld.setRows(losses.getContent());
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Loss create(@RequestBody Loss loss) {
        loss.getLossdetails().forEach(dispatchDetail -> {
            dispatchDetail.setLoss(loss);
        });
        loss.setNumber(codeService.getLossNum());
        loss.setAudit(userDetailsUtils.getCurrent().getUsername());
        loss.setExecutor(userDetailsUtils.getCurrent().getUsername());
        loss.setDate(new Date());
        loss.setStateTime(new Date());
        return service.save(loss);
    }
}
