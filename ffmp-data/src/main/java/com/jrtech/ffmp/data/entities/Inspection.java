package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jiangliang on 2016/11/2.巡检标准，elus
 */
@Entity
@Table(name = "Inspection")
@JsonIgnoreProperties(value = {"maintenanceProject"})
public class Inspection extends AbstractNamedObject{
    @NotNull
    @ManyToOne
    private MaintenanceProject maintenanceProject;
    //巡检比例
    private int ratio;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<MrrStandard> mrrStandards=new ArrayList<MrrStandard>();

    public MaintenanceProject getMaintenanceProject() {
        return maintenanceProject;
    }

    public void setMaintenanceProject(MaintenanceProject maintenanceProject) {
        this.maintenanceProject = maintenanceProject;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public Collection<MrrStandard> getMrrStandards() {
        Collection<MrrStandard> mrrStandards1 = new ArrayList<>();
        mrrStandards.forEach(mrrStandard -> {
            if (!mrrStandards1.contains(mrrStandard)) {
                mrrStandards1.add(mrrStandard);
            }
        });
        mrrStandards.clear();
        mrrStandards.addAll(mrrStandards1);
        return mrrStandards;
    }
}
