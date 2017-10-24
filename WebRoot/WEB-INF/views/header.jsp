<link rel="shortcut icon" href="images/logo.ico" />
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css"
	href="js/easyui/themes/mythem/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript" src="js/highstock.js"></script>
<!-- <script type="text/javascript" src="js/highcharts.js"></script>  -->
<script type="text/javascript" src="js/exporting.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script src="js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/ajaxoverwrite.js"></script>
<script type="text/javascript" src="js/dialog.js"></script>
<script type='text/javascript' src='dwr/engine.js'></script>  
<script type='text/javascript' src='dwr/util.js'></script>  
<script type="text/javascript" src="dwr/interface/directController.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
		<link rel="stylesheet" type="text/css" href="css/additional.css">
		<script type="text/javascript" src="js/video/webVideoCtrl.js"></script>
<link rel="stylesheet"
	href="gis/Map/js/arcgis/js/dojo/dijit/themes/claro/claro.css">
<link rel="stylesheet" href="gis/Map/js/arcgis/js/esri/css/esri.css">

<script type="text/javascript" src="gis/Map/js/arcgis/init.js"></script>
<script type="text/javascript" src="js/datagrid-detailview.js"></script>
<script type="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.1.0.js"></script>
<script type="text/javascript">
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
	
	function fixWidth(percent){
		//alert(document.body.clientWidth);
		return (document.body.clientWidth - 25) * percent ;
	}
</script>