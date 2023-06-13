package com.hzlx.dao;

import com.hzlx.entity.RoleInfo;

import java.util.List;

public interface RoleInfoDao {
    /**
     *新增
     * @param name
     * @return
     */
     int addRoleInfo(String name);

    /**
     *根据角色名称模糊查询角色列表
     * @param name 角色名称
     * @return 角色集合
     */

    List<RoleInfo> getRoleInfoAllByName(String name);

    /**
     * 根据角色ID查询角色对象
     * @param id 角色ID
     * @return
     */

    RoleInfo getRoleInfoById(Integer id);

    int updateRoleById(String name, Integer id);

    int updateRoleStatus(Integer id);

    int batchUpdateRoleStatus(String[] ids,Integer able);
}
