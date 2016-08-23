package com.jrtech.templates.vo;

import java.io.Serializable;

/**
 * 创建historyTaskNode需要的参数
 * Created by suelmer on 2016/8/22.
 */
public class HistoryTaskNodeVO implements Serializable{

    //维修任务ID
    private String maintenanceTaskId;
    //当前补助执行结果
    private String stepResult;

    public HistoryTaskNodeVO() {
    }

    public String getMaintenanceTaskId() {
        return maintenanceTaskId;
    }

    public void setMaintenanceTaskId(String maintenanceTaskId) {
        this.maintenanceTaskId = maintenanceTaskId;
    }

    public String getStepResult() {
        return stepResult;
    }

    public void setStepResult(String stepResult) {
        this.stepResult = stepResult;
    }
}
