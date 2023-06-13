package com.hzlx.service;

import javax.servlet.http.HttpServletRequest;

public interface RoleInfoService {
    /**
     * 根据rolename查询所有角色 支持模糊查询
     * @param request
     */
    void getRoleInfoAllByName(HttpServletRequest request);

    /**
     * 根据角色ID查询角色对象
     * @param request
     * @return
     */
    String getRoleInfoById(HttpServletRequest request);

    /**
     * 新增角色 和 修改角色 根据ID来判断 如果ID能取到值 则为修改 反为正 新增
     * @param request
     * @return
     */
    String editRole(HttpServletRequest request);

    /**
     * 根据角色ID删除角色
     * @param request
     * @return
     */
    String updateRoleStatus(HttpServletRequest request);

    /**
     * 批量启用
     * @param request
     * @return
     */
    String ableRoles(HttpServletRequest request);
}
