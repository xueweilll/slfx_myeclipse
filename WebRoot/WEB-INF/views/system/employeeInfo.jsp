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
	<input id="empId" type="hidden" value="${employee.getId()}" />
	<input id="sidid" type="hidden" value="${snameid}" />
	<form id="ffemp" method="post" style="margin:10px; ">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label for="level"
					style="display: inline-block; width: 80px"> 级别:</label></td>
				<td><input id="level" name="level"
					value="${employee.getLeve()}" /></td>
			</tr>
			<tr>
				<td><label for="orgId"
					style="display: inline-block; width: 80px">部门名称:</label></td>
				<td><input id="orgId" name="orgId"
					value="${employee.getDepartment().getId()}" /></td>
			</tr>
			<tr>
				<td><label>枢纽:</label></td>
				<td><input id="sid" name="sid" class="easyui-combobox"
					value="${employee.getSid()}" /></td>
			</tr>
			<tr>
				<td><label for="empName">人员名称:</label></td>
				<td><input id="Name" class="easyui-textbox" type="text"
					name="empName"
					data-options="required:true,validType:['name','length[0,100]']"
					value="${employee.getName()}" /></td>
			</tr>
			<tr>
				<td><label for="birthday">生日:</label></td>
				<td><input id="birthday" name="birthday" value="${date}" /></td>
			</tr>
			<tr>
				<td><label for="position">职务:</label></td>
				<td><input id="userName" class="easyui-textbox" type="text"
					name="position"
					data-options="validType:['userName','length[1,10]']"
					value="${employee.getPosition()}" /></td>
			</tr>

			<tr>
				<td><label for="sex">性别:</label></td>
				<td><input id="sex" name="sex" value="${employee.getSex()}" /></td>
			</tr>
			<tr>
				<td><label for="age">年龄:</label></td>
				<td><input id="age" class="easyui-textbox" type="text"
					name="age"
					data-options="required:true,validType:['integer','length[1,3]'],
					disabled:true"
					value="${employee.getAge()}" /></td>
			</tr>

			<tr>
				<td><label for="phone">手机:</label></td>
				<td><input id="phone" class="easyui-textbox" type="text"
					name="phone"
					data-options="required:true,validType:['mobile','length[1,11]']"
					value="${employee.getPhone()}" /></td>
			</tr>
			<tr>
				<td><label for="mobile">电话:</label></td>
				<td><input id="mobile" class="easyui-textbox" type="text"
					name="mobile" data-options="validType:['phone','length[1,12]']"
					value="${employee.getTelephone()}" /></td>
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
			var myDate = new Date();
			var y;
			$("#orgId").combotree({
				url : 'department/departmentsBind.html',
				required : true,
				onSelect : function(node) {
					$("#sid").combobox({
						url : 'employee/station.html?id=' + node.id,
						valueField : 'id',
						textField : 'name',
						value : ''
					});
				}
			});
			$("#sid").combobox({
				url : 'employee/station.html?id=' + $("#orgId").val(),
				valueField : 'id',
				textField : 'name'

			});
			$("#level").combobox({
				url : 'paramers/leve.html',
				valueField : 'id',
				textField : 'text',
				editable : false,
				required : true
			});
			$("#sex").combobox({
				url : 'paramers/sex.html',
				valueField : 'id',
				textField : 'text',
				editable : false,
				required : true
			});
			$('#birthday').datebox().datebox('calendar').calendar(
					{
						validator : function(date) {
							var now = new Date();
							var d2 = new Date(now.getFullYear() - 18, now
									.getMonth(), now.getDate());
							return date <= d2;
						}
					});
			$("#birthday").datebox(
					{

						formatter : function(date) {
							y = date.getFullYear();
							var m = date.getMonth() + 1;
							var d = date.getDate();
							return y + "-" + (m < 10 ? ("0" + m) : m) + "-"
									+ (d < 10 ? ("0" + d) : d);
						},
						editable : false,

						onShowPanel : function() {
							var now = new Date();
							$("#birthday").datebox().datebox('calendar')
									.calendar(
											'moveTo',
											new Date(now.getFullYear() - 18,
													now.getMonth(), now
															.getDay()));
						},
						onSelect : function(date) {

							var x = $("#birthday").textbox("getValue");
							if ((myDate.getFullYear() - y) == 0) {
								alert("请输入正确生日");
								return;
							}
							$("#age").textbox('setText',
									myDate.getFullYear() - y);
							$("#age").textbox({
								'disabled' : true,
								'required' : true
							});
						}
					});

			$("#sub")
					.bind(
							"click",
							function() {
								$.messager.progress();
								/* 			if ($("#orgId").combotree('getValue') == 0) {
												$.messager.alert("操作提示", "部门树不能被选择！", "info");
												$.messager.progress('close');
												return false;
											} */
								var isValid = $("#ffemp").form('validate');
								if (!isValid) {
									$.messager.progress('close');
									return false;
								}
								var sids;
								if ($("#sid").combobox("getValue") == null) {
									sids = "";
								} else {
									sids = $("#sid").combobox("getValue");
								}
								var data = {
									"id" : ($("#empId").val() == "" ? 0 : $(
											"#empId").val()),
									"department" : {
										"id" : $("#orgId").textbox("getValue")
									},
									"name" : $("#Name").textbox("getValue"),
									"birthday" : $("#birthday").datebox(
											"getValue") == "" ? null : $(
											"#birthday").datebox("getValue")
											+ " 00:00:00",
									"age" : $("#age").textbox("getValue"),
									"sex" : $("#sex").combobox("getValue"),
									"position" : $("#userName").textbox(
											"getValue"),
									"telephone" : $("#mobile").textbox(
											"getValue"),
									"phone" : $("#phone").textbox("getValue"),
									"leve" : $("#level").textbox("getValue"),
									"sid" : sids
								};
								$.ajax({
									type : "POST",
									url : "employee/save.html",
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
			document.getElementById('employee.htmlifm').contentWindow.$(
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
