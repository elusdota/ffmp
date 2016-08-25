package com.jrtech.ffmp.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by jiangliang on 2016/8/1.任务参数,elus
 */
@Entity
@Table(name = "FlowchartSteps")
@JsonIgnoreProperties(value = {"taskDefinition"})
public class FlowchartSteps extends AbstractNamedObject {
    public FlowchartSteps() {
        super();
    }

    public FlowchartSteps(String name, String parametric, String type, String yes, String no) {
        super(name);
        this.parametric = parametric;
        this.type = type;
        this.yes = yes;
        this.no = no;
    }

    @NotNull
    @ManyToOne
    private TaskDefinition taskDefinition;
    //键
    private String parametric;
    //执行类型 operation：操作，strat:开始，end:结束，subroutine：子节点，condition,条件，inputoutput：输入输出
    private String type;
    //颜色
    private String color;
    private String yes;
    private String no;

    public String getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = yes;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getParametric() {
        return parametric;
    }

    public void setParametric(String parametric) {
        this.parametric = parametric;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }

    public String getCatch(String par) {
        if (null == par) {
           return null;
        }
        return "yes" .equals(par)  ? yes : no;
    }
}
