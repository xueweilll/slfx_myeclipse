<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户报警关系</title>
<%@include file="../header.jsp"%>
<style type="text/css">
	#tb{
		margin-left: 20px;
	}
</style>
<script type="text/javascript">
	$(function() {
		$("#alarmShip_list").datagrid({
			title : '报警枢纽列表',
			iconCls : 'icon icon-icon5',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'alarmShip/bind.html',
			remoteSort : false,
			idField : 'code',
			pagination : true,
			rownumbers : true,
			onLoadSuccess:function(){
				$.post("alarmShip/bindShip.html",function(data){
					if(data==""){
						return false;
					}
					var codes = data.split(",");
					for(var i=0;i<codes.length;i++){
						//alert(codes[i]);
						var rowindex=$("#alarmShip_list").datagrid("getRowIndex",codes[i]);
						$("#alarmShip_list").datagrid("checkRow",rowindex);
					}
				});
			}
		});
		

		$("#btnSave").click(function() {
			var rows = $("#alarmShip_list").datagrid("getChecked");
			//console.info(rows);
			var codes = "";
			for (var i = 0; i < rows.length; i++) {
				//alert(rows[i].code);
				codes += rows[i].code;
				if (codes != "") {
					codes += ","
				}
			}
			$.post("alarmShip/save.html",
			{codes:codes},
			function(msg) {
				//alert(msg);
				var data = eval('(' + msg + ')');
				//alert(data);
				if(data.result){
					$.messager.alert('提示',data.msg,'info');  
				}
			});
		});
		
		$("#btnClear").click(function() {
			$("#alarmShip_list").datagrid("clearChecked");
			var rows = $("#alarmShip_list").datagrid("getChecked");
			//console.info(rows);
			var codes = "";
			for (var i = 0; i < rows.length; i++) {
				//alert(rows[i].code);
				codes += rows[i].code;
				if (codes != "") {
					codes += ","
				}
			}
			$.post("alarmShip/save.html",
			{codes:codes},
			function(msg) {
				//alert(msg);
				var data = eval('(' + msg + ')');
				//alert(data);
				if(data.result){
					$.messager.alert('提示',data.msg,'info');  
				}
			});
		})
	})
</script>
</head>
<body class="easyui-layout" id="cc">
	<table id="alarmShip_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'code',checkbox:true">选择</th>
				<th data-options="field:'name',width:120">枢纽名称</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
	&nbsp;&nbsp;&nbsp;
		<a id="btnSave" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-add'">布防</a>
			&nbsp;&nbsp;&nbsp;
			<a id="btnClear" class="easyui-linkbutton"
			data-options="plain:false,iconCls:'icon-clear'">撤防</a>
	</div>
</body>
</html>