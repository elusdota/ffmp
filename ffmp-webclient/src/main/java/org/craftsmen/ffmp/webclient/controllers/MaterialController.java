package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Material;
import com.jrtech.templates.services.MaterialService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by suelmer on 2016/8/18.
 */

@RestController
@RequestMapping(value = "/rest/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Material> mrrStandards = materialService.findAll(new CommonSpecs<Material>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());

        return jld;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Material save(@RequestBody Material material){
        return materialService.save(material);
    }
}
