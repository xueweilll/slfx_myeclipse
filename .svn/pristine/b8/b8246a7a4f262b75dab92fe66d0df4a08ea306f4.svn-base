<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加统计明细信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <body>
  <input id="id" type="hidden" value="${id}" />
  <input id="type" type="hidden" value="${type}"/>
	<div id="l" style="height:50%;">
		<table id="list" cellspacing="0" cellpadding="0" data-options="">
			<thead>
				<tr>
					<th data-options="field:'departmentname',width:100">执行部门</th>
					<th data-options="field:'sendername',width:120">部门负责人</th>
					<th data-options="field:'sendtime',width:180">执行时间</th>
					<th data-options="field:'stationname',width:150">枢纽名称</th>
					<th data-options="field:'usernames',width:150">实施人员</th>
					<th data-options="field:'memo',width:350">实施备注</th>
				</tr>
			</thead>
		</table>
	</div>
	<div style="margin-top:0px;"></div>
	<table id="hf" cellspacing="0" cellpadding="0" data-options=""
		style="margin-top:1px;">

	</table>

	<table id="dc" cellspacing="0" cellpadding="0" data-options=""
		style="margin-top:1px;">

	</table>
	</div>
	<script type="text/javascript">
		$(function() {
			var type = $("#type").val();
			$("#list").datagrid({
				title : '统计明细列表',
				width : 'auto',
				height : 'auto',
				pageSize : 20,
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				url : 'exertionrecord/detailsBind.html',
				queryParams: {
					id:$("#id").val(),
					type:$("#type").val()
				},
				remoteSort : false,
				singleSelect : true,
				fitColumns:true,
				view: detailview,
				detailFormatter:function(index,row){
					if(type=="1" || type=="2"){
						return "<div style=\"padding:2px\"><table class=\"unitList\">"+
						"</table><table class=\"gateList\"></table></div>";
					}else {
						return "<div style=\"padding:2px\"><table class=\"unitList\">"+
						"</table><table class=\"gateList\"></table>";					}
				},
				onExpandRow: function(index,row){
					var ddv = $(this).datagrid('getRowDetail',index).find('table.unitList');
					var uurl;
					/* if(type=="0"){ */ //0原先为自主
						if(type=="2"){// 2为自主
						uurl="sdexecute/findUnitByExecuteid.html?executeid="+row.rdeid;
					}else {
						uurl="rdexecute/findUnitByExecuteid.html?executeid="+row.rdeid;
					}
					ddv.datagrid({
						url:uurl,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'机组运行记录',
						height:'auto',
						columns:[[
							{field:'unit',title:'机组名称',width:150},
							{field:'begintime',title:'开始时间',width:150},
							{field:'endtime',title:'结束时间',width:150}
						]],
						onResize:function(){
							$('#list').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#list').datagrid('fixDetailRowHeight',index);
							},0);
						}
					});
					var gatelist = $(this).datagrid('getRowDetail',index).find('table.gateList');
					var gurl;
					/* if(type=="0"){ 0原先为自主*/
					if(type=="2"){// 2为自主
					
						gurl="sdexecute/findGateByExecuteid.html?executeid="+row.rdeid;
					}else {
						gurl="rdexecute/findGateByExecuteid.html?executeid="+row.rdeid;
					}
					gatelist.datagrid({
						url:gurl,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'闸门运行记录',
						height:'auto',
						columns:[[
							{field:'sid',title:'闸门名称',width:150},
							{field:'operatetime',title:'闸门执行时间',width:150},
							{field:'operate',title:'操作',width:150}
						]],
						onResize:function(){
							$('#list').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#list').datagrid('fixDetailRowHeight',index);
							},0);
						}
					});
					/* if(type == "2"){ 原先为大包围*/
						if(type=="0"){
						var cb_list = $(this).datagrid('getRowDetail',index).find('table.cbList');
						cb_list.datagrid({
							title : '回访内容明细',
							width : 'auto',
							height:'auto',
							nowrap : false,
							striped : true,
							collapsible : false,
							idField : 'id',
							url : 'callback/cbListBind.html',
							queryParams : {
								rdid : $("#id").val()
							},
							rownumbers : true,
							columns : [ [ 
				              {field : 'callbackpeople',title : '回访人',width : 90}, 
				              {field : 'callbacktime',title : '回访时间',width : 130}, 
				              {field : 'callbackunit',title : '回访单位',width : 90}, 
				              {field : 'operater',title : '操作人',width : 60}, 
				              {field : 'callback',title : '回访内容',width : 150} 
				              ] ],
							onResize:function(){
								$('#list').datagrid('fixDetailRowHeight',index);
							},
							onLoadSuccess:function(){
								setTimeout(function(){
									$('#list').datagrid('fixDetailRowHeight',index);
								},0);
							}
						});
						var sp_list = $(this).datagrid('getRowDetail',index).find('table.suList');
						sp_list.datagrid({
							title : '督察内容明细',
							width : 'auto',
							height:'auto',
							nowrap : false,
							striped : true,
							collapsible : false,
							idField : 'id',
							url : 'supervise/spListBind.html',
							queryParams : {
								rdid : $("#id").val()
							},
							rownumbers : true,
							columns : [ [ 
								{field : 'callbackpeople',title : '督察人',width : 90}, 
								{field : 'callbacktime',title : '督察时间',width : 130}, 
								{field : 'callbackunit',title : '督察单位',width : 90}, 
								{field : 'operater',title : '操作人',width : 60}, 
								{field : 'callback',title : '督察内容',width : 150} ] ],
							onResize:function(){
								$('#list').datagrid('fixDetailRowHeight',index);
							},
							onLoadSuccess:function(){
								setTimeout(function(){
									$('#list').datagrid('fixDetailRowHeight',index);
								},0);
							}
						});
					}
					$('#list').datagrid('x',index);
				}
			});
			
			
			
			if(type =="0"){
				$("#hf").datagrid({
					title : '回访内容明细',
					width : 'auto',
					height:'auto',
					nowrap : false,
					striped : true,
					collapsible : false,
					idField : 'id',
					url : 'callback/cbListBind.html',
					queryParams : {
						rdid : $("#id").val()
					},
					rownumbers : true,
					columns : [ [ 
		              {field : 'callbackpeople',title : '回访人',width : 90}, 
		              {field : 'callbacktime',title : '回访时间',width : 130}, 
		              {field : 'callbackunit',title : '回访单位',width : 90}, 
		              {field : 'operater',title : '操作人',width : 100}, 
		              {field : 'callback',title : '回访内容',width : 150} 
		              ] ],
					
				});
				
				$("#dc").datagrid({
					title : '督察内容明细',
					width : 'auto',
					height:'auto',
					nowrap : false,
					striped : true,
					collapsible : false,
					idField : 'id',
					url : 'supervise/spListBind.html',
					queryParams : {
						rdid : $("#id").val()
					},
					rownumbers : true,
					columns : [ [ 
						{field : 'callbackpeople',title : '督察人',width : 90}, 
						{field : 'callbacktime',title : '督察时间',width : 130}, 
						{field : 'callbackunit',title : '督察单位',width : 90}, 
						{field : 'operater',title : '操作人',width : 100}, 
						{field : 'callback',title : '督察内容',width : 150} ] ]
				});
			}else{
				$("#l").css("height", "99.7%");//通过设置CSS属性来设置元素的高
			}
				
			
			
		});
	</script>
  </body>
</html>
