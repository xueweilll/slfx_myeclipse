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

<title>My JSP 'systemInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/default.css">
</head>
<script type="text/javascript">
	function addTab(title, url, icon) {
		window.parent.addTab(title, url, icon);
	}
</script>
<style>
body, html {
	font-size: 14px;
	font-family: 微软雅黑 !important
}

p {
	border-bottom: 1px solid #45a9b8;
	line-height: 30px;
	margin: 0px;
	padding: 0px;
	padding-left: 20px;
	padding-right: 20px;
}

.p_bt {
	font-size: 18px;
	color: #0369BE;
	font-weight: bold;
	line-height: 50px;
	height: 50px
}

.sn {
	color: #0369BE;
	font-weight: bold
}

body {
	background: url(images/dispatch/bg.png) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: 100% 100%;
	
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/dispatch/bg.png',
		sizingMethod='scale');
	-ms-filter:
		"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='images/dispatch/bg.png', sizingMethod='scale')";
}

.panel-body {
	border: none;
}
</style>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="padding-top: 25px; padding-left: 25px; padding-right: 25px;">
		<p style="color: #0369BE; font-weight: bold; font-size: 14px;">常州市城市防洪工程管理处负责城市防洪工程的管理和运行，管辖17个水利工程，泵站16座，装机总流量375
			m³/s；节制闸16座，闸宽16米的1座，闸宽12米的4座，闸宽10米的3座，闸宽8米的3座，闸宽6米的5座；船闸1座。</p>
		<p class="p_bt">运北片城市防洪大包围</p>
		<p style="color: #0369BE; font-weight: bold">常州市运北片东至丁塘港、南至新大运河、西至凤凰河、北至沪宁高速公路，区内面积156.2km
			²，该区域是常州市经济、文化核心区和人口密集区，涉及的水利工程有：</p>
		<p>
			<span class="sn">▐ 横塘河北枢纽：</span>双向泵站及节制闸各一座，一列式布置。泵站设计流量为40m³/s，采用4台2200ZGBS10型双向竖井式贯流泵，配套450kW的YKS500-8型异步电动机。节制闸规模为2x10m，闸门为升卧式平面钢闸门。
		</p>
		<p>
			<span class="sn"> ▐ 北塘河枢纽：</span>泵站和节制闸各1座组成，泵站设计流量30m³/s，采用3台1820ZGB10-1.5型竖井贯流泵，配套YKS500-8型异步电动机。节制闸规模为1x12m，闸门为直升式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 北塘河船闸：</span>船闸闸首宽8m，闸室宽12m、闸室长60m，门型为门体水下可移动式水平铰轴的下卧式平板钢闸门。
		</p>
		<p>
			<span class="sn">▐ 永汇河枢纽：</span>泵站和节制闸各1座组成，泵站设计流量为10
			m³/s，采用4台900ZLB2.7-3型立式轴流泵（配套JS148-12型155kW异步电动机）。节制闸规模为1x8m，闸门为升卧式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 老澡港河枢纽：</span>泵站和节制闸各1座组成，泵站设计流量为10m³/s，采用4台900ZLB2.7-3型轴流泵（配套JS148-12型155kW异步电动机）。节制闸规模为1x8m，闸门为升卧式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 澡港河南枢纽：</span>泵站和节制闸各1座组成，泵站设计流量50m³/s，采用5台2180ZGB10-1.3型双向竖井式贯流泵，节制闸规模为2x10m，闸门为升卧式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 大运河西枢纽：</span>泵站和节制闸各1座组成。泵站设计流量10m³/s，4台双向潜水轴流泵，节制闸规模为2x12m。
		</p>
		<p>
			<span class="sn">▐ 西界河闸站：</span>泵站和节制闸各1座组成，泵站设计流量3m³/s，
			1台套900ZLDB-125X型轴流泵，节制闸规模为1x6m，闸门为直升式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 童子河闸站：</span>泵站和节制闸各1座组成，泵站设计流量6m³/s，2台套900ZLDB-125X型轴流泵，节制闸规模为1x8m，闸门为直升式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 南运河枢纽：</span>泵站和节制闸各1座组成，泵站设计流量30m³/s，采用3台1820ZGB10-1.5型竖井贯流泵，节制闸规模为1x12m，闸门为升卧式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 串新河枢纽：</span>泵站和节制闸各1座组成，泵站设计流量20m³/s，采用2台1820ZGB10-1.5型竖井贯流泵，节制闸规模为1x8m，闸门为升卧式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 采菱港枢纽纽：</span>泵站和节制闸各1座组成，泵站设计流量20m³/s，采用2台1820ZGB10-1.5型竖井贯流泵，节制闸规模为1x12m，闸门为升卧式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 大运河东枢纽：</span>泵站和节制闸各1座组成，泵站设计流量100
			m³/s，采用4台3000ZGB25-0.9型竖井贯流泵，节制闸规模为2x16m，闸门为下卧式平面钢闸门。
		</p>
		<p class="p_bt">横塘片低洼区域</p>
		<p style="color: #0369BE; font-weight: bold">横塘片洼地大部分属于天宁区，局部分别隶属于武进区和新北区。该防洪片南面以京杭大运河为界，西面以北塘河为界，东至丁塘港，面积约为33.35km²，多为低洼地区，防洪标准为100年一遇。</p>
		<p>
			<span class="sn">▐ 横塘河北枢纽：</span>既是运北片城市防洪大包围节点工程，也是横塘片区防洪控制工程。
		</p>
		<p>
			<span class="sn">▐ 横塘河南枢纽：</span>泵站和节制闸各1座组成，泵站设计流量10m³/s，3台套900ZLB-125型立式轴流泵，配套电动机功率155KW（JSL-14-12），通过双层流道及四扇控制闸门组成“X”型流道，实现双向抽排功能；节制闸规模为1x10m，闸门为升卧式平板钢闸门。
		</p>
		<p>
			<span class="sn">▐ 横峰沟枢纽：</span>泵站和节制闸各1座组成，泵站设计流量6m³/s，
			2台套900ZLB-125型轴流泵，节制闸规模为1x6m，闸门为直升式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 丁横河枢纽：</span>泵站和节制闸各1座组成，泵站设计流量为15m³/s，5台900ZLB-125型轴流泵（配套JS148-12型155kW异步电动机），节制闸规模为1x6m，闸门为直升式平面钢闸门。
		</p>
		<p>
			<span class="sn">▐ 糜家塘枢纽：</span>泵站和节制闸各1座组成，泵站设计流量为15m³/s，采用5台900ZLB-125型轴流泵（配套JS14-12型155kW异步电动机）；节制闸规模为1x6m，闸门为直升式平面钢闸门。
		</p>
	</div>
</body>
</html>