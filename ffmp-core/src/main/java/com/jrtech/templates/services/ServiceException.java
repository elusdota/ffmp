package com.jrtech.templates.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by elus on 2015-06-03.异常定义
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

    public ServiceException(){
        super("系统平台服务错误!");
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
