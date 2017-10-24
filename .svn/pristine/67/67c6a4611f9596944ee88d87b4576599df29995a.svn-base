<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'facilityInfo.jsp' starting page</title>

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
	<input id="" type="hidden" value="${facility.getId()}" />
	<input id="" type="hidden"
		value="" />
	<input id="" type="hidden"
		value="" />
	<form id="ffemp" method="post"
		style="margin-top: 10px; text-align: center;">
		<div>
			<label for="areaid">地区名称:</label> 
			<input id="areaid"
				class="easyui-combobox" name="facilityName" data-options="required:true"
				value="${facility.getAreaid()}" />
			<label for="pumpstationid">泵站名称:</label> 
			<input id="pumpstationid"
				class="easyui-combobox" name="facilityName" data-options="required:true"
				value="${facility.getPumpstationid()}" />
				
				
			<label for="id">设备编号:</label> 
			<input id="id"
				class="easyui-textbox" name="facilityName" data-options="required:true"
				value="${facility.getId()}" />
			<label for="facilitytypeid">设备类型:</label>						        
			<input id="facilitytypeid"
				class="easyui-combobox" name="brand" data-options="required:true"
				value="${facility.getFacilitytypeid()}" />     
    </select> 
			
		</div>
		<div>
			<label for="name">设备名称:</label>
			<input id="name"
				class="easyui-textbox" name="facilityName" data-options="required:true"
				value="${facility.getName()}" />
			 
		</div>		
		<div>
			<label for="weight">重量:</label> <input id="weight"
				class="easyui-textbox" name="remark"
				data-options="" value="${facility.getWeight()}" />
		</div>
		<div>
			<label for="voltage">额定电压:</label> <input id="voltage"
				class="easyui-textbox" name="remark" style=""
				data-options="" value="${facility.getVoltage()}" />
		</div>
		<div>
			<label for="electricity">额定电流:</label> <input id="electricity"
				class="easyui-textbox" name="remark" style=""
				data-options="" value="${facility.getElectricity()}" />
		</div>
		<div>
			<label for="frequency">额定频率:</label> <input id="frequency"
				class="easyui-textbox" name="remark" style=""
				data-options="" value="${facility.getFrequency()}" />
		</div>
		<div>
			<label for="power">功率消耗:</label> <input id="power"
				class="easyui-textbox" name="remark" style=""
				data-options="" value="${facility.getPower()}" />
		</div>
		<div>
			<label for="producedate">生产日期:</label> <input id="producedate"
				class="easyui-datebox" name="remark" style=""
				data-options="" value="${facility.getProducedate()}" />
		</div>
		<div>
			<label for="factory">生产厂家:</label> <input id="factory"
				class="easyui-textbox" name="remark" style=""
				data-options="" value="${facility.getFactory()}" />
		</div>
		<div>
			<label for="buydate">采购日期:</label> <input id="buydate"
				class="easyui-datebox" name="remark" style=""
				data-options="" value="${facility.getBuydate()}" />
		</div>		
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save()">保存</a>&nbsp;&nbsp;<a id="cancel"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$('#pumpstationid').combobox({
				valueField:'id',
				textField:'name'				
			});
			$('#areaid').combobox({
				valueField:'id',
				textField:'name',
				url:'facility/getArea.html',
				onSelect:function(record){
					$('#pumpstationid').combobox('setValue',"");
					$('#pumpstationid').combobox('setText',"");
					var id=record.id;
					$.ajax({
						type : "POST",
						url : "facility/getPumpstationByAreaId.html?id="+record.id,
						dataType : "json",
						success : function(data2) {
							$('#pumpstationid').combobox('loadData',data2);
						}
					});
				}
			});
			$('#facilitytypeid').combobox({
				valueField:'id',
				textField:'name',
				url:'facility/getEquipment.html'
			});						
			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
			var areaid=$('#areaid').combobox('getValue');
			if(areaid!=""){
				$('#pumpstationid').combobox({
					url:'facility/getPumpstationByAreaId.html?id='+areaid
				})
			}
		});

		function reflush() {
			document.getElementById('facility.htmlifm').contentWindow.$('#facility')
					.datagrid('reload');
		}
		function Save(){
			var data={
				"areaid" : $("#areaid").combobox("getValue"),
				"pumpstationid" : $("#pumpstationid").combobox("getValue"),
				"id" : $("#id").textbox("getText")==""?"0":$("#id").textbox("getText"),
				"name" : $("#name").textbox("getText"),
				"facilitytypeid" : $("#facilitytypeid").combobox("getValue"),
				"weight" : $("#weight").textbox("getText"),
				"voltage" : $("#voltage").textbox("getText"),
				"electricity" : $("#electricity").textbox("getText"),
				"frequency" : $("#frequency").textbox("getText"),
				"power" : $("#power").textbox("getText"),
				"producedate" : $("#producedate").datebox("getText")+ " 00:00:00",
				"factory" : $("#factory").textbox("getText"),
				"buydate" : $("#buydate").datebox("getText")+ " 00:00:00"		
			};
			$.ajax({
				type:"POST",
				url:"facility/Save.html",
				dataType:"text",
				data : {
					"json" : JSON.stringify(data)
				},
				success:function(){
					$.messager.progress('close');
					reflush();
					$('#dialog').window('close');
				}
			});
		}		
	</script>
</body>
</html>
