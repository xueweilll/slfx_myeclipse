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
	<input id="creater" type="hidden" value="${size.getCreater()}" />
	<input id="createtime" type="hidden" value="${createtime}" />
	<form id="ffemp" method="post" style="margin: 10px; text-align: left">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label for="mname"
					style="display: inline-block; width: 80px"> 物资名称：</label></td>
				<td><input id="mname" style="width:300px;" name="mname"
					class="easyui-textbox" data-options="required : true"
					value="${size.getName()}" /></td>
			</tr>
			<tr>
				<td><label for="mtype"
					style="display: inline-block; width: 80px"> 物资类别：</label></td>
				<td><input id="mtype" style="width:300px;" name="mtype"
					class="easyui-combobox" data-options="required : true"
					value="${size.getType()}" /></td>
			</tr>
			<tr>
				<td><label for="pname" style="display: inline-block;width:80px">计量名称：</label>
				</td>
				<td><input id="pname" style="width:300px;" name="pname"
					value="${size.getPrickle().getId()}" /></td>
			</tr>
			<tr>
				<td><label for="sname" style="display: inline-block;width:80px">规格名称：</label>
				</td>
				<td><input id="sname" style="width:300px;" name="sname"
					value="${size.getSize().getId()}" /></td>
			</tr>
			<tr>
				<td><label for="memo" style="display:inline-block; width:100px">物资描述：</label></td>
				<td><input id="memo" style="width:300px;height:120px;"
					name="memo" class="easyui-textbox" data-options="multiline:true,validType:'xssValidate'"
					style="height:80px" value="${size.getMemo()}" /></td>
			</tr>
		</table>
		</br>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="save(0)">保存</a>&nbsp;&nbsp;
			<a id="commit" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="save(1)">保存并提交</a>&nbsp;&nbsp;
			<a id="can" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {

			$("#pname").combobox({
				url : 'materials/findPrickleName.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				required : true

			});
			$("#mtype").combobox({
				url : 'paramers/MaterialType.html',
				valueField : 'id',
				textField : 'text',
				editable : false,
				required : true
			});
			$("#sname").combobox({
				url : 'materials/findSizeName.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				required : true
			});
			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});
		var state;
		function save(obj) {
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				return false;
			}
			if (obj == 0) {
				state = 0;
			} else {
				state = 1;
			}
			var data = {
				"id" : ($("#id").val() == "" ? 0 : $("#id").val()),
				"name" : $("#mname").textbox("getValue"),
				"sizeid" : $("#sname").combobox("getValue"),
				"prickleid" : $("#pname").combobox("getValue"),
				"memo" : $("#memo").textbox("getText"),
				"state" : state,
				"type":$("#mtype").combobox("getValue"),
				"creater" : $("#creater").val()
			};
			if(obj=="1"){
			$.messager.confirm('确认对话框', '您确定要提交吗？', function(r){
				if (r){
					$.ajax({
						type : "POST",
						url : "materials/" + (obj == "0" ? "saveMaterials" : "commit")
								+ ".html",
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
				}
			});
			}else{
			$.ajax({
				type : "POST",
				url : "materials/" + (obj == "0" ? "saveMaterials" : "commit")
						+ ".html",
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
			}
		}
		function reflush() {
			document.getElementById('materials.htmlifm').contentWindow.$(
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
