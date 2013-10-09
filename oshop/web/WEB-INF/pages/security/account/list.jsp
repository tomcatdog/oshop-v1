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
<title>管理员列表</title>
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
			<h3>管理员列表</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
	    	<form action="<%=basePath %>admin/security/account/list" method="post">
	            <table>
	            	<tr>
	            		<td><label for="loginName">登录名</label></td>
	            		<td><input class="text-input input" type="text" id="loginName" name="loginName" value="${account.loginName }"/></td>
	            		<td><label for="state">账号状态</label></td>
	            		<td>
	            			<select name="state">
	            				<option value=""></option>
	            				<c:if test="${!empty stateMaps}">  
						            <c:forEach items="${stateMaps}" var="item"> 
							            <c:choose>
							            	<c:when test="${item.key == account.state }">
							                	<option value="${item.key }" selected="selected">${item.value }</option>
							                </c:when>
							                <c:otherwise>
							                	<option value="${item.key }">${item.value }</option>
							                </c:otherwise>
							            </c:choose> 
						            </c:forEach>  
						        </c:if>  
	            			</select>
	            		</td>
	            	</tr>
	            	<%--超级管理员可见
	            	 <tr>
	            		<td>管理员用户所属商户</td>
	            		<td>管理员级别</td>
	            	</tr> 
	            	--%>
	            	<tr>
	            		<td><input class="button" type="submit" value="搜索" /></td>
	            		<td colspan="2"><span class="input-notification error png_bg hidden"></span></td>
	            		<td><a class="align-right" href="<%=basePath %>admin/security/account/add">添加管理员</a></td>
	            	</tr>
	            </table>
	        </form>
	    </div>
		<div class="tab-content default-tab">
			<form action="<%=basePath %>admin/security/account/oper" method="post">
			<table>
				<thead>
					<tr>
						<th><input class="check-all" type="checkbox" /></th>
						<th style="width:6%">序号</th>
						<th>登录名</th>
						<%-- 超级管理员可见 <th>所属商户</th>
						<th>管理级别</th>--%>
						<th>账号状态</th>
						<th style="width:15%">操作</th>
					</tr>
				</thead>
				<tbody class="list">
					<c:forEach items="${page.list }" var="l" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="ids" value="${l.id }"/></td>
							<td>${loop.count }</td>
							<td>${l.loginName }</td>
							<%--
							<td>所属商户</td>
							<td>管理级别</td>
							 --%>
							<td>${l.stateName }</td>
						 	<td>
								<a href="<%=basePath %>admin/security/account/edit/${l.id }">
									<img src="<%=basePath %>resources/images/icons/pencil.png" alt="编辑"/>
								</a>
								<a href="<%=basePath %>admin/security/account/reset/${l.id }">
									<img src="<%=basePath %>resources/images/icons/reset.png" alt="重置密码" />
								</a>
								<a href="<%=basePath %>admin/security/account/delete/${l.id }">
									<img src="<%=basePath %>resources/images/icons/cross.png" alt="删除" />
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5">
						<div class="bulk-actions align-left">
							<select name="action">
								<option value="">选择操作</option>
								<option value="reset">密码重置</option>
								<option value="delete">删除</option>
							</select>
							<input type="submit" class="button" value="提交操作"/>
						</div>
						<jsp:include page="/WEB-INF/pages/include/pagination.jsp"/>
						</td>
					</tr>
				</tfoot>
			</table>
			</form>
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
	$("#admin_mgr").addClass("current");
	$("#sys_mgr_sub").show();
});
</script>
</body>
</html>
