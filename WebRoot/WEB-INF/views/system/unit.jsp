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
    <title>机组信息</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		$(function(){
			$('#sid').combobox({
				width:150,
				editable:false,				
				valueField:'id',
				textField:'name',
				url:'unit/sid.html'
			});
			$('#unit').datagrid({
				title : '机组列表',
				iconCls : 'icon icon-icon11',
				url:'unit/unitList.html',
				columns:[[
					/* {field:'id',title:'编号'}, */			       			        
			        {field:'station_name',title:'枢纽名称',width:fixWidth(0.15)},
			        {field:'code',title:'机组编号',hidden:true},
			        {field:'name',title:'机组名称',width:fixWidth(0.15)},
			        {field:'types',title:'水泵类型',width:fixWidth(0.1)},
			        {field:'power',title:'水泵型号',width:fixWidth(0.2)},
			        {field:'motertype',title:'电机型号',width:fixWidth(0.199)},
			        {field:'feature',title:'泵站功能',width:fixWidth(0.1)},
			        {field:'designdischarge',title:'设计流量',width:fixWidth(0.1)},
			        {field:'createtime',title:'添加时间',hidden:true},
			        {field:'edittime',title:'修改时间',hidden:true},
			    ]],
			    toolbar:'#toolbar',
			    pageSize : 20,
			    width : 'auto',
				height : 'auto',
				singleSelect : true,
				pagination : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true ,
				/*onDblClickRow:function(index,row){
					showDialogWH("修改机组信息", "unit/unitInfo.html?id="+row.id,450,420);
				}, */
				onLoadSuccess:function(data){
					 var rows = data.rows;
					 var merges = [];
					 var station=rows[0].station_name;
					 var k=0;
					 for(var i=0; i<rows.length; i++){
						if(station == ""){
							station=rows[i].station_name;
							continue;
						}
					  	if(rows[i].station_name == station){
					  		k++;
					  		station=rows[i].station_name;
					  	}else {
					  		if(k > 1){
					  			console.info(i);
						  		merges.push({index: i - k,rowspan: k  });
						  		station=rows[i].station_name;
						  		k=1;
					  		}else {					  			
					  			station= rows[i].station_name;
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
		                     field: 'station_name',
		                     rowspan: merges[i].rowspan
		                 });
		             }
				}
			});
			
			var p = $("#unit").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})
		
		function Add(){
			showDialogWH("添加机组信息", "unit/unitInfo.html?id=0",450,420);
		}	
			
		function Edit(){
			var Item=$('#unit').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialogWH("修改机组信息", "unit/unitInfo.html?id="+Item.id,450,420);
			}
		}
		function Search(){
			$('#unit').datagrid('unselectAll');
			$.messager.progress();
			var sid=$('#sid').combobox('getValue');
			var name=$('#name').textbox('getText');
			var sn={"sid" : sid,
					"name" : name};		
			$('#unit').datagrid('load',{
				//url:'unit/unitList.html?sn='+myEncoder(JSON.stringify(sn))
				sn:JSON.stringify(sn)
			});			
			$.messager.progress('close');
		}
		function Delete(){
			var Item=$('#unit').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"unit/unitDelete.html?id="+Item.id,
							data:{
								
							},
							success:function(){								
								$("#unit").datagrid("reload");
							}
						});
					}
				});
			}
		}		
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
    <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        <div id="toolbar" >       	       	
        	<div class="cz_div_title">        		
       			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Add()">新增</a>
       			<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="Edit()">修改</a>
       			<a class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="Delete()">删除</a>        		  		
        	</div>
        	<div class="cz_div">        	        	
	        	<table cellpadding="0"cellspacing="0" style="padding-top:0px;">
	        		<tr>
	        			<td>枢纽名称：</td>
	        			<td><input id="sid"/>&nbsp;&nbsp;</td>
	        			<td>机组名称：</td>
	        			<td><input id="name" class="easyui-textbox" data-options="width:150"/>&nbsp;&nbsp;</td>
	        			<td><a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查询</a></td>
	        		</tr>
	        	</table>
        	</div>
        </div>
        <div data-options="region:'center',iconCls:'icon icon-icon11'">
        	<table id="unit"></table>
        </div>
    </div>
    <div id="dialog"></div>
  </body>
</html>
