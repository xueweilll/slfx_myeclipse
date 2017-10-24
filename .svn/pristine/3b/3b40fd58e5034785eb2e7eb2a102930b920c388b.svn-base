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

<title>My JSP 'documentInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<input id="documentId" type="hidden" value="${document.getId()}" />
	<input id="userId" type="hidden" value="${document.getId()}" />
	<input id="ty" type="hidden" value="${type}" />
	<form id="ffemp" method="post" enctype="multipart/form-data"
		style="margin-top: 10px; text-align: center;">
		<table cellpadding="0" cellspacing="5" border="0">
			<tr>
				<td><label>公文级别:</label></td>
				<td><input id="level" class="easyui-combobox"
					data-options="required:true" style="width: 305px"value="${document.getLevels()}"></td>
			</tr>

			<tr>
				<td><label for="code">公文编号:</label></td>
				<td><input id="code" class="easyui-textbox" name="code"
					style="width: 305px;"
					data-options="required:true,validType:['userName','length[1,25]'],events:{blur: 
									function(){
										var id = $('#id').val();
										if(id !=null && id.length>0){
											return false;
										}
										$.post('document/yanzheng.html', { code: $('#code').val() }, function(msg){
										    data = eval('(' + msg + ')');
										    if(!data.result)
										    {
										    	$('#code').textbox('clear').textbox('textbox').focus();
										    	$.messager.alert('错误','公文编号已存在请重新输入','error');
										     }
									     }); 
									}}"
					value="${document.getCode()}"></td>
			</tr>
			<tr>
				<td><label for="title">公文标题:</label></td>
				<td><input id="title" class="easyui-textbox" name="title"
					style="width: 305px;"
					data-options="required:true,validType:['userName','length[1,50]']"
					value="${document.getTitle()}"></td>
			</tr>

			<tr>
				<td><label for="receiver">接收人:</label></td>
				<td><input id="receiver" style="width:305px;" /> <input
					id="userIds" type="hidden" value="${users}"></td>
			</tr>
			<tr>
				<td><lable for="type">发送方式:</lable></td>
				<td><input id="type" class="easyui-combobox" name="type"
					style="width:305px;" data-options="required:true"
					value="${document.getIsphonemess()}"></td>
			</tr>
			<tr>
				<td><label for="fileList">文件名</label></td>
				<td>
					<div id="fileList" name="fileList"
						style="border: 0px solid #ccc;"></div> <%-- <input id="textaddress"  class="easyui-textbox" value="${textaddress}"> --%>
						<input id="files" type="hidden" value="${document.getFileaddress()}" />
				</td>
			</tr>
			<tr>
				<td><label for="file">文件:</label></td>
				<td><input id="file" type="file" value="选择文件"
					contentEditable="false" onchange="change();" style="width:305px;"
					data-options="required:true" /> <%-- <input type="button" value="添加附件"
				onclick="createInput('more')" ;/>
			<div id="more"></div> 
			<!-- <input type="file" id='filename' name='filename' value="选择文件" data-options="required:true"> -->
		    <input id="address" type="hidden" value="${address}"> --%> <%-- <input id="text" type='text' value="${address}"> --%>
				</td>
			</tr>
		</table>
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="ajaxFileUpload()">保存</a>&nbsp;&nbsp;<a
				id="can" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		var files = [];
		$(function() {

			var userIds = undefined;
			var address = undefined;
			var id=$("#documentId").val();
			
			if ($("#userIds").val() != "") {
				userIds = eval($("#userIds").val());
			}
			if($("#ty").val() =="1"){
				//filename = eval($("#address").val());
				var filename = $("#files").val();
				//console.info(filename);
				var s=filename.split(',');
				for(var i=0;i<s.length;i++){
					files.push(s[i]);
					var str =  "<div id='"+k+"'>" + s[i] + "<a style='font-color:red;' href='javascript:click(\""
					+ id+"_"+s[i]+ "\")'>下载</a><a style='color:red;' href='javascript:deleteFile(\"" + id+"_"+s[i]
					+ "\","+k+",0)'>删除</a></div>";
					k++;
					console.info(str);
					$("#fileList").append(str);
				}
			}
			else if($("#ty").val() =="2"){
				var filename = $("#files").val();
				
				var s=filename.split(',');
				for(var i=0;i<s.length;i++){
					files.push(s[i]);
					var str =  "<div id='"+k+"'>"+ s[i] + "<a style='font-color:red;' href='javascript:click(\""
					+ id+"_"+s[i]+ "\")'>下载</a></div>";
					console.info(str);
					$("#fileList").append(str);
				}
			}
			$("#title").textbox({
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
							$.post("document/yanzheng.html", {
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
					message : '公文编号已存在.'
				}

			});
			$("#level").combobox({
				url : 'document/level.html',
				valueField : 'id',
				textField : 'text',
				editable : false,
				required : true,

				onSelect : function(node) {
					console.info(node.id);
					$('#receiver').combogrid({
						url : 'document/getUsername.html?leve=' + node.id,
					})
				}
			});
			$('#type').combobox({
				url : 'paramers/receiverType.html',
				valueField : 'id',
				textField : 'text',
				editable : false,
				multiple : true
			})

			$('#receiver').combogrid(
			//alert($("#level").combobox("getValues"));
			{

				url : 'document/getUsername.html?level=0',
				idField : 'id',
				textField : 'ename',
				required : true,
				editable : false,
				multiple : true,
				frozenColumns : [ [ {
					field : 'ck',
					checkbox : true
				} ] ],
				columns : [ [ {
					field : 'dname',
					title : '部门',
					width : 75
				}, 
			      {
					field:'ename',
					title:'员工名',
					width:75
			      }	
				] ],
				fitColumns : true,
			//默认选中value指定的选项

			})
			if (userIds != undefined) {
				$('#receiver').combogrid('setValues', userIds);
			}
			$("#can").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});
		function createInput(parentId) {
			var str = '<div name="div"><font style="font-size:12px;">附件 </font>'
					+ '   '
					+ '<input type="file" contentEditable="false" id="filename'+''+
			'"name="filename'+'"value="" style="width:80 px"/>'
					+ '  '
					+ '<input type="button" value="删除" onclick="removeInput(event)"/>'
					+ '</div>';
			document.getElementById(parentId).insertAdjacentHTML("beforeEnd",
					str);

		}
		/** 
		 * 删除多附件删除框 
		 */
		function removeInput(evt, parentId) {
			alert(parentId);
			var el = evt.target == null ? evt.srcElement : evt.target;
			var div = el.parentNode;
			var cont = document.getElementById(parentId);
			alert(document.getElementById(parentId));
			alert(div);
			if (cont.removeChild(div) == null) {
				return false;
			}
			return true;
		}
		function ajaxFileUpload(id) {
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.alert('错误！', '错误', 'error')
				return false;
			}
			//开始上传文件时显示一个图片,文件上传完成将图片隐藏
			//$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
			//执行上传文件操作的函数filename
			var uplist = $("input[name^=filename]");
			console.info(uplist);
			var arrId = [];
			for (var i = 0; i < uplist.length; i++) {
				if (uplist[i].value) {
					arrId[i] = uplist[i].id;
				}
			}
			var fileaddress = "";
			var f = "";
			console.info(files);
			console.info(files.length);
			for (var i = 0; i < files.length; i++) {
				if (fileaddress == "") {
					fileaddress = getFileName(files[i]);
				} else {
					fileaddress = fileaddress + "," + getFileName(files[i]);
				}
				f += files[i] + ",";
			}

			var s = f.length - 1;
            console.info(f);
			f = f.substring(0, s);
			console.info(f);
			var data1;
			data1 = ($("#documentId").val() == "" ? 0 : $("#documentId").val())
					+ "/" + $("#code").textbox("getValue") + "/"
					+ $("#title").textbox("getValue") + "/"
					+ $("#receiver").combobox("getValues") + "/"
					+ $("#level").combobox("getValues") + "/"
					+ $("#type").combobox("getValues") + "/" + fileaddress;
			$.ajax({
				//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
				url : "document/save.html",
				type : "POST",
				//secureuri : false, //是否启用安全提交,默认为false 
				dataType : "text", //服务器返回的格式,可以是json或xml等
				//fileElementId : files,
				data : {
					jsonStr : data1,
					file : f
				},
				success : function() {
					//服务器响应成功时的处理函数
/* 					$.messager.alert('成功！', '上传成功', 'true');
					data = data.replace("<PRE>", ''); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
					data = data.replace("</PRE>", '');
					data = data.replace("<pre>", '');
					data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
					if (data.substring(0, 1) == 0) { //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
						$("img[id='uploadImage']").attr("src",
								data.substring(2));
						$('#result').html("图片上传成功<br/>");
					} else {
						$('#result').html("图片上传失败，请重试！！");
					} */
					$('#dialog').window('close');
					$.messager.alert("操作提示", "保存成功");
					reflush();
				},
				error : function(data, status, e) { //服务器响应失败时的处理函数
					$('#result').html('图片上传失败，请重试！！');
				}
			});
			
			
		}

		function getBLen(str) {
			var l = str.length;
			var blen = 0;
			for (i = 0; i < l; i++) {
				if ((str.charCodeAt(i) & 0xff00) != 0) {
					blen++;
				}
				blen++;
			}
		}

		function reflush() {
			document.getElementById('document.htmlifm').contentWindow.$(
					'#document_list').datagrid('reload');
		}

		var k=0;
		function change() {
			var file = $("#file").val();
			
			for(var i=0;i<files.length;i++){
				if(getFileName(file) == getFileName(files[i]) || getFileName(file) ==files[i]){
					$.messager.alert('失败！', '文档已存在', 'true');
					$("#file").val("");
					return false;
				}
			}
			files.push(file);
			var fileName = getFileName(file);
			var str = "";		
			//if ($("#documentId").val() == "") {
				str = "<div id='"+k+"'>"+fileName + "<a style='color:red;' href='javascript:deleteFile(\""
						+ myEncoder(file) + "\","+k+",1)'>删除</a><div>";
			//} 
			/* else {
				str = "<div id='"+myEncoder(file)+"'>"+fileName + "<a style='font-color:red;' href='javascript:click(\""
						+ myEncoder(file)
						+ "\")'>下载</a><a style='color:red;' href='javascript:deleteFile(\"" + myEncoder(file)
						+ "\","+k+")'>删除</a></div>";
			} */
			k++;
			$("#fileList").append(str);
			$("#file").val("");
			console.info(files);
		}

		function click(value) {
			
			var link = 'document/download.html?' + 'fileaddress='
					+ myEncoder(value);
			window.location.href=link;
			return false;
		}

		function deleteFile(value,divId,status) {
			document.getElementById(divId).style.display = "none";

			var s=-1;
			for(var i =0;i<files.length;i++){
				
				if(myDecoder(value)== files[i]){
					s= i;							
					break;
				}
			}

			files.splice(s,1);
			var id = $("#documentId").val() 
			if(status == "0"){
				$.ajax({
					url : "document/deleteFile.html?fileaddress="+myEncoder(value),
					type : "GET",				
					dataType : "text", 
					/* data : {
						fileaddress : myEncoder(value)
					}, */
					success : function() {
						
					},
					error : function(data, status, e) { 
						alert("删除失败！");
					}
				});
			}
		}

		function getFileName(o) {
			var pos = o.lastIndexOf("\\");
			return o.substring(pos + 1);
		}

		//附带不用修改浏览器安全配置的javascript代码，兼容ie， firefox全系列

		function getPath(obj) {
			if (obj) {

				if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
					obj.select();

					return document.selection.createRange().text;
				}

				else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
					if (obj.files) {

						return obj.files.item(0).getAsDataURL();
					}
					return obj.value;
				}
				return obj.value;
			}
		}
		//参数obj为input file对象
	</script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
</body>
</html>
