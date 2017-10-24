<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>工程科汇总上报弹窗</title>

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
<style>
.datagrid-row-selected {
    background: #ffffff none repeat scroll 0 0;
    color: #000000;
}
</style>
	<div align="center">		
		<p><img alt="" src="images/zlogo.png">
		<span id="titles" align="center" style="font-size: 25;">汇&nbsp;&nbsp;总&nbsp;&nbsp;单</span></p>
	</div>
	<input type="hidden" id="type" value="${type}">
	<form id="ff" method="post"
		style="margin: 10px; text-align: center;">
		<!-- <div  style="float: left;" align="left">
			<div style="float: left;margin-bottom:10px;">
			<label>汇总人:</label>
			<input class="easyui-textbox" id="code" data-options="width:500" required="true"  value="">
			</div>
		</div>
		<div  style="float: left;margin-bottom:10px;" align="left">
			<div style="float: left;">
			<label>汇总时间:</label>
			<input class="easyui-textbox" id="code" data-options="width:500" required="true"  value="">
			</div>
		</div>
		<div  style="float: left;margin-bottom:10px;" align="left">
			<div style="float: left;">
			<label>备注:</label>
			<input class="easyui-textbox" id="code" data-options="multiline:true,width:500,height:60" required="true"  value="">
			</div>
		</div> -->
		<div align="center"><table id="departmentPatrol" cellspacing="0" cellpadding="0"></table></div>
		</br>
		<div id="btn">
			<a id="btnAdd" href="javascript:void(0)" onclick="add()" class="easyui-linkbutton"
				data-options="iconCls:'icon_add'">保存</a>&nbsp;&nbsp;<!-- <a id="btnAdd1"
				href="javascript:void(0)" onclick="add(1)" class="easyui-linkbutton"
				data-options="iconCls:'icon_commit'">提交</a>&nbsp;&nbsp; --><a id="btnCancel"
				href="javascript:void(0)" onclick="closed()"  class="easyui-linkbutton"
				data-options="iconCls:'icon_delete'">关闭</a>
		</div>
	</form>
	<script type="text/javascript">
		function add(){
			$.messager.progress();
			var array=[];
			var rows = $('#departmentPatrol').treegrid("getSelections");
			if(rows.length==0){
				$.messager.alert("操作提示", "请最少选择一条记录再进行操作！", "warning");
				$.messager.progress('close');
				return false;
			}
			for (var i = 0; i < rows.length; i++) {
				var level =$('#departmentPatrol').treegrid("getLevel", rows[i].id);
				if(level==3){
					array.push({id:rows[i].id,patrolid:rows[i].patrolid});
				}
			}
			var json = {
					"pds":array,
					"degree":$("#type").val()
			};
			$.ajax({
				type : "POST",
				url : "departmentPatrol/save.html",
				dataType : "text",
				data : {
					"jsonStr" : JSON.stringify(json)
				},
				success : function(msg) {
					data = eval('(' + msg + ')');
					$.messager.progress('close');
					if(data.result){
						reflush();
						$.messager.alert("操作提示", "保存成功", "info");
					}else{
						$.messager.alert("操作提示", msg.msg, "error");
					}
					$('#dialog').window('close');
				}
			});
		}
		function closed(){
			$('#dialog').window('close');
		}
		$(function(){
			var typedata=$("#type").val();
			if(typedata==1){
				$("#titles").html("汇&nbsp;&nbsp;总&nbsp;&nbsp;单");
			}else if(typedata==2){
				$("#titles").html("解&nbsp;&nbsp;决&nbsp;&nbsp;单");
			}else{
				$("#titles").html("明&nbsp;&nbsp;细&nbsp;&nbsp;单");
				$("#btn").hide();
			}
			var jsonstr = '${jsonStr}';
			$('#departmentPatrol').treegrid({    
			    data:JSON.parse(jsonstr),    
			    idField:'id',    
			    treeField:'name',
			    singleSelect : false,
			    width : '605',
				height : '350',
				onClickRow:function(row){  
                    //级联选择  
                    $(this).treegrid('cascadeCheck',{  
                        id:row.id, //节点ID  
                        deepCascade:true //深度级联  
                    });  
                },
			    columns:[[
					{field:'ck',checkbox:true},
			        {title:'名称',field:'name',width:180},   
			        {title:'',field:'content',width:380}  
			    ]]    
			});
			if(typedata==0){
				$('#departmentPatrol').treegrid("hideColumn","ck");
			}
		});
		function reflush() {
			document.getElementById('departmentPatrol.htmlifm').contentWindow.$('#departmentPatrol_list')
					.datagrid('reload');
			document.getElementById('departmentPatrol.htmlifm').contentWindow.$('#departmentPatrol_list')
			.datagrid('unselectAll');
		}
		/** 
	     * 扩展树表格级联勾选方法： 
	     * @param {Object} container 
	     * @param {Object} options 
	     * @return {TypeName}  
	     */  
	    $.extend($.fn.treegrid.methods,{  
	        /** 
	         * 级联选择 
	         * @param {Object} target 
	         * @param {Object} param  
	         *      param包括两个参数: 
	         *          id:勾选的节点ID 
	         *          deepCascade:是否深度级联 
	         * @return {TypeName}  
	         */  
	        cascadeCheck : function(target,param){  
	            var opts = $.data(target[0], "treegrid").options;  
	            if(opts.singleSelect)  
	                return;  
	            var idField = opts.idField;//这里的idField其实就是API里方法的id参数  
	            var status = false;//用来标记当前节点的状态，true:勾选，false:未勾选  
	            var selectNodes = $(target).treegrid('getSelections');//获取当前选中项  
	            for(var i=0;i<selectNodes.length;i++){  
	                if(selectNodes[i][idField]==param.id)  
	                    status = true;  
	            }  
	            //级联选择父节点  
	            selectParent(target[0],param.id,idField,status);  
	            selectChildren(target[0],param.id,idField,param.deepCascade,status);  
	            /** 
	             * 级联选择父节点 
	             * @param {Object} target 
	             * @param {Object} id 节点ID 
	             * @param {Object} status 节点状态，true:勾选，false:未勾选 
	             * @return {TypeName}  
	             */  
	            function selectParent(target,id,idField,status){  
	                var parent = $(target).treegrid('getParent',id);  
	                if(parent){  
	                    var parentId = parent[idField];  
	                    if(status)  
	                        $(target).treegrid('select',parentId);  
	                    else  
	                        $(target).treegrid('unselect',parentId);  
	                    selectParent(target,parentId,idField,status);  
	                }  
	            }  
	            /** 
	             * 级联选择子节点 
	             * @param {Object} target 
	             * @param {Object} id 节点ID 
	             * @param {Object} deepCascade 是否深度级联 
	             * @param {Object} status 节点状态，true:勾选，false:未勾选 
	             * @return {TypeName}  
	             */  
	            function selectChildren(target,id,idField,deepCascade,status){  
	                //深度级联时先展开节点  
	                if(!status&&deepCascade)  
	                    $(target).treegrid('expand',id);  
	                //根据ID获取下层孩子节点  
	                var children = $(target).treegrid('getChildren',id);  
	                for(var i=0;i<children.length;i++){  
	                    var childId = children[i][idField];  
	                    if(status)  
	                        $(target).treegrid('select',childId);  
	                    else  
	                        $(target).treegrid('unselect',childId);  
	                    selectChildren(target,childId,idField,deepCascade,status);//递归选择子节点  
	                }  
	            }  
	        }  
	    });
	</script>
</body>
</html>
