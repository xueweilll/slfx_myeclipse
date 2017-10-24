<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>执行部门审核</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 	<style>
	.datagrid-row-selected {
	    background:  none repeat scroll 0 0;
	    color: #000000;
	}
	</style> -->
<script>
		$(function(){
			var stcds=new Array();
			var codes=new Array();
			$("#Edit").click(function (){
				var Item=$('#patrolapproval').datagrid('getSelected');
				if(Item==null){
					$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
				}else if(Item.Levels=="未汇总"){
					showDialogWH("审批巡检记录", "psdepartmentreport/patrolnormaldepartmentInfo.html?patrolplandetailid="+stcds+"&isid="+Item.isid+"&look=0",800,600);
				}else if(Item.Levels=="已汇总"){
					alert("该记录已审批");
				}
			});
			var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
			buttons.splice(1, 0, {
				text: '清空',
				handler: function(target){
					$(target).datetimebox('setValue','');
					$(target).datetimebox('hidePanel');
				}
			});
			$('#begin').datetimebox({
				editable:false,
				width:160,
				buttons:buttons,
				onChange:function(){
					$("#end").datetimebox('enableValidation');
				}
			});
			$('#end').datetimebox({
				editable:false,
				width:160,
				buttons:buttons,
				validType:['compareDate[begin]']
			});
			$("#patrolapproval").datagrid({
				width : 'auto',
				height : 'auto',
				pageSize : 20,
				nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				url : 'psdepartmentreport/pageBind.html',
				remoteSort : false,
				rownumbers : true,
				pagination : true,
				//selectOnCheck: true,
				//checkOnSelect: true,
				singleSelect : true,
			    queryParams : {
			    	all : 0,
			    	classes:3
				},
			    toolbar:'#toolbar',
				columns : [ [ 
					/* { field : 'checked',width : 30,checkbox: true} , */
			        {field:'code',width:160,title:'签发单编号'},
			        {field:'stationname',width : 130,title:'巡检枢纽名称'},
			        {field:'parttime',width : 160,title:'巡检时间'},
			        {field:'creater',width : 130,title:'制单人'},
			        {field:'createtime',width : 130,title:'制单时间'},
			        {field:'isid',hidden:true},
			        {field:'id',hidden:true},
			        {field:'Levels',width:130,title:'状态'}
				] ],
				onCheck: function(index,row){
					var isNext=false;
					$.each(codes, function(index, item){
						if(item != row.code){
							isNext=true;
						}
					});
					if(!isNext){
						stcds.push(row.id);
						codes.push(row.code);
					}else{
						$("#patrolapproval").datagrid("uncheckRow",index);
						stcds.remove(row.id);			
						codes.remove(row.code);
						//$.messager.alert("提示信息","请选择同一签发单号","info");
					}
				},
				onUncheck:function(index,row){
					stcds.remove(row.id);			
					codes.remove(row.code);
				},
				onLoadSuccess:function(data){                
					 var rows = data.rows;
						 var merges = [];
						 var code=rows[0].code;
						 var k=0;
						 for(var i=0; i<rows.length; i++){
							if(code == ""){
								code=rows[i].code;
								continue;
							}
						  	if(rows[i].code == code){
						  		k++;
						  		code=rows[i].code;
						  	}else {
						  		if(k > 1){
						  			//console.info(i);
							  		merges.push({index: i - k,rowspan: k  });
							  		code=rows[i].code;
							  		k=1;
						  		}else {
						  			code= rows[i].code;
						  		}
						  	}
						  	if(i == rows.length -1){
						  		if(k > 1){
						  			merges.push({index: i - k + 1,rowspan: k  });
						  		}
						  	}
						 }
						for(var i=0; i<merges.length; i++){
							$(this).datagrid('mergeCells',{
								index: merges[i].index,
								field: 'code',
								rowspan: merges[i].rowspan
							});
						}
				},
				onCheckAll:function(rows){
					for(var i=0;i< rows.length;i++){
						var isNext=false;
						$.each(codes, function(index, item){
							if(item != rows[i].code){
								isNext=true;
							}
						});
						if(!isNext){
							stcds.push(rows[i].id);
							codes.push(rows[i].code);
						}
					}
					//console.info(stcds);
				},
				onUncheckAll:function(rows){
					for(var i=0;i< rows.length;i++){
						stcds.remove(rows[i].id);
						codes.remove(rows[i].code);
					}
					//console.info(stcds);
				},
			 	onDblClickRow:function(index,row){
				/*  if(row.Levels == '已汇总'){
					alert("该记录已审批");
				}else{ 
					$("#patrolapproval").datagrid("unselectAll");
				$("#patrolapproval").datagrid("uncheckAll");
				$.each(codes, function(index, item){
					codes.remove(codes[i].code);
					stcds.remove(codes[i].id);
					console.info(codes);
				});
					showDialogWH("审批巡检记录", "psdepartmentreport/patrolnormaldepartmentInfo.html?patrolplandetailid="
						+row.id,800,600);
				 }  */
			 		showDialogWH("查看上报单", "psdepartmentreport/patrolnormaldepartmentInfo.html?patrolplandetailid="
							+row.id+"&isid="
							+row.isid+"&look=1",800,600);
				} 
			});
			var p = $("#patrolapproval").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
			$('#tt').tabs({
				tabWidth : '160',
				border : false,
				onSelect : function(title, index) {
					$('#begin').datetimebox('setValue','');
					$('#end').datetimebox('setValue','');
					if(index==0){
						$('#tool').show();	
						//$('#patrolapproval').datagrid('showColumn', 'checked');
					}else{
						$('#tool').hide();
						//$('#patrolapproval').datagrid('hideColumn', 'checked');
					}
					$("#patrolapproval").datagrid("unselectAll");
					$('#patrolapproval').datagrid('load',{
						all:index,
						classes:3,
						begin:$('#begin').datetimebox('getValue'),
						end:$('#end').datetimebox('getValue')
					});
				}
			});
		});		

		function Search(){
			var tab = $('#tt').tabs('getSelected');
			var index = $('#tt').tabs('getTabIndex',tab);
			$("#patrolapproval").datagrid("load", {
				all : index,
				classes:3,
				begin : $("#begin").datetimebox("getValue"),
				end : $("#end").datetimebox("getValue")
			});
		}
		
		
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
	</script>
</head>

<body class="easyui-layout" id="cc">
	<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
		<div id="toolbar">
			<div id="tt">

				<div id="my" title="我的任务" data-options="closable:false"
					style="overflow:auto;padding:20px;display:none;width: 100px;"></div>
				<div id="all" title="全部数据"
					style="padding:20px;display:none;width: 100px;"></div>
			</div>
			<div id="tool" style="" class="cz_div_title">
				<table>
					<tr>
						<td><a class="easyui-linkbutton"
							data-options="iconCls:'icon-add'" id="Edit">审核</a></td>
					</tr>
				</table>
			</div>
			<div class="cz_div">
				制单时间区间:&nbsp;&nbsp;<input id="begin" /> ~ <input id="end" />
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="Search()" id="search" iconCls="icon-search">查询</a>
			</div>
		</div>
		<div data-options="region:'center'">
			<table id="patrolapproval"></table>
		</div>
	</div>
</body>
</html>
