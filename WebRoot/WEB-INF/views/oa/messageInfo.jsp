<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<input id="messageId" type="hidden" value="${message.getId()}" />
	<input id="userId" type="hidden"
		value="${message.receiver().getId()}" />          <!--  得到谁的id-->
	<form id="ffemp" method="post"
		style="margin-top: 10px; text-align: center;">
		<div>
			<label for="content">内容:</label> <input id="content"
				class="easyui-textbox" name="content" style="height:100px;"
				data-options="width: 305,multiline:true,required:true" value="${message.getContent()}" />
		</div>
		<div>
			<label for="receiver">接收人:</label> <input id="receiver" 
				data-options="width: 305,required:true" value="${message.getReceiver()}">
		</div>
		<div>
			<label for="receiverType">发送方式:</label> 
			<input id="receiverType"
				class="easyui-combobox" 
				data-options="width: 305,required:true" value="${message.getReceiver()}">  
		</div>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">发送</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$('#receiver').combogrid({    
			    panelWidth:305,    
			    //value:'006',    
			    idField:'id',    
			    textField:'ename',    
			    url:'message/userBind.html',
			    multiple:true,
			    frozenColumns:[[{field:'ck',checkbox:true}]],
			    editable:false,
			    columns:[[    
			        {field:'dname',title:'部门',width:75},
			        {field:'ename',title:'用户名',width:75},    
			        {field:'ephone',title:'手机号码',width:100} 
			    ]]    
			});
			$('#receiverType').combobox({    
			    url:'paramers/receiverType.html',    
			    valueField:'id',    
			    textField:'text',
			    editable:false,
			    multiple:true,
			    value:'1'
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
						var data;
						data = ($("#messageId").val() == "" ? 0 : $("#messageId").val())+"/"
								+$("#receiver").combogrid("getValues")+"/"+$("#content").textbox("getValue").replace(/\s+/g,"")+"/"
								+$("#receiverType").combobox("getValues");
						$.ajax({
							type : "POST",
							url : "message/save.html",
							dataType : "text",
							data : {
								"jsonStr" : data
							},
							success : function(msg){
										//alert(msg);
										data = eval('(' + msg + ')');
										$.messager.progress('close');
										if (data.result) {
											reflush();
											$("#dialog").dialog("close");
										} else {
											$.messager.alert("操作提示", data.msg,
													"error");
										}
									}
								});
					});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('message.htmlifm').contentWindow.$(
					'#message_list').datagrid('reload');
			document.getElementById('message.htmlifm').contentWindow.$('#message_list')
			.datagrid('unselectAll');
		}
	</script>
</body>
</html>
