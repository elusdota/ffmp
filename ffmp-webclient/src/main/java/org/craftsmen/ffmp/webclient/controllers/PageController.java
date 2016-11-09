package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.templates.services.ServiceException;
import com.jrtech.templates.services.UserDetailsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jiangliang on 2016/6/22.页面跳转控制器，elus
 */
@Controller
public class PageController {
    private Logger logger = LogManager.getLogger(PageController.class.getName());

    //登陆
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,HttpServletRequest rq, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
//            model.addObject("serviceException", "用户名或密码错误!");
            if(null!=UserDetailsUtils.getCurrent()){
            logger.error( UserDetailsUtils.getCurrent().getUsername()+":登录失败");
            }
            HttpSession session = rq.getSession(false);
            AuthenticationException exception= (AuthenticationException) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            throw new ServiceException(exception.getMessage(),"login");
        }
        model.setViewName("login");
        return model;
    }

    //主页
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView();
        String username = UserDetailsUtils.getCurrent().getUsername();
        if (username != null) {
            model.addObject("username", username);
        }
        model.setViewName("index");
        logger.info(username + ":登录成功");
        return model;
    }

    //角色
    @RequestMapping(value = {"/system/role"}, method = RequestMethod.GET)
    public String role(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/system/role";
    }

    //账户
    @RequestMapping("/system/account")
    public String account(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/system/account";
    }

    //组织机构
    @RequestMapping(value = {"/basicInformation/organization"}, method = RequestMethod.GET)
    public String organization(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/basicInformation/organization";
    }

    //采购
    @RequestMapping("/warehouse/purchases")
    public String purchases(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/purchases";
    }

    //入库
    @RequestMapping("/warehouse/inbounds")
    public String inbounds(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/inbounds";
    }

    //备件库存
    @RequestMapping("/warehouse/inventory")
    public String inventory(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/inventory";
    }
    //设备库存
    @RequestMapping("/warehouse/equipmentInventory")
    public String equipmentInventory(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/equipmentInventory";
    }
    //工具库存
    @RequestMapping("/tools/toolsInventory")
    public String toolsInventory(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/tools/toolsInventory";
    }
    //出库
    @RequestMapping("/warehouse/dispatch")
    public String dispatch(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/dispatch";
    }
    //工具借出
    @RequestMapping("/tools/dispatch")
    public String toolsdispatch(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/tools/dispatch";
    }
    //工具归还
    @RequestMapping("/tools/inbounds")
    public String toolsinbounds(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/tools/inbounds";
    }
    //报损
    @RequestMapping("/warehouse/loss")
    public String loss(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/loss";
    }

    //报溢
    @RequestMapping("/warehouse/overflow")
    public String overflow(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/overflow";
    }

    //项目
    @RequestMapping("/taskManagement/maintenanceProject")
    public String maintenanceProject(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/maintenanceProject";
    }

    //创建项目
    @RequestMapping("/taskManagement/createProject")
    public String createProject(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/createProject";
    }
    //设备
    @RequestMapping("/taskManagement/updateProject")
    public ModelAndView updateProject(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/updateProject");
        return model;
    }

    //设备
    @RequestMapping("/taskManagement/equipment")
    public ModelAndView equipment(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/equipment");
        return model;
    }

    //项目信息
    @RequestMapping("/taskManagement/projectInformation")
    public ModelAndView projectInformation(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/projectInformation");
        return model;
    }

    //活动任务
    @RequestMapping("/taskManagement/runTask")
    public String runTask(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/runTask";
    }

    //历史节点
    @RequestMapping("/taskManagement/historyTask")
    public String historyTask(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/historyTask";
    }

    //创建任务
    @RequestMapping("/taskManagement/createTask")
    public String createTask(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/createTask";
    }

    //任务信息
    @RequestMapping("/taskManagement/taskInformation")
    public ModelAndView taskInformation(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/taskInformation");
        return model;
    }

    //任务节点
    @RequestMapping("/taskManagement/taskNode")
    public ModelAndView taskNode(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/taskNode");
        return model;
    }

    //客户
    @RequestMapping("/customer/customer")
    public String customer(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/customer/customer";
    }

    //报修单
    @RequestMapping("/taskManagement/repairForm")
    public String repairForm(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/repairForm";
    }

    /**
     * 基础信息管理->城市区域管理
     *
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    @RequestMapping("/basicInfo/areaMgmt")
    @ResponseStatus(HttpStatus.OK)
    public String areaMgmt(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/basicInformation/areaMgmt";
    }

    /**
     * 基础信息管理->重点部位标准
     *
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    @RequestMapping("/basicInfo/keyPartStandard")
    @ResponseStatus(HttpStatus.OK)
    public String keyPartStandard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/basicInformation/keyPartStandard";
    }

    /**
     * 基础信息管理->设施维管标准
     *
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    @RequestMapping("/basicInfo/MRRStandard")
    @ResponseStatus(HttpStatus.OK)
    public String MRRStandard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/basicInformation/MRRStandard";
    }


    /**
     * 基础信息管理->生产厂家管理
     *
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    @RequestMapping("/basicInfo/manufacturer")
    @ResponseStatus(HttpStatus.OK)
    public String manufacturer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/basicInformation/manufacturer";
    }

    /**
     * 基础信息管理->合同管理
     *
     * @param request
     * @param response
     * @return String
     * @throws Exception
     */
    @RequestMapping("/contract/contract")
    @ResponseStatus(HttpStatus.OK)
    public String contract(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/contract/contract";
    }

    /**
     * 基础信息管理->合同管理->创建合同信息
     *
     * @param request
     * @param response
     * @return string
     * @throws Exception
     */
    @RequestMapping("/contract/contractForm")
    @ResponseStatus(HttpStatus.OK)
    public String contractForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/contract/contractForm";
    }

    /**
     * 基础信息管理->合同管理->更新合同信息
     *
     * @param request
     * @param response
     * @return string
     * @throws Exception
     */
    @RequestMapping("/contract/updateContract")
    @ResponseStatus(HttpStatus.OK)
    public String updateContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/contract/updateContract";
    }

    /**
     * 基础信息管理->合同管理->上传文件
     *
     * @param request
     * @param response
     * @return string
     * @throws Exception
     */
    @RequestMapping("/contract/contractFileUpload")
    @ResponseStatus(HttpStatus.OK)
    public String contractFileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/contract/contractFileUpload";
    }

    //设备查询
    @RequestMapping("/taskManagement/serachEquipment")
    @ResponseStatus(HttpStatus.OK)
    public String serachEquipment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/taskManagement/serachEquipment";
    }

    //职工管理
    @RequestMapping("/basicInformation/employee")
    @ResponseStatus(HttpStatus.OK)
    public String employee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/basicInformation/employee";
    }

    //创建职工
    @RequestMapping("/basicInformation/createEmployee")
    @ResponseStatus(HttpStatus.OK)
    public String createEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/basicInformation/createEmployee";
    }

    //修改职工
    @RequestMapping("/basicInformation/updateEmployee")
    public ModelAndView updateEmployee(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("basicInformation/updateEmployee");
        return model;
    }

    @RequestMapping("/system/information")
    @ResponseStatus(HttpStatus.OK)
    public String information(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/system/information";
    }
    //任务审核
    @RequestMapping("/taskManagement/auditTask")
    public ModelAndView auditTask(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/auditTask");
        return model;
    }
    //申请材料
    @RequestMapping("/taskManagement/material")
    public ModelAndView material(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/material");
        return model;
    }
    //文件管理
    @RequestMapping("/common/fileUpload")
    public ModelAndView fileUpload(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("common/fileUpload");
        return model;
    }
    //巡检标准
    @RequestMapping("/taskManagement/inspection")
    public ModelAndView inspection(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/inspection");
        return model;
    }
    //巡检标准
    @RequestMapping("/taskManagement/equipmentExpired")
    public ModelAndView equipmentExpired(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/equipmentExpired");
        return model;
    }
}
