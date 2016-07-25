package com.jrtech.templates.vo;


import com.jrtech.ffmp.data.entities.Account;
import com.jrtech.ffmp.data.entities.Organization;
import com.jrtech.ffmp.data.entities.Role;

import java.util.Collection;
import java.util.List;


/**
 * Created by jiangliang on 2016/6/23.将角色封装成bootstrap-treeview数据结构对象，elus
 */
public class RoleNode {
    public RoleNode(Organization organization, Account account) {
        this.nodes = new NodesImpl();
        this.nodes.setId(organization.getId());
        this.nodes.setText(organization.getName());
        this.nodes.setType("组织机构");
        if (account != null) {
            State state = new State();
            state.setChecked(false);
            state.setDisabled(true);
            this.nodes.setState(state);
        } else {
            State state = new State();
            state.setChecked(false);
            state.setDisabled(false);
            this.nodes.setSelectable(true);
            this.nodes.setState(state);
        }
        if (organization.getChildren().size() > 0) {
            for (Organization organization1 : organization.getChildren()) {
                this.nodes.getNodes().add(buildTree(organization1, account));
            }
        }
        if (organization.getRoles().size() > 0) {
            for (Role role : organization.getRoles()) {
                buildTree(role, account);
            }
        }
    }

    private Nodes nodes;

    public Nodes getNodes() {
        return nodes;
    }

    public void setNodes(Nodes nodes) {
        this.nodes = nodes;
    }

    public Nodes buildTree(Organization organization,Account account) {
        Nodes nodes = new RoleNode(organization, account).getNodes();
        return nodes;
    }

    public void buildTree(Role role,Account account) {
        Nodes nodes = new NodesImpl();
        nodes.setId(role.getId());
        nodes.setText(role.getName());
        nodes.setType("角色");
        nodes.setSelectable(true);
        if (account != null && account.getRoles().contains(role)) {
            State state = new State();
            state.setChecked(true);
            state.setDisabled(true);
            nodes.setState(state);
        } else {
            State state = new State();
            state.setChecked(false);
            state.setDisabled(false);
            nodes.setState(state);
        }
        this.nodes.getNodes().add(nodes);
    }
}
