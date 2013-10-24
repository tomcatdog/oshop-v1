<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>参数列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/uniform.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/select2.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-style.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/oshop-media.css" />
<link rel="stylesheet" href="<%=basePath %>resources/css/pagination.css" />
<link rel="stylesheet" href="<%=basePath %>thirdparty/datepicker/css/datetimepicker.min.css" />
<link rel="stylesheet" href="<%=basePath %>resources/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,800" />
</head>
<body>
<jsp:include page="/WEB-INF/pages/include/common.jsp" />
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="javascript:void();"><i class="icon-desktop"></i> 系统管理</a>
			<a href="<%=basePath %>admin/system/param/list" class="current">参数列表</a>
		</div>
  	</div>
  	<div class="widget-box">
    	<div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
        	<h5>参数列表</h5>
        </div>
        <div class="widget-content nopadding">
		    <form action="<%=basePath %>admin/system/param/list" method="post" id="search_form">
		    	<table class="table table-striped with-check no-border list-search">
		    		<tr>
		    			<td><label class="control-label input-medium" for="paramName">参数名</label></td>
		    			<td>
		    				<input type="text" id="paramName" name="paramName" class="input-medium" placeholder="参数名" value="${param.paramName }"/>
		    			</td>
		    			<td><label class="control-label" for="group">参数组名</label></td>
				    	<td>
				    		<select id="group" name="group" class="input-medium">
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
				    	<td><a href="javascript: search();" id="search_btn" class="btn btn-success" ><i class="icon-white icon-search"></i>搜索</a></td>
		    		</tr>
		    		<tr>
		    			<td><label class="control-label" for="modifiable">是否可修改</label></td>
		    			<td>
                			<select name="modifiable" class="input-medium">
	            				<option value="">请选择</option>
			                	<option value="1" <c:if test="${param.modifiable == '1' }">selected="selected"</c:if>>是</option>
			                	<option value="0" <c:if test="${param.modifiable == '0' }">selected="selected"</c:if>>否</option>
	            			</select>
		    			</td>
		    			<td><label class="control-label" for="init">系统启动是否加载</label></td>
				    	<td>
                			<select name="init" class="input-medium">
	            				<option value="">请选择</option>
			                	<option value="1" <c:if test="${param.init == '1' }">selected="selected"</c:if>>是</option>
			                	<option value="0" <c:if test="${param.init == '0' }">selected="selected"</c:if>>否</option>
	            			</select>
						</td>
						<td>
							<td><a href="<%=basePath %>admin/system/param/add" class="btn btn-mini"><i class="icon-white icon-plus"></i> 添加参数</a></td>
						</td>
		    		</tr>
		    	</table>
		    </form>
        </div>
        <div class="widget-content">
            <table class="table table-bordered table-striped with-check">
            	<thead>
                	<tr>
                  		<th><input type="checkbox" id="title-table-checkbox" name="title-table-checkbox" /></th>
	                	<th>序号</th>
	                  	<th>参数名</th>
	                  	<th>参数组</th>
	                  	<th>参数值</th>
	                  	<th>自动加载</th>
	                  	<th>备注</th>
	                  	<th>操作</th>
	                </tr>
              </thead>
              <tbody>
              <c:forEach items="${page.list }" var="p" varStatus="loop">
              	<tr>
                	<td><input type="checkbox" name="ids" value="${p.id }"/></td>
					<td>${loop.count }</td>
					<td>${p.paramName }</td>
					<td>${p.group }</td>
					<td>${p.paramValue }</td>
					<td>
						<c:choose>
							<c:when test="${p.init=='1' }">是</c:when>
							<c:otherwise>否</c:otherwise>
						</c:choose>
					</td>
					<td>${p.remark }</td>
				 	<td>
						<c:if test="${p.modifiable=='1' }">
						<a href="<%=basePath %>admin/system/param/edit/${p.id }"><i class="icon-edit"></i> 编辑</a>
						<a href="<%=basePath %>admin/system/param/delete/${p.id }"><i class="icon-remove"></i> 删除</a>
						</c:if>
					</td> 
                </tr>
                </c:forEach>
              </tbody>
            </table>
            <jsp:include page="/WEB-INF/pages/include/pagination.jsp"/>
        </div>
    </div>
</div>
<script src="<%=basePath %>resources/js/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=basePath %>resources/js/bootstrap.min.js"></script> 
<script src="<%=basePath %>resources/js/jquery.uniform.js"></script> 
<script src="<%=basePath %>resources/js/select2.min.js"></script> 
<script src="<%=basePath %>resources/js/oshop.js"></script>
<script src="<%=basePath %>resources/js/oshop.tables.js"></script>
<script src="<%=basePath %>thirdparty/datepicker/js/datetimepicker.min.js"></script>
<script src="<%=basePath %>thirdparty/datepicker/js/locales/bootstrap-datetimepicker.zh-CN-NUM.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	$(".active").removeClass("active");
	$("#menu_system_param").addClass("active");
	$("#menu_system").addClass("open");
	
	$("#search_btn").click(function(){
		$("#search_form").submit();
	});
});
</script>
</body>
</html>
