// ��ʼ�����

// ȫ�ֱ��浱ǰѡ�д���
var g_iWndIndex = 0; //���Բ�����������������д��ڲ����Ľӿ��У����ô�ֵ����������Ĭ��ʹ�õ�ǰѡ�񴰿�
$(function () {
	// ������Ƿ��Ѿ���װ��
	if (-1 == WebVideoCtrl.I_CheckPluginInstall()) {
		alert("����δ��װ�������������WebComponents.exe��װ����װ��ɺ����ֶ�ˢ��ҳ�档");
		//���d���b
		window.open("jsp/video/WebComponents.exe");
		return;
	}
	
	// ��ʼ�����������������
	WebVideoCtrl.I_InitPlugin(500, 300, {
        iWndowType: 2,
		cbSelWnd: function (xmlDoc) {
			g_iWndIndex = $(xmlDoc).find("SelectWnd").eq(0).text();
			var szInfo = "��ǰѡ��Ĵ��ڱ�ţ�" + g_iWndIndex;
			showCBInfo(szInfo);
		}
	});
	WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
	changeWndNum(1);			//1Ϊ1*1,3Ϊ�Ź�����ʾ
	$('embed').attr('style','width:100%;height:100%;');//�ı���Ϊ�ٷֱ���ʾ��ԭ����ֻ֧�̶ֹ�����
	

	// ������Ƿ�����
	if (-1 == WebVideoCtrl.I_CheckPluginVersion()) {
		alert("��⵽�µĲ���汾��˫��������Ŀ¼���WebComponents.exe������");
		return;
	}

	// �����¼���
	$(window).bind({
		resize: function () {
			var $Restart = $("#restartDiv");
			if ($Restart.length > 0) {
				var oSize = getWindowSize();
				$Restart.css({
					width: oSize.width + "px",
					height: oSize.height + "px"
				});
			}
		}
	});

    //��ʼ������ʱ��
    var szCurTime = dateFormat(new Date(), "yyyy-MM-dd");
    //$("#starttime").val(szCurTime + " 00:00:00");
    //$("#endtime").val(szCurTime + " 23:59:59");
});

// ��ʾ������Ϣ
function showOPInfo(szInfo) {
	szInfo = "<div>" + dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss") + " " + szInfo + "</div>";
	$("#opinfo").html(szInfo + $("#opinfo").html());
}

// ��ʾ�ص���Ϣ
function showCBInfo(szInfo) {
	szInfo = "<div>" + dateFormat(new Date(), "yyyy-MM-dd hh:mm:ss") + " " + szInfo + "</div>";
	$("#cbinfo").html(szInfo + $("#cbinfo").html());
}

