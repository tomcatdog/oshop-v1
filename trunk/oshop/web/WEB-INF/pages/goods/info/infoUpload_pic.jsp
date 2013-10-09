<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/include/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commons/comm.jsp" %>
<script type="text/javascript" src="<%=path%>/resources/common/js/ajaxfileupload.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${title}商品图片</title>
<link rel="stylesheet" href="<%=basePath %>resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=basePath %>resources/css/invalid.css" type="text/css" media="screen" />
</head>
<body>
<div id="body-wrapper">
<jsp:include page="/WEB-INF/pages/include/menu.jsp" />
<div id="main-content">
<div class="content-box">
	<div class="content-box-content">
		<div class="content-box-header">
			<h3>${title}商品图片</h3>
			<div class="clear"></div>
		</div>
		<div class="tab-content default-tab">
         <form id="form_upload" enctype="multipart/form-data" method = "post" action = "">  
			<!-- 隐藏属性     开始 -->
			<input style="display:none" type="text" id="title" name="title" value="${title}"/>
			<input style="display:none" type="text" id="goodsId" name="goodsId" value="${goodsId}"/>
			<input style="display:none" type="text" id="toType" name="toType" value="${toType}"/>
			<input style="display:none" type="text" id="rootpath" name="rootpath" value="${rootpath}"/>
			<!-- 隐藏属性     结束 -->
		       <div id="propertyModel" >
			      <table id="addLimit">
			        
			         <c:if test="${toType==0}">
			         <tr>
			          <td>
			          <label>图片上传</label><input type = "file" name = "file" size = "20" id="file" name="file" maxlength = "20" />
			          <label>图片中文名</label><input class="text-input input" type="text" id="imageName" name="imageName" value=""/>
			          <label>是否首页展示</label><select id='homedisplay' name='homedisplay'>
						<option selected='selected' value='0'>否</option>
						<option value='1'>是</option>
					  </select>
			          <input class='button' type='button' onclick='addRow()' value='+' />
			          </td>
			          <!-- 上传图片展示区 -->
				      <td style="width:10%" >
				          <img id="picZS1" width="120px" height="120px" src="<%=basePath %>resources/images/icons/blank_picture.png" />
				       </td>
				      </tr>
				     </c:if>
                   <!-- 修改区 -->
                     <c:if test="${toType==1}">
                     <c:forEach items="${goodsImagePath}" var="list"  varStatus="loop">
                     <input style="display:none" class="text-input input" type="text" id="oldImageId" name="oldImageId" value="${list.imageId}"/>
                     <tr id="${loop.count}">
                     <td>
			              <label>图片上传</label><input type = "file" name = "file" size = "20" id="file"  maxlength = "20" value="" />
				          <input style="display:none" class="text-input input" type="text" id="imageId" name="imageId" value="${list.imageId}"/>
				          <input style="display:none" class="text-input input" type="text" id="imagePath" name="imagePath" value="${list.imagePath}"/>
				          <label>图片中文名</label><input class="text-input input" type="text" id="imageName" name="imageName" value="${list.imageName}"/>
				          <label>是否首页展示</label><select id='homedisplay' name='homedisplay'>
				            <c:choose>
				              <c:when test="${list.homedisplay==0}">
								  <option selected='selected' value='0'>否</option>
								  <option value='1'>是</option>
							  </c:when>
								  <c:when test="${list.homedisplay==1}">
								  <option value='0'>否</option>
								  <option selected='selected' value='1'>是</option>
							  </c:when>
							</c:choose>
						  </select>
						  <input class='button' type='button' onclick='addRow()' value='+' />
						  <c:if test="${loop.count!=1}">&nbsp;<input class='button' type='button' onclick='delRow(this)' value='-' /></c:if>
			          </td>
			          <!-- 上传图片展示区 -->
				      <td style="width:10%" >
				          <img id="picZS${loop.count}" width="120px" height="120px" src="<%=basePath %>${rootpath}${list.imagePath}" />
				      </td>
				      </tr>
                     </c:forEach>
                   </c:if>
			      </table>
		        </div>
			   <div style="text-align:center">
			     <input type="button" class="button" value="上传" id="uploadButton" onclick="upload();"/>
			     <c:if test="${toType==0}">
				 <input class="button" type="button" disabled="disabled" id="frmSubmit"  value="下一步" />
				 </c:if>
			     <span class="input-notification error png_bg hidden"></span>
		       </div> 
		       <!--弹出层时背景层DIV-->
				<div id="fade" class="black_overlay">
				</div>
				<div id="MyDiv" class="white_content">
				  <!-- 弹出内容  -->
				  <div id="addDiv" ><img src="<%=basePath %>resources/images/facebox/loading.gif" /><font color="white">上传中请等待....</font></div>
				</div>
		    </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
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

