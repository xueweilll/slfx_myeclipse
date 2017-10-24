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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<script type="text/javascript">
	$(document).ready(function() {
		loadGrid();
	});

	var id = 0;
	var pareId = 0;
	var url = "";

	function loadGrid() {
		$("#fileslist").datagrid({
			title : '文件管理',
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'files/list.html?folerid=' + id,
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb'
		});

		var p = $("#fileslist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		$("#starttime").datetimebox({
			onChange : function() {
				$('#endtime').datetimebox('enableValidation');
			}
		});
	}

	function rowformater(value, row, index) {
		return "<a href='files/download.html?uploadroute=" + myEncoder(row.uploadroute)
				+ "' target='_blank'>下载</a>";
	}

	/*function downloadFiles() {
		var row = $("#fileslist").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else {
			var link = 'files/download.html?uploadroute='
					+ myEncoder(row.uploadroute);
			window.open(link);
		}
		return false;
	}*/

	$(function() {
		$("input").attr("disabled", "disabled");
		$("#btn").hide();
		$("#tt").tree({
			url : 'files/filesTree.html',
			onClick : function(node) {
				$("#fileslist").datagrid("clearSelections");
				$("#status").textbox("setValue", "显示");
				$("input").attr("disabled", "disabled");
				$("#btn").hide();

				id = node.id;
				pareId = node.pareId;
				if (node.id == "0") {
					name = "根目录";
				} else {
					name = node.obj.name;
					createrId = node.obj.createrid;
				}
				loadGrid();
			}
		});

	});

	function treeBind() {
		$("#tt").tree('reload');
	}

	function selectFiles() {
		$("#fileslist").datagrid('load', {
			starttime : $("#starttime").datetimebox("getValue"),
			endtime : $("#endtime").datetimebox("getValue")
		});
		$('#starttime').datetimebox('setValue', '');
		$('#endtime').datetimebox('setValue', '');
	}

	function distoryFiles() {
		var row = $("#fileslist").datagrid("getSelected");
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
						$('#fileslist').datagrid('reload');
						$.messager.alert('操作提示', '操作成功', 'info');
						$("#fileslist").datagrid("clearSelections");
					}
				});
			}
		});
	}

	function isRoot() {
		if (id == 0 || id == null || id == "") {
			$.messager.alert("操作提示", "文件列表根节点无法共享和删改，请选择其它节点进行操作！！", "error");
			return true;
		} else {
			return false;
		}
	}

	function distroyFolder() {
		//var row = $("#tt").tree('getSelected');

		url = "files/deleteFolder.html";
		if (isRoot()) {
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : url,
					data : {
						"id" : id
					},
					success : function() {
						$.messager.progress('close');
						treeBind();
						$.messager.alert('操作提示',
								'操作成功！(如果该文件夹或子文件夹下包含文件，将不予删除！)', 'info');

						//$("#tt").tree("clear");
						//$("#tt").val(""); 
						//$('.easyui-combotree').combotree("clear");
						//$('#tt').tree('clearSelections'); 
						//$('#tt').combotree('clear');
						//$('#tt').find('.tree-node-selected').removeClass('tree-node-selected');
						//$('#tt').tree('remove', row[0].id);
						//$('#tt').tree('reload', row[0].pid);

						//reflush();
					}
				});
			}
		});
	}

	function addFolder() {
		showDialogWH("新建文件夹", "files/folderInfo.html?id=0", "450px", "300px");
	}

	function editFolder() {
		if (isRoot()) {
			return false;
		} else {
			showDialogWH("修改文件夹", "files/folderInfo.html?id=" + id, "450px",
					"300px");
		}
	}

	function addFiles() {
		showDialogWH("上传文件", "files/filesInfo.html?id=0", "450px", "300px");
	}
	
	function shareFolder() {
		if (isRoot()) {
			return false;
		} else {
			showDialogWH("文件共享", "files/folderToShare.html?id=" + id,
					"450px", "200px");
		}
	}

	function shareFiles() {
		var row = $("#fileslist").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		} else {
			showDialogWH("文件共享", "files/filesToShare.html?id=" + row.id,
					"450px", "200px");
		}
	}

	function reflush() {
		document.getElementById('files.htmlifm').contentWindow.$("#tt").tree(
				'reload');
	}
</script>

<body class="easyui-layout " id="cc" >
	<div 
		data-options="region:'west',title:'文件夹',iconCls:'icon icon-department',tools:'#tl'"
		style=" width: 250px;">
		<ul id="tt"></ul>
	</div>
	<div id="tl">
		<a href="javascript:void(0)" class="icon-share" onclick="shareFolder()"></a>
		<a href="javascript:void(0)" class="icon-add" onclick="addFolder()"></a>
		<a href="javascript:void(0)" class="icon-edit" onclick="editFolder()"></a>
		<a href="javascript:void(0)" class="icon-remove"
			onclick="distroyFolder()"></a>
	</div>
	<div data-options="region:'center',iconCls:'icon icon-editor'"background: #eee">
		<table id="fileslist" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th hidden data-options="field:'id',width:160">id</th>
					<th data-options="field:'name',width:160">文件名称</th>
					<th data-options="field:'folername',width:160">所在目录</th>
					<th data-options="field:'username',width:160">上传人</th>
					<th data-options="field:'uploadtime',width:160">上传时间</th>
					<th data-options="field:'uploadroute',width:80,formatter:  rowformater">操作</th>
				</tr>
			</thead>
		</table>
		<div id="tb" >
			<div class="cz_div_title">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="addFiles()" iconCls="icon-download" plain="false">上传</a> 
				<!-- <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="downloadFiles()" iconCls="icon-download" plain="false">下载</a> -->
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="shareFiles()" iconCls="icon-share" plain="false">共享</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="distoryFiles()" iconCls="icon-cancel" plain="false">删除</a>
			</div>
			<div class="cz_div">
				开始时间: <input id="starttime" type="text" data-options="width:160">
				结束时间: <input id="endtime" type="text" class="easyui-datetimebox"
					data-options="width:160,validType:['compareDate[starttime]']">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="selectFiles()" iconCls="icon-search">查询</a>
			</div>
		</div>
	</div>
</body>
</html>
