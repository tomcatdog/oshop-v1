<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
</head>

<body>
<a href="<%=basePath%>admin/security/login">登陆</a><br/><br/>

<a href="<%=basePath%>test/ckeditor.jsp">CKEditor演示</a><br/><br/>

<a href="<%=basePath%>test/upload.jsp">上传演示</a><br/><br/>

<a href="<%=basePath%>test/jtemplates.jsp">jtemplates演示</a><br/><br/>

<a href="<%=basePath%>test/ztree.jsp">ztree演示</a><br/><br/>
</body>
</html>