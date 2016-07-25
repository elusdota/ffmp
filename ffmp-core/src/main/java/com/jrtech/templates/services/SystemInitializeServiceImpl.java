package com.jrtech.templates.services;

import com.jrtech.ffmp.data.common.AccessType;
import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.entities.Role;
import com.jrtech.ffmp.data.repositories.AccountRepository;
import com.jrtech.ffmp.data.repositories.GrantedAuthorityRepository;
import com.jrtech.ffmp.data.repositories.OrganizationRepository;
import com.jrtech.ffmp.data.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	/* (non-Javadoc)
	 * @see com.jrtech.SystemInitializeService#createData()
	 */
	@Override
	public void createData() {
		createGrantedAuthorities();
		createOrganization();
		createRoles();
		createAccounts();
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
		second.setIcons("fa-truck");
		second.setParent(root);
		root.getChildren().add(second);
		second = new GrantedAuthorityImpl("重点部位标准管理");
		second.setIcons("fa-exclamation");
		second.setParent(root);
		root.getChildren().add(second);
		second = new GrantedAuthorityImpl("生产厂家信息管理");
		second.setIcons("fa-square");
		second.setParent(root);
		root.getChildren().add(second);
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
		third = new GrantedAuthorityImpl("修改设备信息");
		third.setIcons("fa-pencil");
		third.setParent(second);
		second.getChildren().add(third);
		third = new GrantedAuthorityImpl("设备查询");
		third.setIcons("fa-search");
		third.setParent(second);
		second.getChildren().add(third);
		third = new GrantedAuthorityImpl("移除设备");
		third.setIcons("fa-remove");
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
		root.setIcons(" fa-bell");
		second = new GrantedAuthorityImpl("创建报修单");
		second.setIcons("fa-plus ");
		second.setParent(root);
		root.getChildren().add(second);
		third = new GrantedAuthorityImpl("查看维修任务");
		third.setIcons("fa-hourglass");
		third.setParent(second);
		second.getChildren().add(third);
		gaRepository.save(root);
	}

	private void createOrganization() {
		Organization org = new Organization("昆明丰盛科技",0);
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
		Account account = new Account("admin", "admin");
		account.setAccessType(AccessType.SYSTEM);
		account.getRoles().add(admin);
		accountRepository.save(account);

		account = new Account("user", "user");
		accountRepository.save(account);
	}
}
