<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
left: 45%;
width: 20%;
height: 20%;
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>商品价格信息</title>
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
			<h3>${title}商品价格信息</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
        <form id="frm" action="<%=basePath%>goods/info/save_stockPriceList" method="post">
		<!-- 隐藏属性 开始 -->
		<table><tr><td>
		<input style="display:none" type="text" id="title" name="title" value="${title}"/>
		<input style="display:none" type="text" id="goodsId" name="goodsId" value="${goodsId}"/>
		<input style="display:none" type="text" id="toType" name="toType" value="${toType}"/>
		</td></tr></table>
		<!-- 隐藏属性 结束 -->
				 <table>
				<thead>
					<tr>
						<th style="width:7%">序号</th>
						<th style="width:40%">商品组合名称</th>
						<th style="width:20%">套餐组合</th>

						<th style="width:11%">数量(统称/件)</th>
						<th style="width:11%">销售价格</th>
						<th style="width:11%">促销价格</th>
					</tr>
				</thead>
				<tbody class="list">
				<c:if test="${toType==0}">
					<c:forEach items="${StockControlProperty}" var="list" varStatus="loop">
						<tr>
							<td>${loop.count}</td>
							<td>${list.scpName}<input style="display:none" type="text" id="packageTypeName" name="packageTypeName" value="${list.scpName}"/></td>
						 	<td><input class="text-input input" type="text" id="packageType" name="packageType"  value="${list.tempString}"/></td>
						 	<td><input class="text-input input" type="text" id="num" name="num" size="12" value=""/></td>
						 	<td><input class="text-input input" type="text" id="price" name="price" size="12" value=""/></td>
						 	<td><input class="text-input input" type="text" id="salesPrice" name="salesPrice" size="12" value=""/></td>
						</tr>
					</c:forEach>
				</c:if>
				 <!-- 商品价格属性修改 -->
				<c:if test="${toType!=0}">
					<c:forEach items="${priceList}" var="list" varStatus="loop">
						<tr>
							<td>${loop.count}<input style="display:none" type="text" id="spId" name="spId" value="${list.id}"/></td>
							<td>${list.packageTypeName}<input style="display:none" type="text" id="packageTypeName" name="packageTypeName" value="${list.packageTypeName}"/></td>
						 	<td><input class="text-input input" type="text" id="packageType" name="packageType"  value="${list.packageType}"/></td>
						 	<td><input class="text-input input" type="text" id="num" name="num" size="12" value="${list.num}"/></td>
						 	<td><input class="text-input input" type="text" id="price" name="price" size="12" value="${list.price}"/></td>
						 	<td><input class="text-input input" type="text" id="salesPrice" name="salesPrice" size="12" value="${list.salesPrice}"/></td>
						</tr>
					</c:forEach>
				</c:if>
				</tbody>
			</table>
	            <div style="text-align:center">
	            	<input class="button" type="submit" id="frmSubmit" value="提交" />
	            	<span class="input-notification error png_bg hidden"></span>
	            </div>
	           <!--弹出层时背景层DIV-->
				<div id="fade" class="black_overlay">
				</div>
				<div id="MyDiv" class="white_content">
				  <!-- 弹出内容  -->
				  <div><img src="<%=basePath %>resources/images/facebox/loading.gif" /><font color="white">上传中请等待....</font></div>
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
	$("#_goods_info_List").addClass("current");
	$("#goods_sub").show();
});

//提交判断
$("#frmSubmit").click(function() {
      var t=ScollPostion();
      t=(t+280+"px");
      var top=($('.white_content:eq(0)').css('top'));
	   //给top赋值
	   $('.white_content:eq(0)').css('top',(t));
       ShowDiv("MyDiv","fade");
   if($("#toType").val()>0){
	   $("#frm").attr("action","<%=basePath%>goods/info/edit_stockPriceList");
	   $("#frm").submit();
   }
});

//弹出隐藏层
	function ShowDiv(show_div,bg_div){
	document.getElementById(show_div).style.display='block';
	document.getElementById(bg_div).style.display='block' ;
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth;
	$("#"+bg_div).height($(document).height());
	};

//滚动条位置 
 function  ScollPostion() {
var t, l, w, h;
if (document.documentElement && document.documentElement.scrollTop){
	 t = document.documentElement.scrollTop; 
	 l = document.documentElement.scrollLeft;
	 w = document.documentElement.scrollWidth;
	 h = document.documentElement.scrollHeight;
 } else if (document.body) {
	 t = document.body.scrollTop;
	 l = document.body.scrollLeft;
	 w = document.body.scrollWidth;
	 h = document.body.scrollHeight;
 }
 return t;    
}
</script>
</body>
</html>