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
<title>销售商品展示</title>
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
			<h3>${title}销售商品展示</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
				<form action="<%=basePath%>goods/info/save_stockControl" method="post">
				<!-- 隐藏属性 开始 -->
				<table>
				  <thead>
				   <tr><td>
				   <input style="display:none" type="text" id="title" name="title" value="${title}"/>
				   <input style="display:none" type="text" id="goodsId" name="goodsId" value="${goodsId}"/>
				   <input style="display:none" type="text" id="toType" name="toType" value="${toType}"/>
				   <input style="display:none" type="text" id="editType" name="editType" value="${editType}"/>
				    </td></tr>
				   </thead>
				 </table>
				<!-- 隐藏属性 结束 -->
	           <!-- 添加商品属性  -->
	           <c:if test="${toType==0}">
		        <div id="propertyModel" >
		        <table id="addLimit">
		         <tr>
		          <td><label>商品属性名称:</label><input class="text-input input" type="text" id="salesOfProperty" name="salesOfProperty" value=""/>&nbsp;
		          <label>展示方式:</label>
		          <select id="controlPropertyType" name="controlPropertyType">
		            <option selected="selected" value="0">文字</option>
		            <option value="1">图片</option>
		          </select>
		          <input class="button" type="button" onclick="addRow()" value="+" /></td>
		         </tr>
		        </table>
		        </div>
		       </c:if> 
		        <!-- 商品属性修改 -->
		       <c:if test="${toType!=0}">
		       <input id="addLines" style="display:none" class="button" type="button" onclick="addRow()" value="+" />
		       <div id="propertyModel" >
		        <table id="addLimit">
		          <c:forEach items="${stockControl}" var="list" varStatus="loop">
		          <input style="display:none" type="text" id="oldStockId" name="oldStockId" value="${list.stockId}"/>
		             <tr id="${loop.count}">
			          <td><input style="display:none" type="text" id="stockId" name="stockId" value="${list.stockId}"/>
			          <label>商品属性名称:</label><input class="text-input input" type="text" id="salesOfProperty" name="salesOfProperty" value="${list.salesOfProperty}"/>&nbsp;
			          <label>展示方式:</label>
			          <select id="controlPropertyType" name="controlPropertyType">
			          <c:choose>
		            	<c:when test="${list.controlPropertyType==0}">
			              <option selected="selected" value="0">文字</option>
			              <option value="1">图片</option>
			            </c:when>
			            <c:when test="${list.controlPropertyType>0}">
			              <option value="0">文字</option>
			              <option selected="selected" value="1">图片</option>
			            </c:when>
			          </c:choose>  
			          </select>
			          <input class="button" type="button" onclick="addRow()" value="+" />
			           <c:if test="${loop.count>1}"><input class="button" type="button" onclick="delRow(this)" value="-"/></c:if>
			          </td>
			         </tr>
	               </c:forEach>
	               </table>
		        </div>
		       </c:if>
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

	 
//增加属性行
function addRow(){
	var count = $("input[name^=salesOfProperty]").length+1;
	var editType=$("#editType").val();
	if(editType==1){
	  if((count)>0){
	     $("#addLines").hide(); 
	  }else{
	     $("#addLines").show();
	  }
	}
	var str = "<tr id='"+count+"'><td>"
	+"<input style='display:none' type='text' id='stockId' name='stockId' value=''/>"
	+"<label>商品属性名称:</label><input class='text-input input' type='text' id='salesOfProperty' name='salesOfProperty' value=''/>&nbsp;"
	+"<label>展示方式:</label>"
	+"<select id='controlPropertyType' name='controlPropertyType'>"
	+"<option selected='selected' value='0'>文字</option>"
	+"<option value='1'>图片</option></select>"
	+"&nbsp;<input class='button' type='button' onclick='addRow()' value='+' />&nbsp;<input class='button' type='button' onclick='delRow(this)' value='-' /></td><tr>";
	$("#addLimit").append(str);
}

//删除属性行
function delRow(obj){
var editType=$("#editType").val();
if(editType==1){
  var count = $("input[name^=salesOfProperty]").length;
  if(count<2){
	 $("#addLines").show();
  }else{
     $("#addLines").hide();
  }
}
var id = $(obj).parent().parent().attr("id");
$("#"+id).remove();
}

</script>
</body>
</html>
