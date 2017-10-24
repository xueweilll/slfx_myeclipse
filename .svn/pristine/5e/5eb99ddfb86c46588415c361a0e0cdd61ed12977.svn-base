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
	<input id="userId" type="hidden" value="${user.getUserid()}" />
	<input id="actorId" type="hidden"
		value="${user.getActor().getId()}" />
	<input id="employeeId" type="hidden"
		value="${user.getEmployee().getId()}" />
	<input id="departmentId" type="hidden"
		value="${user.getEmployee().getDepartment().getId()}" />
	<form id="ffemp" method="post"
		style="margin: 10px; text-align: center;">
		<div >
			<label for="actorName">角色名称:</label> <select id="actorName"
				data-options="required:true,width:320">
			</select>
		</div>
		<div >
			<label for="departmentName">部门名称:</label> <select id="departmentName"
				data-options="required:true,width:320">
			</select>
		</div>
		<div >
			<label for="employeeName">员工名称:</label> <select id="employeeName"
				data-options="required:true,width:320" onclick="getEmployees()">
			</select>
		</div>
		<div >
			<label for="userName">用户名称:</label> <input id="userName" 
				class="easyui-textbox" name="userName"
				data-options="required:true,width:320,
				validType:['userName','length[2,20]','checkname[$(\'#userId\'),\'user/selectUser.html\']']"
				value="${user.getUsername()}"  />
		</div>
		<div >
			<label for="userEmail">用户邮箱:</label> <input id="userEmail" 
				class="easyui-textbox" name="userEmail" data-options="required:true,validType:'email',width:320"
				value="${user.getEmail()}" />
		</div>
		</br>
		<div id="btn">
			<div align="center" style="text-align: center;">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon_save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
				</div>
		</div>
	</form>
	<script type="text/javascript">
		function getEmployees(val){
			//var id=$("#departmentName").combobox("getValue");
			//console.info(val);
			if(val!=""){
				$('#employeeName').combobox('reload','user/employeesBind.html?id='+val+"&empid="+$("#employeeId").val());
				$('#employeeName').combobox('clear');
			}			
		}
		$(function(){
			$('#actorName').combobox({
			    url:'user/actorsBind.html',
			    valueField:'id',
			    textField:'name',
			    editable:false,
			    onLoadSuccess:function(node){
			    	if(node.length!=0){
			    		for (var i = 0; i < node.length; i++) {
				    		if(node[i].id==$("#actorId").val()){
				    			$("#actorName").combobox("setValue", $("#actorId").val());
				    		}
						}
			    	}
			    }
			});
			$('#departmentName').combotree({
			    url:'department/departmentsBind.html',
			    valueField:'id',
			    textField:'name',
			    editable:false,
			    onSelect:function(node){
			    	if(node.id==0){
			    		$.messager.alert("操作提示", "部门树根节点无法被选择！", "error");
			    		$('#departmentName').combotree('clear');	
					}else{
				    	getEmployees(node.id);
					}
			    },
			    onLoadSuccess:function(node,data){
			    	var departmentid=$("#departmentId").val();
			    	var tree=$('#departmentName').combotree("tree");
			    	var node= $(tree).tree('find', departmentid);
			    	if(node!=null){
			    		$("#departmentName").combotree("setValue", departmentid);
				    	getEmployees(departmentid);
			    	}
			    }
			});
			$('#employeeName').combobox({
			    valueField:'id',
			    textField:'name',
			    editable:false,
			    onLoadSuccess:function(node){
			    	if(node.length!=0){
				    	for (var i = 0; i < node.length; i++) {
				    		if(node[i].id==$("#employeeId").val()){
				    			$("#employeeName").combobox("setValue", $("#employeeId").val());
				    		}
						}
			    	}
			    } 
			});
			$("#sub").bind("click",
					function() {
						$.messager.progress();
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data = {
							"userid" : ($("#userId").val() == "" ? 0 : $(
									"#userId").val()),
							"actor" : {"id":$("#actorName").combobox("getValue")},
							"employee" : {"id":$("#employeeName").combobox("getValue")},
							"username":$("#userName").textbox("getValue"),
							"email":$("#userEmail").textbox("getValue")
						};
						$.ajax({
							type : "POST",
							url : "user/save.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data)
							},
							success : function(msg) {
								//alert(msg);
								data = eval('(' + msg + ')');
								$.messager.progress('close');
								if(data.result){reflush();}
								else{$.messager.alert("操作提示",data.msg,"error");}
								$('#dialog').window('close');
							}
						});
					});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('user.htmlifm').contentWindow.$('#user_list')
					.datagrid('reload');
			document.getElementById('user.htmlifm').contentWindow.$('#user_list')
			.datagrid('unselectAll');
		}
		
		$.extend(
				$.fn.validatebox.defaults.rules.remote.message="用户名已存在,请重新输入！"
		);
	</script>
</body>
</html>
