<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@include file="../header.jsp"%>
    <title>My JSP 'authority1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body onload="bind()">
  <form id="ff" action="aaaa" method="post">
  <input type="hidden" id="aid" value="${aid}">
   <table id="tt" cellpadding="0" cellspacing="5" border="0">
   </table>
  
   <div id="tb">
	<a  onclick="add();" class="easyui-linkbutton" data-options="iconCls:'icon_save',plain:true" >保存</a>
   </div>
   </form>
  <script type="text/javascript">
  			function childrenCheck(demo,node){
  				var nodes =  $("#tt").treegrid("getChildren",node.id);
				for (var i = 1; i <= nodes.length; i++) {
					var demo1 =demo+i*10;
					var temp1=parseInt(parseInt(demo1)/10);
					var parentcheck1 = $("input[name="+temp1+"]");
					if($("#aaa"+demo).is(":checked")){
						$("#aaa"+demo1).prop("checked","checked");
						$(parentcheck1).prop("checked","checked");
					}else{
						var ischecked=false;
						$("#aaa"+demo1).removeAttr("checked");
						for (var k = 1; k < 9; k++) {
							var temp2 = temp1*10+k;
							//alert(temp2);
							if($("#aaa"+temp2).is(":checked")){
								ischecked=true;
							}
						}
						if(!ischecked){
							$(parentcheck1).removeAttr("checked");
						}
					}
				}
  			}
			function checkViews(id){
				//alert(id);
				var str = id.split("aaa");
				var demo=parseInt(str[1]);
				var temp=parseInt(parseInt(str[1])/10);
				var parentcheck = $("input[name="+temp+"]");
				var node = $("#tt").treegrid("find",$(parentcheck).attr("id"));
				//下级方法
				childrenCheck(demo,node);
				//同级方法
				var ischecked1=false;
				for (var k = 1; k < 9; k++) {
					var temp3 = temp*10+k;
					//alert(temp2);
					if($("#aaa"+temp3).is(":checked")){
						ischecked1=true;
					}
				}
				if(!ischecked1){
					$(parentcheck).removeAttr("checked");
				}
				//父级方法
				var parentnode = $("#tt").treegrid("getParent",node.id);
				if(parentnode!=null){
					var demo2 = parseInt($("#"+parentnode.id).attr("name"));
					//childrenCheck(demo,parentnode);
					var nodes = $("#tt").treegrid("getChildren",parentnode.id);
					if(!$("#"+id).is(":checked")){
						var demo3 =demo-(temp-demo2)*10;
						$("#aaa"+demo3).removeAttr("checked");
						//
						var ischecked5=true;
						for (var int = 1; int < 9; int++) {
							var demo4=(demo2*10)+int;
							if($("#aaa"+demo4).is(":checked")){
								ischecked5=fasle;
							}
						}
						if(ischecked5){
							$("#"+parentnode.id).removeAttr("checked");
						}
					}else{
						$(parentcheck).prop("checked","checked");
						var ischecked=true;
						for (var i = 1; i <= nodes.length; i++) {
							var demo4 =demo%10;
							var demo5=parseInt($("#"+parentnode.id).attr("name"));
							var demo6=(demo5+i)*10+demo4;
							if(!$("#aaa"+demo6).is(":checked")){
								ischecked=false;
								//console.info(ischecked);
							}
						}
						if(ischecked){
							var demo3=demo2*10+demo%10;
							$("#aaa"+demo3).prop("checked","checked");
							$("#"+parentnode.id).prop("checked","checked");
							//console.info(demo3);
						}
					}
				}
				
			}
			function checkView() {
				var nodes = $("input[menu=children]");
				for (var int = 0; int < nodes.length; int++) {
					var temp = parseInt($(nodes[int]).attr("name"));
					var node = $("#tt").treegrid("find",
							$(nodes[int]).attr("id"));
					var str = eval(node.obj.val);
					//console.info(temp);
					for (var i = 1; i <= str.length; i++) {
						var demo = temp * 10 + i;
						//console.info(demo);
						if (str[i-1]=="1") {
							$("#aaa" + demo).prop("checked", "checked");
							$("#"+$(nodes[int]).attr("id")).prop("checked", "checked");
						}
					}
				}
				var nodes1 = $("input[menu=parent]");
				
				//console.info(nodes1);
				for (var int = nodes1.length-1; int >= 0; int--) {
					var temp = parseInt($(nodes1[int]).attr("name"));
					var nodes2 =  $("#tt").treegrid("getChildren",nodes1[int].id);
					//console.info(nodes2);
					var ischecked=true;
					for (var i = 0; i < nodes2.length; i++) {
						//console.info(nodes2.length);
						if(!$("#"+nodes2[i].id).is(":checked")){
							ischecked=false;
						}
					}
					for (var k = 1; k < 9; k++) {
						var ischecked1=true;
						for (var i = 1; i <= nodes2.length; i++) {
							var temp1=(temp+i)*10+k;
							//console.info(temp);
							if(!$("#aaa"+temp1).is(":checked")){
								ischecked1=false;
							}
						}
						if(ischecked1){
							var temp1=temp*10+k;
							//console.info(temp1);
							$("#aaa"+temp1).prop("checked", "checked");
						}
					}
					if(ischecked){
						$("#"+nodes1[int].id).prop("checked", "checked");
					}
				}
			}
			function add() {
				$.messager.progress();
				var array = new Array();
				var nodes = $("input[menu=children]");
				//console.info(nodes);
				for (var int = 0; int < nodes.length; int++) {
					var str = "";
					//console.info($(nodes[int]).is(":checked"));
					if ($(nodes[int]).is(":checked")) {
						var temp = parseInt($(nodes[int]).attr("name"));
						//console.info(temp);
						for (var j = 8; j > 0; j--) {
							var demo = temp * 10 + j;
							if ($("#aaa" + demo).is(":checked")) {
								str = "1"+str;
							} else {
								str = "0"+str ;
							}
						}
					}else{
						str = "00000000";
					}
					var node = $("#tt").treegrid("find",
							$(nodes[int]).attr("id"));
					array.push($(nodes[int]).attr("id") + "/" + str + "/"
							+ node.obj.AID + "/"
							+ $("#aid").val());
				}
				var jsonstr = "";
				for (var k = 0; k < array.length; k++) {
					jsonstr = jsonstr + ";" + array[k];
				}
				$.post("power/saveAllot.html", {
					"jsonStr" : jsonstr
				}, function() {
					$.messager.progress('close');
					$("#dialog").window('close');
				});
			};
			var index = 0;
			function bind(){
				$('#tt').treegrid({
							//title : '组织机构列表',
							//iconCls : 'icon-ok',
							//width : 800,
							height : 400,
							nowrap : false,
							rownumbers : true,
							collapsible : true,
							url : 'power/bindAllot.html?id='+$("#aid").val(),
							idField : 'id',
							treeField : 'text',
							fitColumns : true,
							onLoadSuccess:function(){
								checkView();
							},
							toolbar : '#tb',
							columns : [
									[
											{
												field : 'text',
												title : '菜单',
												rowspan : 2,
												width : 350,
												formatter : function(
														val, node) {
													var memo = "parent";
													//console.info($("#tt").treegrid("getChildren",node.id));
													if ($("#tt")
															.treegrid(
																	"getChildren",
																	node.id) == "") {
														memo = "children";
													}
													vals1();
													return '<input type="checkbox" style="width:20;" menu="'
															+ memo
															+ '"  name="'
															+ vals
															+ '" id="'
															+ node.id
															+ '"  onclick=checkSelect(this); />'
															+ val;
												}
											}, {
												title : '权限',
												colspan : 8
											} ],
									[
											{
												field : 'A1',
												title : '保存',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 1;
													return '<input type="checkbox" style="width:10;" onclick=checkViews(id)  id="aaa'+temp+'" />';
												}
											},
											{
												field : 'A2',
												title : '删除',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 2;
													return '<input type="checkbox" style="width:10;" onclick=checkViews(id) id="aaa'+temp+'" />';
												}
											},
											{
												field : 'A3',
												title : '审批',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 3;
													return '<input type="checkbox" style="width:10;" onclick=checkViews(id) id="aaa'+temp+'" />';
												}
											},
											{
												field : 'A4',
												title : '下发',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 4;
													return '<input type="checkbox"style="width:10;" onclick=checkViews(id) id="aaa'+temp+'"/>';
												}
											},
											{
												field : 'A5',
												title : '转发',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 5;
													return '<input type="checkbox" style="width:10;" onclick=checkViews(id) id="aaa'+temp+'"/>';
												}
											},
											{
												field : 'A6',
												title : '提交',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 6;
													return '<input type="checkbox" style="width:10;" onclick=checkViews(id) id="aaa'+temp+'" />';
												}
											},
											{
												field : 'A7',
												title : '打印',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 7;
													return '<input type="checkbox" style="width:10;" onclick=checkViews(id) id="aaa'+temp+'" />';
												}
											},
											{
												field : 'A8',
												title : '导出',
												width : 100,
												align : 'center',
												formatter : function(
														value, row,
														index) {
													var temp = vals * 10 + 8;
													return '<input type="checkbox" style="width:10;" onclick=checkViews(id) id="aaa'+temp+'" />';
												}
											}
 								] ]
						});
			}
			function checkSelect(obj) {
				var nodes = $("#tt").treegrid("getChildren", $(obj).attr("id"));
				var node = $("#tt").treegrid("getParent", $(obj).attr("id"));
				var ischecked=true;
				if(node!=null){
					var nodes1 = $("#tt").treegrid("getChildren", node.id);
					console.info(nodes1.length);
					if($(obj).is(":checked")){
						for (var i = 0; i < nodes1.length; i++) {
							if(!$("#"+nodes1[i].id).is(":checked")){
								ischecked=false;
							}
						}
						if(ischecked){
							$("#"+node.id).prop("checked", "true");
						}
					}else{
						$("#"+node.id).removeAttr("checked");
					}
				}
				//alert(nodes[0].id);
				var count = 0;
				for (var i = 0; i < nodes.length; i++) {
					if ($(obj).is(":checked")) {
						//alert($("#" + nodes[i].id).html());
						$("#" + nodes[i].id).prop("checked", "true");
					} else {
						$("#" + nodes[i].id).removeAttr("checked");
					}
					count = count + 1;
				}
				var temp = parseInt($(obj).attr("name"));
				//alert(temp);
				for (var j = 1; j <= count; j++) {
					for (var int = 1; int < 9; int++) {
						var demo = (temp + j) * 10 + int;
						if ($(obj).is(":checked")) {
							$("#aaa" + demo).prop("checked", "true");
						} else {
							$("#aaa" + demo).removeAttr("checked");
						}
					}
				}
				for (var int = 1; int < 9; int++) {
					var demo = temp * 10 + int;
					if ($(obj).is(":checked")) {
						$("#aaa" + demo).prop("checked", "true");
					} else {
						$("#aaa" + demo).removeAttr("checked");
					}
				}
			}
			var vals = 0;
			function vals1() {
				vals = vals + 1;
			}
		</script>
  </body>
  
</html>
