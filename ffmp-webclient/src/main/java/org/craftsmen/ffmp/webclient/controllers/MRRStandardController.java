package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.templates.services.MrrStandardService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.MrrStandardVO;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 维管设施标准
 * Created by suelmer on 2016/7/16.
 */
@RestController
@RequestMapping(value = "/rest/mrrstandard")
public class MRRStandardController {
    @Autowired
    private MrrStandardService mrrStandardService;
    private Logger logger = LogManager.getLogger(MRRStandardController.class.getName());

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<MrrStandard> mrrStandards = mrrStandardService.findAll(new CommonSpecs<MrrStandard>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载维管设施标准列表");
        return jld;
    }

    @RequestMapping(value = "/findRoot", method = RequestMethod.GET)
    public Iterable<MrrStandard> findRoot() {
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":查找维管设施标准的root列表");
        return mrrStandardService.findRoot();
    }

    @RequestMapping(value = "/findOneByName", method = RequestMethod.GET)
    public Iterable<MrrStandard> findOneByName(@RequestParam("name") String name) {
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":通过name查找维管设施标准，名称--"+name);
        return mrrStandardService.findOneByName(name).getChildren();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MrrStandard create(@RequestBody MrrStandardVO mrrStandardVo) {
        if (mrrStandardVo == null) {
            logger.error(UserDetailsUtils.getCurrent().getUsername() + ":创建维管设施标准错误，维管设施标准数据为NULL");
            throw new ServiceException("创建维管设施标准错误，维管设施标准数据为NULL");
        }
        MrrStandard mrrStandard = mrrStandardVo.getMrrStandard();
        mrrStandard.setParent(mrrStandardVo.getParent());

        mrrStandard.getTechniqueRequirementsList().forEach(item -> item.setMrrStandard(mrrStandard));

        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建维管设施标准，维管设施标准名称--" + mrrStandard.getName());
        return mrrStandardService.save(mrrStandard);
    }

    @RequestMapping(value = "/findOneByCode", method = RequestMethod.GET)
    public MrrStandard findOneByCode(@RequestParam("code") String code) {
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":通过code查找维管设施标准，code--"+code);
        return mrrStandardService.findOneByCode(code);
    }
}
