package com.hzlx.controller;

import com.hzlx.annotation.Controller;
import com.hzlx.annotation.RequestMapping;
import com.hzlx.annotation.ResponseBody;
import com.hzlx.service.BusInfoService;
import com.hzlx.service.MenuInfoService;
import com.hzlx.service.impl.BusInfoServiceImpl;
import com.hzlx.service.impl.MenuInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/bus")
public class BusInfoController {
    private BusInfoService busInfoService=new BusInfoServiceImpl();
    private MenuInfoService menuInfoService=new MenuInfoServiceImpl();
    /**
     * 模糊查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/busList")
    public String busList(HttpServletRequest request, HttpServletResponse response){
        busInfoService.getBusInfoAllByName(request);
        menuInfoService.showMenu(request);
        return "/pages/bus_list";
    }

    /**
     * 编辑    模态框 回显
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getBus")
    @ResponseBody
    public String getBus(HttpServletRequest request,HttpServletResponse response){
        return busInfoService.getBus(request);
    }

    /**
     * 新增/编辑——  无ID为新增    有ID为编辑
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/editBus")
    @ResponseBody
    public String editBus(HttpServletRequest request,HttpServletResponse response){
        return busInfoService.editBus(request);
    }

    /**
     * 删除——改变状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateBusStatus")
    @ResponseBody
    public String updateBusStatus(HttpServletRequest request,HttpServletResponse response){
        return busInfoService.updateBusStatus(request);
    }

    /**
     * 启用
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ableBus")
    @ResponseBody
    public String ableBus(HttpServletRequest request,HttpServletResponse response){
        return busInfoService.ableBus(request);
    }
}
