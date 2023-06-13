package com.hzlx.dao;

import com.hzlx.entity.UserInfo;

import java.util.List;

public interface UserInfoDao {
    /**
     * 根据用户名和密码 查询用户信息
     * @param userName 用户名
     * @param password 密码
     * @return 用户对象
     */
    UserInfo getUserInfoByUserNameAndPassword(String userName,String password);
    /**
     * 对账号进行校验
     * @param userName
     * @return
     */
    UserInfo getUserInfoByUserName(String userName);
    /**
     * 新增
     * @param userInfo
     * @return
     */
    int addUserInfo(UserInfo userInfo);
    /**
     * 更改密码
     * @param userName
     * @param password
     * @return
     */
    int editPwd(String userName,String password);
    /**
     * 模糊查询
     * @param nickName
     * @return
     */
  List<UserInfo> getUserInfoAllByNickName(String nickName);
    /**
     * 编辑-修改
     * @param nickName
     * @param userName
     * @param password
     * @param tel
     * @param address
     * @param sex
     * @param avatar
     * @param id
     * @return
     */
    int updateUserById(String nickName, String userName, String password, String tel, String address, Integer sex, String avatar, Integer id);
    /**
     * 编辑    模态框 回显
     * @param id
     * @return
     */
    UserInfo getUserInfoById(Integer id);
    /**
     * 删除
     * @param id
     * @return
     */
    int updateUserStatus(Integer id);
    /**
     * 启用
     * @param ids
     * @return
     */
    int batchUpdateStatus(String[] ids,Integer able);
}