// ��ʽ��ʱ��
function dateFormat(oDate, fmt) {
	var o = {
		"M+": oDate.getMonth() + 1, //�·�
		"d+": oDate.getDate(), //��
		"h+": oDate.getHours(), //Сʱ
		"m+": oDate.getMinutes(), //��
		"s+": oDate.getSeconds(), //��
		"q+": Math.floor((oDate.getMonth() + 3) / 3), //����
		"S": oDate.getMilliseconds()//����
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (oDate.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

// ��ȡ���ڳߴ�
function getWindowSize() {
	var nWidth = $(this).width() + $(this).scrollLeft(),
		nHeight = $(this).height() + $(this).scrollTop();

	return {width: nWidth, height: nHeight};
}

// ��ѡ��� 0���ļ���  1���ļ�
function clickOpenFileDlg(id, iType) {
	var szDirPath = WebVideoCtrl.I_OpenFileDlg(iType);
	
	if (szDirPath != -1 && szDirPath != "" && szDirPath != null) {
		$("#" + id).val(szDirPath);
	}
}

// ��ȡ���ز���
function clickGetLocalCfg() {
	var xmlDoc = WebVideoCtrl.I_GetLocalCfg();

	$("#netsPreach").val($(xmlDoc).find("BuffNumberType").eq(0).text());
	$("#wndSize").val($(xmlDoc).find("PlayWndType").eq(0).text());
	$("#rulesInfo").val($(xmlDoc).find("IVSMode").eq(0).text());
	$("#captureFileFormat").val($(xmlDoc).find("CaptureFileFormat").eq(0).text());
	$("#packSize").val($(xmlDoc).find("PackgeSize").eq(0).text());
	$("#recordPath").val($(xmlDoc).find("RecordPath").eq(0).text());
	$("#downloadPath").val($(xmlDoc).find("DownloadPath").eq(0).text());
	$("#previewPicPath").val($(xmlDoc).find("CapturePath").eq(0).text());
	$("#playbackPicPath").val($(xmlDoc).find("PlaybackPicPath").eq(0).text());
	$("#playbackFilePath").val($(xmlDoc).find("PlaybackFilePath").eq(0).text());
    $("#protocolType").val($(xmlDoc).find("ProtocolType").eq(0).text());

	showOPInfo("�������û�ȡ�ɹ���");
}

// ���ñ��ز���
function clickSetLocalCfg() {
	var arrXml = [],
		szInfo = "";
	
	arrXml.push("<LocalConfigInfo>");
	arrXml.push("<PackgeSize>" + $("#packSize").val() + "</PackgeSize>");
	arrXml.push("<PlayWndType>" + $("#wndSize").val() + "</PlayWndType>");
	arrXml.push("<BuffNumberType>" + $("#netsPreach").val() + "</BuffNumberType>");
	arrXml.push("<RecordPath>" + $("#recordPath").val() + "</RecordPath>");
	arrXml.push("<CapturePath>" + $("#previewPicPath").val() + "</CapturePath>");
	arrXml.push("<PlaybackFilePath>" + $("#playbackFilePath").val() + "</PlaybackFilePath>");
	arrXml.push("<PlaybackPicPath>" + $("#playbackPicPath").val() + "</PlaybackPicPath>");
	arrXml.push("<DownloadPath>" + $("#downloadPath").val() + "</DownloadPath>");
	arrXml.push("<IVSMode>" + $("#rulesInfo").val() + "</IVSMode>");
	arrXml.push("<CaptureFileFormat>" + $("#captureFileFormat").val() + "</CaptureFileFormat>");
    arrXml.push("<ProtocolType>" + $("#protocolType").val() + "</ProtocolType>");
	arrXml.push("</LocalConfigInfo>");

	var iRet = WebVideoCtrl.I_SetLocalCfg(arrXml.join(""));

	if (0 == iRet) {
		szInfo = "�����������óɹ���";
	} else {
		szInfo = "������������ʧ�ܣ�";
	}
	showOPInfo(szInfo);
}

// ���ڷָ���
function changeWndNum(iType) {
	iType = parseInt(iType, 10);
	WebVideoCtrl.I_ChangeWndNum(iType);
}

// ��¼
function clickLogin() {
	var szIP = $("#loginip").val(),
		szPort = $("#port").val(),
		szUsername = $("#username").val(),
		szPassword = $("#password").val();

	if ("" == szIP || "" == szPort) {
		return;
	}

	var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername, szPassword, {
		success: function (xmlDoc) {
			showOPInfo(szIP + " ��¼�ɹ���");

			$("#ip").prepend("<option value='" + szIP + "'>" + szIP + "</option>");
			setTimeout(function () {
				$("#ip").val(szIP);
				getChannelInfo();
			}, 10);
		},
		error: function () {
			showOPInfo(szIP + " ��¼ʧ�ܣ�");
		}
	});

	if (-1 == iRet) {
		showOPInfo(szIP + " �ѵ�¼����");
	}
}

// �˳�
function clickLogout(url) {
	var szIP = url,
		szInfo = "";

	if (szIP == "") {
		return;
	}

	var iRet = WebVideoCtrl.I_Logout(szIP);
	if (0 == iRet) {
		szInfo = "�˳��ɹ���";

		$("#ip option[value='" + szIP + "']").remove();
		getChannelInfo();
	} else {
		szInfo = "�˳�ʧ�ܣ�";
	}
	showOPInfo(szIP + " " + szInfo);
}

// ��ȡ�豸��Ϣ
function clickGetDeviceInfo() {
	var szIP = $("#ip").val();

	if ("" == szIP) {
		return;
	}

	WebVideoCtrl.I_GetDeviceInfo(szIP, {
		success: function (xmlDoc) {
			var arrStr = [];
			arrStr.push("�豸���ƣ�" + $(xmlDoc).find("deviceName").eq(0).text() + "\r\n");
			arrStr.push("�豸ID��" + $(xmlDoc).find("deviceID").eq(0).text() + "\r\n");
			arrStr.push("�ͺţ�" + $(xmlDoc).find("model").eq(0).text() + "\r\n");
			arrStr.push("�豸���кţ�" + $(xmlDoc).find("serialNumber").eq(0).text() + "\r\n");
			arrStr.push("MAC��ַ��" + $(xmlDoc).find("macAddress").eq(0).text() + "\r\n");
			arrStr.push("���ذ汾��" + $(xmlDoc).find("firmwareVersion").eq(0).text() + " " + $(xmlDoc).find("firmwareReleasedDate").eq(0).text() + "\r\n");
			arrStr.push("����汾��" + $(xmlDoc).find("encoderVersion").eq(0).text() + " " + $(xmlDoc).find("encoderReleasedDate").eq(0).text() + "\r\n");
			
			showOPInfo(szIP + " ��ȡ�豸��Ϣ�ɹ���");
			alert(arrStr.join(""));
		},
		error: function () {
			showOPInfo(szIP + " ��ȡ�豸��Ϣʧ�ܣ�");
		}
	});
}

// ��ȡͨ��
function getChannelInfo() {
	var szIP = $("#ip").val(),
		oSel = $("#channels").empty(),
		nAnalogChannel = 0;

	if ("" == szIP) {
		return;
	}

	// ģ��ͨ��
	WebVideoCtrl.I_GetAnalogChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			var oChannels = $(xmlDoc).find("VideoInputChannel");
			nAnalogChannel = oChannels.length;

			$.each(oChannels, function (i) {
				var id = parseInt($(this).find("id").eq(0).text(), 10),
					name = $(this).find("name").eq(0).text();
				if ("" == name) {
					name = "Camera " + (id < 9 ? "0" + id : id);
				}
				oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
			});
			showOPInfo(szIP + " ��ȡģ��ͨ���ɹ���");
		},
		error: function () {
			showOPInfo(szIP + " ��ȡģ��ͨ��ʧ�ܣ�");
		}
	});
	// ����ͨ��
	WebVideoCtrl.I_GetDigitalChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			var oChannels = $(xmlDoc).find("InputProxyChannelStatus");

			$.each(oChannels, function (i) {
				var id = parseInt($(this).find("id").eq(0).text(), 10),
					name = $(this).find("name").eq(0).text(),
					online = $(this).find("online").eq(0).text();
				if ("false" == online) {// ���˽��õ�����ͨ��
					return true;
				}
				if ("" == name) {
					name = "IPCamera " + ((id - nAnalogChannel) < 9 ? "0" + (id - nAnalogChannel) : (id - nAnalogChannel));
				}
				oSel.append("<option value='" + id + "' bZero='false'>" + name + "</option>");
			});
			showOPInfo(szIP + " ��ȡ����ͨ���ɹ���");
		},
		error: function () {
			showOPInfo(szIP + " ��ȡ����ͨ��ʧ�ܣ�");
		}
	});
	// ��ͨ��
	WebVideoCtrl.I_GetZeroChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			var oChannels = $(xmlDoc).find("ZeroVideoChannel");
			
			$.each(oChannels, function (i) {
				var id = parseInt($(this).find("id").eq(0).text(), 10),
					name = $(this).find("name").eq(0).text();
				if ("" == name) {
					name = "Zero Channel " + (id < 9 ? "0" + id : id);
				}
				if ("true" == $(this).find("enabled").eq(0).text()) {// ���˽��õ���ͨ��
					oSel.append("<option value='" + id + "' bZero='true'>" + name + "</option>");
				}
			});
			showOPInfo(szIP + " ��ȡ��ͨ���ɹ���");
		},
		error: function () {
			showOPInfo(szIP + " ��ȡ��ͨ��ʧ�ܣ�");
		}
	});
}

