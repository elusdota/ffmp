package com.jrtech.templates.vo;

/**
 * Created by jiangliang on 2016/6/25.bootstrap-treeview 状态对象，elus
 */
public class State {
	//是否选中checkbox
    public  boolean checked;
    //是否禁用
    public  boolean disabled;
    //是否展开
    public  boolean expanded;
    //是否选中
    public  boolean selected;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
