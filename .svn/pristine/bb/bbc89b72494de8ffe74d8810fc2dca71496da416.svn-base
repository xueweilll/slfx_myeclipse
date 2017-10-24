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
    <title>摄像头</title>   
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
			$('#camera').datagrid({
				title : '摄像头列表',
				iconCls : 'icon icon-icon12',
				url:'camera/cameraList.html',
				columns:[[				    			        
			        {field:'station_name',title:'枢纽名称',width:fixWidth(0.4)},
			        {field:'code',title:'摄像头编号',hidden:"true"},
			        {field:'name',title:'摄像头名称',width:fixWidth(0.4)},
			        {field:'channel',title:'通道号',width:fixWidth(0.199)},
			        {field:'url',title:'视频URL地址',hidden:"true"},
			        {field:'createtime',title:'添加时间',hidden:"true"},
			        {field:'edittime',title:'修改时间',hidden:"true"}
			    ]],
			    iconCls:'icon icon-icon12',
			    toolbar:'#toolbar',
			    width : 'auto',
				height : 'auto',
			    pageSize : 20,
			    nowrap : false,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				remoteSort : false,
				idField : 'id',
				singleSelect : true,
				pagination : true,
				rownumbers : true,
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
					 console.info(merges);
					 for(var i=0; i<merges.length; i++){
		                 $(this).datagrid('mergeCells',{
		                     index: merges[i].index,
		                     field: 'station_name',
		                     rowspan: merges[i].rowspan
		                 });
		             }
				}
				/* ,				
				onDblClickRow:function(index,row){
					showDialog("修改摄像头信息", "camera/cameraInfo.html?id="+row.id);
				} */
			});
			var p = $("#camera").datagrid('getPager');
			$(p).pagination({
				pageList : [ 5, 10, 15, 20 ],
				beforePageText : '第',
				afterPageText : '页        共{pages}页',
				displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
			});
		});
		
		function Add(){
			showDialogWH("添加摄像头信息", "camera/cameraInfo.html?id=0",500,300);
		}	
			
		function Edit(){
			var Item=$('#camera').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				showDialogWH("修改摄像头信息", "camera/cameraInfo.html?id="+Item.id,500,300);
			}
		}
		function Search(){
			$('#camera').datagrid('unselectAll');
			$.messager.progress();
			var sid=$('#sid').combobox('getValue');
			var name=$('#name').textbox('getText');
			var sn={"sid":sid,"name":name};
			$('#camera').datagrid('load',{
				/* url:'camera/cameraList.html?sn='+JSON.stringify(sn) */
				sn:JSON.stringify(sn)
			});
			$("#camera").datagrid("unselectAll");
			$.messager.progress('close');
		}
		function Delete(){
			var Item=$('#camera').datagrid('getSelected');
			if(Item==null){
				$.messager.alert("操作提示","请选择一条记录再进行操作！","error");
			}else{
				$.messager.confirm("删除提示","您确定要执行删除操作吗？",function(data){
					if(data){
						$.ajax({
							type:'post',
							url:"camera/cameraDelete.html?id="+Item.id,
							success:function(){								
								$("#camera").datagrid("reload");
								$("#camera").datagrid("unselectAll");
							}
						});
					}
				});
			}
		}				
	</script>
  </head>
  
  <body class="easyui-layout" id="cc">               
       	<table id="camera"></table>      
        <div id="toolbar">       	       	
 	      <div class="cz_div_title">
 			<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="Add()">新增</a>
 			<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="Edit()">修改</a>
 			<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-clear'" onclick="Delete()">删除</a>
          </div>
          <div class="cz_div" >
        	<table cellpadding="0"cellspacing="0" style="padding-top:0px;">
        		<tr>
        			<td>枢纽名称：</td>
        			<td><input id="sid"/>&nbsp;&nbsp;</td>
        			<td>摄像头名称：</td>
        			<td><input id="name" class="easyui-textbox" data-options="width:150"/>&nbsp;&nbsp;</td>
        			<td><a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="Search()">查询</a></td>
        		</tr>
        	</table>
       	  </div>
        </div>
  </body>
</html>
