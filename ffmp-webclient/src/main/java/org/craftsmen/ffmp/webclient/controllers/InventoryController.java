package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Inventory;
import com.jrtech.templates.services.UserDetailsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.jrtech.templates.services.InventoryService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.vo.InventorySearch;
import com.jrtech.templates.vo.InventorySpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/28.库存controller,elus
 */
@RestController
@RequestMapping(value = "/rest/inentory")
public class InventoryController {
    @Autowired
    private InventoryService service;
    private Logger logger = LogManager.getLogger(InventoryController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody InventorySearch parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Inventory> inventoryPage = service.findAll(new InventorySpecs().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(inventoryPage.getTotalElements());
        jld.setRows(inventoryPage.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载库存列表");
        return jld;
    }

    @RequestMapping(value = "/searchByName", method = RequestMethod.POST)
    public JSONListData searchByName(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        if (parameters.getSearch() == null) {
            parameters.setSearch("不为空");
        }
        if (parameters.getDescription() != null) {
            Page<Inventory> inventoryPage = service.findByInventoryTypeAndNameLike(parameters.getDescription(),"%"+parameters.getSearch()+"%", pageable);
            JSONListData jld = new JSONListData();
            jld.setTotal(inventoryPage.getTotalElements());
            jld.setRows(inventoryPage.getContent());
            logger.info(UserDetailsUtils.getCurrent().getUsername() + ":查询库存，关键字--" + parameters.getSearch());
            return jld;
        }else {
            Page<Inventory> inventoryPage = service.findByInventoryTypeNotAndNameLike("工具","%"+parameters.getSearch()+"%", pageable);
            JSONListData jld = new JSONListData();
            jld.setTotal(inventoryPage.getTotalElements());
            jld.setRows(inventoryPage.getContent());
            logger.info(UserDetailsUtils.getCurrent().getUsername() + ":查询库存，关键字--" + parameters.getSearch());
            return jld;
        }
    }
}
