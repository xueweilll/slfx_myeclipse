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
<script type="text/javascript">
</script>
<style>
#map {
	padding: 0;
	margin: 0;
	height: 100%;
}

.panel-body {
	border: none;
}
.lcbt{ height；30px;!important}
</style>
<script type="text/javascript">
function addTab(title,url,icon) {
	window.parent.addTab(title, url, icon);
}
</script>
</head>

<body class="easyui-layout"
	style="padding:0px;margin:0px;border:0px!important;overflow:scroll">
	<table style="width:100%;height:100%;text-align:center">
		<tr>
					<td >&nbsp;</td>
		</tr>
		<tr>
		 <td style="height:652px"><img src="images/wzlct.png" width="610" height="532" usemap="#Map" border="0" /></td>
		</tr>
		<tr>
			<td >&nbsp;</td>
		</tr>
	</table>

	<map name="Map" id="Map">
	
  <area shape="rect" class="lctbk" coords="251,2,372,78" onclick="addTab('物资归还','checkInStorage.html','icon icon-icon44')" />
  <area shape="rect" class="lctbk" coords="57,77,179,155" onclick="addTab('物资入库','inStorage.html','icon icon-icon44')"  />
  <area shape="rect" class="lctbk" coords="12,226,135,303" onclick="addTab('物资出库','outStorage.html','icon icon-icon44')" />
  <area shape="rect" class="lctbk" coords="57,385,178,462" onclick="addTab('物资外借','checkOutStorage.html','icon icon-icon44')" />
  <area shape="rect" class="lctbk" coords="244,447,364,525" onclick="addTab('物资报废','scrapStorage.html','icon icon-icon44')" />
  <area shape="rect" class="lctbk" coords="435,78,556,156"  onclick="addTab('物资盘点','checkStorage.html','icon icon-icon44')"  />
  <area shape="rect" class="lctbk" coords="474,226,595,303"  onclick="addTab('物资损益','profitAndLossStorage.html','icon icon-icon44')"/>
  <area shape="rect" class="lctbk" coords="436,384,558,462" onclick="addTab('物资调拨','allotStorage.html','icon icon-icon44')" />
	
	
		<!--<area shape="rect" class="lctbk" coords="311,125,427,163" onclick="addTab('物资出库','outStorage.html','icon icon-icon44')" />
		<area shape="rect" class="lctbk" coords="312,183,428,220" />
		<area shape="rect" class="lctbk" coords="311,271,426,307" />
		<area shape="rect" class="lctbk" coords="311,340,426,377" onclick="addTab('物资损益','profitAndLossStorage.html','icon icon-icon44')" />
		<area shape="rect" class="lctbk" coords="312,396,426,432"  />
		<area shape="rect" class="lctbk" coords="632,144,752,222" onclick="addTab('月报表','monthlyReport.html','icon icon-icon44')" />
		<area shape="rect" class="lctbk" coords="631,235,752,311" onclick="addTab('物资库存','storageTable.html','icon icon-icon44')" />
		<area shape="rect" class="lctbk" coords="632,324,752,400" onclick="addTab('物资出库表','outStockTable.html','icon icon-icon44')" />
		<area shape="rect" class="lctbk" coords="632,412,752,490"  />
		<area shape="rect" class="lctbk" coords="631,499,752,577" onclick="addTab('出入库统计','statistics.html','icon icon-icon44')" />
		<area shape="rect" class="lctbk" coords="311,446,427,483"  />
		<area shape="rect" class="lctbk" coords="311,499,427,537"  />
		<area shape="rect" class="lctbk" coords="311,551,427,589"  />
		<area shape="rect" class="lctbk" coords="311,64,427,101" onclick="addTab('物资入库','inStorage.html','icon icon-icon44')" />-->
	</map>
</body>
</html>
