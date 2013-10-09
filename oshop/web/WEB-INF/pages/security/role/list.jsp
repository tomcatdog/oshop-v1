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
<title>角色列表</title>
<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript">
var base_path = "<%=basePath %>";
</script>
</head>
<body>
<div id="body-wrapper">
<jsp:include page="/WEB-INF/pages/include/menu.jsp" />
<div id="main-content">
<div class="content-box">
	<div class="content-box-content">
		<div class="content-box-header">
			<h3>角色列表</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
	    	<form action="<%=basePath %>admin/security/role/list" method="post">
	            <table>
	            	<tr>
	            		<td style="width:10%"><label for="roleName">角色名</label></td>
	            		<td style="width:20%"><input class="text-input input" type="text" id="roleName" name="roleName" value="${role.roleName }"/></td>
	            		<td style="width:10%"><input class="button" type="submit" value="搜索" /></td>
	            		<td style="width:50%"><span class="input-notification error png_bg hidden"></span></td>
	            		<td style="width:10%"><a class="align-right" href="<%=basePath %>admin/security/role/add">添加角色</a></td>
            		</tr>
	            </table>
	        </form>
	    </div>
		<div class="tab-content default-tab">
			<table>
				<thead>
					<tr>
						<th style="width:2%"><input class="check-all" type="checkbox" /></th>
						<th style="width:6%">序号</th>
						<th>角色名</th>
						<th>描述</th>
						<th style="width:15%">操作</th>
					</tr>
				</thead>
				<tbody class="list">
					<c:forEach items="${page.list }" var="l" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="ids" value="${l.id }"/></td>
							<td>${loop.count }</td>
							<td>${l.roleName }</td>
							<td>${l.roleDesc }</td>
						 	<td>
								<a href="<%=basePath %>admin/security/role/edit/${l.id }">
									编辑
								</a>
								<a href="javascript: select_permission(${l.id })">
									修改权限
								</a>
								<a href="javascript: remove('${l.roleName }', ${l.id })">
									删除
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
						<jsp:include page="/WEB-INF/pages/include/pagination.jsp"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/facebox.js"></script>
<!-- configuration.js最后引入 -->
<script type="text/javascript" src="<%=basePath %>resources/scripts/configuration.js"></script>
<script type="text/javascript">
function remove(_name, _id){
	if(window.confirm("确定要删除角色 ： "+_name)) {
		window.location.href = "<%=basePath %>admin/security/role/delete/"+_id;
	}
}
function select_permission(_id) {
	window.open("<%=basePath %>admin/security/role/permission/"+_id, "添加权限", 
		"width=400,height=600,top=400,left=300,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no");
}
$(document).ready(function(){
	$("#sys_mgr").addClass("current");
	$("#role_mgr").addClass("current");
	$("#sys_mgr_sub").show();
});
</script>
</body>
</html>
