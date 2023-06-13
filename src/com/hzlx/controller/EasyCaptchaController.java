package com.hzlx.controller;


import com.hzlx.annotation.*;
import com.hzlx.service.EasyCaptchaService;
import com.hzlx.service.impl.EasyCaptchaServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/api/captcha")

public class EasyCaptchaController  {
    private EasyCaptchaService easyCaptchaServlet=new EasyCaptchaServiceImpl();
    @RequestMapping("/code")

    public String captcha(HttpServletRequest request, HttpServletResponse response){
        easyCaptchaServlet.captcha(request,response);
        return "";
    }
    @RequestMapping("/check")
    @ResponseBody
    public String check(HttpServletRequest request,HttpServletResponse response){
        return easyCaptchaServlet.check(request);
    }
}
