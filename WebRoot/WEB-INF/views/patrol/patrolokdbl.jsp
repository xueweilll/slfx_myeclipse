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
<title>确认巡检记录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>

<body>
	<form id="ffemp" method="post" enctype="multipart/form-data"
		style="margin:10px; text-align: left;">

		<input id="patrolid" value="${patrol.getId()}" type="hidden" />
		<div>
			<label for="patroltime">巡检日期:</label> <input id="patroltime"
				class="easyui-datetimebox" data-options="editable:false,disabled:true" 
				value="${patroltime}" />
		</div>
		</br>
		<div style="height:350px;"><table id="approval"></table></div>
		</br>
		<div><label>巡检综述:</label><input class="easyui-textbox" style="width:660px;height:80px;" data-options="multiline:true,disabled:true" id="memo" value="${patrol.getMemo()}"/></div>
		</br>
		<div id="btn" style="text-align: center">
			<!-- <a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="ok()">确认</a> -->			
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			var patrolid=$('#patrolid').val();
			$('#approval').datagrid({
				url:'approval/patroldetailList.html?patrolid='+patrolid,
				columns:[[
			        {field:'enumid1',title:'巡视检查部位',width:100},
			        {field:'enumid2',title:'巡检内容要求',width:380},
			        {field:'istype',title:'巡视检查记录',width:100},
			        {field:'contents',title:'问题记录',width:60},
			        {field:'handletype',title:'处理方式',width:80}
			    ]],
			    toolbar:'#toolbar',
			    /* pageSize : 20, */
			    width : 'auto',
				height : '300',
				singleSelect : true,
				/* pagination : true, */
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true
			});
		});
		function ok(){
			$.post('approval/verify.html',{
				patrolid:$('#patrolid').val()
			},function(msg){
				if(msg=="true"){
					$.messager.progress('close');
					$('#dialog').window('close');
					reflush();
					$.messager.alert('操作提示', '操作成功',
					'info');
				}
			});
		}
			
			
		function cancel(){
			$("#ffemp").form("clear");
		}
		function reflush() {
			document.getElementById('patrolconfirm.htmlifm').contentWindow
					.$('#patrolconfirmlist').datagrid('reload');
		}
	</script>

</body>
</html>
