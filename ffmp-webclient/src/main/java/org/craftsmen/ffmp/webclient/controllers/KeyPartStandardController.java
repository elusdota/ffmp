package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.KeyPartStandard;
import com.jrtech.templates.services.KeyPartStandardService;
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
 * 重点部位标准
 * Created by suelmer on 2016/7/18.
 */
@RestController
@RequestMapping("/rest/keyPartStandard")
public class KeyPartStandardController {

    @Autowired
    private KeyPartStandardService keyPartStandardService;
    private Logger logger = LogManager.getLogger(KeyPartStandardController.class.getName());

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public KeyPartStandard save(@RequestBody KeyPartStandard keyPartStandard){
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建重点部位信息，重点部位信息名称--"+keyPartStandard.getName());
        return keyPartStandardService.save(keyPartStandard);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<KeyPartStandard> mrrStandards = keyPartStandardService.findAll(new CommonSpecs<KeyPartStandard>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载重点部位信息列表");
        return jld;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") String id){
        keyPartStandardService.delete(id);
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":删除重点部位信息，重点部位信息名称id--" + id);
        return "delete success!";
    }
}
