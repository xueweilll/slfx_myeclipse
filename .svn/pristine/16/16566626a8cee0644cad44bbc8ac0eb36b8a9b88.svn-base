<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'userInfo.jsp' starting page</title>

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
	<div>		
		<p><img alt="" src="images/zlogo.png">
		<span align="center" style="font-size: 25;">汇&nbsp;&nbsp;总&nbsp;&nbsp;单</span></p>
	</div>
	<input type="hidden" id="jsonStr" value="">
	<form id="ff" method="post"
		style="margin: 10px; text-align: center;">
		<div style="float: left;" align="left">
			<div style="float: left;">
			<label>审核人:</label>
			<input class="easyui-textbox" id="code" data-options="width:500" required="true"  value="">
			</div>
		</div>
		</br>
		<div style="float: left;" align="left">
			<div style="float: left;">
			<label>审核时间:</label>
			<input class="easyui-textbox" id="code" data-options="width:500" required="true"  value="">
			</div>
		</div>
		</br>
		<div style="float: left;" align="left">
			<div style="float: left;">
			<label>备注:</label>
			<input class="easyui-textbox" id="code" data-options="multiline:true,width:500,height:60" required="true"  value="">
			</div>
		</div>
		</br>
		<div style="margin-top:100px"><table id="departmentPatrol" cellspacing="0" cellpadding="0"></table></div>
		</br>
		<div id="btn">
			<a id="btnAdd" href="javascript:void(0)" onclick="add(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon_add'">保存</a>&nbsp;&nbsp;<a id="btnAdd1"
				href="javascript:void(0)" onclick="add(1)" class="easyui-linkbutton"
				data-options="iconCls:'icon_commit'">提交</a>&nbsp;&nbsp;<a id="btnCancel"
				href="javascript:void(0)" onclick="closed()"  class="easyui-linkbutton"
				data-options="iconCls:'icon_delete'">关闭</a>
		</div>
	</form>
	<script type="text/javascript">
		$(function(){
			var jsonstr = '${jsonStr}';
			$('#departmentPatrol').treegrid({    
			    data:JSON.parse(jsonstr),    
			    idField:'id',    
			    treeField:'name',
			    singleSelect : false,
			    width : '600',
				height : '250',
				onClickRow:function(row){  
                    //级联选择  
                    $(this).treegrid('cascadeCheck',{  
                        id:row.id, //节点ID  
                        deepCascade:true //深度级联  
                    });  
                },
			    columns:[[
					{field:'ck',checkbox:true},
			        {title:'名称',field:'name',width:180}   
			    ]], 
			    onSelect: function (index, row) {
	                var opt = $(this).datagrid("options");
	                var rows1 = opt.finder.getTr(this, "", "selected", 1);
	                var rows2 = opt.finder.getTr(this, "", "selected", 2);
	                if (rows1.length > 0) {
	                    $(rows1).each(function () {
	                        var tempIndex = parseInt($(this).attr("datagrid-row"));
	                        if (tempIndex == index) {
	                            $(this).css('color', '#000000');
	                        }
	                    });
	                }
	                if (rows2.length > 0) {
	                    $(rows2).each(function () {
	                        var tempIndex = parseInt($(this).attr("datagrid-row"));
	                        if (tempIndex == index) {
	                            $(this).css('color', '#000000');
	                        }
	                    });
	                }

	            }, //取消选中变灰色
	            onUnselect: function (index, row) {
	                var opt = $(this).datagrid("options");
	                var rows1 = opt.finder.getTr(this, "", "allbody", 1);
	                var rows2 = opt.finder.getTr(this, "", "allbody", 2);
	                if (rows1.length > 0) {
	                    $(rows1).each(function () {
	                        var tempIndex = parseInt($(this).attr("datagrid-row"));
	                        if (tempIndex == index) {
	                            $(this).css('color', '#C0C0C0');
	                        }
	                    });
	                }
	                if (rows2.length > 0) {
	                    $(rows2).each(function () {
	                        var tempIndex = parseInt($(this).attr("datagrid-row"));
	                        if (tempIndex == index) {
	                            $(this).css('color', '#C0C0C0');
	                        }
	                    });
	                }
	            }    
			});
		});
		
		function reflush() {
			document.getElementById('bigSurround.htmlifm').contentWindow.$('#bigSurround_list')
					.datagrid('reload');
			document.getElementById('bigSurround.htmlifm').contentWindow.$('#bigSurround_list')
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
