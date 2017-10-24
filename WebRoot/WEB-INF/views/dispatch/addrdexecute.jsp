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
<title>My JSP 'messageInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<input id="id" type="hidden" value="${receiptDispatchStations.getId()}" />
	<input id="executeid" type="hidden"  value=""/>
	<div>		
		<p style="vertical-align: middle;"><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">具&nbsp;体&nbsp;实&nbsp;施&nbsp;单</span></p>
	</div>
	<form id="addrdexecute" method="post"
		style="margin:10px; text-align:left;">
		<div>
			<!-- <label for="rdid">调度单ID:</label>  -->
			<input id="rdid" type="hidden" name="rdid"
				value="${receiptDispatchStations.getRdispatchid()}" />
		</div>
		<div>
			<label for="sname">执行枢纽:</label> 
			<input id="sid" type="hidden" name="sid" 
				value="${receiptDispatchStations.getSid()}" />
			<input id="sname"
				class="easyui-textbox" name="sname" disabled="disabled"
				style="width: 415px;" data-options="required:true"
				value="${receiptDispatchStations.getStation().getName()}"/>
		</div>

		<div>
			<label for="executer">调度执行人:</label> <input id="executer"
				style="width:415px;" data-options="required:true"
				value="${executer}" />
		</div>
		<div>

			<label>开机时外河水位:</label> <input class="easyui-textbox"
				id="startouterlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="" />

		</div>
		<div>
			<label>开机时内河水位:</label> <input class="easyui-textbox"
				id="startinlandlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="" />
		</div>
		<div>
			<label>停机时外河水位:</label> <input class="easyui-textbox"
				id="stopouterlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="" />

		</div>
		<div>
			<label>停机时内河水位:</label> <input class="easyui-textbox"
				id="stopinlandlevel" data-options="validType:'intOrFloat'"
				style="width:415px" value="" />

		</div>
		<div>
			<label for="memo">执行备注:</label> <input id="memo"
				class="easyui-textbox" name="memo" style="width:415px;height:100px;"
				data-options="multiline:true" value="${rde.getMemo()}"/>
		</div>

		<div
			style="width: 520px;height: 220px;margin-top: 10px;margin-bottom: 10px">
			<table id="units"></table>
		</div>
		<div
			style="width: 520px;height: 220px;margin-top: 10px;margin-bottom: 10px">
			<table id="gates"></table>
		</div>

		<div id="btn" style="text-align: center">
		<!-- <a id="test" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">测试</a>&nbsp;&nbsp; -->
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;
			<a id="sub2" href="javascript:void(0)" class="easyui-linkbutton" 
			data-options="iconCls:'icon-save'" onclick="save2()">保存并提交</a>&nbsp;&nbsp;				
			<a id="clear"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>

	<script type="text/javascript">
		var editIndex1 = undefined;
		var editIndex2 = undefined;
		var sid = document.getElementById("sid").value;
		var rdid = document.getElementById("rdid").value;
		var executeid = $("#executeid").val() == "" ? "0" : $("#executeid").val();
		$(function() {
			/*$("#memo").textbox({
				required : true,
				showSeconds : true,
				missingMessage : "该输入项为必输项"
			});*/
			
			$('#executer').combogrid({
				url : 'document/getUsername.html',
				idField : 'id',
				textField : 'ename',
				required : true,
				editable : false,
				multiple : true,
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				columns : [ [ {
					field : 'dname',
					title : '部门',
					width : 145
				}, {
					field : 'ename',
					title : '人员名称',
					width : 140
				} ] ],
				fitColumns : true
			});
			
			$('#units').datagrid({
				columns : [ [ {
					field : 'sid',
					title : '机组名称',
					width : 150,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'post',
							url : 'rdexecute/findUnits.html?sid='+sid,
							required : true,
							editable : false,
							onSelect : function(){
								var value = $(this).combobox('getValue');
								var isCan = true;
								//判断本机组在本次实施单中是否有未完成，有则不能添加
								var rows = $('#units').datagrid('getRows');	
								for(var i=0;i< rows.length;i++){
									if(rows.uid ==""){
										continue;
									}
									if(rows[i].uid == value && (rows[i].endtime == "" || rows[i].endtime == undefined)){
										isCan = false;
									}
								}
								if(!isCan){
									alert("该机组在此次实施单中还未完成，不能添加！");
									$(this).combobox('setValue','');
									$(this).combobox('setText','');
									return false;
								}
								//判断本机组在其他实施单中是否有未完成，如果有则不能添加
								$.ajax({
									type : "POST",
									url : "rdexecute/checkUnitComplete.html",
									dataType : "text",
									async:false, 
									data : {
										"uid":value,
										"executeid":executeid
									},
									success : function(msg) {
										data = eval('(' + msg + ')');
										if (!data.result) {
											isCan = false;
										}
									}
								});
								if(!isCan){
									alert("该机组在其他实施单中还未完成，不能添加！");
									$(this).combobox('setValue','');
									$(this).combobox('setText','');
									return false;
								}
							}
							
						}
					}
				},{field:'uid',title:'',hidden:true}
				,{field:'begintime',title:'开始时间',editor:{type:'datetimebox',options:{required : true,editable:false}},width:170},
				{field:'endtime',title:'结束时间',editor:{type:'datetimebox',options:{editable:false}},width:170}] ],
				width : 'auto',
				height : 'auto',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				toolbar : [ {
					text : '添加机组执行明细',
					iconCls : 'icon-add',
					handler : append1
				}, {
					text : '删除机组执行明细',
					iconCls : 'icon-cut',
					handler : removeit1
				} ],
				iconCls : 'icon-edit',
				onClickCell : onClickCell1,
				onEndEdit : onEndEdit1
			});
			
			function endEditing1() {
				if (editIndex1 == undefined) {
					return true;
				}
				if ($('#units').datagrid('validateRow', editIndex1)) {
					$('#units').datagrid('endEdit', editIndex1);
					editIndex1 = undefined;
					return true;
				} else {
					return false;
				}
			}

			function onClickCell1(index, field) {
				if (editIndex1 != index) {
					if (endEditing1()) {
						$('#units').datagrid('selectRow', index).datagrid(
								'beginEdit', index);
						var ed = $('#units').datagrid('getEditor', {
							index : index,
							field : field
						});
						if (ed) {
							($(ed.target).data('textbox') ? $(ed.target).textbox(
									'textbox') : $(ed.target)).focus();
						}
						editIndex1 = index;
					} else {
						setTimeout(function() {
							$('#units').datagrid('selectRow', editIndex1);
						}, 0);
					}
				}
			}

			function onEndEdit1(index, row) {
				var ed1 = $(this).datagrid('getEditor', {
					index : index,
					field : 'sid'
				});
				row.sid = $(ed1.target).combobox('getText');
				row.uid = $(ed1.target).combobox('getValue');
			}

			function append1() {
				if (endEditing1()) {
					$('#units').datagrid('appendRow', {});
					editIndex1 = $('#units').datagrid('getRows').length - 1;
					$('#units').datagrid('selectRow', editIndex1).datagrid(
							'beginEdit', editIndex1);
				}
			}

			function removeit1() {
				if (editIndex1 == undefined) {
					return;
				}
				$('#units').datagrid('cancelEdit', editIndex1).datagrid(
						'deleteRow', editIndex1);

				editIndex1 = undefined;
			}
			
			$('#gates').datagrid({
				columns : [ [ {
					field : 'sid',
					title : '闸门名称',
					width : 150,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'post',
							url : 'rdexecute/findGates.html?sid='+sid,
							required : true,
							editable : false
						}
					}
				
				},{field:'gid',title:'',hidden:true},
				{field:'operate',title:'操作',editor:{type:'combobox',options:{
					url : 'paramers/Operate.html',
					valueField : 'id',
					textField : 'text',
					method : 'post',
					editable : false,
					required : true,
					onSelect: function () {
						var value = $(this).combobox('getValue');
						var dd = $('#gates').datagrid('getEditor', { index: editIndex2, field: 'operatetime' });
						if(value == "2"){
							$(dd.target).datetimebox({
								required : false,editable:false								
							});
						}else{
							$(dd.target).datetimebox({
								required : true,editable:false
							});
						}
					}
					}},width:170},{field:'operatetype',title:'',hidden:true},
				{field:'operatetime',title:'闸门执行时间',editor:{type:'datetimebox',options:{required : true,editable:false}},width:170}
				] ],
				width : 'auto',
				height : 'auto',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				toolbar : [ {
					text : '添加闸门执行明细',
					iconCls : 'icon-add',
					handler : append2
				}, {
					text : '删除闸门执行明细',
					iconCls : 'icon-cut',
					handler : removeit2
				} ],
				iconCls : 'icon-edit',
				onClickCell : onClickCell2,
				onEndEdit : onEndEdit2
			});
			
			function endEditing2() {
				if (editIndex2 == undefined) {
					return true;
				}
				if ($('#gates').datagrid('validateRow', editIndex2)) {
					$('#gates').datagrid('endEdit', editIndex2);
					editIndex2 = undefined;
					return true;
				} else {
					return false;
				}
			}

			function onClickCell2(index, field) {
				
				if (editIndex2 != index) {
					if (endEditing2()) {
						$('#gates').datagrid('selectRow', index).datagrid(
								'beginEdit', index);
						var ed = $('#gates').datagrid('getEditor', {
							index : index,
							field : field
						});
						if (ed) {
							($(ed.target).data('textbox') ? $(ed.target).textbox(
									'textbox') : $(ed.target)).focus();
						}
						editIndex2 = index;
					} else {
						setTimeout(function() {
							$('#gates').datagrid('selectRow', editIndex2);
						}, 0);
					}
				}
			}

			function onEndEdit2(index, row) {
				var ed1 = $(this).datagrid('getEditor', {
					index : index,
					field : 'operate'
				});
				row.operate = $(ed1.target).combobox('getText');
				row.operatetype = $(ed1.target).combobox('getValue');
				var ed2 = $(this).datagrid('getEditor', {
					index : index,
					field : 'sid'
				});
				row.sid = $(ed2.target).combobox('getText');
				row.gid = $(ed2.target).combobox('getValue');
			}

			function append2() {
				if (endEditing2()) {
					$('#gates').datagrid('appendRow', {});
					editIndex2 = $('#gates').datagrid('getRows').length - 1;
					$('#gates').datagrid('selectRow', editIndex2).datagrid(
							'beginEdit', editIndex2);
				}
			}

			function removeit2() {
				if (editIndex2 == undefined) {
					return;
				}
				$('#gates').datagrid('cancelEdit', editIndex2).datagrid(
						'deleteRow', editIndex2);
				editIndex2 = undefined;
			}

			
			$("#sub").bind("click",	function() {
				$.messager.progress();
				var isValid = $("#addrdexecute").form('validate');
				if (!isValid) {
					$.messager.progress('close');
					alert("必填项不能为空");
					return;
				}
				
				var unit = $('#units').datagrid('getRows');			
				var gate = $('#gates').datagrid('getRows');
				if (unit.length == 0 && gate.length == 0) {
					$.messager.progress('close');
					alert("机组执行明细和闸门执行明细不能同时为空！");
					return;
				}				
				
				var us="";
				var units=[];
				if(endEditing1()){
					var rows=$('#units').datagrid('getRows');
					for(var i=0;i<rows.length;i++){
						if(rows[i].endtime == null || rows[i].endtime == "" || rows[i].endtime == undefined){
							continue;
						}
						if(rows[i].begintime>rows[i].endtime){
							$.messager.progress('close');
							alert("机组的开始时间不能大于结束时间");
							return;
						}
					}
					for(var i=0;i<rows.length;i++){
						if(rows[i].endtime !=""){
							units.push({"unitid":rows[i].uid,"begintime":rows[i].begintime,"endtime":rows[i].endtime});
						}else{
							units.push({"unitid":rows[i].uid,"begintime":rows[i].begintime});
						}
					}
				}
				
				var gates=[];
				if(endEditing2()){
					var rows=$('#gates').datagrid('getRows');
					for(var i=0;i<rows.length;i++){
						if(rows[i].operatetime != ""){
							gates.push({"gid":rows[i].gid,"operatetime":rows[i].operatetime,"operate":rows[i].operatetype});
						}else{
							gates.push({"gid":rows[i].gid,"operate":rows[i].operatetype});
						}
						
					}
				}

				var executers= [];
				var strs = $("#executer").combogrid("getValues") ;
				for(var i = 0;i< strs.length;i++){
					executers.push({"peopletyle":0,"userid":strs[i]});
				}
				
				var json={
					"id":executeid,
					"sid":sid,
					"rdstationid":$("#id").val(),
					"memo":$("#memo").textbox("getValue").replace(/\s+/g,""),
					"startouterlevel":$("#startouterlevel").textbox("getValue"),
					"startinlandlevel":$("#startinlandlevel").textbox("getValue"),
					"stopouterlevel":$("#stopouterlevel").textbox("getValue"),
					"stopinlandlevel":$("#stopinlandlevel").textbox("getValue"),
					"executegate":gates,
					"rdeplist":executers,
					"executeunits":units
				};
				$.ajax({
					type : "POST",
					url : "rdexecute/save.html",
					dataType : "text",
					data : {
						"json": JSON.stringify(json),
						"state":0,
						"rdid":rdid
					},
					success : function() {
						$.messager.progress('close');
						$('#dialog').window('close');
						reflush();
						$.messager.alert('操作提示', '操作成功','info');
					}
				});
			});
			
			$("#sub2").bind(
					"click",
					function() {
						$.messager.progress();
						var isValid = $("#addrdexecute").form('validate');
						if (!isValid) {
							$.messager.progress('close');
							alert("必填项不能为空");
							return;
						}
						
						if($("#startouterlevel").textbox("getValue") == ""){
							$.messager.progress('close');
							$.messager.alert("操作提示","开机外河水位不能为空！","error");
							return;
						}
						
						if($("#startinlandlevel").textbox("getValue") == ""){
							$.messager.progress('close');
							$.messager.alert("操作提示","开机内河水位不能为空！","error");
							return;
						}
						
						if($("#stopouterlevel").textbox("getValue") == ""){
							$.messager.progress('close');
							$.messager.alert("操作提示","停机外河水位不能为空！","error");
							return;
						}
						
						if($("#stopinlandlevel").textbox("getValue") == ""){
							$.messager.progress('close');
							$.messager.alert("操作提示","停机内河水位不能为空！","error");
							return;
						}
						
						var unit = $('#units').datagrid('getRows');			
						var gate = $('#gates').datagrid('getRows');
						if (unit.length == 0 && gate.length == 0) {
							$.messager.progress('close');
							alert("机组执行明细和闸门执行明细不能同时为空！");
							return;
						}
						
						if(endEditing1()){
							var isEtime=false;
							for(var i=0;i<unit.length;i++){
								var etime = unit[i].endtime;
								if(etime == null || etime == undefined || etime ==""){
									isEtime = true;
								}
							}
							if(isEtime){
								$.messager.progress('close');
								alert("机组执行结束时间必须输入！");
								return;
							}
						}
						
						var us="";
						var units=[];
						if(endEditing1()){
							var rows=$('#units').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								if(rows[i].endtime == null || rows[i].endtime == "" || rows[i].endtime == undefined){
									continue;
								}
								if(rows[i].begintime>rows[i].endtime){
									$.messager.progress('close');
									alert("机组的开始时间不能大于结束时间");
									return;
								}
							}
							for(var i=0;i<rows.length;i++){
								if(rows[i].begintime !=""){
									units.push({"unitid":rows[i].uid,"begintime":rows[i].begintime,"endtime":rows[i].begintime});
								}else{
									units.push({"unitid":rows[i].uid,"begintime":rows[i].begintime});
								}
							}
						}
						
						var ge="";
						var opertime="";
						var oper="";
						var gates=[];
						if(endEditing2()){
							var rows=$('#gates').datagrid('getRows');
							for(var i=0;i<rows.length;i++){
								if(rows[i].operatetime != ""){
									gates.push({"gid":rows[i].gid,"operatetime":rows[i].operatetime,"operate":rows[i].operatetype});
								}else{
									gates.push({"gid":rows[i].gid,"operate":rows[i].operatetype});
								}
								
							}
						}

						var executers= [];
						var strs = $("#executer").combogrid("getValues") ;
						for(var i = 0;i< strs.length;i++){
							executers.push({"peopletyle":0,"userid":strs[i]});
						}

						var json={
								"id":executeid,
								"sid":sid,
								"rdstationid":$("#id").val(),
								"memo":$("#memo").textbox("getValue").replace(/\s+/g,""),
								"startouterlevel":$("#startouterlevel").textbox("getValue"),
								"startinlandlevel":$("#startinlandlevel").textbox("getValue"),
								"stopouterlevel":$("#stopouterlevel").textbox("getValue"),
								"stopinlandlevel":$("#stopinlandlevel").textbox("getValue"),
								"executegate":gates,
								"rdeplist":executers,
								"executeunits":units
							};
						
						$.ajax({
							type : "POST",
							url : "rdexecute/save.html",
							dataType : "text",
							data : {
								"json": JSON.stringify(json),
								"state":1,
								"rdid":rdid
							},
							success : function() {
								$.messager.progress('close');
								$('#dialog').window('close');
								reflush();
								$.messager.alert('操作提示', '操作成功','info');
							}
						});
					});

			$("#clear").bind("click", function() {
				//$("#addrdexecute").form("clear");
				$("#dialog").dialog("close");
			});
			$("#cancle").bind("click", function() {
				$("#dialog").dialog("close");
			});
			function reflush() {
				document.getElementById('rdexecute.htmlifm').contentWindow
						.$('#rdexelist').datagrid('reload');
			}
		});

	</script>

</body>
</html>
