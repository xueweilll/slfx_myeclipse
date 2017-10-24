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
				{"placename":"新北区","pumpname":"仙水泵站","equipmentid":"000001","equipmentname":"凸轮螺旋泵","maintaintime":"2010-6-2","maintainsituation":"已修好"},
				{"placename":"新北区","pumpname":"海泰泵站","equipmentid":"000004","equipmentname":"凸轮转子泵","maintaintime":"","maintainsituation":"需维修"},
				{"placename":"天宁区","pumpname":"环海泵站","equipmentid":"000006","equipmentname":"凸轮离心泵","maintaintime":"2010-6-5","maintainsituation":"已修好"},
				{"placename":"钟楼区","pumpname":"铺放泵站","equipmentid":"000010","equipmentname":"凸轮螺旋泵","maintaintime":"2010-6-5","maintainsituation":"报废"}
			]};
			$('#facilityMaintain').datagrid({	
				data:ss,						
				columns:[[
			        {field:'placename',title:'地区名称',width:100},
			        {field:'pumpname',title:'泵站名称',width:100},
			        {field:'equipmentid',title:'设备id',width:100},
			        {field:'equipmentname',title:'设备名称',width:100},
			        {field:'maintaintime',title:'处理时间',width:100},
			        {field:'maintainsituation',title:'维修情况',width:100}		        	        
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
			var p = $("#facilityMaintain").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})
		function Add(){
			showDialog("添加设备故障信息", "facilityMaintain/facilityMaintainInfo.html?id=0");
		}
		function Edit(){
			var Item=$('#facilityMaintain').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialog("维修设备信息", "facilityMaintain/facilityMaintainInfo.html?id="+Item.equipmentid);
			}
		}
		function Delete(){
			var Item=$('#facilityMaintain').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"facilityMaintain/facilityMaintainDelete.html?id="+Item.equipmentid,
							success:function(){								
								$("#facilityMaintain").datagrid("reload");
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
        			
        			<td colspan="2"><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="Edit()">维修</a>
        				<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="Delete()">删除</a>
        			</td>      
        		</tr>
        		<tr>
        			<td>设备名称：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>维修时间：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>地区名称：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>泵站名称：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查看</a></td>
        		</tr>
        	</table>
        </div>               
        <div data-options="region:'center',title:'设备维修'">
        	<table id="facilityMaintain"></table>
        </div>
    </div>
  </body>
</html>
