package com.jrtech.templates.services;


import com.jrtech.ffmp.data.entities.FlowchartSteps;

import java.util.List;

/**
 * Created by jiangliang on 2016/8/1.任务执行步骤，elus
 */
public interface FlowchartStepsService {
    /**
     * 通过步骤参数查询 对象
     * @param parametric
     * @return
     */
    FlowchartSteps findOneByParametric(String parametric);
}
