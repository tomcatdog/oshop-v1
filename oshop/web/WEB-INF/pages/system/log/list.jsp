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
<title>参数列表</title>
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
			<h3>参数列表</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
	    	<form action="<%=basePath %>admin/system/log/list" method="post">
	            <table>
	            	<tr>
	            		<td><label for="groupName">参数组名</label></td>
	            		<td><input class="text-input input" type="text" id="groupName" name="groupName" value="${param.groupName }"/></td>
	            		<td><label for="paramName">参数名</label></td>
	            		<td><input class="text-input input" type="text" id="paramName" name="paramName" value="${param.paramName }"/></td>
	            		<td><label for="modifiable">是否可修改</label></td>
	            		<td><input class="text-input input" type="text" id="modifiable" name="modifiable" value="${param.modifiable }"/></td>
	            	</tr>
	            	<tr>
	            		<td><label for="createTime">系统启动是否加载</label></td>
	            		<td>
	            			<input class="text-input input" type="text" 
	            				id="init" name="init" value='<fmt:formatDate value="${param.init }" type="both"/>'/>
	            		</td>
	            		<td><input class="button" type="submit" value="搜索" /></td>
	            		<td><span class="input-notification error png_bg hidden"></span></td>
	            	</tr>
	            </table>
	        </form>
	    </div>
		<div class="tab-content default-tab">
			<table>
				<thead>
					<tr>
						<th><input class="check-all" type="checkbox" /></th>
						<th style="width:6%">序号</th>
						<th>参数名</th>
						<th>参数组</th>
						<th>参数值</th>
						<th>参数类型</th>
						<th style="width:6%">操作</th>
					</tr>
				</thead>
				<tbody class="list">
					<c:forEach items="${page.list }" var="l" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="ids" value="${l.id }"/></td>
							<td>${loop.count }</td>
							<td>${l.userName }</td>
							<td>${l.ip }</td>
							<td><fmt:formatDate value="${l.createTime }" type="both"/></td>
							<td>${l.operation }</td>
						 	<td>
								<%--<a href="#">
									<img src="<%=basePath %>resources/images/icons/pencil.png" alt="编辑" />
								</a>--%>
								<a href="<%=basePath %>admin/system/log/delete/${l.id }">
									<img src="<%=basePath %>resources/images/icons/cross.png" alt="删除" />
								</a>
							</td> 
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
						<%--<div class="bulk-actions align-left">
							<select name="action">
								<option value="">选择操作</option>
								<option value="delete">删除</option>
							</select>
							<input type="submit" class="button" value="提交操作"/>
						</div>
						--%><jsp:include page="/WEB-INF/pages/include/pagination.jsp"/>
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
<script type="text/javascript" src="<%=basePath %>resources/datetime-picker/WdatePicker.js"></script>
<!-- configuration.js最后引入 -->
<script type="text/javascript" src="<%=basePath %>resources/scripts/configuration.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sys_mgr").addClass("current");
	$("#log_mgr").addClass("current");
	$("#sys_mgr_sub").show();
});
</script>
</body>
</html>
