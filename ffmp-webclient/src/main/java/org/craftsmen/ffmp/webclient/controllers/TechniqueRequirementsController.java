package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.TechniqueRequirements;
import com.jrtech.templates.services.MrrStandardService;
import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.TechniqueRequirementsService;
import com.jrtech.templates.services.UserDetailsUtils;
import com.jrtech.templates.vo.TechniqueRequirementsVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 维管标准的技术要求
 * Created by suelmer on 2016/9/4.
 */
@RestController
@RequestMapping(value = "/rest/techniquers")
public class TechniqueRequirementsController {
    @Autowired
    private TechniqueRequirementsService techniqueRequirementsService;
    @Autowired
    private MrrStandardService mrrStandardService;
    private Logger logger = LogManager.getLogger(MRRStandardController.class.getName());

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TechniqueRequirements create(@RequestBody TechniqueRequirementsVO vo) {
        if(null == vo || "".equals(vo.getName())){
            logger.error(UserDetailsUtils.getCurrent().getUsername() + ":创建维管设施标准-技术要求错误，维管设施标准技术要求数据为NULL");
            throw new ServiceException("创建维管设施标准技术要求错误，维管设施标准技术要求数据为NULL");
        }
        TechniqueRequirements requirements = new TechniqueRequirements();
        requirements.setName(vo.getName());
        requirements.setChangetime(vo.getChangetime());
        requirements.setMaturity(vo.getMaturity());
        requirements.setProportion(vo.getProportion());
        requirements.setLifetime(vo.getLifetime());
        requirements.setNumber(vo.getNumber());
        requirements.setInspection(vo.getInspection());
        requirements.setDescription(vo.getDescription());
        requirements.setType(vo.getType());
        requirements.setMrrMethod(vo.getMrrMethod());
        requirements.setMrrStandard(mrrStandardService.findOne(vo.getMrrStandard().getId()));
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":创建维管设施标准技术要求，检查内容为--" + vo.getName());
        return techniqueRequirementsService.save(requirements);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public TechniqueRequirements update(@RequestBody TechniqueRequirementsVO vo) {
        if(null == vo || "".equals(vo.getId())){
            logger.error(UserDetailsUtils.getCurrent().getUsername() + ":更新维管设施标准-技术要求错误，维管设施标准技术要求数据为NULL");
            throw new ServiceException("更新维管设施标准技术要求错误，维管设施标准技术要求数据为NULL");
        }
        TechniqueRequirements requirements = techniqueRequirementsService.findOne(vo.getId());
        requirements.setName(vo.getName());
        requirements.setChangetime(vo.getChangetime());
        requirements.setMaturity(vo.getMaturity());
        requirements.setProportion(vo.getProportion());
        requirements.setLifetime(vo.getLifetime());
        requirements.setNumber(vo.getNumber());
        requirements.setInspection(vo.getInspection());
        requirements.setDescription(vo.getDescription());
        requirements.setType(vo.getType());
        requirements.setMrrMethod(vo.getMrrMethod());
        requirements.setMrrStandard(mrrStandardService.findOne(vo.getMrrStandard().getId()));
        System.out.println(vo.getMrrStandard().getId()+">>>>>>>>>>>>>>>>>>>"+vo.getId());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":更新维管设施标准技术要求，检查内容为--" + vo.getName());
        return techniqueRequirementsService.save(requirements);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("id") String id){
//        TechniqueRequirements requirements = techniqueRequirementsService.findOne(id);
        techniqueRequirementsService.delete(id);
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":删除维管设施标准准技术要求， id--" + id);
        return "delete success!";
    }
}
