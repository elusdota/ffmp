package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Contract;
import com.jrtech.templates.services.ContractService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.ContractVO;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 合同信息管理控制器
 * Created by suelmer on 2016/7/18.
 */
@RestController
@RequestMapping(value = "/rest/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;
    private Logger logger = LogManager.getLogger(ContractController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Contract> mrrStandards = contractService.findAll(new CommonSpecs<Contract>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载合同列表");
        return jld;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Contract save(@RequestBody ContractVO contractVO){
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建合同，合同名称--"+contractVO.getName());
        return contractService.save(contractVO);
    }
}
