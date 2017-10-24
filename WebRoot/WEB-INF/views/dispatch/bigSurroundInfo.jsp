<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userInfo.jsp' starting page</title>

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
	<input type="hidden" id="rid" value="${dispatch.getReceiptid()}">
	<input type="hidden" id="did" value="${id}">
	<input type="hidden" id="bigInfo" value="${type}">
	<input type="hidden" id="state" value="${state}">
	<div>		
		<p><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">分&nbsp;&nbsp;解&nbsp;&nbsp;单</span></p>
	</div>
	<form id="ff" method="post"
		style="margin: 10px; text-align: center;">
		<!-- <div  align="center">
			<a class="easyui-linkbutton" style="width: 200px;" data-options="disabled:true,size:'large'">调度单</a>
		</div> -->
		<div style="float: left;" align="left">
			<div style="float: left;"><label>分解单编号:</label><input class="easyui-textbox" id="code" data-options="width:230,disabled:true" required="true"  style="width:200" value="${dcode}"> </div>
			&nbsp;&nbsp;&nbsp;&nbsp;<div style="float:right;"><label>调度令编号:</label><input id="receiptid" required="true" type="text" style="width:200"></div>
		</div>
		<div style="float: left;">
			<div style="float: left;"><label>调度令方式:</label><input class="easyui-textbox" id="way" data-options="disabled:true,width:200" style=""> </div>
			&nbsp;&nbsp;&nbsp;&nbsp;<div style="float:right;">
			<label>调度令发起人:</label><input id="launcher" class="easyui-textbox" data-options="disabled:true" style="width:200">
			<!-- <label>调度发起时间:</label><input id="launchTime"class="easyui-textbox" data-options="disabled:true" style="width:200"> --> </div>
		</div>
		<div style="float: left;">
			<!-- <div style="float: left;"><label>调度令发起人:</label><input class="easyui-textbox" id="launcher" data-options="disabled:true" style="width:200"> </div> 
			&nbsp;&nbsp;&nbsp;&nbsp;--><div style="float:right;">
			<label>调度令接收人:</label><input id="receipter" class="easyui-textbox" data-options="disabled:true" style="width:200">
			<!-- <label>调度结束:</label><input id="endTime" class="easyui-textbox" data-options="disabled:true" style="width:200"> --> </div>
		</div>
		<div style="float: left;">
			<!-- <div style="float: left;"><label>调度令接收人:</label><input class="easyui-textbox" id="receipter" data-options="disabled:true" style="width:200"> </div> -->
			&nbsp;&nbsp;&nbsp;&nbsp;<div style="float:right;"><label>调度令接收时间:</label><input id="receipteTime" class="easyui-textbox" data-options="disabled:true" style="width:200"> </div>
		</div>
		<div style="float: left;margin-top:10px;">
			<label>调度令内容:</label><input class="easyui-textbox" id="rmemo" data-options="disabled:true,multiline:true" style="width:510px;height: 60px;">
		</div>
		<div style="float: left;margin-top:10px;">
			<label>调度备注:</label><input class="easyui-textbox" id="memo" data-options="multiline:true,validType:'length[0,66]'" value="${dispatch.getMemo()}" style="width:510px;height: 60px;">
		</div>
		<div style="float: left;margin-top:10px;margin-bottom:10px;">
			<label>转发内容:</label><input  class="easyui-textbox" id="context" data-options="multiline:true,validType:'length[0,66]'" value="${dispatch.getTransmitcontents()}" style="width:510px;height: 60px;">
		</div>
			</br>
		<div style="display:none"><table id="dispatchingOrder" cellspacing="0" cellpadding="0"></table></div>
			</br>
		<div ><table id="dispatchingstations" cellspacing="0" cellpadding="0"></table></div>
		</br>
		<div id="btn">
			<a id="btnAdd" href="javascript:void(0)" onclick="add(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon_add'">保存</a>&nbsp;&nbsp;<a id="btnAdd1"
				href="javascript:void(0)" onclick="add(1)" class="easyui-linkbutton"
				data-options="iconCls:'icon_commit'">提交</a>&nbsp;&nbsp;<a id="btnCancel"
				href="javascript:void(0)" onclick="closed()"  class="easyui-linkbutton"
				data-options="iconCls:'icon_delete'">关闭</a>
		</div>
		<div id="btnDetails" >
			<a id="btnorder" href="javascript:void(0)" onclick="addOrder()" plain="true" class="easyui-linkbutton"
				data-options="iconCls:'icon_add'" >添加</a>&nbsp;&nbsp;<a id="btnRemove"
				href="javascript:void(0)" onclick="distory();" plain="true" class="easyui-linkbutton"
				data-options="iconCls:'icon_delete'" >删除</a>&nbsp;&nbsp;<a id="btnRemove"
				href="javascript:void(0)" onclick="apecct()" plain="true" class="easyui-linkbutton"
				data-options="iconCls:'icon_save'">保存</a>&nbsp;&nbsp;<a id="btnRemove"
				href="javascript:void(0)" onclick="edit()" plain="true" class="easyui-linkbutton"
				data-options="iconCls:'icon_edit'">编辑</a>
		</div> 
	</form>
	<script type="text/javascript">
		var editIndex = undefined;
		function receiptValue(id){
			$.post("bigSurround/receiptBind.html",{"id":id},function(data){
	    		data = eval('(' + data + ')');
	    		if($("#rid").val()!=""){
					$("#receiptid").val(data.code);
					$('#receiptid').attr("disabled",true);
				}
	    		$("#rmemo").textbox("setValue",data.memo);
	    		$("#way").textbox("setValue",data.way);
	    		$("#launchTime").textbox("setValue",data.launchTime);
	    		$("#launcher").textbox("setValue",data.launcher);
	    		$("#endTime").textbox("setValue",data.endTime);
	    		$("#receipter").textbox("setValue",data.receipter);
	    		$("#receipteTime").textbox("setValue",data.receipteTime);
	    	});
		}
		function closed(){
			$('#dialog').window('close');
		}
		$(function(){
			if($("#bigInfo").val()=="0"){
				$("#code").attr("disabled",true);
				$("#btnAdd1").hide();
				$("#btnAdd").hide();
				$("#btnDetails").hide();
			}
			if($("#rid").val()!=""){
				receiptValue($("#rid").val());
			}else{
				$('#receiptid').combobox({    
					 url:'bigSurround/disReceipt.html',    
					 valueField:'id',    
					 textField:'CODE',
					 width:200,
					 editable:false,
					 onChange:function(newValue){
					   receiptValue(newValue);
					 }
				});
			}
			$('#dispatchingOrder').datagrid({
				title : '添加调度指令',
				width : '610',
				height : 'auto',
				nowrap : false,
				striped : true,
				
				collapsible : false,
				singleSelect:true,
				url:'bigSurround/instructionsBind.html',
				queryParams: {
					id: $("#did").val()					
				},
				rownumbers:true,
				checkOnSelect:false,
				toolbar:($("#bigInfo").val()=="0" ? "":'#btnDetails'),
			    columns:[[    
				    {field:'GateOperate',title:'闸门操作',width:90,
				    	formatter: function(value,row,index){
								if (value==1){
									return "开闸";
								} else if (value==2){
									return "关闸";
								}else{
									return "不操作";
								}
						},editor:{
						type:'combobox',
						options:{
							valueField:'id',
							textField:'text',
							method:'get',
							url:'paramers/gateOperate.html',
							editable:false,
							value:'0',
				    	    onChange:function(newValue,oldValue){
				    	    	var lastIndex=editindex;
								var ed1 = $('#dispatchingOrder').datagrid('getEditor', {index:lastIndex,field:'UnitOperate'});
								var ed2 = $('#dispatchingOrder').datagrid('getEditor', {index:lastIndex,field:'GateOperateTime'});
								var ed3 = $('#dispatchingOrder').datagrid('getEditor', {index:lastIndex,field:'UnitOperateTime'});
								if(newValue!="0"){
				    	    		$(ed2.target).datetimebox({required:true});
				    	    		$(ed2.target).datetimebox({disabled:false});
				    	    	}else{
				    	    		$(ed2.target).datetimebox({disabled:true});
				    	    	}
				    	    	if(newValue=="1"){
				    	    		 var data=$(ed1.target).combobox("getValue"); 
				    	    		if(data=="1"){
				    	    			 $(ed1.target).combobox("setValue","0"); 
				    	    			 $(ed3.target).datetimebox({required:false});
				    	    		}
				    	    	}
				    	    }
						}
					}
					},    
				    {field:'GateOperateTime',title:'操作时间',width:130,editor:{
						type:'datetimebox',
						options:{
							editable:false
						}
					}
					},     
				    {field:'UnitOperate',title:'机组操作',width:90,
						formatter: function(value,row,index){
								if (value==1){
									return "开泵";
								} else if (value==2){
									return "关泵";
								}else{
									return "不操作";
								}
						},editor:{
						type:'combobox',
						options:{
							valueField:'id',
							textField:'text',
							method:'get',
							url:'paramers/unitOperate.html',
							editable:false,
							value:'0',
							onChange:function(newValue,oldValue){
				    	    	var lastIndex=editindex;
								var ed1 = $('#dispatchingOrder').datagrid('getEditor', {index:lastIndex,field:'GateOperate'});
								var ed2 = $('#dispatchingOrder').datagrid('getEditor', {index:lastIndex,field:'GateOperateTime'});
								var ed3 = $('#dispatchingOrder').datagrid('getEditor', {index:lastIndex,field:'UnitOperateTime'});
				    	    	if(newValue!="0"){
				    	    		$(ed3.target).datetimebox({required:true});
				    	    		$(ed3.target).datetimebox({disabled:false});
				    	    	}else{
				    	    		$(ed3.target).datetimebox({disabled:true});
				    	    	}
				    	    	if(newValue=="1"){
				    	    		 var data=$(ed1.target).combobox("getValue"); 
				    	    		if(data=="1"){
				    	    			 $(ed1.target).combobox("setValue","0");
				    	    			 $(ed2.target).datetimebox({required:false});
				    	    		}
				    	    	}
				    	    }
						}
					}
					},     
				    {field:'UnitOperateTime',title:'操作时间',width:130,editor:{
							type:'datetimebox',
							options:{
								editable:false
							}
						}
					}
			    ]],
			    onBeforeLoad:function(){
			    	if($("#did").val()==0){
			    		return false;
			    	}
			    }
			});
			$('#dispatchingstations').datagrid({
				title : '调度枢纽',
				width : '610',
				height : 'auto',
				url:'bigSurround/stationsBind.html',
				nowrap : false,
				striped : true,
				collapsible : false,
				idField : 'id',
				rownumbers:true,
				checkOnSelect:false,
				queryParams: {
					id: $("#did").val(),
					state : $("#state").val()
				},
				frozenColumns:[[{field:'ck',checkbox:($("#bigInfo").val()=="0" ? false:true)}]],
			    columns:[[    
			        {field:'name',title:'枢纽',width:150} ,
			        {
		            	field:'runcount',
		            	title:'运行数量',
		            	width:80,
		            	editor:{
		            		type:'numberspinner',
		            		options:{
		            			/* min:0,  */
		            			value:0,
		                    	editable:false
		            		}
		            	}
		            },{
		            	field:'keepcount',
		            	title:'保持数量',
		            	width:80,
		            	editor:{
		            		type:'numberspinner',
		            		options:{
		            			value:0,
		                    	editable:false
		            		}
		            	}
		            },{
		            	field:'gatetype',
		            	title:'闸门状态',
		            	width:80,
		            	editor:{
		            		type:'combobox',
		            		options:{
		            			valueField:'id',
		                        textField:'text',
		                        method:'post',
		                        url:'paramers/gateType.html',
		                        editable:false
		            		}
		            	},
		            	formatter: function(value,row,index){
		    				if (row.gatetype == "0"){
		    					return "关闸";
		    				} else if (row.gatetype == "1"){
		    					return "开闸";
		    				}
		    			}
		            }
			    ]] ,
			    onLoadSuccess:function(data){
			    	if($("#rid").val()!=""){
	    				var rows=data.rows;
			    		for (var i = 0; i < rows.length; i++) {
							if(rows[i].code){
					    		var index=$('#dispatchingstations').datagrid("getRowIndex",rows[i].id);
					    		$('#dispatchingstations').datagrid("checkRow",index);
							}
						}		
			    	}else{
				    	$('#dispatchingstations').datagrid("checkAll");
				    	$('#dispatchingstations').datagrid("unselectAll");
			    	}
			    },
			    onCheckAll : function(rows){
			    	$('#dispatchingstations').datagrid("unselectAll");
			    	if($("#receiptid").combobox("getValue") == ""){
			    		return false;
			    	}
			    	var s=[];
			    	for(var i =0;i < rows.length; i++){
			    		s.push(rows[i].id);
			    	}
			    	$.ajax({
						type : "POST",
						url : "bigSurround/keepCountList.html",
						dataType : "text",
						data : {
							"json" : JSON.stringify(s),
							"receiptid" : ($("#rid").val() == "" ? $("#receiptid").combobox("getValue") : $("#rid").val())
						},
						success : function(msg) {
							data = eval('(' + msg + ')');
							for(var i= 0; i< data.length; i++){
								for(var j =0;j < rows.length; j++){
									if(data[i].sid  == rows[j].id){
							         	// 得到columns对象
							            var columns = $('#dispatchingstations').datagrid("options").columns;
							            // 得到rows对象
							            rows[j][columns[0][2].field]=data[i].runcount;
							            // 刷新该行, 只有刷新了才有效果
							            $('#dispatchingstations').datagrid('refreshRow', j);
										break;
									}
								}								
							}
						}
					}); 
			    },
			    onCheck : function(index,row){
			    	if($("#receiptid").combobox("getValue") == ""){
			    		return false;
			    	}
			    	var s=[];
			    	s.push(row.id);
			    	$.ajax({
						type : "POST",
						url : "bigSurround/keepCountList.html",
						dataType : "text",
						data : {
							"json" : JSON.stringify(s),
							"receiptid" : ($("#rid").val() == "" ? $("#receiptid").combobox("getValue") : $("#rid").val())
						},
						success : function(msg) {
							data = eval('(' + msg + ')');
							for(var i= 0; i< data.length; i++){
								var columns = $('#dispatchingstations').datagrid("options").columns;
					            // 得到rows对象
					            row[columns[0][2].field]=data[i].runcount;
					            // 刷新该行, 只有刷新了才有效果
					            $('#dispatchingstations').datagrid('refreshRow', index);
													
							}
						}
					}); 
			    },
			    onClickCell: onClickCell,
		        onEndEdit: onEndEdit	   
			});
		});
		
		
		function onClickCell(index, field) {
			if (editIndex != index) {
				if (endEditing()) {
					$('#dispatchingstations').datagrid('selectRow', index).datagrid('beginEdit', index);
					var ed = $('#dispatchingstations').datagrid('getEditor', {
						index : index,
						field : field
					});
					if (ed) {
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
					}
					editIndex = index;
				} else {
					setTimeout(function() {
						$('#dispatchingstations').datagrid('selectRow',	editIndex);
					}, 0);
				}
			}
		}

		function onEndEdit(index, row) {
			var ed1 = $(this).datagrid('getEditor', {
				index : index,
				field : 'name'
			});
			//row.sid = $(ed1.target).combobox('getText');
		}

		function endEditing() {
			if (editIndex == undefined) {
				return true;
			}
			if ($('#dispatchingstations').datagrid('validateRow', editIndex)) {
				$('#dispatchingstations').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}

		function add(type) {
			var isValid = $("#ff").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				return false;
			}
			var rows1 = $("#dispatchingOrder").datagrid("getRows");
			var lastIndex = rows1.length - 1;
			if (editindex != undefined) {
				lastIndex = editindex;
			}
			var row = $("#dispatchingOrder").datagrid("getEditors", lastIndex);
			if (row != "") {
				$.messager.progress('close');
				$.messager.alert("操作提示", "请先保存调度指令！", "error");
				return false;
			}
			
			if (!endEditing()) {
				return false;
			}
			
			/* 	if(rows1.length==0){
					$.messager.progress('close');
					$.messager.alert("操作提示","请添加调度指令！","error");
					return false;
				} *///调度指令判断
			var rows = $("#dispatchingstations").datagrid("getChecked");
			if (rows.length == 0) {
				$.messager.progress('close');
				$.messager.alert("操作提示", "请添调度枢纽！", "error");
				return false;
			}
			var data = [];
			for (var i = 0; i < rows1.length; i++) {
				var str1 = "0";
				var str = "0";
				var date = "";
				var date1 = "";
				if (rows1[i].GateOperate != null) {
					str = rows1[i].GateOperate;
					date = rows1[i].GateOperateTime;
				}
				if (rows1[i].UnitOperate != null) {
					str1 = rows1[i].UnitOperate;
					date1 = rows1[i].UnitOperateTime;
				}
				var time = new Date();
				var strtime = time.getFullYear() + "-" + (time.getMonth() + 1)
						+ "-" + time.getDate() + " " + time.getHours() + ":"
						+ time.getMinutes() + ":" + time.getSeconds();
				data.push({
					"gateoperatet" : str,
					"gateoperatetime" : (date == "" ? strtime : date),
					"unitoperate" : str1,
					"unitoperatetime" : (date1 == "" ? strtime : date1)
				});
			}
			var data1 = [];
			for (var i = 0; i < rows.length; i++) {
				var keepcount="";
				var gatetype="";
				var runcount="";
				console.info(rows[i].runcount);
				console.info(rows[i].keepcount);			
				console.info(rows[i].gatetype);
				if(rows[i].keepcount == undefined){
					keepcount="";
				}else {
					keepcount = rows[i].keepcount;
				}
				if(rows[i].gatetype == undefined){
					gatetype="";
				}else {
					gatetype = rows[i].gatetype;
				}
				if(rows[i].runcount == undefined){
					runcount="";
				}else {runcount
					runcount = rows[i].runcount;
				}
				data1.push({
					"sid" : rows[i].id,
					"runcount":runcount,
					"keepcount":keepcount,
					"gatetype":gatetype
				});
			}
			var json = {
				"id" : $("#did").val(),
				"code" : $("#code").textbox("getValue"),
				"receiptid" : ($("#rid").val() == "" ? $("#receiptid")
						.combobox("getValue") : $("#rid").val()),
				"receiptDispatchInstructions" : data,
				"dispatchStations" : data1,
				"memo" : $("#memo").textbox("getValue").replace(/\s+/g, ""),
				"transmitcontents" : $("#context").textbox("getValue").replace(/\s+/g, ""),
				"dispatchtype" : type
			}
			if (type == "1") {
				$.messager.confirm('确认对话框', '您确定要提交吗？', function(r) {
					if (r) {
						$.ajax({
							type : "POST",
							url : "bigSurround/"+ (type == "0" ? "save" : "commit")+ ".html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(json)
							},
							success : function(msg) {
								data = eval('(' + msg + ')');
								$.messager.progress('close');
								if (data.result) {
									reflush();
								}
								$('#dialog').window('close');
							}
						});
					}
				});
			} else {
				$.ajax({
					type : "POST",
					url : "bigSurround/" + (type == "0" ? "save" : "commit")+ ".html",
					dataType : "text",
					data : {
						"jsonStr" : JSON.stringify(json)
					},
					success : function(msg) {
						data = eval('(' + msg + ')');
						$.messager.progress('close');
						if (data.result) {
							reflush();
						}
						$('#dialog').window('close');
					}
				});
			}

		}
		var editindex = undefined;
		function edit() {
			//调度指令
			/* if(editindex!=undefined){
				$.messager.alert("操作提示", "请先保存调度指令！", "error");
				return false;
			} */
			var row = $("#dispatchingOrder").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			var lastIndex = $("#dispatchingOrder").datagrid("getRowIndex", row);
			editindex = lastIndex;
			$('#dispatchingOrder').datagrid('beginEdit', lastIndex);
			var ed1 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'GateOperate'
			});
			var ed2 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'GateOperateTime'
			});
			var ed3 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'UnitOperate'
			});
			var ed4 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'UnitOperateTime'
			});
			if (editindex == 0) {
				var datetime = $(ed2.target).datetimebox("getValue");
				$(ed1.target).combobox({
					disabled : true
				});
				$(ed1.target).combobox("setValue", "2");
				$(ed2.target).datetimebox("setValue", datetime);
			}
			if ($(ed1.target).combobox("getValue") == 0) {
				$(ed2.target).datetimebox({
					disabled : true
				});
			}
			if ($(ed3.target).combobox("getValue") == 0) {
				$(ed4.target).datetimebox({
					disabled : true
				});
			}
		}

		function apecct() {
			if (editindex != undefined) {
				lastIndex = editindex;
			} else {
				var lastIndex = $("#dispatchingOrder").datagrid("getRows").length - 1;
			}
			var row = $("#dispatchingOrder").datagrid("getEditors", lastIndex);
			if (row == "") {
				return false;
			}
			var isval = $("#dispatchingOrder").datagrid("validateRow",
					lastIndex);
			var ed1 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'GateOperate'
			});
			var ed2 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'GateOperateTime'
			});
			var ed3 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'UnitOperate'
			});
			var ed4 = $('#dispatchingOrder').datagrid('getEditor', {
				index : lastIndex,
				field : 'UnitOperateTime'
			});
			if (isval) {
				var data1 = $(ed1.target).combobox("getValue");
				var data2 = $(ed3.target).combobox("getValue");
				if (data1 == "0" && data2 == "0") {
					$("#dispatchingOrder").datagrid("deleteRow", lastIndex);
				} else {
					$("#dispatchingOrder").datagrid("endEdit", lastIndex);
				}
			} else {
				$.messager.alert("操作提示", "请选择操作时间！", "error");
			}
			editindex = undefined;
		}
		function addOrder() {
			var rows = $("#dispatchingOrder").datagrid("getRows");
			var lastIndex = rows.length;
			editindex = lastIndex;
			if (rows.length == 0) {
				var row = $("#dispatchingOrder").datagrid("getEditors",
						lastIndex);
				if (row != "") {
					return false;
				}
				$("#dispatchingOrder").datagrid("appendRow", {
					"GateOperate" : "2",
					"UnitOperate" : "0"
				});
				$('#dispatchingOrder').datagrid('beginEdit', lastIndex);
				var ed1 = $('#dispatchingOrder').datagrid('getEditor', {
					index : lastIndex,
					field : 'GateOperate'
				});
				var ed2 = $('#dispatchingOrder').datagrid('getEditor', {
					index : lastIndex,
					field : 'GateOperateTime'
				});
				var ed4 = $('#dispatchingOrder').datagrid('getEditor', {
					index : lastIndex,
					field : 'UnitOperateTime'
				});
				$(ed1.target).combobox({
					disabled : true
				});
				$(ed4.target).datetimebox({
					disabled : true
				});
				$(ed1.target).combobox("setValue", "2");
			} else {
				var row = $("#dispatchingOrder").datagrid("getEditors",
						lastIndex - 1);
				if (row != "") {
					return false;
				}
				$("#dispatchingOrder").datagrid("appendRow", {
					"GateOperate" : "0",
					"UnitOperate" : "0"
				});
				$('#dispatchingOrder').datagrid('beginEdit', lastIndex);
				var ed1 = $('#dispatchingOrder').datagrid('getEditor', {
					index : lastIndex,
					field : 'GateOperate'
				});
				var ed2 = $('#dispatchingOrder').datagrid('getEditor', {
					index : lastIndex,
					field : 'GateOperateTime'
				});
				var ed4 = $('#dispatchingOrder').datagrid('getEditor', {
					index : lastIndex,
					field : 'UnitOperateTime'
				});
				$(ed2.target).datetimebox({
					disabled : true
				});
				$(ed4.target).datetimebox({
					disabled : true
				});
			}
		}
		function distory() {
			var row = $("#dispatchingOrder").datagrid("getSelected");
			var index = $("#dispatchingOrder").datagrid("getRowIndex", row);
			if (index == "0") {
				$.messager.alert("操作提示", "首条记录不能删除！", "error");
				return false;
			}
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			var rows = $("#dispatchingOrder").datagrid("getRows");
			$("#dispatchingOrder").datagrid("deleteRow", rows.length - 1);
		}

		function reflush() {
			document.getElementById('bigSurround.htmlifm').contentWindow.$(
					'#bigSurround_list').datagrid('reload');
			document.getElementById('bigSurround.htmlifm').contentWindow.$(
					'#bigSurround_list').datagrid('unselectAll');
		}
	</script>
</body>
</html>
