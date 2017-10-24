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
    <title>My JSP 'facility.jsp' starting page</title>   
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
			$('#facility').datagrid({
				url:'facility/getFacility.html',							
				columns:[[
					{field:'area_name',title:'地区名称'},			       			        
			        {field:'pumpstation_name',title:'泵站名称'},		        
			        {field:'id',title:'设备编号'},			       			        
			        {field:'facilitytype_name',title:'设备类型'},
			        {field:'name',title:'设备名称'},
			        {field:'weight',title:'重量'},
			        {field:'voltage',title:'额定电压'},
			        {field:'electricity',title:'额定电流'},
			        {field:'frequency',title:'额定频率'},
			        {field:'power',title:'功率消耗'},
			        {field:'producedate',title:'生产日期'},
			        {field:'factory',title:'生产厂家'},
			        {field:'buydate',title:'采购日期'}
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
				fit : true,
			})
			var p = $("#facility").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})
		function Add(){
			showDialog("添加设备信息", "facility/facilityInfo.html?id=0");
		}		
		function Edit(){
			var Item=$('#facility').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialog("修改设备信息", "facility/facilityInfo.html?id="+Item.id);
			}
		}
		function Search(){
			$.messager.progress();
			alert("查看");
			$.messager.progress('close');
		}
		function Delete(){
			var Item=$('#facility').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"facility/facilityDelete.html?id="+Item.id,
							success:function(){								
								$("#facility").datagrid("reload");
							}
						});
					}
				});
			}
		}
		/* function showDialog(title,url){		
		$("#dialog").dialog({
			title:title,
			href:url,
			width: 500,
			height:400,
			closed:true,
			cache:false,
			modal:true
		}).dialog("open");
	}	 */	
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">
    <div id="cc" class="easyui-layout" style="width:100%;height:100%;">
        <div id="toolbar">       	       	
        	<table>
        		<tr>
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Add()">新增</a></td>
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="Edit()">修改</a>
        				<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="Delete()">删除</a>
        			</td>      
        		</tr>
        		<tr>
        			<td>设备名称：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>采购日期：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>额定电压：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>额定电流：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查看</a></td>
        		</tr>
        	</table>
        </div>               
        <div data-options="region:'center',title:'设备建档'">
        	<table id="facility"></table>
        </div>
    </div>
    <div id="dialog"></div>
  </body>
</html>
