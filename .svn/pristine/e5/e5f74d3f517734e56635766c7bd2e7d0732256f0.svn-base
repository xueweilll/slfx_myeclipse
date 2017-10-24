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
<title>My JSP 'messageInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<div>		
		<p style="vertical-align: middle;"><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">具&nbsp;体&nbsp;实&nbsp;施&nbsp;单</span></p>
	</div>
	<input id="id" type="hidden" value="${rde.getRds().getId()}" />
	<input id="executeid" type="hidden" value="${rde.getId()}"/>
	<input id="stationid" type="hidden"  value="${rde.getRds().getSid()}"/>
	<form id="addrdexecute" method="post"
		style="margin: 10px; text-align:left;">
		<div>
			<!-- <label for="rdid">调度单ID:</label>  -->
			<input id="rdid" type="hidden" name="rdid" disabled="disabled"
				style="width:415px;" data-options="required:true"
				value="${rde.getRds().getReceiptDispatch().getId()}" />
		</div>
		<div>
			<label for="sname">执行枢纽:</label> 
			<input id="sid" type="hidden" name="sid"
				value="${rde.getRds().getSid()}"/>
			<input id="sname"
				class="easyui-textbox" name="sname" disabled="disabled"
				style="width: 415px;" data-options="required:true"
				value="${rde.getS().getName()}"/>
		</div>
		<div>
			<label for="executer">调度执行人:</label> 
			<input id="executer" disabled="disabled" style="width:415px;" data-options="required:true"/>
			<input id="userids" type="hidden"  value="${userids}"/>
		</div>
		<div>
			<label for="startouterlevel">开机时外河水位:</label>
			<input class="easyui-textbox" disabled="disabled" id="startouterlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="${rde.getStartouterlevel()}" />
		</div>
		<div>
			<label for="startinlandlevel">开机时内河水位:</label>
			<input class="easyui-textbox" disabled="disabled" id="startinlandlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="${rde.getStartinlandlevel()}" />
		</div>
		<div>
			<label for="stopouterlevel">停机时外河水位:</label>
			<input class="easyui-textbox" disabled="disabled" id="stopouterlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="${rde.getStopouterlevel()}" />
		</div>
		<div>
			<label for="stopinlandlevel">停机时内河水位:</label>
			<input class="easyui-textbox" disabled="disabled" id="stopinlandlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="${rde.getStopinlandlevel()}" />
		</div>
		<div>
			<label for="memo">执行备注:</label> <input id="memo"
				class="easyui-textbox" name="memo" disabled="disabled" style="width:415px;height:100px;"
				data-options="multiline:true" data-options="required:true" value="${rde.getMemo()}"/>
		</div>

		<div
			style="width:520px;margin-top: 10px">
			<table id="units"></table>
		</div>
		<div
			style="width:520px;margin-top: 10px;margin-bottom: 10px">
			<table id="gates"></table>
		</div>
	</form>

	<script type="text/javascript">
		var editIndex1 = undefined;
		var editIndex2 = undefined;
		
		var stationid = document.getElementById("stationid").value;
		var sid = document.getElementById("sid").value;
		var rdid = document.getElementById("rdid").value;
		var executeid = $("#executeid").val() == "" ? "0" : $("#executeid").val();
		$(function() {
			/*$("#memo").textbox({
				required : true,
				showSeconds : true,
				missingMessage : "该输入项为必输项"
			});*/
			
			$('#executer').combobox({
				url : 'rdexecute/findUser.html',
				valueField : 'userid',
				textField : 'username',
				required : true,
				editable : false,
				multiple:true,
				onLoadSuccess:function(){
					var userids=eval($('#userids').val());
					$('#executer').combobox('setValues',userids);
				}				
			});	
			
			$('#units').datagrid({
				url:'rdexecute/findUnitByExecuteid.html?executeid='+executeid,
				columns : [ [
						{
							field : 'unit',
							title : '机组名称',
							width : 132,
							editor : {
								type : 'combobox',
								options : {
									valueField : 'id',
									textField : 'name',
									method : 'post',
									url : 'rdexecute/findUnits.html?sid='+sid,
									required : true,
									editable : false,
									onSelect:function(record){
										var ed1 = $('#units').datagrid('getEditor',
											{
												index : editIndex1,
												field : 'unitid'
											});
										$(ed1.target).textbox('setText',record.id);
									}
								}
							}
						},{
							field : 'unitid',
							title : '机组id',
							width : 250,
							hidden:true,														
							editor : {
								type : 'textbox',
								options : {
									editable : false
								}
							}
						},{
							field : 'begintime',
							title : '开始时间',
							editor : {
								type : 'datetimebox',
								options : {
									editable : false,
									required : true
								}
							},
							width : 180
						}, {
							field : 'endtime',
							title : '结束时间',
							editor : {
								type : 'datetimebox',
								options : {
									editable : false,
									required : true
								}
							},
							width : 180
						} ] ],
				width : '520px',
				height : '175px',
				singleSelect : true,
				
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				
				toolbar : [ {
					text : '添加机组执行明细',
					iconCls : 'icon-add',
					handler : append1
				}, {
					text : '删除机组执行明细',
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
				if ($('#units').datagrid('validateRow', editIndex1)) {
					$('#units').datagrid('endEdit', editIndex1);
					editIndex1 = undefined;
					return true;
				} else {
					return false;
				}
			}

			function onClickCell1(index, field) {
				if (editIndex1 != index) {
					if (endEditing1()) {
						$('#units').datagrid('selectRow', index).datagrid(
								'beginEdit', index);
						var ed = $('#units').datagrid('getEditor', {
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
							$('#units').datagrid('selectRow', editIndex1);
						}, 0);
					}
				}
			}

			function onEndEdit1(index, row) {
				var ed1 = $(this).datagrid('getEditor', {
					index : index,
					field : 'unit'
				});
				row.unit = $(ed1.target).combobox('getText');
			}

			function append1() {
				if (endEditing1()) {
					$('#units').datagrid('appendRow', {});
					editIndex1 = $('#units').datagrid('getRows').length - 1;
					$('#units').datagrid('selectRow', editIndex1).datagrid(
							'beginEdit', editIndex1);
				}
			}

			function removeit1() {
				if (editIndex1 == undefined) {
					return;
				}
				$('#units').datagrid('cancelEdit', editIndex1).datagrid(
						'deleteRow', editIndex1);

				editIndex1 = undefined;
			}
			
			$('#gates').datagrid({
				url:'rdexecute/findGateByExecuteid.html?executeid='+executeid,
				columns : [ [ {
					field : 'sid',
					title : '闸门名称',
					width : 132,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'post',
							url : 'rdexecute/findGates.html?sid='+sid,
							required : true,
							editable : false,
							onSelect:function(record){
								var ed1 = $('#gates').datagrid('getEditor',
									{
										index : editIndex2,
										field : 'sidid'
									});
								$(ed1.target).textbox('setText',record.id);
							}
						}
					}
				},{
					field : 'sidid',
					title : '闸门id',
					width : 250,
					hidden:true,														
					editor : {
						type : 'textbox',
						options : {
							editable : false
						}
					}
				},{
						field : 'operatetime',
						title : '闸门执行时间',
						editor : {
							type : 'datetimebox',
							options : {
								editable : false,
								required : true
							}
						},
						width : 180
					},{
						field : 'operate',
						title : '操作',
						editor : {
							type : 'combobox',
							options : {
								url : 'paramers/Operate.html',
								valueField : 'id',
								textField : 'text',
								method : 'post',
								editable : false,
								required : true
							}
						},
						width : 180
					},
					
				] ],
				width : '520',
				height : '175',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				
				toolbar : [ {
					text : '添加闸门执行明细',
					iconCls : 'icon-add',
					handler : append2
				}, {
					text : '删除闸门执行明细',
					iconCls : 'icon-cut',
					handler : removeit2
				} ],
				iconCls : 'icon-edit',
				onClickCell : onClickCell2,
				onEndEdit : onEndEdit2
			});
									
		
			
			function endEditing2() {
				if (editIndex2 == undefined) {
					return true;
				}
				if ($('#gates').datagrid('validateRow', editIndex2)) {
					$('#gates').datagrid('endEdit', editIndex2);
					editIndex2 = undefined;
					return true;
				} else {
					return false;
				}
			}

			function onClickCell2(index, field) {
				if (editIndex2 != index) {
					if (endEditing2()) {
						$('#gates').datagrid('selectRow', index).datagrid(
								'beginEdit', index);
						var ed = $('#gates').datagrid('getEditor', {
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
							$('#gates').datagrid('selectRow', editIndex2);
						}, 0);
					}
				}
			}

			function onEndEdit2(index, row) {
				var ed1 = $(this).datagrid('getEditor', {
					index : index,
					field : 'operate'
				});
				row.operate = $(ed1.target).combobox('getText');
				var ed2 = $(this).datagrid('getEditor', {
					index : index,
					field : 'sid'
				});
				row.sid = $(ed2.target).combobox('getText');
			}

			function append2() {
				if (endEditing2()) {
					$('#gates').datagrid('appendRow', {});
					editIndex2 = $('#gates').datagrid('getRows').length - 1;
					$('#gates').datagrid('selectRow', editIndex2).datagrid(
							'beginEdit', editIndex2);
				}
			}

			function removeit2() {
				if (editIndex2 == undefined) {
					return;
				}
				$('#gates').datagrid('cancelEdit', editIndex2).datagrid(
						'deleteRow', editIndex2);

				editIndex2 = undefined;
			}

			$("#sub").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#addrdexecute").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							alert("必填项不能为空");
							return false;
						}
						
						var units = $('#units').datagrid('getRows');			
						var gates = $('#gates').datagrid('getRows');
						if (units.length == 0 && gates.length == 0) {
							$.messager.progress('close');
							alert("机组执行明细和闸门执行明细不能同时为空！");
							return;
						}
						
						var us="";
						var betime="";
						var edtime="";
						if(endEditing1()){
							var rows=$('#units').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								if(rows[i].begintime>rows[i].endtime){
									$.messager.progress('close');
									alert("机组的开始时间不能大于结束时间");
									return;
								}
							}
							for(var i=0;i<rows.length;i++){
								var unit=rows[i].unit+",";
								us += unit;
								
								var btime = rows[i].begintime+",";
								betime += btime;
								
								var etime = rows[i].endtime+",";
								edtime += etime;
							}
						}
						us=us.substring(0, us.length-1);
						betime=betime.substring(0, betime.length-1);
						edtime=edtime.substring(0, edtime.length-1);
						
						var ge="";
						var opertime="";
						var oper="";
						if(endEditing2()){
							var rows=$('#gates').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								var gate=rows[i].sid+",";
								ge += gate;
								
								var otime = rows[i].operatetime+",";
								opertime += otime;
								
								var etime = rows[i].operate+",";
								oper += etime;
							}
						}
						ge=ge.substring(0, ge.length-1);
						opertime=opertime.substring(0, opertime.length-1);
						oper=oper.substring(0, oper.length-1);

						var data;
						data = ($("#id").val() == "" ? 0 : $("#id").val())
								+ "/" + sid
								+ "/" + rdid
								+ "/" + $("#executer").combogrid("getValues") 
								+ "/" + $("#memo").textbox("getValue")
								+ "/" + us
								+ "/" + betime
								+ "/" + edtime
								+ "/" + ge
								+ "/" + opertime
								+ "/" + oper
								+ "/" + executeid;

						$
								.ajax({
									type : "POST",
									url : "rdexecute/save.html",
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
						var isValid = $("#addrdexecute").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							alert("必填项不能为空");
							return false;
						}
						
						var units = $('#units').datagrid('getRows');			
						var gates = $('#gates').datagrid('getRows');
						if (units.length == 0 && gates.length == 0) {
							$.messager.progress('close');
							alert("机组执行明细和闸门执行明细不能同时为空！");
							return;
						}
						
						var us="";
						var betime="";
						var edtime="";
						if(endEditing1()){
							var rows=$('#units').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								if(rows[i].begintime>rows[i].endtime){
									$.messager.progress('close');
									alert("机组的开始时间不能大于结束时间");
									return;
								}
							}
							for(var i=0;i<rows.length;i++){
								var unit=rows[i].unit+",";
								us += unit;
								
								var btime = rows[i].begintime+",";
								betime += btime;
								
								var etime = rows[i].endtime+",";
								edtime += etime;
							}
						}
						us=us.substring(0, us.length-1);
						betime=betime.substring(0, betime.length-1);
						edtime=edtime.substring(0, edtime.length-1);
						
						var ge="";
						var opertime="";
						var oper="";
						if(endEditing2()){
							var rows=$('#gates').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								var gate=rows[i].sid+",";
								ge += gate;
								
								var otime = rows[i].operatetime+",";
								opertime += otime;
								
								var etime = rows[i].operate+",";
								oper += etime;
							}
						}
						ge=ge.substring(0, ge.length-1);
						opertime=opertime.substring(0, opertime.length-1);
						oper=oper.substring(0, oper.length-1);

						var data;
						data = ($("#id").val() == "" ? 0 : $("#id").val())
								+ "/" + sid
								+ "/" + rdid
								+ "/" + $("#executer").combogrid("getValues") 
								+ "/" + $("#memo").textbox("getValue")
								+ "/" + us
								+ "/" + betime
								+ "/" + edtime
								+ "/" + ge
								+ "/" + opertime
								+ "/" + oper
								+ "/" + executeid
								+"/qqq";

						$
								.ajax({
									type : "POST",
									url : "rdexecute/save.html",
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
				$("#addrdexecute").form("clear");
			});
			$("#cancle").bind("click", function() {
				$("#dialog").dialog("close");
			});
			function reflush() {
				document.getElementById('rdexecute.htmlifm').contentWindow
						.$('#rdexelist').datagrid('reload');
			}
		});

	</script>

</body>
</html>
