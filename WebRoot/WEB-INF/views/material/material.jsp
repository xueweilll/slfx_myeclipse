<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@include file="../header.jsp"%>
    <title>出入库统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/highcharts.js"></script>
<script src="js/exporting.js"></script>
<script type="text/javascript">
$(function () {
	//var arrayName="[";
	var arrayName=[];
	var arrayData=[];
	$.get("statistics/monthJSON.html",function(data){
		arrayName=$(data.month);
		arrayData.push({"name":"出库","data":data.outs});
		arrayData.push({"name":"入库","data":data.ints});
		$('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '出入库柱状图'
	        },
	        subtitle: {
	            text: '从本月向上读取一年'
	        },
	        xAxis: {
	            categories:arrayName
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '操作次数 (次)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y} 次</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series:arrayData
	    });
	},"json");
});
</script>
<style>
#map {
	padding: 0;
	margin: 0;
	height: 100%;
	
}

 .panel-body{border:none;}
</style>
  </head>
  
  <body class="easyui-layout" style="padding:0px;margin:0px;border:0px!important;">
		<div id="container" data-options="region:'center'"></div>
  </body>
</html>
