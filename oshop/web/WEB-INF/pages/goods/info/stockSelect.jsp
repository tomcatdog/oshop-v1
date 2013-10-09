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
<title>选择${title}销售商品图片属性</title>
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
			<h3>选择${title}销售商品图片属性</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
			<form action="<%=basePath%>goods/info/to_stockSelectPic" method="post">
			<!-- 隐藏属性     开始 -->
			<table>
			  <tr><td>
			  <input style="display:none" type="text" id="title" name="title" value="${title}"/>
			  <input style="display:none" type="text" id="goodsId" name="goodsId" value="${goodsId}"/>
			  <input style="display:none" type="text" id="toType" name="toType" value="${toType}"/>
			  <input style="display:none" type="text" id="editType" name="editType" value="1"/>
			  </td></tr>
			</table>
			<!-- 隐藏属性     结束 -->
				<table>
				     <tr>
					    <td>
					    <select id="stockId" name="stockId">
					        <option value="-1">--请选择--</option>
						    <c:forEach items="${stockControl}" var="list" varStatus="loop">
						       <option value="${list.stockId}">${list.salesOfProperty}</option>
						    </c:forEach>
					    </select>
					    </td>
					  </tr> 
				</table>
				<div style="text-align:center">
				<c:if test="${empty isOver }">
				 <input class="button" type="submit" id="frmSubmit"  value="下一步" />
			     <span class="input-notification error png_bg hidden"></span>
			    </c:if>
			    <c:if test="${not empty isOver }">
				 <input class="button" type="submit" id="frmSubmit"  value="继续" />
				 <input class="button" type="button" id="finishSubmit"  value="完成" />
			     <span class="input-notification error png_bg hidden"></span>
			    </c:if>
		       </div> 
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
	$("#_goods_info_List").addClass("current");
	$("#goods_sub").show();
});

//提交判断
$("#frmSubmit").click(function() {
   if($("#stockId").val()!=-1){
	   $("#frm").submit();
   }else{
       alert("请选择需要相应商品属性名称");
       return false;
   }
});

//完成返回商品信息页面
$("#finishSubmit").click(function() {
  location.href="<%=basePath%>goods/info/info_List";
});
</script>
</body>
</html>
