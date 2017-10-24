<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'facilityInfo.jsp' starting page</title>

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
	<form id="ffemp" method="post"
		style="margin-top: 10px; text-align: center;">
		<div>			
			<input id="id" type="hidden" value="${camera.getId()}" />
			<table>
				<tr>
					<td><label for="sid">枢纽名称:</label></td>
					<td><input id="sid"
				data-options="prompt:'必选项'"
				value="${camera.getSid()}" /></td>
				</tr>
				<tr>
					<td><label for="code">摄像头编号:</label></td>
					<td><input id="code" value="${camera.getCode()}" /></td>
				</tr>
				<tr>
					<td><label for="name">摄像头名称:</label></td>
					<td><input id="name" value="${camera.getName()}" /></td>
				</tr>
				<tr>
				    <td><label for="channel">通道号:</label></td>
				    <td><input id="channel" class="easyui-textbox" data-options="validType:'integer'" value="${camera.getChannel()}"/></td>
				</tr>
				<tr>
					<td><label for="url">视频URL地址:</label></td>
					<td><input id="url"
				class="easyui-textbox" name="url" 
				value="${camera.getUrl()}" /></td>
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
			var id=$("#id").val();			
			$('#code').textbox({
				/* disabled:true, */
				prompt:'必填项',
				required:true,
				events:{
					blur:function(){
						var sid=$('#sid').combobox('getValue');
						if(sid==""){
							alert("枢纽名称不能为空");
							$('#code').textbox('setText','');
							return;
						}
						/*新增  */
						if(id==""){
							$.post(
								'camera/codeOnlyOne.html',
								{
									code:$('#code').val(),
									sid:$("#sid").combobox("getValue")
								},
								function(msg){
									if(msg.msg==true){
										alert("摄像头编号已存在，请重新输入");
										$('#code').textbox('setText','');
										return;
									}
								},'json');							
						/*编辑  */	
						}else{
							$.post(
							'camera/codeOnlyOne.html',
							{
								code:$('#code').val(),
								id:id,
								"sid":$("#sid").combobox("getValue")
							},
							function(msg){															
								if(msg.msg==true){																
									alert("摄像头编号已存在，请重新输入");
									$('#code').textbox('setText','');
									return;
								}
							},'json');
						}
					}
				}
			});
			/* if(id==""){
				$.post('dispatchinstructions/findCodeByPref.html',{
					pref:'Camera'
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
							$('#name').textbox('setText','');
							return;
						}
						if(id==""){
							$.post('camera/cameraOnlyOne.html',
							{
								"camera":$('#name').val(),
								"sid":$("#sid").combobox("getValue")
							},
							function(msg){
								if(msg=="true"){
									alert("摄像头名称已存在，请重新输入");
									$('#name').textbox('setText','');
									return;
								}
							});
						}else{
							$.post('camera/cameraOnlyOne.html',
							{"camera":$('#name').val(),
							 "id":id,
							 "sid":$("#sid").combobox("getValue")
							},
							function(msg){
								if(msg=="true"){
									alert("摄像头名称已存在，请重新输入");
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
				url:'camera/sid.html'
			});
								
			$("#cancel").bind("click", function() {
				$("#ffemp").form("clear");
			});						
		});	
		function reflush() {
			document.getElementById('camera.htmlifm').contentWindow.$('#camera')
					.datagrid('reload');
		}
		function Save(){
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				return false;
			}
			if($("#channel").textbox("getText")=="" && $("#url").textbox("getText") ==""){
				alert("通道号与url地址不能同时为空！");
				return false;
			}
			var data={
				"id" : ($("#id").val()==""?0:$("#id").val()),
				"sid" : $("#sid").combobox("getValue"),
				"code" : $("#code").textbox("getText"),
				"name" : $("#name").textbox("getText"),
				"url" : $("#url").textbox("getText"),
				"channel":$("#channel").textbox("getText"),
				"isdel" : "0"
			};
			$.ajax({
				type:"POST",
				url:"camera/save.html",
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
