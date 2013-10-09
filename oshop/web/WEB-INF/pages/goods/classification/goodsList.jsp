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
<title>分类商品信息管理</title>
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
			<h3>分类商品信息管理列表</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
	    	<form action="<%=basePath%>goods/classification/goods_List" method="post">
	            <table>
	                <tr>
	            		<td><input class="button" type="button" id="addGoods" value="新增" /></td>
	            		<td><span class="input-notification error png_bg hidden"></span></td>
	            	</tr>
	            	<tr>
	            		<td><label for="userName">分类名称</label></td>
	            		<td><input class="text-input input" type="text" id="typeName" name="typeName" value="${goodstype.typeName}"/></td>
	            	</tr>
	            	<tr>
	            		<td><input class="button" type="submit" value="搜索" /></td>
	            		<td><span class="input-notification error png_bg hidden"></span></td>
	            	</tr>
	            </table>
	        </form>
	    </div>
        <div class="tab-content default-tab">
			<form action="<%=basePath%>goods/classification/delete" method="post">
			 <table>
				<thead>
					<tr>
						<th><input class="check-all" type="checkbox" /></th>
						<th style="width:6%">序号</th>
						<th>分类名称</th>
						<th style="width:6%">操作</th>
					</tr>
				</thead>
				<tbody class="list">
					<c:forEach items="${page.list}" var="list" varStatus="loop">
						<tr>
							<td><input type="checkbox" name="ids" value="${list.goodsTypeId}"/></td>
							<td>${loop.count }</td>
							<td>${list.typeName }</td>
							<!-- <td><fmt:formatDate value="${l.createTime }" type="both"/></td> -->
						 	<td>
								<a href="<%=basePath %>goods/classification/edit/${list.goodsTypeId}">
									<img src="<%=basePath %>resources/images/icons/pencil.png" alt="编辑" />
								</a>
								<a href="<%=basePath %>goods/classification/delete/${list.goodsTypeId}">
									<img src="<%=basePath %>resources/images/icons/cross.png" alt="删除" />
								</a>
							</td> 
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="7">
						<div class="bulk-actions align-left">
							<select name="action" id="action">
								<option value="">选择操作</option>
								<%--<option value="edit">编辑</option>--%>
								<option value="delete">删除</option>
							</select>
							<input type="submit" id="selectSubmit" disabled="disabled" class="button" value="提交操作"/>
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
<script type="text/javascript" src="<%=basePath %>resources/scripts/configuration.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#goods").addClass("current");
	$("#_goods_List").addClass("current");
	$("#goods_sub").show();
	
	//新增
	 $("#addGoods").click(function() {
	   location.href = "<%=basePath%>goods/classification/add_Goods";
	 });
	 
	 //下拉框选择事件
	 $("#action").change(function(){
	 var selectValue=$("#action").val();
	 if(selectValue!=""){
	   $("#selectSubmit").removeAttr("disabled");
	   }else{
	   $("#selectSubmit").attr({"disabled":"disabled"});
	   }
	 });
	 
});
</script>
</body>
</html>
