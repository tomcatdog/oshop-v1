<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="<%=basePath%>/thirdparty/ckeditor/ckeditor.js"></script> 
<script src="<%=basePath%>/thirdparty/ckfinder/ckfinder.js"></script> 
<title>Insert title here</title>
</head>
<body>
<textarea id="eeee" name="eeee" class="ckeditor" rows="10" cols="80" style='display: none'>&nbsp;</textarea>
</body>
</html>