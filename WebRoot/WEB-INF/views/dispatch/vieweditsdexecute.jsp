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
	<form id="addsdexecute" method="post"
		style="margin:10px; text-align: center;">
		<input id="sdstationid" type="hidden" value="${sde.getSds().getId()}" />
		<input id="executeid" type="hidden"  value="${sde.getId()}"/>
		<input id="id" type="hidden"  value="${id}"/>
		<input id="stationid" type="hidden" value="${sde.getS().getId()}" />
		<input id="userids" type="hidden"  value="${userids}"/>
		<table>
			<tr>
				<td><label for="code">具体实施单编号:</label></td>
				<td><input id="code"
				class="easyui-textbox" name="sdid" disabled="disabled"
				style="width: 320px;" data-options="required:true"
				value="${sde.getSds().getSelfDispatch().getCode()}" /></td>
			</tr>
			<tr>
				<td><label for="sid">执行枢纽:</label></td>
				<td><input id="sid"
				class="easyui-textbox" name="sid" disabled="disabled"
				style="width: 320px;" data-options="required:true"
				value="${sde.getS().getName()}" /></td>
			</tr>
			<tr>
				<td><label for="executer">具体实施人:</label></td>
				<td><input id="executer" style="width:320px;" disabled="disabled"/></td>
			</tr>
			<tr>
				<td><label>开机时外河水位:</label></td>
				<td><input class="easyui-textbox" id="startouterlevel"
					data-options="validType:'intOrFloat'" style="width:320px" value="${sde.getStartouterlevel()}" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td><label>开机时内河水位:</label></td>
				<td><input class="easyui-textbox" id="startinlandlevel"
					data-options="validType:'intOrFloat'" style="width:320px" value="${sde.getStartinlandlevel()}" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td><label>停机时外河水位:</label></td>
				<td><input class="easyui-textbox" id="stopouterlevel"
					data-options="validType:'intOrFloat'" style="width:320px" value="${sde.getStopouterlevel()}" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td><label>停机时内河水位:</label></td>
				<td><input class="easyui-textbox" id="stopinlandlevel"
					data-options="validType:'intOrFloat'" style="width:320px" value="${sde.getStopinlandlevel()}" disabled="disabled" />
				</td>
			</tr>
			<tr>
				<td><label for="memo">实施备注:</label></td>
				<td><input id="memo" class="easyui-textbox" name="memo" style="width:320px;height:100px;" data-options="multiline:true,disabled:'disabled'" value="${sde.getMemo()}"/></td>
			</tr>
		</table>
		<div style="width: 320px;height: 220px;margin-top: 10px;margin-bottom: 10px ">
			<table id="unit"></table>
		</div>
		<div
			style="width: 320px;height: 220px;margin-top: 10px;margin-bottom: 10px">
			<table id="gate"></table>
		</div>
	</form>

	<script type="text/javascript">
		var editIndex1 = undefined;
		var editIndex2 = undefined;
		/* var sid = document.getElementById("sid").value; */
		$(function() {
			var stationid=$("#stationid").val();
			var executeid=$('#executeid').val();			
			$('#executer').combobox({
				url : 'sdexecute/findUser.html',
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
			 $('#unit').datagrid({
				url:'sdexecute/findUnitByExecuteid.html?executeid='+executeid,
				columns : [ [
						{
							field : 'unit',
							title : '机组',
							width : 102,
							editor : {
								type : 'combobox',
								options : {
									valueField : 'id',
									textField : 'name',
									method : 'post',
									url : 'sdexecute/findUnit.html?sid='+stationid,
									required : true,
									editable : false,
									onSelect:function(record){
										var ed1 = $('#unit').datagrid('getEditor',
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
							width : 150,
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
							width : 160
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
							width : 160
						} ] ],
				width : '450px',
				height : '220px',
				singleSelect : true,
				
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				
		/* 		toolbar : [ {
					text : '添加机组指令',
					iconCls : 'icon-add',
					handler : append1
				}, {
					text : '删除机组指令',
					iconCls : 'icon-cut',
					handler : removeit1
				} ], */
				iconCls : 'icon-edit'/* ,
				onClickCell : onClickCell1,
				onEndEdit : onEndEdit1 */
			}); 
			$('#gate').datagrid({
				url:'sdexecute/findGateByExecuteid.html?executeid='+executeid,
				columns : [ [ {
					field : 'sid',
					title : '闸门',
					width : 102,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'post',
							url : 'sdexecute/findGate.html?sid='+stationid,
							required : true,
							editable : false,
							onSelect:function(record){
								var ed1 = $('#gate').datagrid('getEditor',
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
					width : 150,
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
						width : 160
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
						width : 160
					},
					
				] ],
				width : '450',
				height : '220',
				singleSelect : true,
				
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				
/* 				toolbar : [ {
					text : '添加闸门指令',
					iconCls : 'icon-add',
					handler : append2
				}, {
					text : '删除闸门指令',
					iconCls : 'icon-cut',
					handler : removeit2
				} ], */
				iconCls : 'icon-edit'/* ,
				onClickCell : onClickCell2,
				onEndEdit : onEndEdit2 */
			});
									
		});		
		function endEditing1() {
			if (editIndex1 == undefined) {
				return true;
			}
			if ($('#unit').datagrid('validateRow', editIndex1)) {
				$('#unit').datagrid('endEdit', editIndex1);
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
			if ($('#gate').datagrid('validateRow', editIndex2)) {
				$('#gate').datagrid('endEdit', editIndex2);
				editIndex2 = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell1(index, field) {
			if (editIndex1 != index) {
				if (endEditing1()) {
					$('#unit').datagrid('selectRow', index).datagrid(
							'beginEdit', index);
					var ed = $('#unit').datagrid('getEditor', {
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
						$('#unit').datagrid('selectRow', editIndex1);
					}, 0);
				}
			}
		}
		function onClickCell2(index, field) {
			if (editIndex2 != index) {
				if (endEditing2()) {
					$('#gate').datagrid('selectRow', index).datagrid(
							'beginEdit', index);
					var ed = $('#gate').datagrid('getEditor', {
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
						$('#gate').datagrid('selectRow', editIndex2);
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
		function onEndEdit2(index, row) {
			var ed1 = $(this).datagrid('getEditor', {
				index : index,
				field : 'sid'
			});
			row.sid = $(ed1.target).combobox('getText');
			var ed2 = $(this).datagrid('getEditor', {
				index : index,
				field : 'operate'
			});
			row.operate = $(ed2.target).combobox('getText');
		}
		function append1() {
			if (endEditing1()) {
				$('#unit').datagrid('appendRow', {});
				editIndex1 = $('#unit').datagrid('getRows').length - 1;
				$('#unit').datagrid('selectRow', editIndex1).datagrid(
						'beginEdit', editIndex1);
			}
		}
		function append2() {
			if (endEditing2()) {
				$('#gate').datagrid('appendRow', {});
				editIndex2 = $('#gate').datagrid('getRows').length - 1;
				$('#gate').datagrid('selectRow', editIndex2).datagrid(
						'beginEdit', editIndex2);
			}
		}
		function removeit1() {
			if (editIndex1 == undefined) {
				return;
			}
			$('#unit').datagrid('cancelEdit', editIndex1).datagrid(
					'deleteRow', editIndex1);

			editIndex1 = undefined;
		}
		function removeit2() {
			if (editIndex2 == undefined) {
				return;
			}
			$('#gate').datagrid('cancelEdit', editIndex2).datagrid(
					'deleteRow', editIndex2);

			editIndex2 = undefined;
		}
		function clear(){
			$("#addsdexecute").form("clear");
		}
		function reflush() {
			document.getElementById('sdexecute.htmlifm').contentWindow
					.$('#exelist').datagrid('reload');
		}
	</script>

</body>
</html>
