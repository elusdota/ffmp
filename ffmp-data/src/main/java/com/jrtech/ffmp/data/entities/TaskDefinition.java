package com.jrtech.ffmp.data.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jiangliang on 2016/8/1.任务定义，elus
 */
@Entity
@Table(name = "TaskDefinition")
public class TaskDefinition extends AbstractNamedObject {
    public TaskDefinition(){
        super();
    }
    public TaskDefinition(String name) {
        super(name);
    }
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taskDefinition", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Collection<FlowchartSteps> flowchartStepses = new ArrayList<FlowchartSteps>();
    private String value;

    public Collection<FlowchartSteps> getFlowchartStepses() {
        return flowchartStepses;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
