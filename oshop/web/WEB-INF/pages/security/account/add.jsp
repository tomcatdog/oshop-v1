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
<title>添加管理员</title>
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
			<h3>添加管理员</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
	    	<form action="<%=basePath %>admin/security/account/add" method="post">
	            <table>
	            	<tr>
	            		<td><label for="loginName">登录名</label></td>
	            		<td><input class="text-input input" type="text" id="loginName" name="loginName" value="${account.loginName }"/></td>
            		</tr>
            		<tr>
	            		<td><label for="grade">管理级别</label></td>
	            		<td>
	            			<select name="grade" class="input">
	            				<option value=""></option>
	            				<c:if test="${!empty gradeMaps}">  
						            <c:forEach items="${gradeMaps}" var="item"> 
					                	<option value="${item.key }">${item.value }</option>
						            </c:forEach>  
						        </c:if>  
	            			</select>
            			</td>
	            	</tr>
            		<tr>
	            		<td><label for="grade">账号状态</label></td>
	            		<td>
	            			<select name="state" class="input">
	            				<option value=""></option>
	            				<c:if test="${!empty stateMaps}">  
						            <c:forEach items="${stateMaps}" var="item"> 
							            <c:choose>
							            	<c:when test="${item.key == 1 }">
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
	            	<tr>
	            		<td>
	            			<input class="button" type="submit" value="提交" />&nbsp;&nbsp;<a href="javascript:history.back();">返回</a>
	            		</td>
	            		<td colspan="2"><span class="input-notification error png_bg hidden"></span></td>
	            	</tr>
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
