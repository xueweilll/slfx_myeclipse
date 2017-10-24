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
				style="font-size: 25;">执行部门上报单</span>
		</p>
	</div>
	<table id="detail_list" cellspacing="0" cellpadding="0">
	</table>
	<div id="btn" style="margin-top:10px;">
		<a id="reprot" class="easyui-linkbutton" data-options="iconCls:'icon-add'">上报</a>
		<a id="cancel" style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">取消</a>
	</div>

	<script type="text/javascript">
		var details=[];
		
		$(function() {
			var stcds=new Array();
			var isids=new Array();
			var jsonstr = '${jsonStr}';
			var look='${look}';
			if(look=="1"){
				$("#btn").hide();
			}else{
                 $("#btn").show();				
			}
			$("#detail_list").datagrid({
				//data : JSON.parse(jsonstr),
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
				columns : [ [  {
					field : 'SID',
					hidden : true,
					title : ''
				}, {
					field : 'IMPLEMENTID',
					hidden : true,
					title : ''
				},{
					field : 'ISID',
					hidden : true,
					title : ''
				},{
					field : 'NAME',
					width : 100,
					title : '枢纽名称'
				}, {
					field : 'CLASSES',
					width : 100,
					title : '一级'
				}, {
					field : 'TNAME',
					width : 150,
					title : '二级'
				}, {
					field : 'REMARK',
					width : 200,
					title : '备注'
				}] ]
			});
			//console.info(JSON.parse(jsonstr));
			$("#detail_list").datagrid("loadData",JSON.parse(jsonstr));
			$("#reprot").bind("click",function(){
				var rows = $("#detail_list").datagrid("getRows");
				var details=[];
				for(var i=0;i< rows.length;i++){
					var isNext=false;
					$.each(stcds, function(index, item){
						if(item == rows[i].IMPLEMENTID){
							isNext=true;
						}
					});
					if(!isNext){
						stcds.push(rows[i].IMPLEMENTID);
						isids.push(rows[i].ISID);
					}
				}
				for(var i =0;i< stcds.length;i++){
					details.push({"implementid":stcds[i]});
				}
				var data={
					"id":"0",
					"isids":isids,
					"details":details
				};
				$.ajax({
					type : "POST",
					url : "psdepartmentreport/save.html",
					dataType : "text",
					data : {
						"jsonStr" : JSON.stringify(data)
					},
					success : function(msg) {
						data = eval('(' + msg + ')');
						$.messager.progress('close');
						$('#dialog').window('close');
						reflush();
					}
				});
			});
			$("#cancel").bind("click",function(){ 
				$('#dialog').window('close');
			});
			
		});
		
		Array.prototype.remove = function(val) {
			var index = this.indexOf(val);
			if (index > -1) {
			this.splice(index, 1);
			}
		};
		
		
		Array.prototype.indexOf = function(val) {
			for (var i = 0; i < this.length; i++) {
				if (this[i] == val) return i;
			}
			return -1;
		};
		function reflush() {
			document.getElementById('regularpatrolspecialdepartmentreport.htmlifm').contentWindow
					.$('#patrolapproval').datagrid('reload');
		}
	</script>
</body>
</html>
