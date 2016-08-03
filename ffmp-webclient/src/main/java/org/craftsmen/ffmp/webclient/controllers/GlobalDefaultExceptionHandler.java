package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.templates.services.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiangliang on 2016/7/26.异常处理控制器。elus
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, RuntimeException e) throws Exception {
        ModelAndView model = new ModelAndView();
        if(e.getMessage().isEmpty()){
            model.addObject("serviceException", new ServiceException());
        }else {
            model.addObject("serviceException", new ServiceException(e.getMessage()));
        }
        model.addObject("url", req.getRequestURL());
        model.setViewName("common/tips");
        return model;
    }

}
