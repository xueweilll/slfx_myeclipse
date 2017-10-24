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
<title>首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="gis/Map/js/arcgis/js/dojo/dijit/themes/claro/claro.css">
<link rel="stylesheet" href="gis/Map/js/arcgis/js/esri/css/esri.css">
<script type="text/javascript" src="js/jquery.timers.js"></script>

<script src="gis/Map/js/arcgis/init.js"></script>
<script>
$(function(){
	/* $("#WarnLog_list").datagrid({
		width : 'auto',
		height : 'auto',
		nowrap : false,
		striped : true,
		border : false,
		collapsible : false,
		fit : true,
		idField : 'id',
		singleSelect : true,
		rownumbers : true,
		columns:[[    
		          {field:'name',title:'枢纽名称',width:100},  
		          {field:'b',title:'报警信息',width:'300'},  
		          {field:'c',title:'报警时间',width:160},  
		          {field:'d',title:'解除时间',width:160},  
		          {field:'e',title:'报警状态',width:60}
		]],
		toolbar:'#tb'
	});
	$('#map').everyTime('20s','B',function(){
		$.post("floodctl/warnCurrentList.html",function(data){
			console.info(data.length);
			if(data.length===0){
				var rows=$('#WarnLog_list').datagrid("getRows");
				if(rows.length==0){
					$('#map').layout("collapse","south");
				}
			}else{
				$data=$(data);
				$data.each(function(){
					var row=$('#WarnLog_list').datagrid("getRowIndex",this.id);
					if(row == -1){
						$('#WarnLog_list').datagrid('insertRow',{index:0,row:this});
					}
				});
				if($("#WarnLog").css("display")=="none"){
					$('#map').layout("expand","south");
				}
			}
		},"json")
	},0,true); */
	$('#map').layout("collapse","south");
});

function setCenter(x,y){
	var location = new esri.geometry.Point(x, y, map.spatialReference);
	map.centerAt(location);
}

