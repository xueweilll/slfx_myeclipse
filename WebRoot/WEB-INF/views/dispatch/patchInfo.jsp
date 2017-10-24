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
<title>执行人员汇报编辑</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div>		
		<p><img alt="" src="images/zlogo.png" style="vertical-align: middle;">
		<span align="center" style="font-size: 25;">汇&nbsp;&nbsp;报&nbsp;&nbsp;单</span></p>
	</div>
<input id="departmentid" type="hidden" value="${departmentid}" />
	<form id="ffemp" method="post" enctype="multipart/form-data"
		style="margin-top: 10px; text-align: center;padding-left:10px;">

		<input id="id" value="${dispatch.getId()}" type="hidden" /> <input
			id="selfdispatchinstructions" value="${instruction}" type="hidden" />
		<input id="SelfDispatchStations" type="hidden" />
		<table>
			<tr>
				<td><label for="code">汇报单编号:</label> </td>
				<td><input id="code" value="${dispatch.getCode()}" /></td>
			</tr>
			<tr>
				<td><label for="promotetime">汇报时间:</label></td>
				<td><input id="promotetime" name="brand" value="${promotertime}" /></td>
			</tr>
			<tr>
				<td><label for="promoter">汇报发起人:</label></td>
				<td><input id="promoter" style="width:350px;" value="${dispatch.getUser().getUserid()}" /></td>
			</tr>
			<tr>
				<td><label for="memo">汇报备注:</label></td>
				<td><input id="memo"
				style="width:350px;height:100px;" class="easyui-textbox"
				data-options="multiline:true" value="${dispatch.getMemo()}" /></td>
			</tr>
		</table>
		</br>
		<!-- <div  ><table id="instructions"></table></div> -->
		</br>
		<div ><table id="stations"></table></div>
		<div id="btn" style="text-align: center;margin-top:10px;">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save()">保存</a>&nbsp;&nbsp;
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save2()">保存并提交</a>&nbsp;&nbsp;
			<a id="cancel" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'" onclick="cancel()">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		var editIndex1 = undefined;
		var editIndex2 = undefined;
		$(function() {			
			var aaa = $("#id").val();
			$('#code').textbox({
				//required:true,
				disabled:true
			});
		/* 	if(aaa==""){
				$.post('dispatchinstructions/findCode.html',function(data){
					$('#code').textbox('setText',data);
				});
			} */
			var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
			buttons.splice(3, 0, {
				text: '清空',
				handler: function(target){
					$(target).datetimebox('setValue','');
					$(target).datetimebox('hidePanel');
				}
			});
			$('#promotetime').datetimebox({
				required:true,
				editable:false,
				buttons:buttons
			});
			
			function myformatter(date){  
			    var y = date.getFullYear();  
			    var m = date.getMonth()+1;  
			    var d = date.getDate();  
			    var h = date.getHours();  
			    var min = date.getMinutes();  
			    var sec = date.getSeconds();
			    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d) +" "+(h<10?('0'+h):h) + ':'+(min<10?('0'+min):min)+':'+(sec<10?('0'+sec):sec);   
			}
			
			var curr_time = new Date();  
			$("#promotetime").datetimebox("setValue",myformatter(curr_time));  
			
			$('#promoter').combobox({
				url : 'dispatchinstructions/user.html',
				textField : 'username',
				valueField : 'userid',
				editable : false,
				required : true
			});
			/* $('#instructions').datagrid({
				columns : [ [
						{
							field : 'instruction1',
							title : '闸门操作',
							width :80,
							editor : {
								type : 'combobox',
								options : {
									valueField : 'id',
									textField : 'text',
									method : 'post',
									url : 'paramers/instruction.html',
									required : true,
									editable : false,
									onLoadSuccess:function(){
										if($(this).combobox('getText')==""){
											$(this).combobox('select',0);
										}										
									},
									onSelect : function(record) {										
										var ed1 = $('#instructions').datagrid('getEditor',
												{
													index : editIndex1,
													field : 'gateoperatetime'
												});
										if (record.id == 1 || record.id == 2) {
											$(ed1.target).datetimebox(
												{
													required : true,
													disabled : false
												})
										} else {
											$(ed1.target).datetimebox(
												{
													required : false,
													disabled : true
												})
										}
									}
								}
							}
						},
						{
							field : 'gateoperatetime',
							title : '闸门操作时间',
							editor : {
								type : 'datetimebox',
								options : {
									editable : false,
									disabled : true
								}
							},
							width : 132,
							id : "a"
						},
						{
							field : 'instruction2',
							title : '机组操作',
							width :80,
							editor : {
								type : 'combobox',
								options : {
									valueField : 'id',
									textField : 'text',
									method : 'post',
									url : 'paramers/gate.html',
									required : true,
									editable : false,
									onLoadSuccess:function(){
										if($(this).combobox('getText')==""){
											$(this).combobox('select',0);
										}
									},
									onSelect : function(record) {
										var ed1 = $(
												'#instructions')
												.datagrid(
														'getEditor',
														{
															index : editIndex1,
															field : 'unitoperatetime'
														});
										if (record.id == 1
												|| record.id == 2) {
											$(ed1.target)
													.datetimebox(
															{
																required : true,
																disabled : false
															})
										} else {
											$(ed1.target)
													.datetimebox(
															{
																required : false,
																disabled : true
															})
										}
									}
								}
							}
						}, {
							field : 'unitoperatetime',
							title : '机组操作时间',
							editor : {
								type : 'datetimebox',
								options : {
									editable : false,
									disabled : true
								}
							},
							width : 135
						} ] ],
				width : '455px',
				height : '200px',
				singleSelect : true,
				
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				onSelect:function(a,row){				
					if(editIndex1==a){
						$('#instructions').datagrid('beginEdit', a);
						var ed1 = $('#instructions').datagrid('getEditor',
						{
							index : a,
							field : 'gateoperatetime'
						});
						if(row.instruction1=="开闸" || row.instruction1=="关闸"){
							$(ed1.target).datetimebox({
								required : true,
								disabled : false
							}).datetimebox('setValue',row.gateoperatetime);
						}
						var ed2 = $('#instructions').datagrid('getEditor',
							{
								index : a,
								field : 'unitoperatetime'
							});
						if(row.instruction2=="开泵" || row.instruction2=="关泵"){
							$(ed2.target).datetimebox({
								required : true,
								disabled : false
							}).datetimebox('setValue',row.unitoperatetime);
						}
					}
				},
				toolbar : [ {
					text : '添加调度指令',
					iconCls : 'icon-add',
					handler : append1
				}, {
					text : '删除调度指令',
					iconCls : 'icon-cut',
					handler : removeit1
				} ],
				iconCls : 'icon-edit',
				onClickCell : onClickCell1,
				onEndEdit : onEndEdit1
			}); */
			$('#stations').datagrid({
				columns : [ [ {
					field : 'sid',
					title : '枢纽',
					width : 120,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'post',
							/* url : 'dispatchinstructions/station.html', */
							url : 'station/stationCombobox.html?departmentid='+ $("#departmentid").val(),
							required : true,
							editable : false,
							onSelect:function(record){
								var ed1 = $('#stations').datagrid('getEditor',
										{
											index : editIndex2,
											field : 'controlwater'
										});
										var ed2 = $('#stations').datagrid('getEditor',
										{
											index : editIndex2,
											field : 'truewater'
										});
										$(ed1.target).textbox('setText',record.controlwaterlevel);
										$(ed2.target).textbox('setText',record.truewaterlevel);
								var rows=$('#stations').datagrid('getRows');
								var k=0;
								for(var i=0;i<rows.length;i++){
									if(rows[i].sid==record.name){
										k++;
									}
								}
								if(k>0){
									alert("已存在");
									$(this).combobox('setValue','');
									$(ed1.target).textbox('setText',"");
									$(ed2.target).textbox('setText',"");
								}
								
								var ed3 = $('#stations').datagrid('getEditor',
										{
											index : editIndex2,
											field : 'sids'
										});
								$(ed3.target).textbox('setText',record.id);
							}
						}
					}
				},{
					field : 'controlwater',
					title : '控制水位',
					width : 100,
					editor : {
						type : 'textbox',
						options : {
							editable : false
						}
					}
				},{
					field : 'truewater',
					title : '实际水位',
					width : 100,
					editor : {
						type : 'textbox',
						options : {
							editable : false
						}						
					}
					
				},{
					field : 'sids',
					title : 'sids',
					width : 100,
					editor : {
						type : 'textbox',
						options : {
							editable : false
						}
					},
					hidden:true
				} ] ],
				width : '455',
				height : '200',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				toolbar : [ {
					text : '添加汇报明细',
					iconCls : 'icon-add',
					handler : append2
				}, {
					text : '删除汇报明细',
					iconCls : 'icon-cut',
					handler : removeit2
				} ],
				iconCls : 'icon-edit',
				onClickCell : onClickCell2,
				onEndEdit : onEndEdit2
			});
			if (aaa != "") {
				$('#instructions')
						.datagrid(
								{
									url : "dispatchinstructions/findInstructionsByid.html?id="
											+ aaa
								});
				$('#stations')
						.datagrid(
								{
									url : "dispatchinstructions/findStationsByid.html?id="
											+ aaa
								});
			}
		});
		function endEditing1() {
			if (editIndex1 == undefined) {
				return true;
			}
			if ($('#instructions').datagrid('validateRow', editIndex1)) {
				$('#instructions').datagrid('endEdit', editIndex1);
				editIndex1 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function endEditing2() {
			if (editIndex2 == undefined) {
				return true;
			}
			if ($('#stations').datagrid('validateRow', editIndex2)) {
				$('#stations').datagrid('endEdit', editIndex2);
				editIndex2 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell1(index, field) {
			if (editIndex1 != index) {
				if (endEditing1()) {
					$('#instructions').datagrid('selectRow', index).datagrid(
							'beginEdit', index);
					var ed = $('#instructions').datagrid('getEditor', {
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
						$('#instructions').datagrid('selectRow', editIndex1);
					}, 0);
				}
			}
		}
		function onClickCell2(index, field) {
			if (editIndex2 != index) {
				if (endEditing2()) {
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
					editIndex2 = index;
				} else {
					setTimeout(function() {
						$('#stations').datagrid('selectRow', editIndex2);
					}, 0);
				}
			}
		}
		function onEndEdit1(index, row) {
			var ed1 = $(this).datagrid('getEditor', {
				index : index,
				field : 'instruction1'
			});
			row.instruction1 = $(ed1.target).combobox('getText');
			var ed2 = $(this).datagrid('getEditor', {
				index : index,
				field : 'instruction2'
			});
			row.instruction2 = $(ed2.target).combobox('getText');
		}
		function onEndEdit2(index, row) {
			var ed1 = $(this).datagrid('getEditor', {
				index : index,
				field : 'sid'
			});
			row.sid = $(ed1.target).combobox('getText');
		}
		function append1() {			
			if (endEditing1()) {
				var rows=$('#instructions').datagrid('getRows');
				if(rows.length>0){					
					for(var i=0;i<rows.length;i++){
						if(rows[i].instruction1=="不操作" && rows[i].instruction2=="不操作"){						
							$('#instructions').datagrid('deleteRow', i);
							i=-1;
						}
					}
				}
				$('#instructions').datagrid('appendRow', {});
				editIndex1 = $('#instructions').datagrid('getRows').length - 1;
				/* $('#instructions').datagrid('selectRow', editIndex1).datagrid(
						'beginEdit', editIndex1); */
				$('#instructions').datagrid('selectRow', editIndex1);				
			}
		}
		function append2() {
			if (endEditing2()) {
				$('#stations').datagrid('appendRow', {});
				editIndex2 = $('#stations').datagrid('getRows').length - 1;
				$('#stations').datagrid('selectRow', editIndex2).datagrid(
						'beginEdit', editIndex2);
			}
		}
		function removeit1() {
			if (editIndex1 == undefined) {
				return;
			}
			$('#instructions').datagrid('cancelEdit', editIndex1).datagrid(
					'deleteRow', editIndex1);

			editIndex1 = undefined;
		}
		function removeit2() {
			if (editIndex2 == undefined) {
				return;
			}
			$('#stations').datagrid('cancelEdit', editIndex2).datagrid(
					'deleteRow', editIndex2);

			editIndex2 = undefined;
		}
		function reflush() {
			document.getElementById('dispatchinstructions.htmlifm').contentWindow
					.$('#selfdispatch').datagrid('reload');
		}
		function Save() {
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				alert("必填项不能空着");
				return;
			}
			
			var instructiones = new Array();
			var gateoperatetimes = new Array();
			var unitoperatetimes = new Array();
			var ises = "";
			var gaes = "";
			var utes = "";			
			var sides = "";
			var selfdispatchstation=[];
			var controlwaters="";
			var truewaters="";
			if (endEditing2()) {
				var stations = $('#stations').datagrid('getRows');
				if (stations.length == 0) {
					alert("调度明细不能为空");
					return;
				}
				for (var i = 0; i < stations.length; i++) {					
					sides += stations[i].sid + ",";
					selfdispatchstation.push({
						"sid":stations[i].sids,						
						"controlwaterlevel":stations[i].controlwater,
						"truewaterlevel":stations[i].truewater,"state":0
						});
					if(stations[i].controlwater==""){
						controlwaters+="a,";
					}else{
						controlwaters+=stations[i].controlwater + ",";
					}
					if(stations[i].truewater==""){
						truewaters+="a,";
					}else{
						truewaters+=stations[i].truewater + ",";
					}
					
				}
			}
			sides = sides.substring(0, sides.length - 1);
			controlwaters=controlwaters.substring(0, controlwaters.length-1);
			truewaters=truewaters.substring(0, truewaters.length-1);
			//var id = $("#id").val() == "" ? "0" : $("#id").val();
			var id = $("#id").val();
			var promotetime = $("#promotetime").datetimebox("getText");
			var promoter = $("#promoter").combobox("getValue");
			var memo = $("#memo").textbox("getText").replace(/\s+/g,"");
			var code = $('#code').textbox("getText");
			var data2 = id + "/" + promotetime + "/" + promoter + "/" + memo
					+ "/" + code + "/" + ises + "/" + gaes + "/" + utes + "/"
					+ sides+"/"+controlwaters+"/"+truewaters;			
			var data={
				"id":id,
				"promoter":promoter,
				"promotetime":promotetime,
				"memo":memo,
				"code":code,
				"state":0,
				"stations":selfdispatchstation
			};
			$.ajax({
				url : "dispatchinstructions/save.html",
				type : "POST",
				dataType : "text",
				data : {
					"json" : JSON.stringify(data)
				},
				success : function() {
					reflush();
					$('#dialog').window('close');
				},
				error : function(data, status, e) { //服务器响应失败时的处理函数
					$('#result').html('图片上传失败，请重试！！');
				}
			});
		}		
		function Save2() {
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				alert("必填项不能空着");
				return;
			}
			$.messager.confirm('确定对话框','确定要提交吗？',function(r){
				if(r){
					var instructiones = new Array();
					var gateoperatetimes = new Array();
					var unitoperatetimes = new Array();
					var ises = "";
					var gaes = "";
					var utes = "";					
					var sides = "";
					var selfdispatchstation=[];
					var controlwaters="";
					var truewaters="";
					if (endEditing2()) {
						var stations = $('#stations').datagrid('getRows');
						if (stations.length == 0) {
							alert("调度明细不能为空");
							return;
						}
						for (var i = 0; i < stations.length; i++) {
							sides += stations[i].stationid + ",";
							selfdispatchstation.push({
								"sid":stations[i].sids,
								"controlwaterlevel":stations[i].controlwater,
								"truewaterlevel":stations[i].truewater,"state":0
								});
							if(stations[i].controlwater==""){
								controlwaters+="a,";
							}else{
								controlwaters+=stations[i].controlwater + ",";
							}
							if(stations[i].truewater==""){
								truewaters+="a,";
							}else{
								truewaters+=stations[i].truewater + ",";
							}
						}
					}
					sides = sides.substring(0, sides.length - 1);
					controlwaters=controlwaters.substring(0, controlwaters.length-1);
					truewaters=truewaters.substring(0, truewaters.length-1);
					var id = $("#id").val();
					var promotetime = $("#promotetime").datetimebox("getText");
					var promoter = $("#promoter").combobox("getValue");
					var memo = $("#memo").textbox("getText").replace(/\s+/g,"");
					var code = $('#code').textbox("getText");
					var data2 = id + "/" + promotetime + "/" + promoter + "/" + memo
							+ "/" + code + "/" + ises + "/" + gaes + "/" + utes + "/"
							+ sides +"/"+controlwaters+"/"+ truewaters+"/aaa";
					var data={
							"id":id,
							"promoter":promoter,
							"promotetime":promotetime,
							"memo":memo,
							"code":code,
							"state":1,
							"stations":selfdispatchstation
						};
					$.ajax({
						url : "dispatchinstructions/save.html",
						type : "POST",
						dataType : "text",
						data : {
							"json" : JSON.stringify(data)
						},
						success : function() {
							reflush();
							$('#dialog').window('close');
						},
						error : function(data, status, e) { //服务器响应失败时的处理函数
							$('#result').html('图片上传失败，请重试！！');
						}
					});
					/* $.ajax({
						url : "dispatchinstructions/save.html",
						type : "POST",
						dataType : "text",
						data : {
							"json" : data2
						},
						success : function() {
							reflush();
							$('#dialog').window('close');
						},
						error : function(data, status, e) { //服务器响应失败时的处理函数
							$('#result').html('图片上传失败，请重试！！');
						}
					}); */
					
				}				
			});
		}
		function cancel(){
			$('#dialog').window('close');
		}
	</script>

</body>
</html>
