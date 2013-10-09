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
<title>权限列表</title>
<link rel="stylesheet" href="<%=basePath %>resources/ztree/css/ztree.css" type="text/css">
<script type="text/javascript" src="<%=basePath %>resources/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=basePath %>resources/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
var setting = {
	check: {
		enable: true,
		nocheckInherit: true,
		chkboxType: { "Y": "ps", "N": "ps" }
	}, data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "parentId",
			rootPId: null
		}, key: {
			checked: "isChecked",
			name: "perName",
			title: "perDesc"
		}
	},callback: {
		onCheck: onCheck
	}
};
function onCheck(){
	var zTree = $.fn.zTree.getZTreeObj("permission_tree");
	var checkedNodes=zTree.getCheckedNodes(true);
	var count=checkedNodes.length;
	var ids=new Array();
	for(var i=0;i<count;i=i+1){
		ids.push(checkedNodes[i].id);
	}
	ids=ids.join(",");
	$("#p_checked").val(ids);
}
//expandNode 方法就是展开节点的。 getNodesByParam 可以根据属性查找你需要的节点。。。。如果是根节点，那么直接用 getNodes 获取数据，直接for 遍历即可
function select_permission() {
	$.ajax({
		type: "GET",
        url: "<%=basePath %>admin/security/permission/list/${id }",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
        	$.fn.zTree.init($("#permission_tree"), setting, data);
        },
        error: function (msg) {
            alert("加载权限列表出错： "+msg);
        }
    });
}
$(document).ready(function(){
	select_permission();
});
</script>
</head>
<body>
<div id="permmission_list" >
	<ul id="permission_tree" class="ztree"></ul>
</div>
<form action="" method="post">
<input type="hidden" name="p_checked" id="p_checked" />
</form>
</body>
</html>
