package com.hzlx.service.impl;

import com.hzlx.component.BgmsConfig;
import com.hzlx.dao.MenuInfoDao;
import com.hzlx.dao.impl.MenuInfoDaoImpl;
import com.hzlx.entity.MenuInfo;
import com.hzlx.entity.UserInfo;
import com.hzlx.entity.vo.MenuInfoVo;
import com.hzlx.service.MenuInfoService;
import com.hzlx.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class MenuInfoServiceImpl implements MenuInfoService {
    private MenuInfoDao menuInfoDao=new MenuInfoDaoImpl();
    @Override
    public String showMenu(HttpServletRequest request) {
        //获取session中的用户信息
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY);
        //组装显示的菜单树
        List<MenuInfoVo> menuInfoVos=assemblyMenuTree(userInfo.getId());
        request.setAttribute(BgmsConfig.CACHE_MENU_LIST,menuInfoVos);
        request.getSession().setAttribute(BgmsConfig.CACHE_MENU_LIST,menuInfoVos);
        return "pages/home";
    }
    public void getMenuList(HttpServletRequest request){
        String keyword=request.getParameter("keyword");
        request.setAttribute(BgmsConfig.MENU_LIST,menuInfoDao.getMenuAll(keyword));
        request.setAttribute(BgmsConfig.FIRST_MENU,menuInfoDao.getMenuInfoByPid(-1));
        request.setAttribute(BgmsConfig.KEYWORD,keyword);
    }

    @Override
    public String getMenu(HttpServletRequest request) {
        //获取用户提交过来的menuId
        Integer id=Integer.parseInt(request.getParameter("id"));
        if ( id == null ){
            return BaseResult.error(20001,"请求数据异常");
        }

        MenuInfo menuInfo = menuInfoDao.getMenuInfoById(id);
        return BaseResult.success(menuInfo);
    }

    @Override
    public String editMenu(HttpServletRequest request) {
        Integer id=Integer.parseInt(request.getParameter("id"));
        Integer pId = Integer.parseInt(request.getParameter("pId"));
        String title=request.getParameter("title");
        String icon=request.getParameter("icon");
        String href = request.getParameter("href");

        //TODO参数校验 如果非空字段为空 给出错误提示
        int rows=menuInfoDao.updateMwnuInfoById(id,title,icon,href,pId);
        if (rows>0) {
            return BaseResult.success();
        } else {
            return BaseResult.error(20002,"修改菜单失败");
        }

    }

    @Override
    public String deleteMenu(HttpServletRequest request) {
        Integer id=Integer.parseInt(request.getParameter("id"));
        //TODO判空
        int rows=menuInfoDao.deleteMenuInfoById(id);
        if (rows>0) {
            return BaseResult.success();
        } else {
            return BaseResult.error(20002,"删除菜单失败");
        }
    }

    @Override
    public String addMenu(HttpServletRequest request) {
        Integer pId=Integer.parseInt(request.getParameter("pId"));
        String title=request.getParameter("title");
        String icon = request.getParameter("icon");
        String href = request.getParameter("href");
        int rows=menuInfoDao.saveMenu(title,icon,href,pId);
        if (rows>0) {
            return BaseResult.success();
        }
        return BaseResult.error(20003,"新增菜单失败");
    }

    @Override
    public String ableMenu(HttpServletRequest request) {
        String idsStr=request.getParameter("ids");
        Integer able= null;
        try {
            able = Integer.valueOf(request.getParameter("able"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String[] ids = idsStr.substring(0, idsStr.length() - 1).split(",");
        int rows=menuInfoDao.batchUpdataStatus(ids,able);
        if (rows>0) {
            return BaseResult.success();
        }else {
            return BaseResult.error(20008,"启动失败");
        }
    }

    /**
     * 组装菜单树
     * @return
     */
    private List<MenuInfoVo> assemblyMenuTree(Integer userId){
        List<MenuInfo> oneMenuList=menuInfoDao.getMenInfoListByPid(userId,-1);
        List<MenuInfoVo> menuList=new ArrayList<>();
        //循环遍历一级菜单
        oneMenuList.forEach(menuInfo -> {
            MenuInfoVo menuInfoVo = menuInfoConvertMenuInfoVo(menuInfo);
            menuInfoVo.setList(menuInfoDao.getMenInfoListByPid(userId,menuInfoVo.getId()));
            menuList.add(menuInfoVo);
        });
        return menuList;
    }

    /**
     * 将menInfo转换为MenuInfoVo
     * @param menuInfo
     * @return
     */
    private MenuInfoVo menuInfoConvertMenuInfoVo(MenuInfo menuInfo){
        MenuInfoVo menuInfoVo = new MenuInfoVo();
        menuInfoVo.setId(menuInfo.getId());
        menuInfoVo.setTitle(menuInfo.getTitle());
        menuInfoVo.setIcon(menuInfo.getIcon());
        menuInfoVo.setHref(menuInfo.getHref());
        menuInfoVo.setPId(menuInfo.getPId());
        menuInfoVo.setCreateTime(menuInfo.getCreateTime());
        menuInfoVo.setStatus(menuInfo.getStatus());

        return menuInfoVo;


    }
}
