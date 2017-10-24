<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>待办事项-新增</title>

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
	<form id="ffemp" method="post"  enctype="multipart/form-data"
		style="margin-top: 10px; text-align: center;">
		<div>
		<input id="id" value="${todolist.getId()}" type="hidden"/>
		<input id="userIds" type="hidden" value="${users}">
		<input id="fileaddresses" type="hidden" value="${todolist.getFileaddress()}"/>
		<table>
			<tr>
				<td><label for="title">标题:</label></td>
				<td><input id="title" class="easyui-textbox" style="width:300px;" data-options="required:true,prompt:'必填项'" value="${todolist.getTitle()}" /></td>
			</tr>
			<tr>
				<td><label for="users">代办人:</label></td>
				<td><input id="users" style="width:300px;"/></td>
			</tr>
			<tr>
				<td><label for="dodate">待办时间:</label></td>
				<td><input id="dodate"
				class="easyui-datetimebox" name="brand" style="width:300px;" data-options="required:true,editable:false,prompt:'必选项'"
				value="${date}" /></td>
			</tr>
			<tr>
				<td><label for="contents">内容:</label></td>
				<td><input id="contents" style="width:300px;height:100px;" class="easyui-textbox" data-options="multiline:true" value="${todolist.getContents()}"/></td>
			</tr>
		</table>			
		</div>
		<div style="margin-left:100px">
			<table>
				<tr>
					<td>
					<input type='checkbox' style="width:20px;" id="ispc" />PC消息提醒
					<input id="pc" type="hidden" value="${todolist.getIspc()}">
					</td>					
					<td>
					<input type='checkbox' style="width:20px;" id="isphonemess" />手机短信提醒
					<input id="phonemess" type="hidden" value="${todolist.getIsphonemess()}">
					</td>										
				</tr>
			</table>
		</div>
		<br/>						
		<div>
			<input type="file" id="file" onchange="addFile()" style="width:200px;"/>			
		</div>
		<div id="more"></div>
		<br/>							
		<div id="btn" style="text-align: center">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save()">保存</a>&nbsp;&nbsp;<a id="cancel"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">取消</a>
		</div>
	</form>
	<script type="text/javascript">
		var k=1;
		var result=0;
		var files=[];
		var filenames=[];
		$(function() {
			var userIds=undefined;	
			if($("#userIds").val()!=""){
				userIds = eval($("#userIds").val());
			}
			document.getElementById("ispc").checked=$("#pc").val()=="0"?true:false;
			document.getElementById("isphonemess").checked=$("#phonemess").val()=="0"?true:false;
			$('#users').combogrid({
				prompt:'必选项',
				editable:false,
				required:true,
				panelWidth: 300,
	            multiple: true,
	            idField: 'userid',
	            textField: 'name',
	            url:'todolist/userList2.html',
	            method: 'post',
	            columns: [[	          
	                {field:'name',title:'姓名'},            
	            ]],
            	fitColumns: true
			})
			if(userIds!=undefined){
				//alert(userIds);
				$('#users').combogrid('setValues',userIds);
			}			
			var fileaddresses=$("#fileaddresses").val();			
			var f=undefined;
			if(fileaddresses!=""){
				result=1;
				f=fileaddresses.split(",");
				for(var i=0;i<f.length;i++){										
					createInput(f[i]);
				}
				result=0;
			}
			$("#cancel").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});			
		function reflush() {
			document.getElementById('todolist.htmlifm').contentWindow.$('#todolist').datagrid('reload');
		}
		function Save(){
			var id=$("#id").val()==""?"0":$("#id").val();
			var title=$("#title").textbox("getText");
			var dodate=$("#dodate").datebox("getText");
			var contents= $("#contents").textbox("getText");
			var ispc=document.getElementById("ispc").checked==true?"0":"1";
			var isphonemess=document.getElementById("isphonemess").checked==true?"0":"1";
			var dotoer=$('#users').combogrid('getValues');		
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				return false;
			}
			var addresses="";
			if(files.length>0){
				for(var i=0;i<files.length;i++){
					addresses+=files[i]+",";
				}
			}
			if(addresses!=""){
				addresses=addresses.substring(0,addresses.length-1);
			}
			var names="";
			if(filenames.length>0){
				for(var i=0;i<filenames.length;i++){
					names+=filenames[i]+",";
				}
			}
			if(names!=""){
				names=names.substring(0,names.length-1);
			}			
			var data=id+"/"+title+"/"+dodate+"/"+contents+"/"+ispc+"/"+isphonemess+"/"+dotoer+"/"+names+"/"+addresses;			
			/* var uplist=$("input[name^=fileaddress]");		   
		    if(uplist.length>1){
		    	for(var i=0; i< uplist.length-1; i++){
		    		for(var j=i+1;j<uplist.length;j++){
		    			if(uplist[i].value && uplist[j].value){
			    			if(uplist[i].value==uplist[j].value){
			    				alert("文件有重复");
			    				return;
			    			}
		    			}		
		    		}
		    	}
		    } */
		    /*  var arrId=[]; */		    
		    /* for (var i=0; i< uplist.length; i++){  
		        if(uplist[i].value!=""){
		            arrId[i] = uplist[i].value;
		            alert(arrId[i]);		           
		        }
	        } */
		    /* $.ajaxFileUpload({
		        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
		        url:'todolist/save.html',
		        secureuri:false,                       //是否启用安全提交,默认为false
		        //fileElementId:'myBlogImage',           //文件选择框的id属性
		        dataType:'text',                       //服务器返回的格式,可以是json或xml等
		        fileElementId:arrId, */
		       /*  fileElementId:'fileaddress',
				data:{"json" :data},
		        success:function(data, status){        //服务器响应成功时的处理函数
		            data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
		            data = data.replace("</PRE>", '');
		            data = data.replace("<pre>", '');
		            data = data.replace("</pre>", ''); //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
		            if(data.substring(0, 1) == 0){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
		                $("img[id='uploadImage']").attr("src", data.substring(2));
		                $('#result').html("图片上传成功<br/>");
		            }else{
		                $('#result').html("图片上传失败，请重试！！");
		            }
		            reflush();
		    		$('#dialog').window('close');
		        },
		        error:function(data, status, e){ //服务器响应失败时的处理函数
		            $('#result').html('图片上传失败，请重试！！');
		        }
		    });	 */	
		    $.ajax({
				url : "todolist/save.html",
				type : "POST",
				dataType : "text",
				data : {
					"json" :data
				},
				success : function(data1, data2) {
					reflush();
		    		$('#dialog').window('close');
				},
				error : function(data, status, e) { //服务器响应失败时的处理函数
					$('#result').html('图片上传失败，请重试！！');
				}
			}); 
		}		
		function addFile(){
			var fa=document.getElementById("file").value;
			var address=fa.substring(fa.lastIndexOf("\\")+1);
			if(filenames.length>0){
				for(var i=0;i<filenames.length;i++){
					if(address==filenames[i]){
						alert("文件名不能重复");
						return;
					}				
				}
			}
			createInput(fa);
		}
		function createInput(address){						
			var as=undefined;					
			if(result==1){
				as=address.substring(37);				
			}else{
				files.push(address);				
				as=address.substring(address.lastIndexOf("\\")+1);
			}
			filenames.push(as);
			var dd="dd"+k;
			var ss="ss"+k;
			var ssd="ssd"+k;
			var str='<div id="'+dd+'"><label style="width:30px">文件:</label>'+
			'<input type="text" style="width:200px;" value="'+as+'" name="fileaddress" disabled="true" readOnly="true"/>'+
			'<input type="hidden" value="'+address+'" id="'+ss+'"/>'+'<input type="hidden" value="'+result+'" id="'+ssd+'"/>'+
			'<input type="button" value="删除" style="width:50px;" onclick="del('+result+','+k+')"/></div>';
			document.getElementById("more").insertAdjacentHTML("beforeEnd",str);
			k++;
		}
		function del(aa,dd){					
			var ss="ss"+dd;
			var ff=document.getElementById(ss).value;
			var ffname=ff;
			if(aa==0){
				ffname=ff.substring(ff.lastIndexOf("\\")+1);
			}else{
				ffname=ff.substring(37);
			}				
			filenames.splice($.inArray(ffname,filenames),1);
			if(files.length>0){
				for(var i=0;i<files.length;i++){
					if(files[i].substring(files[i].lastIndexOf("\\")+1)==ffname){
						files.splice($.inArray(ff,files),1);
					}
				}
			}														
			var id="dd"+dd;
			var div = document.getElementById(id);
		    while(div.hasChildNodes()) //当div下还存在子节点时 循环继续
		    {
		        div.removeChild(div.firstChild);
		    }
		    document.getElementByIdx_x(id).removeNode(true);
		}			
	</script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
</body>
</html>
