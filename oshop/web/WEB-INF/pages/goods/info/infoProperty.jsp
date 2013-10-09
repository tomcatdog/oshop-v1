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
<title>${title}商品详情内容</title>
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
			<h3>${title}商品详情内容</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
		<form action="<%=basePath%>goods/info/save_GoodsProperty" method="post">
		<!-- 隐藏属性 开始 -->
		<table><tr><td>
		<input style="display:none" type="text" id="title" name="title" value="${title}"/>
		<input style="display:none" type="text" id="goodsId" name="goodsId" value="${goodsId}"/>
		<input style="display:none" type="text" id="toType" name="toType" value="${toType}"/>
		</td></tr></table>
		<!-- 隐藏属性 结束 -->
		    <!-- 添加商品属性 -->
	           <c:if test="${toType==0}">
		        <div id="propertyModel" >
		        <table id="addLimit">
		         <tr>
		          <td>
		          <select id="propertyModelId" name="propertyModelId">
			         <c:forEach items="${propertyModel}" var="list" varStatus="loop">
				       <option value="${list.modelId}">${list.property}</option>
			         </c:forEach>
			      </select>
			      &nbsp;<input class="text-input input" type="text" id="goodsPropertyIdValue" name="goodsPropertyIdValue" value=""/>
		          &nbsp;<input class="button" type="button" onclick="addRow()" value="+" /></td>
		         </tr>
		        </table>
		        </div>
		       </c:if>
		   <!-- 修改商品属性 -->
		   <c:if test="${toType!=0}">
		     <div id="propertyModel" >
		        <table id="addLimit">
		        <c:forEach items="${goodsProperty}" var="gplist" varStatus="gploop">
		         <tr id="${gploop.count}">
		          <td>
		          <select id="propertyModelId" name="propertyModelId">
			         <c:forEach items="${propertyModel}" var="list" varStatus="loop">
			          <c:choose>
				       <c:when test="${gplist.propertyModelId==list.modelId}">
		            	 <option selected="selected" value="${list.modelId}">${list.property}</option>
		               </c:when>
		               <c:when test="${gplist.propertyModelId!=list.modelId}">
		            	 <option value="${list.modelId}">${list.property}</option>
		               </c:when>
				      </c:choose>
			         </c:forEach>
			      </select>
			      &nbsp;<input class="text-input input" type="text" id="goodsPropertyIdValue" name="goodsPropertyIdValue" value="${gplist.goodsPropertyIdValue}"/>
		          &nbsp;<input class="button" type="button" onclick="addRow()" value="+" />
		          <c:if test="${gploop.count>1}"><input class="button" type="button" onclick="delRow(this)" value="-"/></c:if></td>
		         </tr>
		         </c:forEach>
		        </table>
		        </div>
		   </c:if>
		   <div style="text-align:center">
	       <input class="button" type="submit" value="下一步" />
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

//显示模本属性
		 $("#addPropert").click(function() {
		   var ishidden=$("#propertyModel").is(":hidden");//是否隐藏
		   if(ishidden){
		      $("#propertyModel").show();
		      $("#isPropert").val(1);
		   }else{
		      $("#propertyModel").hide();
		      $("#isPropert").val(0);
		   }
		 });
	 
//增加属性行
function addRow(){
	var count = $("input[name^=goodsPropertyIdValue]").length+1;
	var str = "<tr id='"+count+"'><td>"
	+"<select id='propertyModelId' name='propertyModelId'>"
	+"<c:forEach items='${propertyModel}' var='list' varStatus='loop'>"
	+"<option value='${list.goodsTypeId}'>${list.property}</option>"
	+"</c:forEach></select>"
	+"&nbsp;<input class='text-input input' type='text' id='goodsPropertyIdValue' name='goodsPropertyIdValue' value=''/>"
	+"&nbsp;<input class='button' type='button' onclick='addRow()' value='+' />&nbsp;<input class='button' type='button' onclick='delRow(this)' value='-' /></td><tr>";
	$("#addLimit").append(str);
}

//删除属性行
function delRow(obj){
var id = $(obj).parent().parent().attr("id");
$("#"+id).remove();
}

</script>
</body>
</html>