// ��ȡ����ͨ��
function clickGetDigitalChannelInfo() {
	var szIP = $("#ip").val(),
		iAnalogChannelNum = 0;

	$("#digitalchannellist").empty();

	if ("" == szIP) {
		return;
	}

	// ģ��ͨ��
	WebVideoCtrl.I_GetAnalogChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			iAnalogChannelNum = $(xmlDoc).find("VideoInputChannel").length;
		},
		error: function () {
			
		}
	});

	// ����ͨ��
	WebVideoCtrl.I_GetDigitalChannelInfo(szIP, {
		async: false,
		success: function (xmlDoc) {
			var oChannels = $(xmlDoc).find("InputProxyChannelStatus");
			
			$.each(oChannels, function () {
				var id = parseInt($(this).find("id").eq(0).text(), 10),
					ipAddress = $(this).find("ipAddress").eq(0).text(),
					srcInputPort = $(this).find("srcInputPort").eq(0).text(),
					managePortNo = $(this).find("managePortNo").eq(0).text(),
					online = $(this).find("online").eq(0).text(),
					proxyProtocol = $(this).find("proxyProtocol").eq(0).text();
							
				var objTr = $("#digitalchannellist").get(0).insertRow(-1);
				var objTd = objTr.insertCell(0);
				objTd.innerHTML = (id - iAnalogChannelNum) < 10 ? "D0" + (id - iAnalogChannelNum) : "D" + (id - iAnalogChannelNum);
				objTd = objTr.insertCell(1);
				objTd.width = "25%";
				objTd.innerHTML = ipAddress;
				objTd = objTr.insertCell(2);
				objTd.width = "15%";
				objTd.innerHTML = srcInputPort;
				objTd = objTr.insertCell(3);
				objTd.width = "20%";
				objTd.innerHTML = managePortNo;
				objTd = objTr.insertCell(4);
				objTd.width = "15%";
				objTd.innerHTML = "true" == online ? "����" : "����";
				objTd = objTr.insertCell(5);
				objTd.width = "25%";
				objTd.innerHTML = proxyProtocol;
			});
			showOPInfo(szIP + " ��ȡ����ͨ���ɹ���");
		},
		error: function () {
			showOPInfo(szIP + " û������ͨ����");
		}
	});
}

// ��ʼԤ��
function clickStartRealPlay(url) {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szIP = url,
		iStreamType = parseInt(1, 10),		//������
		//bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
		szInfo = "";

	if ("" == szIP||null==szIP) {
		return;
	}

	if (oWndInfo != null) {// �Ѿ��ڲ����ˣ���ֹͣ
		WebVideoCtrl.I_Stop();
	}

	var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
		iStreamType: iStreamType
		//iChannelID: iChannelID,
		//bZeroChannel: bZeroChannel
	});

	if (0 == iRet) {
		szInfo = "��ʼԤ���ɹ���";
	} else {
		szInfo = "��ʼԤ��ʧ�ܣ�";
	}

	showOPInfo(szIP + " " + szInfo);
}

