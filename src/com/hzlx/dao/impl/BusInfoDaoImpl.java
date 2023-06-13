package com.hzlx.dao.impl;

import com.hzlx.dao.BusInfoDao;
import com.hzlx.entity.BusInfo;
import com.hzlx.utils.BaseDao;
import com.mysql.cj.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusInfoDaoImpl extends BaseDao<BusInfo> implements BusInfoDao{
    @Override
    public List<BusInfo> getBusInfoAllByShopName(String shopName) {
        String sql="select * from t_bus_info";
        if (!StringUtils.isNullOrEmpty(shopName)) {
            sql+=" where shop_name like concat('%',?,'%')";
            return selectListForObject(sql,BusInfo.class,shopName);
        }
        return selectListForObject(sql,BusInfo.class);
    }

    @Override
    public BusInfo getBusInfoById(Integer id) {
        String sql="select * from t_bus_info where id=?";
        return selectOne(sql,BusInfo.class,id);
    }

    @Override
    public BusInfo getBusInfoByShopName(String shopName) {
        return selectOne("select * from t_bus_info where shop_name=?",BusInfo.class,shopName);
    }

    @Override
    public int addBusInfo(BusInfo busInfo) {
        String sql="insert into t_bus_info values(null,?,?,?,?,?,default,default)";
        return executeUpdate(sql,
                busInfo.getShopName(),
                busInfo.getBossName(),
                busInfo.getPassword(),
                busInfo.getTel(),
                busInfo.getAddress());
    }

    @Override
    public int updateBusById(String shopName, String bossName, String password, String tel, String address, Integer id) {
        String sql="update t_bus_info set shop_name=?,boss_name=?,password=?,tel=?,address=? where id=?";
        return executeUpdate(sql,shopName,bossName,password,tel,address,id);
    }

    @Override
    public int updateBusStatus(Integer id) {
        String sql="update t_bus_info set `status`=0 where id=?";
        return executeUpdate(sql,id);
    }

    @Override
    public int batchBusStatus(String[] ids,Integer able) {
        String sql=null;
        if (able==1) {
            sql="update t_bus_info set `status`=1 where id in(";
        }else {
            sql="update t_bus_info set `status`=0 where id in(";
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
