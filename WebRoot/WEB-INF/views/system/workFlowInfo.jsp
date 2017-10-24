<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'workFlowInfo.jsp' starting page</title>

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
		style="margin-top: 10px; text-align: center;">
		<div>
			<label for="name">流程名称:</label> <input id="name"
				class="easyui-textbox" name="name" style="width: 305px;"
				data-options="required:true,
				validType:['chinese','length[2,10]']"
				  />
		</div>
		<div>
			<label for="activitiType">流程类别:</label> <input id="activitiType"
				class="easyui-combobox" style="width: 305px;"
				data-options="required:true" value="">
		</div>
		<div>
			<label for="zipfile">ZIP文件:</label>
			<input type="file" class="easyui-validatebox" id="zipfile" name="zipfile" accept="aplication/zip" style="width:300px"
			 data-options="required:true"  >
		</div>
		
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
	function reflush() {
		document.getElementById('workFlow.htmlifm').contentWindow.$(
				'#deployment_list').datagrid('reload');
	}
		$(function(){
			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						$.ajaxFileUpload({
					        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
					        url : "workFlow/save.html",
					        secureuri:false,                       //是否启用安全提交,默认为false
					        dataType:'text',                       //服务器返回的格式,可以是json或xml等
					        fileElementId:'zipfile',
							data:{name:$("#name").val(),type:$("#activitiType").combobox("getValue")},
					        success:function(data){        //服务器响应成功时的处理函数
					            $.messager.progress('close');
					        	if(data){
						            $.messager.alert('操作提示', '操作成功', 'info');
						            $("#dialog").dialog("close");
						            reflush();
					        	}else{
						        	$.messager.alert('操作提示', '上传失败，请重试！', 'error');
					        	}
					            
					        },
					        error:function(data, status, e){ //服务器响应失败时的处理函数
					        	$.messager.alert('操作提示', '上传失败，请重试！', 'error');
					        }
					  });
						
			});
			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
			
			$('#activitiType').combobox({    
			    url:'paramers/activitiType.html',    
			    valueField:'id',    
			    textField:'text',
			    editable:false,
			    multiple:false,
			    value:'0'
			});
		});
	</script>
</body>
</html>
