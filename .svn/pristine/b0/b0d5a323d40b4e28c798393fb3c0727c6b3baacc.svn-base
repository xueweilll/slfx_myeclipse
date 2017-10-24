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
<title>处理巡检记录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


</head>

<body>
	<form id="ffemp" method="post" enctype="multipart/form-data"
		style="margin:10px; text-align:left;">

		<input id="id" value="${patrol.getId()}" type="hidden" />
		<div >
			<label for="patroltime"  style="width:60px;">巡检日期:</label> 
			<input id="patroltime" class="easyui-datetimebox" data-options="required:true,disabled:true,width:190" value="${patroltime}" />&nbsp;&nbsp;
			<label  style="width:60px;">处理人员:</label> <input id="operater" />&nbsp;&nbsp;
			<label  style="width:60px;">处理时间:</label> <input id="operatetime" class="easyui-datetimebox" data-options="required:true,editable:false,width:190"/>
		</div>
		</br>
		<div style="height:350px;"><table id="approval"></table></div>
		</br>
		<div style="width:750px;"><label style="width:60px;float:left;">巡检综述:</label>
		<input class="easyui-textbox" style="width:680px;height:80px;float:left;" data-options="multiline:true,disabled:true" id="memo" value="${patrol.getMemo()}"/>
		</div>
		</br>
		<div id="btn" style="text-align: center;clear:both;">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save1()">保存</a>&nbsp;&nbsp;
				<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="Save2()">保存并提交</a>&nbsp;&nbsp;			
			<a id="cancel" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'" onclick="Cancel()">取消</a>
		</div>
	</form>
	<!-- <script type="text/javascript" src="/easyui/datagrid-cellediting.js"></script> -->
	<script type="text/javascript">
		var editindex1=undefined;
		$.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field){
							col.editor = null;
						}
					}
					$(this).datagrid('beginEdit', param.index);
	                var ed = $(this).datagrid('getEditor', param);
	                if (ed){
	                    if ($(ed.target).hasClass('textbox-f')){
	                        $(ed.target).textbox('textbox').focus();
	                    } else {
	                        $(ed.target).focus();
	                    }
	                }
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			},
	        enableCellEditing: function(jq){
	            return jq.each(function(){
	                var dg = $(this);
	                var opts = dg.datagrid('options');
	                opts.oldOnClickCell = opts.onClickCell;
	                opts.onClickCell = function(index, field){
	                    if (opts.editIndex != undefined){
	                        if (dg.datagrid('validateRow', opts.editIndex)){
	                            dg.datagrid('endEdit', opts.editIndex);	                            
	                            opts.editIndex = undefined;
	                        } else {
	                            return;
	                        }
	                    }
	                    dg.datagrid('selectRow', index).datagrid('editCell', {
	                        index: index,
	                        field: field
	                    });
	                    opts.editIndex = index;
	                    opts.oldOnClickCell.call(this, index, field);
	                }
	            });
	        }
		});
		$(function() {
			var patrolid=$('#id').val();
			$('#operater').combobox({
				url:'resolve/operater.html',
				required:true,
				editable:false,
				multiple:true,
				panelHeight:'auto',
				textField:'name',
				valueField:'userid',
				width:160
			});
			$.post('resolve/findOperaterByPatrolid.html',
			{patrolid:patrolid},	
			function(result){
				if(result!=""){
					$('#operater').combobox('setValues',eval(result));
				}
			})
			$.post('resolve/findOperatetimeByPatrolid.html',
			{patrolid:patrolid},
			function(result){
				if(result!=""){
					$('#operatetime').datetimebox('setValue',result);
				}
			});
			$('#approval').datagrid({
				url:'resolve/patroldetailProblemList3.html?patrolid='+patrolid,
				columns:[[
					{field:'enumid',title:'',hidden:true},
			        {field:'enumid1',title:'巡视检查部位'},
			        {field:'enumid2',title:'巡检内容要求'},
			        {field:'istype',title:'巡视检查记录'},
			        {field:'contents',title:'问题记录'},
			        {field:'handletype',title:'处理方式'},
			        {field:'handlecontents',title:'处理情况',editor:{type:'textbox',options:{required:true}},width:172}
			    ]],
			    toolbar:'#toolbar',
			    /* pageSize : 20, */
			    width : 'auto',
				height : '300',
				singleSelect : true,
				/* pagination : true, */
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false,
				fit : true,
				onClickCell:function(index,field,value){
					editindex1=index;
				}
			})
			.datagrid('enableCellEditing');
		});
		function Save1(){
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
/* 				alert("巡检人员不能为空"); */
				return;
			}
			if(editindex1!=undefined){
				if($('#approval').datagrid('validateRow', editindex1)){
					$('#approval').datagrid('endEdit', editindex1);
					editindex1=undefined;
				}
			}
			var rows=$('#approval').datagrid('getRows');
			var enumid="";
			var handlecontents="";
			for(var i=0;i<rows.length;i++){
				if(rows[i].handlecontents==undefined || trimStr(rows[i].handlecontents)==""){
					alert("处理情况不能为空");
					return;
				}
				enumid+=rows[i].enumid+",";
				handlecontents+=rows[i].handlecontents+",";
			}
			enumid=enumid.substring(0,enumid.length-1);
			handlecontents=handlecontents.substring(0,handlecontents.length-1);
			var operater=$('#operater').combobox('getValues');
			var operatetime=$('#operatetime').datetimebox('getValue');
			var patrolid=$('#id').val();
			var data=patrolid+"/"+enumid+"/"+handlecontents+"/"+operater+"/"+operatetime;
			$.post('resolve/save.html',{
				json:data
			},function(msg){
				if(msg=="true"){
					reflush();
					$('#dialog').window('close');
				}
			});
		}
		function Save2(){
			var isValid = $("#ffemp").form('validate');
			if (!isValid) {
				$.messager.progress('close');
/* 				alert("巡检人员不能为空"); */
				return;
			}
			$.messager.confirm('确定对话框','您确定要提交吗？',function(r){
				if(r){
					if(editindex1!=undefined){
						if($('#approval').datagrid('validateRow', editindex1)){
							$('#approval').datagrid('endEdit', editindex1);
							editindex1=undefined;
						}
					}
					
					var rows=$('#approval').datagrid('getRows');
					var enumid="";
					var handlecontents="";
					for(var i=0;i<rows.length;i++){
						if(rows[i].handlecontents==undefined || trimStr(rows[i].handlecontents)==""){
							alert("处理情况不能为空");
							return;
						}
						enumid+=rows[i].enumid+",";
						handlecontents+=rows[i].handlecontents+",";
					}
					enumid=enumid.substring(0,enumid.length-1);
					handlecontents=handlecontents.substring(0,handlecontents.length-1);
					var operater=$('#operater').combobox('getValues');
					var operatetime=$('#operatetime').datetimebox('getValue');
					var patrolid=$('#id').val();
					var data=patrolid+"/"+enumid+"/"+handlecontents+"/"+operater+"/"+operatetime+"/asdasda";
					$.post('resolve/save.html',{
						json:data
					},function(msg){
						if(msg=="true"){
							reflush();
							$('#dialog').window('close');
						}
					});
				}
			});
		}
		function Cancel(){
			$("#ffemp").form("clear");
		}
		function reflush() {
			document.getElementById('resolve.htmlifm').contentWindow
					.$('#approval').datagrid('reload');
		}
		function trimStr(str){
			return str.replace(/(^\s*)|(\s*$)/g,"");
		}
	</script>

</body>
</html>
