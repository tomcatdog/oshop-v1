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
	    	<form action="<%=basePath %>admin/system/param/list" method="post">
	            <table>
	            	<tr>
	            		<td><label for="paramName">参数名</label></td>
	            		<td><input class="text-input input" type="text" id="paramName" name="paramName" value="${param.paramName }"/></td>
	            		<td><label for="group">参数组名</label></td>
	            		<td>
	            			<select name="group" class="input">
	            				<option value="">请选择</option>
	            				<c:if test="${!empty group}">  
						            <c:forEach items="${group}" var="item"> 
					                	<c:choose>
							            	<c:when test="${item == param.group }">
							                	<option value="${item }" selected="selected">${item }</option>
							                </c:when>
							                <c:otherwise>
							                	<option value="${item }">${item }</option>
							                </c:otherwise>
							            </c:choose>
						            </c:forEach>
						        </c:if>  
	            			</select>
	            		</td>
	            		<td><label for="modifiable">是否可修改</label></td>
	            		<td>
	            			<select name="modifiable">
	            				<option value="">请选择</option>
			                	<option value="1" <c:if test="${param.modifiable == '1' }">selected="selected"</c:if>>是</option>
			                	<option value="0" <c:if test="${param.modifiable == '0' }">selected="selected"</c:if>>否</option>
	            			</select>
	            		</td>
	            	</tr>
	            	<tr>
	            		<td><label for="createTime">系统启动是否加载</label></td>
	            		<td>
	            			<select name="init">
	            				<option value="">请选择</option>
			                	<option value="1" <c:if test="${param.init == '1' }">selected="selected"</c:if>>是</option>
			                	<option value="0" <c:if test="${param.init == '0' }">selected="selected"</c:if>>否</option>
	            			</select>
	            		</td>
	            		<td><input class="button" type="submit" value="搜索" /></td>
	            		<td><span class="input-notification error png_bg hidden"></span></td>
	            		<td colspan="2"><a class="align-right" href="<%=basePath %>admin/system/param/add">添加参数</a></td>
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
						<th style="width: 40%">备注</th>
						<th style="width:10%">操作</th>
					</tr>
				</thead>
				<tbody class="list">
					<c:forEach items="${page.list }" var="p" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="ids" value="${p.id }"/></td>
							<td>${loop.count }</td>
							<td>${p.paramName }</td>
							<td>${p.group }</td>
							<td>${p.paramValue }</td>
							<td>${p.remark }</td>
						 	<td>
								<c:if test="${p.modifiable=='1' }">
								<a href="<%=basePath %>admin/system/param/edit/${p.id }">编辑</a>
								<a href="<%=basePath %>admin/system/param/delete/${p.id }">删除</a>
								</c:if>
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
<script type="text/javascript" src="test.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sys_mgr").addClass("current");
	$("#param_mgr").addClass("current");
	$("#sys_mgr_sub").show();
});
</script>
</body>
</html>
