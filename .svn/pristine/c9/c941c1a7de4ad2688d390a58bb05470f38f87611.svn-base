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

<title></title>

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
	<div>
		<p>
			<img alt="" src="images/zlogo.png"> <span align="center"
				style="font-size: 25;">执行部门处理单</span>
		</p>
	</div>
	<input id="type" type="hidden" value="${type}" />
	<table id="detail_list" cellspacing="0" cellpadding="0">
	</table>
	<div id="btn" style="margin-top:10px;">
		<a id="reprot" class="easyui-linkbutton" data-options="iconCls:'icon-add'">上报</a>
		<a id="cancel" style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">取消</a>
	</div>

	<script type="text/javascript">
		var details=[];
		
		$(function() {
			var jsonstr = '${jsonStr}';
			
			$("#detail_list").datagrid({
				data : JSON.parse(jsonstr),
				width : 'auto',
				height : '400',
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				//fit : true,
				//url : 'patrolnormaldepartment/pageBind.html',
				remoteSort : false,
				rownumbers : true,
				//pagination : true,
				selectOnCheck : true,
				checkOnSelect : true,

				columns : [ [ {
					field : 'checked',
					width : 30,
					checkbox : true
				}, {
					field : 'patrolid',
					hidden : true,
					title : ''
				}, {
					field : 'sname',
					width : 100,
					title : '枢纽名称'
				}, {
					field : 'degreename',
					width : 100,
					title : '次数'
				}, {
					field : 'text',
					width : 200,
					title : '巡检项'
				}, {
					field : 'contents',
					width : 160,
					title : '巡检内容'
				}, {
					field : 'handletype',
					width : 150,
					title : '处理方式',
					hidden:true
				}] ],
				onCheck: function(index,row){	
					var isNext=false;
					$.each(details, function(index, item){
						if(item == row.patrolid){
							isNext=true;
						}
					});
					if(!isNext){
						details.push(row.patrolid);	
					}else{
					}
					
				},
				onUncheck:function(index,row){
					details.remove(row.patrolid);			
				},
				onCheckAll:function(rows){
					for(var i=0;i< rows.length;i++){
						var isNext=false;
						$.each(details, function(index, item){
							if(item == rows[i].patrolid){
								isNext=true;
							}
						});
						if(!isNext){
							details.push(rows[i].patrolid);	
						}
					}
				},
				onUncheckAll:function(rows){
					for(var i=0;i< rows.length;i++){
						details.remove(rows[i].patrolid);
					}
				}
				
			});
			$("#cancel").bind("click",function(){
				$("#detail_list").datagrid('uncheckAll');
			})
			$("#reprot").bind("click",function(){
				var rows = $("#detail_list").datagrid("getChecked");
				var ds = [];
				var pd=[];
				for(var i=0;i< rows.length;i++){
					pd.push(rows[i].id);
				}
				
				for(var j=0; j < details.length; j++){
					ds.push({"patrolid":details[j],"state":0});
				}
				console.info(ds);
				var json = {
					"id":"0",
					"type":"0",
					"state":0,
					"patrolNormalReportDetails":ds
				}
				var departmentjson={
					"type":0,
					"ids":pd
				}
				$.ajax({
					type : "POST",
					url : "patrolnormaldepartment/save.html",
					dataType : "text",
					data : {
						"jsonStr" : JSON.stringify(json),
						"departmentjson": JSON.stringify(departmentjson)
					},
					success : function(msg) {
						data = eval('(' + msg + ')');
						reflush();
						$.messager.progress('close');
						$('#dialog').window('close');
					
					}
				});
			});
			
			Array.prototype.remove = function(val) {
				var index = this.indexOf(val);
				if (index > -1) {
				this.splice(index, 1);
				}
			};
			
			var type=$("#type").val();
			if(type =="1"){
				$("#btn").hide();
				$('#detail_list').datagrid('hideColumn', 'checked');
				$('#detail_list').datagrid('showColumn', 'handletype');
			}
		});
		
		function reflush() {
			document.getElementById('patrolnormaldepartment.htmlifm').contentWindow.$(
					'#patrolapproval').datagrid('reload');
		}
	</script>
</body>
</html>
