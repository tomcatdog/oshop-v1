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
<title>${title}销售产品属性文字/图片内容</title>
<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript">
var base_path = "<%=basePath %>";
var flog=true;
$(document).ready(function(){
	$("#goods").addClass("current");
	$("#_goods_info_List").addClass("current");
	$("#goods_sub").show();
});

var divStr;
$(document).ready(function(){
	$("#goods").addClass("current");
	$("#_goods_info_List").addClass("current");
	$("#goods_sub").show();
});

//弹出隐藏层
function ShowDiv(show_div,bg_div,id_value){
document.getElementById(show_div).style.display='block';
document.getElementById(bg_div).style.display='block' ;
var bgdiv = document.getElementById(bg_div);
bgdiv.style.width = document.body.scrollWidth;
$("#"+bg_div).height($(document).height());
if(flog){
		divStr="<c:forEach items='${goodsImagePath}' var='list' varStatus='loop'>"
		+"&nbsp;<a href='#'><img src='<%=basePath %>${rootpath}${list.imagePath}' id='image${loop.count}' "
		+"alt='${list.imagePath}' style='cursor:hand;' onclick='PicClick(${loop.count},"+id_value+")' /></a>"
		+"</c:forEach>";
		$("#addDiv").append(divStr);
		flog=false;
		}
};

//关闭弹出层
function CloseDiv(show_div,bg_div){
document.getElementById(show_div).style.display='none';
document.getElementById(bg_div).style.display='none';
};

//遮罩层图片点击事件
function PicClick(pic_id,id_value){
$("#picPath"+id_value).val($("#image"+pic_id).attr("alt"));
CloseDiv('MyDiv','fade');
$("#addDiv").html('');
};

	 
//增加属性行
function addRow(pid){
	var count = $("input[name^=scpName]").length+1;
	var editType=$("#editType").val();
	if(editType==1){
	  if((count)>0){
	     $("#addLines").hide(); 
	  }else{
	     $("#addLines").show();
	  }
	}
	var str = "<tr id='"+count+"'><td>"
	+"<input style='display:none' type='text' id='scpId' name='scpId' value=''/>"
	+"<label>文字名称:</label><input class='text-input input' type='text' id='scpName' name='scpName' maxlength='20' value=''/>"
    +"<label>图片地址:</label><input class='text-input input' type='text' id='picPath"+count+"' name='picPath' value='请选择' style='color: gray;' "
    +"onfocus='if (value =='请选择'){value =''}' onblur='if (value ==''){value='请选择'}' readonly='readonly' onclick='' />"           
	+"&nbsp;<input class='button' type='button' onclick='addRow()' value='+' />&nbsp;<input class='button' type='button' onclick='delRow(this)' value='-' /></td><tr>";
	$("#addLimit").append(str);
	$("#picPath" + count).attr("onclick", "ShowDiv('MyDiv','fade',"+count+")");
}

//删除属性行
function delRow(obj){
var editType=$("#editType").val();
if(editType==1){
  var count = $("input[name^=scpName]").length;
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
<style>
.black_overlay{
display: none;
position: absolute;
top: 0%;
left: 0%;
width: 100%;
height: 100%;
background-color: black;
z-index:1001;
-moz-opacity: 0.8;
opacity:.80;
filter: alpha(opacity=80);
}
.white_content {
display: none;
position: absolute;
top: 10%;
left: 10%;
width: 80%;
height: 80%;
border: 16px solid white;
background-color: white;
z-index:1002;
overflow: auto;
}
.white_content_small {
display: none;
position: absolute;
top: 20%;
left: 30%;
width: 40%;
height: 50%;
border: 16px solid lightblue;
background-color: white;
z-index:1002;
overflow: auto;
}
</style>
</head>
<body>
<div id="body-wrapper">
<jsp:include page="/WEB-INF/pages/include/menu.jsp" />
<div id="main-content">
<div class="content-box">
	<div class="content-box-content">
		<div class="content-box-header">
			<h3>${title}销售产品属性文字/图片内容</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
			<form action="<%=basePath%>goods/info/save_stockControlProperty" method="post">
			<!-- 隐藏属性     开始 -->
			<table>
			  <tr><td>
			  <input style="display:none" type="text" id="title" name="title" value="${title}"/>
			  <input style="display:none" type="text" id="goodsId" name="goodsId" value="${goodsId}"/>
			  <input style="display:none" type="text" id="toType" name="toType" value="${toType}"/>
			  <input style="display:none" type="text" id="stockId" name="stockId" value="${stockId}"/>
			  <input style="display:none" type="text" id="editType" name="editType" value="${editType}"/>
			  </td></tr>
			</table>
			<!-- 隐藏属性     结束 -->
               <table id="addLimit">
               <c:if test="${toType<1}">
               <tr>
                 <td>
                 <!-- 添加销售商品文字/图片内容  -->
                   <label>文字名称:</label><input class="text-input input" type="text" id="scpName" name="scpName" maxlength="20" value=""/>
                   <label>图片地址:</label><input class="text-input input" type="text" id="picPath1" name="picPath" value="请选择" style="color: gray;" 
                   onfocus="if (value =='请选择'){value =''}" onblur="if (value ==''){value='请选择'}" readonly="readonly" onclick="ShowDiv('MyDiv','fade',1)" />
                   <input class="button" type="button" onclick="addRow(1)" value="+" />
                 </td>
               </tr>
               </c:if>
                  <!-- 修改销售商品文字/图片内容  -->
                  <c:if test="${toType>0}">
                  <input id="addLines" style="display:none" class="button" type="button" onclick="addRow()" value="+" />
                   <c:forEach items="${stockControlProperty}" var="list" varStatus="loop">
                   <input style="display:none" type="text" id="oldScpId" name="oldScpId" value="${list.scpId}"/>
                    <tr id="${loop.count}">
                     <td>
                       <input style="display:none" type="text" id="scpId" name="scpId" value="${list.scpId}"/>
	                   <label>文字名称:</label><input class="text-input input" type="text" id="scpName" name="scpName" maxlength="20" value="${list.scpName}"/>
	                   <label>图片地址:</label><input class="text-input input" type="text" id="picPath1" name="picPath" value="${list.picPath}" style="color: gray;"
	                    readonly="readonly" onclick="ShowDiv('MyDiv','fade',${loop.count})" />
	                   <input class="button" type="button" onclick="addRow(1)" value="+" />&nbsp;<input class="button" type="button" onclick="delRow(this)" value="-" />
	                 </td>
                    </tr>
                   </c:forEach>
                 </c:if>
				</table>
				<div style="text-align:center">
				 <input class="button" type="submit" id="frmSubmit"  value="下一步" />
			     <span class="input-notification error png_bg hidden"></span>
		       </div>
		       
		       <!--弹出层时背景层DIV-->
				<div id="fade" class="black_overlay">
				</div>
				<div id="MyDiv" class="white_content">
				  <div style="text-align: right; cursor: default; height: 40px;">
					<span style="font-size: 16px;" onclick="CloseDiv('MyDiv','fade')"><img src="<%=basePath %>resources/images/icons/cross.png" /></span>
					</div>
				  <!-- 弹出内容  -->
				  <div id="addDiv" />
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
</body>
</html>