// ֹͣԤ��
function clickStopRealPlay() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Stop();
		if (0 == iRet) {
			szInfo = "ֹͣԤ���ɹ���";
		} else {
			szInfo = "ֹͣԤ��ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ������
function clickOpenSound() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var allWndInfo = WebVideoCtrl.I_GetWindowStatus();
		// ѭ���������д��ڣ�����д��ڴ����������ȹر�
		for (var i = 0, iLen = allWndInfo.length; i < iLen; i++) {
			oWndInfo = allWndInfo[i];
			if (oWndInfo.bSound) {
				WebVideoCtrl.I_CloseSound(oWndInfo.iIndex);
				break;
			}
		}

		var iRet = WebVideoCtrl.I_OpenSound();

		if (0 == iRet) {
			szInfo = "�������ɹ���";
		} else {
			szInfo = "������ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// �ر�����
function clickCloseSound() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_CloseSound();
		if (0 == iRet) {
			szInfo = "�ر������ɹ���";
		} else {
			szInfo = "�ر�����ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ��������
function clickSetVolume() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		iVolume = parseInt($("#volume").val(), 10),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_SetVolume(iVolume);
		if (0 == iRet) {
			szInfo = "�������óɹ���";
		} else {
			szInfo = "��������ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ץͼ
function clickCapturePic() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var szChannelID = $("#channels").val(),
			szPicName = oWndInfo.szIP + "_" + szChannelID + "_" + new Date().getTime(),
			iRet = WebVideoCtrl.I_CapturePic(szPicName);
		if (0 == iRet) {
			szInfo = "ץͼ�ɹ���";
		} else {
			szInfo = "ץͼʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ��ʼ¼��
function clickStartRecord() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var szChannelID = $("#channels").val(),
			szFileName = oWndInfo.szIP + "_" + szChannelID + "_" + new Date().getTime(),
			iRet = WebVideoCtrl.I_StartRecord(szFileName);
		if (0 == iRet) {
			szInfo = "��ʼ¼��ɹ���";
		} else {
			szInfo = "��ʼ¼��ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ֹͣ¼��
function clickStopRecord() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_StopRecord();
		if (0 == iRet) {
			szInfo = "ֹͣ¼��ɹ���";
		} else {
			szInfo = "ֹͣ¼��ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ��ȡ�Խ�ͨ��
function clickGetAudioInfo() {
	var szIP = $("#ip").val();

	if ("" == szIP) {
		return;
	}

	WebVideoCtrl.I_GetAudioInfo(szIP, {
		success: function (xmlDoc) {
			var oAudioChannels = $(xmlDoc).find("TwoWayAudioChannel"),
				oSel = $("#audiochannels").empty();
			$.each(oAudioChannels, function () {
				var id = $(this).find("id").eq(0).text();

				oSel.append("<option value='" + id + "'>" + id + "</option>");
			});
			showOPInfo(szIP + " ��ȡ�Խ�ͨ���ɹ���");
		},
		error: function () {
			showOPInfo(szIP + " ��ȡ�Խ�ͨ��ʧ�ܣ�");
		}
	});
}

// ��ʼ�Խ�
function clickStartVoiceTalk() {
	var szIP = $("#ip").val(),
		iAudioChannel = parseInt($("#audiochannels").val(), 10),
		szInfo = "";

	if ("" == szIP) {
		return;
	}

	if (isNaN(iAudioChannel)){
		alert("��ѡ��Խ�ͨ����");
		return;
	}

	var iRet = WebVideoCtrl.I_StartVoiceTalk(szIP, iAudioChannel);

	if (0 == iRet) {
		szInfo = "��ʼ�Խ��ɹ���";
	} else {
		szInfo = "��ʼ�Խ�ʧ�ܣ�";
	}
	showOPInfo(szIP + " " + szInfo);
}

// ֹͣ�Խ�
function clickStopVoiceTalk() {
	var szIP = $("#ip").val(),
		iRet = WebVideoCtrl.I_StopVoiceTalk(),
		szInfo = "";

	if ("" == szIP) {
		return;
	}

	if (0 == iRet) {
		szInfo = "ֹͣ�Խ��ɹ���";
	} else {
		szInfo = "ֹͣ�Խ�ʧ�ܣ�";
	}
	showOPInfo(szIP + " " + szInfo);
}

// ���õ��ӷŴ�
function clickEnableEZoom() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_EnableEZoom();
		if (0 == iRet) {
			szInfo = "���õ��ӷŴ�ɹ���";
		} else {
			szInfo = "���õ��ӷŴ�ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ���õ��ӷŴ�
function clickDisableEZoom() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_DisableEZoom();
		if (0 == iRet) {
			szInfo = "���õ��ӷŴ�ɹ���";
		} else {
			szInfo = "���õ��ӷŴ�ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ����3D�Ŵ�
function clickEnable3DZoom() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Enable3DZoom();
		if (0 == iRet) {
			szInfo = "����3D�Ŵ�ɹ���";
		} else {
			szInfo = "����3D�Ŵ�ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ����3D�Ŵ�
function clickDisable3DZoom() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Disable3DZoom();
		if (0 == iRet) {
			szInfo = "����3D�Ŵ�ɹ���";
		} else {
			szInfo = "����3D�Ŵ�ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ȫ��
function clickFullScreen() {
	WebVideoCtrl.I_FullScreen(true);
}

// PTZ���� 9Ϊ�Զ���1,2,3,4,5,6,7,8Ϊ����PTZ
var g_bPTZAuto = false;
function mouseDownPTZControl(iPTZIndex) {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
		iPTZSpeed = $("#ptzspeed").val(),
		bStop = false;

	if (bZeroChannel) {// ��ͨ����֧����̨
		return;
	}
	
	if (oWndInfo != null) {
		if (9 == iPTZIndex && g_bPTZAuto) {
			iPTZSpeed = 0;// �Զ��������ٶ���Ϊ0���Թر��Զ�
			bStop = true;
		} else {
			g_bPTZAuto = false;// ������������Զ��϶��ᱻ�ر�
			bStop = false;
		}

		WebVideoCtrl.I_PTZControl(iPTZIndex, bStop, {
			iPTZSpeed: iPTZSpeed,
			success: function (xmlDoc) {
				if (9 == iPTZIndex) {
					g_bPTZAuto = !g_bPTZAuto;
				}
				showOPInfo(oWndInfo.szIP + " ������̨�ɹ���");
			},
			error: function () {
				showOPInfo(oWndInfo.szIP + " ������̨ʧ�ܣ�");
			}
		});
	}
}

// ����PTZֹͣ
function mouseUpPTZControl() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(1, true, {
			success: function (xmlDoc) {
				showOPInfo(oWndInfo.szIP + " ֹͣ��̨�ɹ���");
			},
			error: function () {
				showOPInfo(oWndInfo.szIP + " ֹͣ��̨ʧ�ܣ�");
			}
		});
	}
}

// ����Ԥ�õ�
function clickSetPreset() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		iPresetID = parseInt($("#preset").val(), 10);

	if (oWndInfo != null) {
		WebVideoCtrl.I_SetPreset(iPresetID, {
			success: function (xmlDoc) {
				showOPInfo(oWndInfo.szIP + " ����Ԥ�õ�ɹ���");
			},
			error: function () {
				showOPInfo(oWndInfo.szIP + " ����Ԥ�õ�ʧ�ܣ�");
			}
		});
	}
}

// ����Ԥ�õ�
function clickGoPreset() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		iPresetID = parseInt($("#preset").val(), 10);

	if (oWndInfo != null) {
		WebVideoCtrl.I_GoPreset(iPresetID, {
			success: function (xmlDoc) {
				showOPInfo(oWndInfo.szIP + " ����Ԥ�õ�ɹ���");
			},
			error: function () {
				showOPInfo(oWndInfo.szIP + " ����Ԥ�õ�ʧ�ܣ�");
			}
		});
	}
}

// ����¼��
var iSearchTimes = 0;
function clickRecordSearch(iType) {
	var szIP = $("#ip").val(),
		iChannelID = $("#channels").val(),
		bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
		szStartTime = $("#starttime").val(),
		szEndTime = $("#endtime").val();

	if ("" == szIP) {
		return;
	}

	if (bZeroChannel) {// ��ͨ����֧��¼������
		return;
	}

	if (0 == iType) {// �״�����
		$("#searchlist").empty();
		iSearchTimes = 0;
	}

	WebVideoCtrl.I_RecordSearch(szIP, iChannelID, szStartTime, szEndTime, {
		iSearchPos: iSearchTimes * 40,
		success: function (xmlDoc) {
			if("MORE" === $(xmlDoc).find("responseStatusStrg").eq(0).text()) {
				
				for(var i = 0, nLen = $(xmlDoc).find("searchMatchItem").length; i < nLen; i++) {
					var szPlaybackURI = $(xmlDoc).find("playbackURI").eq(i).text();
					if(szPlaybackURI.indexOf("name=") < 0) {
						break;
					}
					var szStartTime = $(xmlDoc).find("startTime").eq(i).text();
					var szEndTime = $(xmlDoc).find("endTime").eq(i).text();
					var szFileName = szPlaybackURI.substring(szPlaybackURI.indexOf("name=") + 5, szPlaybackURI.indexOf("&size="));

					var objTr = $("#searchlist").get(0).insertRow(-1);
					var objTd = objTr.insertCell(0);
					objTd.id = "downloadTd" + i;
					objTd.innerHTML = iSearchTimes * 40 + (i + 1);
					objTd = objTr.insertCell(1);
					objTd.width = "30%";
					objTd.innerHTML = szFileName;
					objTd = objTr.insertCell(2);
					objTd.width = "30%";
					objTd.innerHTML = (szStartTime.replace("T", " ")).replace("Z", "");
					objTd = objTr.insertCell(3);
					objTd.width = "30%";
					objTd.innerHTML = (szEndTime.replace("T", " ")).replace("Z", "");
					objTd = objTr.insertCell(4);
					objTd.width = "10%";
					objTd.innerHTML = "<a href='javascript:;' onclick='clickStartDownloadRecord(" + i + ");'>����</a>";
					$("#downloadTd" + i).data("playbackURI", szPlaybackURI);
				}

				iSearchTimes++;
				clickRecordSearch(1);// ��������
			} else if ("OK" === $(xmlDoc).find("responseStatusStrg").eq(0).text()) {
				var iLength = $(xmlDoc).find("searchMatchItem").length;
				for(var i = 0; i < iLength; i++) {
					var szPlaybackURI = $(xmlDoc).find("playbackURI").eq(i).text();
					if(szPlaybackURI.indexOf("name=") < 0) {
						break;
					}
					var szStartTime = $(xmlDoc).find("startTime").eq(i).text();
					var szEndTime = $(xmlDoc).find("endTime").eq(i).text();
					var szFileName = szPlaybackURI.substring(szPlaybackURI.indexOf("name=") + 5, szPlaybackURI.indexOf("&size="));

					var objTr = $("#searchlist").get(0).insertRow(-1);
					var objTd = objTr.insertCell(0);
					objTd.id = "downloadTd" + i;
					objTd.innerHTML = iSearchTimes * 40 + (i + 1);
					objTd = objTr.insertCell(1);
					objTd.width = "30%";
					objTd.innerHTML = szFileName;
					objTd = objTr.insertCell(2);
					objTd.width = "30%";
					objTd.innerHTML = (szStartTime.replace("T", " ")).replace("Z", "");
					objTd = objTr.insertCell(3);
					objTd.width = "30%";
					objTd.innerHTML = (szEndTime.replace("T", " ")).replace("Z", "");
					objTd = objTr.insertCell(4);
					objTd.width = "10%";
					objTd.innerHTML = "<a href='javascript:;' onclick='clickStartDownloadRecord(" + i + ");'>����</a>";
					$("#downloadTd" + i).data("playbackURI", szPlaybackURI);
				}
				showOPInfo(szIP + " ����¼���ļ��ɹ���");
			} else if("NO MATCHES" === $(xmlDoc).find("responseStatusStrg").eq(0).text()) {
				setTimeout(function() {
					showOPInfo(szIP + " û��¼���ļ���");
				}, 50);
			}
		},
		error: function () {
			showOPInfo(szIP + " ����¼���ļ�ʧ�ܣ�");
		}
	});
}

// ��ʼ�ط�
function clickStartPlayback() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szIP = $("#ip").val(),
		bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
		iChannelID = $("#channels").val(),
		szStartTime = $("#starttime").val(),
		szEndTime = $("#endtime").val(),
		szInfo = "",
		bChecked = $("#transstream").prop("checked"),
		iRet = -1;

	if ("" == szIP) {
		return;
	}

	if (bZeroChannel) {// ��ͨ����֧�ֻط�
		return;
	}

	if (oWndInfo != null) {// �Ѿ��ڲ����ˣ���ֹͣ
		WebVideoCtrl.I_Stop();
	}

	if (bChecked) {// ����ת��ط�
		var oTransCodeParam = {
			TransFrameRate: "16",// 0��ȫ֡�ʣ�5��1��6��2��7��4��8��6��9��8��10��10��11��12��12��16��14��15��15��18��13��20��16��22
			TransResolution: "2",// 255��Auto��3��4CIF��2��QCIF��1��CIF
			TransBitrate: "23"// 2��32K��3��48K��4��64K��5��80K��6��96K��7��128K��8��160K��9��192K��10��224K��11��256K��12��320K��13��384K��14��448K��15��512K��16��640K��17��768K��18��896K��19��1024K��20��1280K��21��1536K��22��1792K��23��2048K��24��3072K��25��4096K��26��8192K
		};
		iRet = WebVideoCtrl.I_StartPlayback(szIP, {
			iChannelID: iChannelID,
			szStartTime: szStartTime,
			szEndTime: szEndTime,
			oTransCodeParam: oTransCodeParam
		});
	} else {
		iRet = WebVideoCtrl.I_StartPlayback(szIP, {
			iChannelID: iChannelID,
			szStartTime: szStartTime,
			szEndTime: szEndTime
		});
	}

	if (0 == iRet) {
		szInfo = "��ʼ�طųɹ���";
	} else {
		szInfo = "��ʼ�ط�ʧ�ܣ�";
	}
	showOPInfo(szIP + " " + szInfo);
}

// ֹͣ�ط�
function clickStopPlayback() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Stop();
		if (0 == iRet) {
			szInfo = "ֹͣ�طųɹ���";
		} else {
			szInfo = "ֹͣ�ط�ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ��ʼ����
function clickReversePlayback() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szIP = $("#ip").val(),
		bZeroChannel = $("#channels option").eq($("#channels").get(0).selectedIndex).attr("bZero") == "true" ? true : false,
		iChannelID = $("#channels").val(),
		szStartTime = $("#starttime").val(),
		szEndTime = $("#endtime").val(),
		szInfo = "";

	if ("" == szIP) {
		return;
	}

	if (bZeroChannel) {// ��ͨ����֧�ֻط�
		return;
	}

	if (oWndInfo != null) {// �Ѿ��ڲ����ˣ���ֹͣ
		WebVideoCtrl.I_Stop();
	}

	var iRet = WebVideoCtrl.I_ReversePlayback(szIP, {
		iChannelID: iChannelID,
		szStartTime: szStartTime,
		szEndTime: szEndTime
	});

	if (0 == iRet) {
		szInfo = "��ʼ���ųɹ���";
	} else {
		szInfo = "��ʼ����ʧ�ܣ�";
	}
	showOPInfo(szIP + " " + szInfo);
}

// ��֡
function clickFrame() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Frame();
		if (0 == iRet) {
			szInfo = "��֡���ųɹ���";
		} else {
			szInfo = "��֡����ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ��ͣ
function clickPause() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Pause();
		if (0 == iRet) {
			szInfo = "��ͣ�ɹ���";
		} else {
			szInfo = "��ͣʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// �ָ�
function clickResume() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Resume();
		if (0 == iRet) {
			szInfo = "�ָ��ɹ���";
		} else {
			szInfo = "�ָ�ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ����
function clickPlaySlow() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_PlaySlow();
		if (0 == iRet) {
			szInfo = "���ųɹ���";
		} else {
			szInfo = "����ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// ���
function clickPlayFast() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
		szInfo = "";

	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_PlayFast();
		if (0 == iRet) {
			szInfo = "��ųɹ���";
		} else {
			szInfo = "���ʧ�ܣ�";
		}
		showOPInfo(oWndInfo.szIP + " " + szInfo);
	}
}

// OSDʱ��
function clickGetOSDTime() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	
	if (oWndInfo != null) {
		var szTime = WebVideoCtrl.I_GetOSDTime();
		if (szTime != -1) {
			$("#osdtime").val(szTime);
			showOPInfo(oWndInfo.szIP + " ��ȡOSDʱ��ɹ���");
		} else {
			showOPInfo(oWndInfo.szIP + " ��ȡOSDʱ��ʧ�ܣ�");
		}
	}
}

// ����¼��
var iDownloadID = -1;
var tDownloadProcess = 0;
function clickStartDownloadRecord(i) {
	var szIP = $("#ip").val(),
		szChannelID = $("#channels").val(),
		szFileName = szIP + "_" + szChannelID + "_" + new Date().getTime(),
		szPlaybackURI = $("#downloadTd" + i).data("playbackURI");

	if ("" == szIP) {
		return;
	}

	iDownloadID = WebVideoCtrl.I_StartDownloadRecord(szIP, szPlaybackURI, szFileName);

	if (iDownloadID < 0) {
		var iErrorValue = WebVideoCtrl.I_GetLastError();
		if (34 == iErrorValue) {
			showOPInfo(szIP + " �����أ�");
		} else if (33 == iErrorValue) {
			showOPInfo(szIP + " �ռ䲻�㣡");
		} else {
			showOPInfo(szIP + " ����ʧ�ܣ�");
		}
	} else {
		$("<div id='downProcess' class='freeze'></div>").appendTo("body");
		tDownloadProcess = setInterval("downProcess(" + i + ")", 1000);
	}
}
// ���ؽ���
function downProcess() {
	var iStatus = WebVideoCtrl.I_GetDownloadStatus(iDownloadID);
	if (0 == iStatus) {
		$("#downProcess").css({
			width: $("#searchlist").width() + "px",
			height: "100px",
			lineHeight: "100px",
			left: $("#searchlist").offset().left + "px",
			top: $("#searchlist").offset().top + "px"
		});
		var iProcess = WebVideoCtrl.I_GetDownloadProgress(iDownloadID);
		if (iProcess < 0) {
			clearInterval(tDownloadProcess);
			tDownloadProcess = 0;
			m_iDownloadID = -1;
		} else if (iProcess < 100) {
			$("#downProcess").text(iProcess + "%");
		} else {
			$("#downProcess").text("100%");
			setTimeout(function () {
				$("#downProcess").remove();
			}, 1000);

			WebVideoCtrl.I_StopDownloadRecord(iDownloadID);

            		showOPInfo("¼���������");
			clearInterval(tDownloadProcess);
			tDownloadProcess = 0;
			m_iDownloadID = -1;
		}
	} else {
		WebVideoCtrl.I_StopDownloadRecord(iDownloadID);

		clearInterval(tDownloadProcess);
		tDownloadProcess = 0;
		iDownloadID = -1;
	}
}

// ���������ļ�
function clickExportDeviceConfig() {
	var szIP = $("#ip").val(),
		szInfo = "";

	if ("" == szIP) {
		return;
	}

	var iRet = WebVideoCtrl.I_ExportDeviceConfig(szIP);

	if (0 == iRet) {
		szInfo = "���������ļ��ɹ���";
	} else {
		szInfo = "���������ļ�ʧ�ܣ�";
	}
	showOPInfo(szIP + " " + szInfo);
}

// ���������ļ�
function clickImportDeviceConfig() {
	var szIP = $("#ip").val(),
		szFileName = $("#configFile").val();

	if ("" == szIP) {
		return;
	}

	if ("" == szFileName) {
		alert("��ѡ�������ļ���");
		return;
	}

	var iRet = WebVideoCtrl.I_ImportDeviceConfig(szIP, szFileName);

	if (0 == iRet) {
		WebVideoCtrl.I_Restart(szIP, {
			success: function (xmlDoc) {
				$("<div id='restartDiv' class='freeze'>������...</div>").appendTo("body");
				var oSize = getWindowSize();
				$("#restartDiv").css({
					width: oSize.width + "px",
					height: oSize.height + "px",
					lineHeight: oSize.height + "px",
					left: 0,
					top: 0
				});
				setTimeout("reconnect('" + szIP + "')", 20000);
			},
			error: function () {
				showOPInfo(szIP + " ����ʧ�ܣ�");
			}
		});
	} else {
		showOPInfo(szIP + " ����ʧ�ܣ�");
	}
}

// ����
function reconnect(szIP) {
	WebVideoCtrl.I_Reconnect(szIP, {
		success: function (xmlDoc) {
			$("#restartDiv").remove();
		},
		error: function () {
			setTimeout(function () {reconnect(szIP);}, 5000);
		}
	});
}

// ��ʼ����
m_tUpgrade = 0;
function clickStartUpgrade(szIP) {
	var szIP = $("#ip").val(),
		szFileName = $("#upgradeFile").val();

	if ("" == szIP) {
		return;
	}

	if ("" == szFileName) {
		alert("��ѡ�������ļ���");
		return;
	}

	var iRet = WebVideoCtrl.I_StartUpgrade(szIP, szFileName);
	if (0 == iRet) {
		m_tUpgrade = setInterval("getUpgradeStatus('" + szIP + "')", 1000);
	} else {
		showOPInfo(szIP + " ����ʧ�ܣ�");
	}
}

// ��ȡ����״̬
function getUpgradeStatus(szIP) {
	var iStatus = WebVideoCtrl.I_UpgradeStatus();
	if (iStatus == 0) {
		var iProcess = WebVideoCtrl.I_UpgradeProgress();
		if (iProcess < 0) {
			clearInterval(m_tUpgrade);
			m_tUpgrade = 0;
			showOPInfo(szIP + " ��ȡ����ʧ�ܣ�");
			return;
		} else if (iProcess < 100) {
			if (0 == $("#restartDiv").length) {
				$("<div id='restartDiv' class='freeze'></div>").appendTo("body");
				var oSize = getWindowSize();
				$("#restartDiv").css({
					width: oSize.width + "px",
					height: oSize.height + "px",
					lineHeight: oSize.height + "px",
					left: 0,
					top: 0
				});
			}
			$("#restartDiv").text(iProcess + "%");
		} else {
			WebVideoCtrl.I_StopUpgrade();
			clearInterval(m_tUpgrade);
			m_tUpgrade = 0;

			$("#restartDiv").remove();

			WebVideoCtrl.I_Restart(szIP, {
				success: function (xmlDoc) {
					$("<div id='restartDiv' class='freeze'>������...</div>").appendTo("body");
					var oSize = getWindowSize();
					$("#restartDiv").css({
						width: oSize.width + "px",
						height: oSize.height + "px",
						lineHeight: oSize.height + "px",
						left: 0,
						top: 0
					});
					setTimeout("reconnect('" + szIP + "')", 20000);
				},
				error: function () {
					showOPInfo(szIP + " ����ʧ�ܣ�");
				}
			});
		}
	} else if (iStatus == 1) {
		WebVideoCtrl.I_StopUpgrade();
		showOPInfo(szIP + " ����ʧ�ܣ�");
		clearInterval(m_tUpgrade);
		m_tUpgrade = 0;
	} else if (iStatus == 2) {
		mWebVideoCtrl.I_StopUpgrade();
		showOPInfo(szIP + " ���Բ�ƥ�䣡");
		clearInterval(m_tUpgrade);
		m_tUpgrade = 0;
	} else {
		mWebVideoCtrl.I_StopUpgrade();
		showOPInfo(szIP + " ��ȡ״̬ʧ�ܣ�");
		clearInterval(m_tUpgrade);
		m_tUpgrade = 0;
	}
}

// ������汾
function clickCheckPluginVersion() {
	var iRet = WebVideoCtrl.I_CheckPluginVersion();
	if (0 == iRet) {
		alert("���Ĳ���汾�Ѿ������µģ�");
	} else {
		alert("��⵽�µĲ���汾��");
	}
}

// Զ�����ÿ�
function clickRemoteConfig() {
	var szIP = $("#ip").val(),
		iDevicePort = parseInt($("#deviceport").val(), 10) || "",
		szInfo = "";
	
	if ("" == szIP) {
		return;
	}

	var iRet = WebVideoCtrl.I_RemoteConfig(szIP, {
		iDevicePort: iDevicePort,
		iLan: 1
	});

	if (-1 == iRet) {
		szInfo = "����Զ�����ÿ�ʧ�ܣ�";
	} else {
		szInfo = "����Զ�����ÿ�ɹ���";
	}
	showOPInfo(szIP + " " + szInfo);
}

function clickRestoreDefault() {
    var szIP = $("#ip").val(),
        szMode = "basic";
    WebVideoCtrl.I_RestoreDefault(szIP, szMode, {
        success: function (xmlDoc) {
            $("#restartDiv").remove();
            showOPInfo(szIP + " �ָ�Ĭ�ϲ����ɹ���");
            //�ָ���ɺ���Ҫ����
            WebVideoCtrl.I_Restart(szIP, {
                success: function (xmlDoc) {
                    $("<div id='restartDiv' class='freeze'>������...</div>").appendTo("body");
                    var oSize = getWindowSize();
                    $("#restartDiv").css({
                        width: oSize.width + "px",
                        height: oSize.height + "px",
                        lineHeight: oSize.height + "px",
                        left: 0,
                        top: 0
                    });
                    setTimeout("reconnect('" + szIP + "')", 20000);
                },
                error: function () {
                    showOPInfo(szIP + " ����ʧ�ܣ�");
                }
            });
        },
        error: function () {
            showOPInfo(szIP + " �ָ�Ĭ�ϲ���ʧ�ܣ�");
        }
    });
}

function PTZZoomIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(10, false, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " ����+�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  ����+ʧ�ܣ�");
            }
        });
    }
}

function PTZZoomout() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(11, false, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " ����-�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  ����-ʧ�ܣ�");
            }
        });
    }
}

function PTZZoomStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(11, true, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " ����ֹͣ�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  ����ֹͣʧ�ܣ�");
            }
        });
    }
}

function PTZFocusIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(12, false, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " �۽�+�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  �۽�+ʧ�ܣ�");
            }
        });
    }
}

function PTZFoucusOut() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(13, false, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " �۽�-�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  �۽�-ʧ�ܣ�");
            }
        });
    }
}

function PTZFoucusStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(12, true, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " �۽�ֹͣ�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  �۽�ֹͣʧ�ܣ�");
            }
        });
    }
}

function PTZIrisIn() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(14, false, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " ��Ȧ+�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  ��Ȧ+ʧ�ܣ�");
            }
        });
    }
}

function PTZIrisOut() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(15, false, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " ��Ȧ-�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  ��Ȧ-ʧ�ܣ�");
            }
        });
    }
}

function PTZIrisStop() {
    var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

    if (oWndInfo != null) {
        WebVideoCtrl.I_PTZControl(14, true, {
            iWndIndex: g_iWndIndex,
            success: function (xmlDoc) {
                showOPInfo(oWndInfo.szIP + " ��Ȧֹͣ�ɹ���");
            },
            error: function () {
                showOPInfo(oWndInfo.szIP + "  ��Ȧֹͣʧ�ܣ�");
            }
        });
    }
}

