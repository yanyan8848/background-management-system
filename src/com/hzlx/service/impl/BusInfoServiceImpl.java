package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.BusInfoDao;
import com.hzlx.dao.impl.BusInfoDaoImpl;
import com.hzlx.entity.BusInfo;
import com.hzlx.entity.UserInfo;
import com.hzlx.service.BusInfoService;
import com.hzlx.utils.BaseResult;
import com.hzlx.utils.MD5Utils;
import com.mysql.cj.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BusInfoServiceImpl implements BusInfoService {
    private BusInfoDao busInfoDao=new BusInfoDaoImpl();
    @Override
    public void getBusInfoAllByName(HttpServletRequest request) {
        //获取前端传来的 busname
        String keyword=request.getParameter(BgmsConfig.KEYWORD);
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
        request.setAttribute(BgmsConfig.BUS_LIST,busInfoDao.getBusInfoAllByShopName(keyword));
    }

    @Override
    public String getBus(HttpServletRequest request) {
        Integer id=null;
        try {
            id=Integer.parseInt(request.getParameter("id"));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id==null) {
            return BaseResult.error(10006,"请求数据异常");
        }
        BusInfo busInfo=busInfoDao.getBusInfoById(id);
        return BaseResult.success(busInfo);
    }

    @Override
    public String editBus(HttpServletRequest request) {
        Integer id=null;
        try {
            id=Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String shopName = request.getParameter("shopName");
        String bossName = request.getParameter("bossName");
        String password = request.getParameter("password");
        String encryptMD5 = MD5Utils.encryptMD5(password);
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        int rows=0;
        if (StringUtils.isNullOrEmpty(shopName)
                ||StringUtils.isNullOrEmpty(bossName)
                ||StringUtils.isNullOrEmpty(encryptMD5)
                ||StringUtils.isNullOrEmpty(tel)
                ||StringUtils.isNullOrEmpty(address)) {
            return BaseResult.error(10006,"请求参数异常");

        }
        BusInfo busInfo=new BusInfo();
        busInfo.setShopName(shopName);
        busInfo.setBossName(bossName);
        busInfo.setPassword(encryptMD5);
        busInfo.setTel(tel);
        busInfo.setAddress(address);
        if (id==null) {
            //重名校验
            BusInfo busInfo1=busInfoDao.getBusInfoByShopName(shopName);
            if (busInfo1==null) {
                //添加
                rows=busInfoDao.addBusInfo(busInfo);
                return BaseResult.success();
            }else {
                return BaseResult.error(10009,"店铺名已被使用");
            }
        }else {
            //修改
            rows=busInfoDao.updateBusById(shopName,bossName,encryptMD5,tel,address,id);
            return BaseResult.success();
        }
    }

    @Override
    public String updateBusStatus(HttpServletRequest request) {
        Integer id=null;
        try {
            id=Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int rows=busInfoDao.updateBusStatus(id);
        if (rows>0) {
            return BaseResult.success();
        }else {
            return BaseResult.error(10003,"删除失败");
        }
    }

    @Override
    public String ableBus(HttpServletRequest request) {
        String idsStr=request.getParameter("ids");
        Integer able= null;
        try {
            able = Integer.valueOf(request.getParameter("able"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String[] ids=idsStr.substring(0,idsStr.length()-1).split(",");
        int rows= 0;
            rows = busInfoDao.batchBusStatus(ids,able);
        if (rows>0) {
            return BaseResult.success();
        }else {
            return BaseResult.error(10008,"启用失败");
        }
    }
}
