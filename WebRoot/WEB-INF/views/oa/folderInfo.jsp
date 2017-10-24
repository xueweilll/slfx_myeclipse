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
<title>My JSP 'messageInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="folderId" type="hidden" value="${foler.getId()}" />
	<form id="addfolder" method="post"
		style="margin-top: 10px; text-align: center;">
		<div>
			<label for="name">文件夹名称:</label> <input id="name"
				class="easyui-textbox" name="name" style="width: 305px;"
				data-options="required:true" value="${foler.getName()}" />
		</div>
		<!-- <div>
			<label for="createrId">创建人:</label> <input id="createrId"
				style="width: 305px;" data-options="required:true"
				value="${foler.getCreaterid()}">
		</div> -->
		<div>
			<label for="parentId">上级目录名称:</label> <input id="parentId"
				name="parentId" style="width: 305px;" data-options="required:true"
				value="${foler.getParentid()}"> </select>
		</div>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="clear"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
			/*$('#createrId').combogrid({
				url : 'document/getUsername.html',
				idField : 'id',
				textField : 'username',
				editable : false,
				columns : [ [ {
					field : 'dname',
					title : '部门',
					width : 145
				}, {
					field : 'username',
					title : '用户名',
					width : 140
				} ] ]
			});*/

			$('#parentId').combotree(
					{
						url : 'files/filesTree.html',
						valueField : 'id',
						textField : 'name',
						editable : false,
						value : $("#parentId").val(),//默认选中value指定的选项
						onLoadSuccess : function() {
							$("#parentId").combotree("setValues",
									$("#parentId").val());
						}
					});

			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#addfolder").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data;
						data = ($("#folderId").val() == "" ? 0 : $("#folderId")
								.val())
								+ "/"
								//+ $("#createrId").combogrid("getValues")
								//+ "/"
								+ $("#name").textbox("getValue")
								+ "/"
								+ $("#parentId").textbox("getValue");

						$
								.ajax({
									type : "POST",
									url : "files/saveFolder.html",
									dataType : "text",
									data : {
										"jsonStr" : data
									},
									success : function(msg) {
										data = eval('(' + msg + ')');
										$.messager.progress('close');
										if (data.result) {
											reflush();
											$("#dialog").dialog("close");
											$.messager.alert('操作提示', '操作成功', 'info');
										} else {
											$.messager.alert("操作提示", data.msg,
													"error");
										}
									}
								});
					});

			$("#clear").bind("click", function() {
				$("#addfolder").form("clear");
			});
			$("#cancle").bind("click", function() {
				$("#dialog").dialog("close");
			});
		});

		function reflush() {
			document.getElementById('files.htmlifm').contentWindow.$("#tt")
					.tree('reload');
		}
	</script>

</body>
</html>
