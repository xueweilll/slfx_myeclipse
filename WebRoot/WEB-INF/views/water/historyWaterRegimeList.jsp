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
 <%@include file="../header.jsp"%> 
<title>My JSP 'user.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <script type="text/javascript" src="js/exporting.js"></script> 
<script type="text/javascript"  src="js/highstock.js"></script> -->

<script type="text/javascript">
	$(function() {
		var arrayName=[];
		var arrayData=[];
		var arrayS=[];
		var name=[];
		var ssname=[];
		var snames=[];
		var maxints;
		var minints;
		var maxouts ;
		var minouts;
		var titlename="";
			$.get("historyWaterRegime/waterJSON.html",function(data){
			$data=$(data);
			$data.each(function(index){
				 maxints = Math.max.apply(null,$(this.st.ints));
				 minints = Math.min.apply(null,$(this.st.ints));
				 maxouts = Math.max.apply(null,$(this.st.outs));
				 minouts = Math.min.apply(null,$(this.st.outs));
				 titlename="外河最高水位："+maxouts+"米  "+"最低水位："+minouts+"米  "+"<br>内河最高水位："+maxints+"米  "+"最低水位："+minints+"米";
				arrayData.push({"name":"外河水位","data":this.st.outs,
					"time":this.st.month});
			    arrayData.push({"name":"内河水位","data":this.st.ints,
			    	"time":this.st.month});
			    arrayName=$(this.st.month);
			  createChart();
			});
			},"json");
			//arrayS=$(data.sname);
			var lengthtemp=	parseInt(arrayName.length/9);
			createChart=function(){
			$('#container').highcharts({
			chart: {
		        	renderTo:'#container',
		            type: 'spline'
		        }, 
		        title: {
		            text: titlename
		        },		    
		        xAxis: {
                 	categories:arrayName,
                 	tickInterval:lengthtemp
		        },
		        scrollbar: {
		            enabled: true
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: '水位(米)'
		            }
		        },
		        tooltip: {
		            formatter:function(){
		            	
		            	return this.x+'<b>'+this.series.name+'</b></br>'+
		            	': '+
		            	this.y+' m';
		            	
		            }
		        },
		        series:arrayData		
		    });
			};					
		$('#tt').tabs({
			tabWidth : '160',
			border : false,
			onSelect : function(title, index) {
				if (index == "1") {					
					$("#td").hide();
					$("#inStorage_list").hide();
				} else {
					
					$("#td").show();
					$("#inStorage_list").show();
				}
				width:"auto";
				height:"auto";
				$("#selfdispatch").datagrid("unselectAll");
				$("#selfdispatch").datagrid("load", {
					typeDate : index
				});
			}
		});
		$("#inStorage_list").datagrid({
			width : 'auto',
			height : 'auto',
			pageSize : 20,
			nowrap : false,
			striped : true,
			border : true,
			collapsible : false,
			fit : true,
			url : 'historyWaterRegime/historyWaterRegimeList.html',
			idField : 'id',
			singleSelect : true,
			pagination : true,
			rownumbers : true,
			toolbar : '#tb',
			queryParams : {
				typeDate : "0"
			}
		});
		var p = $("#inStorage_list").datagrid('getPager');
		$(p).pagination({
			pageList : [ 5, 10, 15, 20 ],
			beforePageText : '第',
			afterPageText : '页        共{pages}页',
			displayMsg : '当前显示{from}-{to}条记录      共{total}条记录'
		});
		var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
		buttons.splice(2, '', {
			text : '清除',
			handler : function(target) {
				$("#" + target.id).datetimebox('setValue', '')
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
		$("#starttime").datetimebox({
			editable : false,
			buttons : buttons,
			onChange : function() {
				$("#endtimes").datetimebox('enableValidation')
			}
		});
		$("#endtimes").datetimebox({
			editable : false,
			buttons : buttons
		});
		$("#station").combobox({
			url:'historyWaterRegime/station.html',
			valueField : 'id',
			textField : 'name',
			editable:false,
			onLoadSuccess:function(node){
				//console.info(node[0].id)
				$('#station').combobox('setValues', node[0].id);
			}
			
		});
		$("#sname").combobox({
			url:'historyWaterRegime/station.html',
			valueField : 'id',
			textField : 'name'
		});
		  
         $("#years").datebox({
        	 panelHeight : 80,
        		editable : false,
    			buttons : buttons2,
    		            onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
    		                span1.trigger('click'); //触发click事件弹出月份层
    		                if (!tdss) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
    		                    tdss = p.find('div.calendar-menu-month-inner td');
    		                    tdss.click(function (e) {
    		                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
    		                        var year = /\d{4}/.exec(span1.html())[0]//得到年份
    		                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
    		                        $('#years').datebox('hidePanel')//隐藏日期对象
    		                        .datebox('setValue', year ); //设置日期的值
    		                    });
    		                }, 0)
    		            },
    		            parser: function (s) {
    		                if (!s) return new Date();
    		                var arr = s.split('-');
    		                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
    		            },
    		            formatter: function (d) { 
    	            	
    		            	return d.getFullYear();/*getMonth返回的是0开始的，忘记了。。已修正*/ },
    		            onChange:function(value){
    						$("#months").datebox('setValue',''),
    						$("#days").datebox('setValue','')
    		            }
         });
		$("#months").datebox({
			editable : false,
			buttons : buttons2,
		            onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
		                span.trigger('click'); //触发click事件弹出月份层
		                if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
		                    tds = p.find('div.calendar-menu-month-inner td');
		                    tds.click(function (e) {
		                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
		                        var year = /\d{4}/.exec(span.html())[0]//得到年份
		                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
		                        $('#months').datebox('hidePanel')//隐藏日期对象
		                        .datebox('setValue', year + '-' + month); //设置日期的值
		                    });
		                }, 0)
		            },
		            parser: function (s) {
		                if (!s) return new Date();
		                var arr = s.split('-');
		                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		            },
		            formatter: function (d) { 	            	
		            	return d.getFullYear() + '-' + (d.getMonth()<9?"0"+(d.getMonth()+1):d.getMonth()+1);/*getMonth返回的是0开始的*/ },
		            onChange:function(value){
						$("#years").datebox('setValue',''),
						$("#days").datebox('setValue','')
		            }
		});
		var y=$('#years').datebox('panel'),
		tdss=false,
		span1=y.find('span.calendar-text');
		        var p = $('#months').datebox('panel'), //日期选择对象
		            tds = false, //日期选择对象中月份
		            span = p.find('span.calendar-text'); //显示月份层的触发控件	            
         $("#days").datebox({
        	 editable : false,
 			buttons : buttons1,
             onChange:function(value){
					$("#years").datebox('setValue',''),
					$("#months").datebox('setValue','')
	            }
         });
	});
	function myformatter(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m);
    }
	function myEncoder(ch) {

		var val = ch != '' ? encodeURI(encodeURI(ch)) : '';

		//var path = "test.jsp?title="+title;
		return val;
	}

	function myDecoder(ch) {

		var val = ch != '' ? decodeURI(decodeURI(ch)) : '';

		//var path = "test.jsp?title="+title;
		return val;
	}
