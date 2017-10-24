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
<link rel="shortcut icon" href="images/logo.ico" />
<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" type="text/css"
	href="js/easyui/themes/mythem/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script src="js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript" src="js/ajaxoverwrite.js"></script>
<title>水利信息化综合管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function createDesktop(sUrl, sName) {
		try {
			var fso = new ActiveXObject("Scripting.FileSystemObject");
			var shell = new ActiveXObject("WScript.Shell");
			var folderPath = shell.SpecialFolders("Desktop");//获取桌面本地桌面地址
			if (!fso.FolderExists(folderPath)) {
				fso.CreateFolder(folderPath);
			}
			if (!fso.FileExists(folderPath + "//" + sName + ".lnk")) {
				//在指定的文件夹下创建名为sName的快捷方式
				var shortLink = shell.CreateShortcut(folderPath + "//" + sName
						+ ".lnk"); //相应的描述信息
				shortLink.Description = "shortcut for " + sName; //快捷方式指向的链接
				shortLink.TargetPath = sUrl; //激活链接并且窗口最大化
				shortLink.WindowStyle = 3;
				shortLink.Save();
				alert('成功');
			}
		} catch (e) {
			alert("当前IE安全级别不允许操作！");
		}
	}
	$(function() {
		$("#login").click(function() {
			if ($.trim($("#username").val()) == "") {
				$.messager.alert("操作提示", "请输入登录用户名！", "info");
				return false;
			}
			if ($.trim($("#password").val()) == "") {
				$.messager.alert("操作提示", "请输入登录密码", "info");
				return false;
			}
			$.post("login/verify.html", {
				username : $("#username").val(),
				password : $("#password").val()
			}, function(data) {
				data = eval('(' + data + ')');
				if (data.result) {
					/* alert("main.html"); */
					window.open("main.html", "_self");					
				} else {
					$.messager.alert("操作提示", data.msg, "error");
				}

			});
		});
		$("#reset").click(function() {
			$("#username").val("");
			$("#password").val("");
		});
		//FuckInternetExplorer();

	});
	function FuckInternetExplorer() {
		var explorer = navigator.userAgent.toLocaleLowerCase();
		if(!!window.ActiveXObject || "ActiveXObject" in window){
			//IE11一下
			if (explorer.indexOf("msie") >= 0) {
				var b_version = navigator.appVersion;
				var version = b_version.split(";");
				if (version.length > 1) {
					var trim_Version = parseInt(version[1].replace(/[ ]/g, "")
							.replace(/MSIE/g, ""));
					if (trim_Version >= 9) {
						return false;
					}
				}
			}else if(explorer.indexOf("trident") >= 0){//IE11
				return false;
			}
		}
		
		//firefox 
		else if (explorer.indexOf("firefox") >= 0) {
			return false;
		}
		$('#dialog').dialog({
			title : '版本提醒',
			width : 300,
			height : 200,
			closed : false,
			cache : false,
			noheader : false,
			modal : true,
			content : '<p>本系统只支持IE9及以上和火狐浏览器,<br/>请更换为指定浏览器后再试<p><br><a href="javascript:void(0)" onclick="downFirefox()" >火狐浏览器下载</a>'
		});
	}
	function downFirefox(){
		window.open("js/soft/Firefox_45.0.2.5941_setup.1460531315.exe");
	}
	function downquik(){
		window.open("login/download.html");
	}
</script>
<body class="easyui-layout" style="text-align:center;width:100%;">
	<div id="winbody" data-options="region:'center',iconCls:'icon-home'"
		style="overflow: hidden;background-color:#fff!important"
		style="overflow: hidden;">
		<div
			style=" height:89px; text-align:center; margin-top:30px; margin-bottom:30px;">
			<img src="images/login_logo.gif" />
		</div>
		<div  class="login_img">
			<center>
				<div class="dx_1000" style="height: 348px;">
					<div class="div_login">
						<div class="zhxx" style="float:right!important">
							<div class="zhm">账户名:</div>
							<div class="div_input">
								<input value="${username }" id="username" type="text"
									class="ps_input" value="请输入用户名"
									onblur="if(this.value =='') {this.value = '请输入用户名';this.style.border='2px solid #29a9c8'}"
									onfocus="if(this.value == '请输入用户名'){ this.value = '';this.style.color='#737e73'; this.style.border='2px solid #fea200';}"
									name="username" maxlength"11" />
							</div>
						</div>

						<div class="zhxx" style=" margin-top:20px;">
							<div class="zhm">密 &nbsp;码:</div>
							<div class="div_input">
								<input value="${userpwd}" id="password" type="password"
									class="ps_input" value="请输入密码"
									onblur="if(this.value =='') {this.value = '请输入密码'; this.style.border='2px solid #29a9c8'}"
									onfocus="if(this.value == '请输入密码'){ this.value = '';this.style.color='#737e73'; this.style.border='2px solid #fea200';}"
									name="username" maxlength"11" />
							</div>
						</div>


						<div class="zhxx" style=" margin-top:20px; ">
							<a id="login" href="javascript:;" class="register">登录</a> <a
								id="reset" href="javascript:;" class="register">重置</a>
						</div>
					</div>


				</div>
			</center>
		</div>
		<center>
			<a onclick="downquik()"  href="javascript:void(0)">创建快捷方式</a>
			<p>本系统只支持IE9及以上和火狐浏览器</p>
			<div class="main_div"
				style="height:50px; line-height:50px; text-align: center; margin-top:30px; color:#000">
				<img align="center" src="./images/logo_ns.png" /> Copyright © 2015
				All Rights Reserved. 常州市城市防洪管理处 版权所有 南水科技 技术支持

			</div>
		</center>
	</div>
	<div id="dialog"></div>
</body>
</html>
