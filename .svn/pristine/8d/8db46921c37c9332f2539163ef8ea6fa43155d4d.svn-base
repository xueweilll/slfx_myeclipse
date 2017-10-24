/**
 * 弹出框、编码
 */

Date.prototype.Format = function(fmt) { //author: meizz 
	var o = {
		"M+": this.getMonth() + 1, //月份 
		"d+": this.getDate(), //日 
		"h+": this.getHours(), //小时 
		"m+": this.getMinutes(), //分 
		"s+": this.getSeconds(), //秒 
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		"S": this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
var timer;
var myInterval;
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

	function showDialog(title, url) {
		/* $("#dialog").dialog('setTitle', title);
		$("#dialog").dialog('open').dialog('refresh', url); */
		$("#dialog").dialog({
			title : title,
			href : url,
			width : 500,
			height : 400,
			closed : true,
			cache : false,
			modal : true
		}).dialog("open");
	}

	function showDialogWH(title, url, width, height) {
		/* $("#dialog").css({'width':width,'height':height});
		$("#dialog").dialog('setTitle', title);
		$("#dialog").dialog('open').dialog('refresh', url); */
		//alert(width);
		$("#dialog").dialog({
			title : title,
			href : url,
			width : width,
			height : height,
			closed : true,
			cache : false,
			modal : true
		}).dialog("open");
	}
	
	function showDialogWHIntval(title, url, width, height) {
		/* $("#dialog").css({'width':width,'height':height});
		$("#dialog").dialog('setTitle', title);
		$("#dialog").dialog('open').dialog('refresh', url); */
		$("#dialog").dialog({
			title : title,
			href : url,
			width : width,
			height : height,
			closed : true,
			cache : false,
			modal : true,
			onClose:function(){
				window.clearInterval(myInterval);
			}
		}).dialog("open");
	}

	function showDialogWHH(title, url, width, height) {
		/* $("#dialog").css({'width':width,'height':height});
		$("#dialog").dialog('setTitle', title);
		$("#dialog").dialog('open').dialog('refresh', url); */
		//alert(width);
		//alert(url);
		$("#window").window(
				{
					title : title,
					//href:url,
					content : '<iframe scrolling="no" frameborder="0"  src="'
							+ url
							+ '" style="width:100%;height:99%;"></iframe>',
					width : width,
					height : height,
					closed : true,
					minimizable : false,
					maximizable : false,
					collapsible : false,
					cache : false,
					modal : true
				}).dialog("open");
	}

	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	function editpwd() {
		showDialogWH("修改密码", "user/editPwd.html", 300, 220);
	}
	
	function returnback(){
		window.open("main.html","_self");
	}

	function loginout(){
		$.post("login/logout.html",function(msg) {
			msg = eval('(' + msg + ')');
			if(msg){
				//window.open("login.html","_self");
				window.location.href="login.html";
			}else{
				$.messager.alert("操作提示", "退出系统失败,请联系管理员！","error");
			}
		});
		//$.get("logout.html");
	}