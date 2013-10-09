<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/commons/comm.jsp" %>
<script type="text/javascript" src="<%=path%>/resources/common/js/ajaxfileupload.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form id="form_upload" enctype="multipart/form-data" method = "post" action = "<%=path%>/resource/upload">  
      <!--  <input type="text" name="userName" />    --> 
       <p>上传文件1:<input type = "file" name = "file" size = "20" maxlength = "20"><br></p>  
       <p>上传文件2:<input type = "file" name = "file" size = "20" maxlength = "20"><br></p>  
       <p>上传文件3:<input type = "file" name = "file" size = "20" maxlength = "20"><br></p>  
       <p>上传文件4:<input type = "file" name = "file" size = "20" maxlength = "20"><br></p>  
       <input type="button" value="上传" onclick="upload();"/>
       <input type="submit" value="上传2"/>
    </form>  

</body>
<script type="text/javascript">
function upload(){
	var params=$("#form_upload").serialize();
	 $.ajaxFileUpload
		(
			{
				url:"<%=path%>/resource/upload",
				data:params,
				secureuri:false,
				oldFormId:'form_upload',//fileElementId:'fileToUpload',
				dataType: 'json',
				cache:false,
				async:false,
				success: function (data, status)
				{
					alert("成功:"+data.result.success+"失败:"+data.result.fail);
					
				},error: function (errdata, status, e)
				{
					alert("操作失败！");
				}
			}
		)

}
</script>
</html>