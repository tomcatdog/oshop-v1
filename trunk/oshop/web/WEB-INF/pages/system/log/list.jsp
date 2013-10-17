<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>日志列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/uniform.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/select2.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-style.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-media.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/pagination.css" />
<link rel="stylesheet" href="<%=basePath %>resources/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,800" />
</head>
<body>
<jsp:include page="/WEB-INF/pages/include/common.jsp" />
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="javascript:void();"><i class="icon-desktop"></i> 系统管理</a>
			<a href="<%=basePath %>admin/system/log/list" class="current">日志列表</a>
		</div>
  	</div>
  	<div class="widget-box">
    	<div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
        	<h5>系统日志</h5>
        </div>
        <div class="widget-content">
            <table class="table table-bordered table-striped with-check">
            	<thead>
                	<tr>
                  		<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th>
	                	<th>序号</th>
	                  	<th>用户名</th>
	                  	<th>登陆IP</th>
	                  	<th>操作详情</th>
	                  	<th>记录时间</th>
	                  	<th>操作</th>
	                </tr>
              </thead>
              <tbody>
              <c:forEach items="${page.list }" var="sysLog" varStatus="loop">
              	<tr>
                	<td><input type="checkbox" name="ids" value="${sysLog.id }"/></td>
                	<td>${loop.count }</td>
					<td>${sysLog.userName }</td>
					<td>${sysLog.ip }</td>
					<td>${sysLog.operation }</td>
					<td><fmt:formatDate value="${sysLog.createTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align: center;">
						<a href="<%=basePath %>admin/system/log/delete/${sysLog.id }"><i class="icon-remove"></i> 删除</a>
					</td> 
                </tr>
                </c:forEach>
              </tbody>
            </table>
            <jsp:include page="/WEB-INF/pages/include/pagination.jsp"/>
        </div>
    </div>
<script src="<%=basePath %>resources/js/jquery.min.js"></script> 
<script src="<%=basePath %>resources/js/bootstrap.min.js"></script> 
<script src="<%=basePath %>resources/js/jquery.uniform.js"></script> 
<script src="<%=basePath %>resources/js/select2.min.js"></script> 
<script src="<%=basePath %>resources/js/oshop.js"></script>
<script src="<%=basePath %>resources/js/oshop.tables.js"></script>
</body>
</html>
