<%--
  Created by IntelliJ IDEA.
  User: Z1790
  Date: 2023/5/30
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="utils/tools.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
  <title>后台管理系统登录</title>
  <link rel="icon" href="${ctx}/static/bootstrap/images/favicon.ico" type="favicon.ico">
  <meta name="keywords" content="LightYear,光年,后台模板,后台管理系统,光年HTML模板">
  <meta name="description" content="LightYear是一个基于Bootstrap v3.3.7的后台管理系统的HTML模板。">
  <meta name="author" content="yinqi">
  <link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-3.3.4.css">
  <link href="${ctx}/static/bootstrap/css/materialdesignicons.min.css" rel="stylesheet">
  <link href="${ctx}/static/bootstrap/css/style.min.css" rel="stylesheet">
  <style>
    .lyear-wrapper {
      position: relative;
    }
    .lyear-login {
      display: flex !important;
      min-height: 100vh;
      align-items: center !important;
      justify-content: center !important;
    }
    .login-center {
      background: #fff;
      min-width: 38.25rem;
      padding: 2.14286em 3.57143em;
      border-radius: 5px;
      margin: 2.85714em 0;
    }
    .login-header {
      margin-bottom: 1.5rem !important;
    }
    .login-center .has-feedback.feedback-left .form-control {
      padding-left: 38px;
      padding-right: 12px;
    }
    .login-center .has-feedback.feedback-left .form-control-feedback {
      left: 0;
      right: auto;
      width: 38px;
      height: 38px;
      line-height: 38px;
      z-index: 4;
      color: #dcdcdc;
    }
    .login-center .has-feedback.feedback-left.row .form-control-feedback {
      left: 15px;
    }
  </style>
</head>

<body>
<div class="row lyear-wrapper">
  <div class="lyear-login">
    <div class="login-center">
      <div class="login-header text-center">
        <a href="index.html"> <img alt="light year admin" src="${ctx}/static/bootstrap/images/logo-sidebar.png"> </a>
      </div>
      <form action="#!" method="post">
        <div class="form-group has-feedback feedback-left">
          <input type="text" placeholder="请输入您的用户名" class="form-control" name="userName" id="userName" />
          <span class="mdi mdi-account form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="form-group has-feedback feedback-left">
          <input type="password" placeholder="请输入密码" class="form-control" id="password" name="password" />
          <span class="mdi mdi-lock form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="form-group has-feedback feedback-left row">
          <div class="col-xs-7">
            <input type="text" id="code" name="captcha" class="form-control" placeholder="验证码">
            <span class="mdi mdi-check-all form-control-feedback" aria-hidden="true"></span>
          </div>
          <div class="col-xs-5">
            <img src="${ctx}/api/captcha/code" class="pull-right" id="captcha" style="cursor: pointer;"
                 onclick="this.src=this.src+'?d='+Math.random();" title="点击刷新" alt="captcha">
          </div>
        </div>
        <div class="form-group">
          <button class="btn btn-block btn-primary" type="button" onclick="checkCode()">
            立即登录
          </button>
        </div>
      </form>
      <hr>

    </div>
  </div>
</div>
<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="https://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
<script type="text/javascript">


  function checkCode(){
    let code=document.getElementById("code").value;
    if (code==null||code==""){
      alert("请输入验证码")
    }else {
      $.ajax({
        url:"${ctx}/api/captcha/check",
        type:"post",
        dataType:"json",
        data:{
          code:code
        },success(res) {
          if (res.code==200){
            login();
          }else {
            alert(res.msg)
          }
        }
    })
    }

    function login(){
      let userName = document.getElementById("userName").value;
      let password = document.getElementById("password").value;
      if (checkInput()){
        $.ajax({
          url:"${ctx}/api/user/login",
          type:"post",
          dataType:"json",
          data:{
            userName:userName,
            password:password
          },success(res){
            if (res.code==200){
              location.href="${ctx}/api/menu/showMenuTree"
            }else {
              alert(res.msg);
            }
          }
        })
      }
    }
    }

  function checkInput(){
    let userName = document.getElementById("userName").value;
    let password = document.getElementById("password").value;
    if (userName==null||userName==""){
      return false;
    }
    if (password==null||password==""){
      return false;
    }
    return true;
  }

</script>
</body>
</html>
