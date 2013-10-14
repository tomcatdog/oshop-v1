<%@ page language="java" import="java.util.*,java.net.URL"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>oshop后台管理登陆</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-login.css" />
<link rel="stylesheet" href="<%=basePath %>resources/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,800" type="text/css">
</head>
<body id="login">
<div id="loginbox">            
	<form id="loginform" class="form-vertical" action="${action }" method="post">
	<input type="hidden" name="password" id="password" />
 	<div class="control-group normal_text"> <h3><img src="<%=basePath %>resources/img/logo.png" alt="Logo" /></h3></div>
    	<div class="alert alert-danger hide" >
        	<c:choose>
        		<c:when test="${!empty sessionMessage}">
        			<div id="msg">${sessionMessage }</div>
        		</c:when>
        		<c:otherwise>
        			<div id="msg" style="display: none;">${sessionMessage }</div>
        		</c:otherwise>
        	</c:choose>
        </div>
    	<div class="control-group">
        	<div class="controls">
            	<div class="main_input_box">
                	<span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" id="loginName" name="loginName" placeholder="用户名/邮箱/手机" />
                </div>
            </div>
        </div>
        <div class="control-group">
        	<div class="controls">
            	<div class="main_input_box">
                	<span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" id="_password" name="_password" placeholder="密码" />
                </div>
            </div>
        </div>
        <div class="form-actions">
            <span class="pull-left"><a id="login_btn" class="btn btn-success" href="javascript:check();"/> 登陆</a></span>
            <span class="pull-right"><a href="#" class="flip-link btn btn-info" id="to-recover"> 忘记密码?</a></span>
        </div>
        </form>
        <form id="recoverform" action="#" class="form-vertical">
		<p class="normal_text"> 请输入您的安全邮箱： </p>
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                    </div>
                </div>
            <div class="form-actions">
                <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登陆</a></span>
                <span class="pull-right"><a class="btn btn-info"/> 恢复</a></span>
            </div>
        </form>
    </div>
    
    <script src="<%=basePath %>resources/js/jquery.min.js"></script>  
    <script src="<%=basePath %>resources/js/oshop.login.js"></script> 
</body>
</html>
