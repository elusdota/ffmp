package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.GrantedAuthorityImpl;
import com.jrtech.ffmp.data.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by jiangliang on 2016/6/25.boostrap-treeview 权限树数据结构对象，elus
 */
public class GrantedAuthorityNode {

    public GrantedAuthorityNode(GrantedAuthorityImpl grantedAuthority,Role role) {

        this.nodes = new NodesImpl();
        this.nodes.setId(grantedAuthority.getId());
        this.nodes.setText(grantedAuthority.getName());
        this.nodes.setTags("available");
        if (role!=null&&role.getAuthorities().contains(grantedAuthority)) {
            State state=new State();
            state.setChecked(true);
            state.setDisabled(true);
//            state.setExpanded(true);
            this.nodes.setState(state);
        }
        else {
            State state=new State();
            state.setChecked(false);
            state.setDisabled(true);
//            state.setExpanded(false);
            this.nodes.setState(state);
        }
        if (grantedAuthority.getChildren().size() > 0) {
            for (GrantedAuthorityImpl grantedAuthority1 : grantedAuthority.getChildren()) {
                this.nodes.getNodes().add(buildTree(grantedAuthority1,role));
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

    public Nodes buildTree(GrantedAuthorityImpl grantedAuthority,Role role) {
        Nodes nodes = new GrantedAuthorityNode(grantedAuthority,role).getNodes();
        return nodes;
    }
}
