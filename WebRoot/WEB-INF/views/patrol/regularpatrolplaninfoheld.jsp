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
<title>My JSP 'patrolplaninfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	
		<div>
			
			<table id="table">
				  <input id="look" type="hidden" value="${look}"/> 
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label for="username">当前账号:</label></td>
					<td><input id="username" style="width:280px" disabled="disabled" data-options="editable:false" class="easyui-textbox" required="true" value="${sessionScope.loginUser.username}"/></td>
				</tr>
		 <form id="ffemp" method="post" style="margin:10px; text-align: center;">
			             <input id="id"  style="width:300px" value="${patrolSpecialIssue.getId()}" type="hidden"/>
			      <tr>
					<td>&nbsp;&nbsp;&nbsp;<label for="code">签发单号:</label></td>
					<td><input id="code" style="width:280px" disabled="disabled" data-options="editable:false" class="easyui-textbox" required="true" value="${code} "/></td>
				</tr>      
				<%-- <tr>
					<td>&nbsp;&nbsp;&nbsp;<label for="creater">签发人</label></td>
					<td><input id="creater" style="width:280px"  disabled="true" required="true" value="${sessionScope.loginUser.userid}" /></td>
			
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label for="createtime">签发时间:</label></td>
					<td><input id="createtime" style="width:280px" class="easyui-datetimebox" required="true" value="${patrolSpecialIssue.getCreatetime()}"  /></td>
				</tr> --%>
				<tr>
			        <td>&nbsp;&nbsp;&nbsp;<label for="classes">类别:</label></td>
			   <td><input id="classes" style="width:280px" class="easyui-combobox"  value="${patrolSpecialIssue.getClasses()}" /></td>
				  <%-- <td><select id="classes" class="easyui-combobox" disabled="disabled" name="state" style="width:280px;" data-options="editable:false,panelHeight:100" value="${patrolSpecialIssue.getClasses()} required="true" >
							<option value="0" >汛前</option>
							<option value="1" >汛期</option>
							<option value="2" >汛后</option>
							 <option value="3" disabled="true">特别检查</option> --%>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label for="content">签发内容:</label></td>
					<td><input id="content" style="width:280px" disabled="disabled" required="true" data-options="multiline:true,height:70,editable:false" class="easyui-textbox" value="${patrolSpecialIssue.getContent()}" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;<label for="remark">备注:</label></td>
					<td><input id="remark" style="width:280px" disabled="disabled" data-options="multiline:true,height:70,editable:false" class="easyui-textbox" value="${patrolSpecialIssue.getRemark()}" /></td>
				</tr>											
			</table>					 			 			 						 
		</div>
		</br>
		<!-- <div id="btn" style="text-align: center">
			<a id="sub"  class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save()">保存</a>&nbsp;&nbsp;
			<a id="sub2"
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" >保存并提交</a>&nbsp;&nbsp;
			<a id="cancel" class="easyui-linkbutton"
                data-options="iconCls:'icon-cancel'">取消</a>
		</div> -->
	</form>
	<script type="text/javascript">
		$(function() {
			var id=$('#id').val();	
			$("#cancel").bind("click", function() {
				$("#ffemp").form("clear");
			});
		});	
		/* function reflush() {			
			for (var i = 0; i < parent.grid.length; i++) {
				try{
					parent.grid[i].datagrid("reload");
				}catch (e) {
					continue;
				}
			}
		}  */
		function reflush(){
			document.getElementById('regularpatrolplan.htmlifm').contentWindow
			.$('#patrolplanlist').datagrid('reload');
		}
		$("#classes").combobox({
			valueField:'value',
			textField:'label',
			data:[{
				label:'汛前',
			    value:'0'
			},{
				label:'汛期',
				value:'1'
			},{
				label:'汛后',
			    value:'2'
			}],
			editable:false,
			disabled:true
		});
		$("#sub").bind("click",function() {			
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				return false;
			}
			var data={
				"id":$("#id").val()==""?"0":$("#id").val(),
				//"username":$('#username').textbox('getText'), 
				//"creater":"", 
				//"createtime":"",
				//"creater":[{"userid":$("#")},{"userid":$("#")}]
				//"user":{"userid":"2e6a88b6-5af0-41fc-8c95-b72ec3e76374"},
				"classes":$('#classes').combobox('getValue'),	       
				"content":$('#content').textbox('getText').replace(/\s+/g,""),
				"remark":$('#remark').textbox('getText').replace(/\s+/g,"")
			};
			$.ajax({
				type:"POST",
				url:"regularpatrolplan/save.html",
				dataType:"text",
				data : {
					"json" : JSON.stringify(data)
				},
				success:function(msg){
					$.messager.progress('close');
					reflush();
				
					$('#dialog').window('close');
					 if(msg>="1")
	                    {
	                        $.messager.alert("保存信息", "保存成功", "info");
	                        reflush(); 
	    					$('#dialog').window('close');
	                        /*  $("#sub").datagrid('updateRow',{index:rowIndex,row:{createtime:}}); */
	                       /*  $('#sub').datagrid('reload');  */
	                    }
				}
			});
			
		});
		$("#sub2").bind("click",function() {			
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
				return false;
			}
			var data={
				"id":$("#id").val()==""?"0":$("#id").val(),
				"classes":$('#classes').combobox('getValue'),	       
				"content":$('#content').textbox('getText'),
				"remark":$('#remark').textbox('getText')
			};
			$.ajax({
				type:"POST",
				url:"regularpatrolplan/commit.html",
				dataType:"text",
				data : {
					"json" : JSON.stringify(data)
				},
				success:function(msg){
					$.messager.progress('close');
					reflush();
				
					$('#dialog').window('close');
					 if(msg>="1")
	                    {
	                        $.messager.alert("提交信息", "提交成功", "info");
	                        reflush(); 
	    					$('#dialog').window('close');
	                       
	                    }
				}
			});
			
		})			 
	</script>
	
</body>
</html>
