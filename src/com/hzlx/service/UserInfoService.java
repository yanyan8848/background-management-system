package com.hzlx.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户相关的底层痴线
 */
public interface UserInfoService {
    /**
     * 用户登录
     * @param request http请求。用于获取用户提交的数据
     * @return json字符串
     */
    String login(HttpServletRequest request);

    /**
     * 新增/编辑——  无ID为新增    有ID为编辑    已加密
     * @param request
     * @param response
     * @return
     */
    String editUser(HttpServletRequest request,HttpServletResponse response);


    /**
     * 更改密码
     * @param request
     * @return
     */
    String edit(HttpServletRequest request);

    /**
     * 注册   账号验证
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    String register(HttpServletRequest request,HttpServletResponse response ) throws IOException;

    /**
     * 模糊查询
     * @param request
     */
    void getUserInfoAllByNickName(HttpServletRequest request);

    /**
     * 编辑    模态框 回显
     * @param request
     * @return
     */
    String getUser(HttpServletRequest request);

    /**
     * 删除——改变状态
     * @param request
     * @return
     */
    String updateUserStatus(HttpServletRequest request);

    /**
     * 启用
     * @param request
     * @return
     */
    String ableUser(HttpServletRequest request);
}
