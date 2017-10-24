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

<title>My JSP 'autorityInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<input id="powerId" type="hidden" value="${actor.getId()}" />
	<form id="ffemp" method="post" style="margin: 10px; text-align: left">
		<div>
			<label for="powerName">角色名称:</label> <input id="powerName"
				class="easyui-textbox" type="text" name="powerName"
				value="${actor.getName()}" data-options="validType:['userName','length[0,100]']" />
		</div>
		<div>
			<label for="powerDsp">角色描述:</label> <input id="powerDsp"
				class="easyui-textbox" type="text" name="powerDsp"
				style="height: 60px"
				data-options="multiline:true,validType:['powerDsp','length[1,150]']"
				value="${actor.getRemark()}" />
		</div>
		</br>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;<a id="can"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			//验证角色名称重复
			$("#powerName").textbox({
				required : true,
				onChange : function(newValue, oldValue) {
					var id = $("#powerId").val();
					if (id != null && id.length > 0) {
						return false;
					}
					if (newValue == null || newValue.lenth == 0) {
						return false;
					}
					$.post("power/exsitName.html", {
						name: $('#powerName').val()
					}, function(msg) {
						data = eval('(' + msg + ')');
						if (!data.result) {
							$("#powerName").textbox("clear").textbox(
									"textbox").focus();
							$.messager.alert('错误','角色已存在请重新输入','error');
						}
					});
				}
			});
			$.extend($.fn.validatebox.defaults.rules, {    
				powerDsp : {
					validator : function(value, param) {
						return /^[\u0391-\uFFE5\w]+$/.test(value);
					},
					message : '用户描述只允许汉字、英文字母、数字及下划线。'
				}   
			});  
			//key事件，keydown、keyup等等
			$("#powerDsp").textbox({
				inputEvents : $.extend({}, $.fn.textbox.defaults.inputEvents, {
					keyup : function(e) {
						if (e.keyCode == 13) { // when press ENTER key, accept the inputed value.
							/* $("#Name").textbox('setValue', $(this).val()); */
							alert("enter");
						}

					}
				})
			});
			//验证事件，当textbox发生变化时启用
			var b = true;
			$.extend($.fn.validatebox.defaults.rules, {
				esxit : {
					validator : function(value, param) {
						if (value.length >= 3) {
							$.post("power/exsitName.html", {
								name : value
							}, function(msg) {
								data = eval('(' + msg + ')');
								b = data.result;
							});
							return b;
						} else {
							b = true;
							return b;
						}
					},
					message : '角色已存在.'
				}

			});

			$("#sub").bind(
					"click",
					function() {
						//$.messager.progress();
						var isValid = $("#ffemp").form('validate');
						if (!isValid) {
							$.messager.alert("操作提示","信息输入有误","error");
							$.messager.progress('close');
							return false;
						}
						var data = {
							"id" : ($("#powerId").val() == "" ? 0 : $(
									"#powerId").val()),
							"name" : $("#powerName").textbox("getValue"),
							"remark" : $("#powerDsp").textbox("getValue")
						};
						$.ajax({
							type : "POST",
							url : "power/save.html",
							dataType : "text",
							data : {
								"jsonStr" : JSON.stringify(data)
							},
							success : function() {
								$.messager.progress('close');
								reflush();
								$.messager.alert("操作提示", "保存成功");
								$('#dialog').window('close');
							}
						});
					});

			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});

		function reflush() {
			document.getElementById('power.htmlifm').contentWindow.$(
					'#power_list').datagrid('reload');
		}

		function myformatter(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			var h = date.getHours();
			var M = date.getMinutes();
			var s = date.getSeconds();
			return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
					+ (d < 10 ? ('0' + d) : d) + ' ' + (h < 10 ? ('0' + h) : h)
					+ ':' + (M < 10 ? ('0' + M) : M) + ':'
					+ (s < 10 ? ('0' + s) : s);
		}
	</script>
</body>
</html>
