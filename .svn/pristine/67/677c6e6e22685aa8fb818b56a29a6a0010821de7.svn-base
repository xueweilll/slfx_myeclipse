<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>签发单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="../demo.css">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
<style>
.datagrid-row-selected {
    background: #ffffff none repeat scroll 0 0;
    color: #000000;
}
</style>
	<div align="center">		
		<p><img alt="" src="images/zlogo.png">
		<span id="titles" align="center" style="font-size: 25;">分&nbsp;&nbsp;解&nbsp;&nbsp;单</span></p>
	</div>
	<input id="id" value="${resolveInfo.id}" type="hidden">
	<form id="ff" method="post"	style="margin: 10px; text-align: center;">		
		<div  style="" align="left">
		
			<div style="float: left;display:none;">
			<label>分解单编号:</label>
			<input class="easyui-textbox" id="code"  required="true" disabled="disabled" data-options="editable:false"  style="width:395" value="${patrolIssue.getId()}" 
			<%-- value="${patrolSpecialResolve.getIsid() }" --%>
			> 
			</div>
			<div style="float: left;padding-bottom:10px;">
			<label>分解单编号:</label>
			<input class="easyui-textbox"  required="true" disabled="disabled" data-options="editable:false"  style="width:395" value="${patrolIssue.getCode()}" 
			<%-- value="${patrolSpecialResolve.getIsid() }" --%>
			> 
			</div>
			<div style="float: left;padding-bottom:15px;">
			<label>签发内容:</label>
			<input class="easyui-textbox" data-options="multiline:true,width:395,height:60" disabled="disabled" data-options="editable:false" required="true"  value="${patrolIssue.getContent() }">
			</div>
			<div style="float: left;padding-bottom:10px;">
			<label>分解内容:</label>
			<input class="easyui-textbox" id="contents" data-options="multiline:true,width:395,height:60" disabled="disabled" data-options="editable:false" required="true"  value="${resolveInfo.getContent() }">
			</div>			
		</div> 
		<br>
		<div  style="float: left;margin-top:10px;width:500px;height:300px;">
			<table id="stationPatrol" cellspacing="0" cellpadding="0"></table>
		</div>
		</br>
		<!-- <div id="btn">
			<a id="btnAdd" href="javascript:void(0)" onclick="add(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon_add'">保存</a>&nbsp;&nbsp;
				<a id="btnAdd"
				href="javascript:void(0)" onclick="add(1)" class="easyui-linkbutton"
				data-options="iconCls:'icon_commit'">保存并提交</a>&nbsp;&nbsp;
				<a id="btnCancel"
				href="javascript:void(0)" onclick="closed()"  class="easyui-linkbutton"
				data-options="iconCls:'icon_delete'">关闭</a>
		</div> -->
	</form>
	<script type="text/javascript">
		
		$(function(){
			$("#stationPatrol").datagrid({
				width : 'auto',
				height : 'auto',
				/* pageSize : 20, */
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				url : 'regularegpatrolengineer/findDepartment.html',
				queryParams:{
					"rid" :$('#id').val()
				},
				columns:[[
				    /* {field:'ck',checkbox:true}, */
				    /* {field:'id',title:'ID'}, */
			        {field:'name',title:'部门名称'}
			    ]],
				remoteSort : false,
				idField : 'id',
				singleSelect : false,				
				rownumbers : true,
				selectOnCheck:true,
				checkOnSelect:true,
				
				
				
				onCheck: function(index,row){	
					var isNext=false;
					$.each(arr, function(index, item){
						if(item == row.id){
							isNext=true;
						}
					});
					if(!isNext){
						arr.push(row.id);	
						//console.info(arr);
					}else{
						//console.info("isNext");
						//console.info(arr);
					}
				},
				/* onUncheck:function(index,row){
					arr.remove(row.id);			
					//console.info(arr);
				}, */
				onLoadSuccess:function(data){
					$("input[type='checkbox']").attr("disabled","disabled");
					$.ajax({
						url:'regularegpatrolengineer/findDetailsByRid.html',
						type : "POST",
						dataType : "text",
						data : {
							"rid" :$('#id').val()
						},
						success : function(msg) {
							var aaa=eval(msg);
							$.each(aaa, function(index, item){
								var rows=	$("#stationPatrol").datagrid('getRows');								
								for(var i=0;i<rows.length;i++){									
									if(rows[i].id==this){
										$("#stationPatrol").datagrid('selectRow',i);
									}
								}	
							});
						}
					})
					/* if(data){
						$.each(data.rows, function(index, item){
							
							if(item.checked){
								arr.push(item.id);
							}
						});
					} */
					
				},
				/* onCheckAll:function(rows){
					for(var i=0;i< rows.length;i++){
						var isNext=false;
						$.each(arr, function(index, item){
							if(item == rows[i].id){
								isNext=true;
							}
						});
						if(!isNext){
							arr.push(rows.id);	
						}
					}
					//console.info(arr);
				}, */
				/* onUncheckAll:function(rows){
					for(var i=0;i< rows.length;i++){
						arr.remove(rows.id);
					}
					//console.info(arr);
				} */
			});
		});
		//********************************************
		var arr=[];
		function add(obj){
			var rows=$("#stationPatrol").datagrid('getSelections');
			if(rows==null){
				$.messager.alert("操作提示", "请至少选择一个枢纽再进行操作！", "error");
				return false;
			}else{
			/* for(var i=0;i<rows.length;i++){
				arr.push(rows[i].id);
				//console.info(arr);
			}
			var details = [];
			$.each(arr, function(index, item){
				details.push({"did":item});
			}); */
			var details2=[];
			for(var i=0;i<rows.length;i++){
				details2.push({"did":rows[i].id});
			}			
			  var data={
					 // "id":$("#id").val(),
					  "id":obj,//判断保存提交
						"isid":$('#code').textbox('getText'),
						"content":$('#contents').textbox('getText'),
			    		'listPatrolSpecialResolveDetails':details2
			    		};
			$.ajax({
				url : "egpatrolengineer/"+(obj=="0" ? "send":"commit")+".html",
				type : "POST",
				dataType : "text",
				data : {
					"jsonStr" :JSON.stringify(data)
				},
				success : function(msg) {
					data = eval('(' + msg + ')');
					console.log(data);
					$.messager.progress('close');
					reflush();
					if(data.result){
						$.messager.show({
							title:'我的消息',
							msg:'执行成功',
							timeout:5000,
							showType:'slide'
						});
					}else{
						$.messager.alert("操作提示", data.msg, "error");
					}
					$('#dialog').window('close');
					reflush();
				}
			});
			}
			
		}

		function closed(){
			$('#dialog').window('close');
		}
		function reflush(){
			document.getElementById('regularegpatrolengineer.htmlifm').contentWindow
			.$('#egpatrolengineerlist').datagrid('reload');
		}
	</script>
</body>
</html>
