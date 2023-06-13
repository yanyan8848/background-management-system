package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.service.EasyCaptchaService;
import com.hzlx.utils.BaseResult;
import com.wf.captcha.ArithmeticCaptcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EasyCaptchaServiceImpl implements EasyCaptchaService {
    @Override
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(3);  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
//        captcha.text();  // 获取运算的结果：5
        request.getSession().setAttribute(BgmsConfig.CAPTCHA_CODE, captcha.text().toLowerCase());
        try {
            captcha.out(response.getOutputStream());  // 输出验证码
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String check(HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute(BgmsConfig.CAPTCHA_CODE);
        String code=request.getParameter("code");
//        resp.setContentType("text/html;charset=UTF-8");
        if (captcha.equals(code)){
            return BaseResult.success();
        }else {
            return BaseResult.error(10003,"验证码错误");
        }
    }
}
