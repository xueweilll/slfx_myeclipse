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

<title>闸门信息</title>

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

	<input id="Id" type="hidden" value="${gate.getId()}" />
	<form id="ffgate" method="post" style="margin: 10px; text-align: left">
		<table cellpadding="0" cellspacing="5" border="0">
						<tr>
				<td><label for="Sid" style="display: inline-block; width: 80px">枢纽名称:</label></td>
				<td><input id="Sid" name="Sid" style="width:250px;"
					value="${gate.getSid()}" /></td>
			</tr>
			<tr>
				<td><label for="Code"
					style="display: inline-block; width: 80px">闸门编号:</label></td>
				<td><input id="Code" name="Code" class="easyui-textbox"
					type="text" style="width:250px;" value="${gate.getCode()}"
					/></td>
			<!-- 		data-options="required:true,events:{blur: 
									function(){
										var id = $('#id').val();
										if(id !=null && id.length>0){
											return false;
										}
										$.get('gate/exsitCode.html', { code: $('#Code').val() }, function(msg){
										    data = eval('(' + msg + ')');
										    if(!data.result)
										    {
										    	$('#Code').textbox('clear').textbox('textbox').focus();;
										    	alert('编号已存在！！！');
										     }
									     }); 
									}}" --> 
			</tr>
			<tr>
				<td><label for="">闸门名称:</label></td>
				<td><input id="Name" class="easyui-textbox" type="text"
					name="Name" data-options="required:true,validType:'length[1,20]'"
					style="width:250px;" value="${gate.getName()}" style="" /></td>
			</tr>

			<tr>
				<td><label for="OnOffWay">启闭方式:</label></td>
				<td><input id="OnOffWay" name="OnOffWay" style="width:250px;"
					value="${gate.getOnoffway()}" /> <%-- <select id="OnOffWay" class="easyui-combobox" name="OnOffWay"
						style="width:250px;" value="${gate.getOnoffway()}">
						<option value="0" selected="true">液压</option>
						<option value="1">卷扬</option>
					</select> --%></td>
			</tr>

		</table>
		&nbsp;
		<div id="btn" style="text-align: center; ">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				id="can" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			var id=$("#Id").val();	
			$("#Sid").combobox({
				url : 'station/stationByFk.html',
				valueField : 'id',
				textField : 'name',
				editable : false,
				/* value : $("#Sid").val(), */
				required : true
			});

			$("#OnOffWay").combobox({
				url : 'paramers/onOffWayData.html',
				valueField : 'id',
				textField : 'text',
				editable : false,
				required : true,
				value : $("#OnOffWay").val()
			});

				$('#Code').textbox({					
					required:true,
					validType:'length[0,20]',
					prompt:'长度在20个字母或汉字之内',		            
					events:{
						blur:function(){
							var pd=$("#Sid").combobox("getValue");
							if(pd==""){
								alert("请选择枢纽");
								$('#Code').textbox('setText','');
								return;
							}
							if(id==""){
								$.post('gate/codeOnlyOne.html',
										{
									        code:$('#Code').val(),
									        sid:$("#Sid").combobox("getValue")
										},
									function(aaa){
										if(aaa=="true"){
											alert("闸门编号已存在，请重新输入");
											$('#Code').textbox('setText','');
											return;
										}
								});
							}else{
								$.post('gate/codeOnlyOne.html',
										{
							        code:$('#Code').val(),
							        sid:$("#Sid").combobox("getValue"),
							        id:id
								},
										function(aaa){
											if(aaa=="true"){
												alert("闸门编号已存在，请重新输入");
												$('#Code').textbox('setText','');
												return;
											}
									});
							}
						}
					
					}
				});
			
			$('#Name').textbox({
				required:true,
				validType:'length[0,20]',
				prompt:'长度在20个字母或汉字之内',
				events:{
					blur:function(){
						var pd=$("#Sid").combobox("getValue");
						if(pd==""){
							alert("请选择枢纽");
							$('#Name').textbox('setText','');
							return;
						}
						if(id==""){
							$.post('gate/nameOnlyOne.html',
									{
								     name:$("#Name").val(),
								     sid:$("#Sid").combobox("getValue")
									},
								function(aaa){
									if(aaa=="true"){
										alert("闸门名称已存在，请重新输入");
										$('#Name').textbox('setText','');
										return;
									}
							});
						}else{
							$.post('gate/nameOnlyOne.html',
									{
							     name:$("#Name").val(),
							     sid:$("#Sid").combobox("getValue"),
							     id:id
								},
									function(aaa){
										if(aaa=="true"){
											alert("闸门名称已存在，请重新输入");
											$('#Name').textbox('setText','');
											return;
										}
								});
						}
					}
				}
			});

			$("#sub").bind("click", function() {
				/* $.messager.progress(); */
				var isValid = $("#ffgate").form('validate');
				if (!isValid) {
					$.messager.progress('close');
					return false;
				}
				var data = {
					"id" : ($("#Id").val() == "" ? 0 : $("#Id").val()),
					"code" : $("#Code").textbox("getValue"),
					"name" : $("#Name").textbox("getValue"),
					"sid" : $("#Sid").combobox("getValue"),
					"onoffway" : $("#OnOffWay").combobox("getValue")
				};

				$.ajax({
					type : "POST",
					url : "gate/save.html",
					dataType : "text",
					data : {
						"jsonStr" : JSON.stringify(data)
					},
					success : function() {
						$.messager.progress('close');
						reflush();
						$('#dialog').window('close');
					}
				});
			});

			$("#can").bind("click", function() {
				$("#ffgate").form("clear");
			});
		});

		//key事件，keydown、keyup等等
		$("#Name").textbox({
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
						$.get("gate/exsitCode.html", {
							code : value
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
				message : '编号已存在.'
			}

		});

		function reflush() {
			document.getElementById('gate.htmlifm').contentWindow.$(
					'#gate_list').datagrid('reload');
		}
	</script>
</body>
</html>
