package com.jrtech.templates.services;

/**
 * Created by elus on 2015-06-03.异常定义
 */
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
