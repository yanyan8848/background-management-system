package com.hzlx.filter;

import com.hzlx.utils.PropertiesUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebFilter("/*")
public class FilterVIP implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        //将不论是否登录都需要放行的资源 校验 放行 ----->登录页（index.jsp）登录校验（login）验证码（captcha.do）验证码校验（checkCaptcha.do）静态资源（/static/）
//        String requestURI=request.getRequestURI();

        //需要登录之后才能访问的资源，如果没有登录，重定向到index.jsp
        HttpSession session=request.getSession();
        Object userInfo=session.getAttribute("userInfo");

        if (userInfo != null) {
            if (request.getRequestURI().equals(request.getContextPath()+"/")||request.getRequestURI().equals(request.getContextPath()+"/index.jsp"))
            {
                response.sendRedirect(request.getContextPath()+"/pages/home.jsp");
            }else {
                filterChain.doFilter(request, response);
            }
            //如果登陆过放行

        } else {
            if (filterRequests(request)) {
                //放行
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            //request.getContextPath()---->/bgms
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    private boolean filterRequests(HttpServletRequest request){
        PropertiesUtil.load("config");
        String excludeUrls = PropertiesUtil.getValue("exclude.urls");
        String[] urls=excludeUrls.split(",");
        if (request.getRequestURI().contains("static")){
            return true;
        }
        for (String url : urls) {
            if (request.getRequestURI().equals(request.getContextPath()+url)) {
                return true;
            }
        }
        return false;
    }
}
