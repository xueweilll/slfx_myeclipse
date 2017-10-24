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
<title>水利信息化综合管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/menuII.js"></script>
<script type="text/javascript" src="js/basic.js"></script>
<script type="text/javascript" src="js/message.js"></script>
<script type="text/javascript" src="js/jquery.timers.js"></script>
<script type="text/javascript" src="js/alarm.js"></script>
<style type="text/css">
.datagrid-mask {
	  opacity: 0;
	  filter: alpha(opacity = 0);
	    
}

.datagrid-mask-msg {
	 opacity: 0;
	 filter: alpha(opacity = 0);
}
</style>

</head>

<body id="ds" class="easyui-layout">
	<script type="text/javascript">
		function alarmSet() {
			var url = "alarmShip.html";
			addTab("报警设置", url, "icon icon-home");
		}
		function informationMessage(msg) {
			alert(msg);
		}
		var stationcode = undefined;
		function setstationcode(code) {
			stationcode = code;
		}
		function getData() {
		}
		$(function() {
			$('#ds').everyTime('1das', 'A', function() {
				var tab = $('#tt').tabs("getSelected");
				var index = $('#tt').tabs('getTabIndex', tab);
				if (index == "0") {
					$("#water_list").datagrid("load");
				} else if (index == "1") {
					$("#PPTN_list").datagrid("load");
				} else if (index == "2") {
					$("#PumpRun_list").datagrid("load");
				} else {
					$("#GateRun_list").datagrid("load");
				}
			}, 0, true);
			$('#tt').tabs({
				border : false,
				onSelect : function(title, index) {
					if (index == "0") {
						$("#water_list").datagrid("load");
					} else if (index == "1") {
						$("#PPTN_list").datagrid("load");
					} else if (index == "2") {
						$("#PumpRun_list").datagrid("load");
					} else {
						$("#GateRun_list").datagrid("load");
					}
				}
			});
			$("#water_list")
					.datagrid(
							{
								width : 'auto',
								height : 'auto',
								nowrap : false,
								striped : true,
								border : false,
								collapsible : false,
								fit : true,
								url : 'floodctl/waterCurrentList.html',
								idField : 'code',
								loadMsg : null,
								singleSelect : true,
								rownumbers : true,
								remoteSort : false,
								sortOrder : 'asc',
								sortName : 'levels',
								columns : [ [ {
									field : 'name',
									title : '枢纽名称',
									width : 100
								}, {
									field : 'd',
									title : '内河水位',
									width : 60
								},
								/* {field:'c',title:'外河水势',width:60},  */
								{
									field : 'b',
									title : '外河水位',
									width : 60
								}, {
									field : 'time',
									title : '采集时间',
									width : 120
								}, {
									field : 'levels',
									title : '',
									width : 160,
									hidden : true
								}
								/* {field:'e',title:'内河水势',width:60}  */
								] ],
								onBeforeLoad : function() {
									var tab = $('#tt').tabs("getSelected");
									tab = $('#tt').tabs('getTabIndex', tab);
									if (tab != "0") {
										return false;
									}
								},
								onClickRow : function(index, row) {
									var station = eval(row.obj);
									//$("#signMap")[0].contentWindow.childrenfun();
									document.getElementById('map.htmlifm').contentWindow
											.setCenter(station.lat, station.lon);
								}
							});
			$("#PPTN_list").datagrid({
				width : 'auto',
				height : 'auto',
				nowrap : false,
				striped : true,
				border : false,
				loadMsg : null,
				collapsible : false,
				fit : true,
				url : 'floodctl/pptnCurrentList.html',
				idField : 'code',
				singleSelect : true,
				rownumbers : true,
				columns : [ [ {
					field : 'name',
					title : '枢纽名称',
					width : 85
				}, {
					field : 'c',
					title : '降水量',
					width : 60
				}, {
					field : 'd',
					title : '降水历时',
					width : 60,
					hidden : true
				}, {
					field : 'e',
					title : '天气',
					width : 60,
					hidden : true
				}, {
					field : 'b',
					title : '时间',
					width : 120
				} ] ],
				onBeforeLoad : function() {
					var tab = $('#tt').tabs("getSelected");
					tab = $('#tt').tabs('getTabIndex', tab);
					if (tab != "1") {
						return false;
					}
				}
			});
			$("#PumpRun_list").datagrid({
				width : 'auto',
				height : '400',
				nowrap : false,
				striped : true,
				border : false,
				loadMsg : null,
				collapsible : false,
				fit : true,
				url : 'floodctl/pumpRunCurrentList.html',
				idField : 'code',
				singleSelect : true,
				rownumbers : true,
				remoteSort : false,
				multiSort : true,
				sortOrder : 'asc,asc',
				sortName : 'levels,unitname',
				columns : [ [ {
					field : 'levels',
					title : '',
					width : 50,
					hidden : true
				}, {
					field : 'name',
					title : '枢纽名称',
					width : 85
				}, {
					field : 'unitname',
					title : '机组编号',
					width : 60
				}, {
					field : 'b',
					title : '机组编号',
					width : 60,
					hidden : true
				}, {
					field : 'd',
					title : '运行状态',
					width : 60
				}, {
					field : 'c',
					title : '采集时间',
					width : 120
				}, {
					field : 'e',
					title : '实时流量',
					width : 60,
					hidden : true
				}

				] ],
				onBeforeLoad : function() {
					var tab = $('#tt').tabs("getSelected");
					tab = $('#tt').tabs('getTabIndex', tab);
					if (tab != "2") {
						return false;
					}
				},
				onLoadSuccess : function(data) {
					var rows = data.rows;
					var merges = [];
					var station = rows[0].name;
					var k = 0;

					for (var i = 0; i < rows.length; i++) {
						if (station == "") {
							station = rows[i].name;
							continue;
						}
						if (rows[i].name == station) {
							k++;
							station = rows[i].name;
						} else {
							if (k > 1) {
								console.info(i);
								merges.push({
									index : i - k,
									rowspan : k
								});
								station = rows[i].name;
								k = 1;
							} else {
								station = rows[i].name;
							}
						}
						if (i == rows.length - 1) {
							if (k > 1) {
								merges.push({
									index : i - k + 1,
									rowspan : k
								});
							}
						}
					}
					console.info(merges);
					for (var i = 0; i < merges.length; i++) {
						$(this).datagrid('mergeCells', {
							index : merges[i].index,
							field : 'name',
							rowspan : merges[i].rowspan
						});
					}
				}
			});
			$("#GateRun_list").datagrid({
				width : 'auto',
				height : '400',
				nowrap : false,
				striped : true,
				border : false,
				loadMsg : null,
				collapsible : false,
				fit : true,
				url : 'floodctl/gateRunCurentList.html',
				idField : 'code',
				singleSelect : true,
				rownumbers : true,
				remoteSort : false,
				multiSort : true,
				sortOrder : 'asc,asc',
				sortName : 'levels,gatename',
				columns : [ [ {
					field : 'name',
					title : '枢纽名称',
					width : 85
				}, {
					field : 'b',
					title : '闸门编号',
					width : 60,
					hidden : true
				}, {
					field : 'gatename',
					title : '节制闸编号',
					width : 60
				}, {
					field : 'd',
					title : '运行状态',
					width : 60,
					hidden : true
				}, {
					field : 'e',
					title : '位置状态',
					width : 60
				}, {
					field : 'levels',
					title : '',
					width : 160,
					hidden : true
				}, {
					field : 'c',
					title : '采集时间',
					width : 120
				} ] ],
				onBeforeLoad : function() {
					var tab = $('#tt').tabs("getSelected");
					tab = $('#tt').tabs('getTabIndex', tab);
					if (tab != "3") {
						return false;
					}
				},
				onLoadSuccess : function(data) {
					var rows = data.rows;
					var merges = [];
					var station = rows[0].name;
					var k = 0;

					for (var i = 0; i < rows.length; i++) {
						if (station == "") {
							station = rows[i].name;
							continue;
						}
						if (rows[i].name == station) {
							k++;
							station = rows[i].name;
						} else {
							if (k > 1) {
								console.info(i);
								merges.push({
									index : i - k,
									rowspan : k
								});
								station = rows[i].name;
								k = 1;
							} else {
								station = rows[i].name;
							}
						}
						if (i == rows.length - 1) {
							if (k > 1) {
								merges.push({
									index : i - k + 1,
									rowspan : k
								});
							}
						}
					}
					console.info(merges);
					for (var i = 0; i < merges.length; i++) {
						$(this).datagrid('mergeCells', {
							index : merges[i].index,
							field : 'name',
							rowspan : merges[i].rowspan
						});
					}
				}
			});
		});
	</script>
	<input type="hidden" id="sys" value="${sys}" />
	<input type="hidden" id="userid" value="${loginUser.userid}" />
	<div data-options="region:'north',split:false"
		style="height: 68px; background-image: url('images/fh_bg.png'); background-repeat: repeat-x; overflow: hidden;">
		<img id="log" src="images/logo.png" style="height: 68px; float: left;" />
		<div id="menu"
			style="background-image: url('images/logo_ft.png'); text-align: right; width: 533px; float: right; overflow: hidden; height: 68px; line-hight: 68px; display: inline-block;">
			<a href="javascript:void(0)" onclick="returnback()"
				class="returnback"></a> <a href="javascript:void(0)" class="warn"
				onclick="alarmSet()"></a> <a class="menu_message"
				herf="javascript:void(0)" onclick="showmessageCenter()"> <span
				id="messagecount"
				style="color: #fc982d; padding-left: 2px; font-weight: bold;">
					0</span>
			</a>
		</div>
	</div>
	<div data-options="region:'center',iconCls:'icon-home'"
		style="background: #eee;">
		<div id="tabs" class="easyui-tabs" fit="true" border="false"></div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<div
		data-options="region:'west',iconCls:'icon icon-menu',title:'菜单',split:true"
		style="width: 250px;" id="west">
		<div id='wnav' class="easyui-accordion" fit="true" border="false">
		</div>
	</div>
	<div data-options="region:'east',split:true,title:'防洪信息'"
		style="width: 360px;" id="east">
		<div id="tt" class="easyui-tabs"
			data-options="fit:true,tabPosition:'top'">
			<div title="水情" data-options="tabWidth:53">
				<table id="water_list" cellspacing="0" cellpadding="0"></table>
			</div>
			<div title="雨情" data-options="tabWidth:53">
				<table id="PPTN_list" cellspacing="0" cellpadding="0"></table>
			</div>
			<div title="机组" data-options="tabWidth:53">
				<table id="PumpRun_list" cellspacing="0" cellpadding="0"></table>
			</div>
			<div title="节制闸" data-options="tabWidth:53">
				<table id="GateRun_list" cellspacing="0" cellpadding="0"></table>
			</div>
		</div>
	</div>
	<div style="height: 34px;"
		data-options="region:'south',split:true,collapsible:false"
		class="bqxx">
		<span class="bqxx_span span_left02">当前用户：${loginUser.username}</span><span
			class="bqxx_span span_left01">登录时间：<%=new java.util.Date()%></span><span
			class="bqxx_span span_right">常州市城市防洪工程管理处信息化管理系统 v1.0</span>
	</div>
	<div id="dialog"></div>
	<div id="window"></div>
	<div id="messagedialog">
		<table id="messageCenter_list" cellspacing="0" cellpadding="0">
			</div>
</body>
</html>
