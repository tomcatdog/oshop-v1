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
<title>后台管理首页</title>
<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/jqueryCss.css" type="text/css" media="screen" />
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
			<h3>${title}分类商品信息</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
		<!-- 隐藏属性 开始 -->
		<input style="display:none" type="text" id="title" name="title" value="${title}"/>
		<!-- 隐藏属性 结束 -->
				<form id="frm" action="<%=basePath%>goods/classification/save_GoodsType" method="post">
				 <table>
				  <thead>
				   <tr>
	            		<td><font color="red">* </font><label for="typeName">分类商品名称</label></td>
	            		<td><input class="text-input input" type="text" id="typeName" name="typeName" value="${goodsType.typeName}"/></td>
	               </tr>
	               <tr>
	            	    <td><label for="fatherNode">上级商品</label></td>
	            	    <td>
		            	    <select id="fatherNode" name="fatherNode">
		            	     <c:forEach items="${page.list}" var="op">
		            	     <c:choose>
		            	     <c:when test="${goodsType.fatherNode==op.goodsTypeId and title=='修改'}">
		            	     	<option selected="selected" value="${op.goodsTypeId}">${op.typeName}</option>
		            	     </c:when>
		            	     <c:when test="${goodsType.fatherNode!=op.goodsTypeId and title=='修改'}">
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
	                <tr>
                        <td><label for="nodeGrade">节点等级</label></td>
	            		<td>
		            		<select id="nodeGrade" name="nodeGrade">
			            		<c:forEach items="${grade}" var="list" varStatus="loop">
			            		  <c:choose>
			            		     <c:when test="${goodsType.nodeGrade==list and title=='修改'}">
				            	     	<option selected="selected" value="${list}">${list}</option>
				            	     </c:when>
				            	     <c:when test="${goodsType.nodeGrade!=list and title=='修改'}">
			            	     	    <option value="${list}">${list}</option>
				            	     </c:when>
				            	     <c:when test="${title!='修改'}">
				            	     	<option value="${list}">${list}</option>
				            	     </c:when>
				            	  </c:choose>
			            		</c:forEach>
			            	</select>
		            	</td>
	               </tr>
	               <tr>
	            	 <td><input style="display:none" type="text" id="isPropert" name="isPropert" value="0"/><label><a id="addPropert" href="#">增加商品详情名称(隐藏为取消增加)</a></label></td>
	               </tr>
	             </thead>
	           </table>
	           
	           <!-- 添加模本属性 -->
	           <c:if test="${pmSize==0}">
		        <div id="propertyModel" style="display:none" >
		        <table id="addLimit">
		         <tr>
		          <td><input class="text-input input" type="text" id="property" name="property" value=""/>&nbsp;<input class="button" type="button" onclick="addRow()" value="+" /></td>
		         </tr>
		        </table>
		        </div>
		       </c:if>
		       <!-- 模本属性修改 -->
		       <c:if test="${pmSize!=0}">
		       <div id="propertyModel" >
		        <table id="addLimit">
		          <c:forEach items="${propertyModel}" var="list" varStatus="loop">
	             <tr id="${loop.count}">
		          <td><input class="text-input input" type="text" id="property" name="property" value="${list.property}"/>&nbsp;
		          <input class="button" type="button" onclick="addRow()" value="+" />
		          <c:if test="${loop.count>1}"><input class="button" type="button" onclick="delRow(this)" value="-"/></c:if>
		          </td>
		         </tr>
	               </c:forEach>
	               </table>
		        </div>
		       </c:if>
	            <div style="text-align:center">
	            	<input class="button" type="submit" id="frmSubmit" value="提交" />
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
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery.validate.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/datetime-picker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/scripts/configuration.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#goods").addClass("current");
		$("#_goods_List").addClass("current");
		$("#goods_sub").show();	
		if($("#title").val()=='修改'){
		   $("#isPropert").val(1);
		}
		
		//提交表单验证
		jQuery("#frm").validate({
           rules: {
           "typeName":{required:true,maxlength:20}
           },
           messages: {
           "typeName":{required:"分类商品名称"}
           }
        });
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
	var count = $("input[name^=property]").length+1;
	var str = "<tr id='"+count+"'><td>"
	+"<input class='text-input input' type='text' id='property' name='property' value='' />"
	+"&nbsp;<input class='button' type='button' onclick='addRow()' value='+' />&nbsp;<input class='button' type='button' onclick='delRow(this)' value='-' /></td><tr>";
	$("#addLimit").append(str);
}

//删除属性行
function delRow(obj){
var id = $(obj).parent().parent().attr("id");
$("#"+id).remove();
}

//提交判断
$("#frmSubmit").click(function() {
   if($("#title").val()=="修改"){
	   $("#frm").attr("action","<%=basePath%>goods/classification/update_GoodsType/${goodsType.goodsTypeId}");
	   $("#frm").submit();
   }
});
</script>
</body>
</html>