/* function distory(){
	var row=$('#WarnLog_list').datagrid("getSelected");
	if(row==null){
		$.messager.alert('消息提醒','请先选择一条数据','info');
		return false;
	}
	$.post("floodctl/delete.html",{code:row.code},function(){
		var index=$('#WarnLog_list').datagrid("getRowIndex",row);
		$('#WarnLog_list').datagrid("deleteRow",index);
		var rows=$('#WarnLog_list').datagrid("getRows");
		if(rows.length==0){
			$('#map').layout("collapse","south");
		}
	});
} */
	dojo.require("esri.map");
	dojo.require("esri.layers.graphics");
	var map;
	var sr;
	function init(){
		sr = new esri.SpatialReference({
			//wkid : 2437
			wkid:4490
		});
		map = new esri.Map("map",
		{
					center : new esri.geometry.Point(119.96923837004582,
							31.812328864234377, sr),
							 //center: new esri.geometry.Point(496741.870339,3515105.862128,sr),  
					zoom : 8,
					maxZoom : 15,
					logo : false
		}); 
				//map = new esri.Map("map");

		//var mapurl="http://services.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer";
		//var tiledLayer = new esri.layers.ArcGISTiledMapServiceLayer(mapurl); 
		//var mapurl="http://218.93.114.86:8080/arcgis/rest/services/SLBJMap/MapServer";
		//var mapurl = "http://www.czmap.com.cn:8399/arcgis/rest/services/CZSjtlyMap/MapServer";
		var mapurl="http://218.94.6.92:6080/arcgis/rest/services/jssl_vector_L3_L17/MapServer";
		//var mapurl="http://58.216.140.181:8080/gxserver/MapService/czmapserver_sl_ags";
		var tiledLayer = new esri.layers.ArcGISTiledMapServiceLayer(mapurl);
		//var tiledLayer = new esri.layers.ArcGISDynamicMapServiceLayer(mapurl); 
		map.addLayer(tiledLayer);
		//setCenter('13353471.212969888','3736440.4781922484');
		dojo.connect(map, "onLoad", initGraphics);
		
		
	}

	function initGraphics() {
	 	var isClick= false;
		dojo.connect(map.graphics, "onClick", function(evt){						
			var s= evt.graphic.attributes;
			//alert(s.code);
			window.parent.setstationcode(s.code);
			isClick= true;
			showDialogWHIntval("枢纽信息", "currentMonitorsController/current.html?code="+s.code, 600, 400);
		});
		dojo.connect(map, "onClick", function(evt) {
			if(!isClick){
				var s = undefined;
				window.parent.setstationcode(s);
			}
			isClick= false;
			//prompt('', evt.mapPoint.x + ';' + evt.mapPoint.y);
			//map.infoWindow.setContent('x='+evt.mapPoint.x+'<br>y='+evt.mapPoint.y); 
			//map.infoWindow.show(evt.mapPoint,map.getInfoWindowAnchor(evt.screenPoint));
			
		});

		//把图层添加到地图上
		// map.addLayer(graphicLayer); 
		//
		$.post("map/stationBind.html",function(data){
			$.each(data,function(i,item){
				createMarker(item.code,item.Name,item.Lat,item.Lon,"images/suniu_new.gif",21,21);				
			});
			createPolygon();
		},'json');		
		//setCenter(13353471.2129888,3736440.4781922484);
	}
	
	function createPolygon(){
		var json =[];
		json.push(new esri.geometry.Point(120.0225262375998,31.81280989022426, map.spatialReference));
		json.push(new esri.geometry.Point(120.02631250498163,31.807657019094805, map.spatialReference));
		json.push(new esri.geometry.Point(120.02489629859537,31.80561854020546, map.spatialReference));
		json.push(new esri.geometry.Point(120.02455297583505,31.803665892006194, map.spatialReference));
		json.push(new esri.geometry.Point(120.02532545204575,31.801799074497005, map.spatialReference));
		json.push(new esri.geometry.Point(120.03053966646796,31.79645611403967, map.spatialReference));
		json.push(new esri.geometry.Point(120.03217044957944,31.794589296530482, map.spatialReference));
		json.push(new esri.geometry.Point(120.03092590457331,31.787164941838768, map.spatialReference));
		json.push(new esri.geometry.Point(120.02989593629238,31.782701745954732, map.spatialReference));
		json.push(new esri.geometry.Point(120.0286943066313,31.778646245848563, map.spatialReference));
		json.push(new esri.geometry.Point(120.0286299336137,31.77478386479508, map.spatialReference));
		json.push(new esri.geometry.Point(120.03043237810535,31.771629586934726, map.spatialReference));
		json.push(new esri.geometry.Point(120.02755704998773,31.77004171916829, map.spatialReference));
		json.push(new esri.geometry.Point(120.02693477748467,31.762789025856733, map.spatialReference));
		json.push(new esri.geometry.Point(120.02723518489995,31.762059464991072, map.spatialReference));
		json.push(new esri.geometry.Point(120.02800766111065,31.76107241205518, map.spatialReference));
		json.push(new esri.geometry.Point(120.02727810024498,31.75420595684897, map.spatialReference));
		json.push(new esri.geometry.Point(120.02566877480604,31.746609940777102, map.spatialReference));
		json.push(new esri.geometry.Point(120.02601209756635,31.74532248042594, map.spatialReference));
		json.push(new esri.geometry.Point(120.02148452866476,31.74300505179384, map.spatialReference));
		
		json.push(new esri.geometry.Point(120.03987375401385,31.732662453639485, map.spatialReference));
		json.push(new esri.geometry.Point(120.0325137723397,31.72674013602413, map.spatialReference));
		json.push(new esri.geometry.Point(120.02880159499385,31.725860371450832, map.spatialReference));
		json.push(new esri.geometry.Point(120.02255741229071,31.72667576300657, map.spatialReference));
		json.push(new esri.geometry.Point(120.00742975316454,31.73075272078526, map.spatialReference));
		json.push(new esri.geometry.Point(120.00712505395302,31.7308492803116, map.spatialReference));
		json.push(new esri.geometry.Point(119.99178281810165,31.73735095508498, map.spatialReference));
		json.push(new esri.geometry.Point(119.98749128359778,31.738059058278118, map.spatialReference));
		json.push(new esri.geometry.Point(119.9734579657701,31.73831655034835, map.spatialReference));
		json.push(new esri.geometry.Point(119.96124855010657,31.741899981659095, map.spatialReference));
		json.push(new esri.geometry.Point(119.94730106296898,31.7424578811446, map.spatialReference));
		json.push(new esri.geometry.Point(119.9293839064153,31.746642127285885, map.spatialReference));
		json.push(new esri.geometry.Point(119.91633764152351,31.748015418327128, map.spatialReference));
		json.push(new esri.geometry.Point(119.90309825757906,31.7468781616836, map.spatialReference));
		json.push(new esri.geometry.Point(119.89522329176444,31.746921077028638, map.spatialReference));
		json.push(new esri.geometry.Point(119.87067571440228,31.760868564166252, map.spatialReference));
		json.push(new esri.geometry.Point(119.86271491789759,31.776039138637476, map.spatialReference));
		json.push(new esri.geometry.Point(119.86067643900826,31.785094276440667, map.spatialReference));
		json.push(new esri.geometry.Point(119.86039748926551,31.794535652349207, map.spatialReference)); 
		json.push(new esri.geometry.Point(119.8608266427159,31.80895520828225, map.spatialReference));
		json.push(new esri.geometry.Point(119.86157766125409,31.81333257347621, map.spatialReference));
		json.push(new esri.geometry.Point(119.86829391275266,31.825885311900063, map.spatialReference));
		json.push(new esri.geometry.Point(119.87610450554972,31.839253441879652, map.spatialReference));
		json.push(new esri.geometry.Point(119.88043895539863,31.84618427010342, map.spatialReference));
		json.push(new esri.geometry.Point(119.88713374922467,31.857106225415794, map.spatialReference));
		json.push(new esri.geometry.Point(119.89081374006173,31.86322166208383, map.spatialReference));
		json.push(new esri.geometry.Point(119.89422550999231,31.869047420172844, map.spatialReference));
		json.push(new esri.geometry.Point(119.89719739763625,31.873918311834753, map.spatialReference));
		json.push(new esri.geometry.Point(119.89772311061297,31.87477661873553, map.spatialReference));
		json.push(new esri.geometry.Point(119.89727249949007,31.87401487136109, map.spatialReference));
		json.push(new esri.geometry.Point(119.90064135407562,31.872791784027484, map.spatialReference));
		json.push(new esri.geometry.Point(119.90675679074363,31.87038852470531, map.spatialReference));
		json.push(new esri.geometry.Point(119.9125932776689,31.868167655599553, map.spatialReference)); 
		json.push(new esri.geometry.Point(119.91506949294664,31.867282526608133, map.spatialReference));		
		json.push(new esri.geometry.Point(119.91605654588253,31.86688555966652, map.spatialReference));
		json.push(new esri.geometry.Point(119.91698995463713,31.86655296574247, map.spatialReference)); 
		json.push(new esri.geometry.Point(119.91796627873676,31.866434948543613, map.spatialReference));
		json.push(new esri.geometry.Point(119.9187280261112,31.86610235461956, map.spatialReference));
		json.push(new esri.geometry.Point(119.9195756041757,31.866059439274522, map.spatialReference));
		json.push(new esri.geometry.Point(119.91988674042724,31.865941422075668, map.spatialReference));
		json.push(new esri.geometry.Point(119.91999402878983,31.86538352259016, map.spatialReference));
		json.push(new esri.geometry.Point(119.92300883177882,31.864235537110375, map.spatialReference));
		json.push(new esri.geometry.Point(119.92787972344071,31.862111227530953, map.spatialReference));
		json.push(new esri.geometry.Point(119.928729012462,31.861678944664973, map.spatialReference));
		json.push(new esri.geometry.Point(119.93629284202508,31.85746251201491, map.spatialReference));
		json.push(new esri.geometry.Point(119.94160361597362,31.85522018523663, map.spatialReference));
		json.push(new esri.geometry.Point(119.94708605130232,31.852205382247654, map.spatialReference));
		json.push(new esri.geometry.Point(119.95700056467966,31.851080341734047, map.spatialReference));
		json.push(new esri.geometry.Point(119.96450797043128,31.847595112071566, map.spatialReference));
		json.push(new esri.geometry.Point(119.96575744096579,31.85021667041514, map.spatialReference));
		json.push(new esri.geometry.Point(119.97718945489024,31.86512603051992, map.spatialReference));
		json.push(new esri.geometry.Point(119.97968599441487,31.857903189023794, map.spatialReference));
		json.push(new esri.geometry.Point(119.98137042170764,31.85543555668406, map.spatialReference));
		json.push(new esri.geometry.Point(119.98397752891873,31.8518306677008, map.spatialReference));
		json.push(new esri.geometry.Point(119.98507187021723,31.848000473156088, map.spatialReference));
		json.push(new esri.geometry.Point(119.98571560039282,31.845983451939265, map.spatialReference));
		json.push(new esri.geometry.Point(119.98670265332872,31.841938680669358, map.spatialReference));
		json.push(new esri.geometry.Point(119.98735711234055,31.83676738159218, map.spatialReference));
		json.push(new esri.geometry.Point(119.98783990997224,31.835082954299406, map.spatialReference));
		json.push(new esri.geometry.Point(119.98782918113592,31.835318988697114, map.spatialReference));
		json.push(new esri.geometry.Point(119.98898789545201,31.833677476749386, map.spatialReference));
		json.push(new esri.geometry.Point(119.99050066136463,31.83270115264975, map.spatialReference));
		json.push(new esri.geometry.Point(119.99414846569293,31.830802148631783, map.spatialReference));
		json.push(new esri.geometry.Point(119.99884769597467,31.82840961814587, map.spatialReference));
		json.push(new esri.geometry.Point(120.00430867363083,31.82540554399315, map.spatialReference));
		json.push(new esri.geometry.Point(120.00656172924538,31.82246584285799, map.spatialReference));
		json.push(new esri.geometry.Point(120.00764534170762,31.81923646314382, map.spatialReference));
		json.push(new esri.geometry.Point(120.00831052955571,31.81625384666362, map.spatialReference));
		json.push(new esri.geometry.Point(120.00937268434541,31.812015956341043, map.spatialReference));
		json.push(new esri.geometry.Point(120.00964090525193,31.811812108452102, map.spatialReference));
		json.push(new esri.geometry.Point(120.01452252575008,31.81199449866852, map.spatialReference));
		json.push(new esri.geometry.Point(120.01636788558676,31.812316363756313, map.spatialReference));
		json.push(new esri.geometry.Point(120.0210134716872,31.812455838627688, map.spatialReference));
		json.push(new esri.geometry.Point(120.0225262375998,31.81280989022426, map.spatialReference));
		var polygon = new esri.geometry.Polygon(map.spatialReference);
		polygon.addRing(json);
		var symbol = new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([255,0,0]), 5); 
		var graphic = new esri.Graphic(polygon,symbol); 
		map.graphics.add(graphic);
	}
	
	//新增marker（标注）
	//x横坐标
	//y纵坐标
	//icon图片路径
	//width图片宽
	//height图片高
	//backFun回调函数
	function createMarker(code,txt,x, y, icon, width, height) {
		//设置标注的经纬度
		//方法一
		var pt = new esri.geometry.Point(x, y, map.spatialReference);
		//设置标注显示的图标
		//var symbol = new esri.symbol.SimpleMarkerSymbol();
		var symbol = new esri.symbol.PictureMarkerSymbol(icon, width, height);
/* 		var symbol = new esri.symbol.PictureFillSymbol(icon,
			    new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
			    new dojo.Color('#fff'), 1),
			    width, height); */
		//创建图像
		var attr = {"code":code};
		var graPic = new esri.Graphic(pt, symbol,attr);
		map.graphics.add(graPic);
		var font = new esri.symbol.Font();
	    font.setWeight(esri.symbol.Font.WEIGHT_BOLD);
	    var text = new esri.symbol.TextSymbol(txt); 
	    text.setOffset(0,-26);
	    text.setFont(font); 
	    //text.setAlign(esri.symbol.TextSymbol.ALIGN_START);
	    text.setColor(new dojo.Color([0,33,255,0.8]));
	    /* text.setColor(new dojo.Color([255,255,2,1])); */
	    //text.setBackGroundColor(new dojo.Color([255,255,2,1]));
	    //text.setDecoration("blink");
	    var gLbl = new esri.Graphic(pt,text,attr);
	    map.graphics.add(gLbl);
	}
	
	

	dojo.ready(init);
</script>
<style>
#map {
	padding: 0;
	margin: 0;
	height: 100%;
}

text {
	font-size: 13px;
	color: #0033ff;
}
</style>
</head>

<body style="padding: 0px; margin: 0px" class="easyui-layout">
	<div data-options="region:'center',iconCls:'icon-home'"
		style="background: #eee; border: 0px !important">
		<div id="map"></div>

	</div>
	<!-- <div id="WarnLog" style="height:200px;" data-options="region:'south',split:false,collapsible:true,title:'报警数据'">
		<table id="WarnLog_list" cellspacing="0" cellpadding="0"></table>
	</div>
	<div id="tb">
		<a  class="easyui-linkbutton" data-options="iconCls:'icon_delete',plain:true" onclick="distory()">删除</a>
	</div> -->

</body>
</html>
