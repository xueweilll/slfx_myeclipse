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
    <title>部门管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	var id = 0;
	var pareId = 0;
	var url = "";
	$(function() {
		$("#btn").hide();
		$("#tt").tree({
			/* url : 'organization/bind.html', */
			url : 'department/departmentsBind.html',
			method:'POST',
			onClick : function(node) {
				$("#status").textbox("setValue", "显示");
				$("#orgphone").textbox({disabled:true});
				$("#orgname").textbox({disabled:true});
				$("#remark").textbox({disabled:true});
				$("#btn").hide();
				id = node.id;
				pareId = node.pareId;
				$("#ff").form('load', {
					name : node.text,
					telephone : node.obj.telephone,
					remark : node.obj.remark
				});
			}
		});

		$("#sub").bind('click', function() {
			submit();
			// $("#ff").submit();
		});

		$("#can").bind('click', function() {
			$("#orgphone").textbox("clear");
			$("#orgname").textbox("clear");
			$("#remark").textbox("clear");
		});
	});

	function treeBind() {
		$("#tt").tree('reload');
	}

	function submit() {
		if (isDepartment()) {
			return false;
		}
		$.messager.progress();
		var isValid = $("#ff").form('validate');
		if (!isValid) {
			$.messager.progress('close');
			return false;
		}
		var data = {
				"id":id,
				"telephone" : $("#orgphone").textbox("getValue"),
				"remark" : $("#remark").textbox("getValue").replace(/\s+/g,""),
				"name" : $("#orgname").textbox("getValue"),
				"pareid":pareId
		};
		$.ajax({
			type : "POST",
			url : url,
			dataType : "text",
			data : {
				"jsonStr" : JSON.stringify(data)
			},
			success : function(data) {
				$.messager.progress('close');
				data = eval('('+data+')');
				if(data.result){
					treeBind();
					$.messager.alert('操作提示','操作成功！','info');
				}else{
					$.messager.alert('操作提示','操作失败！','info');
				}
			}
		});
	}

	function add() {
		$("#status").textbox("setValue", "添加");
		$("#orgphone").textbox({disabled:false});
		$("#orgname").textbox({disabled:false});
		$("#remark").textbox({disabled:false});
		$("#btn").show();
		url = "department/save.html";
		pareId = id;
		$("#departmentid").val(0);
		$("#ff").form('load', {
			name : "",
			telephone : "",
			remark : ""
		});
	}

	function edit() {
		if (isDepartment()) {
			return false;
		}
		$("#departmentid").val(id);
		$("#status").textbox("setValue", "编辑");
		$("#orgphone").textbox({disabled:false});
		$("#orgname").textbox({disabled:false});
		$("#remark").textbox({disabled:false});
		$("#btn").show();
		url = "department/save.html";
	}

	function isDepartment() {
		if (id == 0 && url != "department/save.html") {
			$.messager.alert("操作提示", "部门树根节点无法被增删改！", "error");
			return true;
		} else {
			return false;
		}
	}

	function distroy() {
		url = "department/delete.html";
		if (isDepartment()) {
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {
			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : url,
					data : {
						"id":id
					},
					success : function() {
						$.messager.progress('close');
						treeBind();
						$.messager.alert('操作提示','操作成功','info');
					}
				});
			}
		});
	}
</script>
  </head>
  
  <body class="easyui-layout" id="cc">
  <input type="hidden" id="departmentid">
	<div
		data-options="region:'west',title:'部门树形列表',split:true,iconCls:'icon icon-icon4',tools:'#tl'"
		style="padding: 5px; width: 250px">
		<ul id="tt"></ul>
	</div>
	<div id="tl">
		<a href="javascript:void(0)" class="icon_add" onclick="add()"></a> <a
			href="javascript:void(0)" class="icon_edit" onclick="edit()"></a>
		<a href="javascript:void(0)" class="icon_delete" onclick="distroy()"></a>
	</div>
	<div
		data-options="region:'center',title:'部门编辑',iconCls:'icon icon-icon4'"
		style="padding: 25px; background: #eee">
		<form id="ff" method="post">
			<table cellpadding="0" cellspacing="5" border="0" align="center">
				<tr>
					<td><label for="status">显示操作:</label></td>
					<td><input class="easyui-textbox" disabled="disabled" type="text" name="status"
						id="status" /></td>
				</tr>
				<tr>
					<td><label for="orgname">部门名称:</label></td>
					<td><input class="easyui-textbox" type="text" name="name"
						id="orgname" data-options="disabled:true,required:true,validType:['length[1,10]','chinese','checkname[$(\'#departmentid\'),\'department/selectDepartmentName.html\']']" /></td>
				</tr>
				<tr>
					<td><label for="orgphone">部门电话:</label></td>
					<td><input class="easyui-textbox" id="orgphone" type="text"
						name="telephone" data-options="disabled:true,required:true,validType:['phone']" /></td>
				</tr>
				<tr>
					<td><label for="orgremark">部门备注:</label></td>
					<td><input class="easyui-textbox" type="text" id="remark"
						name="remark" style="height: 60px"
						data-options="disabled:true,multiline:true,validType:'length[0,2000]'" /></td>
				</tr>
			</table>
		</form>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon_save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</div>
</body>
</html>
