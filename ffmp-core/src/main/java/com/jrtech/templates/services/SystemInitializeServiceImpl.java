package com.jrtech.templates.services;

import com.jrtech.ffmp.data.common.AccessType;
import com.jrtech.ffmp.data.entities.*;
import com.jrtech.ffmp.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemInitializeServiceImpl implements SystemInitializeService {

    @Autowired
    private GrantedAuthorityRepository gaRepository;
    @Autowired
    private OrganizationRepository orgRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TaskDefinitionRepository taskDefinitionRepository;

    /* (non-Javadoc)
     * @see com.jrtech.SystemInitializeService#createData()
     */
    @Override
    public void createData() {
        createGrantedAuthorities();
        createOrganization();
        createRoles();
        createAccounts();
        createTaskDefinition();
    }

    /* (non-Javadoc)
     * @see com.jrtech.SystemInitializeService#clearData()
     */
    @Override
    public void clearData() {
        deleteAccounts();
        deleteRoles();
        deleteOrganization();
        deleteGrantedAuthorities();
        deleteTaskDefinition();
    }

    private void deleteGrantedAuthorities() {
        List<GrantedAuthorityImpl> roots = gaRepository.findRoot();
        roots.forEach(ga -> {
            gaRepository.delete(ga);
        });
    }

    private void deleteOrganization() {
        Organization root = orgRepository.findRoot();
        if (null != root) {
            orgRepository.delete(root);
        }
    }
    private void deleteTaskDefinition() {
        Iterable<TaskDefinition> taskDefinitions=taskDefinitionRepository.findAll();
        taskDefinitions.forEach(taskDefinition -> {
            taskDefinitionRepository.delete(taskDefinition.getId());
        });
    }

    private void deleteRoles() {
        roleRepository.deleteAll();
    }

    private void deleteAccounts() {
        accountRepository.deleteAll();
    }

    private void createGrantedAuthorities() {
//		GrantedAuthorityImpl root = new GrantedAuthorityImpl("主页");
//		gaRepository.save(root);

        GrantedAuthorityImpl root = new GrantedAuthorityImpl("系统管理");
        root.setIcons("fa-cogs");
        GrantedAuthorityImpl second = new GrantedAuthorityImpl("账户管理");
        second.setIcons("fa-users");
        second.setSrc("system/account");
        second.setParent(root);
        root.getChildren().add(second);
        GrantedAuthorityImpl third = new GrantedAuthorityImpl("创建账户");
        third.setIcons("fa-user-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("注销账户");
        third.setIcons("fa-user-times");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("更改账户密码");
        third.setIcons("fa-user-secret");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("为账户分配角色");
        third.setIcons("fa-wrench");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("角色管理");
        second.setIcons("fa-cubes");
        second.setSrc("system/role");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建角色");
        third.setIcons(" fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("修改角色名称");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("删除角色");
        third.setIcons("fa-remove");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("为角色分配权限");
        third.setIcons("fa-unlock");
        third.setParent(second);
        second.getChildren().add(third);
        root.setAccessType(AccessType.SYSTEM);
        for (GrantedAuthorityImpl permission : root.getChildren()) {
            permission.setAccessType(AccessType.SYSTEM);
            permission.getChildren().forEach(child -> {
                child.setAccessType(AccessType.SYSTEM);
            });
        }
        gaRepository.save(root);

        root = new GrantedAuthorityImpl("基础信息管理");
        root.setIcons("fa-share-alt");
        second = new GrantedAuthorityImpl("组织机构管理");
        second.setSrc("basicInformation/organization");
        second.setIcons("fa-server");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建机构");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("修改机构");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("删除机构");
        third.setIcons("fa-remove");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("职工管理");
        second.setSrc("basicInformation/employee");
        second.setIcons("fa-child");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建职工");
        third.setIcons("fa-user");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("修改职工");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("职工离职");
        third.setIcons("fa-remove");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("设施维管标准管理");
        second.setSrc("basicInfo/MRRStandard");
        second.setIcons("fa-truck");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建设施维管标准");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("修改设施维管标准");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("删除设施维管标准");
        third.setIcons("fa-remove");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("重点部位标准管理");
        second.setSrc("basicInfo/keyPartStandard");
        second.setIcons("fa-exclamation");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建重点部位标准");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("修改重点部位标准");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("删除重点部位标准");
        third.setIcons("fa-remove");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("生产厂家信息管理");
        second.setSrc("basicInfo/manufacturer");
        second.setIcons("fa-square");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建生产厂家");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("修改生产厂家");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("删除生产厂家");
        third.setIcons("fa-remove");
        third.setParent(second);
        second.getChildren().add(third);

        gaRepository.save(root);

        root = new GrantedAuthorityImpl("维管工作管理");
        root.setIcons("fa-wrench");
        second = new GrantedAuthorityImpl("项目信息管理");
        second.setIcons("fa-file");
        second.setSrc("taskManagement/maintenanceProject");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建项目");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("设备录入");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查询项目");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看项目详细信息");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("项目设备管理");
        second.setIcons("fa-steam");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("打印条形码");
        third.setIcons("fa-pencil");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("设备查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("待完成任务");
        second.setSrc("taskManagement/runTask");
        second.setIcons("fa-archive");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建任务");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看任务");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("历史任务");
        second.setSrc("taskManagement/historyTask");
        second.setIcons("fa-archive");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("历史任务查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看历史任务");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        gaRepository.save(root);

        root = new GrantedAuthorityImpl("客户信息管理");
        root.setSrc("customer/customer");
        root.setIcons("fa-cc");
        second = new GrantedAuthorityImpl("注册客户基本信息");
        second.setIcons("fa-plus");
        second.setParent(root);
        root.getChildren().add(second);
        second = new GrantedAuthorityImpl("修改客户基本信息");
        second.setIcons("fa-pencil");
        second.setParent(root);
        root.getChildren().add(second);
        second = new GrantedAuthorityImpl("创建报修单");
        second.setIcons("fa-wrench");
        second.setParent(root);
        root.getChildren().add(second);
        second = new GrantedAuthorityImpl("查询维保工作");
        second.setIcons("fa-steam-square");
        second.setParent(root);
        root.getChildren().add(second);
        gaRepository.save(root);

        root = new GrantedAuthorityImpl("合同管理");
        root.setSrc("contract/contract");
        root.setIcons("fa-file-word-o");
        second = new GrantedAuthorityImpl("创建合同信息");
        second.setIcons("fa-plus");
        second.setParent(root);
        root.getChildren().add(second);
        second = new GrantedAuthorityImpl("修改合同信息");
        second.setIcons("fa-pencil");
        second.setParent(root);
        root.getChildren().add(second);
        second = new GrantedAuthorityImpl("查询合同信息");
        third.setIcons("fa-search");
        second.setParent(root);
        root.getChildren().add(second);
        gaRepository.save(root);

        root = new GrantedAuthorityImpl("数据统计分析");
        root.setIcons("fa-pie-chart");
        second = new GrantedAuthorityImpl("月度维保统计");
        second.setIcons(" fa-bar-chart");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("年度维保统计");
        second.setIcons(" fa-bar-chart");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("合同统计");
        second.setIcons("fa-file-code-o");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("合同收款统计");
        third.setIcons("fa-cc-visa");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("合同金额统计");
        third.setIcons("fa-paypal");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("项目统计");
        second.setIcons(" fa-line-chart");
        second.setParent(root);
        third = new GrantedAuthorityImpl("查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        root.getChildren().add(second);
        second = new GrantedAuthorityImpl("故障统计");
        second.setIcons(" fa-line-chart");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("客户报告统计");
        second.setIcons("fa-line-chart");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        gaRepository.save(root);

        root = new GrantedAuthorityImpl("仓库管理");
        root.setIcons("fa-bank");
        second = new GrantedAuthorityImpl("采购单");
        second.setSrc("warehouse/purchases");
        second.setIcons("fa-cart-plus");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建采购单");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("采购单查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看采购单详情");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("入库单");
        second.setSrc("warehouse/inbounds");
        second.setIcons("fa-compress");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建入库单");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("入库单查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看入库单详情");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("出库单");
        second.setSrc("warehouse/dispatch");
        second.setIcons("fa-expand");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建出库单");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("出库单查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看出库单详情");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("库存管理");
        second.setSrc("warehouse/inventory");
        second.setIcons("fa-ship");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("库存查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("报溢单");
        second.setSrc("warehouse/overflow");
        second.setIcons("fa-cloud-upload");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建报溢单");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("报溢单查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看报溢单详情");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        second = new GrantedAuthorityImpl("报损单");
        second.setSrc("warehouse/loss");
        second.setIcons("fa-cloud-download");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("创建报损单");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("报损单查询");
        third.setIcons("fa-search");
        third.setParent(second);
        second.getChildren().add(third);
        third = new GrantedAuthorityImpl("查看报损单详情");
        third.setIcons("fa-hand-lizard-o");
        third.setParent(second);
        second.getChildren().add(third);
        gaRepository.save(root);

        root = new GrantedAuthorityImpl("平台开发");
        root.setIcons("fa-sitemap");
        second = new GrantedAuthorityImpl("权限管理");
        second.setIcons("fa-warning ");
        second.setParent(root);
        root.getChildren().add(second);
        third = new GrantedAuthorityImpl("添加权限");
        third.setIcons("fa-plus");
        third.setParent(second);
        second.getChildren().add(third);

        root = new GrantedAuthorityImpl("报修单");
        root.setSrc("taskManagement/repairForm");
        root.setIcons(" fa-bell");
        second = new GrantedAuthorityImpl("创建报修单");
        second.setIcons("fa-plus ");
        second.setParent(root);
        root.getChildren().add(second);
        second = new GrantedAuthorityImpl("查看维修任务");
        second.setIcons("fa-hourglass");
        second.setParent(root);
        root.getChildren().add(second);
        gaRepository.save(root);
    }

    private void createOrganization() {
        Organization org = new Organization("昆明丰盛科技", 0);
        org.setAccessType(AccessType.SYSTEM);
        orgRepository.save(org);
    }

    private void createRoles() {
        Organization org = orgRepository.findRoot();
        Role admin = new Role("系统管理员", org);
        admin.setAccessType(AccessType.SYSTEM);
        gaRepository.findAll().forEach(authority -> {
            admin.getAuthorities().add(authority);
        });
        roleRepository.save(admin);
    }

    private void createAccounts() {
        Role admin = roleRepository.findOneByName("系统管理员");
        String password = new BCryptPasswordEncoder().encode("admin");
        Account account = new Account("admin", password);
        account.setAccessType(AccessType.SYSTEM);
        account.getRoles().add(admin);
        accountRepository.save(account);
        String password1 = new BCryptPasswordEncoder().encode("user");
        account = new Account("user", password1);
        accountRepository.save(account);
    }
    private void createTaskDefinition() {
        TaskDefinition taskDefinition=new TaskDefinition("维修任务");
        String value="st->cond1(right)\n"+"cond1(yes)->cond2\n"+"cond1(no)->op2->cond3\n"+"cond2(yes,right)->op1(right)->op2\n"+"cond2(no)->en1\n"
                +"cond3(yes)->cond4(right)\n"+"cond3(no,right)->op2\n"+"cond4(yes)->en\n"+"cond4(no)->op2\n";
        taskDefinition.setValue(value);
        List<FlowchartSteps> flowchartStepses=new ArrayList<>();
        flowchartStepses.add(new FlowchartSteps("开始", "st", "start","cond1",""));
        flowchartStepses.add(new FlowchartSteps("是否需要更换材料", "cond1", "condition","cond2","op2"));
        flowchartStepses.add(new FlowchartSteps("客户是否批准更换材料", "cond2", "condition","op1","en1"));
        flowchartStepses.add(new FlowchartSteps("申请材料", "op1", "operation","op2",""));
        flowchartStepses.add(new FlowchartSteps("维修", "op2", "operation","",""));
        flowchartStepses.add(new FlowchartSteps("客户审核是否维修完成", "cond3", "condition","cond4","op2"));
        flowchartStepses.add(new FlowchartSteps("维保总监审核是否维修完成", "cond4", "condition","en","op2"));
        flowchartStepses.add(new FlowchartSteps("终止","en1","end","",""));
        flowchartStepses.add(new FlowchartSteps("结束","en","end","",""));
        taskDefinition.getFlowchartStepses().addAll(flowchartStepses);
        taskDefinition.getFlowchartStepses().forEach(flowchartSteps -> {
            flowchartSteps.setTaskDefinition(taskDefinition);
        });
        taskDefinitionRepository.save(taskDefinition);
        TaskDefinition taskDefinition1=new TaskDefinition("巡检任务");
        String value1="st->op(right)->cond1\n"+"cond1(yes)->cond2\n"+"cond1(no)->op\n"
                +"cond2(yes)->en\n"+"cond2(no)->op\n";
        taskDefinition1.setValue(value1);
        List<FlowchartSteps> flowchartStepses1=new ArrayList<>();
        flowchartStepses1.add(new FlowchartSteps("开始", "st", "start","op",""));
        flowchartStepses1.add(new FlowchartSteps("巡检", "op", "operation","cond1",""));
        flowchartStepses1.add(new FlowchartSteps("客户审核是否巡检完成", "cond1", "condition","cond2","op"));
        flowchartStepses1.add(new FlowchartSteps("维保总监审核是否巡检完成", "cond2", "condition","en","op"));
        flowchartStepses1.add(new FlowchartSteps("结束","en","end","",""));
        taskDefinition1.getFlowchartStepses().addAll(flowchartStepses1);
        taskDefinition1.getFlowchartStepses().forEach(flowchartSteps -> {
            flowchartSteps.setTaskDefinition(taskDefinition1);
        });
        taskDefinitionRepository.save(taskDefinition1);
    }
}
