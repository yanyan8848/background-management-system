package com.hzlx.dao.impl;

import com.hzlx.dao.UserInfoDao;
import com.hzlx.entity.UserInfo;
import com.hzlx.utils.BaseDao;
import com.mysql.cj.util.StringUtils;

import java.util.List;

public class UserInfoDaoImpl extends BaseDao<UserInfo> implements UserInfoDao {
    @Override
    public UserInfo getUserInfoByUserNameAndPassword(String userName, String password) {
        return selectOne("select * from t_user_info where user_name=? and `password`=?", UserInfo.class,userName,password);
    }

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return selectOne("select * from t_user_info where user_name=?", UserInfo.class,userName);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        String sql="insert into t_user_info values (null,?,?,?,?,?,?,?,null,default);";
        return executeUpdate(sql,
                userInfo.getNickName(),
                userInfo.getUserName(),
                userInfo.getPassword(),
                userInfo.getTel(),
                userInfo.getAddress(),
                userInfo.getSex(),
                userInfo.getAvatar());
    }

    @Override
    public int editPwd(String userName,String password) {
        String sql="update t_user_info set password =? where user_name=?";
        return executeUpdate(sql,password,userName);
    }

    @Override
    public List<UserInfo> getUserInfoAllByNickName(String nickName) {
        String sql="select * from t_user_info";
        if (!StringUtils.isNullOrEmpty(nickName)) {
            sql += " where nick_name like concat('%',?,'%')";
            return selectListForObject(sql, UserInfo.class,nickName);
        }
        return selectListForObject(sql, UserInfo.class);
    }

    @Override
    public int updateUserById(String nickName, String userName, String password, String tel, String address, Integer sex, String avatar, Integer id) {
        String sql="update t_user_info set nick_name=?,user_name=?,`password`=?,tel=?,address=?,sex=?,avatar=? where id=?";
        return executeUpdate(sql,nickName,userName,password,tel,address,sex,avatar,id);
    }

    @Override
    public UserInfo getUserInfoById(Integer id) {
        String sql="select * from t_user_info where id=?";
        return selectOne(sql,UserInfo.class,id);
    }

    @Override
    public int updateUserStatus(Integer id) {
        String sql="update t_user_info set `status`=0 where id=?";
        return executeUpdate(sql,id);
    }

    @Override
    public int batchUpdateStatus(String[] ids, Integer able) {
        String sql=null;
        if (able==1) {
            sql="update t_user_info set `status`=1 where id in(";
        }else {
            sql="update t_user_info set `status`=0 where id in(";
        }
        for (int i = 0; i < ids.length; i++) {
            if (i==ids.length-1) {
                sql+=" ?";
            }else {
                sql+=" ?,";
            }
        }
        sql+=")";
        return executeUpdate(sql,ids);
    }
}
