package com.hzlx.controller;

import com.hzlx.annotation.Controller;
import com.hzlx.annotation.RequestMapping;
import com.hzlx.annotation.RequestParameter;
import com.hzlx.annotation.ResponseBody;
import com.hzlx.service.MenuInfoService;
import com.hzlx.service.impl.MenuInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/menu")
public class MenuInfoController {
    private MenuInfoService menuInfoService=new MenuInfoServiceImpl();
    @RequestMapping("/showMenuTree")
    public String showMenuTree(HttpServletRequest request, HttpServletResponse response){
        return menuInfoService.showMenu(request);
    }
    @RequestMapping("/menuList")
    public String menuList(HttpServletRequest request, HttpServletResponse response){
        menuInfoService.getMenuList(request);
        menuInfoService.showMenu(request);
        return "pages/menu_list";
    }
    @RequestMapping("/getMenu")
    @ResponseBody
    public String getmenu(HttpServletRequest request, HttpServletResponse response){
        return menuInfoService.getMenu(request);
    }
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpServletRequest request, HttpServletResponse response){
        return menuInfoService.editMenu(request);
    }
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public String deleteMenu(HttpServletRequest request, HttpServletResponse response){
        return menuInfoService.deleteMenu(request);
    }
    @RequestMapping("/addMenu")
    @ResponseBody
    public String addMenu(HttpServletRequest request,HttpServletResponse response){
        return menuInfoService.addMenu(request);
    }
    @RequestMapping("/ableMenu")
    @ResponseBody
    public String ableMenu(HttpServletRequest request,HttpServletResponse response){
        return menuInfoService.ableMenu(request);
    }
}
