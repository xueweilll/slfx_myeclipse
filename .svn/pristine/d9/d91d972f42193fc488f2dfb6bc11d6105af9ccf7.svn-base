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
	<input id="id" type="hidden" value="${patrol.getId()}" />
	<div>		
		<p><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">巡&nbsp;检&nbsp;录&nbsp;入&nbsp;单</span></p>
	</div>
	<form id="patrolrecordinfo" method="post"
		style="margin:2px; text-align: center;">
		<div style="margin-top: 2px; text-align: left;margin-left: 10px">
			<label for="Sid">枢纽名称:</label>
			<input id="Sid" name="Sid" style="width:250px;"	value="${gate.getSid()}" />
		</div>
		<div style="margin-top: 2px; text-align: left;margin-left: 10px">
			<label for="excepttime">巡检时间:</label> <input id="excepttime" style="width:250px;"
				class="easyui-datetimebox" name="excepttime" value="${excepttime}"
				data-options="required:true,editable:false" />
		</div>
		<div style="text-align: left;margin-left: 10px">
			<label for="users">巡检人员:</label> <input id="users"
				style="width:250px;" data-options="required:true" value="${users}"/>
		</div>
		<div style="height: 615px;margin-top: 10px;margin-bottom: 10px">
			<table id="records"></table>
		</div>
		<div style="margin-top: 10px; text-align: left;">
			<label for="memo">巡检综述:</label><br /> <input id="memo"
				style="width:750px;height:100px;" class="easyui-textbox" name="memo"
				value="${patrol.getMemo()}" data-options="multiline:true" />
			</td>
		</div>
		</br>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp; <a id="commit"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存并提交</a>&nbsp;&nbsp; <a
				id="cancle" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
		<div id="btnDetails">
			<a id="btnorder" href="javascript:void(0)" plain="true"
				class="easyui-linkbutton" data-options="iconCls:'icon_add'">一键正常</a>
		</div>
	</form>

	<script type="text/javascript">
		var editIndex1 = undefined;
		$(function() {	
			$("#Sid").combobox({
				url : 'station/stationByFk.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				/* value : $("#Sid").val(), */
				required : true
			});
			
			$('#users').combogrid({
				url : 'patrolreceipt/userListByDepartment.html',
				idField : 'id',
				textField : 'ename',
				required : true,
				editable : false,
				multiple : true,
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				columns : [ [ {
					field : 'dname',
					title : '部门',
					width : 145
				}, {
					field : 'ename',
					title : '用户名',
					width : 140
				} ] ],
				fitColumns : true
			});

			$("#btnorder").bind(
					"click",
					function() {

						var rows = $('#records').datagrid('getRows');

						for (var i = 0; i < rows.length; i++) {
							$('#records').datagrid('selectRow', i).datagrid(
									'beginEdit', i);
							var istype = rows[i].problemtype;

							if (istype == "1") {
								var record = $('#records').datagrid(
										'getEditor', {
											index : i,
											field : 'record'
										});
								var handletype = $('#records').datagrid(
										'getEditor', {
											index : i,
											field : 'handletype'
										});
								$(record.target).textbox('setValue', '');
								$(record.target).textbox({
									disabled : true
								});
								$(handletype.target).textbox('setValue', '');
								$(handletype.target).textbox({
									disabled : true
								});

							}

							var problemtype = $('#records').datagrid(
									'getEditor', {
										index : i,
										field : 'problemtype'
									});
							$(problemtype.target).textbox("setValue", '0');
							$('#records').datagrid('endEdit', i);
						}
					});

			$('#records').datagrid({
				url : 'patrolreceipt/PatrolType.html?id='
						+ $("#id").val(),
				columns : [ [{
							field : 'enumid',
							title : '',
							hidden : true,
							width : 110
						},{
							field : 'text',
							title : '巡视检查部位',
							width : 130
						},{
							field : 'contents',
							title : '巡视内容要求',
							width : 250
						},{
							formatter : function(value, row,
									index) {
								if (value == 1) {
									return "有问题";
								} else if (value == 0) {
									return "正常";
								}
							},
							field : 'problemtype',
							title : '是否有问题',
							editor : {
								type : 'combobox',
								options : {
									url : 'paramers/ProblemType.html',
									valueField : 'id',
									textField : 'text',
									method : 'post',
									editable : false,
									required : true,
									onSelect : function(value) {
										var row = $("#records").datagrid("getSelected");
										if (row == null) {
											return false;
										}
										var lastIndex = $("#records").datagrid("getRowIndex",row);
										var record = $('#records').datagrid('getEditor',{index : lastIndex,field : 'record'});
										var handletype = $('#records').datagrid('getEditor',{index : lastIndex,field : 'handletype'});
										if (value.id == "1") {
											$(record.target).textbox({disabled : false,required : true});
											$(handletype.target).textbox({disabled : false,required : true});
										} else {
											$(record.target).textbox('setValue','');
											$(record.target).textbox({disabled : true});
											$(handletype.target).combobox('setValue','');
											$(handletype.target).textbox({disabled : true});
											//$('#records').datagrid('endEdit', lastIndex);
										}
									}
								}
							},
							width : 70
						}, {
							field : 'record',
							title : '问题记录',
							editor : {
								type : 'textbox',
								options : {
									valueField : 'id',
									textField : 'text',
									method : 'post',
									disabled : true
								}
							},
							width : 150
						},
						{
							formatter : function(val, row,
									index) {
								if (row.problemtype == "0") {
									return "";
								} else {
									if (val == "0") {
										return "自行解决";
									}
									if (val == "1") {
										return "上报工程科";
									}
									if (val == null || val == "" || val == undefined || val == "_") {
										return "";
									}
								}
							},
							field : 'handletype',
							title : '处理方式',
							editor : {
								type : 'combobox',
								options : {
									url : 'paramers/HandleType.html',
									editable : false,
									valueField : 'id',
									textField : 'text',
									method : 'post',
									disabled : true
								}
							},
							width : 120
						} ] ],
				idField : 'id',
				width : 'auto',
				height : 'auto',
				singleSelect : true,
				rownumbers : true,
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				remoteSort : false,
				fit : true,
				toolbar : ('#btnDetails'),
				iconCls : 'icon-edit',
				onClickCell : onClickCell1,
				onEndEdit : onendEditing
			});

			function endEditing1() {
				if (editIndex1 == undefined) {
					return true
				}
				if ($('#records').datagrid('validateRow', editIndex1)) {
					$('#records').datagrid('endEdit', editIndex1);
					editIndex1 = undefined;
					return true;
				} else {
					return false;

				}
			}

			function onClickCell1(index, field) {
				if (endEditing1()) {
					$('#records').datagrid('selectRow', index).datagrid(
							'beginEdit', index);
					var row = $("#records").datagrid("getSelected");
					var problemtypes = row.problemtype;
					var record = $('#records').datagrid('getEditor', {
						index : index,
						field : 'record'
					});
					var handletype = $('#records').datagrid('getEditor', {
						index : index,
						field : 'handletype'
					});

					if (problemtypes == undefined) {
						$(record.target).textbox({
							disabled : true
						});
						$(handletype.target).textbox({
							disabled : true
						});
					} else if (problemtypes == "0") {
						$(record.target).textbox({
							disabled : true
						});
						$(handletype.target).textbox({
							disabled : true
						});
						$(handletype.target).textbox('setValue', '');
					} else if (problemtypes == "1") {
						$(record.target).textbox({
							disabled : false,
							required : true
						});
						$(handletype.target).textbox({
							disabled : false,
							required : true
						});
					}
					var ed = $('#records').datagrid('getEditor', {
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
						$('#records').datagrid('selectRow', editIndex1);
					}, 0);
				}
			}

			function onendEditing(index, row, change) {
				/* var ed1 = $(this).datagrid('getEditor', {
					index : index,
					field : 'problemtype'
				});
				row.problemtype = $(ed1.target).combobox('getText');

				var ed2 = $(this).datagrid('getEditor', {
					index : index,
					field : 'handletype'
				});
				row.handletype = $(ed2.target).combobox('getText'); */
			}

			$("#sub").bind("click",function() {
				//$.messager.progress();
				var isValid = $("#patrolrecordinfo").form('validate');
				if (!isValid) {
					$.messager.progress('close');
					alert("必填项不能为空");
					return;
				}
				endEditing1();

				var id = $("#id").val() == "" ? 0 : $("#id").val();

				var details = [];
				var rows = $('#records').datagrid('getRows');
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].problemtype == undefined) {
						continue;
					}
					var enumid = rows[i].enumid;
					var istype = parseInt(rows[i].problemtype);
					var record;
					var handletype;
					if (istype == 0) {
						record = null;
						handletype = null;
					} else {
						record = rows[i].record;
						handletype = parseInt(rows[i].handletype);
					}
					details.push({
						"id" : rows.id,
						"patrolid" : id,
						"enumid" : enumid,
						"istype" : istype,
						"contents" : record,
						"handletype" : handletype
					});
				}

				var data = {
					"id" : id,
					"patrolplandetailsid" : $("#ppDetails").val(),
					"patroltime" : $("#excepttime").datetimebox(
							"getValue"),
					"memo" : $("#memo").textbox("getValue").replace(
							/\s+/g, ""),
					"state" : 0,
					"pds" : details
				};
				//alert(JSON.stringify(data));

				$.ajax({
					type : "POST",
					url : "patrolreceipt/save.html",
					dataType : "text",
					data : {
						"jsonStr" : JSON.stringify(data)
					},
					success : function() {
						$.messager.progress('close');
						$('#dialog').window('close');
						reflush();
						$.messager.alert('操作提示', '操作成功', 'info');
					}
				});
			});

			$("#commit").bind(
					"click",
					function() {
						var isValid = $("#patrolrecordinfo").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							alert("必填项不能为空");
							return;
						}
						endEditing1();
						var id = $("#id").val() == "" ? 0 : $("#id").val();

						var details = [];
						var rows = $('#records').datagrid('getRows');

						for (var i = 0; i < rows.length; i++) {
							var istype = rows[i].problemtype;

							if (istype == undefined || istype == null) {
								//alert(istype==null);
								//alert(i+"-"+istype);
								$.messager.alert('操作提示', '未完全录入,不能提交！！！',
										'error');
								return false;
							}
						}

						for (var i = 0; i < rows.length; i++) {
							if (rows[i].problemtype == undefined) {
								continue;
							}
							var enumid = rows[i].enumid;
							var istype = parseInt(rows[i].problemtype);
							var record = rows[i].record;
							var handletype = parseInt(rows[i].handletype);

							details.push({
								"id" : rows.id,
								"patrolid" : id,
								"enumid" : enumid,
								"istype" : istype,
								"contents" : record,
								"handletype" : handletype
							});
						}

						var data = {
							"id" : id,
							"patrolplandetailsid" : $("#ppDetails").val(),
							"patroltime" : $("#excepttime").datetimebox(
									"getValue"),
							"memo" : $("#memo").textbox("getValue").replace(
									/\s+/g, ""),
							"state" : 1,
							"pds" : details
						};
						$.ajax({
							type : "POST",
							url : "patrolreceipt/commit.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data),
								"ppid" : $("#ppid").val(),
								"sid" : $("#sid").val()
							},
							success : function() {
								$.messager.progress('close');
								$('#dialog').window('close');
								reflush();
								$.messager.alert('操作提示', '操作成功', 'info');
							}
						});
					});

			$("#clear").bind("click", function() {
				$("#patrolplaninfo").form("clear");
			});
			$("#cancle").bind("click", function() {
				$("#dialog").dialog("close");
			});

			function reflush() {
				document.getElementById('patrolreceipt.htmlifm').contentWindow
						.$('#patrolreceiptlist').datagrid('reload');
			}
		});
	</script>

</body>
</html>
