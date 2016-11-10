package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.ffmp.data.entities.MrrStandard;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by jiangliang on 2016/7/27.设备操作控制器,elus
 */
@RestController
@RequestMapping(value = "/rest/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService service;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;
    @Autowired
    private MrrStandardService mrrStandardService;
    private Logger logger = LogManager.getLogger(EquipmentController.class.getName());

    @RequestMapping(value = "/findProject", method = RequestMethod.POST)
    public JSONListData findProject(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(parameters.getSearch());
        Page<Equipment> equipments = service.findByOwner(maintenanceProject, pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(equipments.getTotalElements());
        jld.setRows(equipments.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载项目设备列表");
        return jld;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public JSONListData findAll(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        Page<Equipment> equipments = service.findAll(new CommonSpecs<Equipment>().spec(parameters), pageable);
        JSONListData jld = new JSONListData();
        jld.setTotal(equipments.getTotalElements());
        jld.setRows(equipments.getContent());
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载设备列表");
        return jld;
    }

    @RequestMapping(value = "/findExpired", method = RequestMethod.POST)
    public JSONListData findExpired(@RequestBody TableGetDataParameters parameters) {
        PageableImpl pageable = new PageableImpl(parameters);
        MaintenanceProject maintenanceProject=maintenanceProjectService.findOne(parameters.getSearch());
        Page<Equipment> equipments = service.findAll(new EquipmentSpecs<Equipment>().spec(mrrStandardService.findExpired(),maintenanceProject), pageable);
        List<EquipmentTeachVo> equipmentTeachVos=new ArrayList<>();
        equipments.forEach(equipment -> {
            MrrStandard mrrStandard = mrrStandardService.findOneByName(equipment.getTypemin());
            final Date[] date1 = {new Date()};
            final Date[] date2 = {new Date()};
            mrrStandard.getTechniqueRequirementsList().forEach(techniqueRequirements -> {
                if (techniqueRequirements.getChangetime() > 12 || techniqueRequirements.getLifetime() > 0) {
                    if (techniqueRequirements.getMaturity() == "生产日期") {
                        date1[0] = getDate(equipment.getProductionDate(), techniqueRequirements.getChangetime());
                        date2[0] = getDate(equipment.getProductionDate(), techniqueRequirements.getLifetime());
                    } else {
                        date1[0] = getDate(equipment.getInputDate(), techniqueRequirements.getChangetime());
                        date2[0] = getDate(equipment.getInputDate(), techniqueRequirements.getLifetime());
                    }
                }
            });
            EquipmentTeachVo equipmentTeachVo = new EquipmentTeachVo();
            equipmentTeachVo.setEquipment(equipment);
            equipmentTeachVo.setExpired(date1[0]);
            equipmentTeachVo.setScrap(date2[0]);
            equipmentTeachVos.add(equipmentTeachVo);
        });
        JSONListData jld = new JSONListData();
        jld.setTotal(equipments.getTotalElements());
        jld.setRows(equipmentTeachVos);
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":加载需要定期维修的设备列表");
        return jld;
    }

    private Date getDate(Date date1, int year) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date1);
        calender.add(Calendar.YEAR, year);
//        calender.add(Calendar.MONTH, month);
        return calender.getTime();
    }

    @RequestMapping(value = "/findAllList", method = RequestMethod.GET)
    public List<Equipment> findAllList(@RequestParam("projectId") String projectId) {
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(projectId);
        return service.findByOwner(maintenanceProject);
    }

    /**
     * 扫描条码 通过条码返回设备信息
     *
     * @param code
     * @return 设备信息
     */
    @RequestMapping(method = RequestMethod.GET)
    public Equipment get(@RequestParam("code") String code) {
        logger.info(UserDetailsUtils.getCurrent().getUsername() + ":扫码获取设备信息，编码--" + code);
        return service.findOneByCode(code);
    }

    //创建设备
    @RequestMapping(method = RequestMethod.POST)
    public Equipment create(@RequestBody Equipment equipment) {
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(equipment.getOwner().getId());
        final int[] i = {0};
        i[0] = service.findByOwner(equipment.getOwner()).size();
        i[0] = i[0] + 1;
        int t = i[0] - 1;
        String code = getLastSixNum("" + t, 3);
        String equipmentcode = getCodeNum(equipment.getOwner().getCode(), 4) +
                mrrStandardService.findOneByName(equipment.getTypemax()).getCode() +
                mrrStandardService.findOneByName(equipment.getTypemin()).getCode() + code;
        equipment.setCode(equipmentcode);
        equipment.setOwner(maintenanceProject);
        return service.save(equipment);
    }

    public String getLastSixNum(String s, int v) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < v; j++) {
            rs = "0" + rs;
            // 直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs, j + 1, "0");
        }
        return rs;
    }

    public String getCodeNum(String s, int v) {
        String rs = s;
        int i = Integer.parseInt(rs);
        rs = "" + i;
        for (int j = rs.length(); j < v; j++) {
            rs = "0" + rs;
            // 直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs, j, "0");
        }
        return rs;
    }
}
