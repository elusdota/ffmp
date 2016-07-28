package com.jrtech.templates.vo;

import com.jrtech.ffmp.data.entities.MrrStandard;

/**
 * Created by suelmer on 2016/7/18.
 */
public class MrrStandardVO {

    private  MrrStandard mrrStandard;

    private  MrrStandard parent;

    public MrrStandard getMrrStandard() {
        return mrrStandard;
    }

    public void setMrrStandard(MrrStandard mrrStandard) {
        this.mrrStandard = mrrStandard;
    }

    public MrrStandard getParent() {
        return parent;
    }

    public void setParent(MrrStandard parent) {
        this.parent = parent;
    }
}
