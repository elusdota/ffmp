package org.craftsmen.ffmp.webclient.controllers;

import java.util.Date;

import com.jrtech.ffmp.data.entities.Purchases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jrtech.templates.services.CodeService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.PurchasesService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.InventorySearch;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.WarehouseSpecs;

/**
 * Created by jiangliang on 2016/6/28.采购controller,elus
 */
@RestController
@RequestMapping(value = "/rest/purchases")
public class PurchasesController {
    @Autowired
    private PurchasesService service;
    @Autowired
    private CodeService codeService;
    @Autowired
    private UserDetailsUtils userDetailsUtils;

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Purchases> purchases = service.findAll(new WarehouseSpecs<Purchases>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(purchases.getTotalElements());
        jld.setRows(purchases.getContent());
        return jld;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Purchases create(@RequestBody Purchases purchases) {
        purchases.getPurchasesdetails().forEach(purchasesDetail -> {
            purchasesDetail.setPurchases(purchases);
        });
        purchases.setNumber(codeService.getPurchNum());
        purchases.setAudit(userDetailsUtils.getCurrent().getUsername());
        purchases.setExecutor(userDetailsUtils.getCurrent().getUsername());
        purchases.setDate(new Date());
        purchases.setStateTime(new Date());
        return service.save(purchases);
    }
}
