package org.craftsmen.ffmp.webclient.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiangliang on 2016/6/22.
 */
@Controller
public class PageController {
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "用户名或密码错误!");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String index(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/index";
    }

    @RequestMapping(value = {"/system/role"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String role(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/system/role";
    }

    @RequestMapping("/system/account")
    @ResponseStatus(HttpStatus.OK)
    public String account(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/system/account";
    }

    @RequestMapping(value = {"/basicInformation/organization"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String organization(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/basicInformation/organization";
    }

    @RequestMapping("/warehouse/purchases")
    @ResponseStatus(HttpStatus.OK)
    public String purchases(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/purchases";
    }

    @RequestMapping("/warehouse/inbounds")
    @ResponseStatus(HttpStatus.OK)
    public String inbounds(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/inbounds";
    }

    @RequestMapping("/warehouse/inventory")
    @ResponseStatus(HttpStatus.OK)
    public String inventory(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/inventory";
    }

    @RequestMapping("/warehouse/dispatch")
    @ResponseStatus(HttpStatus.OK)
    public String dispatch(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/warehouse/dispatch";
    }

    @RequestMapping("/taskManagement/maintenanceProject")
    @ResponseStatus(HttpStatus.OK)
    public String maintenanceProject(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/maintenanceProject";
    }

    @RequestMapping("/taskManagement/createProject")
    @ResponseStatus(HttpStatus.OK)
    public String createProject(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/createProject";
    }

    @RequestMapping("/taskManagement/equipment")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView equipment(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/equipment");
        return model;
    }

    @RequestMapping("/taskManagement/projectInformation")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView projectInformation(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/projectInformation");
        return model;
    }

    @RequestMapping("/taskManagement/runTask")
    @ResponseStatus(HttpStatus.OK)
    public String runTask(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/runTask";
    }
    @RequestMapping("/taskManagement/historyTask")
    @ResponseStatus(HttpStatus.OK)
    public String historyTask(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/historyTask";
    }
    @RequestMapping("/taskManagement/createTask")
    @ResponseStatus(HttpStatus.OK)
    public String createTask(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/createTask";
    }

    @RequestMapping("/taskManagement/taskInformation")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView taskInformation(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/taskInformation");
        return model;
    }

    @RequestMapping("/taskManagement/taskNode")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView taskNode(@RequestParam("id") String id) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("id", id);
        model.setViewName("taskManagement/taskNode");
        return model;
    }

    @RequestMapping("/customer/customer")
    @ResponseStatus(HttpStatus.OK)
    public String customer(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/customer/customer";
    }
    @RequestMapping("/taskManagement/repairForm")
    @ResponseStatus(HttpStatus.OK)
    public String repairForm(HttpServletRequest rq, HttpServletResponse response) throws Exception {
        return "/taskManagement/repairForm";
    }

    /**
     * 基础信息管理->城市区域管理
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

}