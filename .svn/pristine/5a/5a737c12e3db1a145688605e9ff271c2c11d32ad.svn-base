<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>My JSP 'paramers.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>参数设定</title>
<script type="text/javascript">
/**
 * 
 */
var editIndex = undefined;
function endEditing(){
	if(editIndex == undefined){
		return true;
	}
	if($("#paramers_list").datagrid('validateRow',editIndex)){
		//var ed = $("#paramers_list").datagrid('getEditor',{index:editIndex,field:'paramersId'});
		//var paramersValues = $(ed.target).textbox('getValue');
		//$("#paramers_list").datagrid('getRows')[editIndex]['paramersValues']=paramersValues;
		$("#paramers_list").datagrid('endEdit',editIndex);
		editIndex=undefined;
		return true;
	}else{
		return false;
	}
}

function onClickRow(index){
	if(editIndex!=index){
		if(endEditing()){
			$("#paramers_list").datagrid('selectRow',index).datagrid('beginEdit',index);
			editIndex=index;
		}else{
			$("#paramers_list").datagrid('selectRow',editIndex);
		}
	}
}

function accept(){
	if (endEditing()){
		$.messager.progress();
		var rows = $('#paramers_list').datagrid('getChanges');
		 //alert(JSON.stringify(rows));
		$.ajax({
			type : "POST",
			url : "paramers/save",
			dataType : "text",
			data : {
				"strJson" : JSON.stringify(rows)
			},
			success : function() {
				$.messager.progress('close');
				$("#paramers_list").datagrid('reload');
			}
		});
		$('#paramers_list').datagrid('acceptChanges');
	}
}

function reject(){
	$.messager.progress();
	$('#paramers_list').datagrid('rejectChanges');
	editIndex = undefined;
	$.ajax({
		type : "POST",
		url : "paramers/recover",
		success : function() {
			$.messager.progress('close');
			$("#paramers_list").datagrid('reload');
		}
	});
}

</script>
</head>
<body class="easyui-layout" id="cc">
	<table id="paramers_list" title="参数列表" class="easyui-datagrid"
		data-options="
				iconCls: 'icon icon-paramer',
				singleSelect: true,
				url: 'paramers/paramers_data',
				toolbar: '#tb',
				method: 'get',
				rownumbers:true,
				width:'auto',
				height:'100%',
				striped:true,
				idField:'paramersId',
				border:true,
				onClickRow: onClickRow
			">
		<thead>
			<tr>
				<th data-options="field:'paramersKeys',width:150">键</th>
				<th data-options="field:'paramersValues',width:150,editor:'textbox'">值</th>
				<th data-options="field:'defaultValues',width:150">默认值</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<td>地图刷新时间</td>
			<td>5000</td>
			<td>5000</td>
			</tr>
			<tr>
			<td>数据采集时间</td>
			<td>5000</td>
			<td>5000</td>
			</tr>
			<tr>
			<td>数据备份时间</td>
			<td>23:59:59</td>
			<td>23:59:59</td>
			</tr>
			<tr>
			<td>上班时间</td>
			<td>09:00:00</td>
			<td>09:00:00</td>
			</tr>
			<tr>
			<td>下班时间</td>
			<td>17:00:00</td>
			<td>17:00:00</td>
			</tr>
			<tr>
			<td>GPS监听端口</td>
			<td>6118</td>
			<td>6118</td>
			</tr>
			<tr>
			<td>数据采集端口</td>
			<td>6119</td>
			<td>6119</td>
			</tr>
		</tbody>
	</table>

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			data-options="iconCls:'icon-undo',plain:true" onclick="reject()">恢复默认</a>
	</div>
</body>
</html>