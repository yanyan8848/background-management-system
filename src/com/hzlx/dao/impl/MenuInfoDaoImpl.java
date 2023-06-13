package com.hzlx.dao.impl;

import com.hzlx.dao.MenuInfoDao;
import com.hzlx.entity.MenuInfo;
import com.hzlx.utils.BaseDao;
import com.mysql.cj.util.StringUtils;

import java.util.List;
import java.util.Map;

public class MenuInfoDaoImpl extends BaseDao<MenuInfo> implements MenuInfoDao {

    @Override
    public List<MenuInfo> getMenInfoListByPid(Integer userId,Integer pId) {
//        String sql="select * from t_menu_info where `status`=1 and p_id=?";
        String sql="select tmi.* from t_menu_info tmi \n" +
                "join t_menu_role_info tmri on tmri.menu_id = tmi.id\n" +
                "join t_user_role_info turi on tmri.role_id = turi.role_id\n" +
                "where turi.user_id=? and tmi.`status`=1 and tmi.p_id=?";
        return selectListForObject(sql,MenuInfo.class,userId,pId);
    }

    @Override
    public List<Map<String, Object>> getMenuAll(String keyword) {
        String sql="select tmi1.*,tmi2.title as pName from t_menu_info tmi1\n" +
                "left join t_menu_info tmi2 on tmi2.id=tmi1.p_id";
        if (!StringUtils.isNullOrEmpty(keyword)) {
            sql+=" where tmi1.title like concat('%',?,'%')";
            return selectListForMap(sql,keyword);
        }
        return selectListForMap(sql);
    }

    @Override
    public MenuInfo getMenuInfoById(Integer id) {
        String sql="select * from t_menu_info where id=?";
        return selectOne(sql,MenuInfo.class,id);
    }

    @Override
    public int updateMwnuInfoById(Integer id, String title, String icon,String href,Integer pId) {
        String sql="update t_menu_info set title=?,icon=?,href=?,p_id=? where id=?";
        return executeUpdate(sql,title,icon,href,pId,id);
    }

    @Override
    public int deleteMenuInfoById(Integer id) {
        String sql="update t_menu_info set `status`=0 where id=?";
        return executeUpdate(sql,id);
    }

    @Override
    public int saveMenu(String title, String icon, String href, Integer pId) {
        String sql="insert into t_menu_info values(null,?,?,?,?,now(),default)";
        return executeUpdate(sql,title,icon,href,pId);
    }

    @Override
    public List<MenuInfo> getMenuInfoByPid(Integer pId) {
        String sql="select * from t_menu_info where `status`=1 and p_id=?";
        return selectListForObject(sql,MenuInfo.class,pId);
    }

    @Override
    public int batchUpdataStatus(String[] ids,Integer able) {
        String sql=null;
        if (able==1) {
            sql="update t_menu_info set `status`=1 where id in(";
        }else {
            sql="update t_menu_info set `status`=0 where id in(";
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
