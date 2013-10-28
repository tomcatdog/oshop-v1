<%@ page language="java" import="java.util.*,java.net.URL"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加参数</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap-switch.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/uniform.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-style.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-media.css" />
<link rel="stylesheet" href="<%=basePath %>resources/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,800" />
</head>
<body>
<jsp:include page="/WEB-INF/pages/include/common.jsp" />
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="javascript:void();"><i class="icon-desktop"></i> 系统管理</a> <a
				href="<%=basePath %>admin/system/param/list" class="current">添加参数</a>
		</div>
	</div>
	<div class="widget-box">
		<div class="widget-title">
			<span class="icon"> <i class="icon-th"></i> </span>
			<h5>添加参数</h5>
		</div>
		<div class="widget-content nopadding">
			<form action="<%=basePath %>admin/system/param/add" method="get" class="form-horizontal">
				<div class="control-group">
					<label class="control-label">参数名</label>
					<div class="controls">
						<input type="text" class="span3" id="paramName" name="paramName"
							placeholder="参数名" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">参数组</label>
					<div class="controls">
						<input type="text" class="span3" id="group" name="group"
							placeholder="参数组" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">参数值</label>
					<div class="controls">
						<input type="text" class="span3" id="paramValue" name="paramValue" placeholder="参数值" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否允许修改</label>
					<div class="controls">
					<div class="make-switch" data-on="primary" data-off="info"
						data-on-label="是" data-off-label="否" id="_modifiable">
						<input type="checkbox" <c:if test="${p.modifiable=='1' or p==null}">checked="checked"</c:if> />
						<input type="hidden" id="modifiable" name="modifiable" value="${p.modifiable }"/>
					</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">是否初始化加载</label>
					<div class="controls">
					<div class="make-switch" data-on="primary" data-off="info" 
						data-on-label="是" data-off-label="否" id="_init">
						<input type="checkbox" <c:if test="${p.init=='1' or p==null}">checked="checked"</c:if> />
						<input type="hidden" id="init" name="init" value="${p.init }"/>
					</div>
					</div>
				</div>
				<div class="form-actions">
	            	<button type="submit" class="btn btn-success">Save</button>
	            </div>
			</form>
		</div>
	</div>
</div>
<script src="<%=basePath %>resources/js/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=basePath %>resources/js/bootstrap.min.js"></script>
<script src="<%=basePath %>resources/js/bootstrap-switch.min.js"></script>
<script src="<%=basePath %>resources/js/jquery.uniform.js"></script>
<script src="<%=basePath %>resources/js/oshop.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".active").removeClass("active");
	$("#menu_system_param").addClass("active");
	$("#menu_system").addClass("open");
	
	$("#_modifiable").on('switch-change', function (e, data) {
		if(data.value || data.value=="true"){
		alert(data.value);
			$("#modifiable").value("1");
		} else {
			$("#modifiable").value("0");
		}
	});
	$("#_init").on('switch-change', function (e, data) {
		if(data.value || data.value=="true"){
			$("#init").value("1");
		} else {
			$("#init").value("0");
		}
	});
});
</script>
</body>
</html>