function upload(){
    ShowDiv("MyDiv","fade");
    var rootpath=$("#rootpath").val();
    var toType=$("#toType").val();
	var params=$("#form_upload").serialize();
	var goodsId=$("#goodsId").val();
	 $.ajaxFileUpload
		(
			{
				url:"<%=path%>/goods/info/upload?goodsid="+goodsId,
				data:params,
				secureuri:false,
				oldFormId:'form_upload',
				dataType: 'json',
				cache:false,
				async:false,
				success: function (data, status)
				{
			  //替换图片
                if(toType==0){
				   for(var i=0;i<data.result.picPaths.length;i++){
				     $("#picZS"+(i+1)).attr("src", "<%=basePath %>"+rootpath+data.result.picPaths[i]);
				   }
				   alert("成功:"+data.result.success+"失败:"+data.result.fail);
				   CloseDiv('MyDiv','fade');
				}else{
				   CloseDiv('MyDiv','fade');
				   location.href="<%=basePath %>goods/info/edit_InfoUploadPic/"+goodsId;
				}
					$("#frmSubmit").removeAttr("disabled");
					$("#uploadButton").hide();
				},error: function (errdata, status, e)
				{
					alert("操作失败！");
				}
			}
		);
}

//弹出隐藏层
	function ShowDiv(show_div,bg_div){
	document.getElementById(show_div).style.display='block';
	document.getElementById(bg_div).style.display='block' ;
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth;
	$("#"+bg_div).height($(document).height());
	};
	
	//关闭弹出层
	function CloseDiv(show_div,bg_div){
	document.getElementById(show_div).style.display='none';
	document.getElementById(bg_div).style.display='none';
	};
	
//增加属性行
function addRow(){
	var count = $("input[name^=file]").length+1;
	var str = "<tr id='"+count+"'><td>"
	+"<label>图片上传</label><input type = 'file' size = '20' id='file' name='file' maxlength = '20' />"
	+"<input style='display:none' class='text-input input' type='text' id='imageId' name='imageId'/>"
	+"&nbsp;<label>图片中文名</label><input class='text-input input' type='text' id='imageName' name='imageName' value=''/>"
	+"<label>是否首页展示</label><select id='homedisplay' name='homedisplay'>"
	+"<option selected='selected' value='0'>否</option>"
	+"<option value='1'>是</option>"
	+"</select>"
	+"&nbsp;<input class='button' type='button' onclick='addRow()' value='+' />&nbsp;<input class='button' type='button' onclick='delRow(this)' value='-' /></td>"
	+"<td style='width:10%'><img id='picZS"+count+"' width='120px' height='120px' src='<%=basePath %>resources/images/icons/blank_picture.png' /></td>"
	+"</tr>";
	$("#addLimit").append(str);
}

//删除属性行
function delRow(obj){
var id = $(obj).parent().parent().attr("id");
$("#"+id).remove();
}

//提交下一步
$("#frmSubmit").click(function() {
	var goodsId=$("#goodsId").val();
	var toType=$("#toType").val();
	location.href="<%=basePath%>goods/info/to_StockControl?goodsId="+goodsId+"&toType="+toType;
});
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
top: 40%;
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
</html>
