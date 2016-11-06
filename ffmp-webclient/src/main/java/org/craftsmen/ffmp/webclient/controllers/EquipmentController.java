package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.ffmp.data.entities.Equipment;
import com.jrtech.ffmp.data.entities.MaintenanceProject;
import com.jrtech.templates.services.*;
import com.jrtech.templates.vo.CommonSpecs;
import com.jrtech.templates.vo.EquipmentVo;
import com.jrtech.templates.vo.JSONListData;
import com.jrtech.templates.vo.TableGetDataParameters;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @RequestMapping(value = "/findAllList", method = RequestMethod.GET)
    public List<Equipment> findAllList(@RequestParam("projectId") String projectId) {
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(projectId);
        return service.findByOwner(maintenanceProject);
    }

    //查出未分配条码的设备
    @RequestMapping(value = "/findAllListCodeIsNull", method = RequestMethod.GET)
    public Collection<Equipment> findByOwnerAndCodeIsNull(@RequestParam("id") String id) {
        MaintenanceProject maintenanceProject = maintenanceProjectService.findOne(id);
        return service.findByOwnerAndCodeIsNull(maintenanceProject);
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

    //分配条码
    @RequestMapping(value = "/distribution", method = RequestMethod.POST)
    public Equipment create(@RequestBody EquipmentVo equipmentVo) {
        Equipment equipment = service.findOne(equipmentVo.getEquipment().getId());
        Equipment equipment1 = new Equipment();
        final int[] i = {0};
        i[0] = service.findByOwner(equipment.getOwner()).size();
        i[0] = i[0] + 1;
        int t = i[0] - 1;
        String code = getLastSixNum("" + t, 3);
        String equipmentcode = getCodeNum(equipment.getOwner().getCode(), 4) +
                mrrStandardService.findOneByName(equipment.getTypemax()).getCode() +
                mrrStandardService.findOneByName(equipment.getTypemin()).getCode() + code;
        if (equipment.getQuantity() <= equipmentVo.getQuantity()) {
            equipment.setQuantity(equipmentVo.getQuantity());
            equipment.setCode(equipmentcode);
            equipment.setLocation(equipmentVo.getLocation());
        } else {
            equipment1.setOwner(equipment.getOwner());
            equipment1.setName(equipment.getName());
            equipment1.setCustomer(equipment.getCustomer());
            equipment1.setInputDate(equipment.getInputDate());
            equipment1.setDescription(equipment.getDescription());
            equipment1.setLocation(equipmentVo.getLocation());
            equipment1.setManufacturer(equipment.getManufacturer());
            equipment1.setModel(equipment.getModel());
            equipment1.setCode(equipmentcode);
            equipment1.setNowstate(equipment.getNowstate());
            equipment1.setTypemax(equipment.getTypemax());
            equipment1.setTypemin(equipment.getTypemin());
            equipment1.setProductionDate(equipment.getProductionDate());
            equipment1.setQuantity(equipmentVo.getQuantity());
            equipment.setQuantity(equipment.getQuantity() - equipment1.getQuantity());
            service.save(equipment1);
        }
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
