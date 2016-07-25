package com.jrtech.templates.services;

import com.jrtech.ffmp.data.entities.*;
import com.jrtech.templates.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;

/**
 * Created by jiangliang on 2016/6/28.
 */
@Service
public class CodeService {
    @Autowired
    private PurchasesService purchasesService;
    @Autowired
    private InboundsService inboundsService;
    @Autowired
    private DispatchService dispatchService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RepairFormService repairFormService;
    @Autowired
    private MaintenanceProjectService maintenanceProjectService;
    private static String STATNUM = "000001";
    /**
     * 判断序号是否到了最后一个
     */
    public String getLastSixNum(String s) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < 6; j++) {
            rs = "0" + rs;
            // 直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs, j + 1, "0");
        }
        return rs;
    }
    public String getReLastSixNum(String s) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < 7; j++) {
            rs = "0" + rs;
            // 直接使用StringUtils类的leftPad方法处理补零
            rs = StringUtils.leftPad(rs, j + 1, "0");
        }
        return rs;
    }

    /**
     * 获取给定位数的随机字符串
     *
     * @param number
     * @return
     */
    public String getNumCode(int number) {
        Random rd = new Random();
        String codeToken = "";
        int getNum;
        do {
            getNum = Math.abs(rd.nextInt()) % 10 + 48;//产生数字0-9的随机数
            //getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
            char charNum = (char) getNum;
            String dn = Character.toString(charNum);
            codeToken += dn;
        } while (codeToken.length() < number);

        return codeToken;
    }
    /**
     * 产生采购单不重复的号码 加锁
     */
    public synchronized String getPurchNum() {
        String code = "P";
        PageableImpl pageable = new PageableImpl(buildInventorySearch());
        long last6Num = purchasesService.findAll(new WarehouseSpecs<Purchases>().spec(buildInventorySearch()), pageable).getTotalElements();
        if (last6Num == 0) {
            code = STATNUM;
        } else {
            code = getLastSixNum(String.valueOf(last6Num));
        }
        return code;
    }
    /**
     * 产生入库单不重复的号码 加锁
     */
    public synchronized String getInboundsNum() {
        String code = "";
        PageableImpl pageable = new PageableImpl(buildInventorySearch());
        long last6Num = inboundsService.findAll(new WarehouseSpecs<Inbounds>().spec(buildInventorySearch()),pageable).getTotalElements();
        if (last6Num == 0) {
            code = STATNUM;
        } else {
            code = getLastSixNum(String.valueOf(last6Num));
        }
        return code;
    }
    /**
     * 产生出库单不重复的号码 加锁
     */
    public synchronized String getDispatchNum() {
        String code = "";
        PageableImpl pageable = new PageableImpl(buildInventorySearch());
        long last6Num = dispatchService.findAll(new WarehouseSpecs<Dispatch>().spec(buildInventorySearch()), pageable).getTotalElements();;
        if (last6Num == 0) {
            code = STATNUM;
        } else {
            code = getLastSixNum(String.valueOf(last6Num));
        }
        return code;
    }
    /**
     * 产生客户不重复的号码 加锁
     */
    public synchronized String getCustomerNum() {
        String code = "";
        PageableImpl pageable = new PageableImpl(buildInventorySearch());
        long last6Num = customerService.findAll(new CommonSpecs<Customer>().spec(bulidParameters()), pageable).getTotalElements();;
        if (last6Num == 0) {
            code = STATNUM;
        } else {
            code = getLastSixNum(String.valueOf(last6Num));
        }
        return code;
    }
    /**
     * 产生报修单不重复的号码 加锁
     */
    public synchronized String getRepairFormNum() {
        String code = "";
        PageableImpl pageable = new PageableImpl(buildInventorySearch());
        long last6Num = repairFormService.findAll(new RepairFormSpecs<RepairForm>().spec(bulidParameters()), pageable).getTotalElements();;
        if (last6Num == 0) {
            code = STATNUM;
        } else {
            code = getLastSixNum(String.valueOf(last6Num));
        }
        return code;
    }
    /**
     * 产生项目不重复的号码 加锁
     */
    public synchronized String getMaintenanceProjectNum() {
        String code = "";
        PageableImpl pageable = new PageableImpl(buildInventorySearch());
        long last6Num = maintenanceProjectService.findAll(new CommonSpecs<MaintenanceProject>().spec(bulidParameters()), pageable).getTotalElements();;
        if (last6Num == 0) {
            code = STATNUM;
        } else {
            code = getLastSixNum(String.valueOf(last6Num));
        }
        return code;
    }
    public InventorySearch buildInventorySearch(){
        InventorySearch inventorySearch=new InventorySearch();
        inventorySearch.setLimit(10);
        inventorySearch.setOffset(0);
        return inventorySearch;
    }
    public TableGetDataParameters bulidParameters(){
        TableGetDataParameters parameters=new TableGetDataParameters();
        parameters.setLimit(10);
        parameters.setOffset(0);
        return parameters;
    }
}
