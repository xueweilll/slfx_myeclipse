<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>机组信息</title>

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
	<form id="ffemp" method="post" style="margin:10px; text-align: center;">
		<div>			
			<input id="id"  style="width:300px" value="${unit.getId()}" type="hidden"/>
			<table>
				<tr>
					<td><label for="sid">枢纽名称:</label></td>
					<td><input id="sid" style="width:300px"	value="${unit.getSid()}" /></td>
				</tr>
				<tr>
					<td><label for="code">机组编号:</label></td>
					<td><input id="code" style="width:300px" value="${unit.getCode()}" /></td>
				</tr>
				<tr>
					<td><label for="name">机组名称:</label></td>
					<td><input id="name" style="width:300px" name="brand" value="${unit.getName()}" /></td>
				</tr>
				<tr>
					<td><label for="types">水泵类型:</label></td>
					<td><input id="types" style="width:300px" value="${unit.getTypes()}"/></td>
				</tr>
				<tr>
					<td><label for="power">水泵型号:</label></td>
					<td><input id="power" style="width:300px"
				class="easyui-textbox" name="power"
				 value="${unit.getPower()}" /></td>
				</tr>
				<tr>
					<td><label for="motertype">电机型号:</label></td>
					<td><input id="motertype" style="width:300px"
				class="easyui-textbox" name="remark" style=""
				data-options="" value="${unit.getMotertype()}" /></td>
				</tr>
				<tr>
					<td><label for="feature">泵站功能:</label></td>
					<td><input id="feature" style="width:300px" value="${unit.getFeature()}"/></td>
				</tr>
				<tr>
					<td><label for="designdischarge">设计流量:</label></td>
					<td><input id="designdischarge"
				class="easyui-textbox" name="remark" style="width:300px"
				data-options="required:true,prompt:'请输入数字',validType:['intOrFloat','length[1,8]']" value="${unit.getDesigndischarge()}" /></td>
				</tr>				
			</table>			 			 			 						 
		</div>	
		</br>			
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save()">保存</a>&nbsp;&nbsp;<a id="cancel"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			var id=$('#id').val();
			$('#code').textbox({
				required:true,
				/* disabled:true, */
				validType:'length[0,20]',
				prompt:'长度在20个字母或汉字之内',
				 events:{
					blur:function(){
						var sid=$('#sid').combobox('getValue');
						if(sid==""){
							alert("枢纽名称不能为空");
							$('#code').textbox('setText','');
							return;
						}
						if(id==""){
							$.post(
							'unit/codeOnlyOne.html',
									{
								code:$('#code').val(),
								"sid":$("#sid").combobox("getValue")
									},
							function(msg){															
								if(msg.msg==true){																
									alert("机组编号已存在，请重新输入");
									$('#code').textbox('setText','');
									return;
								}
							},'json');														
						}else{
							$.post(
							'unit/codeOnlyOne.html',
							{
								code:$('#code').val(),
								id:id,
								"sid":$("#sid").combobox("getValue")
							},
							function(msg){															
								if(msg.msg==true){																
									alert("机组编号已存在，请重新输入");
									$('#code').textbox('setText','');
									return;
								}
							},'json');
							/* $.post(
							'unit/codeOnlyOne.html?code='+newvalue,
							function(msg){															
								if(msg=="true"){																
									alert("编号已存在，请重新输入");
									return;
								}
							}) */
						}
					}				
				} 								
			});
			/* if(id==""){
				$.post('dispatchinstructions/findCodeByPref.html',{
					pref:'Unit'
				},function(msg){
					$('#code').textbox('setText',msg);
				})
			} */
			$('#name').textbox({
				required:true,
				validType:'length[0,20]',
				prompt:'长度在20个字母或汉字之内',
				events:{
					blur:function(){
						var sid=$('#sid').combobox('getValue');
						if(sid==""){
							alert("枢纽名称不能为空");
							$('#code').textbox('setText','');
							return;
						}
						if(id==""){
							$.post('unit/nameOnlyOne.html',
									{
								name:$('#name').val(),							
								sid:$("#sid").combobox("getValue")	
									},
								function(aaa){
									if(aaa=="true"){
										alert("机组名称已存在，请重新输入");
										$('#name').textbox('setText','');
										return;
									}
							});
						}else{
							$.post('unit/nameOnlyOne.html',
									{
								name:$('#name').val(),
								id:id,
								sid:$("#sid").combobox("getValue")							
									},
									function(aaa){
										if(aaa=="true"){
											alert("机组名称已存在，请重新输入");
											$('#name').textbox('setText','');
											return;
										}
								});
						}
					}
				}
			});
			$('#sid').combobox({
				editable:false,
				required:true,
				valueField:'id',
				textField:'name',
				url:'unit/sid2.html',
				prompt:'必选项'
			});
			$('#types').combobox({
				editable:false,
				required:true,
				valueField:'id',
				textField:'text',
				url:'paramers/types.html',
				prompt:'必选项'
			});
			$('#feature').combobox({
				editable:false,
				required:true,
				valueField:'id',
				textField:'text',
				url:'paramers/featureData.html',
				prompt:'必选项'
			});
			
			$("#cancel").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});	
		function reflush() {
			document.getElementById('unit.htmlifm').contentWindow.$('#unit')
					.datagrid('reload');
		}
		function Save(){			
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				return false;
			}
			var data={				
				"id" : $("#id").val()==""?"0":$("#id").val(),
				"sid" : $("#sid").combobox("getValue"),
				"code" : $("#code").textbox("getText"),
				"name" : $("#name").textbox("getText"),
				"types" : $("#types").combobox("getValue"),
				"power" : $("#power").textbox("getText"),
				"motertype" : $("#motertype").textbox("getText"),
				"feature" : $("#feature").combobox("getValue"),
				"designdischarge":$("#designdischarge").textbox("getText"),				
				"isdel" : "0"
			};
			$.ajax({
				type:"POST",
				url:"unit/save.html",
				dataType:"text",
				data : {
					"json" : JSON.stringify(data)
				},
				success:function(msg){					
					$.messager.progress('close');
					reflush();
					$('#dialog').window('close');
				}
			});
		}			
	</script>
</body>
</html>
