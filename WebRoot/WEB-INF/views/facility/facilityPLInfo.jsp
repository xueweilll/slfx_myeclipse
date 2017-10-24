<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'facilityErrorInfo.jsp' starting page</title>

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
	<input id="" type="hidden" value="${user.getId()}" />
	<input id="" type="hidden"
		value="${user.getPower().getId()}" />
	<input id="" type="hidden"
		value="${user.getEmployee().getId()}" />
	<form id="ffemp" method="post"
		style="margin-top: 10px; text-align: center;">
		<div>
			<label for="powerName">地区名称:</label> 
			<select class="easyui-combobox" name="state" id="userName" style="width:305px;">
		        <option value="AL">新北区</option>
		        <option value="AK">钟楼区</option>
		        <option value="AZ">天宁区</option>
		        <option value="AZ">武进区</option>       
		    </select>
			<label for="powerName">泵站名称:</label> 
			<select class="easyui-combobox" name="state" id="powerName" style="width:305px;">
		        <option >仙水泵站</option>
		        <option value="AK">海泰泵站</option>
		        <option value="AZ">龙江泵站</option>              
		    </select>
		</div>
		<div>
			<label for="employeeName">设备类型:</label>
			<select class="easyui-combobox" name="state" id="brand" style="width:305px;">
		        <option >螺旋泵</option>
		        <option >真空泵</option>
		        <option >离心泵</option>
		        <option >热水泵</option>
		    </select>
		</div>
		<div>
			<label for="userName">损益情况:</label> 
			<select class="easyui-combobox" name="state" id="brand" style="width:305px;">
		        <option >进</option>
		        <option >出</option>		               
		    </select>
		</div>
		<div>
			<label for="remark">损益数量:</label> <input id="remark"
				class="easyui-textbox" name="remark"
				data-options="" value="${user.getRemark()}" />
		</div>
		<div>
			<label for="remark">损益日期:</label> <input id="remark"
				class="easyui-datebox" name="remark" style=""
				data-options="" value="${user.getRemark()}" />
		</div>						
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$('#powerName').combobox({
			    url:'power/power_data.html',
			    valueField:'powerId',
			    textField:'powerName',
			    value:$("#powerId").val() //默认选中value指定的选项
			});
			$('#employeeName').combobox({
			    url:'employee/emp_data.html',
			    valueField:'employeeId',
			    textField:'empName',
			    value:$("#employeeId").val() //默认选中value指定的选项
			});
			$("#powerName").combobox({editable:false});
			$("#employeeName").combobox({editable:false});
			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data = {
							"id" : ($("#userId").val() == "" ? 0 : $(
									"#userId").val()),
							"power" : {"id":$("#powerName").combobox("getValue")},
							"employee" : {"id":$("#employeeName").combobox("getValue")},
							"username":$("#userName").textbox("getValue"),
							"remark":$("#remark").textbox("getValue")
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
		}
	</script>
</body>
</html>
