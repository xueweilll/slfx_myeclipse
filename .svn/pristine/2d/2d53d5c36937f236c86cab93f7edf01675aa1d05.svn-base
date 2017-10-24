<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
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
  	<div>
		<p>
			<img alt="" src="images/zlogo.png" style="vertical-align: middle;"> <span align="center"
				style="font-size: 25;vertical-align: middle;">调&nbsp;&nbsp;度&nbsp;&nbsp;令</span>
		</p>
	</div>
    <input id="id" type="hidden" value="${dispatch.getId()}" />
	<input id="state" type="hidden" value="${state}" />
	<form id="ffemp" method="post"
		style="margin-top: 1px;padding-left:10px;padding-right:10px;">
		<table cellpadding="0" cellspacing="1" border="0">
			<tr>
				<td><label>调度令编号:</label></td>
				<td><input id="code" class="easyui-textbox"
					data-options="disabled:true" value="${dispatch.getCode()}"></td>
			</tr>
			<tr>
				<td><label>调度发起时间:</label></td>
				<td><input id="PromoteTime" class="easyui-textbox"
					data-options="disabled:true" value="${promotetime}"></td>
			</tr>
			<tr>
				<td><label>调度发起人:</label></td>
				<td><input id="Promoter" class="easyui-textbox"
					data-options="disabled:true" value="${promoter}" /></td>
			</tr>
			<tr>
				<td><label>调度备注：</label>
				<td><input id="MEMO" class="easyui-textbox"
					data-options="disabled:true" value="${dispatch.getMemo()}"></td>
			</tr>
		</table>
		<div>
			<table id="station_list" cellspacing="0" cellpadding="0">
			</table>
		</div>
		<div id="btn" style="text-align: center; margin-top:5px;">
			<a id="sub" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'">通过</a>
				<a id="Nopass" href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-no'">不通过</a>
		</div>
		
	</form>
	<script type="text/javascript">
	var editIndex1 = undefined;
	var editIndex2 = undefined;
	var aaa=$("#id").val();
		var editIndex1 = undefined;
		$(function() {
			if ($("#state").val() == "1") {
				$("#sub").hide();
				$("#Nopass").hide();
			}

			$("#sub").bind("click", function() {
				$.messager.confirm('确认对话框', '您确定要通过吗？', function(r){
					var stations=$('#station_list').datagrid('getRows');
					var stations1 = [];		
					if(endEditing2()){	
						for(var i=0;i<stations.length;i++){
							
							stations1.push({"sid":stations[i].id,"controlwaterlevel":stations[i].controlwaterlevel,
								"truewaterlevel":stations[i].truewaterlevel
								});
						}
							for (var i = 0; i < stations.length - 1; i++) {
								for (var j = i + 1; j < stations.length; j++) {
									if (stations[i].id == stations[j].id) {
										alert("泵站不能重复!");
										$.messager.progress('close');
										return;
									}
								}
							}
					}
					var date={
						"stations":stations1
					};
					if (r){
						$.ajax({
							type : "POST",
							url : "sdApproval/pass.html",
							dataType : "text",
							data : {
								"id" : $("#id").val(),
								"json" :JSON.stringify(date)
							},
							success : function() {								
								reflush();
								$('#dialog').window('close');
							}
						});
					}
				});
			});
			
			$("#Nopass").bind("click", function() {
				$.messager.confirm('确认对话框', '您确定要不通过吗？', function(r){
					if (r){
						$.ajax({
							type : "POST",
							url : "sdApproval/nopass.html",
							dataType : "text",
							data : {
								"id" : $("#id").val()
							},
							success : function() {								
								reflush();
								$('#dialog').window('close');
							}
						});
					}
				});
			});						
        $('#station_list').datagrid({
    		queryParams : {
				id : $("#id").val()
			},
				columns:[[
			        {   
			        	field:'name',title:'枢纽',width:200,
			        	editor:{type:'combobox',
                            options:{
                                valueField:'id',
                                textField:'name',
                                method:'post',
                                url:'station/stationCombobox.html',
                                required:true,
                                editable:false,
                                onSelect:function(record){                      
                            		var rows=$('#station_list').datagrid('getRows');
    								var k=0;   		
    								for(var i=0;i<rows.length;i++){
    									if(rows[i].name==record.name){
    										k++;
    									}
    								}								
    								if(k>0){
    									alert("枢纽已存在,请重新选择");
    									 $(this).combobox('setValue','');   							
    								}
                                	var ed1=$('#station_list').datagrid('getEditor',{
                                		index: editIndex2,
                                		field:'id'
                                	});
                                	var ed3=$('#station_list').datagrid('getEditor',{
                                		index:editIndex2,
                                	    field:'departmentid'
                                	});
                                	var ed2=$('#station_list').datagrid('getEditor',{
                                		index:editIndex2,
                                		field:'controlwaterlevel'
                                	});
                                	var ed4=$('#station_list').datagrid('getEditor',{
                                		index:editIndex2,
                                		field:'truewaterlevel'
                                	});
                                $(ed1.target).textbox('setText',record.id);
                                $(ed2.target).textbox('setText',record.controlwaterlevel);
                                $(ed4.target).textbox('setText',record.truewaterlevel);
                              
                                }                             
                    }
			       
			        	}
			      }
			        ,{field:'id',
  	            title:'枢纽id',
	            hidden:true,
	         editor:{
		        type:'textbox',
		        options:{
			    editable:false
			    }
            }},
            {field:'controlwaterlevel',title:'控制水位',
            	editor:{
            		type:'textbox',
            		options:{
            			editable:false
            		}
            	}},
            	{field:'truewaterlevel',title:'实际水位',
            		editor:{
            			type:'textbox',
            			options:{
            				editable:false
            			}
            		}},
            {field:'departmentid',
            	title:'部门id',
            	 hidden:true,
            	editor:{
            	type:'textbox',
		        options:{
			    editable:false
            }}}
			    ]],
			    width : '470px',
				height : '200px',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				collapsible : false,
				/* toolbar:[{
		            text:'添加明细',
		            iconCls:'icon-add',
		            handler:append2
		        },{
		            text:'删除明细',
		            iconCls:'icon-cut',
		            handler:removeit2
		        }],
		        iconCls: 'icon-edit',
		        onClickCell: onClickCell2,
		        onEndEdit: onEndEdit2 */		        
			});
			if (aaa != "") {
				$('#station_list')
						.datagrid(
								{
									url : "sdApproval/ddmx.html"
											
								});
			}
		
		});
		
	
			/* $('#station_list').datagrid({
				url:'dispatchinstructions/findStationsByid.html',
				queryParams : {
					id : $("#id").val()
				},
				columns : [ [ {
					field : 'sid',
					title : '枢纽',
					width : 100,
					editor : {
						type : 'combobox',
						options : {
							valueField : 'id',
							textField : 'name',
							method : 'get',
							url : 'station/stationCombobox.html',
							required : true,
							editable : false,
							onSelect:function(record){
								var ed1 = $('#stations').datagrid('getEditor',
										{
											index : editIndex2,
											field : 'controlwater'
										});
										var ed2 = $('#stations').datagrid('getEditor',
										{
											index : editIndex2,
											field : 'truewater'
										});
										$(ed1.target).textbox('setText',record.controlwaterlevel);
										$(ed2.target).textbox('setText',record.truewaterlevel);
								var rows=$('#stations').datagrid('getRows');
								var k=0;
								for(var i=0;i<rows.length;i++){
									if(rows[i].sid==record.name){
										k++;
									}
								}
								if(k>0){
									alert("已存在");
									$(this).combobox('setValue','');
									$(ed1.target).textbox('setText',"");
									$(ed2.target).textbox('setText',"");
								}
								
							}
						}
					}
				},{
					field : 'controlwater',
					title : '控制水位',
					width : 100,
					editor : {
						type : 'textbox',
						options : {
							editable : false,
						}
					}
				},{
					field : 'truewater',
					title : '实际水位',
					width : 100,
					editor : {
						type : 'textbox',
						options : {
							editable : false,
						}						
					}
				} ] ],
				width : '455',
				height : '200',
				singleSelect : true,
				rownumbers : true,
				striped : true,
				border : true,
				collapsible : false
				
			}); 
		}); */
        function onEndEdit2(index, row){
            var ed1 = $(this).datagrid('getEditor', {
                index: index,
                field: 'name'
            });
            row.name = $(ed1.target).combobox('getText');
        }
        function onClickCell2(index, field){
            if (editIndex2 != index){
                if (endEditing2()){
                    $('#station_list').datagrid('selectRow', index).datagrid('beginEdit', index);
                    var ed = $('#station_list').datagrid('getEditor', {index:index,field:field});
                    if (ed){
                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                    }
                    editIndex2 = index;
                } else {
                    setTimeout(function(){
                        $('#station_list').datagrid('selectRow', editIndex2);
                    },0);
                }
            }
        }
        function endEditing2(){
            if (editIndex2 == undefined){return true}
            if ($('#station_list').datagrid('validateRow', editIndex2)){
                $('#station_list').datagrid('endEdit', editIndex2);
                editIndex2 = undefined;
                return true;
            } else {
                return false;
            }
        }
        function append2(){
            if (endEditing2()){
                $('#station_list').datagrid('appendRow',{});
                editIndex2 = $('#station_list').datagrid('getRows').length-1;
                $('#station_list').datagrid('selectRow', editIndex2).datagrid('beginEdit', editIndex2);              
            }
        }
        function removeit2(){
            if (editIndex2 == undefined){return}
            $('#station_list').datagrid('cancelEdit', editIndex2)
                    .datagrid('deleteRow', editIndex2);
                   
            editIndex2 = undefined;
        }
			if ($("#state").val() != "1") {
				$('#station_list').datagrid({
					toolbar : [ {
						text : '添加汇报明细',
						iconCls : 'icon-add',
						handler : append2
					}, {
						text : '删除汇报明细',
						iconCls : 'icon-cut',
						handler : removeit2
					} ],
					iconCls : 'icon-edit',
					onClickCell : onClickCell2,
					onEndEdit : onEndEdit2
				});
			} 
		
		function reflush() {
			document.getElementById('sdApproval.htmlifm').contentWindow
					.$('#selfdispatch').datagrid('reload');
		}
	</script>
  </body>
</html>
