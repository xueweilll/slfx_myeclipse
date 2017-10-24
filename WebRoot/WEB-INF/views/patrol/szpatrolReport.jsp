<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>水政巡检上报</title>
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
			<input id="id"  style="width:300px" value="${szpatrol.getId()}" type="hidden"/>
			<table>
				<tr>
					<td><label for="sid">枢纽名称:</label></td>
					<td><input id="sid" style="width:300px"	value="${unit.getSid()}" /></td>
				</tr>
				<tr>
					<td><label for="patroltime">巡查时间:</label></td>
					<td><input id="patroltime" style="width:300px" class="easyui-datetimebox"					
					data-options="disabled:true,editable:false,required:true,prompt:'必选项'" value="${patroltime}" /></td>
				</tr>
				<tr>
					<td><label for="patroladdress">巡查地点或线路:</label></td>
					<td><input id="patroladdress" data-options="disabled:true" style="width:300px" class="easyui-textbox" value="${szpatrol.getPatroladdress()}" /></td>
				</tr>
				<tr>
					<td><label for="patrolcase">巡查情况:</label></td>
					<td><input id="patrolcase" style="width:300px" data-options="disabled:true" class="easyui-textbox" value="${szpatrol.getPatrolcase()}"/></td>
				</tr>
				<tr>
					<td><label for="patrolmemo">措施:</label></td>
					<td><input id="patrolmemo" data-options="disabled:true" style="width:300px"
				class="easyui-textbox" name="remark"
				value="${szpatrol.getPatrolmemo()}" /></td>
				</tr>
				<tr>
					<td><label for="szpatroluser">巡查人员:</label></td>
					<td><input id="szpatroluser" style="width:300px"
				class="easyui-textbox" name="remark" style=""
				data-options="" value="${unit.getMotertype()}" /></td>
				</tr>
			</table>
		</div>
		</br>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Report()">提交</a>&nbsp;&nbsp;<a id="cancel"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		var id=$('#id').val();
		var begin="1";
		$(function() {			
			$('#szpatroluser').combobox({
				editable:false,
				required:true,
				valueField:'userid',
				textField:'name',
				prompt:'必选项',
				multiple:true,
                panelHeight:'auto',
                disabled:true
			});
			$('#sid').combobox({
				editable:false,
				disabled:true,
				required:true,
				url:'szpatrol/sid.html',
				valueField:'id',
				textField:'name',
				prompt:'必选项',
				onChange:function(newValue, oldValue){
					var sid=newValue;
					$('#szpatroluser').combobox({
						url:'szpatrol/user.html?sid='+sid,
						onLoadSuccess:function(){
							if(id!="" && begin=="1"){
								$.ajax({
									type: "post",
									url:"szpatrol/findPatrolUser.html",
									data:{szid:id},
									dataType:"json",
									success:function(data){
										$('#szpatroluser').combobox('setValues',eval(data));
									},
									async:false
								});
								begin=2;
							}
						}
					});
					/* $.ajax({
						type: "post",
						url:"szpatrol/user.html",
						data:{sid:sid},
						dataType:"json",
						success:function(data){
							$('#szpatroluser').combobox({'loadData',data});
						},
						async:false
					}); */
				}
			});
			
			if(id!=""){
				$.ajax({
					type: "post",
					async:false,
					url:"szpatrol/findStationBySession.html",
					success:function(data){
						if(data!=""){
							$('#sid').combobox({
								disabled:true
							});
						}
					},
					dataType:"text"
				});
				$.ajax({
					type: "post",
					url:"szpatrol/findStationBySzid.html",
					data:{szid:id},
					dataType:"text",
					success:function(data){
						$('#sid').combobox('setValue',data);
					},
					async:false
				});
			}else{
				$.ajax({
					type: "post",
					url:"szpatrol/findStationBySession.html",
					async:false,
					dataType:"text",
					success:function(data){
						if(data!=""){
							$('#sid').combobox({
								disabled:true
							});
							$('#sid').combobox('setValue',data);																
						}
					}
				});
			}
			$("#cancel").bind("click", function() {
				$('#dialog').window('close');
			});
		});		
		function reflush() {
			document.getElementById('szpatrol.htmlifm').contentWindow.$('#szpatrol')
					.datagrid('reload');
		}		
		function Report(){
			var id=$('#id').val();
			$.post('szpatrol/report.html',{szid:id},function(msg){
				if(msg=="true"){
					$.messager.progress('close');
					reflush();
					$('#dialog').window('close');
				}
			});
		}
	</script>
</body>
</html>
