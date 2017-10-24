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
<title>工程科汇总上报弹窗</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	-->
</head>

<body>
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/arithmetic.js"></script>
	<!-- <style>
.datagrid-row-selected {
	background: #ffffff none repeat scroll 0 0;
	color: #000000;
}
</style> -->
	<div align="center">
		<p>
			<img alt="" src="images/zlogo.png"> <span id="titles"
				align="center" style="font-size: 25;">实&nbsp;&nbsp;施&nbsp;&nbsp;单</span>
		</p>
	</div>
	<input type="hidden" id="type" value="${type}">
	<form id="ff" method="post" style="margin: 10px; text-align: center;">
		<div style="float: left;" align="left">
			<div style="float: left;margin-bottom:10px;">
				<label for="code">实施单编号:</label> <input class="easyui-textbox" id="code"
					data-options="width:400,disabled:true" required="true" value="${code}">
			</div>
		</div>
		<div style="float: left;" align="left">
			<div style="float: left;margin-bottom:10px;">
				<label for="issue">签发单编号:</label> <input  id="issue" name="issue" style="width:400px;" >
			</div>
		</div>
		<div style="float: left;margin-bottom:10px;" align="left">
			<div style="float: left;">
				<label for="date">巡检时间:</label> <input class="easyui-datetimebox" id="date"
					data-options="width:400" required="true" value="">
			</div>
		</div>
		<div style="float: left;margin-bottom:10px;" align="left">
			<div style="float: left;">
				<label  for="sid">枢纽:</label>
				 <input  class="easyui-combobox" id="sid" name="sid" style="width:400px;" >
			</div>
		</div>
		<div style="float: left;margin-bottom:10px;width:600px;" align="left">
			<label>枢纽详情:</label><br />
			<div align="center" id="station_first_demo">
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('pumpa')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">#1主机泵系统检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('pumpb')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">#2主机泵系统检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('pumpc')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">#3主机泵系统检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('pumpd')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">#4主机泵系统检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('lowelectrical')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">低压电气系统检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('rhysqs')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">润滑油、冷却水系统检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('yysystem')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">液压启闭机系统</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('gate')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">节制闸门及清污机检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('supervisory')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">自动化监控系统检查记录表</a> <a
					href="javascript:void(0)" class="easyui-linkbutton" onclick="dialogInfo('plumber')"
					data-options="iconCls:''" style="width: 600px;height: 30px;">水工建筑物检查记录表</a> -->
			</div>
		</div>
		<div align="center">
			<table id="departmentPatrol" cellspacing="0" cellpadding="0"></table>
		</div>
		</br>
		<div id="btn">
			<a id="btnAdd" href="javascript:void(0)" onclick="add()"
				class="easyui-linkbutton" data-options="iconCls:'icon_add'">提交</a>&nbsp;&nbsp;<a
				id="btnCancel" href="javascript:void(0)" onclick="closed()"
				class="easyui-linkbutton" data-options="iconCls:'icon_delete'">关闭</a>
		</div>
	</form>
	<div id="dialogContent">
		
	</div>
	<script type="text/javascript">
	var ids = [];
		$(function(){
			$('#dialogContent').dialog({    
			    width: 650,    
			    height: 600,    
			    closed: false,    
			    cache: false,    
			    modal: true
			}).dialog("close");
			$('#dialogContent').dialog("clear");
			var firstDemo = ${firstDemo};
			var html = "";
			if(firstDemo){
				var tempParentId = -1;
				var tempId = -1;
				var rows = 0;
				for (var obj = 0; obj <firstDemo.length;obj++ ) {
					if(firstDemo[obj].PARENTID == 0){
						tempParentId = firstDemo[obj].ID;
						html += "<a href='javascript:void(0)' class='easyui-linkbutton' onclick=dialogInfo('"+firstDemo[obj].ID+"') style='width: 600px;height: 30px;'>"+firstDemo[obj].CLASSES+"</a>";
						$("#dialogContent").append('<div id="'+firstDemo[obj].ID+'" style="display: none;"><p class="btt">'+firstDemo[obj].CLASSES+'</p><table class="table01" cellpadding="0" cellspacing="0"><tr>	<td class="gdlm" style="width:50px;">检 查<br /> 项 目</td><td class="gdlm">检查内容</td><td class="gdlm" style="width:80px;">检查结果</td><td class="gdlm">备注</td></tr></table></div>');
						//alert($("#"+firstDemo[obj].ID+" .table01").eq(0).html());
						ids.push(firstDemo[obj].ID);
						rows=0;
						tempId = -1;
					}else if(firstDemo[obj].PARENTID != 0 && tempParentId == firstDemo[obj].PARENTID){
						tempId = tempId == -1 ? firstDemo[obj].ID : tempId;
						var nextId = firstDemo[obj+1 < firstDemo.length ? obj+1 : obj].PARENTID;
						if(nextId == 0){
							nextId = firstDemo[obj+2 < firstDemo.length ? obj+2 : obj].PARENTID;
						}
						//alert(obj+1 < firstDemo.length ? obj+1 : firstDemo.length);
						/* 
							<tr><td rowspan="2" class="gdlm">进出水<br /> 流道
								<td>进、出水流道（管道）稳定；流道混凝土光滑平整，强度符合设计要求</td><td class="gdlm">
								<select class="register_input" value="合格"><option value="0">合格</option><option value="1">不合格</option></select></td><td> <textarea class="dwb"></textarea></td>
							</tr>
						
						*/
						if(rows == 0){
							var html1 = "<tr><td id='"+(tempParentId+"_"+firstDemo[obj].ID)+"' class='gdlm'>"+firstDemo[obj].CLASSES+"</td><td class='gdlm'>"+firstDemo[obj].NAME+"</td><td><select id='"+firstDemo[obj].PROJECTID+"_select' class='register_input'><option value='0'>合格</option><option value='1'>不合格</option></select></td><td> <textarea id='"+(tempParentId+"_"+firstDemo[obj].ID+"_"+firstDemo[obj].PROJECTID)+"_area' class='dwb'></textarea></td></tr>";
							$("#"+tempParentId+" .table01").eq(0).append(html1);
						}else if(tempId == firstDemo[obj].ID){
							var html1 = "<tr><td class='gdlm'>"+firstDemo[obj].NAME+"</td><td><select id='"+firstDemo[obj].PROJECTID+"_select' class='register_input'><option value='0'>合格</option><option value='1'>不合格</option></select></td><td> <textarea id='"+(tempParentId+"_"+firstDemo[obj].ID+"_"+firstDemo[obj].PROJECTID)+"_area' class='dwb'></textarea></td></tr>";
							$("#"+tempParentId+" .table01").eq(0).append(html1);
						}
						if(tempId == firstDemo[obj].ID){
							rows++;
							if(nextId != tempParentId){
								$("#"+tempParentId+"_"+tempId).attr("rowspan",rows);
							} 
						}else{
							$("#"+tempParentId+"_"+tempId).attr("rowspan",rows);
							tempId = firstDemo[obj].ID;
							rows = 0;
							if(rows == 0){
								var html1 = "<tr><td id='"+(tempParentId+"_"+firstDemo[obj].ID)+"' class='gdlm'>"+firstDemo[obj].CLASSES+"</td><td class='gdlm'>"+firstDemo[obj].NAME+"</td><td><select id='"+firstDemo[obj].PROJECTID+"_select' class='register_input'><option value='0'>合格</option><option value='1'>不合格</option></select></td><td> <textarea id='"+(tempParentId+"_"+firstDemo[obj].ID+"_"+firstDemo[obj].PROJECTID)+"_area' class='dwb'></textarea></td></tr>";
								$("#"+tempParentId+" .table01").eq(0).append(html1);
							}
							rows++;
						}
						if(obj+1 == firstDemo.length){
							$("#"+tempParentId+"_"+tempId).attr("rowspan",rows);
						}
					}
				}
			}
			if(html != "")
				$("#station_first_demo").html(html);
			
		/* 	$("#sid").combobox({
				url : 'patrolImplement/stationList.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				 value : $("#Sid").val(),
				required : true
			}); */
			
			$("#issue").combobox({
				url : 'patrolImplement/issueList.html?classes=3',
				valueField : 'id',
				textField : 'code',
				editable : false,
				required : true,
				onSelect: function(value){
					//console.info(value.code);
					$("#sid").combobox({
						url : 'patrolImplement/stationList.html?isid='+value.id,
						valueField : 'id',
						textField : 'name',
						editable : false,
						/* value : $("#Sid").val(), */
						required : true
					});
				}
			});
		});
		function dialogInfo(obj){
			$('#dialogContent').children("div").hide();
			$("#"+obj).show();
			var titles=$("#"+obj).find("p").html();
			$("#"+obj).find("p").hide();
			$('#dialogContent').dialog({title: titles});
			$('#dialogContent').dialog("open");
		}
		function backResult1(obj){
			var result="";
			for(var j = 0; j < obj.length; j++){
				var selects=$("#"+obj[j]).find(".register_input");
				for (var i = 0; i < selects.length; i++) {
					var select = selects[i];
					var selectResult=$(select).attr("id").substring(0,$(select).attr("id").indexOf("_select"))+"@"+$(select).parent().next().children(0).val();
					if($(select).val()==1){
						result=result+selectResult+",";
					}
				}
				if(result!=""){
					result.substr(0,result.length-1);
				}
			}
			
			return result;
		}
		function add(){
			var isValid = $("#ff").form('validate');
			if (!isValid) {
				//$.messager.progress('close');
				return false;
			}
			/* var pumpaResult=backResult("pumpa");
			var pumpbResult=backResult("pumpb");
			var pumpcResult=backResult("pumpc");
			var pumpdResult=backResult("pumpd");
			var lowelectricalResult=backResult("lowelectrical");
			var rhysqsResult=backResult("rhysqs");
			var yysystemResult=backResult("yysystem");
			var gateResult=backResult("gate");
			var supervisoryResult=backResult("supervisory");
			var plumberResult=backResult("plumber"); */
			var obj = {};
			//alert($("#date").datetimebox("getValue"));
			obj.implementid = "0";
			obj.examintime = $("#date").datetimebox("getValue");
			obj.station = $("#sid").combobox("getValue");
			obj.issueid = $("#issue").combobox("getValue");
			if(backResult1(ids)==""){
				$.messager.alert('消息提醒','问题记录不能为空！','info');
				return false;
			}
			obj.info = backResult1(ids);
			$.ajax({
				type : "POST",
				url : "patrolImplement/save.html",
				dataType : "text",
				data : obj,
				success : function(data) {
					if(data){
						$.messager.progress('close');
						$('#dialog').window('close');
						reflush();
						$.messager.alert('操作提示', '操作成功',
						'info');
					}else
						$.messager.alert('操作提示', '操作失败',
						'warning');
					
				}
			});
		}
		
		function closed(){
			$('#dialog').window('close');
		}
		
		function reflush() {
			document.getElementById('patrolImplement.htmlifm').contentWindow
					.$('#patrolImplement_list').datagrid('reload');
		}
	</script>
</body>
</html>
