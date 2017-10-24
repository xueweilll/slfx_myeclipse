<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'editpwd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form id="ffemp" method="post"
		style="margin: 10px; text-align: center;">
		<div style="float: left;">
			<label style="width: 60px;">原始密码:</label><input id="oldpwd" 
				class="easyui-textbox" name="oldpwd"
				data-options="type:'password',required:true,width:200,
				validType:['englishOrNum','length[5,20]']"
				  />
		</div>
		<div style="float: left;">
			<label style="width: 60px;">新设密码:</label><input id="newpwd" 
				class="easyui-textbox" name="newpwd" data-options="type:'password',width:200,required:true,validType:['englishOrNum','length[5,20]']">
		</div>
		<div style="float: left;margin-bottom: 10px;">
			<label style="width: 60px;">确认密码:</label><input id="compwd" 
				class="easyui-textbox" name="compwd" data-options="type:'password',width:200,required:true,validType:['englishOrNum','length[5,20]','same[\'newpwd\']']""
			 />
		</div>
		</br>
		<div id="btn" style="margin-top: 10px;">
			<div align="center" style="text-align: center;">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon_save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
				</div>
		</div>
	</form>
	<script type="text/javascript">
		$(function(){
			$("#sub").bind("click",
					function() {
						$.messager.progress();
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data = {
							"newpwd":$("#newpwd").textbox("getValue"),
							"oldpwd":$("#oldpwd").textbox("getValue")
						};
						$.ajax({
							type : "POST",
							url : "user/changePWD.html",
							dataType : "text",
							data : data,
							success : function(msg) {
								//alert(msg);
								data = eval('(' + msg + ')');
								$.messager.progress('close');
								if(data.result){}
								else{$.messager.alert("操作提示",data.msg,"error");}
								$('#dialog').window('close');
							}
						});
					});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});
	</script>
</body>
</html>
