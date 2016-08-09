package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.templates.services.MrrStandardService;
import com.jrtech.templates.services.PageableImpl;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.MrrStandardVO;
import com.jrtech.templates.vo.TableGetDataParameters;
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

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<MrrStandard> mrrStandards = mrrStandardService.findAll(new CommonSpecs<MrrStandard>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(mrrStandards.getTotalElements());
        jld.setRows(mrrStandards.getContent());

        return jld;
    }

    @RequestMapping(value = "/findRoot", method = RequestMethod.GET)
    public Iterable<MrrStandard> findRoot() {
        return mrrStandardService.findRoot();
    }

    @RequestMapping(value = "/findOneByName", method = RequestMethod.GET)
    public Iterable<MrrStandard> findOneByName(@RequestParam("name") String name) {
        return mrrStandardService.findOneByName(name).getChildren();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MrrStandard create(@RequestBody MrrStandardVO mrrStandardVo) {
        if (mrrStandardVo == null) {

            throw new ServiceException("创建维管设施标准错误，维管设施标准数据为NULL");
        }
        MrrStandard mrrStandard = mrrStandardVo.getMrrStandard();
        mrrStandard.setParent(mrrStandardVo.getParent());

        mrrStandard.getTechniqueRequirementsList().forEach(item -> item.setMrrStandard(mrrStandard));


        return mrrStandardService.save(mrrStandard);
    }
}
