package com.jrtech.templates.services;


import com.jrtech.ffmp.data.entities.FlowchartSteps;

import java.util.List;

/**
 * Created by jiangliang on 2016/8/1.
 */
public interface FlowchartStepsService {

    FlowchartSteps findOneByParametric(String parametric);
}
