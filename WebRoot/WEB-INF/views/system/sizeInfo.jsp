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

<title>My JSP 'employeeInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="id" type="hidden" value="${size.getId()}" />
	<form id="ffemp" method="post" style="margin: 10px; text-align: left">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label for="name"
					style="display: inline-block; width: 80px"> 规格名称:</label></td>
				<td><input id="name" style="width:300px;" name="name" class="easyui-textbox"
					data-options="required : true" value="${size.getName()}" /></td>
			</tr>
		</table>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$("#sid").combobox({
				 url:'employee/station.html',
				valueField : 'id',
				textField : 'name'			
			});
			$("#sub").bind(
					"click",
					function() {
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data = {
							"id" : ($("#id").val() == "" ? 0 : $("#id")
									.val()),
							"name" : $("#name").textbox("getValue")

						};
						$.ajax({
							type : "POST",
							url : "size/saveSize.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data)
							},
							success : function() {
								$.messager.progress('close');
								reflush();
								$.messager.alert("操作提示", "保存成功");
								$('#dialog').window('close');
							}
						});
					});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('size.htmlifm').contentWindow.$(
					'#employee_list').datagrid('reload');
		}

		function myformatter(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
					+ (d < 10 ? ('0' + d) : d);
		}

		function myparser(s) {
			if (!s)
				return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0], 10);
			var m = parseInt(ss[1], 10);
			var d = parseInt(ss[2], 10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
				return new Date(y, m - 1, d);
			} else {
				return new Date();
			}
		}
	</script>
</body>
</html>
