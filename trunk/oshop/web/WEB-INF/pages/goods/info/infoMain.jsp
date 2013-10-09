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
<title>商品信息</title>
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
			<h3>${title}商品信息</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
				<form action="<%=basePath%>goods/info/save_GoodsInfo" method="post">
				<!-- 隐藏属性 开始 -->
				<table>
				  <thead>
				   <tr><td>
				   <input style="display:none" type="text" id="title" name="title" value="${title}"/>
				   <input style="display:none" type="text" id="goodsId" name="goodsId" value="${goodsInfo.goodsId}"/>
				   <input style="display:none" type="text" id="toType" name="toType" value="${toType}"/>
				    </td></tr>
				   </thead>
				 </table>
				<!-- 隐藏属性 结束 -->
				<table>
				  <thead>
				   <tr>
	            		<td><label for="goodsName">商品名称</label></td>
	            		<td><input class="text-input input" type="text" id="goodsName" name="goodsName" value="${goodsInfo.goodsName}"/></td>
	               </tr>
	               <tr>
	            	    <td><label for="saleType">是否促销</label></td>
	            	    <td> 
	            	       <select id="saleType" name="saleType">
		            	       <c:choose>
		            	        <c:when test="${goodsInfo.saleType==0 and title=='修改'}">
		            	     	  <option selected="selected" value="0">否</option>
		            	     	  <option value="1">是</option>
		            	        </c:when>
		            	        <c:when test="${goodsInfo.goodsTypeId!=0 and title=='修改'}">
		            	     	  <option value="0">否</option>
		            	     	  <option selected="selected" value="1">是</option>
		            	     	</c:when>
		            	        <c:when test="${title!='修改'}">
		            	     	  <option selected="selected" value="0">否</option>
	            	              <option value="1">是</option>
		            	        </c:when>
		            	      </c:choose>   
	            	       </select>
	            	    </td>
	               </tr>
	               <tr>
	            	    <td><label for="goodsTypeId">商品类型</label></td>
	            	    <td>
		            	    <select id="goodsTypeId" name="goodsTypeId">
		            	    <option value="0">--请选择--</option>
		            	     <c:forEach items="${page.list}" var="op">
		            	       <c:choose>
		            	        <c:when test="${goodsInfo.goodsTypeId==op.goodsTypeId and title=='修改'}">
		            	     	  <option selected="selected" value="${op.goodsTypeId}">${op.typeName}</option>
		            	        </c:when>
		            	        <c:when test="${goodsInfo.goodsTypeId!=op.goodsTypeId and title=='修改'}">
		            	     	  <option value="${op.goodsTypeId}">${op.typeName}</option>
		            	     	</c:when>
		            	        <c:when test="${title!='修改'}">
		            	     	  <option value="${op.goodsTypeId}">${op.typeName}</option>
		            	        </c:when>
		            	      </c:choose>
		            	     </c:forEach>
		            	    </select>
	            	    </td>
	            	</tr>
	             </thead>
	          </table>
	            <div style="text-align:center">
	            	<input class="button" type="submit" id="frmSubmit" value="下一步" />
	            	<span class="input-notification error png_bg hidden"></span>
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
if($("#goodsTypeId").val()==0){
	if (!(confirm("商品类型未选择，无法进行下一步，只保存该商品信息，确定吗？")))
	 return false;	
}
});
</script>
</body>
</html>
