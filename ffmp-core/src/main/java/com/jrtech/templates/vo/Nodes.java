package com.jrtech.templates.vo;

import java.util.List;

/**
 * Created by jiangliang on 2016/6/23.boostrap-treeview 数据结构接口，elus
 */
public interface Nodes {
    public String getId();

    public void setId(String id);

    public String getText();

    public void setText(String text);

    public List<Nodes> getNodes();

    public State getState();

    public void setState(State state);

    public String getType();

    public void setType(String type);

    public String getTags();

    public void setTags(String tags);

    public boolean isSelectable();

    public void setSelectable(boolean selectable);
}