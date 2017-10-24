/**
 * 菜单I
 */
var firstMenuName = 'basic';
var sys;
$(function() {
	messageCenter();
	sys = $("#sys").val();
	tabClose();
	tabCloseEven();
	$.post("main/menuJson.html", {
		sys : sys
	}, function(msg) {
		_menus = eval('(' + msg + ')');
		$("#wnav").accordion({
			animate : false
		});
		addNav(_menus[firstMenuName].children);
		InitLeftMenu();
	});
	// alert(sys);
	switch (sys) {

	case "sys":
		showLog("images/logo_Basics.png");
		showDefaultPage("system/systemInfo.html");
		break;
	case "oa":
		showLog("images/logo_oa.png");
		showDefaultPage("");
		break;
	case "dispatch":
		showLog("images/logo_Dispatch.png");
		showDefaultPage("map.html");
		break;
	case "material":
		showLog("images/logo_material.png");
		showDefaultPage("material/materialInfo.html");
		break;
	case "inspection":
		showLog("images/logo_Security.png");
		showDefaultPage("system/systemInfo.html");
		break;
	}
});

function showLog(url) {
	$("#log").attr("src", url);
}

// 显示首页
function showDefaultPage(url) {
	addTab("首页", url, "icon icon-home");
}

function Clearnav() {
	var pp = $('#wnav').accordion('panels');

	$.each(pp, function(i, n) {
		if (n) {
			var t = n.panel('options').title;
			$('#wnav').accordion('remove', t);
		}
	});

	pp = $('#wnav').accordion('getSelected');
	if (pp) {
		var title = pp.panel('options').title;
		$('#wnav').accordion('remove', title);
	}
}

function addNav(data) {
	// alert(data.children[0].id);
	$.each(data, function(i, sm) {
		// alert(sm.id);
		if (sm.children != undefined) {
			// alert(sm.children);
			var menulist = "";
			menulist += '<ul>';
			$.each(sm.children, function(j, o) {
				// alert(o.obj.menuicon);
				menulist += '<li><div><a ref="' + o.id
						+ '" href="javascript:void(0)" rel="' + o.obj.menuurl
						+ '" ><span class="icon ' + o.obj.menuicon
						+ '" >&nbsp;</span><span class="nav">' + o.text
						+ '</span></a></div></li> ';
			});
			menulist += '</ul>';
			// alert(sm.text);
			$('#wnav').accordion('add', {
				title : sm.text,
				content : menulist,
				iconCls : 'icon ' + sm.obj.menuicon
			});
		}
	});

	var pp = $('#wnav').accordion('panels');
	var t = pp[0].panel('options').title;
	$('#wnav').accordion('select', t);

}

// 初始化左侧
function InitLeftMenu() {

	hoverMenuItem();

	$('#wnav li a').click(function() {
		var tabTitle = $(this).children('.nav').text();
		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = getIcon(menuid, icon);
		addTab(tabTitle, url, icon);
		$('#wnav li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});
	// addTab("首页", "largesurrounddispatch.html", "icon icon-home");
}

/**
 * 菜单项鼠标Hover
 */
function hoverMenuItem() {
	$(".easyui-accordion").find('a').hover(function() {
		$(this).parent().addClass("hover");
	}, function() {
		$(this).parent().removeClass("hover");
	});
}

// 获取左侧导航的图标
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus[firstMenuName].children, function(i, sm) {
		if (sm.children != undefined) {
			$.each(sm.children, function(j, o) {
				if (o.id == menuid) {
					icon += o.obj.menuicon;
					return false;
				}
			});
		}
	});
	// alert(icon);
	return icon;
}

function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe id="' + url + "ifm" + '" name="' + url + "ifm"
			+ '" scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}

// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#tabs').tabs('close', t);
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}