<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath %>resources/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<title>ztree</title>
</head>

<body>
<a href="javascript: select_permission();" >修改权限</a>
<div id="permmission_list" >
	<ul id="permission_tree" class="ztree"></ul>
</div>
<div>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
<script type="text/javascript">
var setting = {
			check: {
				enable: true,
				chkboxType : { "Y" : "ps", "N" : "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	
var zNodes =[
 			{ id:1, pId:0, name:"随意勾选 1"},
 			{ id:11, pId:1, name:"随意勾选 1-1"},
 			{ id:111, pId:11, name:"随意勾选 1-1-1"},
 			{ id:112, pId:11, name:"随意勾选 1-1-2"},
 			{ id:12, pId:1, name:"随意勾选 1-2"},
 			{ id:121, pId:12, name:"随意勾选 1-2-1"},
 			{ id:122, pId:12, name:"随意勾选 1-2-2"},
 			{ id:2, pId:0, name:"随意勾选 2"},
 			{ id:21, pId:2, name:"随意勾选 2-1"},
 			{ id:22, pId:2, name:"随意勾选 2-2"},
 			{ id:221, pId:22, name:"随意勾选 2-2-1"},
 			{ id:222, pId:22, name:"随意勾选 2-2-2"},
 			{ id:23, pId:2, name:"随意勾选 2-3"}
 		];
$.fn.zTree.init($("#permission_tree"), setting, zNodes);
$.fn.zTree.init($("#treeDemo"), setting, zNodes);	
function select_permission() {
	//$.fn.zTree.init($("#permission_tree"), setting, zNodes);
}
$(document).ready(function(){
	select_permission();
});
</script>
</html>