dateFormat = function (oDate, fmt) {
    var o = {
        "M+": oDate.getMonth() + 1, //�·�
        "d+": oDate.getDate(), //��
        "h+": oDate.getHours(), //Сʱ
        "m+": oDate.getMinutes(), //��
        "s+": oDate.getSeconds(), //��
        "q+": Math.floor((oDate.getMonth() + 3) / 3), //����
        "S": oDate.getMilliseconds()//����
    };
    if(/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (oDate.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if(new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

// �л�ģʽ
function changeIPMode(iType) {
	var arrPort = [0, 7071, 80];

	$("#serverport").val(arrPort[iType]);
}

// ��ȡ�豸IP
function clickGetDeviceIP() {
	var iDeviceMode = parseInt($("#devicemode").val(), 10),
		szAddress = $("#serveraddress").val(),
		iPort = parseInt($("#serverport").val(), 10) || 0,
		szDeviceID = $("#deviceid").val(),
		szDeviceInfo = "";

	szDeviceInfo = WebVideoCtrl.I_GetIPInfoByMode(iDeviceMode, szAddress, iPort, szDeviceID);

	if ("" == szDeviceInfo) {
		showOPInfo("�豸IP�Ͷ˿ڽ���ʧ�ܣ�");
	} else {
		showOPInfo("�豸IP�Ͷ˿ڽ����ɹ���");

		var arrTemp = szDeviceInfo.split("-");
		$("#loginip").val(arrTemp[0]);
		$("#deviceport").val(arrTemp[1]);
	}
}