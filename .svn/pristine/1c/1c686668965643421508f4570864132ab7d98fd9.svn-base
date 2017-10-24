<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
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
<title>My JSP 'current.jsp' starting page</title>

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
<style type="text/css">
.datagrid-mask{
	  opacity: 0;
	  filter: alpha(opacity = 0);    
}
.datagrid-mask-msg{
	 opacity: 0;
	 filter: alpha(opacity = 0);
}
</style>
	<script type="text/javascript">
		var arrayName = [];
		var arrayData = [];
		var arrayData1 = [];
		var szIP = "10.32.222.109";
		var szPort = "8080";
		var szUsername = "admin";
		var szPassword = "12345";
		var iChannelID = 1;
		var t = false;
		var result = 0;
		var g_iWndIndex = 0;
		function getDatas() {
			var tab = $('#tabsaas').tabs("getSelected");
			var index = $('#tabsaas').tabs('getTabIndex', tab);
			if (index == "2") {
				$("#PumpRun_list1").datagrid("load");
			} else if (index == "3") {
				$("#GateRun_list1").datagrid("load");
			}
		}
		function clock() {
			getDatas();
			getDatas1();
		}
		function getDatas1() {
			$.post("floodctl/waterCurrentList.html", {
				"code" : $("#code").val()
			}, function(data) {
				$data = $(data);
				$data.each(function() {
					if(arrayName.length<80){
						arrayName.push(this.time);
						arrayData.push(this.b);
						arrayData1.push(this.d);
					}else{
						arrayName.shift();
						arrayData.shift();
						arrayData1.shift();
						arrayName.push(this.time);
						/* -(Math.random()*100) */
						arrayData.push(this.b);
						arrayData1.push(this.d);
					}
				});
				 myChart.setOption({
					 xAxis:{
						 data: arrayName
					 },
					 series: [{
				            data: arrayData
				        },{
				        	data: arrayData1
				        }]
				    });
			}, "json");
		}
		var myChart = echarts.init(document.getElementById('container'));
		$(function() {
			$('#tabsaas').tabs();
			var date = new Date();
			var startDate = new Date();
			startDate.setTime(date.getTime() - (1000 * 60 * 60 * 24));
			date.Format("yyyy-MM-dd hh:mm:ss");
			$.post("historyWaterRegime/historyWaterRegimeList.html", {
				page : 1,
				rows : 80,
				starttime : startDate.Format("yyyy-MM-dd hh:mm:ss"),
				endtime : date.Format("yyyy-MM-dd hh:mm:ss"),
				stcd:$("#code").val()
			}, function(json) {
				$json = $(json.rows);
				$json.each(function() {
					arrayData.unshift(this.ppupz);
					arrayData1.unshift(this.ppdwz);
					arrayName.unshift(this.tm);
				});
				option = {
					    title: {
					        text: '水情曲线图'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['内河水位','外河水位']
					    },
					    xAxis: {
					    	type : 'category',
				            boundaryGap : false,
				            data :arrayName
					    },
					    yAxis: {
					        type: 'value',
					        name:'水位（米）',
					        boundaryGap: [0, '100%'],
					        splitLine: {
					            show: false
					        }
					    },
					    series: [{
							name : "外河水位",
							type:'line',
							data : arrayData
						}, {
							name : "内河水位",
							type:'line',
							data : arrayData1
						} ]
					};
				myChart.setOption(option);
				myInterval = self.setInterval("clock()", 5000);
			},'json');
			$.ajax({
				type : 'POST',
				url : "currentMonitorsController/stationContents.html",
				data : {
					"code" : $("#code").val()
				},
				success : function(msg) {
					if (msg == "" || msg == undefined) {
						return;
					}
					data = eval('(' + msg + ')');
					szIP = data.NVRUrl;
					szPort = data.NVRProt;
					szUsername = data.NVRUsername;
					szPassword = data.NVRPassword;
					console.info(szIP);
				}
			});
			$("#PumpRun_list1").datagrid({
				width : 'auto',
				height : '400',
				nowrap : false,
				striped : true,
				border : false,
				collapsible : false,
				loadMsg:null,
				fit : true,
				url : 'floodctl/pumpRunCurrentList.html',
				queryParams : {
					"code" : $("#code").val()
				},
				idField : 'id',
				singleSelect : true,
				rownumbers : true,
				columns : [ [ {
					field : 'unitname',
					title : '机组编号',
					width : 100
				}, {
					field : 'c',
					title : '时间',
					width : 160
				}, {
					field : 'd',
					title : '运行状态',
					width : 100
				}, {
					field : 'e',
					title : '实时流量',
					width : 100
				} ] ],
				onBeforeLoad : function() {
					var tab = $('#tabsaas').tabs("getSelected");
					tab = $('#tabsaas').tabs('getTabIndex', tab);
					if (tab != "2") {
						return false;
					}
				}
			});

			$("#GateRun_list1").datagrid({
				width : 'auto',
				height : '400',
				nowrap : false,
				striped : true,
				border : false,
				collapsible : false,
				loadMsg:null,
				fit : true,
				url : 'floodctl/gateRunCurentList.html',
				idField : 'id',
				singleSelect : true,
				rownumbers : true,
				queryParams : {
					"code" : $("#code").val()
				},
				columns : [ [ {
					field : 'gatename',
					title : '闸门编号',
					width : 100
				}, {
					field : 'c',
					title : '时间',
					width : 160
				}, {
					field : 'd',
					title : '运行状态',
					width : 100
				}, {
					field : 'e',
					title : '位置状态',
					width : 100
				} ] ],
				onBeforeLoad : function() {
					var tab = $('#tabsaas').tabs("getSelected");
					tab = $('#tabsaas').tabs('getTabIndex', tab);
					if (tab != "3") {
						return false;
					}
				}
			});
			$('#tabsaas')
					.tabs(
							{
								border : false,
								onSelect : function(title, index) {
									var code = $("#code").val();
									/*if(index=="1"){
										 $("#WarnLog_list1").datagrid("load");
									} else if(index=="3"){
										$("#PPTN_list1").datagrid("load");
									} else */
									if (index == "2") {
										$("#PumpRun_list1").datagrid("load");
									} else if (index == "3") {
										$("#GateRun_list1").datagrid("load");
									} else if (index == "4") {
										// 检查插件是否已经安装过
										if (-1 == WebVideoCtrl
												.I_CheckPluginInstall()) {
											$.messager.confirm('确认对话框', '您还未安装过插件,请点击确定下载！', function(r){
												if (r){
												    window.open("js/video/WebComponents.exe");
												}
											});
											/* $.messager
													.alert(
															'操作提示',
															'您还未安装过插件，双击开发包目录里的WebComponents.exe安装！',
															'error'); */
											return;
										}

										// 初始化插件参数及插入插件
										WebVideoCtrl.I_InitPlugin(585, 330, {
											iWndowType : 1,
											cbSelWnd : function(xmlDoc) {
												g_iWndIndex = $(xmlDoc).find(
														"SelectWnd").eq(0)
														.text();
											}
										});
										WebVideoCtrl
												.I_InsertOBJECTPlugin("divPlugin");
										WebVideoCtrl.I_ChangeWndNum(3);
										clickLogin();
										$
												.ajax({
													type : 'POST',
													url : "currentMonitorsController/cameraListSecond.html?scode="
															+ $("#code").val(),
													data : {
														"code" : $("#code")
																.val()
													},
													success : function(msg) {
														//alert(msg);
														var data = eval(msg);

														for (var i = 0; i < data.length; i++) {
															var oWndInfo = WebVideoCtrl
																	.I_GetWindowStatus(i), szInfo = "";
															var channel = data[i].channel;

															if ("" == szIP) {
																return;
															}

															if (oWndInfo != null) {// 已经在播放了，先停止
																WebVideoCtrl
																		.I_Stop();
															}

															var iRet = WebVideoCtrl
																	.I_StartRealPlay(
																			szIP,
																			{
																				iWndIndex : i,
																				iStreamType : 1,
																				iChannelID : channel,
																				bZeroChannel : false
																			});
														}
													}
												});
									}
								}
							});

			$('#container1')
					.highcharts(
							{
								chart : {
									type : 'column'
								},
								title : {
									text : '雨量柱状图'
								},
								xAxis : {
									categories : [ '00', '02', '04', '06',
											'08', '10', '12', '14', '16', '18',
											'20', '22' ]
								},
								yAxis : {
									min : 0,
									title : {
										text : '降雨量(mm)'
									}
								},
								tooltip : {
									headerFormat : '<span style="font-size:10px">{point.key}时降水量：</span><table>',
									pointFormat : '<tr><td style="color:{series.color};padding:0"></td>'
											+ '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
									footerFormat : '</table>',
									shared : true,
									useHTML : true
								},
								plotOptions : {
									column : {
										pointPadding : 0.2,
										borderWidth : 0
									}
								},
								series : [ {
									name : '降雨量',
									data : [ 49.9, 71.5, 106.4, 129.2, 144.0,
											176.0, 135.6, 148.5, 216.4, 194.1,
											95.6, 54.4 ]

								} ]
							});

		});

		// 登录
		function clickLogin() {
			if ("" == szIP || "" == szPort) {
				return;
			}

			var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername,
					szPassword, {
						success : function(xmlDoc) {
							result = 1;
						},
						error : function() {
							result = 2;
						}
					});

			if (-1 == iRet) {
				result = 3;
			}
			return result;
		}
	</script>
	<script type="text/javascript">
		$(function() {
			$("#flip").mouseenter(function() {
				if ($("#panel1").is(":hidden")) {
					$("#panel1").show().animate({
						height : "275px"
					}, "fast");
				}
			}).mouseleave(function() {
				if (!$("#panel1").is(":hidden")) {
					$("#panel1").animate({
						height : "0px"
					}, "fast", function() {
						$("#panel1").hide();
					});
				}

			});
		});
	</script>

	<input id="code" type="hidden" value="${code}" />
	<div id="tabsaas" class="easyui-tabs"
		data-options="fit:true,tabPosition:'top'">
		<div title="枢纽信息"
			style="float: left;background-image: url(upload/stationimgs/${station.getImgurl()});position:relative;background-position:center center;background-size:100% 100%;"
			id="flip">
			<div id="panel1"
				style="height:0px;display:none;position:absolute; bottom:60px; left:0px; width:100%;background-color:#000;color:#fff;filter:alpha(opacity=50); /*支持 IE 浏览器*/
