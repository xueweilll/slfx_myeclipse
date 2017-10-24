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
<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">

	$(function() {
		$('#tt').tabs({
			tabWidth:'160',
			border : false,
			onSelect : function(title, index) {
				if(index=="1"){
					$(".cz_div_title").show();
					$("#historyWorkSituation_list").datagrid("showColumn",'state');
					$("#historyWorkSituation_list").datagrid("showColumn",'level');
					$("#historyWorkSituation_list").datagrid("hideColumn",'power');
					$("#historyWorkSituation_list").datagrid("hideColumn",'dir');
					$("#historyWorkSituation_list").datagrid("hideColumn",'flow');
					$("#historyWorkSituation_list").datagrid("hideColumn",'temp');
					var options=$("#historyWorkSituation_list").datagrid("options");
					options.url="historyWorkSituation/gateBind.html";
					options.columns[0][1].title="闸门名称";
				}else{
					$(".cz_div_title").hide();
					$("#historyWorkSituation_list").datagrid("hideColumn",'state');
					$("#historyWorkSituation_list").datagrid("hideColumn",'level');
					$("#historyWorkSituation_list").datagrid("showColumn",'dir');
					$("#historyWorkSituation_list").datagrid("showColumn",'power');
					$("#historyWorkSituation_list").datagrid("showColumn",'flow');
					$("#historyWorkSituation_list").datagrid("showColumn",'temp');
					var options=$("#historyWorkSituation_list").datagrid("options");
					options.url="historyWorkSituation/pumprunBind.html";
					options.columns[0][1].title="机组名称";
				}
				$("#historyWorkSituation_list").datagrid();
			}
		});
		$("#historyWorkSituation_list").datagrid({
			iconCls : 'icon icon-icon7',
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'historyWorkSituation/pumprunBind.html',
			idField : 'id',
			singleSelect : true,
			onDblClickRow:function(rowIndex, rowData){
				searchInfo(rowData);
			},
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			columns:[[    
			          {field:'sname',title:'枢纽名称',width:160},    
			          {field:'code',title:'机组名称',width:160},    
			          {field:'time',title:'采集时间',width:140},    
			          {field:'runState',title:'运行状态',width:80},    
			          {field:'dir',title:'引排方向',width:80},    
			          {field:'va',title:'Va',width:50,hidden:true},    
			          {field:'vb',title:'Vb',width:50,hidden:true},    
			          {field:'vc',title:'Vc',width:50,hidden:true},   
			          {field:'ia',title:'Ia',width:50,hidden:true},    
			          {field:'ib',title:'Ib',width:50,hidden:true},	  
			          {field:'ic',title:'Ic',width:50,hidden:true},	  
			          {field:'power',title:'实时功率',width:80},   
			          {field:'flow',title:'实时流量',width:80},   
			          {field:'temp',title:'电机轴温度',width:80},   
			          {field:'state',title:'位置状态',width:80,hidden:true}, 
			          {field:'level',title:'闸门开度',width:80,hidden:true} 
			      ]]
		}); 
		var p = $("#historyWorkSituation_list").datagrid('getPager');
		//console.info(p);
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		$("#starttime").datetimebox({
			editable:false,
		    buttons:buttons,
		    onChange:function(){
		    	$("#endtimes").datetimebox('enableValidation');
		    }
		});
		$("#endtime").datetimebox({
			editable:false,
		    buttons:buttons
		});

	});
	function selectbig(){
		var isValid=$("#endtime").datetimebox("isValid");
		if(!isValid){
			return false;
		}
		/* var data=$("#code").textbox("getValue"); */
		$("#historyWorkSituation_list").datagrid('load',{
			/* code:(data=="" ? null:data), */
			starttime: $("#starttime").datetimebox("getValue"),
			endtime: $("#endtime").datetimebox("getValue")
		});
	}
	var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
	buttons.splice(0,1,{
		text: '清空',
		handler: function(target){
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	}); 

</script>

</head>

<body class="easyui-layout" id="cc">
		<table id="historyWorkSituation_list" cellspacing="0" cellpadding="0"></table>
		<div id="tb" style="padding:5px;height:auto">
		<div id="tt" class="easyui-tabs" style="">
			<div title="机组运行工况" style="padding:20px;display:none;width: 100px;">
			</div>
			<div title="闸门运行工况" data-options="closable:false"
				style="overflow:auto;padding:20px;display:none;width: 100px;">
			</div>
		</div>
		<div style="display: none">
		<!-- class="cz_div_title" -->
    		</div>
    		<div class="cz_div">
    		<!-- 枢纽编号:<input id="code" class="easyui-textbox" name="code"   
    				data-options="width:'160'"  /> -->
			采集时间: <input id="starttime" type="text" data-options="width:160,editable:false,buttons: buttons
			">
			~ <input id="endtime" type="text" class="easyui-datetimebox"
				data-options="width:160,editable:false,buttons: buttons,validType:['compareDate[starttime]']">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="selectbig()" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>
