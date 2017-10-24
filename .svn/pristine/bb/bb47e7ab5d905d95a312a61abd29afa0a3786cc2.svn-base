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
	<input type="hidden" id="sid" value="${id}">
	<input type="hidden" id="storageInfo" value="${storageInfo}">
	<input type="hidden" id="hid" value="${stock.getHandler()}">
	<form id="ff" method="post"
		style="margin:10px; text-align: center;">
		<div style="float: left;">
			<div style="float: left;"><label style="width: 70px;text-align: center;">出库单编号:</label><input class="easyui-textbox" id="code" data-options="width:230,disabled:true"  value="${stock.getCode()}" > </div>
			<div style="float:right;"><label style="width: 70px;text-align: center;">审批人:</label><input id="handler"  type="text"  ></div>
		</div>
		<div style="float: left;margin-top:10px;">
			<label style="width: 70px;text-align: center;" >事由:</label><input  class="easyui-textbox" id="casuse" data-options="multiline:true,validType:'length[0,66]',width:540,height: 60" value="${stock.getCasuse()}" >
		</div>
		<div style="float: left;margin-top:10px;">
			<label style="width: 70px;text-align: center;" >备注:</label><input class="easyui-textbox" id="memo" data-options="multiline:true,validType:'length[0,66]',width:540,height: 60" value="${stock.getMemo()}" >
		</div>
		<div ><table id="inStorageInfo" cellspacing="0" cellpadding="0"></table></div>
		</br>
		<div id="btn">
			<a  id="commit" style="display: none;" href="javascript:void(0)" class="easyui-linkbutton" onclick="commit()" iconCls="icon_commit" >提交</a>
			<a id="btnAdd" href="javascript:void(0)" onclick="add()" class="easyui-linkbutton"
				data-options="iconCls:'icon_save'">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;<a id="btnCancel"
				href="javascript:void(0)" onclick="closed()"  class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'">关闭</a>
		</div>
		<div id="btnDetails">
			<a id="btnorder" href="javascript:void(0)" onclick="addOrder()" class="easyui-linkbutton"
				data-options="iconCls:'icon_add'" plain="true">添加</a>&nbsp;&nbsp;<a id="btnRemove"
				href="javascript:void(0)" onclick="distory();" class="easyui-linkbutton"
				data-options="iconCls:'icon_delete'" plain="true">删除</a>&nbsp;&nbsp;<a id="btnRemove"
				href="javascript:void(0)" onclick="apecct()" class="easyui-linkbutton"
				data-options="iconCls:'icon_save'" plain="true">保存</a>&nbsp;&nbsp;<a id="btnRemove"
				href="javascript:void(0)" onclick="edit()" class="easyui-linkbutton"
				data-options="iconCls:'icon_edit'" plain="true">编辑</a>
		</div>
	</form>
	<script type="text/javascript">
		function closed(){
			$('#dialog').window('close');
		}
		$(function(){
			$('#handler').combogrid({    
			    //value:'006',    
			    idField:'id',
			    width:230,
			    textField:'ename',    
			    url:'outStorage/userBind.html',
			    required:true,
			    editable:false,
			    columns:[[    
			        {field:'dname',title:'部门',width:75},
			        {field:'ename',title:'人员名称',width:75},    
			        {field:'ephone',title:'手机号码',width:100} 
			    ]],
			    onBeforeLoad:function(param){
			    	if($("#storageInfo").val()!=""){
			    		return false;
			    	}
			    },
			    onLoadSuccess:function(node){
			    	if(node.rows.length!=0){
			    		for (var i = 0; i < node.rows.length; i++) {
				    		if(node.rows[i].id==$("#hid").val()){
				    			$("#handler").combogrid("setValue", $("#hid").val());
				    		}
						}
			    	}
			    }    
			});
			if($("#storageInfo").val()!=""){
				$("#handler").combogrid("setText",$("#hid").val());
			}
			if($("#storageInfo").val()=="0"){
				$("#code").attr("disabled",true);
				$("#handler").combogrid({disabled:true,value:$("#hid").val(),onBeforeLoad:function(param){return false;}});
				$("#memo").attr("disabled",true);
				$("#casuse").attr("disabled",true);
				$("#btnAdd").hide();
				$("#commit").hide();
				$("#btnDetails").hide();
			}
			if($("#storageInfo").val()=="1"){
				$("#code").attr("disabled",true);
				$("#handler").combogrid({disabled:true,value:$("#hid").val(),onBeforeLoad:function(param){return false;}});
				$("#memo").attr("disabled",true);
				$("#casuse").attr("disabled",true);
				$("#btnAdd").hide();
				$("#commit").show();
				$("#btnDetails").hide();
			}
			$('#inStorageInfo').datagrid({
				title : '添加出库明细',
				width : '610',
				height : '200',
				nowrap : false,
				striped : true,
				collapsible : false,
				singleSelect:true,
				url:'outStorage/selectStockItems.html',
				rownumbers:true,
				queryParams: {
					id: $("#sid").val()
				},
				idField:'id',
				checkOnSelect:false,
				toolbar:($("#storageInfo").val()=="" ? '#btnDetails':""),
			    columns:[[
			        {field:'id',hidden:true,editor:{
						type:'textbox',
						options:{
							disabled:true
						}
					}},
					{field:'type',title:'物资类别',width:90,formatter:function(val){
						var types="";
						var vals=Number(val);
						switch (vals) {
						case 0:
							types="灯具";
							break;
						case 1:
							types="电器开关";
							break;
						case 2:
							types="工具";
							break;
						case 3:
							types="劳保用品";
							break;
						case 4:
							types="电线电缆";
							break;
						case 5:
							types="油类";
							break;
						case 6:
							types="办公用品";
							break;
						case 7:
							types="生活用具";
							break;
						case 8:
							types="备用备件";
							break;
						default:
							types="无";
							break;
						}
						return types;
					},editor:{
						type:'combobox',
						options:{
							url:'paramers/MaterialType.html',
							required:true,
							editable:false,
							valueField:'id',
							textField:'text',
							onSelect:function(record){
								var ed = $('#inStorageInfo').datagrid('getEditor', {index:editindex,field:'name'});
								$(ed.target).combobox("reload");
								$(ed.target).combobox({disabled:false});
							}
						}
					}},
				    {field:'name',title:'物资名称',width:90,editor:{
							type:'combobox',
							options:{
								url:'outStorage/selectMaterials.html',
								required:true,
								editable:false,
								disabled:true,
								valueField:'name',
								textField:'name',
								onSelect:function(record){
									var ed = $('#inStorageInfo').datagrid('getEditor', {index:editindex,field:'id'});
									var ed1 = $('#inStorageInfo').datagrid('getEditor', {index:editindex,field:'size'});
									var ed2 = $('#inStorageInfo').datagrid('getEditor', {index:editindex,field:'prickle'});
									$(ed.target).textbox("setValue",record.id);
									$(ed1.target).textbox("setValue",record.size);
									$(ed2.target).textbox("setValue",record.prickle);
								},
								onBeforeLoad: function(param){
									var rows=$('#inStorageInfo').datagrid("getRows");
									var str="";
									var ed = $('#inStorageInfo').datagrid('getEditor', {index:editindex,field:'type'});
									var type=$(ed.target).combobox("getValue");
									if(type==""){
										return false;
									}
									for (var i = 0; i < rows.length; i++) {
										var index= $('#inStorageInfo').datagrid('getRowIndex',rows[i]);
										if(rows[i].id!=undefined&&index!=editindex){
											str=rows[i].id+";"+str;
										}
									}
									param.str =str;
									param.type =type;
								}
							}
						}
				    },    
				    {field:'size',title:'规格',width:130,editor:{
						type:'textbox',
						options:{
							disabled:true
						}
					}},     
				    {field:'prickle',title:'计量单位',width:90,editor:{
						type:'textbox',
						options:{
							disabled:true
						}
					}},     
				    {field:'count',title:'出库数量',width:90,editor:{
							type:'textbox',
							options:{
								required:true,
								validType:['minValue[1]','integer']
							}
						}
					},
					{field:'msg',title:'提示信息',width:130}
			    ]],
			    onBeforeLoad:function(){
			    	if($("#sid").val()==0){
			    		return false;
			    	}
			    }
			});
		});
		function add(){
			$.messager.progress();
			if(editindex!=undefined){
				$.messager.progress('close');
				$.messager.alert("操作提示", "请保存出库明细！", "error");
				return false;
			}
			var isValid = $("#ff").form('validate');
			var rows=$('#inStorageInfo').datagrid('getRows');
			var data=[];
			if(rows.length==0){
				$.messager.progress('close');
				$.messager.alert("操作提示", "请添加出库明细！", "error");
				return false;
			}else{
				for (var i = 0; i < rows.length; i++) {
					data.push({"materialid":rows[i].id,"count":rows[i].count});
				}
			}
			if (!isValid){
				$.messager.progress('close');
				return false;
			}
			var json = {
					"id":$("#sid").val(),
					"code":$("#code").textbox("getValue"),
					"handler":$("#handler").combobox("getValue"),
					"items":data,
					"memo":$("#memo").textbox("getValue").replace(/\s+/g,""),
					"casuse":$("#casuse").textbox("getValue").replace(/\s+/g,"")
			}
			$.ajax({
				type : "POST",
				url : "outStorage/save.html",
				dataType : "text",
				data : {
					"jsonStr" : JSON.stringify(json)
				},
				success : function(msg) {
					data = eval('(' + msg + ')');
					$.messager.progress('close');
					if(data.result){
						reflush();
					}else{
						$.messager.alert("操作提示", msg.msg, "error");
					}
					$('#dialog').window('close');
				}
			});
		}
		var editindex=undefined;
		function edit(){
			if(editindex!=undefined){
				$.messager.alert("操作提示", "请先保存出库数据！", "error");
				return false;
			}
			var row = $("#inStorageInfo").datagrid("getSelected");
			if (row == null) {
				$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
				return false;
			}
			var lastIndex=$("#inStorageInfo").datagrid("getRowIndex",row);
			editindex=lastIndex;
			$('#inStorageInfo').datagrid('beginEdit', lastIndex);
			var ed = $('#inStorageInfo').datagrid('getEditor', {index:editindex,field:'name'});
			$(ed.target).combobox({disabled:false,value:row.name});
			
		}
		
		function apecct(){
			if(editindex==undefined){
				return false;
			}
			var isval=$("#inStorageInfo").datagrid("validateRow",editindex);
			if(isval){
				$('#inStorageInfo').datagrid('endEdit', editindex);
			}else{
				$.messager.alert("操作提示", "请选择物资或正确填写出库数量！", "error");
				return false;
			}
			editindex=undefined;
		}
		function addOrder(){
			if(editindex!=undefined){
				$.messager.alert("操作提示", "请先保存出库明细！", "error");
				return;
			}
			var rows=$("#inStorageInfo").datagrid("getRows");
			var lastIndex=rows.length;
			editindex=lastIndex;
			$('#inStorageInfo').datagrid('appendRow',{"count":"1"});
			$('#inStorageInfo').datagrid('beginEdit', lastIndex);
		}
		function distory(){
			var row = $("#inStorageInfo").datagrid("getSelected");
			var index = $("#inStorageInfo").datagrid("getRowIndex",row);
			if (row == null) {
				$.messager.alert("操作提示", "请点击行数字选择一条记录再进行操作！", "error");
				return false;
			}
			$("#inStorageInfo").datagrid("deleteRow",index);
			editindex=undefined;
		}
		
		function reflush() {
			document.getElementById('outStorage.htmlifm').contentWindow.$('#outStorage_list')
					.datagrid('reload');
			document.getElementById('outStorage.htmlifm').contentWindow.$('#outStorage_list')
			.datagrid('unselectAll');
		}
		
		function commit(){
			$.messager.confirm('确认对话框', '您确定要提交吗？', function(r){
				if (r){
					$.messager.progress();
					$.post("outStorage/commit.html",{"id":$("#sid").val()},function(data){
						data = eval('(' + data + ')');
						$.messager.progress('close');
						if(data.result===true){
							reflush();
							$('#dialog').window('close');
						}else if(data.result===1){
							data = eval('(' + data.msg + ')');
							for (var i = 0; i < data.length; i++) {
								index = $("#inStorageInfo").datagrid("getRowIndex",data[i]);
								$('#inStorageInfo').datagrid('updateRow',{
									index: index,
									row: {
										msg: '<span style="color:red;">库存不足</span>'
									}
								});
								$("#commit").linkbutton({disabled:true});
							}
						}else{
							$('#dialog').window('close');
							$.messager.alert("操作提示", data.msg, "error");
						}
					});
				}
			});
		}
	</script>
</body>
</html>
