<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userInfo.jsp' starting page</title>

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
<input type="hidden" id="pid" value="${pid}">
		<div style="float: left;">
		<form action="" id="ffemp">
		<table id="task_list" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th data-options="field:'name',width:120">节点名称</th>
					<th data-options="field:'users',width:160">人员配置</th>
				</tr>
			</thead>
		</table>
		</form>
			<div id="btn" style="text-align: center;float: ">
					<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
						href="javascript:void(0)" class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>
		</br>
	<script type="text/javascript">
		$(function(){
			$("#task_list").datagrid({
				title : '人员配置',
				width : '500',
				height : '300',
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				url : 'workFlow/taskBind.html',
				remoteSort : false,
				idField : 'id',
				singleSelect : true,
				queryParams: {
					id: $("#pid").val()
				},
				sortOrder:'asc',
				sortName:'orderby',
				rownumbers : true,
				columns:[[    
				          {field:'name',title:'节点名称',width:220},    
				          {field:'orderby',hidden:true},    
				          {field:'users',title:'人员配置',width:220,
				        	  formatter: function(val,row){
				        		  return '<input name="receiver" id="'+row.id+'" taskid="'+row.id+'" value="'+val+'" style="width: 150px;" >'; 
							}
						   }	  
				      ]],
				onLoadSuccess:function(){
					$.post("workFlow/userBind.html",function(data){
						$('input[name=receiver]').combogrid({    
						    panelWidth:305,    
						    //value:'006',    
						    idField:'id',    
						    textField:'ename',    
						    data:data,
						    multiple:true,
						    required:true,
						    frozenColumns:[[{field:'ck',checkbox:true}]],
						    editable:false,
						    columns:[[    
						        {field:'dname',title:'部门',width:75},
						        {field:'ename',title:'人员名称',width:75},    
						        {field:'ephone',title:'所属枢纽',width:100} 
						    ]]    
						});
					},"json");
				}
			});
			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var rows=$("#task_list").datagrid("getRows");
						var data="";
						for (var i = 0; i < rows.length; i++) {
							var str=$("#"+rows[i].id).combogrid("getValues");
							data =rows[i].id+","+str+"/"+data;
						}
						$.ajax({
							type : "POST",
							url : "workFlow/saveTaskUser.html",
							dataType : "text",
							data : {
								"jsonStr" : data,
								pid:$("#pid").val()
							},
							success : function(msg) {
								//alert(msg);
								data = eval('(' + msg + ')');
								$.messager.progress('close');
								if(data.result){$.messager.alert("操作提示","添加成功","info");}
								else{$.messager.alert("操作提示",data.msg,"error");}
								$('#dialog').window('close');
							}
						});
						
			});
			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});
	</script>
</body>
</html>
