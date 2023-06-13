package com.hzlx.dao;

import com.hzlx.entity.MenuInfo;

import java.util.List;
import java.util.Map;

public interface MenuInfoDao {
    List<MenuInfo> getMenInfoListByPid(Integer userId,Integer pId);

    List<Map<String,Object>> getMenuAll(String keyword);

    MenuInfo getMenuInfoById(Integer id);

    int updateMwnuInfoById(Integer id, String title, String icon,String href,Integer pId);

    int deleteMenuInfoById(Integer id);

    int saveMenu(String title, String icon, String href, Integer pId);

    List<MenuInfo> getMenuInfoByPid(Integer pId);

    int batchUpdataStatus(String[] ids,Integer able);
}