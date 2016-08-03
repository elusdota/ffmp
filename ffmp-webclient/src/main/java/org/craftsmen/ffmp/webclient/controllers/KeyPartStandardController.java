package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.KeyPartStandard;
import com.jrtech.templates.services.KeyPartStandardService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 重点部位标准
 * Created by suelmer on 2016/7/18.
 */
@RestController
@RequestMapping("/rest/keyPartStandard")
public class KeyPartStandardController {

    @Autowired
    private KeyPartStandardService keyPartStandardService;

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public KeyPartStandard save(@RequestBody KeyPartStandard keyPartStandard){
        return keyPartStandardService.save(keyPartStandard);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<KeyPartStandard> mrrStandards = keyPartStandardService.findAll(new CommonSpecs<KeyPartStandard>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());
        return jld;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") String id){
        keyPartStandardService.delete(id);
        return "delete success!";
    }
}
