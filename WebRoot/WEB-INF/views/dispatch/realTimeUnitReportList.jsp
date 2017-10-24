<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@include file="../header.jsp"%>
<title>realTimeUnitReport</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	$(function() {
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2,'', {
			text: '清空',
			handler: function(target){
				$(target).datetimebox('setValue','');
				$(target).datetimebox('hidePanel');
			}
		});
		var starttime="";
		 var endtime="";
		$("#employee_list").datagrid({
			width : 'auto',
			height : 'auto',
			//pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'realTimeUnitReport/realTimeUnitReportList.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : false,
			rownumbers : true,
			showFooter: true,
			columns : [ [ 
		    {
				field : 'sname',
				title:'枢纽名称',
				width:120
			},  
			{
				field : 'uname',
				title : '机组名称',
				width : 100
			}, 
			{
				field : 'kjtime',
				title : '开机时长(时)',
				width : 200
			}, {
				field : 'totalflow',
				title : '总流量(万立方米)',
				width : 200
			}, {
				field : 'uid',
				hidden:'true'
			} 
			] ],
			/* view:detailview,
			detailFormatter:function(index,row){
			return "<div style=\"padding:2px\"><table class=\"unitList\"style=\"width:450\">"+
					"</table>"+
					"</div>";
			},
		 	onExpandRow: function(index,row){
				var ddv = $(this).datagrid('getRowDetail',index).find('table.unitList');
			     var uurl;
					uurl="realTimeUnitReport/findUnitDetials.html?uid="+row.uid+"&starttime="+starttime+"&endtime="+endtime;
					ddv.datagrid({
						url:uurl,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'机组运行记录',
						height:'auto',
						columns:[[
							{field:'starttime',title:'开始时间',width:180},
							{field:'endtime',title:'结束时间',width:180},
							{field:'kjtime',title:'开机总时间',width:100},
							{field:'totalflow',title:'开机总流量',width:100}
						]],
						onResize:function(){
							$('#employee_list').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#employee_list').datagrid('fixDetailRowHeight',index);
							},0);
						}
					});
					//$('#employee_list').datagrid('x',index);
				},	 */ 	
				onLoadSuccess:function(data){
					 var rows = data.rows;
					 var merges = [];
					 var snames=rows[0].sname;
					 var k=0;
					 starttime=data.starttime;
					 endtime=data.endtime;
					 for(var i=0; i<rows.length; i++){
						if(snames == ""){
							snames=rows[i].sname;
							continue;
						}
					  	if(rows[i].sname == snames){
					  		k++;
					  		snames=rows[i].sname;
					  	}else {
					  		if(k > 1){
					  			//console.info(i);
						  		merges.push({index: i - k,rowspan: k  });
						  		snames=rows[i].sname;
						  		k=1;
					  		}else {
					  			snames= rows[i].sname;
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
						 //console.info(merges);
		                 $(this).datagrid('mergeCells',{
		                     index: merges[i].index,
		                     field: 'sname',
		                     rowspan: merges[i].rowspan
		                 });
				}
					 },
					 onDblClickRow:function(rowIndex,rowData){
					  showDialogWH("查看机组运行记录", "realTimeUnitReport/findUnitDetialsInfo.html?uid="+rowData.uid+"&starttime="+starttime+"&endtime="+endtime,680,600);
					    }
		});
		var scodes = [];
		var sid=[];
		var ucodes = [];
		var uid=[];
		
		$("#statioin").combogrid({
			width : 'auto',
			url : 'realTimeUnitReport/station.html',
			remoteSort : false,
			idField : 'id',
			textField : 'name',
			multiple : true,
			frozenColumns:[[{field:'ck',checkbox:true}]],
			columns : [ [ {
				field : 'name',
				title : '枢纽名称'
			}, {
				field : 'code',
				hidden : true
			}, {
				field : 'id',
				hidden : true
			} ] ],
			onCheck : function(index, row) {
				var isNext = false;
				$.each(scodes, function(index, item) {
					if (item == row.code) {
						isNext = true;
					}
				});
				if (!isNext) {
					scodes.push(row.code);
					sid.push(row.id);
					findunit(sid);
				} else {
				}
			},
			onUncheck : function(index, row) {
				scodes.remove(row.code);
				sid.remove(row.id);
				findunit(sid);
			},
			onCheckAll : function(rows) {
				for (var i = 0; i < rows.length; i++) {
					var isNext = false;
					$.each(scodes, function(index, item) {
						if (item == rows[i].code) {
							isNext = true;
						}
					});
					if (!isNext) {
						scodes.push(rows[i].code);
						sid.push(rows[i].id);
						findunit(sid);
					}
				}
			},
			onUncheckAll : function(rows) {
				for (var i = 0; i < rows.length; i++) {
					scodes.remove(rows[i].code);
					sid.remove(rows[i].id);
					findunit(sid);
				}
			}
		});
	 function findunit(scodes){
		$("#unit").combogrid({
			width : 'auto',
			url : 'realTimeUnitReport/unit.html?sids='+scodes,
			idField : 'id',
			textField : 'name',
			frozenColumns:[[{field:'ck',checkbox:true}]],
			multiple : true,
			columns : [ [  {
				field : 'name',
				title : '机组名称'
			}, {
				field : 'code',
				hidden : true
			},{
				field:'id',
				hidden:true
			} ] ] ,
			onCheck : function(index, row) {
				var isNext = false;
				$.each(uid, function(index, item) {
					if (item == row.id) {
						isNext = true;
					}
				});
				if (!isNext) {
					ucodes.push(row.code);
					uid.push(row.id);
				} else {
				}

			},
			onUncheck : function(index, row) {
				ucodes.remove(row.code);
				uid.remove(row.id);
			},
			onCheckAll : function(rows) {
				for (var i = 0; i < rows.length; i++) {
					var isNext = false;
					$.each(uid, function(index, item) {
						if (item == rows[i].id) {
							isNext = true;
						}
					});
					if (!isNext) {
						ucodes.push(rows[i].code);
						uid.push(rows[i].id);
					}
				}
			},
			onUncheckAll : function(rows) {
				for (var i = 0; i < rows.length; i++) {
					ucodes.remove(rows[i].code);
					uid.remove(rows[i].id);
				}
			} 
		});
		}
	 
		$("#search").bind("click", function() {
			$("#employee_list").datagrid('unselectAll');
			starttime=$("#begintimes").datetimebox("getValue");
			 endtime=$("#endtimes").datetimebox("getValue");
			var data = {
				/* "scode" : scodes,
				"ucode" : ucodes */
				"scode":sid,
				"ucode":uid
			};
			if((starttime !=""&&endtime=="")){
				$.messager.alert("操作提示","结束时间不能为空","error");
			}else if(starttime ==""&&endtime!=""){
				$.messager.alert("操作提示","开始时间不能为空","error");
			}else{
			$("#employee_list").datagrid("load", {
				"jsonStr" : JSON.stringify(data),
				"begintimes" : $("#begintimes").datetimebox("getValue"),
				"endtimes" : $("#endtimes").datetimebox("getValue")
			});
			}
		});
		$("#export").bind("click",function(){
			var data = {
					"scode" : sid,
					"ucode" : uid
				};
			
			/* if($("#begintimes").datetimebox("getValue")!=""&&$("#endtimes").datetimebox("getValue")==""){
				$.messager.alert("操作提示","结束时间不能为空","error");
				return;
			}else if($("#begintimes").datetimebox("getValue")==""&&$("#endtimes").datetimebox("getValue")!=""){
				$.messager.alert("操作提示","开始时间不能为空","error");
			    return;
			}
			else{ */
				var link="realTimeUnitReport/export.html?jsonStr="+ JSON.stringify(data) + "&begintimes="+ starttime + "&endtimes="+endtime;
			/* } */
			window.location.href=link;
		});
		$("#begintimes").datetimebox({
			editable : false,
			buttons : buttons,
			onChange : function() {
				$("#endtimes").datetimebox('enableValidation');
			}
		});
		$("#endtimes").datetimebox({
			editable : false,
			buttons : buttons
		});
		Array.prototype.remove = function(val) {
			var index = this.indexOf(val);
			if (index > -1) {
				this.splice(index, 1);
			}
		};
	/*  	var p = $("#employee_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});  */
		
	});
</script>
</head>

<body class="easyui-layout" id="cc">
	<table id="employee_list" cellspacing="0" cellpadding="0"
		data-options="toolbar:'#tb'">
	</table>
	<div id="tb">
		<div class="cz_div">
			<form id="department" method="post">
				枢纽名称:<input id="statioin" class="easyui-combobox"
					style="width: 150px" editable="false" /> &nbsp;&nbsp; 机组名称:<input
					id="unit" class="easyui-combobox" style="width: 150px"
					editable="false"> &nbsp;&nbsp;开机时间区间:<input id="begintimes"
					class="easyui-datetimebox" style="width: 130px">
				&nbsp;&nbsp; ~ <input id="endtimes" class="easyui-datetimebox"
					data-options="width:160,editable:false,validType:['compareDate[begintimes]']"
					style="width: 130px"> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-search" id="search">查询</a>
				&nbsp;&nbsp; <a id="export" class="easyui-linkbutton" style=""
					data-options="plain:false,iconCls:'icon-edit'">导出</a>
			</form>
		</div>
	</div>
</body>
</html>
