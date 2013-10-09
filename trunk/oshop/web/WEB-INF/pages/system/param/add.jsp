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
<title>添加参数</title>
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
			<h3>添加参数</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
	    	<form action="<%=basePath %>admin/system/param/add" method="post">
	            <table>
	            	<tr>
	            		<td><label for="paramName">参数名</label></td>
	            		<td><input class="text-input input" type="text" id="paramName" name="paramName" /></td>
            		</tr>
            		<tr>
	            		<td><label for="group">参数组</label></td>
	            		<td>
	            			<input class="text-input input" type="text" id="group" name="group" />
	            			<span ></span>
            			</td>
	            	</tr>
	            	<tr>
	            		<td><label for="paramValue">参数值</label></td>
	            		<td><input class="text-input input" type="text" id="paramValue" name="paramValue" /></td>
            		</tr>
            		
            		<tr>
	            		<td><label for="paramgroup">是否允许修改</label></td>
	            		<td>
	            			<select name="modifiable" class="input">
	            				<option value=""></option>
			                	<option value="1" selected="selected">是</option>
			                	<option value="0">否</option>
	            			</select>
            			</td>
	            	</tr>
	            	<tr>
	            		<td><label for="paramgroup">是否初始化加载</label></td>
	            		<td>
	            			<select name="init" class="input">
	            				<option value=""></option>
			                	<option value="1" selected="selected">是</option>
			                	<option value="0">否</option>
	            			</select>
            			</td>
	            	</tr>
	            	<tr>
	            		<td><label for="remark">参数说明</label></td>
	            		<td>
	            			<textarea class="text-input input" id="remark" name="remark"></textarea>
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
	$("#param_mgr").addClass("current");
	$("#sys_mgr_sub").show();
});
</script>
</body>
</html>
