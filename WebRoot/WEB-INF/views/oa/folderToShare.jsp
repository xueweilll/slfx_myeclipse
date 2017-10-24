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
	<input id="id" type="hidden" value="${foler.getId()}" />

	<form id="sharefolder" method="post"
		style="margin-top: 10px; text-align: center;">
		<div>
			<p>
		</div>
		<div>
			<p>
		</div>
		<div>
			<label for="shareuserid">文件夹共享用户:</label>
			<!-- <input id="uploader"
				style="width: 305px;" data-options="required:true"
				value="${files.getUploader()}"> -->
			<input id="shareuserid" style="width:300px;" data-options="required:true" />
		</div>
		<div>
			<p>
		</div>
		<div>
			<p>
		</div>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="clear"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#shareuserid').combogrid({
				url : 'document/getUsername.html',
				idField : 'id',
				textField : 'username',
				required : true,
				editable : false,
				multiple : true,
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				columns : [ [ {
					field : 'dname',
					title : '部门',
					width : 145
				}, {
					field : 'username',
					title : '用户名',
					width : 140
				} ] ],
				fitColumns : true
			});

			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#sharefolder").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}

						var data;
						data = ($("#id").val() == "" ? 0 : $("#id").val())
								+ "/" + $("#shareuserid").combogrid("getValues");

						$
								.ajax({
									type : "POST",
									url : "files/saveFolderShare.html",
									dataType : "text",
									data : {
										"jsonStr" : data
									},
									success : function(msg) {
										data = eval('(' + msg + ')');
										$.messager.progress('close');
										if (data.result) {
											reflush();
											$.messager.alert('操作提示', '操作成功',
													'info');
											$("#dialog").dialog("close");
										} else {
											$.messager.alert("操作提示", data.msg,
													"error");
										}
									}
								});

					});

			$("#clear").bind("click", function() {
				$("#sharefolder").form("clear");
			});
			$("#cancle").bind("click", function() {
				$("#dialog").dialog("close");
			});
		});

		function reflush() {
			document.getElementById('files.htmlifm').contentWindow.$(
					"#fileslist").datagrid('reload');
		}
	</script>
</body>
</html>
