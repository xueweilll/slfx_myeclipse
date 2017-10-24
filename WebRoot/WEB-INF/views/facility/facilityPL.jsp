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
    <title>My JSP 'facilityError.jsp' starting page</title>   
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
			var ss={"total":5,"rows":[
				{"placename":"新北区","pumpname":"仙水泵站","equipmentbrand":"螺旋泵","situation":"损失","num":"1","time":"2012-6-4"},
				{"placename":"新北区","pumpname":"仙水泵站","equipmentbrand":"离心泵","situation":"损失","num":"2","time":"2012-6-4"}		
			]};		
			$('#facilityPL').datagrid({	
				data:ss,						
				columns:[[
			        {field:'placename',title:'地区名称',width:100},
			        {field:'pumpname',title:'泵站名称',width:100},
			        {field:'equipmentbrand',title:'设备类型',width:100},			       
			        {field:'situation',title:'损益情况（进或出）',resizable:true},
			        {field:'num',title:'损益数量',width:100},
	        		{field:'time',title:'损益日期',width:100}        		    	        
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
			var p = $("#facilityPL").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})	
		function Add(){
			showDialog("添加设备故障信息", "facilityPL/facilityPLInfo.html?id=0");
		}
		function Edit(){
			var Item=$('#facilityPL').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialog("添加设备信息", "facilityPL/facilityPLInfo.html?id="+Item.id);
			}
		}
		function Delete(){
			var Item=$('#facilityPL').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"facilityPL/facilityPLDelete.html?id="+Item.id,
							success:function(){								
								$("#facilityPL").datagrid("reload");
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
        <div id="toolbar">       	       	
        	<table>
        		<tr>
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Add()">新增</a></td>
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="Edit()">修改</a>
        				<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="Delete()">删除</a>
        			</td>      
        		</tr>
        		<tr>
        			<td>设备类型：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>损益时间：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>损益情况：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>地区名称：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>        			
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查看</a></td>
        		</tr>
        	</table>
        </div>               
        <div data-options="region:'center',title:'设备损益'">
        	<table id="facilityPL"></table>
        </div>
    </div>
  </body>
</html>
