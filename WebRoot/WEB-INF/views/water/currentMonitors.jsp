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
<title>实时监控</title>
<%@include file="../header.jsp"%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
	
</style>
<script type="text/javascript" src="js/video/webVideoCtrl.js"></script>
<script type="text/javascript">
	var initarea = "869a88c4-f168-4dd1-903f-f67522a8a7b4";
	var szIP="10.32.222.109";
	var szPort="8080";
	var szUsername="admin";
	var szPassword="12345";
	var iChannelID=1;
	var t=false;	
	var result=0;
	var g_iWndIndex = 0;
	$(function() {		
		$('#area').tree({
			checkbox : false,
			animate : true,
			url : 'currentMonitorsController/stationList.html',
			onClick : function(node) {
				WebVideoCtrl.I_Stop();
				$('#videos').tree({
					url : 'currentMonitorsController/cameraList.html?sid='+ node.id
				});
				$('#videos').tree('reload');
				var data=eval('(' + node.attributes + ')');
				szIP= data.NVRUrl;
				szPort= data.NVRProt;
				szUsername=data.NVRUsername;
				szPassword=data.NVRPassword;
				clickLogin();
			},
			onLoadSuccess : function() {
				$('#area').tree('expandAll',$('#area').tree('getRoot').target);
				
			},
			onLoadError : function() {
				$.messager.alert('操作提示', '枢纽加载失败！', 'error');
			}
		});
		$('#videos').tree({
			checkbox : false,
			animate : true,
			url : 'currentMonitorsController/cameraList.html?sid=' + initarea,
			onClick : function(node) {				
				try {
					t=true;
					var data=eval('(' + node.attributes + ')');
					var channel= data.channel;
					iChannelID = parseInt(channel, 10);					
					if(t){
						clickStartRealPlay();
					}else {
						alert("请先选择枢纽！");
					}										
				} catch (error) {
					
				}
			},
			onLoadError : function() {
				$.messager.alert('操作提示', '摄像头加载失败！', 'error');
			}
		});
		
		// 检查插件是否已经安装过
		if (-1 == WebVideoCtrl.I_CheckPluginInstall()) {
			/* $.messager.alert('操作提示',"您还未安装过插件，双击开发包目录里的WebComponents.exe安装！", 'error'); */
			$.messager.confirm('确认对话框', '检测到您还未安装过插件,请点击确定下载并安装！', function(r){
				if (r){
				    window.open("js/video/WebComponents.exe");
				}
			});
			return;
		}
		
		var width=$("#center").width();
		var height=$("#center").height();
		// alert(width+"|"+height);
		// 初始化插件参数及插入插件
		WebVideoCtrl.I_InitPlugin(width, height, {
	        iWndowType: 2,
			cbSelWnd: function (xmlDoc) {
				g_iWndIndex = $(xmlDoc).find("SelectWnd").eq(0).text();				
			}
		});
		WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
		WebVideoCtrl.I_ChangeWndNum(1);
		// 检查插件是否最新
		if (-1 == WebVideoCtrl.I_CheckPluginVersion()) {
			$.messager.confirm('确认对话框', '检测到新的插件版本,请点击确定并下载！', function(r){
				if (r){
				    window.open("js/video/WebComponents.exe");
				}
			});
			/* $.messager.alert('操作提示',"检测到新的插件版本，双击开发包目录里的WebComponents.exe升级！", 'error'); */
			return;
		}
		
		
	});
	
	// 登录
	function clickLogin() {
		if ("" == szIP || "" == szPort) {
			return;
		}

		var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
			success: function (xmlDoc) {				
				result= 1;				
			},
			error: function () {
				result= 2;
			}
		});


		if (-1 == iRet) {
			result= 3;
		}
		return result;
	}
	
	// 开始预览
	function clickStartRealPlay() {
		var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
			szInfo = "";
		if ("" == szIP) {
			return;
		}

		if (oWndInfo != null) {// 已经在播放了，先停止
			WebVideoCtrl.I_Stop();
		}

		var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
			iStreamType: 1,
			iChannelID: iChannelID,
			bZeroChannel: false
		});

		if (0 == iRet) {
			szInfo = "开始预览成功！";
		} else {
			alert("开始预览失败！");
		}

	}

</script>
</head>

<body class="easyui-layout">
	<input id="code" type="hidden" value="${code}" />
	<div region="west" style="width:180px" border=false>
		<div class="easyui-layout" fit=true>
			<div region="north" style="height:200px;">
				<div class="easyui-panel" fit=true border=false title="枢纽列表">
					<ul id="area"></ul>
				</div>
			</div>
			<div region="center" title="摄像头">
				<ul id="videos"></ul>
			</div>
		</div>
	</div>
	<div region="center" border=false>
		<div id="center" class="easyui-panel" fit=true title="监控画面" id="screen"
			style="padding:0px 0px 0px 0px">
			<!-- <a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">开始查看</a><br> -->
			<div id="divPlugin" class="plugin" ></div>
		</div>
	</div>
</body>
</html>
