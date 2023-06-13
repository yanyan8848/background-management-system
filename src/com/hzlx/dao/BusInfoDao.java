package com.hzlx.dao;

import com.hzlx.entity.BusInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BusInfoDao {
    /**
     * 模糊查询
     * @param shopName
     * @return
     */
    List<BusInfo> getBusInfoAllByShopName(String shopName);

    /**
     * 编辑    模态框 回显
     * @param id
     * @return
     */
    BusInfo getBusInfoById(Integer id);

    /**
     * 新增/编辑——  无ID为新增    有ID为编辑
     * @param shopName
     * @return
     */
    BusInfo getBusInfoByShopName(String shopName);

    /**
     * 新增/添加
     * @param busInfo
     * @return
     */
    int addBusInfo(BusInfo busInfo);

    /**
     * 编辑/修改
     * @param shopName
     * @param bossName
     * @param password
     * @param tel
     * @param address
     * @param id
     * @return
     */
    int updateBusById(String shopName, String bossName, String password, String tel, String address, Integer id);

    /**
     * 删除——改变状态
     * @param id
     * @return
     */
    int updateBusStatus(Integer id);
    /**
     * 启用
     * @param ids
     * @return
     */
    int batchBusStatus(String[] ids,Integer able);

}
