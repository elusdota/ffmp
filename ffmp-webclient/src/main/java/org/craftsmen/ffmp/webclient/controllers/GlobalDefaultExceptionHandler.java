package org.craftsmen.ffmp.webclient.controllers;

import com.jrtech.templates.services.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiangliang on 2016/7/26.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ModelAndView
    defaultErrorHandler(HttpServletRequest req, RuntimeException e) throws Exception {
        ModelAndView model = new ModelAndView();
        model.addObject("serviceException",new ServiceException(e.getMessage()));
        model.addObject("url", req.getRequestURL());
        model.setViewName("common/tips");
        System.out.println(e.getMessage()+"------------------massage");
        return model;
    }

}
