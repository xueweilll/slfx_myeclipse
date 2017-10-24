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
    <title>My JSP 'facilityCheck.jsp' starting page</title>   
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
				{"placename":"新北区","pumpname":"仙水泵站","equipmentbrand":"螺旋泵","equipmentnum":"1台","stocktoplimit":"5台","stocklowerlimit":"1台"},
				{"placename":"新北区","pumpname":"海泰泵站","equipmentbrand":"螺旋泵","equipmentnum":"1台","stocktoplimit":"5台","stocklowerlimit":"1台"},
				{"placename":"天宁区","pumpname":"龙江泵站","equipmentbrand":"离心泵","equipmentnum":"2台","stocktoplimit":"5台","stocklowerlimit":"1台"}
			]};
			$('#facilityCheck').datagrid({	
				data:ss,						
				columns:[[
			        {field:'placename',title:'地区名称',width:100},
			        {field:'pumpname',title:'泵站名称',width:100},
			        {field:'equipmentbrand',title:'设备类型',width:100},
			        {field:'equipmentnum',title:'设备数量',width:100},			        
			        {field:'stocktoplimit',title:'库存上限',width:100},
			        {field:'stocklowerlimit',title:'库存下限',width:100}			        	        
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
			var p = $("#facilityCheck").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		})		
		function Add(){
			showDialog("添加盘点信息", "facilityCheck/facilityCheckInfo.html?id=0");
		}
		function Edit(){
			var Item=$('#facilityCheck').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialog("添加设备信息", "facilityCheck/facilityCheckInfo.html?id="+Item.id);
			}
		}
		function Delete(){
			var Item=$('#facilityCheck').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"facilityCheck/facilityCheckDelete.html?id="+Item.id,
							success:function(){								
								$("#facilityCheck").datagrid("reload");
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
        			<td><a id="" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Add()">新增</a></td>
        			<td ><a id="" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="Edit()">修改</a>
        				<a id="" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="Delete()">删除</a>       			
        			</td>
        		</tr>
        		<tr>
        			<td>设备类型：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>地区名称：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>泵站名称：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td>设备数量：</td>
        			<td><input class="easyui-textbox" data-options="width:150,height:25"></td>
        			<td><a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查看</a></td>
        		</tr>
        	</table>
        </div>               
        <div data-options="region:'center',title:'设备盘点'">
        	<table id="facilityCheck"></table>
        </div>
    </div>
  </body>
</html>
