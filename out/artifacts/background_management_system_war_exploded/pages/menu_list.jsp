<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/utils/tools.jsp"  %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>文档列表 - 光年(Light Year Admin)后台管理系统模板</title>
    <link rel="icon" href="${ctx}/static/bootstrap/images/favicon.ico" type="image/ico">
    <meta name="keywords" content="LightYear,光年,后台模板,后台管理系统,光年HTML模板">
    <meta name="description" content="LightYear是一个基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
    <meta name="author" content="yinqi">
    <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-3.3.4.css">
    <link href="${ctx}/static/bootstrap/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="${ctx}/static/bootstrap/css/style.min.css" rel="stylesheet">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <aside class="lyear-layout-sidebar">

            <!-- logo -->
            <div id="logo" class="sidebar-header">
                <a href="index.html"><img src="${ctx}/static/bootstrap/images/logo-sidebar.png" title="LightYear" alt="LightYear" /></a>
            </div>
            <div class="lyear-layout-sidebar-scroll">

                <nav class="sidebar-main">
                    <ul class="nav nav-drawer">
                        <c:forEach var="menu" items="${menuList}">
                            <li
                                    <c:if test="${not empty menu.list}">
                                        class="nav-item nav-item-has-subnav"
                                        <c:if test="${menu.id==4}">active open</c:if>
                                    </c:if>
                            ><a href="<c:if test="${menu.href!='javascript:void(0)'}">${ctx}</c:if>${menu.href}"><i class="${menu.icon}"></i>${menu.title}</a>
                                    <%--not empty 对象---->对对象判空--%>
                                <ul class="nav nav-subnav">
                                    <c:if test="${not empty menu.list}">
                                        <c:forEach var="menuItem" items="${menu.list}">
                                    <li <c:if test="${menuItem.id==5}"> class="active" </c:if>
                                        ><a href="${ctx}${menuItem.href}"><i class="${menuItem.icon}"></i>${menuItem.title}</a></li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </li>

                        </c:forEach>
                    </ul>
                </nav>


            </div>

        </aside>
        <!--End 左侧导航-->

        <!--头部信息-->
        <header class="lyear-layout-header">

            <nav class="navbar navbar-default">
                <div class="topbar">

                    <div class="topbar-left">
                        <div class="lyear-aside-toggler">
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                            <span class="lyear-toggler-bar"></span>
                        </div>
                        <span class="navbar-page-title"> 示例页面 - 菜单列表 </span>
                    </div>

                    <ul class="topbar-right">
                        <li class="dropdown dropdown-profile">
                            <a href="javascript:void(0)" data-toggle="dropdown">
                                <img class="img-avatar img-avatar-48 m-r-10" src="${ctx}/static/bootstrap/images/users/avatar.jpg" alt="后台系统" />
                                <span>${userInfo.nickName} <span class="caret"></span></span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li> <a href="lyear_pages_profile.html"><i class="mdi mdi-account"></i> 个人信息</a> </li>
                                <li> <a href="lyear_pages_edit_pwd.html"><i class="mdi mdi-lock-outline"></i> 修改密码</a> </li>
                                <li> <a href="javascript:void(0)"><i class="mdi mdi-delete"></i> 清空缓存</a></li>
                                <li class="divider"></li>
                                <li> <a href="lyear_pages_login.html"><i class="mdi mdi-logout-variant"></i> 退出登录</a> </li>
                            </ul>
                        </li>
                        <!--切换主题配色-->
                        <li class="dropdown dropdown-skin">
                            <span data-toggle="dropdown" class="icon-palette"><i class="mdi mdi-palette"></i></span>
                            <ul class="dropdown-menu dropdown-menu-right" data-stopPropagation="true">
                                <li class="drop-title"><p>主题</p></li>
                                <li class="drop-skin-li clearfix">
                  <span class="inverse">
                    <input type="radio" name="site_theme" value="default" id="site_theme_1" checked>
                    <label for="site_theme_1"></label>
                  </span>
                                    <span>
                    <input type="radio" name="site_theme" value="dark" id="site_theme_2">
                    <label for="site_theme_2"></label>
                  </span>
                                    <span>
                    <input type="radio" name="site_theme" value="translucent" id="site_theme_3">
                    <label for="site_theme_3"></label>
                  </span>
                                </li>
                                <li class="drop-title"><p>LOGO</p></li>
                                <li class="drop-skin-li clearfix">
                  <span class="inverse">
                    <input type="radio" name="logo_bg" value="default" id="logo_bg_1" checked>
                    <label for="logo_bg_1"></label>
                  </span>
                                    <span>
                    <input type="radio" name="logo_bg" value="color_2" id="logo_bg_2">
                    <label for="logo_bg_2"></label>
                  </span>
                                    <span>
                    <input type="radio" name="logo_bg" value="color_3" id="logo_bg_3">
                    <label for="logo_bg_3"></label>
                  </span>
                                    <span>
                    <input type="radio" name="logo_bg" value="color_4" id="logo_bg_4">
                    <label for="logo_bg_4"></label>
                  </span>
                                    <span>
                    <input type="radio" name="logo_bg" value="color_5" id="logo_bg_5">
                    <label for="logo_bg_5"></label>
                  </span>
                                    <span>
                    <input type="radio" name="logo_bg" value="color_6" id="logo_bg_6">
                    <label for="logo_bg_6"></label>
                  </span>
                                    <span>
                    <input type="radio" name="logo_bg" value="color_7" id="logo_bg_7">
                    <label for="logo_bg_7"></label>
                  </span>
                                    <span>
                    <input type="radio" name="logo_bg" value="color_8" id="logo_bg_8">
                    <label for="logo_bg_8"></label>
                  </span>
                                </li>
                                <li class="drop-title"><p>头部</p></li>
                                <li class="drop-skin-li clearfix">
                  <span class="inverse">
                    <input type="radio" name="header_bg" value="default" id="header_bg_1" checked>
                    <label for="header_bg_1"></label>
                  </span>
                                    <span>
                    <input type="radio" name="header_bg" value="color_2" id="header_bg_2">
                    <label for="header_bg_2"></label>
                  </span>
                                    <span>
                    <input type="radio" name="header_bg" value="color_3" id="header_bg_3">
                    <label for="header_bg_3"></label>
                  </span>
                                    <span>
                    <input type="radio" name="header_bg" value="color_4" id="header_bg_4">
                    <label for="header_bg_4"></label>
                  </span>
                                    <span>
                    <input type="radio" name="header_bg" value="color_5" id="header_bg_5">
                    <label for="header_bg_5"></label>
                  </span>
                                    <span>
                    <input type="radio" name="header_bg" value="color_6" id="header_bg_6">
                    <label for="header_bg_6"></label>
                  </span>
                                    <span>
                    <input type="radio" name="header_bg" value="color_7" id="header_bg_7">
                    <label for="header_bg_7"></label>
                  </span>
                                    <span>
                    <input type="radio" name="header_bg" value="color_8" id="header_bg_8">
                    <label for="header_bg_8"></label>
                  </span>
                                </li>
                                <li class="drop-title"><p>侧边栏</p></li>
                                <li class="drop-skin-li clearfix">
                  <span class="inverse">
                    <input type="radio" name="sidebar_bg" value="default" id="sidebar_bg_1" checked>
                    <label for="sidebar_bg_1"></label>
                  </span>
                                    <span>
                    <input type="radio" name="sidebar_bg" value="color_2" id="sidebar_bg_2">
                    <label for="sidebar_bg_2"></label>
                  </span>
                                    <span>
                    <input type="radio" name="sidebar_bg" value="color_3" id="sidebar_bg_3">
                    <label for="sidebar_bg_3"></label>
                  </span>
                                    <span>
                    <input type="radio" name="sidebar_bg" value="color_4" id="sidebar_bg_4">
                    <label for="sidebar_bg_4"></label>
                  </span>
                                    <span>
                    <input type="radio" name="sidebar_bg" value="color_5" id="sidebar_bg_5">
                    <label for="sidebar_bg_5"></label>
                  </span>
                                    <span>
                    <input type="radio" name="sidebar_bg" value="color_6" id="sidebar_bg_6">
                    <label for="sidebar_bg_6"></label>
                  </span>
                                    <span>
                    <input type="radio" name="sidebar_bg" value="color_7" id="sidebar_bg_7">
                    <label for="sidebar_bg_7"></label>
                  </span>
                                    <span>
                    <input type="radio" name="sidebar_bg" value="color_8" id="sidebar_bg_8">
                    <label for="sidebar_bg_8"></label>
                  </span>
                                </li>
                            </ul>
                        </li>
                        <!--切换主题配色-->
                    </ul>

                </div>
            </nav>

        </header>
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-toolbar clearfix">
                                <form class="pull-right search-bar" method="get" action="#!" role="form">
                                    <div class="input-group">
                                        <div class="input-group-btn">
                                            <input type="hidden" name="search_field" id="search-field" value="title">
                                            <button class="btn btn-default dropdown-toggle" id="search-btn"
                                                    data-toggle="dropdown" type="button" aria-haspopup="true"
                                                    aria-expanded="false">
                                                菜单名 <span class="caret"></span>
                                            </button>
                                        </div>
                                        <input type="text" class="form-control" value="${keyword}" name="keyword" placeholder="请输入名称">
                                    </div>
                                </form>
                                <div class="toolbar-btn-action">
                                    <a class="btn btn-primary m-r-5" href="#!"
                                    data-toggle="modal" data-target="#editMenu"
                                       onclick="addMenu()"
                                    ><i class="mdi mdi-plus"></i> 新增</a>
                                    <a class="btn btn-success m-r-5" onclick="enableMenu()" href="#!"><i class="mdi mdi-check"></i> 启用</a>
                                    <a class="btn btn-warning m-r-5" onclick="disableMenu()" href="#!"><i class="mdi mdi-block-helper"></i> 禁用</a>
                                    <a class="btn btn-danger" onclick="delMenu()" href="#!"><i class="mdi mdi-window-close"></i> 删除</a>
                                </div>
                            </div>
                            <div class="card-body">

                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th>
                                                <label class="lyear-checkbox checkbox-primary">
                                                    <input type="checkbox" id="check-all"><span></span>
                                                </label>
                                            </th>
                                            <th>编号</th>
                                            <th>菜单名</th>
                                            <th>icon</th>
                                            <th>href</th>
                                            <th>父级菜单</th>
                                            <th>创建时间</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach var="menu" items="${menus}" varStatus="status">
                                            <tr>
                                                <td>
                                                    <label class="lyear-checkbox checkbox-primary">
                                                        <input type="checkbox" name="ids[]" value="${menu.id}"><span></span>
                                                    </label>
                                                </td>
                                                <td>${status.index+1}</td>
                                                <td>${menu.title}</td>
                                                <td>${menu.icon}</td>
                                                <td>${menu.href}</td>
                                                <td>${menu.pName}</td>
                                                <td><fmf:formatDate value="${menu.create_time}" pattern="yyyy年MM月dd日 HH:mm:ss"></fmf:formatDate> </td>
                                                <td>
                                                    <c:if test="${menu.status==1}">
                                                        <font class="text-success">正常</font>
                                                    </c:if>
                                                    <c:if test="${menu.status!=1}">
                                                        <font class="text-error">无效</font>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <div class="btn-group">
                                                        <a data-toggle="modal" data-target="#editMenu"
                                                           class="btn btn-xs btn-default" href="#!" title="编辑"
                                                           data-toggle="tooltip"
                                                           onclick="editMenu(${menu.id})"
                                                        ><i class="mdi mdi-pencil"></i></a>
                                                        <a class="btn btn-xs btn-default" href="#!" title="删除"
                                                           data-toggle="tooltip"
                                                           onclick="deleteMenu(${menu.id})"
                                                        ><i class="mdi mdi-window-close"></i></a>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>




                                        </tbody>
                                    </table>

                                    <form id="menuForm">
                                    <div class="modal fade" id="editMenu" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalLabel">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close"><span aria-hidden="true">&times;</span>
                                                    </button>
                                                    <h4 class="modal-title" id="exampleModalLabel">修改菜单</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <form>
                                                        <input type="hidden" id="id">
                                                        <div class="form-group">
                                                            <label for="title" class="control-label">菜单名：</label>
                                                            <input type="text" class="form-control" id="title">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="icon" class="control-label">ICON：</label>
                                                            <input type="text" class="form-control" id="icon">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="href" class="control-label">HREF：</label>
                                                            <input type="text" class="form-control" id="href">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="pid" class="control-label">父级菜单：</label>
                                                            <select class="form-control" id="pid"
                                                                    name="example-select" size="1">
                                                                <option value="-1">请选择</option>
                                                                <c:forEach var="pMenu" items="${firstMenu}">
                                                                    1
                                                                    <option value="${pMenu.id}">${pMenu.title}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
             <%--                                           <div class="form-group">
                                                            <div class="col-xs-2 control-label"
                                                                 style="margin-left: 0;padding-left: 0;font-weight: 700;">
                                                                状态
                                                            </div>

                                                            <div class="col-xs-6">
                                                                <label class="lyear-switch switch-solid switch-primary">
                                                                    <input id="status" type="checkbox" checked="">
                                                                    <span></span>
                                                                </label>
                                                            </div>
                                                        </div>--%>
                                                    </form>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="button" class="btn btn-primary" onclick="saveMenu()">保存</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </form>
                                </div>
                                <%--<ul class="pagination">
                                    <li class="disabled"><span>«</span></li>
                                    <li class="active"><span>1</span></li>
                                    <li><a href="#1">2</a></li>
                                    <li><a href="#1">3</a></li>
                                    <li><a href="#1">4</a></li>
                                    <li><a href="#1">5</a></li>
                                    <li><a href="#1">6</a></li>
                                    <li><a href="#1">7</a></li>
                                    <li><a href="#1">8</a></li>
                                    <li class="disabled"><span>...</span></li>
                                    <li><a href="#!">14452</a></li>
                                    <li><a href="#!">14453</a></li>
                                    <li><a href="#!">»</a></li>
                                </ul>--%>

                                <div>
                                    当前第${pageIndex}页:共${pageCount}页 总记录数:${count}记录 <br>
                                    跳转到<input type="number" id="page" onkeyup="page(event)">页
                                    <div style="display: flex">
                                        <button id="home_page" onclick="home(1)">首页</button>
                                        <button id="up" onclick="paging(1)">上一页</button>

                                        <div id="skip_box">
                                        </div>

                                        <button id="down" onclick="paging(2)">下一页</button>
                                        <button id="nohome" onclick="home(2)">尾页</button>
                                    </div>
                                </div>


                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="https://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/js/main.min.js"></script>
