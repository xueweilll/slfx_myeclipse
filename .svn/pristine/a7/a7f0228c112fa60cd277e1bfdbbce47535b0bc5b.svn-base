<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>文件管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<script type="text/javascript">
	
</script>



<body>
	<form action="todolist/uploadservlet.html" method="post" enctype="multipart/form-data">
        <div align="center">
            <fieldset style="width:80%">
                <legend>上传文件</legend><br/>
                    <div align="left">上传文件1</div>
                    <div align="left">
                        <input type="file" name="file1"/>
                    </div>
                    <div align="left">上传文件2</div>
                    <div align="left">
                        <input type="file" name="file2"/>
                    </div>
                    <div>
                        <div align='left'>上传文件说明1</div>
                        <div align='left'><input type="text" name="description1"/></div>
                    </div>
                    <div>
                        <div align='left'>上传文件说明2</div>
                        <div align='left'><input type="text" name="description2"/></div>
                    </div>
                    <div>
                        <div align='left'>
                            <input type='submit' value="上传文件"/>
                        </div>
                    </div>
            </fieldset>
        </div>
    </form>
</body>

</html>
