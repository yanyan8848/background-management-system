package com.hzlx.service;

import javax.servlet.http.HttpServletRequest;

public interface BusInfoService {
    /**
     * 模糊查询
     * @param request
     */
    void getBusInfoAllByName(HttpServletRequest request);

    /**
     * 编辑    模态框 回显
     * @param request
     * @return
     */
    String getBus(HttpServletRequest request);

    /**
     * 新增/编辑——  无ID为新增    有ID为编辑    已加密
     * @param request
     * @return
     */
    String editBus(HttpServletRequest request);

    /**
     * 删除——改变状态
     * @param request
     * @return
     */
    String updateBusStatus(HttpServletRequest request);

    /**
     * 启用
     * @param request
     * @return
     */
    String ableBus(HttpServletRequest request);
}
