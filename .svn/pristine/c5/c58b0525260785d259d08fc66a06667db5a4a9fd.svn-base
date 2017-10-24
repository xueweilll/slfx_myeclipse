<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<%@include file="../header.jsp"%>
<title>My JSP 'dispatchinfo.jsp' starting page</title>

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
	<input id="id" type="hidden" value="${id}" />
	<form id="ffemp" method="post" style=" text-align: center;margin:10px">
		<table cellpadding="0" cellspacing="0" border="0">

			<tr>
				<td><label>工程名称:</label></td>
				<td><input id="projectName" class="easyui-textbox"
					data-options="required:true,disabled:true" value="${projectName}"></td>

			</tr>
			<tr>
				<td><label>编号:</label></td>
				<td><input id="code" class="easyui-textbox"
					data-options="disabled:true" value="${code}"></td>

			</tr>
			<tr>
				<td><label>部门名称:</label></td>
				<td><input id="department" class="easyui-textbox"
					data-options="disabled:true" value="${department}" /></td>



			</tr>
			<tr>
				<td><label>创建时间:</label></td>
				<td><input id="createtime" class="easyui-textbox"
					data-options="disabled:true" value="${createtime}" /></td>
			</tr>
			<tr>

				<td><label>申请时间:</label>
				<td><input id="applydate" class="easyui-textbox"
					data-options="disabled:true" value="${applydate}"></td>

			</tr>
			<tr>
				<td><label>施工单位:</label></td>
				<td><input id="constructionunits" class="easyui-textbox"
					data-options="disabled:true" value="${constructionunits}"></td>

			</tr>

			<tr>
				<td><label>申请人:</label></td>
				<td><input id="applyer" class="easyui-textbox"
					data-options="disabled:true" value="${applyer}">
			</tr>
			<tr>

				<td><label>备注:</label>
				<td><input id="memo" class="easyui-textbox"
					data-options="disabled:true" value="${memo}"></td>


			</tr>
		</table>
		</br>
		<div>
			<table id="tenancedetial_list" class="easyui-datagrid"
				cellspacing="0" cellpadding="0">
				<thead>
					<tr>
						<th data-options="field:'itemname',width:70">项目名称</th>
						<th data-options="field:'materials',width:50">材料人工</th>
						<th data-options="field:'unit',width:30">单位</th>
						<th data-options="field:'quantity',width:70">数量</th>
						<th data-options="field:'price',width:70">单价</th>
						<th data-options="field:'totalamount',width:70">总价</th>
						<th data-options="field:'memo',width:50">备注</th>
					</tr>
				</thead>
			</table>
		</div>
		<div>
			<table>
				<tr>
					<td><label>复核意见:</label></td>
					<td><input id="fsuggestion" class="easyui-textbox" 
					data-options="disabled:true,multiline:true"  style="height:80px;"value="${stepmemo}" /></td>
				</tr>
				<tr>
				   <td>	<a id="add" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
					onclick="addsuggestion()">添加复核意见</a></td>
					<td id="addyj" style="display:none;">
					<input id='suggestion' style="height:80px;"data-options="multiline:true"class='easyui-textbox' />
					</td>
				</tr>			
			</table>
		</div>
		<div>
		  
		</div>
		<div id="yj"></div>
		<div id="btn">
			<a id="agree" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick='agree(0)'>同意</a> <a
				id="disagree" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick='agree(1)'>不同意</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$("#tenancedetial_list").datagrid({
				url : 'tenanceReviewList/tenanceReviewInfoList.html?id='+$('#id').val(),
				rownumbers : true,
				width : 'auto',
				height : '200px',
				closed : false,
				cache : false,
				modal : true
			});
		});
		function agree(obj){
			var date={
					"state":obj,
			    	"maintenanceid":$("#id").val(),
			    	"memo":$("#suggestion").textbox("getText").replace(/\s+/g,"")	    	
			}
			$.messager.confirm('确认对话框', '是否复核?', function(r){
				if (r){
					$.ajax({
						type:"POST",
					    url:"tenanceReviewList/agree.html",
					    dataType:"text",
					    data:{
					    	"id":$("#id").val(),
					    	"type":obj,
				             "json":JSON.stringify(date),
					    	"suggest":$("#suggestion").textbox("getText").replace(/\s+/g,"")
					    },
					    success : function() {
							$.messager.show({
		                      title:'我的消息',
		                      msg:'复核完成',
		                      timeout:5000,
		                      showType:'slide'
		                        }); 
							$('#dialog').window('close');
							reflush();
						}
					    });
				}
			});
		}
		function addsuggestion(){
			$("#addyj").show();
		}
		function reflush() {
			document.getElementById('tenanceReviewList.htmlifm').contentWindow.$(
					'#selfdispatch').datagrid('reload');
			          
		}
		
	</script>
</body>
</html>
