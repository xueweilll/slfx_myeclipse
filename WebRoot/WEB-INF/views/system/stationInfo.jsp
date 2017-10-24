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

<title>枢纽信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- 
<link rel="stylesheet" href="gis/Map/js/arcgis/js/dojo/dijit/themes/claro/claro.css">
<link rel="stylesheet" href="gis/Map/js/arcgis/js/esri/css/esri.css">

<script src="gis/Map/js/arcgis/init.js"></script> -->
</head>

<body>
	<input id="Id" type="hidden" value="${station.getId()}" />
	<form id="ffsta" method="post" style="margin: 2px; text-align: left" enctype="multipart/form-data">
		<div style="width: 48%;height:95%;float:left;">
			<table cellpadding="0" cellspacing="3" border="0">
				<tr>
				    <td>
				        <label for="departmentid"
				        style="display:incline-block;width:80px">部门名称:</label>
				    
				    </td>
				    <td>
				         <input id="departmentid" name="departmentid" class="easyui-textbox"
				         value="${station.getDepartmentid()}" style="width:250px"/>
				    </td>
				</tr>
				<tr>
					<td><label for="Code"
						style="display: inline-block; width: 80px">枢纽编号:</label></td>
					<td><input id="Code" name="Code" class="easyui-textbox"
						type="text" data-options="required:true,prompt:'编号不能重复',validType:['englishOrNum','length[1,8]']"
						value="${station.getCode()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="Name">枢纽名称:</label></td>
					<td><input id="Name" class="easyui-textbox" type="text"
						name="Name"	data-options="required:true,validType:'judgeNull'"	value="${station.getName()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="Address">详细地址:</label></td>
					<td><input id="Address" class="easyui-textbox" type="text"
						name="Address" value="${station.getAddress()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="Lat">坐标x轴:</label></td>
					<td><input id="Lat" class="easyui-textbox" type="text"
						name="Lat" data-options="required:true,editable:false"
						value="${station.getLat()}" style="width: 250px" /></td>
				</tr>
                <tr>
				   <td><label for="Lon">坐标y轴:</label></td>
					<td><input id="Lon" class="easyui-textbox" type="text"
						name="Lon" data-options="required:true,editable:false"
						value="${station.getLon()}" style="width: 250px" /></td>
				
				</tr>
				<tr>
					<td><label for="NvrName">NVR登录名:</label></td>
					<td><input id="NvrName" class="easyui-textbox" type="text"
						name="NvrName" data-options="required:true"
						value="${station.getNvrusername()}" style="width: 250px" /></td>
				</tr>
				<tr>
				   <td><label for="Password">NVR密码:</label></td>
					<td><input id="Password" class="easyui-textbox" type="text"
						name="Password" data-options="required:true"
						value="${station.getNvrpassword()}" style="width: 250px" /></td>
				
				</tr>
				<tr>
				   <td><label for="Url">NVR地址:</label></td>
					<td><input id="Url" class="easyui-textbox" type="text"
						name="Url" data-options="required:true"
						value="${station.getNvrurl()}" style="width: 250px" /></td>
				
				</tr>
				<tr>
				   <td><label for="Prot">NVR端口:</label></td>
					<td><input id="Prot" class="easyui-textbox" type="text"
						name="Prot" data-options="required:true"
						value="${station.getNvrprot()}" style="width: 250px" /></td>
				</tr>
				<tr>
				   <td><label for="ServicePort">服务端口:</label></td>
					<td><input id="ServicePort" class="easyui-textbox" type="text"
						name="ServicePort" data-options="required:true"
						value="${station.getServiceport()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="Levels">顺序:</label></td>
					<td><input id="Levels" class="easyui-numberbox" type="text"
						name="Levels" data-options="required:true,validType:['integer','length[1,10]']"
						value="${station.getLevels()}" style="width: 250px" /></td>
				</tr>

				<tr>
					<td><label for="Remark">枢纽介绍:</label></td>
					<td><input id="Remark" class="easyui-textbox" type="text"
						name="Remark" style="height: 60px;width: 250px"
						data-options="multiline:true" value="${station.getRemark()}" /></td>
				</tr>
				<tr>
					<td><label for="ControlWaterLevel">控制水位:</label></td>
					<td><input id="ControlWaterLevel" class="easyui-textbox" type="text"
						name="ControlWaterLevel"
						data-options="required:true,validType:['intOrFloat','length[1,10]']"
						value="${station.getControlwaterlevel()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="inwatertop">内河上限:</label></td>
					<td><input id="inwatertop" class="easyui-textbox" type="text"
						name="inwatertop"
						data-options="required:true,validType:['intOrFloat','length[1,10]']"
						value="${station.getInwatertop()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="inwaterdown">内河下限:</label></td>
					<td><input id="inwaterdown" class="easyui-textbox" type="text"
						name="inwaterdown"
						data-options="required:true,validType:['intOrFloat','length[1,10]']"
						value="${station.getInwaterdown()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="outwatertop">外河上限:</label></td>
					<td><input id="outwatertop" class="easyui-textbox" type="text"
						name="outwatertop"
						data-options="required:true,validType:['intOrFloat','length[1,10]']"
						value="${station.getOutwatertop()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="outwaterdown">外河下限:</label></td>
					<td><input id="outwaterdown" class="easyui-textbox" type="text"
						name="outwaterdown"
						data-options="required:true,validType:['intOrFloat','length[1,10]']"
						value="${station.getOutwaterdown()}" style="width: 250px" /></td>
				</tr>
				<tr>
					<td><label for="imgurl">上传图片:</label></td>
					<td><input name = "imgurl" type ="file" id="imgurl" style="width:250px"></td>
				</tr>
			</table>
			&nbsp;
			<div id="btn" style="text-align: left; ">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					id="can" href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</div>
		<div id="map" style="width: 51%;height:95%;float:left;margin-left:3px"></div>
	</form>
	<script type="text/javascript">
		$(function() {
			$("#departmentid").combotree({
				url : 'department/departmentsBind.html',
				required : true
			});
			$("#sub").bind("click", function() {
				$.messager.progress();

				var isValid = $("#ffsta").form('validate');
				if (!isValid) {
					$.messager.progress('close');
					return false;
				}
				/* var data = {
					"id" : ($("#Id").val() == "" ? 0 : $("#Id").val()),
					"code" : $("#Code").textbox("getValue"),
					"name" : $("#Name").textbox("getValue"),
					"lat" : $("#Lat").textbox("getValue"),
					"lon" : $("#Lon").textbox("getValue"),
					"levels" : $("#Levels").textbox("getValue"),
					"remark" : $("#Remark").textbox("getValue"),
					"nvrusername":$("#NvrName").textbox("getValue"),
					"nvrpassword":$("#Password").textbox("getValue"),
					"nvrurl":$("#Url").textbox("getValue"),
					"nvrprot":$("#Prot").textbox("getValue"),
					"departmentid":$("#departmentid").textbox("getValue"),
					"controlwaterlevel":$("#ControlWaterLevel").textbox("getValue"),
					"inwatertop":$("#inwatertop").textbox("getValue"),
					"inwaterdown":$("#inwaterdown").textbox("getValue"),
					"outwatertop":$("#outwatertop").textbox("getValue"),
					"outwaterdown":$("#outwaterdown").textbox("getValue"),
					"address":$("#Address").textbox("getValue"),
					"imgurl":document.getElementById("imgurl").value
				}; */
				/* var id=($("#Id").val() == "" ? 0 : $("#Id").val());
				var code=$("#Code").textbox("getValue");
				var name=$("#Name").textbox("getValue");
				var lat=$("#Lat").textbox("getValue");
				var lon=$("#Lon").textbox("getValue");
				var levels=$("#Levels").textbox("getValue");
				var remark=$("#Remark").textbox("getValue");
				var nvrusername=$("#NvrName").textbox("getValue");
				var nvrpassword=$("#Password").textbox("getValue");
				var nvrurl=$("#Url").textbox("getValue");
				var nvrprot=$("#Prot").textbox("getValue");
				var departmentid=$("#departmentid").textbox("getValue");
				var controlwaterlevel=$("#ControlWaterLevel").textbox("getValue");
				var serviceport=$("#ServicePort").textbox("getText");
				var address = ($("#Address").val() == "" ? 0 :$("#Address").textbox("getValue"));
				var data1=id+"/"+code+"/"+name+"/"+lat+"/"+lon+"/"+levels+"/"+"00"+"/"+
				nvrusername+"/"+nvrpassword+"/"+nvrurl+"/"+nvrprot+"/"+departmentid+"/"+controlwaterlevel+"/"+address+"/"+serviceport;				 */
				/* $.ajax({
					type : "POST",
					url : "station/save.html",
					dataType : "text",
					data : {
						"jsonStr" : JSON.stringify(data)
					},
					success : function() {
						$.messager.progress('close');
						reflush();
						$('#dialog').window('close');
					}
				}); */
				var jsonStr = "{'id' : '"+($("#Id").val() == '' ? 0 : $("#Id").val())+"',"
				  +"'code' :'"+ $("#Code").textbox("getValue")+"',"
		          +"'name' :'"+$("#Name").textbox("getValue")+"',"
		          +"'lat' : '"+$("#Lat").textbox("getValue") +"',"
					+"'lon' : '"+$("#Lon").textbox("getValue")+"',"
					+"'levels' :'"+ $("#Levels").textbox("getValue")+"',"
					+"'remark' :'"+ $("#Remark").textbox("getValue")+"',"
					+"'nvrusername':'"+$("#NvrName").textbox("getValue")+"',"
					+"'nvrpassword':'"+$("#Password").textbox("getValue")+"',"
					+"'nvrurl':'"+$("#Url").textbox("getValue")+"',"
					+"'nvrprot':'"+$("#Prot").textbox("getValue")+"',"
					+"'serviceport':'"+$("#ServicePort").textbox("getValue")+"',"
					+"'departmentid':'"+$("#departmentid").textbox("getValue")+"',"
					+"'controlwaterlevel':'"+$("#ControlWaterLevel").textbox("getValue")+"',"
					+"'inwatertop':'"+$("#inwatertop").textbox("getValue")+"',"
					+"'inwaterdown':'"+$("#inwaterdown").textbox("getValue")+"',"
					+"'outwatertop':'"+$("#outwatertop").textbox("getValue")+"',"
					+"'outwaterdown':'"+$("#outwaterdown").textbox("getValue")+"',"
					+"'address':'"+$("#Address").textbox("getValue")+"',"
					+"'imgurl':'"+document.getElementById("imgurl").value+"'}"
				//alert(jsonStr);
				$.ajaxFileUpload({		        
			        url:'station/save.html',
			        secureuri:false,                       //是否启用安全提交,默认为false
			        //fileElementId:'myBlogImage',           //文件选择框的id属性
			        dataType:'text',                       //服务器返回的格式,可以是json或xml等
			        //fileElementId:arrId,					//数组
			        fileElementId:'imgurl',					//单个
					data:{
						"jsonStr":jsonStr
					},
			        success:function(data, status){        //服务器响应成功时的处理函数
			        	$.messager.progress('close');
						reflush();
						$('#dialog').window('close');
			        },
			        error:function(data, status, e){ //服务器响应失败时的处理函数
			            $('#result').html('图片上传失败，请重试！！');
			        }
			    });
			});

			$("#can").bind("click", function() {
				$("#ffsta").form("clear");
				/* $('#dialog').window('close'); */
			});

			$("#Code").textbox(
					{
						onChange : function(newValue, oldValue) {

							var id = $("#id").val();
							if (id != null && id.length > 0) {
								return false;
							}
							if (newValue == null || newValue.lenth == 0) {
								return false;
							}
							$.post("station/exsitCode.html", {
								code : $("#Code").textbox("getValue").replace(/\s+/g,"")
							}, function(msg) {
								data = eval('(' + msg + ')');
								if (!data.result) {
									$("#Code").textbox("clear").textbox(
											"textbox").focus();
									;
									$.messager.alert('操作提示','编号已存在！！！','error');
								}
							});
						}
					});

			$("#Name").textbox(
					{
						onChange : function(newValue, oldValue) {

							var id = $("#id").val();
							if (id != null && id.length > 0) {
								return false;
							}
							if (newValue == null || newValue.lenth == 0) {
								return false;
							}
							$.post("station/exsitName.html", {
								code : $("#Name").textbox("getText").replace(/\s+/g,"")
							}, function(msg) {
								data = eval('(' + msg + ')');
								if (!data.result) {
									$("#Name").textbox("clear").textbox(
											"textbox").focus();
									//alert('站点名称已存在！！！');
									$.messager.alert('操作提示','站点名称已存在！！！','error');
								}
							});
						}
					});

			dojo.require("esri.map");
			dojo.require("esri.layers.graphics");
			var map;
			var sr;
			var graphicLayer;
			function init() {
				sr = new esri.SpatialReference({
					wkid : 4490
				});
				map = new esri.Map("map", {
					center : new esri.geometry.Point(119.96923837004582,
							31.812328864234377, sr),
					zoom : 10,
					maxZoom : 15,
					logo : false
				});

				//var mapurl = "http://www.czmap.com.cn:8399/arcgis/rest/services/CZSjtlyMap/MapServer";
				//var mapurl ="http://58.216.140.181:8080/gxserver/MapService/czmapserver_sl_ags";
				var mapurl="http://218.94.6.92:6080/arcgis/rest/services/jssl_vector_L3_L17/MapServer";
				var tiledLayer = new esri.layers.ArcGISTiledMapServiceLayer(
						mapurl);
				map.addLayer(tiledLayer);
				dojo.connect(map, "onLoad", initGraphics);
			}

			function initGraphics() {
				
				
				dojo.connect(map, "onClick", function(evt) {
					//prompt('', evt.mapPoint.x + ';' + evt.mapPoint.y);	
					graphicLayer.clear();
					$("#Lat").textbox("setValue", evt.mapPoint.x);
					$("#Lon").textbox("setValue", evt.mapPoint.y);
					createMarker(evt.mapPoint.x, evt.mapPoint.y,
							"images/AZURE.png", 27, 37);
				});
				
				
				//创建图层
				graphicLayer = new esri.layers.GraphicsLayer();
				//把图层添加到地图上
				map.addLayer(graphicLayer);
				
				var x = $("#Lat").val();
				var y = $("#Lon").val();
				if (x != null && y != null) {
					createMarker(x, y, "images/AZURE.png", 27, 37);
				}
			}
			

			//新增marker（标注）
			//x横坐标
			//y纵坐标
			//icon图片路径
			//width图片宽
			//height图片高
			//backFun回调函数
			function createMarker(x, y, icon, width, height) {
				//设置标注的经纬度
				//方法一
				var pt = new esri.geometry.Point(x, y, map.spatialReference);
				//设置标注显示的图标
				//var symbol = new esri.symbol.SimpleMarkerSymbol();
				var symbol = new esri.symbol.PictureMarkerSymbol(icon, width,
						height);				
				
				//创建图像
				var graphic = new esri.Graphic(pt, symbol, null, null);
				//把图像添加到刚才创建的图层上
				graphicLayer.add(graphic);
			}
			dojo.ready(init);

		});

		function reflush() {
			document.getElementById('station.htmlifm').contentWindow.$(
					'#station_list').datagrid('reload');
		}
	</script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
</body>
</html>
