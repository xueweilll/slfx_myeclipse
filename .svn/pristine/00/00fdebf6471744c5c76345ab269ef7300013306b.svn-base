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
<title>应急下发表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	$(function() {
		//tab
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				 $('#endtime').datetimebox('setValue', '');
				$('#starttime').datetimebox('setValue', ''); 
				if (index == "0") {
					$("#send").show();
				} else {
					$("#send").hide();
				}
				$("#egpatrolengineerlist").datagrid("unselectAll");
				$("#egpatrolengineerlist").datagrid("load", {
					type : "0",
					typeDate : index
				});
			}
		});
		
		
		var buttons=$.extend([],$.fn.datetimebox.defaults.buttons);
		buttons.splice(2,'',{
			text:'清除',
		    handler:function(target){
		    	$("#"+target.id).datetimebox('setValue','');
		    	$(target).datebox('hidePanel'); 
		    }
		});
		$("#starttime").datetimebox({
			editable:false,
		    buttons:buttons,
		    onChange:function(){
		    	$("#endtime").datetimebox('enableValidation') 
		    }
		});
		$("#endtime").datetimebox({
			editable:false,
		    buttons:buttons
		});
		$("#egpatrolengineerlist").datagrid({
			iconCls : 'icon icon-icon34',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'regularegpatrolengineer/egpatrolengineerlist.html',
			queryParams : {
				type : "0",
				typeDate : "0"
			},
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			onDblClickRow:function(index,row){
				showDialogWH("工程科分解任务","regularegpatrolengineer/egpatrolengineerInfoHide.html?id=" + row.id,550, 600);
				$("#egpatrolengineerlist").datagrid("reload");
			}
			/* onDblClickRow:function(index,row){
				showDialogWH("查看巡检计划信息","egpatrolengineer/egpatrolengineerInfo.html?id="
						+ row.id,480, 550);
			} */
		});

		/* $("#search").bind("click", function() {
			$("#egpatrolengineerlist").datagrid('unselectAll');
				$("#egpatrolengineerlist").datagrid("load", {
					starttime:$("#starttime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue")
				});
		}); */

		//tab
		$("#search").bind("click", function() {
			$("#egpatrolengineerlist").datagrid('unselectAll');
			var selectedTab = $('#tt').tabs('getSelected');
			var title = selectedTab.panel('options').title;
			var tabId = selectedTab.panel('options').id;
			if (tabId == "allData") {
				$("#egpatrolengineerlist").datagrid("load", {
					type : "0",
					typeDate : "1",
					//sender : $("#sender").textbox("getValue"),
					starttime:$("#starttime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue"),
				});
			} else if (tabId == "myTask") {
				$("#egpatrolengineerlist").datagrid("load", {
					type : "0",
					typeDate : "0",
					//sender : $("#sender").textbox("getValue"),
					starttime:$("#starttime").datetimebox("getValue"),
					endtime:$("#endtime").datetimebox("getValue"),
				});
			}

		});

		
		
		var p = $("#egpatrolengineerlist").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#send").bind("click",function() {
		 	var row = $("#egpatrolengineerlist").datagrid("getSelected");
			if (row == null) {
				//测试下发
				//showDialogWH("下发巡检计划信息","egpatrolengineer/egpatrolengineerInfo.html?id="+row.id,480, 550);
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				//return false;
			/* }else if(row.!="0") {
				$.messager.alert("操作提示", "该巡检计划已提交，不能编辑！", "error");
				return false; */
			}else {
				showDialogWH("工程科分解任务","regularegpatrolengineer/egpatrolengineerInfo.html?id=" + row.id,550, 650);
		  	}  
		});
		

	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="egpatrolengineerlist" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'id',width:150,hidden:true">ID</th>
				<th data-options="field:'code',width:150">签发单编号</th>
				<th data-options="field:'creater',width:150">签发人</th>
				<th data-options="field:'createtime',width:150">签发时间</th>
				<!-- <th data-options="field:'classes',width:140">类别</th> -->
				<th data-options="field:'content',width:150">签发内容</th>
				<th data-options="field:'fj',width:150">分解状态</th>
			</tr>
		</thead>
	</table>
	<div id="tb"
		style="padding: 2px 15px; text-align: left; vertical-align: middle">
		<!-- tab -->
		<div id="tt" class="easyui-tabs" style="">
			<div id="myTask" title="我的任务" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
			<div id="allData" title="全部数据"
				style="padding:20px;display:none;width: 100px;"></div>
			
		</div>
		
		<div>
		&nbsp;&nbsp;&nbsp;&nbsp;<a id="send" href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="false">分解</a>
		</div>
		<div class="cz_div">
			<!-- 发令人:&nbsp;&nbsp;<input id="sender" class="easyui-textbox"
				style="width: 120px" />&nbsp;&nbsp; --> 签发时间:&nbsp;&nbsp;<input
				id="starttime" type="text" data-options="width:160"
				class="easyui-datetimebox" /> ~ <input id="endtime" type="text"
				class="easyui-datetimebox"
				data-options="width:160,validType:['compareDate[starttime]']" />
			&nbsp;&nbsp; <a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="search()" id="search" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
