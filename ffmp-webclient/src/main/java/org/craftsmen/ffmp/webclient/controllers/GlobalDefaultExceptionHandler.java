package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.templates.services.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiangliang on 2016/7/26.异常处理控制器。elus
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {ServiceException.class})
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, ServiceException e) throws Exception {
        ModelAndView model = new ModelAndView();
        if (e.getMessage().isEmpty()) {
            model.addObject("serviceException", e);
        } else {
            model.addObject("serviceException", e.getMessage());
        }
        model.addObject("url", req.getRequestURL());
        if (e.getViewName().isEmpty() || null == e.getViewName()) {
            model.setViewName("common/tips");
        } else {
            model.setViewName(e.getViewName());
        }

        return model;
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class})
    public ModelAndView
    runTimeErrorHandler(HttpServletRequest req, RuntimeException e) throws Exception {
        ModelAndView model = new ModelAndView();
        if (e.getMessage().isEmpty()) {
            model.addObject("serviceException", e);
        } else {
            model.addObject("serviceException", e.getMessage());
        }
        model.addObject("url", req.getRequestURL());
        model.setViewName("common/tips");
        return model;
    }
}
