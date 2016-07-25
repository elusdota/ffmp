package com.jrtech.templates.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangliang on 2016/6/23.实现bootstrap-treeview数据结构，elus
 */
public class NodesImpl implements Nodes {
    private String id;
    private String text;
    private String type;
    private State state;
    private String tags;
    private boolean selectable;


    public void setNodes(List<Nodes> nodes) {
        this.nodes = nodes;
    }

    private List<Nodes> nodes = new ArrayList<Nodes>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public List<Nodes> getNodes() {
        return this.nodes;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }


    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getTags() {
        return this.tags;
    }

    @Override
    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean isSelectable() {
        return selectable;
    }

    @Override
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
}
