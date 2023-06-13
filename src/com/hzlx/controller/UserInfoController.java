package com.hzlx.controller;

import com.hzlx.annotation.Controller;
import com.hzlx.annotation.RequestMapping;
import com.hzlx.annotation.ResponseBody;
import com.hzlx.service.MenuInfoService;
import com.hzlx.service.UserInfoService;
import com.hzlx.service.impl.MenuInfoServiceImpl;
import com.hzlx.service.impl.UserInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/user")
public class UserInfoController {
    private UserInfoService userInfoServlet = new UserInfoServiceImpl();
    private MenuInfoService menuInfoService=new MenuInfoServiceImpl();
    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return userInfoServlet.login(request);
    }

    /**
     * 新增/编辑——  无ID为新增    有ID为编辑
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public String editUser(HttpServletRequest request,HttpServletResponse response){
        return userInfoServlet.editUser(request,response);
    }

    /**
     * 编辑    模态框 回显
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request,HttpServletResponse response){
        return userInfoServlet.getUser(request);
    }

    /**
     * 更改密码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpServletRequest request, HttpServletResponse response){
        return userInfoServlet.edit(request);
    }

    /**
     * 模糊查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/userList")
    public String userList(HttpServletRequest request,HttpServletResponse response){
        userInfoServlet.getUserInfoAllByNickName(request);
        menuInfoService.showMenu(request);
        return "/pages/user_list";
    }

    /**
     * 删除——改变状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateUserStatus")
    @ResponseBody
    public String updateUserStatus(HttpServletRequest request,HttpServletResponse response){
        return userInfoServlet.updateUserStatus(request);
    }

    /**
     * 启用
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ableUser")
    @ResponseBody
    public String ableUser(HttpServletRequest request,HttpServletResponse response){
        return userInfoServlet.ableUser(request);
    }

}
