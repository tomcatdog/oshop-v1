<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commons/comm.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<input type="button" value="点击我" onclick="upload('mydiv2')">
<table border="0">
<tr><td>下拉列表1：</td><td><div id="mydiv1"></div></td></tr>
<tr><td>下拉列表2：</td><td><div id="mydiv2"></div></td></tr>
</table>
<textarea id="mytextarea" style="display: none;">
<select id="acceptdepartid" name="acceptdepartidnew">
{#foreach $T as Item}
<option value="{$T.Item.value}">{$T.Item.name}</option>
{#/for}
</select>
</textarea>
</body>
<script type="text/javascript">
$(function(){
	upload("mydiv1");
});
function upload(div){
	
     var obj=$.ajaxcomm.ajaxGetData("<%=path%>/resource/testjtemplates","");
     if(obj.success){
    	 $("#"+div).setTemplateElement('mytextarea',null,{filter_data:false});
		 $("#"+div).processTemplate(obj.result);
     }
}
</script>
</html>