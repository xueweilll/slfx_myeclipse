<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'patrolplaninfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="id" type="hidden" value="${patrolPlan.getId()}"/>
	<form id="patrolplaninfo" method="post"
		style="margin:10px; text-align: center;">
		<div>
			<label for="code">巡检计划单号:</label> <input id="code"
				class="easyui-textbox" name="code" style="width: 320px;"
				data-options="disabled:true" value="${patrolPlan.getCode()}"/>
		</div>
		<div>
			<label for="excepttime">巡检预计时间:</label> <input id="excepttime" 
				class="easyui-datetimebox" style="width: 320px;" name="excepttime"
				data-options="required:true,editable:false" value="${excepttime}"/>
			</td>
		</div>
		<div>
			<label for="sender">发&nbsp;&nbsp;&nbsp;&nbsp;令&nbsp;&nbsp;&nbsp;&nbsp;人&nbsp;:</label> <input id="sender"
				style="width: 320px;" data-options="required:true" value="${patrolPlan.getSender()}"/>
		</div>
		<div>
			<label for="memo">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;:</label> <input id="memo" class="easyui-textbox"
				name="memo" style="width:320px;height:100px;"
				data-options="multiline:true" data-options="required:true" value="${patrolPlan.getMemo()}"/>
		</div>
		</br>
		<div
			style="width: 440px;height: 220px;">
			<table id="stations"></table>
		</div>
			</br>
		<div id="btn" style="text-align: center">
			
		</div>
	</form>

	<script type="text/javascript">
		var editIndex1 = undefined;
		var patrolPlanID = document.getElementById("id").value;
		//var executeid = $("#executeid").val() == "" ? "0" : $("#executeid").val();
		$(function() {
			$("#memo").textbox({
				required : true,
				showSeconds : true,
				missingMessage : "该输入项为必输项"
			});
			
			/*$('#sender').combobox({
				url : 'document/getUsername.html',
				idField : 'id',
				textField : 'username',
				required : true,
				editable : false,
				multiple : true,
				singleSelect : true,
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				columns : [ [ {
					field : 'dname',
					title : '部门',
					width : 145
				}, {
					field : 'username',
					title : '用户名',
					width : 140
				} ] ],
				fitColumns : true
			});*/
			
			$("#sender").combobox({
				url : 'patrolplan/userList.html',
				valueField : 'id',
				textField : 'username',
				editable : false,
				value : $("#sender").val(),
				required : true
			});
			
			
			$('#stations').datagrid({
				url:'patrolplan/findStationBypatrolPlanID.html?patrolPlanID='+patrolPlanID,
				columns : [ [ {
					field : 'station',
					title : '枢纽名称',
					width : 150,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'post',
							url : 'patrolplan/findStations.html',
							required : true,
							editable : false,
							onSelect:function(record){
								
								var rows = $('#stations').datagrid('getRows');
								for (var i = 0; i < rows.length; i++) {
									if(rows[i].stationid==record.id){
										alert("枢纽已存在！");
										$(this).combobox('setValue','');
										return false;
										break;
									}
								}
								
								var ed1 = $('#stations').datagrid('getEditor',
										{
											index : editIndex1,
											field : 'stationid'
										});
									$(ed1.target).textbox('setText',record.id);
							}
						}
					}
				},{
					field : 'stationid',
					title : '枢纽id',
					width : 250,
					hidden:true,														
					editor : {
						type : 'textbox',
						options : {
							editable : false
						}
					}
				}]],
				width : 'auto',
				height : 'auto',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				toolbar : [ {
					text : '添加计划枢纽信息',
					iconCls : 'icon-add',
					handler : append1
				}, {
					text : '删除计划枢纽信息',
					iconCls : 'icon-cut',
					handler : removeit1
				} ],
				iconCls : 'icon-edit',
				onClickCell : onClickCell1,
				onEndEdit : onEndEdit1
			});
			
			function endEditing1() {
				if (editIndex1 == undefined) {
					return true;
				}
				if ($('#stations').datagrid('validateRow', editIndex1)) {
					$('#stations').datagrid('endEdit', editIndex1);
					editIndex1 = undefined;
					return true;
				} else {
					return false;
				}
			}

			function onClickCell1(index, field) {
				if (editIndex1 != index) {
					if (endEditing1()) {
						$('#stations').datagrid('selectRow', index).datagrid(
								'beginEdit', index);
						var ed = $('#stations').datagrid('getEditor', {
							index : index,
							field : field
						});
						if (ed) {
							($(ed.target).data('textbox') ? $(ed.target).textbox(
									'textbox') : $(ed.target)).focus();
						}
						editIndex1 = index;
					} else {
						setTimeout(function() {
							$('#stations').datagrid('selectRow', editIndex1);
						}, 0);
					}
				}
			}

			function onEndEdit1(index, row) {
				var ed1 = $(this).datagrid('getEditor', {
					index : index,
					field : 'station'
				});
				row.station = $(ed1.target).combobox('getText');
			}

			function append1() {
				if (endEditing1()) {
					$('#stations').datagrid('appendRow', {});
					editIndex1 = $('#stations').datagrid('getRows').length - 1;
					$('#stations').datagrid('selectRow', editIndex1).datagrid(
							'beginEdit', editIndex1);
				}
			}

			function removeit1() {
				if (editIndex1 == undefined) {
					return;
				}
				$('#stations').datagrid('cancelEdit', editIndex1).datagrid(
						'deleteRow', editIndex1);

				editIndex1 = undefined;
			}
			
			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#patrolplaninfo").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							alert("必填项不能为空");
							return;
						}
						
						var station = $('#stations').datagrid('getRows');			
						if (station.length == 0) {
							$.messager.progress('close');
							alert("计划枢纽信息不能为空");
							return;
						}
						
						var us="";
						if(endEditing1()){
							var rows=$('#stations').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								var station=rows[i].station+",";
								us += station;
							}
						}
						
						us=us.substring(0, us.length-1);
						
						var data;
						
						data = ($("#id").val() == "" ? 0 : $("#id").val())
								+ "/" + $("#code").textbox("getValue") 
								+ "/" + $("#excepttime").datetimebox("getValue") 
								+ "/" + $("#sender").combobox("getValue") 
								+ "/" + $("#memo").textbox("getValue")
								+ "/" + us;
						$
								.ajax({
									type : "POST",
									url : "patrolplan/save.html",
									dataType : "text",
									data : {
										"jsonStr" : data
									},
									success : function() {
										$.messager.progress('close');
										$('#dialog').window('close');
										reflush();
										$.messager.alert('操作提示', '操作成功',
										'info');
									}
								});
					});
			
			$("#sub2").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#patrolplaninfo").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							alert("必填项不能为空");
							return;
						}
						
						var station = $('#stations').datagrid('getRows');			
						if (station.length == 0) {
							$.messager.progress('close');
							alert("计划枢纽信息不能为空");
							return;
						}
						
						var us="";
						if(endEditing1()){
							var rows=$('#stations').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								var station=rows[i].station+",";
								us += station;
							}
						}
						
						us=us.substring(0, us.length-1);
						
						var data;
						
						data = ($("#id").val() == "" ? 0 : $("#id").val())
								+ "/" + $("#code").textbox("getValue") 
								+ "/" + $("#excepttime").datetimebox("getValue") 
								+ "/" + $("#sender").combobox("getValue") 
								+ "/" + $("#memo").textbox("getValue")
								+ "/" + us
								+"/qqq";
						$
								.ajax({
									type : "POST",
									url : "patrolplan/save.html",
									dataType : "text",
									data : {
										"jsonStr" : data
									},
									success : function() {
										$.messager.progress('close');
										$('#dialog').window('close');
										reflush();
										$.messager.alert('操作提示', '操作成功',
										'info');
									}
								});
					});

			$("#clear").bind("click", function() {
				var s= $("#code").textbox("getValue");				
				$("#patrolplaninfo").form("clear");
				$("#code").textbox("setValue", s);
			});
			$("#cancle").bind("click", function() {
				$("#dialog").dialog("close");
			});
			function reflush() {
				document.getElementById('patrolplan.htmlifm').contentWindow
						.$('#patrolplanlist').datagrid('reload');
			}
		});

	</script>

</body>
</html>