function myparser(s){
        if (!s) return new Date();
        var ss = (s.split('-'));
        var y = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var d = parseInt(ss[2],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
            return new Date(y,m-1,d);
        } else {
            return new Date();
        }
    }
	function selectbig() {
		$("#inStorage_list").datagrid('load', {
			sname : $("#sname").combobox("getText"),
			starttime : $("#starttime").combobox("getValue"),
			endtime : $("#endtimes").combobox("getValue")
		});
	}
	
	function selectpicture(){
		var year=$('#years').combobox('getValue');
		var month=$('#months').combobox('getValue');
		var day=$('#days').combobox('getValue');
		var stationname=$('#station').textbox('getValue');
		var arrayName=[];
		var arrayData=[];
		var maxints;
		var minints;
		var maxouts ;
		var minouts;
		var titlename="";
			$.get('historyWaterRegime/waterJSON.html?i=1'+'&year='+myEncoder(year)+'&month='+myEncoder(month)+'&day='+myEncoder(day)+
					'&station='+myEncoder(stationname),
					function(data){
				$data=$(data);
				$data.each(function(index){
					if(typeof(this.st)=="undefined"){
						$.messager.alert("操作提示", "无历史数据!", "info");
					}else{
						 maxints = Math.max.apply(null,$(this.st.ints));
						 minints = Math.min.apply(null,$(this.st.ints));
						 maxouts = Math.max.apply(null,$(this.st.outs));
						 minouts = Math.min.apply(null,$(this.st.outs));
						 titlename="外河最高水位："+maxouts+"米  "+"最低水位："+minouts+"米  "+"<br>内河最高水位："+maxints+"米  "+"最低水位："+minints+"米";
						 //titlename="外河最大水位："+maxints+"最小水位："+minints+"<br>内河最大水位："+maxouts+"最小水位："+minouts;
					arrayData.push({"name":"外河水位","data":this.st.outs});
				    arrayData.push({"name":"内河水位","data":this.st.ints});
				    arrayName=$(this.st.month);
				}
				});
			var lengthtemp=	parseInt(arrayName.length/9);
			//console.info(lengthtemp);
			$('#container').highcharts({
			chart: {
		        	renderTo:'#container',
		            type: 'spline'
		        }, 
		        title: {
		            text: titlename
		        },		    
		        xAxis: {
                 	categories:arrayName,
                   	tickInterval:lengthtemp
		        },
		        scrollbar: {
		            enabled: false
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: '水位(米)'
		            }
		        },
		        tooltip: {
		            formatter:function(){
		            	return this.x+'<b>'+this.series.name+'</b></br>'+		            	
		            	': '+
		            	this.y+' m';
		            }
		        },
		        series:arrayData
		    });
		},"json");
	
	}

	function distory() {
		var row = $("#inStorage_list").datagrid("getSelected");
		if (row == null) {
			$.messager.alert("操作提示", "请选择一条记录再进行操作！", "error");
			return false;
		}
		if (row.stateValue != "0") {
			$.messager.alert("操作提示", "表单已提交，不能删除！", "error");
			return false;
		}
		$.messager.confirm("删除提示", "您确定要执行删除吗？", function(data) {

			if (data) {
				$.messager.progress();
				$.ajax({
					type : 'POST',
					url : "inStorage/delete.html",
					data : {
						id : row.id
					},
					success : function() {
						$.messager.progress('close');
						$("#inStorage_list").datagrid("unselectAll");
						$("#inStorage_list").datagrid("reload");
					}
				});
			}
		});
	}
	function print() {
		var row = $("#month").combobox("getValue");
		var year = $("#year").combobox("getText");
		if (row == '') {
			$.messager.alert("操作提示", "请选择一个月份再进行操作！", "error");
			return false;
		}
		if (year == '') {
			$.messager.alert("操作提示", "请选择一个年份在进行操作！", "error");
			return false;
		}
		var link = "monthlyReport/print.html?month=" + row + "&year="
				+ myEncoder(year);
		window.location.href = link;
		return false;
	}
	var buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
	buttons.splice(0, 1, {
		text : '清空',
		handler : function(target) {
			$(target).datetimebox("clear");
			$(target).datetimebox("hidePanel");
		}
	});
	//历史水情数据导出
	function exportHistoryData(){
		$.messager.progress();
		//获取枢纽
		var stationid = $("#sname").combobox("getValue");
		if(stationid==""){
			$.messager.alert('消息提醒','枢纽不能为空！','info');
			$.messager.progress('close');
			return false;
		}
		//获取开始时间
		var startTime= $("#starttime").combobox("getValue");
		if(startTime==""){
			$.messager.alert('消息提醒','开始时间不能为空！','info');
			$.messager.progress('close');
			return false;
		}
		//获取结束时间
		var  endTime=$("#endtimes").combobox("getValue");
		if(!$("#endtimes").combobox("isValid")||endTime==""){
			$.messager.alert('消息提醒','结束时间不能大于开始时间且不能为空！','info');
			$.messager.progress('close');
			return false;
		}
		$.post("historyWaterRegime/export.html",{stationId:stationid,startTime:startTime,endTime:endTime},function(json){
			data = eval('(' + json + ')');
			$.messager.progress('close');
			if(!data.result){
				$.messager.alert("操作提示",data.msg,"error");
			}else{
				var path="upload/"+data.msg;
				$("#dowload").attr("action",path);
				$("#dowload").submit();
			}			
		});
	}
