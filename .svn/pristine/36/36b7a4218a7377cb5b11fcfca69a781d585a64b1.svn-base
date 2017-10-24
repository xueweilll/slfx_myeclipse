<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'image.jsp' starting page</title>
    <%@include file="../header.jsp"%>
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
  <input type="hidden" id="sid" value="${sid}">
    <script type="text/javascript">
    	var array=[];
  		$(function(){
  			//$("#imgs").attr("src","workFlow/viewBind.html?id="+$("#pid").val());
  			$('#dd').hide();
  			$('#dg').treegrid({
					title:'辖区调度执行情况',
					data:this.coutent,
	  			  	idField:'id',
	  			 	treeField:'name',    
	  			  	width: 650,    
	    		    height: 550,
	  			    columns:[[    
	  			        {field:'name',title:'调度区域',width:160},  
	  			        {field:'states',title:'状态',width:100},  
	  			        {field:'starttime',title:'开始时间',width:160},  
	  			        {field:'endtime',title:'结束时间',width:160}  
	  			    ]]
	  			});
  			$.post("dispatchExecute/taskBind.html",{"id":$("#sid").val()},function(data){
  				$json=$(data);
  				$json.each(function(index){
  					if(this.name=="count"){
  						$('#dg').treegrid({
  							data:this.coutent
  			  			});
  						$('#dd').show();
  					}
  				});
  			},"json");
  		});
  	</script>
    <table  id="dg" style=""></table>
  </body>
</html>
