<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加督察</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body>
    <input type="hidden" id="did" value="${rd.getId()}">
	<input type="hidden" id="state" value="${rd.getState() }" />
	<div>		
		<p><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">督&nbsp;&nbsp;察&nbsp;&nbsp;单</span></p>
	</div>
	<form id="ff" method="post"
		style="margin:2px;margin-left:10px; text-align: center;">
		<div style="float: left;">
			<div style="float: left;">
				<label>分解单编号:</label><input class="easyui-textbox" id="code"
					data-options="disabled:true,width:230" style="width:230"
					value="${rd.getCode()}">
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令编号:</label><input id="receiptid" class="easyui-textbox"
					data-options="disabled:true,width:230"
					value="${rd.getReceipt().getCode()}" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令方式:</label><input class="easyui-textbox" id="way"
					value="${rd.getDispatchtype()}"
					data-options="disabled:true,width:230" style="" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令发起时间:</label><input id="launchTime" class="easyui-textbox"
					value="${launchtime}" data-options="disabled:true"
					style="width:230" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令发起人:</label><input class="easyui-textbox" id="launcher"
					data-options="disabled:true" style="width:230"
					value="${rd.getReceipt().getLauncher()}">
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令结束时间:</label><input id="endTime" class="easyui-textbox"
					data-options="disabled:true" style="width:230" value="${endtime}" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>调度令接收人:</label><input class="easyui-textbox" id="receipter"
					data-options="disabled:true" style="width:230"
					value="${rd.getNames().getReceiptName()}" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>调度令接收时间:</label><input id="receipteTime"
					class="easyui-textbox" data-options="disabled:true"
					style="width:230" value="${receiptetime}" />
			</div>
		</div>
		<div style="float: left;margin-top:2px;">
			<label>调度令备注:</label><input class="easyui-textbox" id="rmemo"
				data-options="disabled:true,multiline:true"
				style="width:580px;height: 60px;"
				value="${rd.getReceipt().getMemo()}">
		</div>
		<div style="float: left;margin-top:2px;">
			<label>分解单备注:</label><input class="easyui-textbox" id="memo"
				data-options="multiline:true,disabled:true" value="${rd.getMemo()}"
				style="width:580px;height: 60px;">
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>督察人:</label><input class="easyui-textbox" id="supervisepeople"
					data-options="required:true" style="width:230" value="" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float:right;">
				<label>督察单位:</label><input id="superviseunit" class="easyui-textbox"
					data-options="required:true" style="width:230" value="" />
			</div>
		</div>
		<div style="float: left;">
			<div style="float: left;">
				<label>操作人:</label><input id="operater" style="width:230" value="" />
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div style="float: right;">
				<label>督察时间:</label><input id="supervisetime" name="supervisetime"
					style="width:230;" value="" />
			</div>
		</div>
		<div style="float: left;margin-top:2px;margin-bottom:10px;">
			<label>督察内容:</label><input class="easyui-textbox" id="supervisememo"
				data-options="multiline:true,required:true" value=""
				style="width:580px;height: 60px;">
		</div>

		<div id="btn" >
			<a id="save" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-tip'">保存</a> <a id="cancel"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-tip'">取消</a>
		</div>

	</form>
	<script type="text/javascript">
		$(function() {

			$("#supervisetime").datetimebox({
				required : true,
				showSeconds : true,
				missingMessage : "该输入项为必输项",
				editable : false
			});
			
			$("#operater").combobox({
				url : 'areareceipt/userList.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				value : $("#operater").val(),
				required : true
			});

			$("#save").bind("click", function() {
						$.messager.progress();
						var isValid = $("#ff").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							return false;
						}
						var data = {
							"rdid" : ($("#did").val() == "" ? 0 : $("#did").val()),
							"supervisepeople" : $("#supervisepeople").textbox("getValue"),
							"supervise" : $("#supervisememo").textbox("getValue").replace(/\s+/g,""),
							"superviseunit" : $("#superviseunit ").textbox("getValue"),
							"operater":$("#operater").combobox("getValue"),
							"supervisetime" : $("#supervisetime").datetimebox("getValue")
						};

						$.ajax({
							type : "POST",
							url : "supervise/save.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data),
								"type":"1"
							},
							success : function() {
								$.messager.progress('close');
								reflush();
								$('#dialog').window('close');
							}
						});
					});

			$("#cancel").bind("click", function() {
				$('#dialog').window('close');
			});
		});

		function reflush() {
			document.getElementById('supervise.htmlifm').contentWindow.$(
					'#tr_list').datagrid('reload');
		}
	</script>
  </body>
</html>
