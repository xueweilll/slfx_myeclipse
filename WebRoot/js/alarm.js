/**
 * 报警功能
 */
var alarm;
$(function() {
	// alarmMsg("dasauto");
	$('body')
			.append(
					'<audio id="alarm"><source src="video/alarm.mp3" type="audio/mpeg"></audio>');
	alarm = $("#alarm")[0];
	setInterval("alarmDo()", 5000);
});
var dlg;
var isOpen = false;
function alarmMsg(msg) {
	if (isOpen) {
		return false;
	}
	dlg = $.messager
			.show({
				width : 300,
				height : 200,
				title : '报警消息',
				msg : "<p>"
						+ msg
						+ "</p><p><a href='javascript:void(0)' onclick='clearAlarm()'>我知道了，1小时内不再报警</a></p>",
				timeout : 0
			});
	isOpen = true;
}

function clearAlarm() {
	$.post("floodctl/alarmClear.html", function(data) {
		// alert(data);
		msg = eval('(' + data + ')');
		// alert(msg.result);
		if (msg.result) {
			pause();
			dlg.window('close');
			isOpen = true;
		}
	});
}

function play() {
	alarm.loop = "loop";
	alarm.play();
}

function pause() {
	alarm.pause();
}

function alarmDo() {
	$.post("floodctl/alarm.html", function(data) {
		msg = eval('(' + data + ')');
		if (msg.isAlarm) {
			play();
			alarmMsg(msg.msg)
		} else {
			pause();
		}
	});
}