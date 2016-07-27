package com.jrtech.templates.vo;


import com.jrtech.ffmp.data.entities.Organization;

/**
 * Created by jiangliang on 2016/6/23.
 */
public class OrganizationNode extends Organization {
    public OrganizationNode(Organization organization) {
        this.nodes = new NodesImpl();
        this.nodes.setId(organization.getId());
        this.nodes.setText(organization.getName());
        this.nodes.setType("组织机构");
        State state = new State();
        state.setChecked(false);
        state.setDisabled(false);
        state.setExpanded(true);
        this.nodes.setSelectable(true);
        this.nodes.setState(state);
        if (organization.getChildren().size() > 0) {
            for (Organization organization1 : organization.getChildren()) {
                this.nodes.getNodes().add(buildTree(organization1));
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
    public Nodes buildTree(Organization organization) {
        Nodes nodes = new OrganizationNode(organization).getNodes();
        return nodes;
    }
}
