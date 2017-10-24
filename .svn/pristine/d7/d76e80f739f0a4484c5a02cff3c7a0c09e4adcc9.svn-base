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
<%@include file="../header.jsp"%>
<title>文件管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<script type="text/javascript">
	$(document).ready(function() {
		loadMyShareGrid();
		loadShareToMeGrid();
	});

	var id = 0;
	var pareId = 0;
	var url = "";

	function rowformater(value, row, index) {
		return "<a href='files/download.html?uploadroute=" + myEncoder(row.uploadroute)
				+ "' target='_blank'>下载</a>";
	}
	
	function loadMyShareGrid() {
		$("#myshare").datagrid({
			//title : '我共享的文件',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'filesShare/mysharelist.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#mytb'
		});

		/*var p = $("#myshare").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});*/

		$("#starttime").datetimebox({
			onChange : function() {
				$('#endtime').datetimebox('enableValidation');
			}
		});
	}

	function loadShareToMeGrid() {
		$("#sharetome").datagrid({
			//title : '共享给我的文件',
			iconCls : 'icon icon-icon35',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'filesShare/sharetomelist.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tometb'
		});

		/*$("#download").bind(
				"click",
				function() {
					var row = $("#sharetome").datagrid("getSelected");
					if (row == null) {
						$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
						return false;
					} else {
						var link = 'files/download.html?uploadroute='
								+ row.uploadroute;
						window.open(link);
						$.messager.alert('操作提示', '操作成功', 'info');
					}
					return false;
		});*/

		/*var p = $("#myshare").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});*/

		$("#starttime1").datetimebox({
			onChange : function() {
				$('#endtime1').datetimebox('enableValidation');
			}
		});
	}

	function selectMyFiles() {
		$("#myshare").datagrid('load', {
			starttime : $("#starttime").datetimebox("getValue"),
			endtime : $("#endtime").datetimebox("getValue")
		});
		$('#starttime').datetimebox('setValue', '');
		$('#endtime').datetimebox('setValue', '');
	}

	function selectToMeFiles() {
		$("#sharetome").datagrid('load', {
			starttime : $("#starttime1").datetimebox("getValue"),
			endtime : $("#endtime1").datetimebox("getValue")
		});
		$('#starttime1').datetimebox('setValue', '');
		$('#endtime1').datetimebox('setValue', '');
	}

	function distoryFiles() {
		var row = $("#myshare").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "files/deleteFiles.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#myshare").datagrid("reload");
						$.messager.alert('操作提示', '操作成功', 'info');
					}
				});
			}
		});
	}

	function addFiles() {
		showDialogWH("上传文件", "files/filesInfo.html?id=0", "450px", "300px");
	}

	function shareFiles() {
		var row = $("#myshare").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else {
			showDialogWH("文件共享", "files/filesToShare.html?id=" + row.id,
					"450px", "200px");
		}
	}
</script>

<body class="easyui-layout">
	<div class="easyui-tabs" data-options="region:'center'">
		<div title="我共享的文件">
			<table id="myshare" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th hidden data-options="field:'id',width:160">id</th>
						<th data-options="field:'name',width:230">文件名称</th>
						<th data-options="field:'username',width:200">共享用户（共享给谁）</th>
						<th data-options="field:'sharedate',width:200">共享时间</th>
					</tr>
				</thead>
			</table>
			<div id="mytb" >
				<div class="cz_div">
					开始时间: <input id="starttime" type="text" data-options="width:160"
						class="easyui-datetimebox"> 结束时间: <input id="endtime"
						type="text" class="easyui-datetimebox"
						data-options="width:160,validType:['compareDate[starttime]']"
						required="required"> <a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="selectMyFiles()"
						iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
		<div title="共享给我的文件" >
			<table id="sharetome" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th hidden data-options="field:'id',width:160">id</th>
						<th data-options="field:'name',width:230">文件名称</th>
						<th data-options="field:'username',width:200">共享用户（谁共享的）</th>
						<th data-options="field:'sharedate',width:200">共享时间</th>
						<th data-options="field:'uploadroute',width:110,formatter:  rowformater">操作</th>
					</tr>
				</thead>
			</table>
			<div id="tometb" >
				<div class="cz_div">
					开始时间: <input id="starttime1" type="text" data-options="width:160"
						class="easyui-datetimebox"> 结束时间: <input id="endtime1"
						type="text" class="easyui-datetimebox"
						data-options="width:160,validType:['compareDate[starttime1]']"
						required="required"> <a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="selectToMeFiles()"
						iconCls="icon-search">查询</a> 
						<!-- <a href="javascript:void(0)"
						id="download" class="easyui-linkbutton" target="blank"
						iconCls="icon-download" plain="true">下载</a> -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>
