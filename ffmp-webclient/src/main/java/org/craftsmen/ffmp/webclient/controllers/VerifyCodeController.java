package org.craftsmen.ffmp.webclient.controllers;

import com.github.bingoohuang.patchca.service.CaptchaService;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.jrtech.templates.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by suelmer on 2016/9/12.
 */
@Controller
public class VerifyCodeController {
    @Autowired
    private CaptchaService captchaService;

    @RequestMapping(value = "/patchca.png", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        OutputStream os = null;
        HttpSession session = request.getSession(false);
        try {
            os = response.getOutputStream();
            String patchca = EncoderHelper.getChallangeAndWriteImage(captchaService, "png", os);
            session.setAttribute("PATCHCA", patchca);
            System.out.println("验证码======" + patchca);
            os.flush();
        } catch (IOException e) {
            throw new ServiceException("IO error");
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new ServiceException("IO error");
                }
            }
        }
    }
}
