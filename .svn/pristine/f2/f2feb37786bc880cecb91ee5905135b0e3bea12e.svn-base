<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑维修申请</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body>
  <input id="id" type="hidden" value="${mt.getId()}"/>
  <input id="state" type="hidden" value="${mt.getState()}"/>
    <input id="type" type="hidden" value="${type}"/>
	<form id="ff" method="post"
		style="margin: 10px; text-align: center;">
		<div style="float: left;">
			<div style="float: left;">
				<label>编号:</label><input class="easyui-textbox" id="code"
					data-options="disabled:true" style="width:230"
					value="${mt.getCode()}">
			</div>	
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>申请人:</label><input id="applyer" style="width:230" value="${mt.getApplyer()}">
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>申报部门:</label><input  id="department" style="width:230"	value="${mt.getDepartmentid()}">
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>申报时间:</label><input id="applyDate" 	style="width:230" value="${applydate}" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>工程名称:</label><input class="easyui-textbox" id="projectname"	value="${mt.getProjectname()}"
					data-options="required:true" style="width:230" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>施工单位:</label><input id="constructionunits" class="easyui-textbox"
					value="${mt.getConstructionunits()}"
					 style="width:230" />
			</div>
		</div>	
		<div id="st" style="float: left;display:none;">
			<div style="float: left;">
				<label><font color="red">审核不通过步骤:</font></label><input class="easyui-textbox" id="step" value="${step}"
					data-options="disabled:true" style="width:230;color:red;" />
			</div>
		</div>	
		<div id="stm" style="float: left;margin-bottom:10px;display:none;font-color:red;">
			<label><font color="red">不通过意见:</font></label><input class="easyui-textbox" id="memo"
				data-options="disabled:true" value="${mt.getStepmemo()}"
				style="width:575px;height: 60px;">
		</div>	
		<div style="float: left;margin-bottom:10px;">
			<label>备注:</label><input class="easyui-textbox" id="memos"
				data-options="multiline:true" value="${mt.getMemo()}"
				style="width:575px;height: 60px;">
		</div>
		</br>
		<div style="float: left;margin-bottom:2px;">
			<table id="details" cellspacing="0" cellpadding="0"></table>
		</div>	
		<div  id="btn" style="float: left;margin:2px;">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="save" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon_add'">保存</a>&nbsp;&nbsp;
			<a id="commit" href="javascript:void(0)" class="easyui-linkbutton"	data-options="iconCls:'icon_commit'">提交</a>&nbsp;&nbsp;
			<a id="btnCancel" href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon_delete'">关闭</a>
		</div>
		<div id="btnDetails">
			<a id="btnorder" href="javascript:void(0)"  plain="true" class="easyui-linkbutton"
				data-options="iconCls:'icon_add'" >添加</a>&nbsp;&nbsp;<a id="btnRemove"
				href="javascript:void(0)" onclick="distory();" plain="true" class="easyui-linkbutton"
				data-options="iconCls:'icon_delete'" >删除</a>
		</div>

	</form>
	<script type="text/javascript">
	$(function() {	
		var editIndex1 = undefined;
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(3, 0, {
			text: '清空',
			handler: function(target){
				$(target).datetimebox('setValue','');
				$(target).datetimebox('hidePanel');
			}
		});
		
		$("#applyDate").datetimebox({
			required : true,
			showSeconds : true,
			missingMessage : "该输入项为必输项",
			editable : false,
			buttons:buttons
		});
		
		$("#department").combotree({
			url : 'department/departmentsBind.html',
			required : true
		});
		
		$("#applyer").combobox({
			url : 'areareceipt/userList.html',
			valueField : 'id',
			textField : 'name',
			editable : false,
			value : $("#applyer").val(),
			required : true
		});
		
		$("#details").datagrid({
			title : '添加项目明细',
			width : '680',
			height : '350',
			nowrap : false,
			striped : true,
			collapsible : false,
			singleSelect:true,
			url:'maintenance/detailsList.html',
			queryParams: {
				id: $("#id").val()
			},
			rownumbers:true,
			checkOnSelect:false,
			toolbar:'#btnDetails',
		    columns:[[    
			    {field:'itemname',title:'项目名称',width:100,
			    	editor:{
						type:'textbox',
						options:{
							validType:'judgeNull',
							required:true
						}
					}
				},    
			    {field:'materials',title:'所需材料或人工',width:150,
					editor:{
						type:'textbox',
						options:{
							validType:'judgeNull',
							required:true
						}
					}
				},    
			    {field:'unit',title:'单位',width:50,editor:{
						type:'textbox'
					}
				},    
			    {field:'quantity',title:'数量',width:50,editor:{
					type:'textbox',
					options:{
						//validType:'intOrFloat',
						required:true,
						onChange:function(newValue,oldValue){
							if(newValue == null){
								return false;
							}
							if(!isNaN(newValue)){							   
							}else{
							   $.messager.alert('操作错误', '请输入数字！', 'error');
							   var row = $("#details").datagrid("getSelected");
								if (row == null) {
									return false;
								}
								var lastIndex=$("#details").datagrid("getRowIndex",row);
							   var quantity = $('#details').datagrid('getEditor', {index:lastIndex,field:'quantity'});
							   $(quantity.target).textbox('setValue', '');
							}
						}
					}
					}
			    },    
			    {field:'price',title:'单价(元)',width:80,editor:{
					type:'textbox',
					options:{
						//validType:'intOrFloat',
						required:true,
						onChange:function(newValue,oldValue){
							if(newValue == null){
								return false;
							}
							if(!isNaN(newValue)){							   
							}else{
							   $.messager.alert('操作错误', '请输入数字！', 'error');
							   var row = $("#details").datagrid("getSelected");
								if (row == null) {
									return false;
								}
								var lastIndex=$("#details").datagrid("getRowIndex",row);
							   var price = $('#details').datagrid('getEditor', {index:lastIndex,field:'price'});
							   $(price.target).textbox('setValue', '');
							}
						}
					}
					}
			    },    
			    {field:'totalamount',title:'合价(元)',width:80,editor:{
					type:'textbox',
					options:{
						//validType:'intOrFloat',
						required:true,
						onChange:function(newValue,oldValue){
							if(newValue == null){
								return false;
							}
							if(!isNaN(newValue)){							   
							}else{
							   $.messager.alert('操作错误', '请输入数字！', 'error');
							   var row = $("#details").datagrid("getSelected");
								if (row == null) {
									return false;
								}
								var lastIndex=$("#details").datagrid("getRowIndex",row);
							   var totalamount = $('#details').datagrid('getEditor', {index:lastIndex,field:'totalamount'});
							   $(totalamount.target).textbox('setValue', '');
							}
						}
					}
					}
			    },    
			    {field:'memo',title:'备注',width:80,editor:{
					type:'textbox'
					}				
				}
		    ]],
		    onBeforeLoad:function(){
		    	if($("#did").val()==0){
		    		return false;
		    	}
		    },
		    onClickCell : function(index, field){
		    	if (endEditing()) {
		    		$("#details").datagrid('selectRow', index).datagrid('beginEdit', index);
		    		var itemName = $("#details").datagrid('getEditor', {index:index,field:'itemname'});
					var materials = $("#details").datagrid('getEditor', {index:index,field:'materials'});
					var quantity = $("#details").datagrid('getEditor', {index:index,field:'quantity'});
					var price = $("#details").datagrid('getEditor', {index:index,field:'price'});
					var totalAmount = $("#details").datagrid('getEditor', {index:index,field:'totalamount'});
					$(itemName.target).textbox({disabled:false,required:true});
					$(materials.target).textbox({disabled:false,required:true});
					$(quantity.target).textbox({disabled:false,required:true});
					$(price.target).textbox({disabled:false,required:true});
					$(totalAmount.target).textbox({disabled:false,required:true});
		    	}
		    }
		});
					
		$("#btnorder").bind("click",function(){
			if (endEditing()) {
				$("#details").datagrid('appendRow', {});
				editIndex1 = $("#details").datagrid('getRows').length - 1;
				$("#details").datagrid('selectRow', editIndex1).datagrid('beginEdit', editIndex1);
			}
		});
		
		$("#btnRemove").bind("click",function(){
			if (editIndex1 == undefined) {
				return;
			}
			$("#details").datagrid('cancelEdit', editIndex1).datagrid(
					'deleteRow', editIndex1);

			editIndex1 = undefined;
		});
		
		function endEditing() {
			if (editIndex1 == undefined) {
				return true;
			}
			if ($("#details").datagrid('validateRow', editIndex1)) {
				$("#details").datagrid('endEdit', editIndex1);
				editIndex1 = undefined;
				return true;
			} else {
				return false;

			}
		}
		
		$("#commit").bind("click",	function() {			
			var isValid = $("#ff").form('validate');
			if (!isValid){
				$.messager.progress('close');
				return false;
			}
			var rows=$("#details").datagrid("getRows");
			if(rows.length == 0){
				$.messager.progress('close');
				$.messager.alert("操作提示","请添加项目明细！","error");
				return false;
			}
			var id= $("#id").val() == "" ? 0 : $("#id").val();			
			var details = [];
			for (var i = 0; i < rows.length; i++) {
				$("#details").datagrid('endEdit', i);
				details.push({"id":rows[i].id,"maintenanceid":id,"itemname":rows[i].itemname,"materials":rows[i].materials,"unit":rows[i].unit,"quantity":rows[i].quantity,"price":rows[i].price,"totalamount":rows[i].totalamount,"memo":rows[i].memo});
			}
			var data ={
				"id":id,
				"code":$("#code").val(),
				"departmentid":$("#department").combobox("getValue"),
				"projectname":$("#projectname").textbox("getText").replace(/\s+/g,""),
				"constructionunits":$("#constructionunits").textbox("getText").replace(/\s+/g,""),
				"applyer":$("#applyer").combobox("getValue"),
				"applydate":$("#applyDate").datetimebox("getValue"),
				"memo":$("#memos").textbox("getText").replace(/\s+/g,""),
				"state":2,
				"details":details
			};
			$.messager.confirm('确认对话框', '您确定要提交吗？', function(r){
				if (r){
					$.ajax({
						type : "POST",
						url : "maintenance/commit.html",
						dataType : "text",
						data : {
							"jsonStr" :JSON.stringify(data),
							"type":$("#state").val()=="1"?1:0
						},
						success : function() {
							$.messager.progress('close');
							$('#dialog').window('close');
							reflush();
							$.messager.alert('操作提示', '操作成功', 'info');
						}
					});
				}
			});			
		});
		
		$("#save").bind("click",	function() {
			var isValid = $("#ff").form('validate');
			if (!isValid){
				$.messager.progress('close');
				return false;
			}
			var rows=$("#details").datagrid("getRows");
			if(rows.length == 0){
				$.messager.progress('close');
				$.messager.alert("操作提示","请添加项目明细！","error");
				return false;
			}		
			//var index = $("#details").datagrid("selectRows")
			//$("#details").datagrid('endEdit', index);
			var id= $("#id").val() == "" ? 0 : $("#id").val();			
			var details = [];
			for (var i = 0; i < rows.length; i++) {
				$("#details").datagrid('endEdit', i);
				details.push({"id":rows[i].id,"maintenanceid":id,"itemname":rows[i].itemname,"materials":rows[i].materials,"unit":rows[i].unit,"quantity":rows[i].quantity,"price":rows[i].price,"totalamount":rows[i].totalamount,"memo":rows[i].memo});
			}
			var data ={
				"id":id,
				"code":$("#code").val(),
				"departmentid":$("#department").combobox("getValue"),
				"projectname":$("#projectname").textbox("getText").replace(/\s+/g,""),
				"constructionunits":$("#constructionunits").textbox("getText").replace(/\s+/g,""),
				"applyer":$("#applyer").combobox("getValue"),
				"applydate":$("#applyDate").datetimebox("getValue"),
				"memo":$("#memos").textbox("getText").replace(/\s+/g,""),
				"state":$("#state").val()==""?0:$("#state").val(),
				"details":details
			};
					$.ajax({
						type : "POST",
						url : "maintenance/save.html",
						dataType : "text",
						data : {
							"jsonStr" :JSON.stringify(data),
							"type":$("#state").val()=="1"?1:0
						},
						success : function() {
							$.messager.progress('close');
							$('#dialog').window('close');
							reflush();
							$.messager.alert('操作提示', '操作成功', 'info');
						}
					});
		});
		
		function reflush() {
			document.getElementById('maintenance.htmlifm').contentWindow
					.$('#mt_list').datagrid('reload');
		}
		
		var state = $("#state").val();
		if(state == "1"){
			$("#st").show();
			$("#stm").show();
		}
		
		$("#btnCancel").bind("click", function() {
			$("#dialog").dialog("close");
		});
		
		var type=$("#type").val();
		if(type == "1"){
			$("#details").datagrid({
				toolbar:[]
			});
			$("#applyer").combobox({disabled:true});
			$("#projectname").textbox({disabled:true});
			$("#constructionunits").textbox({disabled:true});
			$("#memo").textbox({disabled:true});
			$("#department").combobox({disabled:true});
			$("#applyDate").datetimebox({disabled:true});
			$("#save").hide();
			$("#btnCancel").hide();
			$("#commit").hide();
			//$("#applyer").textbox({disabled:true;});
			//$("#applyer").textbox({disabled:true;});
		}
	});
	</script>
  </body>
</html>