</script>

</head>
<style>
.panel-body-noheader{border:none!important}

</style>
<body class="easyui-layout" id="cc">
	<div id="tt" class="easyui-tabs" data-options="region:'center'" >
		<div title="数据图像" style="width:'100%';height:'100%';padding:0px;margin:0px;border:none">
        <div id ="tbs" class="easyui-panel" style="border:none">
		<div style="display:none;">
		<input id="years" class="easyui-datebox" name="sname"
						data-options="width:'100'" />年</div>
		<div  class="cz_div">				
						枢纽名称:<input id="station" class="easyui-combobox"name="station"
						data-options="width:'130'"/>
						月<input id="months" class="easyui-datebox" name="sname"
						data-options="width:'160'"/>
						日 <input id="days" class="easyui-datebox" name="sname"
						data-options="width:'160'" />
						<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="selectpicture()" iconCls="icon-search">查询</a>
		</div>
		</div> 
		<div id="container" class="easyui-panel"  style="height:90%;width:100%; overflow-x: hidden; overflow-y:hidden; ">			
		</div>
		</div>
		<div title="数据列表"  >
			<form id="dowload" method="post" action="" style="display: none;"></form>
			<table id="inStorage_list" cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th data-options="field:'sname',width:120">枢纽名称</th>
						<!-- <th data-options="field:'code',width:110">枢纽编号</th> -->
						<th data-options="field:'tm',width:160">时间</th>
						<th data-options="field:'ppupz',width:100">外河水位</th>
						<th data-options="field:'ppdwz',width:80">内河水位</th>
						<th data-options="field:'omcn',width:50">开机台数</th>
						<th data-options="field:'ompwr',width:50">开机功率</th>
						<th data-options="field:'pmpq',width:120">抽水流量</th>
						<th data-options="field:'ppupwptn',width:120">外河水势</th>
						<th data-options="field:'ppdwwptn',width:130">内河水势</th>
						<th data-options="field:'pdchcd',width:130">引排特征码</th>
					</tr>
				</thead>
			</table>
			<div id="tb" >
				<div class="cz_div">
					枢纽名称:<input id="sname" class="easyui-combobox" name="sname"
						data-options="width:'130'" /> 时间区间:<input id="starttime"
						type="text" class="easyui-datetimebox" data-options="width:160">
					~<input id="endtimes" type="text" class="easyui-datetimebox"
						data-options="width:160,validType:'compareDate[starttime]'">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="selectbig()" iconCls="icon-search">查询</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="exportHistoryData()" iconCls="icon_export">导出</a>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
