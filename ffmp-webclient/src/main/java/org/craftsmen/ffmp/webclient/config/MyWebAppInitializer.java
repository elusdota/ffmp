package org.craftsmen.ffmp.webclient.config;

import org.krysalis.barcode4j.servlet.BarcodeServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.*;

/**
 * Created by jiangliang on 2016/8/8.
 */
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //barcode servlet 条码配置
        ServletRegistration.Dynamic registration = servletContext.addServlet("BarcodeServlet", new BarcodeServlet());
        registration.addMapping("/barcode");
    }
}