<script type="text/javascript">
    //新增修改的标识 1新增 其他修改
    var type
    var able
    $(function(){
        $('.search-bar .dropdown-menu a').click(function() {
            var field = $(this).data('field') || '';
            $('#search-field').val(field);
            $('#search-btn').html($(this).text() + ' <span class="caret"></span>');
        });
    });
    //启用
    function enableMenu(){
        able=1;
        ableMenu(able);
    }
    function disableMenu(){
        able=2;
        ableMenu(able);
    }
    function delMenu(){
        able=2;
        ableMenu(able)
    }
    function ableMenu(able){
        let checkedIds="";
        //
        var ids = document.getElementsByName("ids[]");
        for (item of ids) {
            if (item.checked) {
                checkedIds+=item.value+","
            }
        }
        if (checkedIds.length > 0) {
            //
            $.ajax({
                url:"${ctx}/api/menu/ableMenu",
                type:"post",
                dataType:"json",
                data:{
                    ids:checkedIds,
                    able:able
                },success(res){
                    if (res.code == 200) {
                        location.reload()
                    }else {
                        alert(res.msq)
                    }
                }
            })
        }else {
            alert("请先选择数据")
        }
    }
    //添加
    function addMenu(){
        type=1;
        document.getElementById("menuForm").reset();//重置表单
        document.getElementById("exampleModalLabel").innerText="添加菜单";
    }
    //编辑
    function editMenu(id){
        type=2;
        document.getElementById("menuForm").reset();//重置表单
        document.getElementById("exampleModalLabel").innerText="添加菜单";
        $.ajax({
            url:"${ctx}/api/menu/getMenu",
            tyupe:"get",
            dataType:"json",
            data:{
                id:id
            },success(res){
                if (res.code == 200) {
                    document.getElementById("id").value=res.data.id;
                    document.getElementById("title").value=res.data.title;
                    document.getElementById("icon").value=res.data.icon;
                    document.getElementById("href").value=res.data.href;
                    document.getElementById("pid").value=res.data.pid;
                }else {
                    alert(res.msg)
                    //TODO 关闭模态框
                }
            }
        })
    }
    function saveMenu(){
        if (checkMenu(1)) {
            let id = document.getElementById("id").value;
            let title = document.getElementById("title").value;
            let icon = document.getElementById("icon").value;
            let href = document.getElementById("href").value;
            let pId = document.getElementById("pid").value;
            let url;
            if (type == 1) {
                url="${ctx}/api/menu/addMenu";
            }else {
                url="${ctx}/api/menu/edit";
            }
            $.ajax({
                url:url,
                type:"post",
                dataType: "json",
                data: {
                    id:id,
                    title:title,
                    href:href,
                    icon:icon,
                    pId:pId
                },success(res) {
                    if (res.code == 200) {
                        //成功
                        location.reload()
                    }
                }
            })
        } else {
          //错误提示
        }
    }
    function checkMenu(){
        if(type!=1){
            let id = document.getElementById("id").value;
            if (id == null || id == "") {
                return false;
            }
        }

        let title = document.getElementById("title").value;
        if (title == null || title == "") {
            return false;
        }
        return true;
    }
    function deleteMenu(id){
        if (confirm("确认删除?")) {
            $.ajax({
                url:"${ctx}/api/menu/deleteMenu",
                type:"post",
                dataType:"json",
                data:{
                    id:id
                },success(res){
                    if (res.code == 200) {
                        location.reload();
                    }else {
                        alert(res.msg);
                    }
                }
            })
        }
    }
</script>
</body>
</html>