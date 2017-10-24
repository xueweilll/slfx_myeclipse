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

<title>历史雨情</title>
<%@include file="../header.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	$(function() {
		var arrayName = [];
		var arrayData = [];
		var titlename = "";
		$.get("historyRainRegime/rainJSON.html", function(data) {
			$data = $(data);
			$data.each(function(index) {
				// titlename="外河最大水位："+maxints+"最小水位："+minints+"<br>内河最大水位："+maxouts+"最小水位："+minouts;
				if (typeof (this.st) == "undefined") {
							$.messager.alert("操作提示", "无历史数据!", "info");
				}
				else{
					arrayData.push({
						"name" : "降水量",
						"data" : this.st.dpr,
						"time" : this.st.month
					});
					arrayName = $(this.st.month);
					createChart();	
				}
		
			});
		}, "json");
		var lengthtemp = parseInt(arrayName.length / 31);
		createChart = function() {
			$('#container').highcharts(
					{
						chart : {
							renderTo : '#container',
							type : 'column'
						},
						title : {
							text : titlename
						},
						xAxis : {
							categories : arrayName,
							tickInterval : lengthtemp
						},
						/* scrollbar : {
							enabled : true
						}, */
						yAxis : {
							min : 0,
							title : {
								text : '水位(mm)'
							}
						},
						tooltip : {
							formatter : function() {

								return this.x + '<b>' + this.series.name
										+ '</b></br>' + ': ' + this.y + ' mm';

							}
						},
						series : arrayData
					});
		};
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				if (index == "0") {
					$("#tb").hide();
					$("#hr_list").hide();
				} else {
					$("#tb").show();
					$("#hr_list").show();
				}
				width: "auto";
				height: "auto";
				/* $("#selfdispatch").datagrid("unselectAll");
				$("#selfdispatch").datagrid("load", {
					typeDate : index
				}); */
			}
		});
		$("#hr_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'historyRainRegime/stationList.html',
			remoteSort : false,
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true
		});

		var p = $("#hr_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});

		var buttons = $.extend([], $.fn.datebox.defaults.buttons);
		buttons.splice(2, '', {
			text : '清除',
			handler : function(target) {
				$("#" + target.id).datebox('setValue', '')
				$(target).datebox('hidePanel');
			}
		});
		var buttons1 = $.extend([], $.fn.datebox.defaults.buttons);
		buttons1.splice(2, '', {
			text : '清除',
			handler : function(target) {
				$("#" + target.id).datebox('setValue', '')
				$(target).datebox('hidePanel');
			}
		});
		var buttons2 = $.extend([], $.fn.datebox.defaults.buttons);
		buttons2.splice(2, '', {
			text : '清除',
			handler : function(target) {
				$("#" + target.id).datebox('setValue', '')
				$(target).datebox('hidePanel');
			}
		});
		$("#starttime").datebox({
			editable : false,
			buttons : buttons,
			onChange : function() {
				$("#endtimes").datebox('enableValidation')
			}
		});
		$("#endtimes").datebox({
			editable : false,
			buttons : buttons
		});
		$("#sname").combobox({
			url : 'historyRainRegime/stationCombobox.html',
			valueField : 'code',
			textField : 'name'
		});
		$("#station").combobox({
			url : 'historyRainRegime/stationpicture.html',
			valueField : 'code',
			textField : 'name',
			editable:false,
			onLoadSuccess : function(node) {
				$('#station').combobox('setValues', node[0].code);
			}
		});
		var p = $('#months').datebox('panel'), //日期选择对象
		tds = false, //日期选择对象中月份
		span = p.find('span.calendar-text'); //显示月份层的触发控件
		$("#months").datebox(
						{
							editable : false,
							buttons : buttons2,
							onShowPanel : function() {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
								span.trigger('click'); //触发click事件弹出月份层
								if (!tds)
									setTimeout(
											function() {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
												tds = p.find('div.calendar-menu-month-inner td');
												tds.click(function(e) {
													  e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
															var year = /\d{4}/.exec(span.html())[0]//得到年份
															, month = parseInt(
																	$(this).attr('abbr'),10); //月份，这里不需要+1
															$('#months').datebox('hidePanel').datebox('setValue',year+ '-'+ month); //设置日期的值
														});
											}, 0)
							},
							parser : function(s) {
								if (!s)
									return new Date();
								var arr = s.split('-');
								return new Date(parseInt(arr[0], 10), parseInt(
										arr[1], 10) - 1, 1);
							},
							formatter : function(d) {
								return d.getFullYear()
										+ '-'
										+ (d.getMonth() < 9 ? "0"+ (d.getMonth() + 1) : d.getMonth() + 1);/*getMonth返回的是0开始的*/
							}
						});
	
	});
	function myDecoder(ch) {
		var val = ch != '' ? decodeURI(decodeURI(ch)) : '';
		//var path = "test.jsp?title="+title;
		return val;
	}
	function myparser(s) {
		if (!s)
			return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
	function selectpicture() {
		var month = $('#months').datebox('getValue');
		var stationname = $('#station').combobox('getValue');
		var arrayName = [];
		var arrayData = [];
		var titlename = "";
		if(month==""){
			$.messager.alert("操作提示","请选择一个日期","info");
			return false;
		}
		$.get("historyRainRegime/rainJSON.html?station="
				+ myEncoder(stationname) + "&month=" + myEncoder(month),
				function(data) {
					$data = $(data);
					$data.each(function(index) {
						// titlename="外河最大水位："+maxints+"最小水位："+minints+"<br>内河最大水位："+maxouts+"最小水位："+minouts;
						if (typeof (this.st) == "undefined") {
							$.messager.alert("操作提示", "无历史数据!", "info");
						} else {
							arrayData.push({
								"name" : "降水量",
								"data" : this.st.dpr,
								"time" : this.st.month
							});
							arrayName = $(this.st.month);
							createChart();
						}

					});
				}, "json");
		var lengthtemp = parseInt(arrayName.length / 31);
		createChart = function() {
			$('#container').highcharts(
					{
						chart : {
							renderTo : '#container',
							type : 'column'
						},
						title : {
							text : titlename
						},
						xAxis : {
							categories : arrayName,
							tickInterval : lengthtemp
						},
						/* scrollbar : {
							enabled : true
						}, */
						yAxis : {
							min : 0,
							title : {
								text : '水位(mm)'
							}
						},
						tooltip : {
							formatter : function() {
								return this.x + '<b>' + this.series.name
										+ '</b></br>' + ': ' + this.y + ' mm';

							}
						},
						series : arrayData
					});
		};
	}
	function selectbig() {
		$("#hr_list").datagrid('load', {
			sname : $("#sname").combobox("getValue"),
			starttime : $("#starttime").combobox("getValue"),
			endtime : $("#endtimes").combobox("getValue")
		});
	}
</script>
</head>
<style>
.panel-body-noheader {
	border: none !important
}
</style>
<body class="easyui-layout" id="cc">
	<div id="tt" class="easyui-tabs" data-options="region:'center'">
		<div title="数据图像"
			style="width:'100%';height:'100%';padding:0px;margin:0px;border:none">
			<div id="tbs" class="easyui-panel" style="border:none">
				<div style="display:none;">
				</div>
				<div class="cz_div">
					枢纽名称:<input id="station" class="easyui-combobox" name="station"
						data-options="width:'130'" /> 月<input id="months"
						class="easyui-datebox" name="sname" data-options="width:'160'" />
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="selectpicture()" iconCls="icon-search">查询</a>
				</div>
			</div>
			<div id="container" class="easyui-panel"
				style="height:90%;width:100%; overflow-x: hidden; overflow-y:hidden; ">
			</div>
		</div>
		<div title="数据列表">
			<table id="hr_list" cellspacing="0" cellpadding="0"
				data-options="toolbar:'#tb'">
				<thead>
					<tr>
						<!-- <th data-options="field:'stcd',width:100">枢纽编号</th> -->
						<th data-options="field:'stcdname',width:150">枢纽名称</th>
						<th data-options="field:'tm',width:150">时间</th>
						<th data-options="field:'dyp',width:130">日降雨量</th>
						<th data-options="field:'hisrain',width:130">历史累计雨量</th>
					</tr>
				</thead>
			</table>
			<div id="tb">
				<div class="cz_div">
					枢纽名称:<input id="sname" class="easyui-combobox" name="sname"
						data-options="width:'130'" /> 时间区间:<input id="starttime"
						type="text" class="easyui-datebox" data-options="width:160">
					~<input id="endtimes" type="text" class="easyui-datebox"
						data-options="width:160,validType:'compareDate[starttime]'">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="selectbig()" iconCls="icon-search">查询</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
