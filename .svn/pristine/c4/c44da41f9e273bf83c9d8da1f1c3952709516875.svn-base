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

<title>新增/编辑片区调度下发</title>

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
	<input type="hidden" id="rid" value="${rd.getReceiptid()}">
	<input type="hidden" id="departmentid" value="${departmentid}">
	<input type="hidden" id="did" value="${rd.getId()}">
	<input type="hidden" id="isDisplay" value="${isDisplay}" />
	<div>		
		<p><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">执&nbsp;&nbsp;行&nbsp;&nbsp;单</span></p>
	</div>
	
	<form id="ff" method="post"
		style="margin: 2px;margin-left:10px; text-align: center;">
		<div style="float: left;">
			<div style="float: left;">
				<label>分解单编号:</label><input class="easyui-textbox" id="code"
					data-options="disabled:true,width:230" style="width:230"
					value="${rd.getCode()}">
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令编号:</label><input id="receiptid" class="easyui-textbox"
					data-options="disabled:true,width:230"
					value="${rd.getReceipt().getCode()}" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令方式:</label><input class="easyui-textbox" id="way"
					value="${typeName}"
					data-options="disabled:true,width:230" style="" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令发起时间:</label><input id="launchTime" class="easyui-textbox"
					value="${launchtime}"
					data-options="disabled:true" style="width:230" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令发起人:</label><input class="easyui-textbox" id="launcher"
					data-options="disabled:true" style="width:230"
					value="${rd.getReceipt().getLauncher()}">
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令结束时间:</label><input id="endTime" class="easyui-textbox"
					data-options="disabled:true" style="width:230"
					value="${endtime}" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令接收人:</label><input class="easyui-textbox" id="receipter"
					data-options="disabled:true" style="width:230"
					value="${rd.getNames().getReceiptName()}" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令接收时间:</label><input id="receipteTime"
					class="easyui-textbox" data-options="disabled:true"
					style="width:230" value="${receiptetime}" />
			</div>
		</div>
		</br>
		<div style="float: left;margin-bottom:10px; margin-top:10px;">
			<label>签发备注:</label><input class="easyui-textbox" id="rmemo"
				data-options="disabled:true,multiline:true"
				style="width:575px;height: 60px;"
				value="${rd.getReceipt().getMemo()}">
		</div>
		</br>
		<div style="float: left;margin-bottom:10px;">
			<label>分解备注:</label><input class="easyui-textbox" id="memo"
				data-options="multiline:true,disabled:true" value="${rd.getMemo()}"
				style="width:575px;height: 60px;">
		</div>
		</br>
		<div style='display:none;'>
			<table id="dispatchingOrder" cellspacing="0" cellpadding="0"></table>
		</div>
		</br>
		<div>
			<table id="dispatchingstations" cellspacing="0" cellpadding="0"></table>
		</div>
		<div id="btn" style="margin-top:5px;">
			<a id="send" href="javascript:void(0)" 
				class="easyui-linkbutton" data-options="iconCls:'icon-tip'">执行</a>
		</div>

	</form>
	<script type="text/javascript">
	$(function(){
		
		$('#dispatchingOrder').datagrid({
			title : '调度指令明细',
			width : '675',
			height : '200',
			nowrap : false,
			striped : true,
			collapsible : false,
			idField : 'id',
			url:'receiptDispatch/instructionsBind.html',
			queryParams: {
				id: $("#did").val()
				
			},
			rownumbers:true,
			checkOnSelect:false,
		    columns:[[   
	            {field:'GateOperate',title:'闸门操作',width:90},
	            {field:'GateOperateTime',title:'操作时间',width:130},
	            {field:'UnitOperate',title:'机组操作',width:90},
	            {field:'UnitOperateTime',title:'操作时间',width:130}
		    ]] 
		});
		$('#dispatchingstations').datagrid({
			title : '调度枢纽明细',
			width : '675',
			height : '200',
			url:'receiptDispatch/stationBind.html',
			queryParams: {
				id: $("#did").val(),
				departmentids : $("#departmentid").val()
			},
			nowrap : false,
			striped : true,
			collapsible : false,
			idField : 'id',
			rownumbers:true,
			checkOnSelect:false,
		    columns:[[    
		        {field:'stationname',title:'枢纽名称',width:150},
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
	            		type:'textbox',
	            		options:{
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
	                        /* method:'post',
	                        url:'paramers/gateType.html', */
	                        editable:false
	            		}
	            	}
	            }
		    ]]		   
		});
		
		if($("#isDisplay").val() == "1"){
			//document.getElementById("btnCancel").style.display="none"; 
			$("#send").hide();
		}
		
		$("#send").bind("click",function(){
			$.messager.confirm('确认对话框', '是否执行?', function(r){
				if (r){
					$.ajax({
						type : "POST",
						url : "receiptDispatch/send.html",
						dataType : "text",
						data : {
							"id" : $("#did").val(),
							"departmentid" : $("#departmentid").val()
						},
						success : function() {
							reflush();
							$('#dialog').window('close');
						}
					});
				}
			});		
		});
	});
	
	function reflush() {
		document.getElementById('receiptDispatch.htmlifm').contentWindow
				.$('#rd_list').datagrid('reload');
	}
	</script>
</body>
</html>
