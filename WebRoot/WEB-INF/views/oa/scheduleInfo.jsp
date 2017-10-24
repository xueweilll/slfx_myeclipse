<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'scheduleInfo.jsp' starting page</title>

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
	<input id="scheduleId" type="hidden" value="${schedule.getId()}" />
	<input id="userId" type="hidden"
		value="${schedule.getScheduler().getId()}" />
	<form id="ffemp" method="post"
		style="margin-top: 10px; text-align: center;">
		<div>
			<label for="title">标题:</label> <input id="title"
				class="easyui-textbox" style="width: 305px;"
				data-options="required:true" value="${schedule.getTitle()}">
		</div>
		<div>
			<label for="content">内容:</label> <input id="content"
				class="easyui-textbox" name="content" style="height: 60px;"
				data-options="multiline:true" value="${schedule.getContent()}" />
		</div>		
		<div>
			<label for="scheduler">待办人:</label> <select id="scheduler"
				class="easyui-combobox" style="width: 305px;"
				data-options="required:true">
			</select>
		</div>
		<div>
			<label for="requiredtime">待办时间:</label> <input id="requiredtime" 
			style="width: 305px;"
				data-options="required:true" value="${schedule.getRequiredtime()}">
		</div>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$('#scheduler').combobox({
			    url:'user/user_data.html',
			    valueField:'powerId',
			    textField:'powerName',
			    editable:false,
			    value:$("#userId").val() //默认选中value指定的选项
			});
			
			$("#requiredtime").datebox({
				required:"true",
				missingMessage:"该输入项为必输项",
				formatter:function(date){
					var y = date.getFullYear();
		            var m = date.getMonth() + 1;
		            var d = date.getDate();
		            return y + "-" + (m < 10 ? ("0" + m) : m) + "-" + (d < 10 ? ("0" + d) : d);
				}
			});
			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data = {
							"id" : ($("#scheduleId").val() == "" ? 0 : $(
									"#scheduleId").val()),
							"title":$("#title").textbox("getValue"),
							"content":$("#content").textbox("getValue"),
							"scheduler" : {"id":$("#scheduler").combobox("getValue")},
							"requiredtime" : $("#requiredtime").datebox("getValue")
									+ " 00:00:00",
						};
						$.ajax({
							type : "POST",
							url : "schedule/save.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data)
							},
							success : function(msg) {
								//alert(msg);
								data = eval('(' + msg + ')');
								$.messager.progress('close');
								if(data.result){reflush();}
								else{$.messager.alert("操作提示",data.msg,"error");}
							}
						});
					});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('schedule.htmlifm').contentWindow.$('#schedule_list')
					.datagrid('reload');
		}
	</script>
</body>
</html>