-moz-opacity:0.50; /*支持 FireFox 浏览器*/
opacity:0.50; /*支持 Chrome, Opera, Safari 等浏览器*/;">
				<p class="snms">${station.getRemark()}</p>
			</div>
			<div class="bottom_xx">

				<table cellspacing="0" cellpadding="0" data-options=""
					class="bottom_table" id="stations">
					<tr>
						<td>枢纽编号:${station.getCode()}</td>
						<td>枢纽名称:${station.getName()}</td>
						<td>机组数量:${station.getNvrurl()}</td>
						<td style=" background-image:none">闸门数量:${station.getNvrprot()}</td>

					</tr>


				</table>

			</div>
		</div>
		<div title="水情信息" data-options="fit:true">
			<div id="container" style="width: 580px;height: 330px"></div>
		</div>
		<!-- <div title="雨情信息" data-options="">
			<div id="container1" style="width: 580px;height: 330px"></div>
		</div> -->
		<div title="机组信息" data-options="">
			<table id="PumpRun_list1" cellspacing="0" cellpadding="0"></table>
		</div>
		<div title="节制闸信息" data-options="">
			<table id="GateRun_list1" cellspacing="0" cellpadding="0"></table>
		</div>
		<div title="监控信息" data-options="">
			<div id="divPlugin" style="width:100%;height: 100%"></div>
		</div>
	</div>
</body>
</html>
