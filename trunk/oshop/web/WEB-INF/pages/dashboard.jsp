<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>后台管理首页</title>
<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript">
var base_path = "<%=basePath %>";
</script>
</head>
<body>
<div id="body-wrapper">
<jsp:include page="include/menu.jsp" />
<div id="main-content">
	<h2>${welcome } ${sessionScope.login_account.loginName }, 欢迎回来！</h2>
    <p id="page-intro">别觉得你被世界抛弃了，世界根本就没空搭理你</p>
    <ul class="shortcut-buttons-set">
	<li>
	   	<a class="shortcut-button" href="#">
	   	<span>
			<img src="<%=basePath %>resources/images/icons/pencil_48.png" alt="icon" /><br />
	  			商品管理
	  	</span>
  		</a>
	</li>
	<li>
		<a class="shortcut-button" href="#">
		<span>
			<img src="<%=basePath%>resources/images/icons/order.png" alt="icon" /><br />
			订单管理
		</span>
		</a>
	</li>
	<li>
		<a class="shortcut-button" href="#">
		<span>
			<img src="<%=basePath%>resources/images/icons/member.png" alt="icon" /><br />
			会员管理
			</span>
		</a>
	</li>
	<li>
		<a class="shortcut-button" href="#">
		<span>
			<img src="<%=basePath%>resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
			文章管理
		</span>
	</a>
	</li>
	<li>
		<a class="shortcut-button" href="#messages" rel="modal"><span>
		<img src="<%=basePath%>resources/images/icons/comment_48.png" alt="icon" /><br />
		权限管理
		</span>
		</a>
	</li>
	</ul> 
</div>
</div>
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/facebox.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/configuration.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#dashboard").addClass("current");
});
</script>
</body>
</html>
