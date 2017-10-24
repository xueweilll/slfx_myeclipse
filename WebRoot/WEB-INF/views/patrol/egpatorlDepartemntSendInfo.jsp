<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"
+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>执行单</title>
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
	<!-- <style>
.datagrid-row-selected {
    background: #ffffff none repeat scroll 0 0;
    color: #000000;
}
</style> -->
	<div align="center">
		<p>
			<img alt="" src="images/zlogo.png"> <span id="titles"
				align="center" style="font-size: 25;">执&nbsp;&nbsp;行&nbsp;&nbsp;单</span>
		</p>
	</div>
	<input id="isid" value="${isid}" type="hidden">
	<input id="did" value="${dids}" type="hidden">
	<input id="look" value="${look}" type="hidden">
	<%-- <input type="hidden" id="type" value="${type}"> --%>
	<form id="ff" method="post" style="margin: 10px; text-align: center;">
		<div style="height:70px;text-align:left;">
			<label>签发单编号:</label> <input class="easyui-textbox" id="code"
				data-options="width:300,height:20" disabled="true" value="${code}">
		</div>
		<div style="height: 70px;text-align: left;">
			<label>执行内容:</label> <input class="easyui-textbox" id="contents"
				data-options="multiline:true,validType:'judgeNull',width:300,height:60" required="true"
				value="${excute.getContent()}">
		</div>
		<div align="center" style="width:100%;height:280px">
			<table id="stationPatrol" cellspacing="0" cellpadding="0"></table>
		</div>
		</br>
		<div id="btn">
			<a id="btnAdd" href="javascript:void(0)" onclick="btnadd(0)"
				class="easyui-linkbutton" data-options="iconCls:'icon_add'">保存</a>&nbsp;&nbsp;
			<a id="btnAdd" href="javascript:void(0)" onclick="btnadd(1)"
				class="easyui-linkbutton" data-options="iconCls:'icon_commit'">保存并提交</a>&nbsp;&nbsp;
			<a id="btnCancel" href="javascript:void(0)" onclick="closed()"
				class="easyui-linkbutton" data-options="iconCls:'icon_delete'">关闭</a>
		</div>
	</form>
	<script type="text/javascript">
	var arr = [];
		$(function(){
			Array.prototype.remove = function(val) {
				var index = this.indexOf(val);
				if (index > -1) {
				this.splice(index, 1);
				}
			};
			if($("#look").val()==1){
				$("#contents").textbox({
					disabled:true
				});
				$("#stationPatrol").datagrid({
					width : 'auto',
					height : 'auto',
					/* pageSize : 20, */
					nowrap : false,
					striped : true,
					border : true,
					collapsible : false,
					fit : true,
					queryParams : {
						did : $("#did").val(),
						isid : $("#isid").val(),
						look:$("#look").val()
					},
					url : 'egpatroldepartmentsend/findstation.html',
					columns:[[
					   /*  {field:'checked',checkbox:true}, */
				        {field:'id',hidden:true},
				        {field:'name',title:'枢纽名称'},
				        {field:'controlwaterlevel',title:'控制水位'}
				    ]],
					remoteSort : false,
					idField : 'id',
					singleSelect : false,				
					rownumbers : true,
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
					onUncheck:function(index,row){
						arr.remove(row.id);			
						//console.info(arr);
					},
					onLoadSuccess:function(data){
						$("#btn").hide();
						if(data){
							$.each(data.rows, function(index, item){
								if(item.checked){
									arr.push(item.id);
								}
							});
						}
					}
				});
			}else{
				$("#stationPatrol").datagrid({
					width : 'auto',
					height : 'auto',
					/* pageSize : 20, */
					nowrap : false,
					striped : true,
					border : true,
					collapsible : false,
					fit : true,
					queryParams : {
						did : $("#did").val(),
						isid : $("#isid").val(),
						look:$("#look").val()
					},
					url : 'egpatroldepartmentsend/findstation.html',
					columns:[[
					    {field:'checked',checkbox:true},
				        {field:'id',hidden:true},
				        {field:'name',title:'枢纽名称'},
				        {field:'controlwaterlevel',title:'控制水位'}
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
					onUncheck:function(index,row){
						arr.remove(row.id);			
						//console.info(arr);
					},
					onLoadSuccess:function(data){ 
						if(data){
							$.each(data.rows, function(index, item){
								if(item.checked){
									arr.push(item.id);
								}
							});
						}
					},
					onCheckAll:function(rows){
						for(var i=0;i< rows.length;i++){
							var isNext=false;
							$.each(arr, function(index, item){
								//console.info(item);
								if(item == rows[i].id){
									isNext=true;
								}
								
							});
							if(!isNext){
								//console.info("123");
								arr.push(rows[i].id);	
							}
						}
						//console.info(arr);
					},
					onUncheckAll:function(rows){
						for(var i=0;i< rows.length;i++){
							arr.remove(rows[i].id);
						}
					}
				});
			}
			
		});
		
		function btnadd(obj){
			var isValid = $("#ff").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				$.messager.alert("操作提示","必填项不能空着", "info");
				return ;
			}
			if(arr.length==0){
				$.messager.alert("操作提示", "请至少选择一个枢纽再进行操作！", "error");
				return false;
			}else{
			/* for(var i=0;i<rows.length;i++){
				//arr.push({"sid":rows[i].id});
				//console.info(arr);
			}  */
			var details = [];
			$.each(arr, function(index, item){
				details.push({"sid":item});
			});
			  var data={
					     'id':obj,//判断保存提交
					     'isid':$("#isid").val(),
					     'content':$("#contents").textbox("getValue").replace(/\s+/g,""),
			    		'patrolSpecialExcuteDetails':details
			    		};
			$.ajax({
				url : "egpatroldepartmentsend/"+(obj=="0" ? "save":"commit")+".html",
				//url : "egpatroldepartmentsend/commit.html",
				type : "POST",
				dataType : "text",
				data : {
					"jsonStr" :JSON.stringify(data)
				},
				success : function(msg) {
					data = eval('(' + msg + ')');
					//console.info(data);
					$.messager.progress('close');
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
		function reflush() {
			document.getElementById('egpatroldepartmentsend.htmlifm').contentWindow
					.$('#sendList').datagrid('reload');
		}
	</script>
</body>
</html>
