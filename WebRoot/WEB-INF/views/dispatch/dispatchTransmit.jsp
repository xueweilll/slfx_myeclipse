<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<%@include file="../header.jsp"%>
<title>My JSP 'dispatchinfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>

	<input id="id" type="hidden" value="${dispatch.getId()}" />
	<input id="state" type="hidden" value="${dispatch.getState()}" />
	<div>		
		<p><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">转&nbsp;&nbsp;发&nbsp;&nbsp;单</span></p>
	</div>
	<form id="ffemp" method="post"
		style=" text-align: center;margin:2px;margin-left: 10px;">
		<table cellpadding="0" cellspacing="0" border="0">

		<tr>
		      <td ><label>分解单编号:</label></td>
		      <td><input id="code" class="easyui-textbox"
					data-options="required:true,disabled:true" value="${dispatch.getCode()}" ></td>
		      
		</tr> 
			<tr>
			    <td>
			    
			       <label>调度令方式:</label>
			    </td>
			    <td>
			       <input id="way" class="easyui-textbox"
					data-options="disabled:true" value="${way}">
			    </td>
			    
			</tr>
			<tr>
				<td><label>调度令发起人:</label></td>
				<td><input id="Promoter" class="easyui-textbox"
					data-options="disabled:true" value="${launcher}" /></td>

			     

			</tr>
			<tr>
				<td><label>调度令接收人:</label></td>
				<td><input id="Promoter" class="easyui-textbox"
					data-options="disabled:true" value="${receiptname}" /></td>

			    

			</tr>
			<tr>

				<td><label>调度令备注：</label>
				<td><input id="memo" class="easyui-textbox"
					data-options="disabled:true" value="${dispatch.getMemo()}"></td>

			</tr>
            <tr>
              <td><label>调度令发起时间:</label></td>
				<td><input id="PromoteTime" class="easyui-textbox"

					data-options="disabled:true" value="${launchertime}"></td>
            
            </tr>
            
            <tr>
              <td><label>调度令结束时间:</label></td>
			     <td><input id="endtime" class="easyui-textbox" data-options="disabled:true"
			     value="${endtime}">
            
            </tr>
            <tr>
               <td><label>调度令接收时间:</label></td>
			     <td><input id="endtime" class="easyui-textbox" data-options="disabled:true"
			     value="${receiptetime}">
            
            </tr>
            <tr>
              
			     <td><label>转发内容：</label>
			      <td><input id="transmitContents" class="easyui-textbox" data-options="disabled:true" value="${dispatch.getTransmitcontents()}"></td>
              
            
            </tr>
		</table>
		</br>
		<div id="btn" >
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">转发</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$("#sub").bind("click", function() {
				$.messager.confirm('确认对话框', '是否转发?', function(r){
					if (r){
						$.ajax({
							type : "POST",
							url : "dispatchTransmitList/transmit.html",
							dataType : "text",
							data : {
								"id" : $("#id").val()
							},
							success : function() {
								$.messager.show({
			                      title:'我的消息',
			                      msg:'请即时回访',
			                      timeout:5000,
			                      showType:'slide'
		                            }); 
								$('#dialog').window('close');
								reflush();
							}
						});
					}
				});				
			});
			$("#zl").datagrid({
				url : 'dispatchIssuedList/ddzl.html?id=' + $("#id").val(),
				title : '调度指令',
				iconCls : '',
				rownumbers : true,
				width : 'auto',
				height : '200px',
				closed : false,
				cache : false,
				modal : true
			});
			$("#mx").datagrid({
				url : 'dispatchIssuedList/ddmx.html?id=' + $("#id").val(),
				title : '调度明细',
				iconCls : '',
				rownumbers : true,
				width : 'auto',
				height : '200px',
				closed : false,
				cache : false,
				modal : true
			});
		});
		function reflush() {
			document.getElementById('dispatchTransmitList.htmlifm').contentWindow.$(
					'#selfdispatch').datagrid('reload');
			          
		}
	</script>
</body>
</html>
