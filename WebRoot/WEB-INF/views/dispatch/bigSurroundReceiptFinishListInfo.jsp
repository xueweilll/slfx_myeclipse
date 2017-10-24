<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bigSurroindReceiptFinishListInfo.jsp' starting page</title>
    
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
    <input id="Id" type="hidden" value="${receipt.getId()}" />
	<input id="type" type="hidden" value="${receipt.getDispatchtype()}" />
	<input id="state" type="hidden" value="${receipt.getState()}" />
	<input id="Accessory" type="hidden" value="${receipt.getFileaddress()}"/>
	<div>		
		<p><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">签&nbsp;&nbsp;发&nbsp;&nbsp;单</span></p>
	</div>
	<form id="ffgate" method="post" style="margin: 2px; margin-left:10px;text-align: left">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label for="Code"
					style="display: inline-block; width: 80px">调度令编号:</label></td>
				<td><input id="Code" name="Code" class="easyui-textbox"
					type="text" style="width:250px;" value="${receipt.getCode()}"
					data-options="disabled:true" /></td>
			</tr>
			<tr>
				<td><label for="Way">调度令方式:</label></td>
				<td><input id="Way" name="Way" style="width:250px;"
					class="easyui-textbox" data-options="disabled:true" value="${wayName}" /></td>
			</tr>
			<tr>
				<td><label for="DispatchType">调度令类型:</label></td>
				<td><input id="DispatchType" name="DispatchType"
					class="easyui-textbox" data-options="disabled:true"
					style="width:250px;" value="${typeName}" /></td>
			</tr>
			<tr>
				<td><label for="DispatchAccessory">调度附件:</label></td>
				<td><input id="DispatchAccessorys" class="easyui-textbox" name="DispatchAccessorys"
					style="width:200px;float:left;" value="${fileaddress}"data-options="disabled:true" />
					<label style="width:40px;float:right;display:none" id="downAccessory" class="easyui-linkbutton">下载</label></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				<td><label for="Launcher">调度令发起人:</label></td>
				<td><input id="Launcher" class="easyui-textbox" type="text"
					name="Launcher" data-options="disabled:true" style="width:250px;"
					value="${receipt.getLauncher()}" /></td>
			</tr>
			<tr>
				<td><label for="Memo">调度令内容:</label></td>
				<td><input id="Memo" class="easyui-textbox" type="text"
					name="Memo" data-options="disabled:true" style="width:250px;"
					value="${receipt.getMemo()}" style="" /></td>
			</tr>
			<tr>
				<td><label for="Receipter"
					style="display: inline-block; width: 80px">调度令接收人:</label></td>
				<td><input id="Receipter" name="Receipter" style="width:250px;" class="easyui-textbox"
					data-options="disabled:true" value="${receipt.getReceipteUser().getUsername()}" /></td>
			</tr>

			<tr>
				<td><label for="ReceipteTime">调度令接收时间:</label></td>
				<td><input id="ReceipteTime" name="ReceipteTime"
					style="width:250px;" data-options="disabled:true"
					class="easyui-textbox" value="${receiptetime}" /></td>
			</tr>
			<tr>
				<td>签发备注:</td>
				<td><input class="easyui-textbox" id="aduitmemo"
					data-options="multiline:true,disabled:true"
					style="width:250px;height: 60px;"  value="${receipt.getAduitmemo()}"/></td>
			</tr>
		</table>
		<div id="btn" style="text-align: left; ">
			 <a	id="aduit" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 140px"
				data-options="iconCls:'icon-save'">完成</a><a
				id="can" href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 20px"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			//look=1双击查看
			var look=${look};
			if (look == "1") {
				$("#btn").hide();
				//$("#downAccessory").hide();
			}else{
				if($("#DispatchAccessorys").val()!=""){
					 $("#downAccessory").show();
				 } 
			}
			
			$("#aduit").bind("click", function() {
				$.ajax({
					type : "POST",
					url : "bigsurroundreceiptfinish/save.html",
					dataType : "text",
					data : {
						"id":$("#Id").val()
					},
					success : function(msg) {
						var data= eval('(' + msg + ')');
						$.messager.progress('close');
						if (data.result) {
							$.messager.alert("操作提示", "操作成功！");
							reflush();
							$('#dialog').window('close');
						} else {
							$.messager.alert("操作提示", data.msg, "error");
						}
						
					}
				});
			});

			$("#can").bind("click", function() {
				$('#dialog').window('close');
			});
			
			$("#downAccessory").bind("click",function(){
				var link="bigsurroundreceiptfinish/downAccessory.html?filename="+myEncoder($("#Accessory").val());
				window.location.href=link;
			});

		});

		function reflush() {
			document.getElementById('bigsurroundreceiptfinish.htmlifm').contentWindow.$('#receipt_list').datagrid('reload');
		}
	</script>
  </body>
</html>
