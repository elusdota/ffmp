package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Manufacturer;
import com.jrtech.templates.services.ManufacturerService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 生产厂商
 * Created by suelmer on 2016/7/19.
 */
@RestController
@RequestMapping("/rest/manufacturer")
public class ManufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;
    private Logger logger = LogManager.getLogger(ManufacturerController.class.getName());

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Manufacturer save(@RequestBody Manufacturer manufacturer){
        return  manufacturerService.save(manufacturer);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Manufacturer> mrrStandards = manufacturerService.findAll(new CommonSpecs<Manufacturer>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载生产厂家列表");
        return jld;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") String id){
          manufacturerService.delete(id);
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":删除生产厂家信息，生产厂家名称id--" + id);
        return "delete success!";
    }
}
