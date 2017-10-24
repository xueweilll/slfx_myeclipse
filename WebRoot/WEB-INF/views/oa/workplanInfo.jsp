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

<title>My JSP 'workplanInfo.jsp' starting page</title>

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
	<input id="workplanId" type="hidden" value="${workplan.getId()}" />
	<form id="ffemp" method="post"
		style="margin-top: 10px; text-align: center;">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label for="title">标题:</label></td>
				<td><input id="title" class="easyui-textbox" type="text"
					name="title"
					data-options="required:true,validType:['length[0,100]']"
					value="${workplan.getTitle()}" /></td>
			</tr>
			<tr>
				<td><label for="remark">内容:</label></td>
				<td><input id="remark" class="easyui-textbox" name="remark"
					style="height: 70px" data-options="multiline:true"
					value="${workplan.getRemark()}" /></td>
			</tr>
			<tr>
				<td><label for="beginTime">开始时间:</label></td>
				<td><input id="beginTime" name="beginTime" value="${beginTime}" /></td>
			</tr>
			<tr>
				<td><label for="endTime">结束时间:</label></td>
				<td><input id="endTime" name="endTime" value="${endTime}" /></td>
			</tr>
			<tr>
				<td><label for="remindTime">提醒时间:</label></td>
				<td><input id="remindTime" name="remindTime"
					value="${remindTime}" /></td>
			</tr>
		</table>
		<div id="btn" style="text-align: center">
			<a id="del" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" style="display:none">删除</a>&nbsp;&nbsp;
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>

	</form>
	<script type="text/javascript">
		$(function() {

			$("#beginTime").datetimebox({
				required : true,
				showSeconds : true,
				missingMessage : "该输入项为必输项",
				editable : false
			});
			$("#endTime").datetimebox({
				required : true,
				showSeconds : true,
				missingMessage : "该输入项为必输项",
				editable : false
			});
			$("#remindTime").datetimebox({
				required : true,
				showSeconds : true,
				missingMessage : "该输入项为必输项",
				editable : false
			});
			$("#sub").bind("click",	function() {
				$.messager.progress();
				var isValid = $("#ffemp").form('validate');
				if (!isValid) {
					$.messager.progress('close');
					return false;
				}
				var s = $("#beginTime").datebox("getValue");
				s = s.substring(0, 7);
				var data = {
					"id" : ($("#workplanId").val() == "" ? 0
							: $("#workplanId").val()),
					"title" : $("#title").textbox("getValue"),
					"remark" : $("#remark").textbox("getValue"),
					"beigintime" : $("#beginTime").datetimebox(
							"getValue"),
					"endtime" : $("#remindTime").datetimebox(
							"getValue"),
					"remindtime" : $("#endTime").datetimebox(
							"getValue")	
				};
				$.ajax({
					type : "POST",
					url : "workplan/save.html",
					dataType : "text",
					data : {
						"jsonStr" : JSON.stringify(data)
					},
					success : function(msg) {
						data = eval('(' + msg + ')');
						$.messager.progress('close');
						if (data.result) {
							$('#dialog').window('close');
							document.getElementById('workplan.htmlifm').contentWindow.removeBox();
							document.getElementById('workplan.htmlifm').contentWindow.drawExsit(s);
						} else {
							$.messager.alert("操作提示",data.msg, "error");
						}
					}
				});
			});

			$("#can").bind("click", function() {
				$('#dialog').window('close');
			});

			$("#del").bind("click",	function() {
				var id = $("#workplanId").val();
				if (id == "") {
					return false;
				}
				var s = $("#beginTime").datebox("getValue");
				s = s.substring(0, 7);
				$.ajax({
					type : 'POST',
					url : "workplan/distory.html",
					dataType : "text",
					data : {
						"id" : id
					},
					success : function(msg) {
						data = eval('(' + msg + ')');
						$.messager.progress("close");
						if (data.result) {
							//$.messager.alert("操作提示", "删除成功！");
							$('#dialog')
									.window('close');
							document
									.getElementById('workplan.htmlifm').contentWindow
									.removeBox();
							document
									.getElementById('workplan.htmlifm').contentWindow
									.drawExsit(s);
						} else {
							$.messager.alert("操作提示",
									data.msg, "error");
						}
					}
				});
			});

			show();
		});

		function show() {
			var text = document.getElementById("workplanId").value;
			if (text == "") {
				document.getElementById("del").style.display = "none";//
			} else {
				document.getElementById("del").style.display = "";//
			}
		}

		
	</script>
</body>
</html>
