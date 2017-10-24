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

<title>固定资产列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="id" type="hidden" value="${assets.getId()}" />
	<input id="creater" type="hidden" value="${assets.getCreater()}" />
	<input id="createtime" type="hidden" value="${createtime}" />
	<form id="ffemp" method="post" style="margin: 10px; text-align: left">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label for="code" style="display: inline-block;width:80px">资产编号：</label>
				</td>
				<td><input id="code" class="easyui-textbox"
					style="width:300px;" name="code" data-options="disabled:true"
					value="${dcode}" /></td>
			</tr>
             <tr>
				<td><label>枢纽名称:</label></td>
				<td><input id="sid" name="sid" style="width:300px;"class="easyui-combobox"
					value="${assets.getSid()}" /></td>
			</tr>
			<tr>
				<td><label for="name"
					style="display: inline-block; width: 80px"> 物品名称：</label></td>
				<td><input id="name" style="width:300px;" name="name"
					class="easyui-textbox" data-options="required : true,validType:'judgeNull'"
					value="${assets.getName()}" /></td>
			</tr>
			<tr>
				<td><label for="principal"
					style="display: inline-block;width:80px">负责人：</label></td>
				<td><input id="principal" style="width:300px;" name="sname"
					class="easyui-textbox" value="${assets.getPrincipal()}" /></td>
			</tr>
			<tr>
				<td><label for="moblie"
					style="display:inline-block; width:100px">联系电话:</label></td>
				<td><input id="moblie" style="width:300px;" name="moblie"
					class="easyui-textbox"
					data-options="validType:['mobile','length[1,11]']"
					value="${assets.getMoblie()}" /></td>
			</tr>
			<tr>
				<td><label for="memo" style="display:inline-block; width:100px">资产描述：</label></td>
				<td><input id="memo" style="width:300px;height:120px;"
					name="memo" class="easyui-textbox"
					data-options="multiline:true,validType:['length[1,2000]']"
					style="height:80px" value="${assets.getMemo()}" /></td>
			</tr>
		</table>
		</br>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="save(0)">保存</a><!-- &nbsp;&nbsp;
			<a id="commit" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="save(1)">生成二维码</a> -->
			&nbsp;&nbsp; <a id="can" href="javascript:void(0)"
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$("#can").bind("click", function() {
				/* $("#ffemp").form("clear"); */
				$("#name").textbox("setText","");
				$("#principal").textbox("setText","");
				$("#moblie").textbox("setText","");
				$("#memo").textbox("setText","");
			});
			$("#sid").combobox({
				url : 'station/stationByFk.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				required : true
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
				"code" : $("#code").textbox("getText"),
				"principal" : $("#principal").textbox("getText"),
				"moblie" : $("#moblie").textbox("getText"),
				"memo" : $("#memo").textbox("getText").replace(/\s+/g, ""),
				"state" : state,
				"name" : $("#name").textbox("getText"),
				"sid":$("#sid").combobox("getValue")
			};
			$.ajax({
				type : "POST",
				url : "assets/" + (obj == "0" ? "saveAssets" : "build")
						+ ".html",
				dataType : "text",
				data : {
					"jsonStr" : JSON.stringify(data),
					"sname":$("#sid").combobox("getText")
				},
				success : function() {
					$.messager.progress('close');
					reflush();
					$.messager.alert("操作提示", "保存成功");
					$('#dialog').window('close');
				}
			});
		}

		function reflush() {
			document.getElementById('assets.htmlifm').contentWindow.$(
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
