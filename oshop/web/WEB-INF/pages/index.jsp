<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>后台管理首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/fullcalendar.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-style.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-media.css" />
<link rel="stylesheet" href="<%=basePath %>resources/font-awesome/css/font-awesome.css"/>
<link rel="stylesheet" href="<%=basePath %>resources/css/jquery.gritter.css" />
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,800" type="text/css" />
</head>
<body>
<jsp:include page="include/common.jsp" />

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="<%=basePath %>admin/security/index" title="首页" class="tip-bottom"><i class="icon-home"></i> 首页</a></div>
  </div>

  <div class="container-fluid">
    <div class="quick-actions_homepage">
      <ul class="quick-actions">
        <li class="bg_lb"> <a href="#"> <i class="icon-home"></i> 首页 </a> </li>
        <li class="bg_lg span3"> <a href="#"> <i class="icon-th-list"></i> 商品管理</a> </li>
        <li class="bg_ly span2"> <a href="#"> <i class="icon-reorder"></i> 订单管理 </a> </li>
        <li class="bg_lo span3"> <a href="#"> <i class="icon-user"></i> 会员管理</a> </li>
        <li class="bg_ls span1"> <a href="#"> <i class="icon-cloud"></i> 内容管理</a> </li>
        <li class="bg_lo span3"> <a href="#"> <i class="icon-signal"></i> 统计报表</a> </li>
        <li class="bg_ls span3"> <a href="#"> <i class="icon-desktop"></i> 系统管理</a> </li>
      </ul>
    </div>
  </div>
</div>
<jsp:include page="include/footer.jsp" />

<script src="<%=basePath %>resources/js/jquery.min.js"></script> 
<script src="<%=basePath %>resources/js/jquery.ui.custom.js"></script> 
<script src="<%=basePath %>resources/js/bootstrap.min.js"></script> 
<script src="<%=basePath %>resources/js/oshop.js"></script> 
</body>
</html>
