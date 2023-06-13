package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.UserInfoDao;
import com.hzlx.dao.impl.UserInfoDaoImpl;
import com.hzlx.entity.MenuInfo;
import com.hzlx.entity.UserInfo;
import com.hzlx.service.UserInfoService;
import com.hzlx.utils.BaseResult;
import com.hzlx.utils.MD5Utils;
import com.mysql.cj.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserInfoServiceImpl implements UserInfoService {
    //引入dao层，用户查询数据库
    private UserInfoDao userInfoDao = new UserInfoDaoImpl();

    @Override
    public String login(HttpServletRequest request) {
        //从请求中去获取用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //用户名和密码做非空校验
        if (StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password)) {
            return BaseResult.error(10001, "用户名或密码不能为空");
        }
        //给密码加密
        String encrypted = MD5Utils.encryptMD5(password, userName);
        //拿加密后的密码和用户名去数据库里查询用户信息
        UserInfo userInfo = userInfoDao.getUserInfoByUserNameAndPassword(userName, encrypted);
        //如果查询到userInfo为空，则说明用户不存在，判定为账号密码错误
        if (userInfo == null) {
            return BaseResult.error(10002, "账号或密码错误");
        } else {
            //用户登录成功后，把用户信息存放到session作用域中，用户后续使用
            request.getSession().setAttribute(BgmsConfig.SESSION_USER_KEY, userInfo);
            return BaseResult.success();
        }
    }
    @Override
    public String editUser(HttpServletRequest request,HttpServletResponse response) {
        Integer id= null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
        String nickName=request.getParameter("nickName");
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String encryp = MD5Utils.encryptMD5(password, userName);
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        Integer sex = Integer.valueOf(request.getParameter("sex"));
        String avatar = request.getParameter("avatar");
        int rows=0;
        //非空校验
        if (StringUtils.isNullOrEmpty(nickName)
                ||StringUtils.isNullOrEmpty(userName)
                ||StringUtils.isNullOrEmpty(encryp)
                ||StringUtils.isNullOrEmpty(tel)
                ||StringUtils.isNullOrEmpty(address)
                ||StringUtils.isNullOrEmpty(String.valueOf(sex))
                ||StringUtils.isNullOrEmpty(avatar)) {
            return BaseResult.error(10006,"请求数据异常");
        }
        UserInfo userInfo=new UserInfo();
        userInfo.setNickName(nickName);
        userInfo.setUserName(userName);
        userInfo.setPassword(encryp);
        userInfo.setTel(tel);
        userInfo.setAddress(address);
        userInfo.setSex(sex);
        userInfo.setAvatar(avatar);
        if (id==null) {
            UserInfo userInfo1 = userInfoDao.getUserInfoByUserName(userName);
                if (userInfo1 == null) {
                    rows=userInfoDao.addUserInfo(userInfo);
//                    response.getWriter().write("<h1 style='color:green;'>注册成功</h1>");
                    return BaseResult.success();
                } else {
//                    response.getWriter().write("<h1 style='color:red;'>账号已存在</h1>");
                    return BaseResult.error(10007,"账号已存在");
                }
        }else {
            rows=userInfoDao.updateUserById(nickName,userName,encryp,tel,address,sex,avatar,id);
            return BaseResult.success();
        }

    }













    @Override
    public String edit(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
        String userInfoUserName = userInfo.getUserName();
        String userInfoPassword = userInfo.getPassword();
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        String encrypted1 = MD5Utils.encryptMD5(password, userInfoUserName);
        if (encrypted1.equals(userInfoPassword)) {
            String encrypted2 = MD5Utils.encryptMD5(password1, userInfoUserName);
            userInfo.setPassword(encrypted2);
            int i = userInfoDao.editPwd(userInfoUserName,encrypted2 );
            if (i > 0) {
                request.getSession().removeAttribute("userInfo");
                return BaseResult.success();
            }return BaseResult.error(10004, "更改失败");

        } else {
            return BaseResult.error(10004, "密码错误");
        }

    }

    @Override
    public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String nickName = request.getParameter("nickName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        Integer sex = Integer.valueOf(request.getParameter("sex"));
        String avatar = request.getParameter("avatar");

        response.setContentType("tex/html;charset=UTF-8");
        if (StringUtils.isNullOrEmpty(nickName) || StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password)) {
            response.getWriter().write("<h1 style='color:red;'>昵称、账号和密码不能为空</h1>");
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setNickName(nickName);
            userInfo.setUserName(userName);
            userInfo.setPassword(password);
            userInfo.setTel(tel);
            userInfo.setAddress(address);
            userInfo.setSex(sex);
            userInfo.setAvatar(avatar);
            UserInfo userInfo1 = userInfoDao.getUserInfoByUserName(userName);
            if (userInfo1 == null) {
                userInfoDao.addUserInfo(userInfo);
                response.getWriter().write("<h1 style='color:green;'>注册成功</h1>");
            } else {
                response.getWriter().write("<h1 style='color:red;'>账号已存在</h1>");
            }
        }
        return null;
    }

    @Override
    public void getUserInfoAllByNickName(HttpServletRequest request) {
        //获取前端传来的 rolename
        String keyword=request.getParameter(BgmsConfig.KEYWORD);
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
        request.setAttribute(BgmsConfig.USER_LIST,userInfoDao.getUserInfoAllByNickName(keyword));
    }

    @Override
    public String getUser(HttpServletRequest request) {
        Integer id=null;
        try {
            id=Integer.parseInt(request.getParameter("id"));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id==null) {
            return BaseResult.error(10006,"请求数据异常");
        }
        UserInfo userInfo=userInfoDao.getUserInfoById(id);
        return BaseResult.success(userInfo);
    }

    @Override
    public String updateUserStatus(HttpServletRequest request) {
        Integer id=null;
        try {
            id=Integer.parseInt(request.getParameter("id"));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int rows=userInfoDao.updateUserStatus(id);
        if (rows>0) {
            return BaseResult.success();
        }else {
            return BaseResult.error(10003,"删除失败");
        }
    }

    @Override
    public String ableUser(HttpServletRequest request) {
        String idsStr=request.getParameter("ids");
        Integer able= null;
        try {
            able = Integer.valueOf(request.getParameter("able"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String[] ids =idsStr.substring(0,idsStr.length()-1).split(",");
        int rows=userInfoDao.batchUpdateStatus(ids,able);
        if (rows>0) {
            return BaseResult.success();
        }else {
            return BaseResult.error(10008,"启用失败");
        }
    }


}
