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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<body>
	<input id="id" type="hidden" value="${files.getId()}" />

	<form id="addfiles" method="post" enctype="multipart/form-data"
		style="margin-top: 10px; text-align: center;">
		<div>
			<label for="filename">选择文件:</label> <input id="filename" type="file"
				name="filename" style="width: 305px;" data-options="required:true"
				value="${files.getFilename()}">
		</div>
		<!-- <div>
			<label for="uploader">创建人:</label> <input id="uploader"
				style="width: 305px;" data-options="required:true"
				value="${files.getUploader()}">
		</div> -->
		<div>
			<label for="folerid">目录名称:</label> <select id="folerid"
				style="width: 305px;" data-options="required:true">
			</select>
		</div>
		<!-- <div>
			<label for="saveroute">保存路径:</label> <input id="saveroute"
				class="easyui-textbox" name="saveroute" style="width: 305px;"
				data-options="required:true" value="${files.getSaveroute()}" />
		</div> -->
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;
				<!-- <a id="clear"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-undo'">清空</a>&nbsp;&nbsp; -->
				<a id="clear"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script type="text/javascript">
		$(function() {
			/*$('#uploader').combogrid({
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
			//默认选中value指定的选项
			});*/

			$('#folerid').combotree({
				url : 'files/filesTree.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				value : $("#folerid").val(),//默认选中value指定的选项
				onSelect : function(node) {
						$('#folerid').combotree('clear');
				},
				onClick : function(node) {
					if (node.id == 0) {
						$.messager.alert("操作提示", "根节点无法选择，请选择相关文件夹！", "error");
						$('#folerid').combotree('clear');
					}
				},
				onLoadSuccess : function() {
					$("#folerid").combotree("setValues", $("#folerid").val());
				}
			});

			$("#sub").bind(
					"click",
					function() {
						var fname = document.getElementById("filename").value;
						if (fname == "") {
							$.messager.alert('操作提示', '请选择所要上传的文件', 'info');
							return false;
						}

						$.messager.progress();
						var isValid = $("#addfiles").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}

						var data1;
						data1 = ($("#id").val() == "" ? 0 : $("#id").val())
								+ "/" + $("#folerid").textbox("getValue");

						$.ajaxFileUpload({
							//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
							url : "files/saveFiles.html",
							secureuri : false, //是否启用安全提交,默认为false
							dataType : 'text', //服务器返回的格式,可以是json或xml等
							fileElementId : 'filename',
							data : {
								jsonStr : data1
							},
							success : function(data, status) { //服务器响应成功时的处理函数

								data = data.replace("<PRE>", ''); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
								data = data.replace("</PRE>", '');
								data = data.replace("<pre>", '');
								data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
								$.messager.progress('close');
								$.messager.alert('操作提示', '操作成功', 'info');
								$("#dialog").dialog("close");
								reflush();

							},
							error : function(data, status, e) { //服务器响应失败时的处理函数
								$('#result').html('上传失败，请重试！！');
							}
						});

					});

			$("#clear").bind("click", function() {
				$("#addfiles").form("clear");
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
